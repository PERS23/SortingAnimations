package github.com.PERS23.SortingAnimations;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.Node;
import javafx.util.Pair;

import java.util.List;

public class SortingService extends Service<Void> {
    private List<Pair<Integer, Node>> mSortList;
    private SortingAlgorithm mSortingAlgorithm;

    private IntegerProperty mSwapsMade = new SimpleIntegerProperty();
    private IntegerProperty mComparisonsMade = new SimpleIntegerProperty();
    private IntegerProperty mArrayAccesses = new SimpleIntegerProperty();

    public SortingService(List<Pair<Integer, Node>> sortList, SortingAlgorithm sortAlgorithm) {
        mSortList = sortList;
        mSortingAlgorithm = sortAlgorithm;
    }

    public void updateSortList(List<Pair<Integer, Node>> sortList) {
        mSortList = sortList;
        mComparisonsMade.set(0);
        mSwapsMade.set(0);
        mArrayAccesses.set(0);
    }

    public void updateSortAlgorithm(SortingAlgorithm sortAlgorithm) {
        mSortingAlgorithm = sortAlgorithm;
        mComparisonsMade.set(0);
        mSwapsMade.set(0);
        mArrayAccesses.set(0);
    }

    @Override
    protected Task<Void> createTask() {
        final SortingService context = this;
        final SortingAlgorithm algorithm = mSortingAlgorithm;
        final List<Pair<Integer, Node>> list = mSortList;

        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                algorithm.sort(context, list);
                return null;
            }
        };
    }

    public void requestUISwap(Node a, Node b) {
        Platform.runLater(() -> {
            Double tmp = a.getLayoutX();
            a.setLayoutX(b.getLayoutX());
            b.setLayoutX(tmp);
            // make sound
        });
    }

    public void incrementSwapsMade() {
        mSwapsMade.set(getSwapsMade() + 1);
    }

    public void incrementComparisonsMade() {
        mComparisonsMade.set(getComparisonsMade() + 1);
    }

    public void incrementArrayAccesses() {
        mArrayAccesses.set(getArrayAccesses() + 1);
    }

    public int getSwapsMade() {
        return mSwapsMade.get();
    }

    public IntegerProperty swapsMadeProperty() {
        return mSwapsMade;
    }

    public int getComparisonsMade() {
        return mComparisonsMade.get();
    }

    public IntegerProperty comparisonsMadeProperty() {
        return mComparisonsMade;
    }

    public int getArrayAccesses() {
        return mArrayAccesses.get();
    }

    public IntegerProperty arrayAccessesProperty() {
        return mArrayAccesses;
    }
}
