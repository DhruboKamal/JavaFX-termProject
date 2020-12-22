package sample.viewer;

import SharedClass.Car;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AllCarController {

    public ObservableList <Car> getObservableCarList(){
        ObservableList <Car> ObservableCarList= FXCollections.observableArrayList();
        ObservableCarList.add(new Car("Y72b7",2014,"red","","","maker","model",6000));
        ObservableCarList.add(new Car("Y72bhhhhh7",2084,"red","Green","","maker2","model",10000));
        return ObservableCarList;
    }



    @FXML
    private Button backBtn;

    @FXML
    private TableView<Car> SimpleTable;

    @FXML
    private TableColumn<Car, String> RegistrationID;

    @FXML
    private TableColumn<Car, Integer> yearID;

    @FXML
    private TableColumn<Car,String> color1ID;

    @FXML
    private TableColumn<Car,String> color2ID;

    @FXML
    private TableColumn<Car,String> color3ID;

    @FXML
    private TableColumn<Car,String> makeID;

    @FXML
    private TableColumn<Car,String> modelID;

    @FXML
    private TableColumn<Car, Integer> priceID;

    
    public void initialize(URL url, ResourceBundle rb){
        RegistrationID.setCellValueFactory(new PropertyValueFactory<Car, String>("registration"));
        yearID.setCellValueFactory(new PropertyValueFactory<Car, Integer>("year"));
        color1ID.setCellValueFactory(new PropertyValueFactory<Car, String>("color1"));
        color2ID.setCellValueFactory(new PropertyValueFactory<Car, String>("color2"));
        color3ID.setCellValueFactory(new PropertyValueFactory<Car, String>("color3"));
        makeID.setCellValueFactory(new PropertyValueFactory<Car, String>("make"));
        modelID.setCellValueFactory(new PropertyValueFactory<Car, String>("model"));
        priceID.setCellValueFactory(new PropertyValueFactory<Car, Integer>("price"));


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
