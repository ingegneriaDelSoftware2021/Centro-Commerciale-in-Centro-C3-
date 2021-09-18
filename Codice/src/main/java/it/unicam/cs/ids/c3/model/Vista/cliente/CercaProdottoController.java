package it.unicam.cs.ids.c3.model.Vista.cliente;

import it.unicam.cs.ids.c3.model.Clienti.ClienteInterface;
import it.unicam.cs.ids.c3.model.Esercente.ListaNegozi;
import it.unicam.cs.ids.c3.model.Esercente.Negozio;
import it.unicam.cs.ids.c3.model.Esercente.Prodotto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;
import java.util.Objects;

public class CercaProdottoController{
    @FXML
    public TextField cercaTextField;
    @FXML
    public Button cercaButton;
    @FXML
    public ListView<String> prodottiListView;
    @FXML
    public Button aggiungiProdottoButton;
    @FXML
    public ChoiceBox<String> negozioChoiceBox;
    @FXML
    public TextField quantitaTextField;

    private ClienteInterface cliente;


    private List<Prodotto> prodottoList;


    @FXML
    public void cercaButtonPressed(ActionEvent event) {
        this.prodottiListView.getItems().clear();
        String nomeDaCercare = this.cercaTextField.getText();
        if(!nomeDaCercare.equals("")&& !negozioChoiceBox.getSelectionModel().isEmpty()){
            String negozioDaCercare = this.negozioChoiceBox.getValue();
            Negozio n = ListaNegozi.getInstance().getNegozi().stream().filter(x->x.getNomeNegozio().contains(negozioDaCercare)).findFirst().orElse(null);
            this.prodottoList = this.cliente.cercaProdotto(nomeDaCercare, Objects.requireNonNull(n).getIDNegozio());
            addProdottiToListView(prodottoList);
        }else if(nomeDaCercare.equals(""))inputNonInseritoCorrettamente("il nome del prodotto da cercare e' vuoto!");
        else if(negozioChoiceBox.getSelectionModel().isEmpty()) inputNonInseritoCorrettamente("non hai selezionato il negozio da cui cercare il prodotto!");
    }


    private void addProdottiToListView(List<Prodotto> list) {
        ObservableList<String> l;
        Prodotto previous = new Prodotto(0,"",0,"",0,"",0,0,0);
        for(Prodotto p : list){
            if(!p.equals(previous)){
                l = FXCollections.observableArrayList();
                l.add(p.getNome());
                this.prodottiListView.getItems().add(l.get(0));
            }
            previous = p;
        }
    }

    private void inputNonInseritoCorrettamente(String s) {
        Alert alert = new Alert(Alert.AlertType.ERROR, s, ButtonType.OK);
        alert.showAndWait();
    }

    @FXML
    public void aggiungiButtonPressed(ActionEvent event) {
        String s = this.prodottiListView.getSelectionModel().getSelectedItems().get(0);
        if(s==null||s.equals(""))inputNonInseritoCorrettamente("non hai selezionato alcun prodotto da aggiungere al carrello");
        if(this.quantitaTextField==null || this.quantitaTextField.getText().equals("")) inputNonInseritoCorrettamente("non e' stata inserita la quantita");
        if(!this.quantitaTextField.getText().chars().allMatch(Character::isDigit)) inputNonInseritoCorrettamente("il carattere inserito non e' un numero");
        else{
            Prodotto z = this.prodottoList.stream().filter(x -> x.getNome().contains(s)).findFirst().orElse(null);
            if(z==null)System.out.println("nooooo p e' null");
            try{
                this.prodottoList.stream().filter(x -> x.getNome().contains(s)).
                        findFirst().
                        ifPresent(p -> this.cliente.aggiungiProdottoAlCarrello(p, Integer.parseInt(quantitaTextField.getText())));
            }catch (IllegalArgumentException exception){
                inputNonInseritoCorrettamente("i prodotti inseriti nel carrello devono essere dello statesso negozio!");
            }

        }
    }


    public void setCliente(ClienteInterface cliente){
        this.cliente = cliente;
        List<Negozio> list = this.cliente.visualizzaNegozi();
        for(Negozio n : list){
            this.negozioChoiceBox.getItems().add(n.getNomeNegozio());
        }

    }






}
