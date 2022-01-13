package com.ale.controller;

import com.ale.dao.JadwalDaoImpl;
import com.ale.entity.Jadwal;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

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
//    @FXML
//    private TableColumn colSisaSlot;
    @FXML
    private Label lblViewHeading;
    @FXML
    private Label lblDi;
    @FXML
    private Button btnBook;
    @FXML
    private Label lblNamaTempat;

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
    private void bookAction(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        jadwals = FXCollections.observableArrayList();
        jadwalDao = new JadwalDaoImpl();
        tableJadwal.setItems(jadwals);

        colJam.setCellValueFactory((d -> new SimpleStringProperty(d.getValue().getJam())));
        colTanggal.setCellValueFactory((d -> new SimpleStringProperty(d.getValue().getTanggal())));
        colJenisVaksin.setCellValueFactory((d -> new SimpleObjectProperty(d.getValue().getTempatVaksin())));

        try {
            jadwals.addAll(jadwalDao.fetchAll());
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


}
