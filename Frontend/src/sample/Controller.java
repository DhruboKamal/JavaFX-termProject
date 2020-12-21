package sample;

import SharedClass.Car;
import SharedClass.User;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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
                    if(response.equalsIgnoreCase("viewer detected")){
                        //server confirmed that its a viewer.

                        List<Car> lst = (List<Car>) ois.readObject();
                        for (Car c : lst){
                            c.DisplayInfo();
                        }
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

    }

}