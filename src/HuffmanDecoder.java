import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * HuffmanDecoder.java
 * A Java implementation of the Huffman Decompression
 *
 * @author Sidhartha Chaganti <chagantisidhartha@gmail.com>
 */

/*
 * Format of compressed file
 * (2 bytes of length of map)(Map + padded 0s)(Data + padded 0s)(Byte containing number of padded 0s)
 * Map is 0s and 1s; 0 means non-leaf node, 1 means leaf node; the byte after 1 is the character contained in the leaf node
 * we used breadth first search to create map, so order is [root, root.left, root.right, root.left.left, root.left.right, root.right.left, root.right.right...]
 * Steps:
 *   1. Read short for length of map (stored is number of bits map needs, b, ex. 30)
 *   2. So, map + 2 bits is what we read - (b + (8 - (b % 8)) / 8; if b & 8 == 0, we read b / 8 bytes: var is read
 *      a. maybe we create a byte array of size read and read that many bytes; then, we create a string variable and
 *         append the binary string representation of each value in the byte array
 *      b. then, we can use substrings to find the value of each bit, ignoring the last (8 - (b % 8)) bits; [we many
 *         need to hold that expression in a variable because we need conditional operations, see above]
 *   3. Iterate through and recreate the structure
 *   4. I think we need to do the same process with the data because we need individual bits, so need to process it into a string
 *   5. Decode the data using algorithm
 */



public class HuffmanDecoder {

    // Data structure which will hold the tree which is stored as bits
    private Node root;
    private int mapSize;
    // Data structure which will hold the compressed data
    private String result; // Whatever we need to write into file

    public void decompress(String input, String output) {
        // Read compressed data from file into what? Own data structure?
        readFromFile(input); // Probably need more helper methods within this
        // Decode the compressed data using the tree that was included with the data
        decode();

        // Write the uncompressed data into this file
        writeToFile(output);
    }

    private void readFromFile(String input) {
        try (DataInputStream in = new DataInputStream(new FileInputStream(input))) {
            mapSize = (int) in.readShort();
            int leftOver = 8 - (mapSize % 8);
            if (mapSize % 8 == 0)
                leftOver = 0;
            int numBytesToRead = (mapSize + leftOver) / 8;

            // read map
            byte[] byteArr = new byte[numBytesToRead];
            in.read(byteArr, 0, numBytesToRead);
            // now, byteArr contains the bytes of the map [and the padded zeros]
            String mapString = "";
            for (byte b : byteArr) {
                mapString += Integer.toBinaryString(b);
            }
            System.out.println(mapString);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void decode() {

    }

    private void writeToFile(String output) {

    }
}
