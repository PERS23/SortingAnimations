package github.com.PERS23.SortingAnimations;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.shape.Rectangle;
import javafx.util.Pair;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private SortingService mSortingService;

    private IntegerProperty mComparisonsMade = new SimpleIntegerProperty();
    private IntegerProperty mSwapsMade = new SimpleIntegerProperty();
    private IntegerProperty mArrayAccessesMade = new SimpleIntegerProperty();

    private ResourceBundle mBundle;

    @FXML private ComboBox<SortAlgorithms> algorithm_choices;
    @FXML private ComboBox<SortSizes> size_choices;
    @FXML private ComboBox<Integer> delay_choices;

    @FXML private Label comparisons_value;
    @FXML private Label swaps_value;
    @FXML private Label array_accesses_value;

    @FXML private ToolBar settings_toolbar;
    @FXML private Group list_container;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mSortingService = new SortingService(null, SortAlgorithms.BUBBLE.getSorter(), 1,
                mComparisonsMade, mSwapsMade, mArrayAccessesMade);
        mSortingService.setOnSucceeded(e -> {
            settings_toolbar.setDisable(false);
        });

        mBundle = resources;

        setupComboBoxes();
        attachListeners();
    }

    private void setupComboBoxes() {
        algorithm_choices.getItems().addAll(SortAlgorithms.values());
        algorithm_choices.getSelectionModel().selectFirst();

        size_choices.getItems().addAll(SortSizes.values());
        size_choices.getSelectionModel().selectFirst();

        delay_choices.getItems().addAll(1, 2, 5, 10, 20, 30, 50);
        delay_choices.getSelectionModel().selectFirst();
    }

    private void attachListeners() {
        algorithm_choices.valueProperty().addListener((observable, oldValue, newValue) -> {
            mSortingService.updateSortAlgorithm(newValue.getSorter());
        });

        delay_choices.valueProperty().addListener((observable, oldValue, newValue) -> {
            mSortingService.updateDelay(newValue);
        });

        mComparisonsMade.addListener((observable, oldValue, newValue) -> {
            comparisons_value.setText(String.valueOf(newValue));
        });

        mSwapsMade.addListener((observable, oldValue, newValue) -> {
            swaps_value.setText(String.valueOf(newValue));
        });

        mArrayAccessesMade.addListener((observable, oldValue, newValue) -> {
            array_accesses_value.setText(String.valueOf(newValue));
        });
    }

    @FXML
    public void handleNewListRequest() {
        mSortingService.updateSortList(generateRandomList());
    }

    /* Simply generates a random number (up to size n) n times and uses that random number to generate the rectangle size */
    private List<Pair<Integer, Rectangle>> generateRandomList() {
        List<Pair<Integer, Rectangle>> result = new ArrayList<>();
        Random gen = new Random();
        final SortSizes choice = size_choices.getSelectionModel().getSelectedItem();

        list_container.getChildren().clear();                                      // Clear the current list from the UI
        for (int i = 0; i < choice.getSize(); i++) {
            int rand = gen.nextInt(choice.getSize());
            Rectangle node = new Rectangle(choice.getWidth(), rand * choice.getHScale()); // Height is completely dependent on the random number generated

            list_container.getChildren().add(node);
            node.setLayoutX(i * (choice.getWidth() + choice.getSpacing()));     // X position directly corresponds to the current index
            node.setLayoutY(choice.getSize() - node.getHeight());               // Y position has to be justified by the largest val so not upside down

            result.add(new Pair<Integer, Rectangle>(rand, node));
        }

        return result;
    }

    @FXML
    public void handleSortListRequest() {
        startSorting();
    }

    private void startSorting() {
        settings_toolbar.setDisable(true);
        mSortingService.restart();
    }
}
