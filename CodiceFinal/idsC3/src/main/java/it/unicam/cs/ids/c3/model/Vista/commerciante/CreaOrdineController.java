package it.unicam.cs.ids.c3.model.Vista.commerciante;

import it.unicam.cs.ids.c3.model.Clienti.ListaClienti;
import it.unicam.cs.ids.c3.model.Corriere.CorriereInterface;
import it.unicam.cs.ids.c3.model.Corriere.ListaCorrieri;
import it.unicam.cs.ids.c3.model.Esercente.CommercianteInterface;
import it.unicam.cs.ids.c3.model.Esercente.Prodotto;
import it.unicam.cs.ids.c3.model.Lockers.ListaLockers;
import it.unicam.cs.ids.c3.model.Lockers.LockerInterface;
import it.unicam.cs.ids.c3.model.Ordini.ListaOrdini;
import it.unicam.cs.ids.c3.model.Ordini.OrdineInterface;
import it.unicam.cs.ids.c3.model.Ordini.StatoOrdine;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class CreaOrdineController implements Initializable {


    public Label indirizzoLabel;
    @FXML
    private ListView<String> prodottiOrdineListView;
    @FXML
    private ComboBox<Integer> ordiniComboBox;
    @FXML
    private ComboBox<String> scegliDestinazioneComboBox;
    @FXML
    private ComboBox<Integer> scegliCorriereComboBox;

    private CommercianteInterface commerciante;

    private final String Locker = "locker";

    private final String Casa = "casa del cliente";

    private List<CorriereInterface> listaCorrieri;

    private List<OrdineInterface> listaOrdini;











    @FXML
    public void creaOrdineButton(ActionEvent event) {
        if(!this.scegliDestinazioneComboBox.getSelectionModel().isEmpty() && !this.scegliCorriereComboBox.getSelectionModel().isEmpty()){
            selezionaCorriere();
            int idordine = this.ordiniComboBox.getSelectionModel().getSelectedItem();
            selezionaDest(idordine);
            this.listaOrdini = ListaOrdini.getInstance().getOrdini().stream().filter(z->z.getStatoOrdine().equals(StatoOrdine.ORDINECREATO)).collect(Collectors.toList());
            this.prodottiOrdineListView.getItems().clear();
            int idToRemove = this.ordiniComboBox.getSelectionModel().getSelectedItem();
            this.ordiniComboBox.getItems().remove(Integer.valueOf(idToRemove));

        }else inputNonInseritoCorrettamente("non hai compilato tutti i campi!");
    }

    private void selezionaDest(int idOrdine) {
        if(this.scegliDestinazioneComboBox.getSelectionModel().getSelectedItem().equals(Locker)){
            Stage stage = new Stage();
            VBox vbox = new VBox();
            ComboBox<String> lockerComboBox = new ComboBox<>();
            lockerComboBox.setPromptText("seleziona un locker...");
            addLockerToCombobox(lockerComboBox);
            Button button = new Button();
            EventHandler<MouseEvent> eventhandler = event -> {
                if(lockerComboBox.getSelectionModel().getSelectedItem().isEmpty())inputNonInseritoCorrettamente("non hai selezionato nessun Locker!");
                else{
                    String  indLock = lockerComboBox.getSelectionModel().getSelectedItem();
                    LockerInterface l = ListaLockers.getInstance().getLockers().stream().filter(x->x.getIndirizzo().equals(indLock)).findFirst().orElse(null);
                    int idLock = l.getID();
                    commerciante.selezionaDestinazioneLocker(idLock,idOrdine);
                    stage.close();
                }
            };
            button.addEventHandler(MouseEvent.MOUSE_CLICKED,eventhandler);

            vbox.getChildren().add(lockerComboBox);
            vbox.getChildren().add(button);
            Scene scene = new Scene(vbox,300,300);

            stage.setScene(scene);
            stage.show();
        }else this.commerciante.selezionaDestinazioneCasa(this.indirizzoLabel.getText(),idOrdine);
    }

    private void addLockerToCombobox(ComboBox<String> combobox) {
        List<LockerInterface> list = ListaLockers.getInstance().getLockers();
        for(LockerInterface l : list){
           combobox.getItems().add(l.getIndirizzo());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.scegliDestinazioneComboBox.getItems().add(Locker);
        this.scegliDestinazioneComboBox.getItems().add(Casa);
    }

    private void inputNonInseritoCorrettamente(String s) {
        Alert a = new Alert(Alert.AlertType.ERROR,s, ButtonType.OK);
        a.showAndWait();
    }

    public void cercaPressed(ActionEvent event) {
        if (this.ordiniComboBox.getSelectionModel().isEmpty())
            inputNonInseritoCorrettamente("non hai selezionato nessun ordine!");
        else{
            this.indirizzoLabel.setText("");
            this.prodottiOrdineListView.getItems().clear();
            OrdineInterface l = ListaOrdini.getInstance().getOrdine(this.ordiniComboBox.getSelectionModel().getSelectedItem());
            Prodotto previous = new Prodotto(0,"",0,"",0,"",0,0,0);
            for(Prodotto o : l.getListaProdotti()){
                if(o.getIDprodotto()!=previous.getIDprodotto()){
                    long quantita = l.getListaProdotti().stream().filter(x->x.getIDprodotto()==o.getIDprodotto()).count();
                    this.prodottiOrdineListView.getItems().add(o.getNome()+" (X"+quantita+")");
                    previous = o;
                }
            }
            int id;
            if(this.listaOrdini.get(0)!=null){
                id = this.listaOrdini.get(0).getIDCliente();
                String indirizzo = ListaClienti.getInstance().getCliente(id).getIndirizzo();
                this.indirizzoLabel.setText(indirizzo);
            }


        }
    }

    private void addCorrieriToCombobox() {
        for(CorriereInterface c : this.commerciante.getCorrieriDisponibili()){
            this.scegliCorriereComboBox.getItems().add(c.getIDCorriere());
        }
    }


    public void setCommerciante(CommercianteInterface commerciante){
        this.commerciante = commerciante;
        List<OrdineInterface> l = ListaOrdini.getInstance().getOrdini().stream().filter(x->x.getIDCommerciante()==commerciante.getIDCommerciante() && x.getStatoOrdine().equals(StatoOrdine.ORDINECREATO)).collect(Collectors.toList());
        this.listaOrdini = l;
        for(OrdineInterface o : l){
            this.ordiniComboBox.getItems().add(o.getIDOrdine());
        }

        addCorrieriToCombobox();

    }



    private void selezionaCorriere(){
        int idCorriere = this.scegliCorriereComboBox.getSelectionModel().getSelectedItem();
        CorriereInterface c = ListaCorrieri.getInstance().getCorrieri().stream().filter(x->x.getIDCorriere()==idCorriere).findFirst().orElse(null);
        this.commerciante.selezionaCorriere(Objects.requireNonNull(c).getIDCorriere(),this.ordiniComboBox.getSelectionModel().getSelectedItem());
    }
}
