public class HuffmanDecoderTest {
    public static void main(String[] args) {
        HuffmanDecoder hd = new HuffmanDecoder();

        hd.decompress("output.txt", "input.txt");
    }
}
