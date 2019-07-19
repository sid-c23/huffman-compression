/**
 * HuffmanDecoder.java
 * A Java implementation of the Huffman Decompression
 * @author Sidhartha Chaganti <chagantisidhartha@gmail.com>
*/

public class HuffmanDecoder {

  // Data structure which will hold the tree which is stored as bits
  private Node root;
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

  }

  private void decode() {

  }

  private void writeToFile(String output) {

  }
}
