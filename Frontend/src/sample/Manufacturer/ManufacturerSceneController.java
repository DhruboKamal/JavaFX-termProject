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
    private TextField registrationField;

    @FXML
    private TextField priceField;

    @FXML
    private TextField regNoFireld;

    @FXML
    private TextField yearField;

    @FXML
    private TextField color1Field;

    @FXML
    private TextField color2Field;

    @FXML
    private TextField color3Field;

    @FXML
    private TextField makeField;

    @FXML
    private TextField modelField;


    @FXML
    void addCar(ActionEvent event) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                ObjectOutputStream objectoutputstream = null;
                try {
                    objectoutputstream = new ObjectOutputStream(SocketConnection.getInstance().getSocket().getOutputStream());
                    objectoutputstream.writeObject("AddCar");
                    objectoutputstream.flush();

                    Car tempCar = new Car(registrationField.getText(), Integer.parseInt(yearField.getText()),color1Field.getText(),color2Field.getText(),color3Field.getText(),makeField.getText(),modelField.getText(),Integer.parseInt(priceField.getText()));
                    objectoutputstream.writeObject(tempCar);
                    objectoutputstream.flush();

                    ObjectInputStream ois = new ObjectInputStream(SocketConnection.getInstance().getSocket().getInputStream());
                    String ReplyMessage = (String) ois.readObject();
                    Alert A = new Alert(Alert.AlertType.INFORMATION);
                    A.setHeaderText("Delete Operation");
                    A.setContentText(ReplyMessage);
                    A.showAndWait();

                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
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


                    //Loading JavaFX
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/Manufacturer/ManufacturerAllCar.fxml"));
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
