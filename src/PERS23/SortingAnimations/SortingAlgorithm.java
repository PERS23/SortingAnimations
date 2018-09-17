package github.com.PERS23.SortingAnimations;

import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import javafx.util.Pair;

import java.util.Collections;
import java.util.List;

public abstract class SortingAlgorithm {
    // We pass in the hosting service so that we can notify progress back to it.
    abstract void sort(SortingService context, List<Pair<Integer, Rectangle>> sortList, int delayMS) throws InterruptedException;

    protected Integer getKey(SortingService context, List<Pair<Integer, Rectangle>> sequence, int i) {
        context.incrementArrayAccesses();
        context.notifyUIArrayAccess(sequence.get(i).getValue());
        return sequence.get(i).getKey();
    }

    protected void swap(SortingService context, List<Pair<Integer, Rectangle>> sequence, int a, int b) {
        context.incrementSwapsMade();
        context.requestUISwap(sequence.get(a).getValue(), sequence.get(b).getValue());
        // 4 array accesses in a swap
        context.incrementArrayAccesses();
        context.incrementArrayAccesses();
        context.incrementArrayAccesses();
        context.incrementArrayAccesses();
        Collections.swap(sequence, a, b);
    }

    protected int compare(SortingService context, int a, int b) {
        context.incrementComparisonsMade();
        if (a > b) {
            return 1;
        } else if (a < b) {
            return -1;
        } else {
            return 0;
        }
    }
}
