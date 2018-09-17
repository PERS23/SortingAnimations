package PERS23.SortingAnimations;

import javafx.scene.shape.Rectangle;
import javafx.util.Pair;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ShellSort extends SortingAlgorithm {
    @Override
    void sort(SortingService context, List<Pair<Integer, Rectangle>> sortList, int delayMS) throws InterruptedException {
        for (int gap = sortList.size() / 2; gap > 0; gap /= 2) {
            for (int i = 0; i < sortList.size(); i++) {
                for (int j = i - gap; j >= 0 && compare(context, getKey(context, sortList, j), getKey(context, sortList, j + gap)) > 0; j -= gap) {
                    swap(context, sortList, j, j + gap);
                    TimeUnit.MILLISECONDS.sleep(delayMS);
                }
            }
        }
    }
}
