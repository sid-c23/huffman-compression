/**
 * HuffmanEncoder.java
 * A Java implementation of the Huffman Compression
 * @author Sidhartha Chaganti <chagantisidhartha@gmail.com>
*/

public class HuffmanEncoder {

  private int[] frequencyArray;
  private String originalString;
  private Node root;
  private Map<Character, Integer> map;
  // need to store the ending compressed data, but not sure which data type to use. Maybe create my own?

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
    encode();
    // Write tree and compressed data to file using defined signature
    writeToFile(output);
  }

  private void readFromFile(String input) {
    
  }

  private void buildFreqArray() {

  }

  private void buildHuffmanTree() {

  }

  private void createPrefixes() {

  }

  private void encode() {

  }

  private void writeToFile(String output) {

  }
}
