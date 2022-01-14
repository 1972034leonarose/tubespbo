package com.ale.controller;

import com.ale.Main;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
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
    private User curUser;
    private TempatVaksin selected;
    @FXML
    private AnchorPane rootAnchor;
    private ResourceBundle rb;
    private Stage homeStage;

    /* ============================= MENU BAR ==========================================================*/
    @FXML
    private void helpAction(ActionEvent actionEvent) {
        // TODO: ignore
    }

    @FXML
    private void languageAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Language.fxml"), ResourceBundle.getBundle("my_bundle"));
        Parent root = loader.load();
        LanguageController controller = loader.getController();
        controller.setController(this);
        Stage stage = new Stage();
        stage.setTitle("Language");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.initOwner(rootAnchor.getScene().getWindow());
        stage.show();
    }

    @FXML
    private void aboutAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Tugas Besar by:");
        alert.setContentText("1972023-Exelonia Maretta; 1972032-Andi Gunawan; 1972034-Leona Rose");
        alert.showAndWait();
    }

    @FXML
    private void editProfileAction(ActionEvent actionEvent) {
    }

    @FXML
    private void historyAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/History.fxml"));
        Parent root = loader.load();
        HistoryController controller = loader.getController();
        controller.setController(this);
        homeStage = new Stage();
        homeStage.setTitle("Booking History");
        homeStage.setScene(new Scene(root));
        homeStage.setResizable(false);
        homeStage.initOwner(rootAnchor.getScene().getWindow());
        homeStage.show();
    }

    @FXML
    private void logoutAction(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setContentText("Are you sure you want to logout?");

        if (alert.showAndWait().get() == ButtonType.OK){
            homeStage = (Stage) rootAnchor.getScene().getWindow();
            homeStage.close();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/LoginView.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Login");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.initOwner(rootAnchor.getScene().getWindow());
            stage.show();
        }
    }
    /* ============================= END MENU BAR ==========================================================*/


    @FXML
    private void viewJadwalActon(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/JadwalView.fxml"));
        loader.setResources(rb);

        Parent root = loader.load();
        JadwalController controller = loader.getController();
        controller.setController(this);
        Stage stage = new Stage();
        stage.setTitle("Jadwal");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.initOwner(rootAnchor.getScene().getWindow());
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tempats = FXCollections.observableArrayList();
        tempatDao = new TempatVaksinDaoImpl();
        tableTempatVaks.setItems(tempats);

        changeLanguage(resourceBundle);

        colNamaTempat.setCellValueFactory((d -> new SimpleStringProperty(d.getValue().getNama())));
        colAlamat.setCellValueFactory((d -> new SimpleStringProperty(d.getValue().getAlamat())));
        colNoTelp.setCellValueFactory((d -> new SimpleStringProperty(d.getValue().getNoTlpn())));
        colEmail.setCellValueFactory((d -> new SimpleStringProperty(d.getValue().getEmail())));
        colVaksinTersedia.setCellValueFactory((d -> new SimpleObjectProperty(d.getValue().getVaksin_id())));
    }

    private void changeLanguage(ResourceBundle bundle){
//        colNamaTempat.setText(bundle.getString("col.namatempat"));
//        colAlamat.setText(bundle.getString("col.alamat"));
//        colNoTelp.setText(bundle.getString("col.nohp"));
//        colEmail.setText(bundle.getString("col.email"));
//        colVaksinTersedia.setText(bundle.getString("col.vaksintersedia"));
//        txtName.setText(bundle.getString("label.nama"));
//        txtSubheading.setText(bundle.getString("label.subheading"));
//        txtWelcome.setText(bundle.getString("label.welcome"));
//        btnViewJadwal.setText(bundle.getString("button.viewjadwal"));
    }

    public LoginController getLoginController(){
        return loginController;
    }

    public void setLoginController(LoginController loginController) throws SQLException, ClassNotFoundException {
        this.loginController = loginController;
        curUser = loginController.getUser();
        txtName.setText(curUser.getNama());

        try {
            tempats.addAll(tempatDao.fetchTerdekat(loginController.getUser()));
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void selectedTempat(MouseEvent mouseEvent) {
        selected = tableTempatVaks.getSelectionModel().getSelectedItem();
        System.out.println(selected);
        if (selected != null){
            btnViewJadwal.setDisable(false);
            System.out.println(getLoginController().getUser());
        }else{
            btnViewJadwal.setDisable(true);
        }
    }

    public TempatVaksin getTempat(){
        return selected;
    }
}
