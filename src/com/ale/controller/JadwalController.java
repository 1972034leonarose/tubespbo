package com.ale.controller;

import com.ale.dao.JadwalDaoImpl;
import com.ale.dao.TempatVaksinDaoImpl;
import com.ale.entity.Jadwal;
import com.ale.entity.TempatVaksin;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class JadwalController implements Initializable {
    @FXML
    private TableView<Jadwal> tableJadwal;
    @FXML
    private TableColumn<Jadwal, String> colTanggal;
    @FXML
    private TableColumn<Jadwal, String> colJam;
    @FXML
    private TableColumn<Jadwal, Object> colJenisVaksin;

    private ObservableList<Jadwal> jadwals;
    private JadwalDaoImpl jadwalDao;
    private TempatVaksin curTempat;
    private Jadwal selected;
    @FXML
    private Label lblViewHeading;
    @FXML
    private Label lblDi;
    @FXML
    private Button btnBook;
    @FXML
    private Label lblNamaTempat;

    private HomeController homeController;
    private ObservableList<TempatVaksin> tempats;
    private TempatVaksinDaoImpl tempatDao;


    @FXML
    private AnchorPane rootAnchor;

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

    @FXML
    private void logoutAction(ActionEvent actionEvent) {
    }

    @FXML
    private void bookAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/BookViewA.fxml"));
        Parent root = loader.load();
        BookAController controller = loader.getController();
        controller.setBookControllerA(this);
        Stage stage = new Stage();
        stage.setTitle("Book A");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.initOwner(rootAnchor.getScene().getWindow());
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        jadwals = FXCollections.observableArrayList();
        jadwalDao = new JadwalDaoImpl();
        tableJadwal.setItems(jadwals);

        colJam.setCellValueFactory((d -> new SimpleStringProperty(d.getValue().getJam())));
        colTanggal.setCellValueFactory((d -> new SimpleStringProperty(d.getValue().getTanggal())));
        colJenisVaksin.setCellValueFactory((d -> new SimpleObjectProperty(d.getValue().getTempatVaksin().getVaksin_id())));
    }

    public ObservableList<Jadwal> getJadwals(){
        return jadwals;
    }

    public void setController(HomeController homeController) {
        this.homeController = homeController;
        curTempat = homeController.getTempat();
        lblNamaTempat.setText(curTempat.getNama());

        try {
            jadwals.addAll(jadwalDao.fetchJadwal(homeController.getTempat()));
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void selectedA(MouseEvent mouseEvent) {
        selected = tableJadwal.getSelectionModel().getSelectedItem();
        System.out.println("here");
        System.out.println(selected);
        System.out.println(homeController.getLoginController().getUser());

        if (selected != null){
            btnBook.setDisable(false);
        }else{
            btnBook.setDisable(true);
        }
    }

    public Jadwal getJadwal(){
        return selected;
    }

    public HomeController getHomeController(){
        return homeController;
    }
}
