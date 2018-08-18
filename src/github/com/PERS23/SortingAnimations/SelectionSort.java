package github.com.PERS23.SortingAnimations;

import javafx.scene.shape.Rectangle;
import javafx.util.Pair;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SelectionSort extends SortingAlgorithm {
    @Override
    void sort(SortingService context, List<Pair<Integer, Rectangle>> sortList, int delayMS) throws InterruptedException {
        for (int i = 0; i < sortList.size(); ++i) {
            int minIndx = i;
            for (int j = i + 1; j < sortList.size(); ++j) { // Find the smallest key in the remaining unsorted list
                if (compare(context, getKey(context, sortList, j), getKey(context, sortList, minIndx)) < 0) {
                    minIndx = j;
                }
                TimeUnit.MILLISECONDS.sleep(delayMS);
            }
            swap(context, sortList, minIndx, i); // Swap it to the end of the sorted list
            TimeUnit.MILLISECONDS.sleep(delayMS);
        }
    }
}
