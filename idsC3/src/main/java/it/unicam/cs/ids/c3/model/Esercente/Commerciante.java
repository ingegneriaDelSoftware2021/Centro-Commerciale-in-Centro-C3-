package it.unicam.cs.ids.c3.model.Esercente;


import it.unicam.cs.ids.c3.model.*;
import it.unicam.cs.ids.c3.model.Clienti.Carrello;
import it.unicam.cs.ids.c3.model.Clienti.Cliente;
import it.unicam.cs.ids.c3.model.Corriere.Corriere;
import it.unicam.cs.ids.c3.model.Corriere.ListaCorrieri;
import it.unicam.cs.ids.c3.model.Lockers.ListaLockers;
import it.unicam.cs.ids.c3.model.Ordini.ListaOrdini;
import it.unicam.cs.ids.c3.model.Ordini.Ordine;
import it.unicam.cs.ids.c3.model.Ordini.StatoOrdine;
import it.unicam.cs.ids.c3.model.Utente.Utente;


import java.util.ArrayList;
import java.util.List;

public class Commerciante extends Utente implements CommercianteInterface {



    private final List<Negozio> negozi;


    private int IDCommerciante;

    private double portafoglio;

    private ListaLockers listaLockers;

    public Commerciante(int IDCommerciante){
        this.portafoglio = 0.0;
        this.IDCommerciante = IDCommerciante;
        negozi = new ArrayList<>();
        this.listaLockers = new ListaLockers();
    }



    @Override
    public void addPromozioni(int IDNegozio, int IDProdotto, int sconto, boolean stato) {
        /*for(Negozio n : negozi.getNegozi()){
            if(n.getIDNegozio()==IDNegozio) n.addPromozione(promozione);
        }*/
    }

    @Override
    public void eliminaPromozione(int IDNegozio,int IDPromozione) {
        /*for(Negozio n : negozi.getNegozi()){
            if(n.getIDNegozio()==IDNegozio) n.rimuoviPromozione(IDPromozione);
        }*/
    }

    @Override
    public void modificaStatoPromozione(int IDNegozio, int  IDPromozione, boolean nuovoStato) {
        /*for(Negozio n : negozi.getNegozi()){
            if(n.getIDNegozio()==IDNegozio) n.aggiornaStatoPromozioni(IDPromozione, nuovoStato);
        }*/
    }

    @Override
    public void modificaScontoPromozione(int IDNegozio, int IDPromozione, double nuovoSconto) {
        /*for(Negozio n : negozi.getNegozi()){
            if(n.getIDNegozio()==IDNegozio){
                n.aggiornaScontoPromozioni(IDPromozione, nuovoSconto);
            }
        }*/
    }

    @Override
    public void creaOrdine(int IDcliente) {
        //TODO sistemare listaOrdini
        /*Carrello c = cliente.getCarrello();
        Ordine o = new Ordine(c);
        listaOrdini.addOrdine(o);*/
    }

    @Override
    public List<Corriere> getCorrieriDisponibili() {
        return null;
    }

    @Override
    public void selezionaDestinazioneCasa(String indirizzo, int IDOrdine) {
        /*for (Ordine o : this.listaOrdini.getOrdini()) {
            if(o.getIDOrdine()==IDOrdine){
                o.setDestinazione(indirizzo);
            }
        }*/
    }

    @Override
    public void selezionaDestinazioneLocker(String indirizzoLocker, int IDOrdine) {
        /*for(Ordine o : listaOrdini.getOrdini()){
            if(o.getIDOrdine()==IDOrdine){
                o.setDestinazione(indirizzoLocker);
            }
        }*/
    }

    @Override
    public ListaLockers getListaLocker() {
        return listaLockers;
    }


    @Override
    public void aggiornaStatoOrdine(int IDOrdine) {
        //ordine.setStatoOrdine(StatoOrdine.INPREPARAZIONE);
        //TODO implementare
    }

    @Override
    public String selezionaCorriere(int IDCorriere, int IDOrdine) {
        /*for(Ordine o : this.listaOrdini.getOrdini()){
            if(o.getIDOrdine()==IDOrdine) o.setCorriere(new Corriere(IDCorriere));
        }*/
        return null;
    }

    @Override
    public int getIDCommerciante() {
        return this.IDCommerciante;
    }

    @Override
    public float getPortafoglio() {
        return this.getPortafoglio();
    }

    @Override
    public List<Negozio> getListaNegozi() {
        return this.negozi;
    }

    @Override
    public void addNegozio(String indirizzo) {

    }

    @Override
    public void riceviPagamento(int idCliente, float importo) {
        if(importo<0) return;
        this.portafoglio+=importo;
    }


}
