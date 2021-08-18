package it.unicam.cs.ids.c3.model.Esercente;


import it.unicam.cs.ids.c3.model.Clienti.Carrello;
import it.unicam.cs.ids.c3.model.Corriere.Corriere;
import it.unicam.cs.ids.c3.model.DBLocale;
import it.unicam.cs.ids.c3.model.Lockers.ListaLockers;
import it.unicam.cs.ids.c3.model.Utente.Utente;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Questa classe &egrave; un'implementazione di default del commerciante.
 * @author Davide Menghini, Francesco Allevi.
 */
public class Commerciante extends Utente implements CommercianteInterface {



    private final ListaNegozi negozi;


    private final int IDCommerciante;

    private float portafoglio;


    /**
     * Questo &egrave; un costruttore di default che crea un commerciante.
     * @param IDCommerciante id del commerciante.
     */
    public Commerciante(int IDCommerciante){
        this.portafoglio = 0;
        this.IDCommerciante = IDCommerciante;
        negozi = new ListaNegozi();

    }



    @Override
    public void addPromozioni(int IDNegozio, int IDProdotto, int sconto, boolean stato) {
        //TODO
        Negozio n = ListaNegozi.getInstance().getNegozi().stream().
                filter(x->x.getIDNegozio()==IDNegozio).
                findFirst().
                orElse(null);

        try {
            int id = generaID();
            Objects.requireNonNull(n).addPromozione(new Promozioni(sconto,id,DBLocale.getInstance().getProdottoFromPromozione(id)));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }




    private int generaID() throws ClassNotFoundException {
            Random r = new Random();
            int rit = 0;
            while(true){
                boolean app = false;
                rit = Math.abs(r.nextInt());
                List<Promozioni> l = DBLocale.getInstance().getAllPromozioni();
                for(Promozioni p : l){

                    if(p.getIDpromozione() == rit)app=true;
                }
                if(!app)break;
            }
            return rit;
    }

    @Override
    public void eliminaPromozione(int IDNegozio,int IDPromozione) {
        //TODO
        /*for(Negozio n : negozi.getNegozi()){
            if(n.getIDNegozio()==IDNegozio) n.rimuoviPromozione(IDPromozione);
        }*/
    }

    @Override
    public void modificaStatoPromozione(int IDNegozio, int  IDPromozione, boolean nuovoStato) {
        //TODO
        /*for(Negozio n : negozi.getNegozi()){
            if(n.getIDNegozio()==IDNegozio) n.aggiornaStatoPromozioni(IDPromozione, nuovoStato);
        }*/
    }

    @Override
    public void modificaScontoPromozione(int IDNegozio, int IDPromozione, double nuovoSconto) {
        //TODO
        /*for(Negozio n : negozi.getNegozi()){
            if(n.getIDNegozio()==IDNegozio){
                n.aggiornaScontoPromozioni(IDPromozione, nuovoSconto);
            }
        }*/
    }

    @Override
    public void creaOrdine(int IDcliente) {
        //TODO sistemare listaOrdini


    }

    @Override
    public List<Corriere> getCorrieriDisponibili() {
        //TODO
        return null;
    }

    @Override
    public void selezionaDestinazioneCasa(String indirizzo, int IDOrdine) {
        //TODO
        /*for (Ordine o : this.listaOrdini.getOrdini()) {
            if(o.getIDOrdine()==IDOrdine){
                o.setDestinazione(indirizzo);
            }
        }*/
    }

    @Override
    public void selezionaDestinazioneLocker(String indirizzoLocker, int IDOrdine) {
        //TODO
        /*for(Ordine o : listaOrdini.getOrdini()){
            if(o.getIDOrdine()==IDOrdine){
                o.setDestinazione(indirizzoLocker);
            }
        }*/
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
    public float getSoldiPortafoglio() {
        return this.portafoglio;
    }

    @Override
    public List<Negozio> getListaNegozi() {
        return null;
        //return this.negozi;
    }

    @Override
    public void addNegozio(String indirizzo) {

    }

    @Override
    public boolean riceviPagamento(int idCliente, float importo) {
        if(importo<0) return false;
        this.portafoglio+=importo;
        return true;
    }

    @Override
    public List<Promozioni> getAllPromozioni() {
        return getListaNegozi().stream().filter(x->x.getPropietario().
                getIDCommerciante()==this.IDCommerciante).
                map(Negozio::getListaPromozioni).
                flatMap(Collection::stream).
          collect(Collectors.toList());

    }


}
