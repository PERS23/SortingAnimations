package github.com.PERS23.SortingAnimations;

public enum SortAlgorithms {
    BUBBLE(20, new BubbleSort(), "Bubble Sort"),
    QUICK(1, new QuickSort(), "Quick Sort");

    private String mName;
    private int mDelayMS;
    private SortingAlgorithm mSorter;

    private SortAlgorithms(int delayMS, SortingAlgorithm sorter, String name) {
        mDelayMS = delayMS;
        mSorter = sorter;
        mName = name;
    }

    public int getDelayMS() {
        return mDelayMS;
    }

    public void setDelayMS(int delayMS) {
        mDelayMS = delayMS;
    }

    public SortingAlgorithm getSorter() {
        return mSorter;
    }

    @Override
    public String toString() {
        return mName;
    }
}
