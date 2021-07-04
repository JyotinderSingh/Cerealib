package com.cerealib;

import static com.cerealib.SerializationReader.*;
import static com.cerealib.SerializationWriter.writeBytes;

public class CLString extends CLBase {
    public static final byte CONTAINER_TYPE = ContainerType.STRING;
    public int count;
    private char[] characters;

    private CLString() {
        size += 1 + 4;
    }


    /**
     * Returns the String represented by the CLString.
     *
     * @return
     */
    public String getString() {
        return new String(characters);
    }

    private void updateSize() {
        size += getDataSize();
    }

    public int getBytes(byte[] dest, int pointer) {
        pointer = writeBytes(dest, pointer, CONTAINER_TYPE);
        pointer = writeBytes(dest, pointer, nameLength);
        pointer = writeBytes(dest, pointer, name);
        pointer = writeBytes(dest, pointer, size);
        pointer = writeBytes(dest, pointer, count);
        pointer = writeBytes(dest, pointer, characters);
        return pointer;
    }

    public int getSize() {
        return size;
    }

    public int getDataSize() {
        return characters.length * Type.getSize(Type.CHAR);
    }

    public static CLString Create(String name, String data) {
        CLString string = new CLString();
        string.setName(name);
        string.count = data.length();
        string.characters = data.toCharArray();
        string.updateSize();
        return string;
    }

    public static CLString deserialize(byte[] data, int pointer) {

        // Read the container type.
        byte containerType = data[pointer++];
        assert (containerType == CONTAINER_TYPE);

        CLString result = new CLString();

        // Read in the nameLength.
        result.nameLength = readShort(data, pointer);
        pointer += Type.getSize(Type.SHORT);

        // Read in the name, using the nameLength.
        result.name = readString(data, pointer, result.nameLength).getBytes();
        pointer += result.nameLength;

        // Read the size.
        result.size = readInt(data, pointer);
        pointer += Type.getSize(Type.INTEGER);

        // Read the count.
        result.count = readInt(data, pointer);
        pointer += Type.getSize(Type.INTEGER);

        // Read the data.
        result.characters = new char[result.count];
        readChars(data, pointer, result.characters);

        pointer += result.count * Type.getSize(Type.CHAR);

        return result;
    }
}
