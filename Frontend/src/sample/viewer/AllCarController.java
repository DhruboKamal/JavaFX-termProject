package sample.viewer;

import SharedClass.Car;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import javax.xml.crypto.Data;
import java.io.IOException;

public class AllCarController {

    @FXML
    private Button backBtn;

    @FXML
    private TableView<Car> tableView;

    @FXML
    private TableColumn<Car, String> registrationCol = new TableColumn<>("view");

    @FXML
    private TableColumn<Car, String> yearCol = new TableColumn<>("view");

    @FXML
    private TableColumn<Car, String> color1Col = new TableColumn<>("view");

    @FXML
    private TableColumn<Car, String> color2Col = new TableColumn<>("view");

    @FXML
    private TableColumn<Car, String> color3Col = new TableColumn<>("view");

    @FXML
    private TableColumn<Car, String> makeCol = new TableColumn<>("view");

    @FXML
    private TableColumn<Car, String> modelCol = new TableColumn<>("view");

    @FXML
    private TableColumn<Car, String> priceCol = new TableColumn<>("view");





    @FXML
    void goToMainMenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/viewer/viewerScene.fxml"));
        Stage stage = (Stage) backBtn.getScene().getWindow();
        stage.setTitle("Viewer Window");
        Scene scene = new Scene(loader.load(), 700.0, 460.0);
        stage.setScene(scene);
    }

}


