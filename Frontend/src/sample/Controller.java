package sample;

import SharedClass.Car;
import SharedClass.User;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class Controller {
    private final SocketConnection connector = SocketConnection.getInstance();
    @FXML
    private TextField usernamefield;

    @FXML
    private TextField passwordfield;

    @FXML
    private Button loginbutton;

    @FXML
    void SignIn(ActionEvent event) {
        String username = usernamefield.getText();
        String password = passwordfield.getText();
        User user = new User();
        user.setUserName(username);
        user.setPassword(password);
        user.setstatus(false);

        System.out.println(username);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try{

                    ObjectOutputStream oos = new ObjectOutputStream(connector.getSocket().getOutputStream());
                    oos.writeObject(user);
                    oos.flush();

                    ObjectInputStream ois = new ObjectInputStream(connector.getSocket().getInputStream());
                    String response = (String) ois.readObject();
                    System.out.println(response);
                    if(response.equalsIgnoreCase("wrong authentication")){
                        Alert A = new Alert(Alert.AlertType.ERROR);
                        A.setHeaderText("Login Window");
                        A.setContentText("Wrong Username of Password");
                        A.showAndWait();
                    }
                    else if(response.equalsIgnoreCase("viewer detected")){
                        //server confirmed that its a viewer.
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/viewer/viewerScene.fxml"));
                        Stage stage = (Stage) loginbutton.getScene().getWindow();
                        stage.setTitle("Viewer Window");
                        Scene scene = new Scene(loader.load(), 700.0, 460.0);
                        stage.setScene(scene);
                    }
                    else if(response.equalsIgnoreCase("manufacture detected")){

                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

    }

}