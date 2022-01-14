package com.ale.controller;

import com.ale.dao.UserDaoImpl;
import com.ale.entity.TempatVaksin;
import com.ale.entity.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class BookAController implements Initializable {
    @FXML
    private Label lblNamaTempat;
    @FXML
    private TextField txtNik;
    @FXML
    private TextField txtNama;
    @FXML
    private TextField txtTglLahir;
    private User user;
    private UserDaoImpl userDao;
    private ObservableList<User> userInfo;
    private LoginController loginController;
    private JadwalController jadwalController;
    @FXML
    private AnchorPane rootAnchor;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userInfo = FXCollections.observableArrayList();
        userDao = new UserDaoImpl();
    }

    @FXML
    private void continueAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/BookDetails.fxml"));
        Parent root = fxmlLoader.load();
        BookDetController controllerDet = fxmlLoader.getController();
        controllerDet.setController(this);
        Scene scene = new Scene(root);
        Stage bookStage = new Stage();
        bookStage.setTitle("Confirm Booking Details");
        bookStage.setScene(scene);
        bookStage.show();
        bookStage.alwaysOnTopProperty();
        Stage curStage = (Stage) rootAnchor.getScene().getWindow();
        curStage.close();

        user = getJadwalController().getHomeController().getLoginController().getUser();

        if(user!=null){
            String name = txtNama.getText().trim();
            String email = txtTglLahir.getText().trim(); //TODO: change txt name

            user.setNama(name);
            user.setEmail(email);
            try {
                userDao.updateSomeData(user);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }


    @FXML
    private void helpAction(ActionEvent actionEvent) {
    }

    @FXML
    private void languageAction(ActionEvent actionEvent) {
    }

    @FXML
    private void aboutAction(ActionEvent actionEvent) {
    }

    @FXML
    private void editProfileAction(ActionEvent actionEvent) {
    }

    @FXML
    private void historyAction(ActionEvent actionEvent) {
    }

    public void setBookControllerA(JadwalController jadwalController) {
        this.jadwalController = jadwalController;

        user = jadwalController.getHomeController().getLoginController().getUser();
        lblNamaTempat.setText(String.valueOf(jadwalController.getHomeController().getTempat()));

        System.out.println(user.getNik());
        txtNik.setText(user.getNik());
        txtNama.setText(user.getNama());
        txtTglLahir.setText(user.getTanggalLahir());
    }

    public JadwalController getJadwalController(){
        return jadwalController;
    }
}
