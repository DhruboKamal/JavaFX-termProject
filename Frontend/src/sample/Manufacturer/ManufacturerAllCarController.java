package sample.Manufacturer;

import SharedClass.Car;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import sample.SocketConnection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

public class ManufacturerAllCarController {
    @FXML
    private Button backBtn;

    @FXML
    private ListView<Car> ListID;
    @FXML

    public void initialize() {

        try{
            ObjectInputStream ois = new ObjectInputStream(SocketConnection.getInstance().getSocket().getInputStream());
            List<Car> lst = (List<Car>) ois.readObject();
            for(int i=0; i<lst.size(); i++)
            {
                ListID.getItems().add(lst.get(i).getCar());
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }



    }
    @FXML
    void goBack(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/manufacturer/ManufacturerScene.fxml"));
        Stage stage = (Stage) backBtn.getScene().getWindow();
        stage.setTitle("Manufacturer Window");
        Scene scene = new Scene(loader.load(), 700.0, 460.0);
        stage.setScene(scene);
    }

}
