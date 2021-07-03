package com.cerealib;

import java.util.ArrayList;
import java.util.List;

import static com.cerealib.SerializationWriter.writeBytes;

public class CLObject {
    public static final byte CONTAINER_TYPE = ContainerType.OBJECT;  // (field, array, object)
    public short nameLength;
    public byte[] name;
    private short fieldCount;
    private List<CLField> fields = new ArrayList<CLField>();
    private short arrayCount;
    private List<CLArray> arrays = new ArrayList<CLArray>();

    private int size = 1 + 2 + 2 + 2;

    public CLObject(String name) {
        setName(name);
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

    public void addField(CLField field) {
        fields.add(field);
        size += field.getSize();
        fieldCount = (short) fields.size();
    }

    public void addArray(CLArray array) {
        arrays.add(array);
        size += array.getSize();
        arrayCount = (short) arrays.size();
    }

    public int getSize() {
        return size;
    }

    public int getBytes(byte[] dest, int pointer) {
        pointer = writeBytes(dest, pointer, CONTAINER_TYPE);
        pointer = writeBytes(dest, pointer, nameLength);
        pointer = writeBytes(dest, pointer, name);

        pointer = writeBytes(dest, pointer, fieldCount);
        for (CLField field : fields) {
            pointer = field.getBytes(dest, pointer);
        }

        pointer = writeBytes(dest, pointer, arrayCount);
        for (CLArray array : arrays) {
            pointer = array.getBytes(dest, pointer);
        }

        return pointer;
    }

}
