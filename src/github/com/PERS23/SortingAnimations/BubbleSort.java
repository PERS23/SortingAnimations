package github.com.PERS23.SortingAnimations;

import javafx.scene.Node;
import javafx.util.Pair;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BubbleSort implements SortingAlgorithm {
    @Override
    public void sort(SortingService context, List<Pair<Integer, Node>> sortList) throws InterruptedException {
        final int DELAY_MS = SortAlgorithms.BUBBLE.getDelayMS();

        for (int i = 0; i < sortList.size() - 1; ++i) {
            for (int j = 0; j < sortList.size() - i - 1; j++) {
                Pair<Integer, Node> current = sortList.get(j), next = sortList.get(j + 1);

                context.incrementArrayAccesses();
                context.incrementArrayAccesses();

                if (current.getKey() > next.getKey()) {
                    context.incrementComparisonsMade();

                    Collections.swap(sortList, j, j + 1);
                    context.incrementSwapsMade();
                    context.requestUISwap(current.getValue(), next.getValue());
                }
                                          // Delay happens after each iteration so as to slow it down enough for viewing
                TimeUnit.MILLISECONDS.sleep(DELAY_MS);
            }
        }
    }
}
