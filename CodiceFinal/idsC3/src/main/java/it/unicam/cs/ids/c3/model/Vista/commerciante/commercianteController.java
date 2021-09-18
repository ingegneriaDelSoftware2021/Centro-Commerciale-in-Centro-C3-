package it.unicam.cs.ids.c3.model.Vista.commerciante;

import it.unicam.cs.ids.c3.model.Esercente.CommercianteInterface;
import it.unicam.cs.ids.c3.model.Esercente.ListaCommercianti;
import it.unicam.cs.ids.c3.model.Vista.commerciante.VisualizzaNegoziCommController;
import it.unicam.cs.ids.c3.model.Vista.commerciante.gestisciPromozioniController;
import it.unicam.cs.ids.c3.model.Vista.commerciante.statisticheNegozioController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;


public class commercianteController{

    @FXML
    public MenuItem creaOrdineMenuItem;
    @FXML
    public MenuItem gestisciPromozioniMenuItem;
    @FXML
    public MenuItem statisticheNegozioMenuItem;
    @FXML
    public MenuItem visualizzaNegoziMenuItem;
    @FXML
    public Label nomeLabel;
    @FXML
    public Label portafoglioLabel;


    CommercianteInterface commerciante;

    @FXML
    public void visualizzaNegoziPressed(ActionEvent event) {
        Parent root;
        Stage stage;
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/visualizzaNegozi.fxml"));
            root = loader.load();
            VisualizzaNegoziCommController g = loader.getController();
            g.setCommerciante(this.commerciante);
            stage = new Stage();
            stage.setScene(new Scene(root));
            stage.getIcons().add(new Image((getClass().getResource("/image.png")).toString()));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void statisticheNegozioPressed(ActionEvent event) {
        Parent root;
        Stage stage;
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/statisticheNegozio.fxml"));
            root = loader.load();
            statisticheNegozioController g = loader.getController();
            g.setCommerciante(this.commerciante);
            stage = new Stage();
            stage.setScene(new Scene(root));
            stage.getIcons().add(new Image((getClass().getResource("/image.png")).toString()));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void gestisciPromozioni(ActionEvent event) {
        Parent root;
        Stage stage;
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/gestisciPromozioni.fxml"));
            root = loader.load();
            gestisciPromozioniController g = loader.getController();
            g.setCommerciante(this.commerciante);
            stage = new Stage();
            stage.setScene(new Scene(root));
            stage.getIcons().add(new Image((getClass().getResource("/image.png")).toString()));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void creaOrdinePressed(ActionEvent event) {
        Parent root;
        Stage stage;
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/creaOrdine.fxml"));
            root = loader.load();
            CreaOrdineController g = loader.getController();
            g.setCommerciante(this.commerciante);
            stage = new Stage();
            stage.setScene(new Scene(root));
            stage.getIcons().add(new Image((getClass().getResource("/image.png")).toString()));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setUserCommerciante(String user) {
        this.commerciante = ListaCommercianti.getInstance().getCommercianteFromUser(user);
        this.nomeLabel.setText(this.commerciante.getNome());
        this.portafoglioLabel.setText(String.valueOf(this.commerciante.getSoldiPortafoglio()));
    }

    @FXML
    public void logoutPressed(ActionEvent event) {
        try {
            Stage stage;
            Parent root;
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/login.fxml"));
            root = fxmlloader.load();
            stage = (Stage) this.nomeLabel.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.getIcons().add(new Image((getClass().getResource("/image.png")).toString()));
            stage.show();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
