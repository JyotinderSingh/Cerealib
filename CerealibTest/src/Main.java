import static com.cerealib.SerializationWriter.*;

public class Main {
    static void printBytes(byte[] data) {
        for (int i = 0; i < data.length; ++i) {
            System.out.printf("0x%x ", data[i]);
        }
    }

    public static void main(String[] args) {
        byte[] data = new byte[8];

        writeBytes(data, 0, 10000);
        printBytes(data);

        byte[] bytes = new byte[]{0x0, 0x0, 0x27, 0x10};
        int readBack = readInt(bytes, 0);
        System.out.println(readBack);

    }
}
