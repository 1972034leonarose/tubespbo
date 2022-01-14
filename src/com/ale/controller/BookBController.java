//package com.ale.controller;
//
//import com.ale.dao.BookingDaoImpl;
//import com.ale.dao.JadwalDaoImpl;
//import com.ale.dao.UserDaoImpl;
//import com.ale.entity.Booking;
//import com.ale.entity.Jadwal;
//import com.ale.entity.User;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.control.ComboBox;
//import javafx.scene.control.Label;
//import javafx.scene.layout.AnchorPane;
//
//import java.net.URL;
//import java.sql.SQLException;
//import java.util.ResourceBundle;
//
//public class BookBController implements Initializable {
//    @FXML
//    private Label lblNamaTempat;
//    @FXML
//    private ComboBox<Booking> comboJam;
//    @FXML
//    private ComboBox<Booking> comboTanggal;
//    @FXML
//    private ComboBox<Booking> comboJenis;
//    @FXML
//    private AnchorPane rootAnchor;
//
//    private BookingDaoImpl bookingDao;
//    private ObservableList<Booking> bookingDet;
//    private BookAController bookControllerA;
//    private Jadwal chosenJadwal;
//    private JadwalDaoImpl jadwalDao;
//    private User chosenUser;
//
//    private ObservableList<Jadwal> comboJamIsi;
//    private ObservableList<Jadwal> comboTglIsi;
//    private ObservableList<Jadwal> comboJenisIsi;
//    private ObservableList<Jadwal> jadwals;
//
//    @FXML
//    private void helpAction(ActionEvent actionEvent) {
//    }
//
//    @FXML
//    private void languageAction(ActionEvent actionEvent) {
//    }
//
//    @FXML
//    private void aboutAction(ActionEvent actionEvent) {
//    }
//
//    @FXML
//    private void editProfileAction(ActionEvent actionEvent) {
//    }
//
//    @FXML
//    private void historyAction(ActionEvent actionEvent) {
//    }
//
//    @FXML
//    private void logoutAction(ActionEvent actionEvent) {
//    }
//
//    @FXML
//    private void continueAction(ActionEvent actionEvent) {
//    }
//
//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//        jadwals = FXCollections.observableArrayList();
//        jadwalDao = new JadwalDaoImpl();
//
////        omboString = FXCollections.observableArrayList(); //Declared somewhere else
////        data.add(new User(rs.getString("username")));
////        comboString.add(rs.getString("username"));
//    }
//
//
//    public void setController(BookAController bookAController) {
//        this.bookControllerA = bookAController;
//
//        try {
//            jadwals.addAll(jadwalDao.fetchJadwal(bookControllerA.getJadwalController().getHomeController().getTempat()));
//            comboJamIsi = FXCollections.observableArrayList();
//            comboTglIsi= FXCollections.observableArrayList();
//            comboJenisIsi = FXCollections.observableArrayList();
//            System.out.println(jadwals);
//            comboJamIsi.add(jadwals.get(0));
//            comboTglIsi.add(jadwals.get(1));
//            comboJenisIsi.add(jadwals.get(2));
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        chosenJadwal = bookAController.getJadwalController().getJadwal();
//        chosenUser = bookAController.getJadwalController().getHomeController().getLoginController().getUser();
//
//    }
//}
