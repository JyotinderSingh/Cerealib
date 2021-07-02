import com.cerealib.Field;
import com.cerealib.IntField;

public class Main {
    static void printBytes(byte[] data) {
        for (int i = 0; i < data.length; ++i) {
            System.out.printf("0x%x ", data[i]);
        }
    }

    public static void main(String[] args) {

        Field field = new IntField("Test", 8);

        byte[] data = new byte[100];
        field.getBytes(data, 0);
        printBytes(data);
    }
}
