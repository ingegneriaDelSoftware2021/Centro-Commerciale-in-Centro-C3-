package it.unicam.cs.ids.c3.model.Vista.cliente;

import com.sun.javafx.collections.ObservableListWrapper;
import it.unicam.cs.ids.c3.model.Esercente.Prodotto;
import it.unicam.cs.ids.c3.model.Ordini.OrdineInterface;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableIntegerValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class OrdiniCheckController implements Initializable {


    @FXML
    private TableView<OrdineInterface> tabellaOrdini;

    @FXML
    private TableColumn<OrdineInterface,String> IDOrdine;

    @FXML
    private TableColumn<OrdineInterface,String> statoOrdine;


    @FXML
    private TableColumn<OrdineInterface,String> prodotti;


    private Integer id;
    private StringProperty stato;
    private StringProperty prod;




    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }



    public void setAllOrdini(List<OrdineInterface> orderList){
        for (OrdineInterface o : orderList) {
            this.stato = new SimpleStringProperty(String.valueOf(o.getStatoOrdine()));
            this.IDOrdine.setCellValueFactory(new PropertyValueFactory<>("IDOrdine"));
            this.statoOrdine.setCellValueFactory(new PropertyValueFactory<>("statoOrdine"));
            this.prodotti.setCellValueFactory(new PropertyValueFactory<>("Prodotti"));
            this.tabellaOrdini.getItems().add(o);
        }


    }






}
