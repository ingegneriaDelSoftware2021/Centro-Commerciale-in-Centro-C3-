package it.unicam.cs.ids.c3.model.Vista.corriere;

import it.unicam.cs.ids.c3.model.Corriere.CorriereInterface;
import it.unicam.cs.ids.c3.model.Lockers.ListaLockers;
import it.unicam.cs.ids.c3.model.Lockers.LockerInterface;
import it.unicam.cs.ids.c3.model.Ordini.ListaOrdini;
import it.unicam.cs.ids.c3.model.Ordini.OrdineInterface;
import it.unicam.cs.ids.c3.model.Ordini.StatoOrdine;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class VisualizzaOrdiniController implements Initializable {


    @FXML
    private ComboBox<String> tipoOrdiniCombobox;

    @FXML
    private ListView<String> ordiniListView;


    private CorriereInterface corriere;

    private String daRititrare = "ordini da ritirare";

    private String daConsegnare = "ordini da consegnare";

    private String tutti = "tutti gli ordini";





    public void setCorriere(CorriereInterface corriere){
        this.corriere = corriere;

    }


    @FXML
    public void ritiraPressed(ActionEvent event) {
        if(this.tipoOrdiniCombobox.getSelectionModel().isEmpty()|| !this.tipoOrdiniCombobox.getSelectionModel().getSelectedItem().equals(daRititrare)) InputNonInseritoCorrettamente("seleziona la lista degli ordini da ritirare!");
        else if(this.ordiniListView.getSelectionModel().isEmpty())InputNonInseritoCorrettamente("non hai selezionato nessun ordine da ritirare");
        else{
            int idOrdine = Integer.parseInt(this.ordiniListView.getSelectionModel().getSelectedItem().split(",")[0]);
            this.corriere.setOrdineToINTRANSITO(idOrdine);
            this.ordiniListView.getItems().clear();
            addOrdiniToList(StatoOrdine.INPREPARAZIONE);

        }
    }

    @FXML
    public void consegnaPressed(ActionEvent event) {
        if(this.tipoOrdiniCombobox.getSelectionModel().isEmpty()|| !this.tipoOrdiniCombobox.getSelectionModel().getSelectedItem().equals(daConsegnare)) InputNonInseritoCorrettamente("seleziona la lista degli ordini da consegnare!");
        else if(this.ordiniListView.getSelectionModel().isEmpty())InputNonInseritoCorrettamente("non hai selezionato nessun ordine da consegnare");
        else{
            int idOrdine = Integer.parseInt(this.ordiniListView.getSelectionModel().getSelectedItem().split(",")[0]);
            OrdineInterface ordine = ListaOrdini.getInstance().getOrdine(idOrdine);
            this.corriere.setOrdineToCONSEGNATO(idOrdine);
            this.ordiniListView.getItems().clear();
            addOrdiniToList(StatoOrdine.INTRANSITO);
        }
    }

    @FXML
    public void cercaOrdiniPressed(ActionEvent event) {
        this.ordiniListView.getItems().clear();
        if(this.tipoOrdiniCombobox.getSelectionModel().isEmpty())InputNonInseritoCorrettamente("scegli che tipo di ordini vuoi visualizzare!");
        else{
            if(this.tipoOrdiniCombobox.getSelectionModel().getSelectedItem().equals(daRititrare)){
                addOrdiniToList(StatoOrdine.INPREPARAZIONE);
            }else if(this.tipoOrdiniCombobox.getSelectionModel().getSelectedItem().equals(daConsegnare)){
                addOrdiniToList(StatoOrdine.INTRANSITO);
            }else if(this.tipoOrdiniCombobox.getSelectionModel().getSelectedItem().equals(tutti)){
                addOrdiniToList(null);
            }
        }
    }

    private void addOrdiniToList(StatoOrdine statoOrdine) {
        if(statoOrdine!=null){
            List<OrdineInterface> list = this.corriere.getListaOrdine().stream().filter(x->x.getStatoOrdine()==statoOrdine && x.getIDCorriere()==this.corriere.getIDCorriere()).collect(Collectors.toList());
            for(OrdineInterface o : list){
                checkOrdineAndAdd(o);
            }
        }else{
            List<OrdineInterface> list = this.corriere.getListaOrdine();
            for(OrdineInterface o : list){
                checkOrdineAndAdd(o);
            }
    }

    }

    private void checkOrdineAndAdd(OrdineInterface o){
        if(o.getDestinazione()!=null && !o.getDestinazione().equals("")){
            this.ordiniListView.getItems().add(o.getIDOrdine()+", indirizzo: "+o.getDestinazione());
        }else{
            LockerInterface l = ListaLockers.getInstance().getLockers().stream().parallel().filter(x->x.getID()==o.getIDLocker()).findFirst().orElse(null);
            this.ordiniListView.getItems().add(o.getIDOrdine()+", indirizzo: "+ Objects.requireNonNull(l).getIndirizzo());
        }
    }

    private void InputNonInseritoCorrettamente(String s) {
        Alert a = new Alert(Alert.AlertType.ERROR,s, ButtonType.OK);
        a.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.tipoOrdiniCombobox.getItems().add(daRititrare);
        this.tipoOrdiniCombobox.getItems().add(daConsegnare);
        this.tipoOrdiniCombobox.getItems().add(tutti);
    }
}
