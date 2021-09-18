package it.unicam.cs.ids.c3.model.Vista.commerciante;

import it.unicam.cs.ids.c3.model.Esercente.CommercianteInterface;
import it.unicam.cs.ids.c3.model.Esercente.Negozio;
import it.unicam.cs.ids.c3.model.Esercente.Prodotto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.List;

public class AggiungiPromozioneController {
    @FXML
    public TextField scontoLabel;
    @FXML
    public ComboBox<String> statoCombobox;
    @FXML
    public Button aggiungiButton;
    public ComboBox<String> prodottoCombobox;
    private CommercianteInterface commerciante;
    private final String attivo = "attivo";
    private final String disattivo = "disattivo";
    private List<Prodotto> listaProdotti;
    private Negozio negozio;

    public void setCommerciante(CommercianteInterface commerciante, Negozio n) {
        this.commerciante = commerciante;
        this.statoCombobox.getItems().add(attivo);
        this.statoCombobox.getItems().add(disattivo);
        this.listaProdotti = n.getListaProdotti();
        this.listaProdotti.forEach(x->this.prodottoCombobox.getItems().add(x.getNome()));
        this.negozio = n;
    }

    @FXML
    public void aggiungiPressed(ActionEvent event) {
        if(this.prodottoCombobox.getSelectionModel().isEmpty())inputNonInseritoCorrettamente("Non e' stato selezionato nessun prodotto");
        else if(this.statoCombobox.getSelectionModel().isEmpty())inputNonInseritoCorrettamente("non e' stato selezionato lo stato della nuova promozione");
        else{
            if(this.scontoLabel.getText().isEmpty())inputNonInseritoCorrettamente("non e' stato inserito lo sconto");
            else if(!this.scontoLabel.getText().chars().allMatch(Character::isDigit)|| (Integer.parseInt(this.scontoLabel.getText())<1 || Integer.parseInt(this.scontoLabel.getText())>100)) inputNonInseritoCorrettamente("lo sconto puo' essere formato solo da numeri compresi tra 1 e 100");
            else{
                int sconto = Integer.parseInt(this.scontoLabel.getText());
                boolean stato;
                stato = statoCombobox.getSelectionModel().getSelectedItem().equals(attivo);
                Prodotto p = this.listaProdotti.stream().filter(x->x.getNome().equals(this.prodottoCombobox.getSelectionModel().getSelectedItem())).findFirst().orElse(null);
                if(p!=null){
                    try{
                        this.commerciante.addPromozioni(negozio.getIDNegozio(),p.getIDprodotto(),sconto,stato);
                    }catch (IllegalStateException e){
                        e.printStackTrace();
                        inputNonInseritoCorrettamente("il prodotto in questione ha gia una promozione. Non e' possibile aggiungere due promozioni ad un prodotto");
                    }
                    Stage stage = (Stage) this.aggiungiButton.getScene().getWindow();
                    stage.close();
                }
            }
        }
    }

    private void inputNonInseritoCorrettamente(String messaggio) {
        Alert a = new Alert(Alert.AlertType.ERROR,messaggio, ButtonType.OK);
        a.showAndWait();
    }
}
