package github.com.PERS23.SortingAnimations;

public enum SortAlgorithms {
    BUBBLE(10, new BubbleSort()),
    QUICK(1, new QuickSort());

    private int mDelayMS;
    private SortingAlgorithm mSorter;

    private SortAlgorithms(int delayMS, SortingAlgorithm sorter) {
        mDelayMS = delayMS;
        mSorter = sorter;
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
}
