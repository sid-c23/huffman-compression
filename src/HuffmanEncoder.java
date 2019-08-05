import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * HuffmanEncoder.java
 * A Java implementation of the Huffman Compression
 *
 * @author Sidhartha Chaganti <chagantisidhartha@gmail.com>
 */

public class HuffmanEncoder {

    private final int SIZE = 256;

    private int[] frequencyArray;
    private String originalString = "";
    private Node root;
    private Map<Character, String> map = new HashMap<>();
    // need to store the ending compressed data, but not sure which data type to use. Maybe create my own?
    private StringBuilder sb = new StringBuilder();

    public void compress(String input, String output) {
        // Read from the file the String
        readFromFile(input);
        // Build a frequency array
        buildFreqArray();
        // Use frequency array to create Huffman Tree via PriorityQueue
        buildHuffmanTree();
        // Iterate through huffman tree to create map of prefix codes to characters
        createPrefixes();
        // Use map to encode each character in the string
        // Write tree and compressed data to file using defined signature
        encode(output);
    }

    private void readFromFile(String input) {
        try (BufferedReader in = new BufferedReader(new FileReader(input))) {
            String line;
            while ((line = in.readLine()) != null)
                this.originalString += line + "\n";
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void buildFreqArray() {
        frequencyArray = new int[SIZE];
        for (char c : originalString.toCharArray())
            frequencyArray[c]++;
    }

    private void buildHuffmanTree() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (int i = 0; i < SIZE; i++) {
            if (frequencyArray[i] > 0) {
                // Add a node to the priority queue
                pq.add(new Node((char) i, frequencyArray[i], null, null));
            }
        }
        // Now, we need to pull the two least frequent nodes and merge them together under a parent
        while (pq.size() > 1) {
            Node n1 = pq.poll();
            Node n2 = pq.poll();
            Node parent = new Node('\0', n1.frequency + n2.frequency, n1, n2);
            pq.add(parent);
        }
        root = pq.poll();
    }

    private void createPrefixes() {
        createMap(root, "");
    }

    private void createMap(Node node, String mapping) {
        if (node.isLeaf()) {
            // We need to add this to the map
            map.put(node.character, mapping);
        } else {
            // Recursively call the node's children, adding a 0 for the left child and 1 for right
            createMap(node.leftChild, mapping + "0");
            createMap(node.rightChild, mapping + "1");
        }
    }

    private void encode(String output) {
        // 2 bytes for length of map
        // n is the number of unique characters in the string, which we can find by iterating through frequencyArray and incrementing n per nonzero value
        int n = 0;
        for (int val : frequencyArray)
            if (val > 0)
                n++;
        if (n == 0)
            return; // dunno
        int sizeOfMap = 10 * n - 1;

        // Up until now, we have written the length of the map
        // now, we do breadth first search
        // if node is not leaf, add 0
        // if node is leaf, add 1 followed by binary representation of char value
        LinkedList<Node> queue = new LinkedList<>();
        String map = "";
        queue.add(root);
        while (queue.size() > 0) {
            Node check = queue.poll();
            if (check.isLeaf()) {
                map += "1";
                map += Integer.toBinaryString(check.character);
            } else {
                // is not leaf, add 0
                map += "0";
                // add left and right children to queue
                queue.add(check.leftChild);
                queue.add(check.rightChild);
            }
        }
        // map string contains the string we have to write to the file
        // pad extra zeros
        while (map.length() % 8 != 0) {
            map += "0";
        }
        for (char c : originalString.toCharArray()) {
            sb.append(this.map.get(c));
        }
        String finalString = sb.toString();
        // how many 0's are we adding?
        int numZeros = finalString.length() - (finalString.length() % 8);
        if (finalString.length() % 8 == 0)
            numZeros = 0;
        while (finalString.length() % 8 != 0) {
            finalString += "0";
        }
        // We need to write the first 2 bytes as this value
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream(output))) {
            out.writeShort(sizeOfMap);
            // write the map to the file
            for (int i = 0; i < map.length(); i += 8) {
                String byteString = map.substring(i, i + 8);
                int parsedByte = 0xFF & Integer.parseInt(byteString, 2);
                out.write(parsedByte);
            }
            for (int i = 0; i < finalString.length(); i += 8) {
                String byteString = finalString.substring(i, i + 8);
                int parsedByte = 0xFF & Integer.parseInt(byteString, 2);
                out.write(parsedByte);
            }
            out.writeByte(numZeros);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

