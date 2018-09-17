package github.com.PERS23.SortingAnimations;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Pair;

import java.util.List;

public class SortingService extends Service<Void> {
    private List<Pair<Integer, Rectangle>> mSortList;
    private SortingAlgorithm mSortingAlgorithm;
    private int mDelayMS;

    // These properties are hosted in the controller, and to update them we have to request it with platform.runlater()
    private IntegerProperty mComparisonsMade;
    private IntegerProperty mSwapsMade;
    private IntegerProperty mArrayAccessesMade;

    private Rectangle mLastAccess;

    public SortingService(List<Pair<Integer, Rectangle>> sortList, SortingAlgorithm sortingAlgorithm, int delayMS,
                          IntegerProperty comparisonsMade, IntegerProperty swapsMade, IntegerProperty arrayAccessesMade) {
        mSortList = sortList;
        mSortingAlgorithm = sortingAlgorithm;
        mDelayMS = delayMS;
        mComparisonsMade = comparisonsMade;
        mSwapsMade = swapsMade;
        mArrayAccessesMade = arrayAccessesMade;
        mLastAccess = null;
    }

    public void updateSortList(List<Pair<Integer, Rectangle>> sortList) {
        mSortList = sortList;
        resetCounts();
    }

    public void updateSortAlgorithm(SortingAlgorithm sortAlgorithm) {
        mSortingAlgorithm = sortAlgorithm;
        resetCounts();
    }

    public void updateDelay(int delayMS) {
        mDelayMS = delayMS;
        resetCounts();
    }

    private void resetCounts() {
        Platform.runLater(() -> {
            mArrayAccessesMade.set(0);
            mSwapsMade.set(0);
            mComparisonsMade.set(0);
        });
    }

    @Override
    protected Task<Void> createTask() {
        final SortingService context = this;
        final SortingAlgorithm algorithm = mSortingAlgorithm;
        final List<Pair<Integer, Rectangle>> list = mSortList;
        final int delayMS = mDelayMS;

        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                algorithm.sort(context, list, delayMS);
                mLastAccess.setFill(Color.BLACK);
                return null;
            }
        };
    }

    public void requestUISwap(Rectangle a, Rectangle b) {
        Platform.runLater(() -> {
            Double tmp = a.getLayoutX();
            a.setLayoutX(b.getLayoutX());
            b.setLayoutX(tmp);
            // make sound
        });
    }

    public void incrementArrayAccesses() {
        Platform.runLater(() -> {
            mArrayAccessesMade.set(mArrayAccessesMade.getValue() + 1);
        });
    }

    /* Used to highlight current accesses as red */
    public void notifyUIArrayAccess(Rectangle currentAccess) {
        Platform.runLater(() -> {
            if (mLastAccess != null) {
                mLastAccess.setFill(Color.BLACK);
            }
        });
        Platform.runLater(() -> { // Staggering them like this so there's some residue red
            currentAccess.setFill(Color.RED);
            mLastAccess = currentAccess;
        });
    }

    public void incrementComparisonsMade() {
        Platform.runLater(() -> {
            mComparisonsMade.set(mComparisonsMade.getValue() + 1);
        });
    }

    public void incrementSwapsMade() {
        Platform.runLater(() -> {
            mSwapsMade.set(mSwapsMade.getValue() + 1);
        });
    }

}
