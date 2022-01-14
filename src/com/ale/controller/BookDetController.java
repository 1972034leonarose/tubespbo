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
import java.sql.SQLException;
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
        // TODO: Close
    }

    @FXML
    private void confirmAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        booking = new Booking();
        bookU = new User();
        bookJ = new Jadwal();
        bookU.setId(bookAController.getJadwalController().getHomeController().getLoginController().getUser().getId());
        bookJ.setId(bookAController.getJadwalController().getJadwal().getId());
        booking.setUser_id(bookU);
        booking.setJadwal_id(bookJ);

        int result = bookingDao.addData(booking);
        if (result == 1) {
            Alert about = new Alert(Alert.AlertType.INFORMATION);
            about.setContentText("Success");
            about.showAndWait();
        }else{
            Alert errorM = new Alert(Alert.AlertType.ERROR);
            errorM.setContentText("Error Adding Data");
            errorM.showAndWait();
        }
        // TODO: exit return to home
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bookingDao = new BookingDaoImpl();
    }

    public void setController(BookAController bookAController) {
        this.bookAController = bookAController;

        String jam = bookAController.getJadwalController().getJadwal().getJam();
        String tempat = bookAController.getJadwalController().getJadwal().getTempatVaksin().getNama();
        String jenis = bookAController.getJadwalController().getJadwal().getTempatVaksin().getVaksin_id().getNama();
        String tgl = bookAController.getJadwalController().getJadwal().getTanggal();

        System.out.println(tempat);
        lblTempatVDet.setText(tempat);
        lblJamVDet.setText(jam);
        lblJenisVD.setText(jenis);
        lblTglVDet.setText(tgl);

        String nik = bookAController.getJadwalController().getHomeController().getLoginController().getUser().getNik();
        String nama = bookAController.getJadwalController().getHomeController().getLoginController().getUser().getNama();
        String kota = bookAController.getJadwalController().getHomeController().getLoginController().getUser().getKota();
        String tglL = bookAController.getJadwalController().getHomeController().getLoginController().getUser().getTanggalLahir();
        String alamat = bookAController.getJadwalController().getHomeController().getLoginController().getUser().getAlamat();

        lblNikDet.setText(nik);
        lblNamaDet.setText(nama);
        lblAlamatDet.setText(alamat);
        lblTptTglDet.setText(kota + " / " + tglL);
    }
}
