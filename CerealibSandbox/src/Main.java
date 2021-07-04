import com.cerealib.*;

import java.util.Random;

public class Main {
    static Random random = new Random();

    static void printBytes(byte[] data) {
        for (int i = 0; i < data.length; ++i) {
            System.out.printf("0x%x ", data[i]);
        }
    }

    public static void serializationTest() {
        int[] data = new int[50000];
        for (int i = 0; i < data.length; ++i) {
            data[i] = random.nextInt();
        }
        CLDatabase database = new CLDatabase("Database");

        CLArray array = CLArray.Integer("RandomNumbers", data);
        CLField field = CLField.Integer("Integer", 8);
        CLField positionx = CLField.Short("xpos", (short) 8);
        CLField positiony = CLField.Short("ypos", (short) 43);

        CLObject object = new CLObject("Entity");
        object.addArray(array);
        object.addArray(CLArray.Char("String", "Hello World!".toCharArray()));
        object.addField(field);
        object.addField(positionx);
        object.addField(positiony);
        object.addString(CLString.Create("Example String", "Testing our CLString class!"));

        database.addObject(object);
        database.addObject(new CLObject("Cerealib1"));
        CLObject a = new CLObject("Cerealib2");
        a.addField(CLField.Boolean("aBool", true));
        database.addObject(a);
        database.addObject(new CLObject("Cerealib2"));
        database.addObject(new CLObject("Cerealib3"));
        database.addObject(new CLObject("Cerealib4"));

        database.serializeToFile("test.cld");
    }

    public static void deserializationTest() {
        CLDatabase database = CLDatabase.deserializeFromFile("test.cld");
        System.out.println("Database: " + database.getName());
        for (CLObject object : database.objects) {
            System.out.println("\n\t" + object.getName());
            for (CLField field : object.fields) {
                System.out.println("\t\t - " + field.getName());
            }
            for (CLArray array : object.arrays) {
                System.out.println("\t\t - " + array.getName());
            }
            for (CLString string : object.strings) {
                System.out.println("\t\t - " + string.getName() + " : " + string.getString());
            }
        }
    }

    public static void main(String[] args) {
//        serializationTest();
//        deserializationTest();

        Sandbox sandbox = new Sandbox();
        sandbox.play();
    }
}
