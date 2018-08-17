package github.com.PERS23.SortingAnimations;

import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import javafx.util.Pair;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class ClassicQuickSort implements SortingAlgorithm {
    @Override
    public void sort(SortingService context, List<Pair<Integer, Rectangle>> sortList, int delay) throws InterruptedException {
        quickSort(context, sortList, delay, 0, sortList.size() - 1);
    }

    private void quickSort(SortingService context, List<Pair<Integer, Rectangle>> sequence, int delay, int a, int b) throws InterruptedException {
        if (a >= b) return; // Sub array is trivially sorted

        int left = a, right = b - 1;
        int pivot = sequence.get(b).getKey();

        while (left <= right) {
            // Scan until reaching value equal or larger than pivot (or right marker)
            while (left <= right && sequence.get(left).getKey() < pivot) {
                left++;
                TimeUnit.MILLISECONDS.sleep(delay);
            }
            // Scan until reaching value equal or smaller than pivot (or left marker)
            while (left <= right && sequence.get(right).getKey() > pivot) {
                right--;
                TimeUnit.MILLISECONDS.sleep(delay);
            }

            if (left <= right) { // Indices did not strictly cross, so swap values and shrink range
                context.requestUISwap(sequence.get(left).getValue(), sequence.get(right).getValue());
                Collections.swap(sequence, left, right);
                left++;
                right--;
            }
        }
        // Put pivot into its final place (currently marked by left index)
        context.requestUISwap(sequence.get(left).getValue(), sequence.get(b).getValue());
        Collections.swap(sequence, left, b);
        // Recurse
        quickSort(context, sequence, delay, a, left - 1);
        quickSort(context, sequence, delay, left + 1, b);
    }
}
