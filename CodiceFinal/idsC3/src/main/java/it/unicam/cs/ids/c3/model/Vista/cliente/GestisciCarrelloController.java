package it.unicam.cs.ids.c3.model.Vista.cliente;

import it.unicam.cs.ids.c3.model.Clienti.Carrello;
import it.unicam.cs.ids.c3.model.Clienti.ClienteInterface;
import it.unicam.cs.ids.c3.model.Clienti.ListaClienti;
import it.unicam.cs.ids.c3.model.Esercente.Prodotto;
import javafx.beans.property.ReadOnlyFloatWrapper;
import javafx.beans.property.ReadOnlyLongProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class GestisciCarrelloController implements Initializable {

    @FXML
    public TableColumn<Prodotto,String> nomeCol;
    @FXML
    public TableColumn<Prodotto,String> qCol;
    @FXML
    public TableColumn<Prodotto,String> prezzoCol;
    @FXML
    public ListView<String> carrelloList;
    @FXML
    public Button modQuantButton;
    @FXML
    public Button deleteButton;
    @FXML
    public ListView<String> quantitaListView;
    @FXML
    public ListView<String> prezzoListView;
    @FXML
    public TextField nuovaQuantitaTextField;
    @FXML
    public Button pagaButton;
    @FXML
    private TableView<Prodotto> carrelloTabella;










    private ClienteInterface cliente;


    public void setCliente(ClienteInterface cliente) {
        this.cliente = cliente;
        if(Objects.requireNonNull(cliente).getCarrello()!=null)addProdottiToTable(cliente.getCarrello());
    }

    private void   addProdottiToTable(Carrello carrello) {
        ObservableList<String> l;
        Prodotto previous = new Prodotto(0,"",0,"",0,"",0,0,0);
        for(Prodotto p : carrello.getProdotti()){
            if(!p.equals(previous)){
                l = FXCollections.observableArrayList();
                long q = carrello.getQuantitaProdotto(p);
                l.add(p.getNome());
                this.carrelloList.getItems().add(l.get(0));
                l = FXCollections.observableArrayList();
                l.add(String.valueOf(q));
                this.quantitaListView.getItems().add(l.get(0));
                l = FXCollections.observableArrayList();
                l.add(String.valueOf(q*p.getPrezzo()));
                this.prezzoListView.getItems().add(l.get(0));
            }
            previous = p;
        }



    }











    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

    @FXML
    public void modificaPressed(ActionEvent event) {
        updateClienteInstance();
        if(this.quantitaListView.getSelectionModel().getSelectedItem()==null) return;
        Prodotto p =getProdottoPressed(this.quantitaListView.getSelectionModel().getSelectedIndex());
        String newQString = this.nuovaQuantitaTextField.getText();
        if (!nuovaQuantitaTextField.getText().chars().allMatch(Character::isDigit)) alertFormatoInseritoSbagliato();
        int nuovaQuantita= Integer.parseInt(newQString);
        this.cliente.modificaQuantitaAlCarrello(p,nuovaQuantita);
        clearAllListView();
        this.cliente = ListaClienti.getInstance().getCliente(this.cliente.getIDcliente());
        addProdottiToTable(this.cliente.getCarrello());
    }

    private void alertFormatoInseritoSbagliato() {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setContentText("il numero inserito non e' valido");
        a.showAndWait();
    }

    @FXML
    public void deletePressed(ActionEvent event) {
        updateClienteInstance();
        Prodotto p = getProdottoPressed(this.carrelloList.getSelectionModel().getSelectedIndex());
        this.cliente.rimuoviProdottoAlCarrello(p);
        clearAllListView();
        setCliente(this.cliente);
    }


    private Prodotto getProdottoPressed(int index){
        String nome = this.carrelloList.getItems().get(index);
        return this.cliente.getCarrello().getProdotti().stream().filter(x->x.getNome().equals(nome)).findFirst().orElse(null);
    }

    private void clearAllListView(){
        this.carrelloList.getItems().clear();
        this.quantitaListView.getItems().clear();
        this.prezzoListView.getItems().clear();
    }


    private void updateClienteInstance(){
        this.cliente = ListaClienti.getInstance().getCliente(this.cliente.getIDcliente());
    }

    public void pagaButtonPressed(ActionEvent event) {
        boolean ris = this.cliente.inviaPagamento(this.cliente.getCarrello());
        if(ris){
            clearAllListView();
            updateClienteInstance();
            addProdottiToTable(this.cliente.getCarrello());
            rispostaInviaPagamento("ordine creato!");
        }else{
            rispostaInviaPagamento("c'e' stato un problema!!");
        }
    }

    private void rispostaInviaPagamento(String s) {
        Alert a = new Alert(Alert.AlertType.INFORMATION,s,ButtonType.OK);
        a.showAndWait();
    }
}
