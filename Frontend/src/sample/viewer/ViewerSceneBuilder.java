package sample.viewer;

import SharedClass.Car;
import com.sun.jdi.connect.Connector;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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

                    //receiving the car list
                    ObjectInputStream ois = new ObjectInputStream(SocketConnection.getInstance().getSocket().getInputStream());
                    List<Car> lst = (List<Car>) ois.readObject();
                    System.out.println("All Cars in the list is received by viewer.");
                    for (Car c: lst){c.DisplayInfo();}

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/viewer/allCar.fxml"));
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
