package github.com.PERS23.SortingAnimations;

import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import javafx.util.Pair;

import java.util.List;

public interface SortingAlgorithm {
    // We pass in the hosting service so that we can notify progress back to it.
    void sort(SortingService context, List<Pair<Integer, Rectangle>> sortList, int delayMS) throws InterruptedException;
}
