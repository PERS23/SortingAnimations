package github.com.PERS23.SortingAnimations;

public enum SortSizes {
    THIRTY(30, "30", 20, 4, 4.0),
    FIFTY(50, "50", 20, 4, 4.0);
    /*SEVENTY,
    HUNDRED,
    TWO_HUNDRED,
    FOUR_HUNDRED_AND_FIFTY,
    SIX_HUNDRED;*/

    private int mSize;
    private String mName;
    private int mWidth;
    private int mSpacing;
    private double mHeightScaleFactor;

    SortSizes(int size, String name, int width, int spacing, double heightScaleFactor) {
        mSize = size;
        mName = name;
        mWidth = width;
        mSpacing = spacing;
        mHeightScaleFactor = heightScaleFactor;
    }

    public int getSize() {
        return mSize;
    }

    public int getWidth() {
        return mWidth;
    }

    public int getSpacing() {
        return mSpacing;
    }

    public double getHeightScaleFactor() {
        return mHeightScaleFactor;
    }

    @Override
    public String toString() {
        return mName;
    }
}
