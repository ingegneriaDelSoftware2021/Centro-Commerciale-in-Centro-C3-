package it.unicam.cs.ids.c3.model.Vista.commerciante;

import it.unicam.cs.ids.c3.model.Esercente.CommercianteInterface;
import it.unicam.cs.ids.c3.model.Esercente.Negozio;
import it.unicam.cs.ids.c3.model.Esercente.Prodotto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.*;

public class statisticheNegozioController implements Initializable {

    @FXML
    public ListView<String> prodottiVendutiListView;
    @FXML
    public Label casaStatLabel;
    @FXML
    public Label lockerStatLabel;
    @FXML
    public Button cercaButton;
    @FXML
    public ComboBox<String> negozioComboBox;

    private CommercianteInterface commerciante;
    private List<Negozio> listaNegozi;




    public void setCommerciante(CommercianteInterface commerciante){
        this.commerciante = commerciante;
        this.listaNegozi = this.commerciante.getListaNegozi();
        List<Negozio> list = this.commerciante.getListaNegozi();
        addNegoziToComboBox(list);
    }

    @FXML
    public void cercaButtonPressed(ActionEvent event) {
        if(negozioComboBox.getSelectionModel().isEmpty())inputNonInseritoCorrettamente();
        String nomeNegozio = this.negozioComboBox.getValue();
        Negozio n = this.listaNegozi.stream().filter(x-> x.getNomeNegozio().equals(nomeNegozio)).findAny().orElse(null);
        Map<Prodotto,Integer> map = Objects.requireNonNull(n).getProdottiVenduti();
        int[] clientiArray = n.getStatClienti();
        this.prodottiVendutiListView.getItems().clear();
        addStatToTable(map);
        addStatToLabel(clientiArray);
    }

    private void addStatToTable(Map<Prodotto, Integer> map) {
        List<Map.Entry<Prodotto, Integer>> list = new ArrayList<>(map.entrySet());
        for(Map.Entry<Prodotto, Integer> p :list){
            this.prodottiVendutiListView.getItems().add("il prodotto "+p.getKey().getNome()+" e' stato venduto +"+p.getValue()+" volte");
        }
    }

    private void addStatToLabel(int[] clientiArray) {
        this.lockerStatLabel.setText(String.valueOf(clientiArray[1]));
        this.casaStatLabel.setText(String.valueOf(clientiArray[0]));
    }

    private void inputNonInseritoCorrettamente() {
        Alert a = new Alert(Alert.AlertType.ERROR, "non e' stato selezionato alcun negozio",ButtonType.OK);
        a.showAndWait();
    }


    private void addNegoziToComboBox(List<Negozio> list) {
        for(Negozio n : list){
            this.negozioComboBox.getItems().add(n.getNomeNegozio());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        prodottiVendutiListView.setStyle("-fx-control-inner-background: #6be8ce;");

    }
}
