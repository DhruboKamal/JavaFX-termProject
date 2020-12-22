package sample.viewer;

import SharedClass.Car;
import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.SocketConnection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AllCarController {


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
    void goToMainMenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/viewer/viewerScene.fxml"));
        Stage stage = (Stage) backBtn.getScene().getWindow();
        stage.setTitle("Viewer Window");
        Scene scene = new Scene(loader.load(), 700.0, 460.0);
        stage.setScene(scene);
    }

}
