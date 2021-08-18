package it.unicam.cs.ids.c3.model.Clienti;

import it.unicam.cs.ids.c3.model.DBLocale;
import it.unicam.cs.ids.c3.model.Esercente.*;
import it.unicam.cs.ids.c3.model.Ordini.Ordine;
import it.unicam.cs.ids.c3.model.Utente.Utente;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Questa classe &egrave; un'implementazione di default dell'interfaccia ClienteInterface
 * e della classe astratta Utente.
 * @author Davide Menghini, Francesco Allevi.
 */
public class Cliente extends Utente implements ClienteInterface {

    private final int IDcliente;


    private Carrello carrello;



    private float portafoglio;


    /**
     * Questo costruttore di deafult serve per inizializzare un cliente.
     * @param iDcliente id di questo cliente.
     * @param nome nome di questo cliente.
     * @param indirizzo indirizzo dell'abitazione del cliente.
     * @param IDCarrello -id del carrello del cliente.
     */
    public Cliente(int iDcliente, String nome, String indirizzo, int IDCarrello) {
        super();
        this.IDcliente = iDcliente;
        setNome(nome);
        super.setIndirizzo(indirizzo);
        this.portafoglio = 0;
        //TODO assegnare il carrello
    }








    @Override
    public List<Negozio> visualizzaNegozi() {
        return null;//listaNegozi.getNegozi().;

    }

    @Override
    public List<Ordine> visualizzaOrdini() {
        //TODO implementare
        return null;
    }

    @Override
    public List<Promozioni> visualizzaPromozioni() {
        //TODO implementare
        return null;
    }


    @Override
    public Carrello visualizzaCarrello() {
        return carrello;
    }

    @Override
    public boolean inviaPagamento(Carrello carrello) {
        //TODO implementare
        Commerciante c = Objects.requireNonNull(ListaNegozi.getInstance().getNegozi(carrello.getIDCommerciante()).
                stream().findFirst().orElse(null)).getPropietario();
        if(carrello.getTotale()>this.portafoglio) return false;
        return c.riceviPagamento(this.IDcliente, carrello.getTotale());

    }

    @Override
    public List<Prodotto> cercaProdotto(String nomeProdotto, String nomeNegozio) {
        if(nomeProdotto.equals("") || nomeNegozio.equals("")) throw new NullPointerException("uno o entrambi le stringhe passate sono null");
        try {
            DBLocale db = DBLocale.getInstance();
            Negozio negozio = db.getAllNegozi().stream().filter(x -> x.getNomeNegozio().toLowerCase().contains(nomeNegozio.toLowerCase()))
                    .findFirst().orElse(null);
            return db.getAllProdottiFromNegozio(Objects.requireNonNull(negozio).getIDNegozio(),nomeProdotto);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //TODO implementare
        return null;
    }

    @Override
    public int getIDcliente() {
        return this.IDcliente;
    }




    @Override
    public void aggiungiCredito(double portafoglio) {
        if(portafoglio<=0) return;
        this.portafoglio+=portafoglio;
    }


    @Override
    public double getPortafoglio() {
        return this.portafoglio;
    }

    @Override
    public Carrello getCarrello() {
        return this.carrello;
    }


}
