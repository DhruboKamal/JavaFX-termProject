package sample.viewer;

import SharedClass.Car;
import com.sun.jdi.connect.Connector;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.SocketConnection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class ViewerSceneBuilder {

    @FXML
    private Button viewAllCarBtn;

    @FXML
    private TextField regnofield;

    @FXML
    private TextField makefield;

    @FXML
    private TextField modelfield;

    @FXML
    void SearchByRegNo(ActionEvent event) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                ObjectOutputStream objectoutputstream = null;
                try {
                    System.out.println("Search Car by Reg no pressed");
                    objectoutputstream = new ObjectOutputStream(SocketConnection.getInstance().getSocket().getOutputStream());
                    objectoutputstream.writeObject("findByReg");
                    objectoutputstream.flush();

                    String regNo = regnofield.getText();
                    objectoutputstream.writeObject(regNo);
                    objectoutputstream.flush();

                    ObjectInputStream objectinputStream = new ObjectInputStream(SocketConnection.getInstance().getSocket().getInputStream());
                    Car carObj = (Car) objectinputStream.readObject();
                    if(carObj == null){
                        System.out.println("Null Object received");
                        Alert A = new Alert(Alert.AlertType.ERROR);
                        A.setHeaderText("Searching Car");
                        A.setContentText("Car with the provided Registration Number is not found.");
                        A.showAndWait();
                    }
                    else{
                        carObj.DisplayInfo();
                        //need to print it on javaFX
                        Alert A = new Alert(Alert.AlertType.INFORMATION);
                        A.setHeaderText("Search Result Found");
                        A.setContentText(carObj.DisplayInfoString());
                        A.showAndWait();
                    }

                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @FXML
    void searchByModelAndMake(ActionEvent event) {

    }

    @FXML
    void viewAllCar(ActionEvent event) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                ObjectOutputStream objectoutputstream = null;
                try {
                    objectoutputstream = new ObjectOutputStream(SocketConnection.getInstance().getSocket().getOutputStream());
                    objectoutputstream.writeObject("viewAllCars");
                    objectoutputstream.flush();


                    //Loading JavaFX
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/viewer/allCar.fxml"));
                    Stage stage = (Stage) viewAllCarBtn.getScene().getWindow();
                    stage.setTitle("Show All Car");
                    Scene scene = new Scene(loader.load(), 700.0, 460.0);
                    stage.setScene(scene);

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }


}