package github.com.PERS23.SortingAnimations;

import javafx.scene.Node;
import javafx.util.Pair;

import java.util.List;

public interface SortingAlgorithm {
    void sort(SortingService context, List<Pair<Integer, Node>> sortList) throws InterruptedException;
}
