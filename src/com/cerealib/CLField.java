package com.cerealib;

import static com.cerealib.SerializationWriter.*;

public class CLField {

    public static final byte CONTAINER_TYPE = ContainerType.FIELD;  // (field, array, object)
    public short nameLength;
    public byte[] name;
    public byte type;
    public byte[] data;

    private CLField() {
    }

    /**
     * Set the name property of the field.
     *
     * @param name name for the field.
     */
    public void setName(String name) {
        assert (name.length() < Short.MAX_VALUE);
        nameLength = (short) name.length();
        this.name = name.getBytes();
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
}
