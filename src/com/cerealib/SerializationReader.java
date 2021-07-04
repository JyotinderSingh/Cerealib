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
     * reads a short from a byte array.
     *
     * @param src     source byte array.
     * @param pointer pointer offset in the source.
     * @return
     */
    public static short readshort(byte[] src, int pointer) {
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
