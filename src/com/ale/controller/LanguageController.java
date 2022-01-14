package com.ale.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class LanguageController implements Initializable {
    @FXML
    private Button IndoButton;
    @FXML
    private Button EnglishButton;
    @FXML
    private Button ContinueButton;
    private ResourceBundle rb;
    @FXML
    private Label lblLanguage;
    private int language; // 1 = indo; 0 = eng
    private HomeController homeController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        changeLanguage(resourceBundle);
    }

    @FXML
    private void IndonesiaAction(ActionEvent actionEvent) {
        changeLanguage(rb.getBundle("my_bundle_id_id"));
        language = 1;
    }

    @FXML
    private void EnglishAction(ActionEvent actionEvent) {
        changeLanguage(rb.getBundle("my_bundle_en_us"));
        language = 0;
    }

    @FXML
    private void ContinueAction(ActionEvent actionEvent) {
        // TODO: Close window
    }

    private void changeLanguage(ResourceBundle rb){
        lblLanguage.setText(rb.getString("lbl.Language"));
        ContinueButton.setText(rb.getString("btn.Continue"));
    }

    public int getLanguage(){
        return language;
    }

    public void setController(HomeController homeController) {
        this.homeController = homeController;
    }
}
