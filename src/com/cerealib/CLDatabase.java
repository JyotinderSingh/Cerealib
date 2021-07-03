package com.cerealib;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static com.cerealib.SerializationWriter.writeBytes;

public class CLDatabase {
    public static final byte[] HEADER = "CLDB".getBytes();
    public static final byte CONTAINER_TYPE = ContainerType.DATABASE;
    public short nameLength;
    public byte[] name;
    private int size = 4 + 1 + 2 + 4 + 2;
    private short objectCount;
    private List<CLObject> objects = new ArrayList<CLObject>();

    public CLDatabase(String name) {
        setName(name);
    }

    public void setName(String name) {
        assert (name.length() < Short.MAX_VALUE);

        if (this.name != null) {
            size -= this.name.length;
        }
        nameLength = (short) name.length();
        this.name = name.getBytes();
        size += nameLength;
    }

    public void addObject(CLObject object) {
        objects.add(object);
        size += object.getSize();
        objectCount = (short) objects.size();
    }

    public int getSize() {
        return size;
    }

    public int getBytes(byte[] dest, int pointer) {
        pointer = writeBytes(dest, pointer, HEADER);
        pointer = writeBytes(dest, pointer, CONTAINER_TYPE);
        pointer = writeBytes(dest, pointer, nameLength);
        pointer = writeBytes(dest, pointer, name);
        pointer = writeBytes(dest, pointer, size);

        pointer = writeBytes(dest, pointer, objectCount);
        for (CLObject object : objects) {
            pointer = object.getBytes(dest, pointer);
        }

        return pointer;
    }

}
