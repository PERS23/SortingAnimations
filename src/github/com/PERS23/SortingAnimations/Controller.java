package github.com.PERS23.SortingAnimations;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;
import javafx.util.Pair;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private SortingService mSortingService;

    private ResourceBundle mBundle;
    @FXML private ComboBox<SortAlgorithms> algorithm_choices;
    @FXML private ComboBox<SortSizes> size_choices;
    @FXML private Button new_list;
    @FXML private Button sort_list;
    @FXML private Label comparisons_label;
    @FXML private Label swaps_label;
    @FXML private Label array_accesses_label;
    @FXML private Label delay_label;
    @FXML private Group list_container;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mSortingService = new SortingService(null, SortAlgorithms.BUBBLE.getSorter());

        algorithm_choices.getItems().addAll(SortAlgorithms.values());
        algorithm_choices.getSelectionModel().selectFirst();
        size_choices.getItems().addAll(SortSizes.values());
        size_choices.getSelectionModel().selectFirst();

        mBundle = resources;
    }

    public Controller() {

    }

    @FXML
    public void handleNewListRequest() {
        mSortingService.updateSortList(generateRandomList());
    }

    private List<Pair<Integer, Node>> generateRandomList() {
        List<Pair<Integer, Node>> result = new ArrayList<>();
        Random gen = new Random();
        final SortSizes choice = size_choices.getSelectionModel().getSelectedItem();

        list_container.getChildren().clear();
        for (int i = 0; i < choice.getSize(); i++) {
            int rand = gen.nextInt(choice.getSize());
            Rectangle node = new Rectangle(choice.getWidth(), rand * choice.getHeightScaleFactor());

            list_container.getChildren().add(node);
            node.setLayoutX(i * (choice.getWidth() + choice.getSpacing()));     // X position directly corresponds to the current index
            node.setLayoutY(choice.getSize() - node.getHeight());               // Y position has to be justified by the largest val so not upside down

            result.add(new Pair<Integer, Node>(rand, node));
        }

        return result;
    }

    @FXML
    public void handleSortListRequest() {
        startSorting();
    }

    private void startSorting() {
        mSortingService.restart();
    }
}
