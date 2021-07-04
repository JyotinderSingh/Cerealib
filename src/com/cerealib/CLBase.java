package com.cerealib;

public abstract class CLBase {
    protected short nameLength;
    protected byte[] name;

    protected int size = 2 + 4;

    /**
     * Returns the name of the entity.
     *
     * @return name of the entity as a String.
     */
    public String getName() {
        return new String(name, 0, nameLength);
    }

    /**
     * Sets the name property of the entity.
     *
     * @param name name for the entity.
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

    public abstract int getSize();
}
