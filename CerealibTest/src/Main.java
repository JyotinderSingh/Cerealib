import static com.jyotindersingh.SerializationWriter.*;

public class Main {
    static void printBytes(byte[] data) {
        for (int i = 0; i < data.length; ++i) {
            System.out.printf("0x%x ", data[i]);
        }
    }

    public static void main(String[] args) {
        byte[] data = new byte[16];


        int pointer = writeBytes(data, 0, true);
        pointer = writeBytes(data, pointer, false);

        printBytes(data);
    }
}
