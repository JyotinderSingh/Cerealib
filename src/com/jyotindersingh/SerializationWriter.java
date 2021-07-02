package com.jyotindersingh;

public class SerializationWriter {

    public static final byte[] HEADER = "CL".getBytes();
    public static final short VERSION = 0x0100;

    /**
     * Writes a source byte array to a destination byte array.
     *
     * @param dest
     * @param pointer
     * @param src
     * @return
     */
    public static int writeBytes(byte[] dest, int pointer, byte[] src) {
        for (int i = 0; i < src.length; i++) {
            dest[pointer++] = src[i];
        }
        return pointer;
    }

    /**
     * Writes a byte to the destination.
     *
     * @param dest    destination byte array.
     * @param pointer index at which the data is to be written.
     * @param value   data to be written.
     * @return
     */
    public static int writeBytes(byte[] dest, int pointer, byte value) {
        dest[pointer++] = value;
        return pointer;
    }

    /**
     * Writes a short to the destination.
     *
     * @param dest    destination byte array.
     * @param pointer index at which the data is to be written.
     * @param value   data to be written.
     * @return
     */
    public static int writeBytes(byte[] dest, int pointer, short value) {
        dest[pointer++] = (byte) ((value >> 8) & 0xff);
        dest[pointer++] = (byte) ((value >> 0) & 0xff);
        return pointer;
    }

    /**
     * Writes a char to the destination.
     *
     * @param dest    destination byte array.
     * @param pointer index at which the data is to be written.
     * @param value   data to be written.
     * @return
     */
    public static int writeBytes(byte[] dest, int pointer, char value) {
        dest[pointer++] = (byte) ((value >> 8) & 0xff);
        dest[pointer++] = (byte) ((value >> 0) & 0xff);
        return pointer;
    }

    /**
     * Writes a int to the destination.
     *
     * @param dest    destination byte array.
     * @param pointer index at which the data is to be written.
     * @param value   data to be written.
     * @return
     */
    public static int writeBytes(byte[] dest, int pointer, int value) {
        dest[pointer++] = (byte) ((value >> 24) & 0xff);
        dest[pointer++] = (byte) ((value >> 16) & 0xff);
        dest[pointer++] = (byte) ((value >> 8) & 0xff);
        dest[pointer++] = (byte) ((value >> 0) & 0xff);
        return pointer;
    }

    /**
     * Writes a long to the destination.
     *
     * @param dest    destination byte array.
     * @param pointer index at which the data is to be written.
     * @param value   data to be written.
     * @return
     */
    public static int writeBytes(byte[] dest, int pointer, long value) {
        dest[pointer++] = (byte) ((value >> 56) & 0xff);
        dest[pointer++] = (byte) ((value >> 48) & 0xff);
        dest[pointer++] = (byte) ((value >> 40) & 0xff);
        dest[pointer++] = (byte) ((value >> 32) & 0xff);
        dest[pointer++] = (byte) ((value >> 24) & 0xff);
        dest[pointer++] = (byte) ((value >> 16) & 0xff);
        dest[pointer++] = (byte) ((value >> 8) & 0xff);
        dest[pointer++] = (byte) ((value >> 0) & 0xff);
        return pointer;
    }

    /**
     * Writes a float to the destination.
     *
     * @param dest    destination byte array.
     * @param pointer index at which the data is to be written.
     * @param value   data to be written.
     * @return
     */
    public static int writeBytes(byte[] dest, int pointer, float value) {
        int data = Float.floatToIntBits(value);
        return writeBytes(dest, pointer, data);
    }

    /**
     * Writes a double to the destination.
     *
     * @param dest    destination byte array.
     * @param pointer index at which the data is to be written.
     * @param value   data to be written.
     * @return
     */
    public static int writeBytes(byte[] dest, int pointer, double value) {
        long data = Double.doubleToLongBits(value);
        return writeBytes(dest, pointer, data);
    }

    /**
     * Writes a boolean to the destination.
     *
     * @param dest    destination byte array.
     * @param pointer index at which the data is to be written.
     * @param value   data to be written.
     * @return
     */
    public static int writeBytes(byte[] dest, int pointer, boolean value) {
        dest[pointer++] = (byte) (value ? 1 : 0);
        return pointer;
    }

    /**
     * Writes a string to the destination.
     *
     * @param dest    destination byte array
     * @param pointer index at which the data is to be written
     * @param string  data to be written
     * @return
     */
    public static int writeBytes(byte[] dest, int pointer, String string) {
        pointer = writeBytes(dest, pointer, (short) string.length());
        return writeBytes(dest, pointer, string.getBytes());
    }

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
        return (short) ((src[pointer] << 8) | src[pointer + 1]);
    }

    /**
     * Reads a char from a byte array.
     *
     * @param src     source byte array.
     * @param pointer pointer offset in the source.
     * @return
     */
    public static char readChar(byte[] src, int pointer) {
        return (char) ((src[pointer] << 8) | (src[pointer + 1]));
    }

    /**
     * Reads an int from a source byte array.
     *
     * @param src     source byte array.
     * @param pointer pointer offset in the source.
     * @return
     */
    public static int readInt(byte[] src, int pointer) {
        return (int) ((src[pointer]) << 24 | (src[pointer + 1] << 16) | (src[pointer + 2] << 8) | (src[pointer + 3]));
    }

    /**
     * Reads a long from a source byte array.
     *
     * @param src     source byte array.
     * @param pointer pointer offset in the source.
     * @return
     */
    public static long readLong(byte[] src, int pointer) {
        return (long) ((long) (src[pointer]) << 56 | ((long) src[pointer + 1] << 48) | ((long) src[pointer + 2] << 40) | ((long) src[pointer + 3] << 32)
                | (src[pointer + 4]) << 24 | (src[pointer + 5] << 16) | (src[pointer + 6] << 8) | (src[pointer + 7])
        );
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

    public static boolean readBoolean(byte[] src, int pointer) {
        assert (src[pointer] == 0 || src[pointer] == 1);
        return src[pointer] != 0;
    }
}
