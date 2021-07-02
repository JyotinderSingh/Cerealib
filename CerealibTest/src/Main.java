import static com.jyotindersingh.SerializationWriter.*;

public class Main {
    static void printBytes(byte[] data) {
        for (int i = 0; i < data.length; ++i) {
            System.out.printf("0x%x ", data[i]);
        }
    }

    public static void main(String[] args) {
        byte[] data = new byte[8];

        String name = "abcdef";
        writeBytes(data, 0, name);
        printBytes(data);

    }
}
