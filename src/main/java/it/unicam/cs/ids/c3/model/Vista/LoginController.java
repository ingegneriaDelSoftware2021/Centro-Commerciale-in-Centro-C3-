package it.unicam.cs.ids.c3.model.Vista;
import it.unicam.cs.ids.c3.model.Autenticazione.UtenteSenzaAccesso;
import it.unicam.cs.ids.c3.model.Vista.cliente.ClienteController;
import it.unicam.cs.ids.c3.model.Vista.commerciante.commercianteController;
import it.unicam.cs.ids.c3.model.Vista.corriere.vistaCorriereController;
import it.unicam.cs.ids.c3.model.Vista.locker.vistaLockerController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;

import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.IOException;



public class LoginController {

    UtenteSenzaAccesso gestoreAutenticazione;


    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField passwordField;

    @FXML
    private ComboBox<String> ruoloComboBox;

    @FXML
    private Pane paneBackground;

    @FXML
    private Button loginButton;








    @FXML
    public void  loginButtonPressed(ActionEvent event){
        Stage stage;
        Parent root;
        String ruolo = this.ruoloComboBox.getValue();
        String user = usernameTextField.getText();
        String pass = passwordField.getText();
        inputNonInseritoCorrettamente(user,pass,ruolo);
        String ris = gestoreAutenticazione.login();
        if(ris.equals("CLIENTE")){
            try {
                FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/vistaCliente.fxml"));
                root = fxmlloader.load();

                ClienteController c = fxmlloader.getController();
                c.setUserCliente(this.usernameTextField.getText());
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));

                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        if(ris.equals("COMMERCIANTE")){
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/vistaCommerciante.fxml"));
            try {
                root = fxmlloader.load();
                commercianteController c = fxmlloader.getController();
                c.setUserCommerciante(this.usernameTextField.getText());
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));

            stage.show();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(ris.equals("LOCKER")){
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/vistaLocker.fxml"));
            try {
                root = fxmlloader.load();
                vistaLockerController c = fxmlloader.getController();
                c.setLocker(this.usernameTextField.getText());
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(ris.equals("CORRIERE")){
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/vistaCorriere.fxml"));
            try {
                root = fxmlloader.load();
                vistaCorriereController c = fxmlloader.getController();
                c.setCorriere(this.usernameTextField.getText());
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }


    }




    private void inputNonInseritoCorrettamente(String user, String pass, String ruolo){
        try {
            gestoreAutenticazione = new UtenteSenzaAccesso(user, pass, ruolo);
        } catch (NullPointerException nullPointerException) {
            launchErrorAlert("CAMPI VUOTI!!!");
        }
        if(this.ruoloComboBox.getSelectionModel().isEmpty()){
            launchErrorAlert("NON HAI SELEZIONATO IL RUOLO!!!");
        }
    }


    private void launchErrorAlert(String messaggio){
        Alert alert = new Alert(Alert.AlertType.ERROR, messaggio, ButtonType.OK);
        alert.showAndWait();
    }

    @FXML
    public void initialize(){
        ruoloComboBox.setItems(FXCollections.observableArrayList("CLIENTE","COMMERCIANTE","LOCKER","CORRIERE"));

    }






}
