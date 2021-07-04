package com.cerealib;

import java.util.ArrayList;
import java.util.List;

import static com.cerealib.SerializationReader.*;
import static com.cerealib.SerializationWriter.writeBytes;

public class CLObject {
    public static final byte CONTAINER_TYPE = ContainerType.OBJECT;
    public short nameLength;
    public byte[] name;
    private int size = 1 + 2 + 4 + 2 + 2 + 2;
    private short fieldCount;
    public List<CLField> fields = new ArrayList<CLField>();
    private short stringCount;
    public List<CLString> strings = new ArrayList<CLString>();
    private short arrayCount;
    public List<CLArray> arrays = new ArrayList<CLArray>();

    private static final int sizeOffset = 1 + 2 + 4;

    private CLObject() {
    }

    public CLObject(String name) {
        setName(name);
    }

    /**
     * Returns the name of the CLObject.
     *
     * @return
     */
    public String getName() {
        return new String(name, 0, nameLength);
    }

    /**
     * Set the name property of the Object.
     *
     * @param name name for the Object.
     */
    public void setName(String name) {
        assert (name.length() < Short.MAX_VALUE);

        if (this.name != null) {
            size -= this.name.length;
        }
        nameLength = (short) name.length();
        this.name = name.getBytes();
        size += nameLength;
    }

    /**
     * Adds a CLField to the CLObject instance.
     *
     * @param field
     */
    public void addField(CLField field) {
        fields.add(field);
        size += field.getSize();
        fieldCount = (short) fields.size();
    }

    /**
     * Adds a CLString to the CLObject instance.
     *
     * @param string
     */
    public void addString(CLString string) {
        strings.add(string);
        size += string.getSize();
        stringCount = (short) strings.size();
    }

    /**
     * Adds a CLArray to the CLObject instance.
     *
     * @param array
     */
    public void addArray(CLArray array) {
        arrays.add(array);
        size += array.getSize();
        arrayCount = (short) arrays.size();
    }

    public int getSize() {
        return size;
    }

    /**
     * Writes a CLObject into a stream of bytes.
     *
     * @param dest    destination byte array where the object will be stored.
     * @param pointer offset into the destination array where writing will start.
     * @return
     */
    public int getBytes(byte[] dest, int pointer) {
        pointer = writeBytes(dest, pointer, CONTAINER_TYPE);
        pointer = writeBytes(dest, pointer, nameLength);
        pointer = writeBytes(dest, pointer, name);
        pointer = writeBytes(dest, pointer, size);

        pointer = writeBytes(dest, pointer, fieldCount);
        for (CLField field : fields) {
            pointer = field.getBytes(dest, pointer);
        }

        pointer = writeBytes(dest, pointer, stringCount);
        for (CLString string : strings) {
            pointer = string.getBytes(dest, pointer);
        }

        pointer = writeBytes(dest, pointer, arrayCount);
        for (CLArray array : arrays) {
            pointer = array.getBytes(dest, pointer);
        }

        return pointer;
    }

    /**
     * Deserializes a stream of bytes into a CLObject.
     *
     * @return
     */
    public static CLObject deserialize(byte[] data, int pointer) {
        byte containerType = data[pointer++];
        assert (containerType == CONTAINER_TYPE);

        // Create a new instance of the CLObject to store the result in.
        CLObject result = new CLObject();

        // Read in the nameLength.
        result.nameLength = readShort(data, pointer);
        pointer += Type.getSize(Type.SHORT);

        // Read in the name, using the nameLength.
        result.name = readString(data, pointer, result.nameLength).getBytes();
        pointer += result.nameLength;

        // Read in the size of the Object.
        result.size = readInt(data, pointer);
        pointer += Type.getSize(Type.INTEGER);

        // Read in the fieldCount.
        result.fieldCount = readShort(data, pointer);
        pointer += Type.getSize(Type.SHORT);

        // Deserialize fields.
        for (int i = 0; i < result.fieldCount; ++i) {
            CLField field = CLField.deserialize(data, pointer);
            result.fields.add(field);
            pointer += field.getSize();
        }

        // Read in the stringCount.
        result.stringCount = readShort(data, pointer);
        pointer += Type.getSize(Type.SHORT);

        // Deserialize strings.
        for (int i = 0; i < result.stringCount; ++i) {
            CLString string = CLString.deserialize(data, pointer);
            result.strings.add(string);
            pointer += string.getSize();
        }

        // Read in the arrayCount.
        result.arrayCount = readShort(data, pointer);
        pointer += Type.getSize(Type.SHORT);

        // Deserialize arrays.
        for (int i = 0; i < result.arrayCount; ++i) {
            CLArray array = CLArray.deserialize(data, pointer);
            result.arrays.add(array);
            pointer += array.getSize();
        }

        return result;
    }

}
