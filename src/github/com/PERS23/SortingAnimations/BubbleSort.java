package github.com.PERS23.SortingAnimations;

import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import javafx.util.Pair;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BubbleSort implements SortingAlgorithm {
    @Override
    public void sort(SortingService context, List<Pair<Integer, Rectangle>> sortList, int delay) throws InterruptedException {
        for (int i = 0; i < sortList.size() - 1; ++i) {
            for (int j = 0; j < sortList.size() - i - 1; ++j) {
                Pair<Integer, Rectangle> current = sortList.get(j), next = sortList.get(j + 1);
                context.incrementArrayAccesses();
                context.notifyUIArrayAccess(current.getValue());
                context.incrementArrayAccesses();
                context.notifyUIArrayAccess(next.getValue());

                context.incrementComparisonsMade();
                if (current.getKey() > next.getKey()) {
                    Collections.swap(sortList, j, j + 1);
                    context.incrementSwapsMade();
                    context.requestUISwap(current.getValue(), next.getValue());
                }
                                          // Delay happens after each iteration so as to slow it down enough for viewing
                TimeUnit.MILLISECONDS.sleep(delay);
            }
        }
    }
}
