package it.unicam.cs.ids.c3.model.Vista.locker;

import it.unicam.cs.ids.c3.model.Clienti.ListaClienti;
import it.unicam.cs.ids.c3.model.Lockers.ListaLockers;
import it.unicam.cs.ids.c3.model.Lockers.LockerInterface;
import it.unicam.cs.ids.c3.model.Vista.cliente.OrdiniCheckController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class vistaLockerController {

    @FXML
    private TextField cercaOrdineTextField;
    @FXML
    private Label indirizzoLabel;

    private LockerInterface locker;

    @FXML
    public void ritiraPaccoPressed(ActionEvent event) {
        this.locker = ListaLockers.getInstance().getLockers().stream().filter(x->x.getID()==this.locker.getID()).findFirst().orElse(null);
        Stage stage;
        stage = new Stage();
        stage.setTitle("Centro Commerciale In Centro C3");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ritiraPaccoLocker.fxml"));
            Parent root = loader.load();
            RitiraPaccoLocker c = loader.getController();
            Objects.requireNonNull(c).setLocker(this.locker);
            stage.setScene(new Scene(root));
            stage.getIcons().add(new Image((getClass().getResource("/image.png")).toString()));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void consegnaPaccoPressed(ActionEvent event) {

    }

    @FXML
    public void logoutPressed(ActionEvent event) {

    }


    public void setLocker(String user){
        locker = ListaLockers.getInstance().getLockerFromUser(user);
        this.indirizzoLabel.setText(locker.getIndirizzo());
    }

    public void consegnaOrdinePressed(ActionEvent event) {
        if(this.cercaOrdineTextField.getText()==null || this.cercaOrdineTextField.getText().equals(""))inputErrato("non e' stato inserito alcun ordine da cercare!");
        else{
            if(!this.cercaOrdineTextField.getText().chars().allMatch(Character::isDigit))inputErrato("puoi inserire solo numeri!");
            else{
                int idordine = Integer.parseInt(this.cercaOrdineTextField.getText());
                this.cercaOrdineTextField.setText("");
                Alert risultato;
                risultato = new Alert(Alert.AlertType.INFORMATION,this.locker.aggiornaStatoOrdine(idordine),ButtonType.OK);
                risultato.showAndWait();
            }
        }
    }

    public void cercaOrdinePressed(ActionEvent event) {
        if(this.cercaOrdineTextField.getText()==null || this.cercaOrdineTextField.getText().equals(""))inputErrato("non e' stato inserito alcun ordine da cercare!");
        else{
            if(!this.cercaOrdineTextField.getText().chars().allMatch(Character::isDigit))inputErrato("puoi inserire solo numeri!");
            else{
                int idordine = Integer.parseInt(this.cercaOrdineTextField.getText());

                Alert risultato;
                int idArmadietto = this.locker.cercaOrdine(idordine);
                if(idArmadietto!=0){
                    risultato= new Alert(Alert.AlertType.INFORMATION,"l'ordine "+ idordine +" sta nell'armadietto: "+idArmadietto,ButtonType.OK);

                }
                else {
                    risultato = new Alert(Alert.AlertType.INFORMATION,"l'ordine inserito non e' presente!",ButtonType.OK);
                }
                risultato.showAndWait();

            }

        }
    }

    private void inputErrato(String s) {
        Alert a = new Alert(Alert.AlertType.ERROR,s, ButtonType.OK);
        a.showAndWait();
    }
}
