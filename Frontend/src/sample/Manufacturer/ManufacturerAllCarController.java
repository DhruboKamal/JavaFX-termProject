package sample.Manufacturer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ManufacturerAllCarController {
    @FXML
    private Button backBtn;

    @FXML
    void goBack(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/manufacturer/ManufacturerScene.fxml"));
        Stage stage = (Stage) backBtn.getScene().getWindow();
        stage.setTitle("Manufacturer Window");
        Scene scene = new Scene(loader.load(), 700.0, 460.0);
        stage.setScene(scene);
    }

}
