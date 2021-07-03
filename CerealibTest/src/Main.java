import com.cerealib.Array;

import java.util.Random;

public class Main {
    static Random random = new Random();

    static void printBytes(byte[] data) {
        for (int i = 0; i < data.length; ++i) {
            System.out.printf("0x%x ", data[i]);
        }
    }

    public static void main(String[] args) {
        int[] data = new int[50000];
        for (int i = 0; i < data.length; ++i) {
            data[i] = random.nextInt();
        }
        Array array = Array.Integer("Test", data);

        byte[] stream = new byte[array.getSize()];

        array.getBytes(stream, 0);
        printBytes(stream);

    }
}
