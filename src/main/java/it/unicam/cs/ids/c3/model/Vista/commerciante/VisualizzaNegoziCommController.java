package it.unicam.cs.ids.c3.model.Vista.commerciante;

import it.unicam.cs.ids.c3.model.Esercente.CommercianteInterface;
import it.unicam.cs.ids.c3.model.Esercente.Negozio;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.List;

public class VisualizzaNegoziCommController {
    @FXML
    private TableView<Negozio> negozioTabella;
    @FXML
    private TableColumn<Negozio,String> nomeColonna;

    private CommercianteInterface commerciante;

    public void setCommerciante(CommercianteInterface commerciante) {
        this.commerciante = commerciante;
        this.nomeColonna.setStyle("-fx-background-color:#6be8ce;");
        this.nomeColonna.setResizable(false);
        addNegoziToTable(this.commerciante.getListaNegozi());
    }

    private void addNegoziToTable(List<Negozio> listaNegozi) {
        for(Negozio n : listaNegozi){
            this.nomeColonna.setCellValueFactory(x->new SimpleStringProperty(x.getValue().getNomeNegozio()));
            this.negozioTabella.getItems().add(n);
        }
    }
}
