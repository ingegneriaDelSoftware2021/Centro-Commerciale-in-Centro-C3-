package it.unicam.cs.ids.c3.model.Vista.locker;

import it.unicam.cs.ids.c3.model.Lockers.Armadietto;
import it.unicam.cs.ids.c3.model.Lockers.LockerInterface;
import it.unicam.cs.ids.c3.model.Ordini.ListaOrdini;
import it.unicam.cs.ids.c3.model.Ordini.OrdineInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.util.List;
import java.util.stream.Collectors;

public class RitiraPaccoLocker {


    @FXML
    private TextField idOrdineTextField;

    @FXML
    private ComboBox<Integer> armadiettiLiberiComboBox;

    private LockerInterface locker;


    @FXML
    public void aggiungiOrdinePressed(ActionEvent event) {
        if(this.armadiettiLiberiComboBox.getSelectionModel().isEmpty())inputNonInseritoCorrettamente("non hai selezionato un locker libero");
        else if (this.idOrdineTextField.getText()==null ||this.idOrdineTextField.getText().equals("") ||!this.idOrdineTextField.getText().chars().allMatch(Character::isDigit))inputNonInseritoCorrettamente("nell'id dell'ordine puoi inserire solo numeri!");
        else{
            int idArm = this.armadiettiLiberiComboBox.getSelectionModel().getSelectedItem();
            int idOrd = Integer.parseInt(this.idOrdineTextField.getText());
            OrdineInterface o = ListaOrdini.getInstance().getOrdine(idOrd);
            this.locker.aggiungiOrdine(idArm,o);
            this.armadiettiLiberiComboBox.getItems().clear();
            addArmadiettiLiberiToComboBox();
            this.idOrdineTextField.setText("");
        }
    }

    private void inputNonInseritoCorrettamente(String s) {
        Alert a = new Alert(Alert.AlertType.ERROR,s, ButtonType.OK);
        a.showAndWait();
    }


    public void setLocker(LockerInterface locker){
        this.locker = locker;
        addArmadiettiLiberiToComboBox();
    }


    private void addArmadiettiLiberiToComboBox(){
        List<Armadietto> armadietti = locker.visualizzaArmadietti().stream().filter(x->x.getOrdine()==null).collect(Collectors.toList());
        for(Armadietto a : armadietti){
            this.armadiettiLiberiComboBox.getItems().add(a.getIDArmadietto());
        }
    }
}
