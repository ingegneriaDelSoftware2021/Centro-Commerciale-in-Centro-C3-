package it.unicam.cs.ids.c3.model.Vista.corriere;

import it.unicam.cs.ids.c3.model.Corriere.CorriereInterface;
import it.unicam.cs.ids.c3.model.Corriere.ListaCorrieri;
import it.unicam.cs.ids.c3.model.Vista.commerciante.CreaOrdineController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.stream.Collectors;

public class vistaCorriereController {
    @FXML
    private Label nomeLabel;
    @FXML
    private Label disponibilitaLabel;

    private CorriereInterface corriere;

    private String disponibile = "DISPONIBILE";

    private String indisponibile = "INDISPONIBILE";

    @FXML
    public void cambiaDisponibilitaPressed(ActionEvent event) {
        boolean oldDisponibilita = corriere.getDisponibilita();
        this.corriere.setDisponibilita(!oldDisponibilita);
        if(this.disponibilitaLabel.getText().equals(disponibile))this.disponibilitaLabel.setText(indisponibile);
        else this.disponibilitaLabel.setText(disponibile);
    }

    @FXML
    public void visualizzaOrdiniPressed(ActionEvent event) {
        Parent root;
        Stage stage;
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/VisualizzaOrdiniCorriere.fxml"));
            root = loader.load();
            VisualizzaOrdiniController g = loader.getController();
            g.setCorriere(this.corriere);
            stage = new Stage();
            stage.setScene(new Scene(root));
            stage.getIcons().add(new Image((getClass().getResource("/image.png")).toString()));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @FXML
    public void logoutPressed(ActionEvent event) {
    }



    public void setCorriere(String user){
        this.corriere = ListaCorrieri.getInstance().getCorrieri().stream().filter(x->x.getUser().equals(user)).findFirst().orElse(null);
        if(Objects.requireNonNull(this.corriere).getDisponibilita())this.disponibilitaLabel.setText(disponibile);
        else this.disponibilitaLabel.setText(indisponibile);
        this.nomeLabel.setText(this.corriere.getNome());


    }


}
