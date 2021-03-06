package com.cerealib;

import static com.cerealib.SerializationReader.*;
import static com.cerealib.SerializationWriter.*;

public class CLField extends CLBase {

    public static final byte CONTAINER_TYPE = ContainerType.FIELD;
    public byte type;
    public byte[] data;

    private CLField() {
    }

    /**
     * Get the byte value from a CLField.
     *
     * @return byte value represented by the CLField.
     */
    public byte getByte() {
        return readByte(data, 0);
    }

    /**
     * Get the short value from a CLField.
     *
     * @return short value represented by the CLField.
     */
    public short getShort() {
        return readShort(data, 0);
    }

    /**
     * Get the char value from a CLField.
     *
     * @return char value represented by the CLField.
     */
    public char getChar() {
        return readChar(data, 0);
    }

    /**
     * Get the int value from a CLField.
     *
     * @return int value represented by the CLField.
     */
    public int getInt() {
        return readInt(data, 0);
    }

    /**
     * Get the long value from a CLField.
     *
     * @return long value represented by the CLField.
     */
    public long getLong() {
        return readLong(data, 0);
    }

    /**
     * Get the float value from a CLField.
     *
     * @return float value represented by the CLField.
     */
    public float getFloat() {
        return readFloat(data, 0);
    }

    /**
     * Get the double value from a CLField.
     *
     * @return double value represented by the CLField.
     */
    public double getDouble() {
        return readDouble(data, 0);
    }

    /**
     * Get the boolean value from a CLField.
     *
     * @return boolean value represented by the CLField.
     */
    public boolean getBoolean() {
        return readBoolean(data, 0);
    }

    /**
     * Write the Field into a byte array.
     * [1 byte CONTAINER TYPE] [2 bytes NAMELENGTH] [NAME] [1 byte TYPE] [DATA]
     *
     * @param dest    destination byte array.
     * @param pointer pointer to a location inside the byte array.
     * @return pointer to the next location inside the byte array.
     */
    public int getBytes(byte[] dest, int pointer) {
        pointer = writeBytes(dest, pointer, CONTAINER_TYPE);
        pointer = writeBytes(dest, pointer, nameLength);
        pointer = writeBytes(dest, pointer, name);
        pointer = writeBytes(dest, pointer, type);
        pointer = writeBytes(dest, pointer, data);

        return pointer;
    }

    /**
     * Helper method to get the size of a particular instance of a Field.
     *
     * @return size of the Field instance.
     */
    public int getSize() {
        assert (data.length == Type.getSize(type));
        return 1 + 2 + name.length + 1 + data.length;
    }

    /**
     * Helper method to create a Byte Field
     *
     * @param name  name of the field
     * @param value data stored in the field
     * @return
     */
    public static CLField Byte(String name, byte value) {
        CLField field = new CLField();
        field.setName(name);
        field.type = Type.BYTE;
        field.data = new byte[Type.getSize(Type.BYTE)];
        writeBytes(field.data, 0, value);
        return field;
    }

    /**
     * Helper method to create a Short Field
     *
     * @param name  name of the field
     * @param value data stored in the field
     * @return
     */
    public static CLField Short(String name, short value) {
        CLField field = new CLField();
        field.setName(name);
        field.type = Type.SHORT;
        field.data = new byte[Type.getSize(Type.SHORT)];
        writeBytes(field.data, 0, value);
        return field;
    }

    /**
     * Helper method to create a Char Field
     *
     * @param name  name of the field
     * @param value data stored in the field
     * @return
     */
    public static CLField Char(String name, char value) {
        CLField field = new CLField();
        field.setName(name);
        field.type = Type.CHAR;
        field.data = new byte[Type.getSize(Type.CHAR)];
        writeBytes(field.data, 0, value);
        return field;
    }

    /**
     * Helper method to create a Integer Field
     *
     * @param name  name of the field
     * @param value data stored in the field
     * @return
     */
    public static CLField Integer(String name, int value) {
        CLField field = new CLField();
        field.setName(name);
        field.type = Type.INTEGER;
        field.data = new byte[Type.getSize(Type.INTEGER)];
        writeBytes(field.data, 0, value);
        return field;
    }

    /**
     * Helper method to create a Long Field
     *
     * @param name  name of the field
     * @param value data stored in the field
     * @return
     */
    public static CLField Long(String name, long value) {
        CLField field = new CLField();
        field.setName(name);
        field.type = Type.LONG;
        field.data = new byte[Type.getSize(Type.LONG)];
        writeBytes(field.data, 0, value);
        return field;
    }

    /**
     * Helper method to create a Float Field
     *
     * @param name  name of the field
     * @param value data stored in the field
     * @return
     */
    public static CLField Float(String name, float value) {
        CLField field = new CLField();
        field.setName(name);
        field.type = Type.FLOAT;
        field.data = new byte[Type.getSize(Type.FLOAT)];
        writeBytes(field.data, 0, value);
        return field;
    }

    /**
     * Helper method to create a Double Field
     *
     * @param name  name of the field
     * @param value data stored in the field
     * @return
     */
    public static CLField Double(String name, double value) {
        CLField field = new CLField();
        field.setName(name);
        field.type = Type.DOUBLE;
        field.data = new byte[Type.getSize(Type.DOUBLE)];
        writeBytes(field.data, 0, value);
        return field;
    }

    /**
     * Helper method to create a Boolean Field
     *
     * @param name  name of the field
     * @param value data stored in the field
     * @return
     */
    public static CLField Boolean(String name, boolean value) {
        CLField field = new CLField();
        field.setName(name);
        field.type = Type.BOOLEAN;
        field.data = new byte[Type.getSize(Type.BOOLEAN)];
        writeBytes(field.data, 0, value);
        return field;
    }

    public static CLField deserialize(byte[] data, int pointer) {

        // Read the container type.
        byte containerType = data[pointer++];
        assert (containerType == CONTAINER_TYPE);

        CLField result = new CLField();

        // Read in the nameLength.
        result.nameLength = readShort(data, pointer);
        pointer += Type.getSize(Type.SHORT);

        // Read in the name, using the nameLength.
        result.name = readString(data, pointer, result.nameLength).getBytes();
        pointer += result.nameLength;

        // Read the type.
        result.type = data[pointer++];

        result.data = new byte[Type.getSize(result.type)];
        readBytes(data, pointer, result.data);
        pointer += Type.getSize(result.type);

        return result;
    }
}
