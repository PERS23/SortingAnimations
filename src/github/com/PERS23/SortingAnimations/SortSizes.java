package github.com.PERS23.SortingAnimations;

public enum SortSizes {
    THIRTY(30, "30", 20, 4, 6.0),
    FIFTY(50, "50", 20, 4, 6.0),
    SEVENTY_FIVE(75, "75", 15, 2, 6.0),
    HUNDRED(100, "100", 10, 2, 5.0),
    TWO_HUNDRED_AND_FIFTY(250, "250", 5, 1, 3.0),
    FOUR_HUNDRED_AND_FIFTY(450, "450", 3, 0, 1.5),
    SIX_HUNDRED_AND_FIFTY(650, "650", 2, 0, 1.3);

    private int mSize;
    private String mName;
    private int mWidth;
    private int mSpacing;
    private double mHScale;
    private int mDelayMS;

    SortSizes(int size, String name, int width, int spacing, double hScale) {
        mSize = size;
        mName = name;
        mWidth = width;
        mSpacing = spacing;
        mHScale = hScale;
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

    public double getHScale() {
        return mHScale;
    }

    public int getDelayMS() {
        return mDelayMS;
    }

    @Override
    public String toString() {
        return mName;
    }
}
