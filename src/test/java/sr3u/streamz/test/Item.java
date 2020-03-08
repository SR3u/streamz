package sr3u.streamz.test;

public class Item {
    private String aString;
    private int anInt;
    private long aLong;
    private double aDouble;

    public Item(String aString, int anInt, long aLong, double aDouble) {
        this.aString = aString;
        this.anInt = anInt;
        this.aLong = aLong;
        this.aDouble = aDouble;
    }

    public String getaString() {
        return aString;
    }

    public Item setaString(String aString) {
        this.aString = aString;
        return this;
    }

    public int getAnInt() {
        return anInt;
    }

    public Item setAnInt(int anInt) {
        this.anInt = anInt;
        return this;
    }

    public long getaLong() {
        return aLong;
    }

    public Item setaLong(long aLong) {
        this.aLong = aLong;
        return this;
    }

    public double getaDouble() {
        return aDouble;
    }

    public Item setaDouble(double aDouble) {
        this.aDouble = aDouble;
        return this;
    }
}
