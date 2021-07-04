package com.cerealib;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static com.cerealib.SerializationWriter.*;
import static com.cerealib.SerializationReader.*;

public class CLDatabase {
    public static final byte[] HEADER = "CLDB".getBytes();
    public static final byte CONTAINER_TYPE = ContainerType.DATABASE;
    public short nameLength;
    public byte[] name;
    private int size = 4 + 1 + 2 + 4 + 2;
    private short objectCount;
    public List<CLObject> objects = new ArrayList<CLObject>();

    private CLDatabase() {
    }

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

    public String getName() {
        return new String(name, 0, nameLength);
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

    /**
     * Deserializes a CLDatabase present in a byte array.
     *
     * @param data byte array containing the CLDatabase.
     * @return a CLDatabase object containing the deserialized information.
     */
    public static CLDatabase deserialize(byte[] data) {
        int pointer = 0;

        // Making sure we are reading a CLDB File
        assert (readString(data, pointer, HEADER.length).equals(new String(HEADER)));
        pointer += HEADER.length;

        byte containerType = readByte(data, pointer++);
        assert (containerType == CONTAINER_TYPE);

        // Create a new instance of CLDatabase to store the result in.
        CLDatabase result = new CLDatabase();

        // Read in the nameLength.
        result.nameLength = readShort(data, pointer);
        pointer += Type.getSize(Type.SHORT);

        // Read in the name, using the nameLength.
        result.name = readString(data, pointer, result.nameLength).getBytes();
        pointer += result.nameLength;

        // Read in the size of the database.
        result.size = readInt(data, pointer);
        pointer += Type.getSize(Type.INTEGER);

        // Read in the number of objects.
        result.objectCount = readShort(data, pointer);
        pointer += Type.getSize(Type.SHORT);

        for (int i = 0; i < result.objectCount; ++i) {
            CLObject object = CLObject.deserialize(data, pointer);

            // Note: We don't use the addObject method here to add a new object to the list,
            // since it will reset our objectCount - leading to inconsistencies in the 'result' CLDatabase instance's 'objectCount' state.
            result.objects.add(object);
            pointer += object.getSize();
        }


        return result;
    }

    /**
     * Deserializes a CLDatabase present in a file.
     *
     * @param path path to the .cld CLDatabase file.
     * @return a CLDatabase object containing the deserialized information.
     */
    public static CLDatabase deserializeFromFile(String path) {
        byte[] buffer = null;
        try {
            BufferedInputStream stream = new BufferedInputStream(new FileInputStream(path));
            buffer = new byte[stream.available()];
            stream.read(buffer);
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return deserialize(buffer);
    }

}
