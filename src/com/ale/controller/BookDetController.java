package com.ale.controller;

import com.ale.dao.BookingDaoImpl;
import com.ale.entity.Booking;
import com.ale.entity.Jadwal;
import com.ale.entity.User;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class BookDetController implements Initializable {
    @FXML
    private Label lblTempatVDet;
    @FXML
    private Label lblTglVDet;
    @FXML
    private Label lblJamVDet;
    @FXML
    private Label lblJenisVD;
    @FXML
    private Label lblJenisVaks;
    @FXML
    private Label lblJam;
    @FXML
    private Label lblTanggal;
    @FXML
    private Label lblTempat;
    @FXML
    private Label lblNikDet;
    @FXML
    private Label lblNamaDet;
    @FXML
    private Label lblTptTglDet;
    @FXML
    private Label lblAlamatDet;
    @FXML
    private AnchorPane rootAnchor;
    private BookAController bookAController;
    private Booking booking;
    private BookingDaoImpl bookingDao;

//    private ObservableList<Jadwal> bookJ;
//    private ObservableList<User> bookU;

    private Jadwal bookJ;
    private User bookU;


    @FXML
    private void cancelAction(ActionEvent actionEvent) {
        // Close
    }

    @FXML
    private void confirmAction(ActionEvent actionEvent) {
        String tempat = lblTempatVDet.getText();
        String tgl = lblTglVDet.getText();
        String jam = lblJamVDet.getText();
        String jenis = lblJenisVaks.getText();
        String nik = lblNikDet.getText();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bookingDao = new BookingDaoImpl();
    }

    public void setController(BookAController bookAController) {
        this.bookAController = bookAController;

        bookAController.getJadwalController().getJadwal();


    }
}
