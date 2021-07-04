package com.cerealib;

import java.nio.ByteBuffer;

public class SerializationReader {
    /**
     * Reads a byte from a byte array.
     *
     * @param src     source byte array.
     * @param pointer pointer offset in the source.
     * @return
     */
    public static byte readByte(byte[] src, int pointer) {
        return src[pointer];
    }

    /**
     * Copies bytes from a source byte array from a given pointer offset to a destination byte array.
     *
     * @param src     source byte array
     * @param pointer pointer offset in the source.
     * @param dest    destination byte array.
     */
    public static void readBytes(byte[] src, int pointer, byte[] dest) {
        System.arraycopy(src, pointer, dest, 0, dest.length);
    }

    /**
     * Copies shorts from a source byte array from a given pointer offset to a destination short array.
     *
     * @param src     source array
     * @param pointer pointer offset in the source.
     * @param dest    destination array.
     */
    public static void readShorts(byte[] src, int pointer, short[] dest) {
        for (int i = 0; i < dest.length; ++i) {
            dest[i] = readShort(src, pointer);
            pointer += Type.getSize(Type.SHORT);
        }
    }

    /**
     * Copies chars from a source byte array from a given pointer offset to a destination char array.
     *
     * @param src     source array
     * @param pointer pointer offset in the source.
     * @param dest    destination array.
     */
    public static void readChars(byte[] src, int pointer, char[] dest) {
        for (int i = 0; i < dest.length; ++i) {
            dest[i] = readChar(src, pointer);
            pointer += Type.getSize(Type.CHAR);
        }
    }

    /**
     * Copies ints from a source byte array from a given pointer offset to a destination int array.
     *
     * @param src     source array
     * @param pointer pointer offset in the source.
     * @param dest    destination array.
     */
    public static void readInts(byte[] src, int pointer, int[] dest) {
        for (int i = 0; i < dest.length; ++i) {
            dest[i] = readInt(src, pointer);
            pointer += Type.getSize(Type.INTEGER);
        }
    }

    /**
     * Copies longs from a source byte array from a given pointer offset to a destination long array.
     *
     * @param src     source array
     * @param pointer pointer offset in the source.
     * @param dest    destination array.
     */
    public static void readLongs(byte[] src, int pointer, long[] dest) {
        for (int i = 0; i < dest.length; ++i) {
            dest[i] = readLong(src, pointer);
            pointer += Type.getSize(Type.LONG);
        }
    }

    /**
     * Copies floats from a source byte array from a given pointer offset to a destination float array.
     *
     * @param src     source array
     * @param pointer pointer offset in the source.
     * @param dest    destination array.
     */
    public static void readFloats(byte[] src, int pointer, float[] dest) {
        for (int i = 0; i < dest.length; ++i) {
            dest[i] = readFloat(src, pointer);
            pointer += Type.getSize(Type.FLOAT);
        }
    }

    /**
     * Copies doubles from a source byte array from a given pointer offset to a destination double array.
     *
     * @param src     source array
     * @param pointer pointer offset in the source.
     * @param dest    destination array.
     */
    public static void readDoubles(byte[] src, int pointer, double[] dest) {
        for (int i = 0; i < dest.length; ++i) {
            dest[i] = readDouble(src, pointer);
            pointer += Type.getSize(Type.DOUBLE);
        }
    }

    /**
     * Copies booleans from a source byte array from a given pointer offset to a destination boolean array.
     *
     * @param src     source array
     * @param pointer pointer offset in the source.
     * @param dest    destination array.
     */
    public static void readBooleans(byte[] src, int pointer, boolean[] dest) {
        for (int i = 0; i < dest.length; ++i) {
            dest[i] = readBoolean(src, pointer);
            pointer += Type.getSize(Type.BOOLEAN);
        }
    }

    /**
     * reads a short from a byte array.
     *
     * @param src     source byte array.
     * @param pointer pointer offset in the source.
     * @return
     */
    public static short readShort(byte[] src, int pointer) {
        return ByteBuffer.wrap(src, pointer, Type.getSize(Type.SHORT)).getShort();
//        return (short) ((src[pointer] << 8) | src[pointer + 1]);
    }

    /**
     * Reads a char from a byte array.
     *
     * @param src     source byte array.
     * @param pointer pointer offset in the source.
     * @return
     */
    public static char readChar(byte[] src, int pointer) {
        return ByteBuffer.wrap(src, pointer, Type.getSize(Type.CHAR)).getChar();
//        return (char) ((src[pointer] << 8) | (src[pointer + 1]));
    }

    /**
     * Reads an int from a source byte array.
     *
     * @param src     source byte array.
     * @param pointer pointer offset in the source.
     * @return
     */
    public static int readInt(byte[] src, int pointer) {
        return ByteBuffer.wrap(src, pointer, Type.getSize(Type.INTEGER)).getInt();
//        return (int) ((src[pointer]) << 24 | (src[pointer + 1] << 16) | (src[pointer + 2] << 8) | (src[pointer + 3]));
    }

    /**
     * Reads a long from a source byte array.
     *
     * @param src     source byte array.
     * @param pointer pointer offset in the source.
     * @return
     */
    public static long readLong(byte[] src, int pointer) {
        return ByteBuffer.wrap(src, pointer, Type.getSize(Type.LONG)).getLong();
        /*
            return (long) ((long) (src[pointer]) << 56 | ((long) src[pointer + 1] << 48) | ((long) src[pointer + 2] << 40) | ((long) src[pointer + 3] << 32)
            | (src[pointer + 4]) << 24 | (src[pointer + 5] << 16) | (src[pointer + 6] << 8) | (src[pointer + 7])
            );
        */
    }

    /**
     * Reads a float from a source byte array.
     *
     * @param src     source byte array.
     * @param pointer pointer offset in the source.
     * @return
     */
    public static float readFloat(byte[] src, int pointer) {
        return Float.intBitsToFloat(readInt(src, pointer));
    }

    /**
     * Reads a double from a source byte array.
     *
     * @param src     source byte array.
     * @param pointer pointer offset in the source.
     * @return
     */
    public static double readDouble(byte[] src, int pointer) {
        return Double.longBitsToDouble(readLong(src, pointer));
    }

    /**
     * Reads a boolean from a source byte array.
     *
     * @param src     source byte array.
     * @param pointer pointer offset in the source.
     * @return
     */
    public static boolean readBoolean(byte[] src, int pointer) {
        assert (src[pointer] == 0 || src[pointer] == 1);
        return src[pointer] != 0;
    }

    public static String readString(byte[] src, int pointer, int length) {
        return new String(src, pointer, length);
    }
}
