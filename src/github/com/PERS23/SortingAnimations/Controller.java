package github.com.PERS23.SortingAnimations;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private SortingService mSortingService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mSortingService = new SortingService(null, null);
    }
}
