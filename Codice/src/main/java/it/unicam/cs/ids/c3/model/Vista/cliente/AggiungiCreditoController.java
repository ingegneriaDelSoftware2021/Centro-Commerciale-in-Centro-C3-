package it.unicam.cs.ids.c3.model.Vista.cliente;

import it.unicam.cs.ids.c3.model.Clienti.ClienteInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AggiungiCreditoController {


    @FXML
    private TextField labelDenaro;


    @FXML
    private Button okButtom;

    private ClienteInterface cliente;

    private ClienteController clienteController;


    @FXML
    public void aggiungiCredito(ActionEvent event){
        if (!labelDenaro.getText().chars().allMatch(Character::isDigit)) alertFormatoInseritoSbagliato();
        float value = Float.parseFloat(labelDenaro.getText());
        if(value<=0) alertFormatoInseritoSbagliato();
        cliente.aggiungiCredito(value);
        clienteController.setUserCliente(cliente.getUser());
        Stage stage = (Stage) labelDenaro.getScene().getWindow();
        stage.setResizable(false);
        stage.close();
    }

    private void alertFormatoInseritoSbagliato() {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setContentText("il numero inserito non e' valido");
        a.showAndWait();
    }


    public void setCliente(ClienteInterface cliente, ClienteController clienteController){
        this.cliente = cliente;
        this.clienteController = clienteController;
    }


}
