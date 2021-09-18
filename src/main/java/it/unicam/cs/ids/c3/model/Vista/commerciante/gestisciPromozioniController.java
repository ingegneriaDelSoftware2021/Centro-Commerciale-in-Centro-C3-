package it.unicam.cs.ids.c3.model.Vista.commerciante;


import it.unicam.cs.ids.c3.model.Esercente.*;
import javafx.collections.FXCollections;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class gestisciPromozioniController {

    @FXML
    public ListView<String> promozioniList;
    @FXML
    public ComboBox<String> negozioComboBox;
    @FXML
    public Button cercaButton;
    @FXML
    public Button aggiungiButton;
    @FXML
    public Button scontoButton;
    @FXML
    public Button attivaDisattivaButton;
    @FXML
    public Button rimuoviButton;
    @FXML
    public TextField nuovoScontoLabel;
    private CommercianteInterface commerciante;
    private List<Promozioni> listaPromozioni;
    private List<Negozio> listaNegozi;
    private List<Prodotto> listaProdotti;

    @FXML
    public void rimuoviPressed(ActionEvent event) {
        if(this.promozioniList.getSelectionModel().isEmpty())inputNonInseritoCorrettamente("non e' stata selezionata nessuna promozione da rimuovere.");
        else{
            Negozio n = getNegozioFromList();
            String nomeProdottoSelezionato = this.promozioniList.getSelectionModel().getSelectedItem();
            Promozioni p = getPromozioneFromNome(nomeProdottoSelezionato);
            Objects.requireNonNull(n).rimuoviPromozione(Objects.requireNonNull(p).getIDpromozione(),p.getProdotto().getIDprodotto());
            setCommerciante(this.commerciante);
            addPromozioniToListView(n.getIDNegozio());
        }
        this.negozioComboBox.getItems().clear();
        addNegoziToComboBox();
    }

    @FXML
    public void attivaDisattivaPressed(ActionEvent event) {
        Negozio n = getNegozioFromList();
        String nomeProdottoSelezionato = this.promozioniList.getSelectionModel().getSelectedItem();
        Promozioni p = getPromozioneFromNome(nomeProdottoSelezionato);
        boolean oldStato = p.getStato();
        this.commerciante.modificaStatoPromozione(n.getIDNegozio(),p.getIDpromozione(),!oldStato);
        this.negozioComboBox.getItems().clear();
        addNegoziToComboBox();
        addPromozioniToListView(n.getIDNegozio());
    }

    @FXML
    public void scontoPressed(ActionEvent event) {
        Negozio n = getNegozioFromList();
        String nomeProdottoSelezionato = this.promozioniList.getSelectionModel().getSelectedItem();
        Promozioni p = getPromozioneFromNome(nomeProdottoSelezionato);
        if(!this.nuovoScontoLabel.getText().chars().allMatch(Character::isDigit)|| (Integer.parseInt(this.nuovoScontoLabel.getText())<1 || Integer.parseInt(this.nuovoScontoLabel.getText())>100)) inputNonInseritoCorrettamente("lo sconto puo' essere formato solo da numeri compresi tra 1 e 100");
        this.commerciante.modificaScontoPromozione(n.getIDNegozio(),p.getIDpromozione(),Integer.parseInt(this.nuovoScontoLabel.getText()));
        this.negozioComboBox.getItems().clear();
        addNegoziToComboBox();
        addPromozioniToListView(n.getIDNegozio());
    }

    @FXML
    public void aggiungiPressed(ActionEvent event) {
        try{

            Stage stage;
            Parent root;
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/aggiungiPromozione.fxml"));
            root = loader.load();
            AggiungiPromozioneController c = loader.getController();
            if(getNegozioFromList()!=null){
                c.setCommerciante(this.commerciante,getNegozioFromList());
                stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.negozioComboBox.getItems().clear();
        addNegoziToComboBox();
    }

    @FXML
    public void cercaPressed(ActionEvent event) {
        if(this.negozioComboBox.getSelectionModel().isEmpty())inputNonInseritoCorrettamente("non e' stato selezionato nessun negozio");
        else{
            int idNegozio = getNegozioSelezionatoFromNome();
            if(idNegozio==0) return;
            addPromozioniToListView(idNegozio);
        }
        this.negozioComboBox.getItems().clear();
        addNegoziToComboBox();
    }

    private void addPromozioniToListView(int idNegozio) {
        this.promozioniList.getItems().clear();
        this.listaPromozioni = commerciante.getAllPromozioni(idNegozio);
        List<String> stringList = new ArrayList<>();
        String stato;
        for(Promozioni p : listaPromozioni){
            if(p.getProdotto()!=null && p.getProdotto().getNome()!=null){
                if(p.getStato())stato = "attiva";
                else stato = "disattiva";
                stringList.add("la promozione sul prodotto "+p.getProdotto().getNome()+" ha uno sconto del "+p.getSconto()+" ed e' "+stato);
            }
        }
        promozioniList.setItems(FXCollections.observableList(stringList));
    }

    private int getNegozioSelezionatoFromNome() {
        String nomeNegozio = this.negozioComboBox.getSelectionModel().getSelectedItem();
        Negozio n = this.listaNegozi.stream().filter(x->x.getNomeNegozio().equals(nomeNegozio)).findFirst().orElse(null);
        if(n!=null){
            return n.getIDNegozio();
        }
        return 0;
    }

    private void inputNonInseritoCorrettamente(String s) {
        Alert a = new Alert(Alert.AlertType.ERROR,s, ButtonType.OK);
        a.showAndWait();
    }


    public void setCommerciante(CommercianteInterface commerciante){
        this.commerciante = commerciante;
        this.listaNegozi = commerciante.getListaNegozi();
        addNegoziToComboBox();
        this.listaProdotti = new ArrayList<>();
        for(Negozio n : this.listaNegozi){
            this.listaProdotti.addAll(n.getListaProdotti());
        }
    }
    private void addNegoziToComboBox(){
        String old = this.negozioComboBox.getSelectionModel().getSelectedItem();
        for(Negozio n : listaNegozi){
            this.negozioComboBox.getItems().add(n.getNomeNegozio());
        }
        this.negozioComboBox.getSelectionModel().select(old);
        this.negozioComboBox.setPromptText("scegli un negozio...");
    }



    private Negozio getNegozioFromList(){
        if(this.negozioComboBox.getSelectionModel().isEmpty())inputNonInseritoCorrettamente("non e' stato selezionato nessun negozio a cui aggiungere una promozione");
        else{
            String nomeNegozioSelezionato = this.negozioComboBox.getSelectionModel().getSelectedItem();
            return this.listaNegozi.stream().filter(x->x.getNomeNegozio().equals(nomeNegozioSelezionato)).findFirst().orElse(null);
        }
        return null;
    }


    private Promozioni getPromozioneFromNome(String nomeProdottoSelezionato){
        String [] nomi = nomeProdottoSelezionato.split(" ha uno sconto del");
        String nome = nomi[0].split("prodotto")[1];
        String nomeP = nome.trim();
        boolean check = this.listaProdotti.stream().anyMatch(x->x.getNome().equals(nomeP));
        if(check)return this.listaPromozioni.stream().filter(x->x.getProdotto().getNome().contains(nomeP)).findFirst().orElse(null);
        else return null;
    }
}
