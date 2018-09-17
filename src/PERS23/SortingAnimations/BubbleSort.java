package PERS23.SortingAnimations;

import javafx.scene.shape.Rectangle;
import javafx.util.Pair;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class BubbleSort extends SortingAlgorithm {
    @Override
    public void sort(SortingService context, List<Pair<Integer, Rectangle>> sortList, int delay) throws InterruptedException {
        for (int i = 0; i < sortList.size() - 1; ++i) {
            for (int j = 0; j < sortList.size() - i - 1; ++j) {
                if (compare(context, getKey(context, sortList, j), getKey(context, sortList, j + 1)) > 0) {
                    swap(context, sortList, j, j + 1);
                }
                                          // Delay happens after each iteration so as to slow it down enough for viewing
                TimeUnit.MILLISECONDS.sleep(delay);
            }
        }
    }
}
