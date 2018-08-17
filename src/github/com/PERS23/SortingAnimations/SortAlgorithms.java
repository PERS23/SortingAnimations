package github.com.PERS23.SortingAnimations;

public enum SortAlgorithms {
    BUBBLE(new BubbleSort(), "Bubble Sort"),
    CLASSIC_QUICK(new ClassicQuickSort(), "(Classic) Quick Sort");

    private String mName;
    private SortingAlgorithm mSorter;

    private SortAlgorithms(SortingAlgorithm sorter, String name) {
        mSorter = sorter;
        mName = name;
    }

    public SortingAlgorithm getSorter() {
        return mSorter;
    }

    @Override
    public String toString() {
        return mName;
    }
}
