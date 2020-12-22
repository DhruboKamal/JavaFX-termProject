package sample.Manufacturer;

import SharedClass.Car;
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

public class ManufacturerSceneController {

    @FXML
    private Button AddCarBtn;

    @FXML
    private Button DeleteCarBtn;

    @FXML
    private Button viewAllCarBtn;

    @FXML
    private Button EditCarBtn;

    @FXML
    private TextField DeleteCarField;

    @FXML
    void addCar(ActionEvent event) {

    }

    @FXML
    void deleteCar(ActionEvent event) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                String RegNo = DeleteCarField.getText();
                ObjectOutputStream objectoutputstream = null;
                try {
                    objectoutputstream = new ObjectOutputStream(SocketConnection.getInstance().getSocket().getOutputStream());
                    objectoutputstream.writeObject("DeleteCar");
                    objectoutputstream.flush();

                    objectoutputstream.writeObject(RegNo);
                    objectoutputstream.flush();

                    ObjectInputStream ois = new ObjectInputStream(SocketConnection.getInstance().getSocket().getInputStream());
                    String ReplyMessage = (String) ois.readObject();
                    Alert A = new Alert(Alert.AlertType.INFORMATION);
                    A.setHeaderText("Delete Operation");
                    A.setContentText(ReplyMessage);
                    A.showAndWait();
                }catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @FXML
    void editCar(ActionEvent event) {

    }

    @FXML
    void viewAllCars(ActionEvent event) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                ObjectOutputStream objectoutputstream = null;
                try {
                    objectoutputstream = new ObjectOutputStream(SocketConnection.getInstance().getSocket().getOutputStream());
                    objectoutputstream.writeObject("viewAllCars");
                    objectoutputstream.flush();

                    //receiving the car list
                    ObjectInputStream ois = new ObjectInputStream(SocketConnection.getInstance().getSocket().getInputStream());
                    List<Car> lst = (List<Car>) ois.readObject();
                    System.out.println("All Cars in the list is received by Manufacturer.");
                    for (Car c: lst){c.DisplayInfo();}
                    //Loading JavaFX
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/Manufacturer/ManufacturerAllCar.fxml"));
                    Stage stage = (Stage) viewAllCarBtn.getScene().getWindow();
                    stage.setTitle("Show All Car");
                    Scene scene = new Scene(loader.load(), 700.0, 460.0);
                    stage.setScene(scene);

                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }

            }
        });
    }

}
