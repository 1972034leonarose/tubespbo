package com.ale.controller;

import com.ale.dao.TempatVaksinDaoImpl;
import com.ale.entity.TempatVaksin;
import com.ale.entity.User;
import com.ale.util.MySQLConnection;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    private MenuItem menuAbout;
    @FXML
    private MenuItem menuEditProfile;
    @FXML
    private MenuItem menuHistory;
    @FXML
    private MenuItem menuLogout;
    @FXML
    private MenuItem menuHelp;
    @FXML
    private MenuItem menuLanguage;
    @FXML
    private TableView<TempatVaksin> tableTempatVaks;
    @FXML
    private TableColumn<TempatVaksin, String> colNamaTempat;
    @FXML
    private TableColumn<TempatVaksin, String> colAlamat;
    @FXML
    private TableColumn<TempatVaksin, String> colNoTelp;
    @FXML
    private TableColumn<TempatVaksin, String> colEmail;
    @FXML
    private TableColumn<TempatVaksin, Object> colVaksinTersedia;
    @FXML
    private Label txtWelcome;
    @FXML
    private Label txtName;
    @FXML
    private Label txtSubheading;
    @FXML
    private Button btnViewJadwal;
    private LoginController loginController;


    ObservableList<TempatVaksin> tempats;
    TempatVaksinDaoImpl tempatDao;

    @FXML
    private void helpAction(ActionEvent actionEvent) {
    }

    @FXML
    private void languageAction(ActionEvent actionEvent) {
    }

    @FXML
    private void aboutAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("1972023-Exelonia Maretta-1972032-Andi Gunawan-1972034-Leona Rose");
        alert.showAndWait();
    }

    @FXML
    private void editProfileAction(ActionEvent actionEvent) {
    }

    @FXML
    private void historyAction(ActionEvent actionEvent) {
    }

    @FXML
    private void logoutAction(ActionEvent actionEvent) {
    }

    @FXML
    private void viewJadwalActon(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tempats = FXCollections.observableArrayList();
        tempatDao = new TempatVaksinDaoImpl();
        tableTempatVaks.setItems(tempats);

        colNamaTempat.setCellValueFactory((d -> new SimpleStringProperty(d.getValue().getNama())));
        colAlamat.setCellValueFactory((d -> new SimpleStringProperty(d.getValue().getAlamat())));
        colNoTelp.setCellValueFactory((d -> new SimpleStringProperty(d.getValue().getNoTlpn())));
        colEmail.setCellValueFactory((d -> new SimpleStringProperty(d.getValue().getEmail())));
        colVaksinTersedia.setCellValueFactory((d -> new SimpleObjectProperty(d.getValue().getVaksin_id())));

        try {
//            tempatDao.fetchAll()
//            tempatDao.fetchTerdekat(loginController.getUser())
            tempats.addAll(tempatDao.fetchAll());
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public LoginController getLoginController(){
        return loginController;
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }
}
