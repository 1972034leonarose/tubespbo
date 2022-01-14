package com.ale.controller;

import com.ale.dao.TempatVaksinDaoImpl;
import com.ale.dao.UserDaoImpl;
import com.ale.entity.TempatVaksin;
import com.ale.entity.User;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private Label lblNik;
    @FXML
    private Label lblPass;
    @FXML
    private TextField txtNik;
    @FXML
    private TextField txtPass;
    @FXML
    private Button btnLogin;
    @FXML
    private Label lblLogin;
    private User user;
    private Stage mainStage;
    private UserDaoImpl userDao;
    @FXML
    private AnchorPane rootAnchor;
    private ResourceBundle rb;
    private TempatVaksinDaoImpl tempatVaksinDao;
    private ObservableList<TempatVaksin> tempats;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userDao = new UserDaoImpl();
        user = new User();
    }

    @FXML
    private void loginAction(ActionEvent actionEvent) {
        String nik = txtNik.getText().trim();
        String password = txtPass.getText().trim();

        user.setNik(nik);
        user.setPassword(password);

        try {
            user = userDao.fetchUser(user);
            if (user!=null) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/HomeView.fxml"));
                fxmlLoader.setResources(rb);
                Parent root = fxmlLoader.load();
                HomeController controller = fxmlLoader.getController();
                controller.setLoginController(this);

                Scene scene = new Scene(root);
                mainStage = new Stage();
                mainStage.setTitle("Home - View Jadwal");
                mainStage.setScene(scene);
                mainStage.show();
                Stage loginStage = (Stage) rootAnchor.getScene().getWindow();
                loginStage.close();
            }else{
                Alert about = new Alert(Alert.AlertType.ERROR);
                about.setContentText("Incorrect Login");
                about.showAndWait();
            }
        } catch (SQLException | ClassNotFoundException | IOException throwables) {
            throwables.printStackTrace();
        }
    }

    public User getUser(){return user;}

}

