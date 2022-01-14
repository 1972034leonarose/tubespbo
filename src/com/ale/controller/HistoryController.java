package com.ale.controller;

import com.ale.dao.BookingDaoImpl;
import com.ale.entity.Booking;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HistoryController implements Initializable {
    @FXML
    private TableView<Booking> tableHistory;
    @FXML
    private TableColumn<Booking, Number> colBookId;
    @FXML
    private TableColumn<Booking, Object> colTempat;
    @FXML
    private TableColumn<Booking, Object>  colJenisV;
    @FXML
    private TableColumn<Booking, Object>  colJamV;
    @FXML
    private TableColumn<Booking, Object>  colTglV;

    private Booking book;
    private BookingDaoImpl bookDao;
    private HistoryController historyController;
    private ObservableList<Booking> bookings;

    @FXML
    private void FileAction(ActionEvent actionEvent) {
    }

    @FXML
    private void EditAction(ActionEvent actionEvent) {
    }

    @FXML
    private void HelpAction(ActionEvent actionEvent) {
    }

    @FXML
    private void selectedRow(MouseEvent mouseEvent) {
    }

    @FXML
    private void printAction(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bookings = FXCollections.observableArrayList();
        bookDao = new BookingDaoImpl();
        tableHistory.setItems(bookings);

        try {
            bookings.addAll(bookDao.fetchAll());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        colBookId.setCellValueFactory((d -> new SimpleIntegerProperty(d.getValue().getId())));
        colTempat.setCellValueFactory((d -> new SimpleObjectProperty(d.getValue().getJadwal_id().getTempatVaksin().getNama())));
        colJenisV.setCellValueFactory((d -> new SimpleObjectProperty<>(d.getValue().getJadwal_id().getTempatVaksin().getVaksin_id().getNama())));
        colJamV.setCellValueFactory((d -> new SimpleObjectProperty<>(d.getValue().getJadwal_id().getJam())));
        colTglV.setCellValueFactory((d -> new SimpleObjectProperty<>(d.getValue().getJadwal_id().getTanggal())));

    }

    public void setController(HomeController homeController) {
        this.historyController = historyController;
    }
}
