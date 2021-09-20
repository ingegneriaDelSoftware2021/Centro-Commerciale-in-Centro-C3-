package it.unicam.cs.ids.c3.model.Esercente;


import it.unicam.cs.ids.c3.model.Corriere.CorriereInterface;
import it.unicam.cs.ids.c3.model.Corriere.ListaCorrieri;

import it.unicam.cs.ids.c3.model.Ordini.ListaOrdini;
import it.unicam.cs.ids.c3.model.Ordini.Ordine;
import it.unicam.cs.ids.c3.model.Utente.Utente;
import java.util.*;

/**
 * Questa classe &egrave; un'implementazione di default del commerciante.
 * @author Davide Menghini, Francesco Allevi.
 */
public class Commerciante extends Utente implements CommercianteInterface {



    private final List<Negozio> negozi;


    private final int IDCommerciante;

    private float portafoglio;


    /**
     * Questo &egrave; un costruttore di default che crea un commerciante.
     * @param IDCommerciante id del commerciante.
     */
    public Commerciante(int IDCommerciante, String nome, String user){
        super();
        setNome(nome);
        setUtente(user);
        this.portafoglio = ListaCommercianti.getInstance().getPortafoglioFromID(IDCommerciante);
        this.IDCommerciante = IDCommerciante;
        negozi = ListaNegozi.getInstance().getNegozi(IDCommerciante);

    }



    @Override
    public void addPromozioni(int IDNegozio, int IDProdotto, int sconto, boolean stato) {
        if(IDNegozio<=0 ||IDProdotto<=0 || sconto<=0) throw new IllegalArgumentException("gli ID passati o lo sconto sono piu' piccoli o uguali a 0");
        Negozio n = ListaNegozi.getInstance().getNegozio(IDNegozio);
        try {
            int id = generaID();
            Objects.requireNonNull(n).addPromozione(new Promozioni(sconto,id,ListaCommercianti.getInstance().getProdottoFromPromozione(id),stato),IDProdotto);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalStateException e){
            throw new IllegalStateException();
        }
    }




    private int generaID() throws ClassNotFoundException {
            Random r = new Random();
            int rit;
            while(true){
                boolean app = false;
                rit = Math.abs(r.nextInt());
                List<Promozioni> l = ListaCommercianti.getInstance().getAllPromozioniFromNegozio();
                for(Promozioni p : l){
                    if(p.getIDpromozione() == rit)app=true;
                }
                if(!app)break;
            }
            return rit;
    }

    @Override
    public void eliminaPromozione(int IDNegozio,int IDPromozione, int IDProdotto) {
        Negozio n = ListaNegozi.getInstance().
                getNegozio(IDNegozio);
        Objects.requireNonNull(n).rimuoviPromozione(IDPromozione,IDProdotto);

    }

    @Override
    public void modificaStatoPromozione(int IDNegozio, int  IDPromozione, boolean nuovoStato) {
        Negozio n = ListaNegozi.getInstance().getNegozio(IDNegozio);
        Objects.requireNonNull(n).aggiornaStatoPromozioni(IDPromozione,nuovoStato);
    }

    @Override
    public void modificaScontoPromozione(int IDNegozio, int IDPromozione, int nuovoSconto) {
        Negozio n = ListaNegozi.getInstance().getNegozio(IDNegozio);
        Objects.requireNonNull(n).aggiornaScontoPromozioni(IDPromozione,nuovoSconto);
    }

    @Override
    public void creaOrdine(int IDcliente, List<Prodotto> prodotti) {
        try {
            ListaOrdini.getInstance().addOrdine(new Ordine(prodotti, IDcliente));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    @Override
    public List<CorriereInterface> getCorrieriDisponibili() {
        return ListaCorrieri.getInstance().getCorrieriDisponibili();
    }

    @Override
    public void selezionaDestinazioneCasa(String indirizzo, int IDOrdine) {
        ListaOrdini.getInstance().setIndirizzoToOrdine(IDOrdine,indirizzo);
    }

    @Override
    public void selezionaDestinazioneLocker(int IDLocker, int IDOrdine) {
        ListaCommercianti.getInstance().setIDLockerToOrdine(IDLocker,IDOrdine);
    }






    @Override
    public void selezionaCorriere(int IDCorriere, int IDOrdine) {
        ListaOrdini.getInstance().setCorriereToOrdine(IDCorriere,IDOrdine);
        ListaCommercianti.getInstance().aggiornaStatoOrdine(IDOrdine);
    }

    @Override
    public int getIDCommerciante() {
        return this.IDCommerciante;
    }

    @Override
    public float getSoldiPortafoglio() {
        return this.portafoglio;
        //return ListaCommercianti.getInstance().getPortafoglioFromID(this.IDCommerciante);
    }

    @Override
    public List<Negozio> getListaNegozi() {
        return this.negozi;

    }



    @Override
    public boolean riceviPagamento(int idCliente, float importo, List<Prodotto> prodotti) {
        if(importo<0)return false;
        ListaCommercianti.getInstance().aggiungiDenaro(this.IDCommerciante, importo);
        this.portafoglio+=importo;
        creaOrdine(idCliente, prodotti);
        return true;
    }

    @Override
    public List<Promozioni> getAllPromozioni(int idNegozio) {
        return ListaCommercianti.getInstance().getAllPromozioniFromNegozio(idNegozio);
    }

    @Override
    public Map<Prodotto, Integer> getProdottiPVenduti(int IDNegozio) {
        Negozio n =  ListaNegozi.getInstance().getNegozio(IDNegozio);
        return n.getProdottiVenduti();
    }

    @Override
    public String getNome() {
        return getNomeCognome();
    }

    @Override
    public String getUser() {
        return getUtente();
    }


}
