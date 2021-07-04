package com.cerealib;

public class SerializationWriter {


    /**
     * Writes a source byte array to a destination byte array.
     *
     * @param dest
     * @param pointer
     * @param src
     * @return
     */
    public static int writeBytes(byte[] dest, int pointer, byte[] src) {
        assert (dest.length >= pointer + src.length);
        for (int i = 0; i < src.length; i++) {
            dest[pointer++] = src[i];
        }
        return pointer;
    }

    /**
     * Writes a source char array to a destination byte array.
     *
     * @param dest
     * @param pointer
     * @param src
     * @return
     */
    public static int writeBytes(byte[] dest, int pointer, char[] src) {
        assert (dest.length >= pointer + src.length);
        for (int i = 0; i < src.length; i++) {
            pointer = writeBytes(dest, pointer, src[i]);
        }
        return pointer;
    }

    /**
     * Writes a source short array to a destination byte array.
     *
     * @param dest
     * @param pointer
     * @param src
     * @return
     */
    public static int writeBytes(byte[] dest, int pointer, short[] src) {
        assert (dest.length >= pointer + src.length);
        for (int i = 0; i < src.length; i++) {
            pointer = writeBytes(dest, pointer, src[i]);
        }
        return pointer;
    }

    /**
     * Writes a source int array to a destination byte array.
     *
     * @param dest
     * @param pointer
     * @param src
     * @return
     */
    public static int writeBytes(byte[] dest, int pointer, int[] src) {
        assert (dest.length >= pointer + src.length);
        for (int i = 0; i < src.length; i++) {
            pointer = writeBytes(dest, pointer, src[i]);
        }
        return pointer;
    }

    /**
     * Writes a source long array to a destination byte array.
     *
     * @param dest
     * @param pointer
     * @param src
     * @return
     */
    public static int writeBytes(byte[] dest, int pointer, long[] src) {
        assert (dest.length >= pointer + src.length);
        for (int i = 0; i < src.length; i++) {
            pointer = writeBytes(dest, pointer, src[i]);
        }
        return pointer;
    }

    /**
     * Writes a source float array to a destination byte array.
     *
     * @param dest
     * @param pointer
     * @param src
     * @return
     */
    public static int writeBytes(byte[] dest, int pointer, float[] src) {
        assert (dest.length >= pointer + src.length);
        for (int i = 0; i < src.length; i++) {
            pointer = writeBytes(dest, pointer, src[i]);
        }
        return pointer;
    }

    /**
     * Writes a source double array to a destination byte array.
     *
     * @param dest
     * @param pointer
     * @param src
     * @return
     */
    public static int writeBytes(byte[] dest, int pointer, double[] src) {
        assert (dest.length >= pointer + src.length);
        for (int i = 0; i < src.length; i++) {
            pointer = writeBytes(dest, pointer, src[i]);
        }
        return pointer;
    }

    /**
     * Writes a source boolean array to a destination byte array.
     *
     * @param dest
     * @param pointer
     * @param src
     * @return
     */
    public static int writeBytes(byte[] dest, int pointer, boolean[] src) {
        assert (dest.length >= pointer + src.length);
        for (int i = 0; i < src.length; i++) {
            pointer = writeBytes(dest, pointer, src[i]);
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
        assert (dest.length >= pointer + Type.getSize(Type.BYTE));
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
        assert (dest.length >= pointer + Type.getSize(Type.SHORT));
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
        assert (dest.length >= pointer + Type.getSize(Type.CHAR));
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
        assert (dest.length >= pointer + Type.getSize(Type.INTEGER));
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
        assert (dest.length >= pointer + Type.getSize(Type.LONG));

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
        assert (dest.length >= pointer + Type.getSize(Type.FLOAT));
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
        assert (dest.length >= pointer + Type.getSize(Type.DOUBLE));
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
        assert (dest.length >= pointer + Type.getSize(Type.BOOLEAN));
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


}
