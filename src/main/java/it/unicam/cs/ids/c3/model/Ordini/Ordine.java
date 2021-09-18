package it.unicam.cs.ids.c3.model.Ordini;
import com.google.common.base.Objects;
import it.unicam.cs.ids.c3.model.Esercente.Prodotto;

import java.util.ArrayList;
import java.util.List;
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

    private List<Prodotto> listaProdotti;
    private int IDCommerciante;
    private final int IDNegozio;
    private int IDLocker;





    /**
     * Questo &egrave; un costruttore di default nel caso in cui l'indirizzo della consegna
     * &egrave; ancora da definire.
     * @param idCliente id del cliente.
     * @param statoOrdine stato dell'ordine.
     */
    public Ordine(int IDOrdine, int idCliente, StatoOrdine statoOrdine, int idnegozio) {
        this.IDCliente = idCliente;
        this.statoOrdine = statoOrdine;
        this.listaProdotti = ListaOrdini.getInstance().getProdottiFromOrdine(this.IDOrdine);
        this.destinazione = "";
        this.IDOrdine = IDOrdine;
        this.IDCorriere = ListaOrdini.getInstance().getIDCorriereFromIDOrdine(this.IDOrdine);
        this.IDCommerciante = ListaOrdini.getInstance().getIDCommercianteFromIDOrdine(this.IDOrdine);
        this.IDLocker = 0;
        this.IDNegozio = idnegozio;
        System.out.println("costruttore senza consegna");
    }



    /**
     * costruttore compketo dell'ordine
     * @param idOrdine id dell'ordine
     * @param idCorriere id del corriere
     * @param idCliente id del cliente
     * @param idCommerciante id del commerciante
     * @param idLocker id del locker
     * @param statoOrdine stato dell'ordine
     * @param indirizzo indirizzo della casa del cliente
     * @param idNegozio id del negozio
     */
    public Ordine(int idOrdine, int idCorriere, int idCliente, int idCommerciante, int idLocker, String statoOrdine, String indirizzo, int idNegozio) {
        this.IDCliente = idCliente;
        this.statoOrdine = StatoOrdine.valueOf(statoOrdine);
        this.IDOrdine = idOrdine;
        this.listaProdotti = ListaOrdini.getInstance().getProdottiFromOrdine(this.IDOrdine);
        this.IDLocker = idLocker;
        this.IDNegozio = idNegozio;
        this.IDCommerciante = idCommerciante;
        this.IDCorriere = idCorriere;
        this.destinazione = indirizzo;
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
     * @param prodotti
     */
    public Ordine(List<Prodotto> prodotti, int IDCliente) throws ClassNotFoundException {

        int idn = prodotti.stream().map(Prodotto::getIDNegozio).findFirst().orElse(0);
        this.IDCliente = IDCliente;
        this.statoOrdine = StatoOrdine.ORDINECREATO;
        this.listaProdotti = prodotti;
        IDCommerciante = ListaOrdini.getInstance().getIDCommFromNegozio(idn);
        this.destinazione = "";
        this.IDCorriere = 0;
        this.IDLocker = 0;
        this.IDNegozio = idn;
        generaID();


    }

    private void generaID() throws ClassNotFoundException {
        Random r = new Random();
        boolean check = false;
        while(!check){
            this.IDOrdine = Math.abs(r.nextInt());
            if(ListaOrdini.getInstance().getOrdini().stream().noneMatch(x -> x.getIDOrdine() == this.IDOrdine)) check = true;
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
        ListaOrdini.getInstance().setCorriereToOrdine(IDCorriere,this.IDOrdine);
    }

    @Override
    public void setDestinazione(String destinazione){
        this.destinazione = destinazione;
    }

    @Override
    public List<Prodotto> getListaProdotti() {
        return this.listaProdotti;
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
        ListaOrdini.getInstance().setIDLockerToOrdine(IDLocker, this.IDOrdine);

    }

    public void updateCounterProdotti(int idOrdine) {
        this.listaProdotti = ListaOrdini.getInstance().getOrdine(idOrdine).getListaProdotti();
        //TODO aggiustare questo metodo che aumenta il counter di tutti i prodotti ma dovrebbe aumentarne solo di uno.
           listaProdotti.forEach(x-> {
               ListaOrdini.getInstance().updateCounterProdotto(x.getIDprodotto(),x.getCounterVendita());
           });
    }

    @Override
    public int getIDNegozio() {
        return this.IDNegozio;
    }




}
