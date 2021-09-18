package it.unicam.cs.ids.c3.model.Vista.cliente;

import it.unicam.cs.ids.c3.model.Clienti.ClienteInterface;
import it.unicam.cs.ids.c3.model.Clienti.ListaClienti;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ClienteController implements Initializable {


    @FXML
    private MenuBar menuBar;

    @FXML
    private Menu azioniMenu;

    @FXML
    private MenuItem statoOrdine;

    @FXML
    private MenuItem promozioni;


    @FXML
    private MenuItem aggiungiCredito;

    @FXML
    private MenuItem gestisciCarrello;

    @FXML
    private MenuItem cercaProdotto;

    @FXML
    private MenuItem logoutItem;


    @FXML
    private GridPane gridInfo;

    @FXML
    private Label nomeLabel;

    @FXML
    private Label portafoglioLabel;


    private String user;


    ClienteInterface cliente;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gridInfo.setAlignment(Pos.CENTER);
    }




    @FXML
    public void getAllOrdiniClientePressed(ActionEvent event){
        cliente = ListaClienti.getInstance().getClienteFromUser(this.user);
        Stage stage;
        stage = new Stage();
        stage.setTitle("Centro Commerciale In Centro C3");
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/statoOrdineCheck.fxml"));
            Parent root = loader.load();
            OrdiniCheckController c = loader.getController();
            Objects.requireNonNull(c).setAllOrdini(this.cliente.getAllOrdiniCliente());
            stage.setScene(new Scene(root));
            stage.getIcons().add(new Image((getClass().getResource("/image.png")).toString()));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void promozioniPressed(ActionEvent event){
        cliente = ListaClienti.getInstance().getClienteFromUser(this.user);
        Stage stage;
        stage = new Stage();
        stage.setTitle("Centro Commerciale In Centro C3");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Visualizzapromozione.fxml"));
            Parent root = loader.load();
            VisualizzaPromozioneController c = loader.getController();
            Objects.requireNonNull(c).setTabellaPromozioni(cliente.visualizzaPromozioni());
            stage.setScene(new Scene(root));
            stage.getIcons().add(new Image((getClass().getResource("/image.png")).toString()));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void aggiungiCreditoPressed(ActionEvent event){
        cliente = ListaClienti.getInstance().getClienteFromUser(this.user);
        Stage stage;
        stage = new Stage();
        stage.setTitle("Centro Commerciale In Centro C3");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/aggiungiCredito.fxml"));
            Parent root = loader.load();
            AggiungiCreditoController c = loader.getController();
            Objects.requireNonNull(c).setCliente(cliente, this);
            stage.setScene(new Scene(root));
            stage.getIcons().add(new Image((getClass().getResource("/image.png")).toString()));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void logoutPressed(ActionEvent event){
             try {
                 Stage stage;
                 Parent root;
                 FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/login.fxml"));
                 root = fxmlloader.load();
                 stage = (Stage) this.nomeLabel.getScene().getWindow();
                 stage.setScene(new Scene(root));
                 stage.show();
             }
             catch (IOException e)
             {
                 e.printStackTrace();
             }
    }




    public void setUserCliente(String user) {
        this.user = user;
        cliente = ListaClienti.getInstance().getClienteFromUser(this.user);
        nomeLabel.setText(cliente.getNome());
        portafoglioLabel.setText(String.valueOf(cliente.getPortafoglio()));
    }



    @FXML
    public void gestisciCarrelloPressed(ActionEvent event){
        cliente = ListaClienti.getInstance().getClienteFromUser(this.user);
        try{
            Stage stage = new Stage();;
            Parent root;
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/gestisciCarrello.fxml"));
            root = loader.load();
            GestisciCarrelloController g = loader.getController();
            g.setCliente(this.cliente);
            stage.setScene(new Scene(root));
            stage.getIcons().add(new Image((Objects.requireNonNull(getClass().getResource("/image.png"))).toString()));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void cercaProdottoPressed(ActionEvent event) {
        cliente = ListaClienti.getInstance().getClienteFromUser(this.user);
        try{
            Stage stage = new Stage();
            Parent root;
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/cercaProdotto.fxml"));
            root = loader.load();
            CercaProdottoController g = loader.getController();
            g.setCliente(this.cliente);
            stage.setScene(new Scene(root));
            stage.getIcons().add(new Image((Objects.requireNonNull(getClass().getResource("/image.png"))).toString()));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
