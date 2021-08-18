package it.unicam.cs.ids.c3.model.Ordini;

import com.google.common.base.Objects;
import it.unicam.cs.ids.c3.model.Clienti.Carrello;

import it.unicam.cs.ids.c3.model.DBLocale;

import java.util.Random;


/**
 * Quiesta classe &egrave; un implementazione di default dell'interfaccia OrdineInterface.
 * @author Davide Menghini, Francesco Allevi.
 */
public class Ordine implements OrdineInterface{

    private final int IDCliente;

    private StatoOrdine statoOrdine;

    private String destinazione;

    private  int IDOrdine;

    private int IDCorriere;

    private Carrello carrello;

    private int IDCommerciante;

    private int IDLocker;

    /**
     * Questo &egrave; un costruttore di default nel caso in cui l'indirizzo della consegna
     * &egrave; la casa del cliente.
     * @param idCliente id del cliente.
     * @param statoOrdine stato dell'ordine.
     * @param destinazione  indirizzo del cliente a cui consegnare l'ordine.
     */
    public Ordine(int IDOrdine,int idCliente, StatoOrdine statoOrdine, String destinazione) {
        IDCliente = idCliente;
        this.IDOrdine = IDOrdine;
        this.statoOrdine = statoOrdine;
        this.destinazione = destinazione;
        statoOrdine = StatoOrdine.ORDINECREATO;
        //TODO aggiustare questo costruttore
        try {
            this.IDCorriere = DBLocale.getInstance().getIDCorriereFromIDOrdine(this.IDOrdine);
            this.IDCommerciante = DBLocale.getInstance().getIDCommercianteFromIDOrdine(this.IDOrdine);
            this.carrello = new Carrello(java.util.Objects.requireNonNull(DBLocale.getInstance().getAllCarrello().stream().
                    filter(x -> x.getIDCarrello() == idCliente)
                    .findFirst().orElse(null)).getIDCarrello());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        this.IDLocker = 2;
    }

    /**
     * Questo &egrave; un costruttore di default nel caso in cui l'indirizzo della consegna
     * &egrave; ancora da definire.
     * @param idCliente id del cliente.
     * @param statoOrdine stato dell'ordine.
     */
    public Ordine(int IDOrdine, int idCliente, StatoOrdine statoOrdine) throws ClassNotFoundException {
        this.IDCliente = idCliente;
        this.statoOrdine = statoOrdine;
        this.carrello = new Carrello(idCliente);
        this.destinazione = "";
        this.IDOrdine = IDOrdine;
        //TODO completare questo costruttore
        this.carrello = DBLocale.getInstance().getAllCarrello().stream().filter(z -> z.getIDCarrello()==idCliente).findFirst().orElse(null);
        this.IDCorriere = DBLocale.getInstance().getIDCorriereFromIDOrdine(this.IDOrdine);
        this.IDCommerciante = DBLocale.getInstance().getIDCommercianteFromIDOrdine(this.IDOrdine);
        this.IDLocker = 0;
    }


    /**
     * Questo &egrave; un costruttore di default nel caso in cui l'indirizzo della consegna
     *      * &egrave; un locker.
     * @param idCliente id del cliente.
     * @param statoOrdine stato dell'ordine.
     * @param IDLocker id del locker di destinazione.
     */
    public Ordine(int IDOrdine, int idCliente, StatoOrdine statoOrdine, int IDLocker) throws ClassNotFoundException {
        this.IDCliente = idCliente;
        this.statoOrdine = statoOrdine;
        this.IDOrdine = IDOrdine;
        this.carrello = DBLocale.getInstance().getCarrelloFromID(this.IDCliente);
        //TODO cercare sulla lista di locker l'id e recuperare l'indirizzo.
        this.IDLocker = IDLocker;
        try {
            this.IDCommerciante = DBLocale.getInstance().getIDCommercianteFromIDOrdine(this.IDOrdine);
            this.IDCorriere = DBLocale.getInstance().getIDCorriereFromIDOrdine(this.IDOrdine);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ordine)) return false;
        Ordine ordine = (Ordine) o;
        return getIDCliente() == ordine.getIDCliente();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getIDCliente());
    }





    /**
     *
     * @param carrello
     */
    public Ordine(Carrello carrello) throws ClassNotFoundException {
        this.IDCliente = carrello.getIDCarrello();
        this.statoOrdine = StatoOrdine.ORDINECREATO;
        this.carrello = carrello;
        IDCommerciante = DBLocale.getInstance().getIDCommFromNegozio(carrello.getIDNegozio());
        this.destinazione = "";
        this.IDCorriere = 0;
        this.IDLocker = 0;
        generaID();
    }

    private void generaID() throws ClassNotFoundException {
        Random r = new Random();
        boolean check = false;
        while(!check){
            this.IDOrdine = Math.abs(r.nextInt());
            if(DBLocale.getInstance().getAllOrdini().stream().noneMatch(x -> x.getIDOrdine() == this.IDOrdine)) check = true;
        }
    }

    @Override
    public int getIDCliente() {
        return IDCliente;
    }

    @Override
    public StatoOrdine getStatoOrdine() {
        return statoOrdine;
    }

    @Override
    public String getDestinazione() {
        return destinazione;
    }

    @Override
    public int getIDOrdine() {
        return IDOrdine;
    }


    @Override
    public void setStatoOrdine(StatoOrdine statoOrdine) {
        this.statoOrdine = statoOrdine;
    }

    @Override
    public int getIDCorriere() {
        return IDCorriere;
    }

    @Override
    public void setIDCorriere(int IDCorriere) {
        try {
            DBLocale.getInstance().setCorriereToOrdine(IDCorriere,this.IDOrdine);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setDestinazione(String destinazione){
        this.destinazione = destinazione;
    }

    @Override
    public Carrello getCarrello() {
        return this.carrello;
    }

    @Override
    public int getIDCommerciante() {
        return IDCommerciante;
    }

    @Override
    public int getIDLocker() {
        return this.IDLocker;
    }

    @Override
    public void setIDLocker(int IDlocker) {
        this.IDLocker = IDlocker;
        try {
            DBLocale.getInstance().setIDLockerToOrdine(IDLocker, this.IDOrdine);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
