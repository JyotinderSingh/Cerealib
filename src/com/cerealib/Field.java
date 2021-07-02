package com.cerealib;

import static com.cerealib.SerializationWriter.*;

public class Field {

    public static final byte CONTAINER_TYPE = ContainerType.FIELD;  // (field, array, object)
    public short nameLength;
    public byte[] name;
    public byte type;
    public byte[] data;

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

    public int getBytes(byte[] dest, int pointer) {
        pointer = writeBytes(dest, pointer, CONTAINER_TYPE);
        pointer = writeBytes(dest, pointer, nameLength);
        pointer = writeBytes(dest, pointer, name);
        pointer = writeBytes(dest, pointer, type);
        pointer = writeBytes(dest, pointer, data);

        return pointer;
    }
}
