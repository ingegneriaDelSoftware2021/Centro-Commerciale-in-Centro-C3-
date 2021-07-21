package it.unicam.cs.ids.c3.model;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Commerciante implements CommercianteInterface{



    private final ListaNegozi negozi;

    private List<Ordine> listaOrdini;

    private int IDCommerciante;

    private double portafoglio;

    private ListaLockers listaLockers;

    public Commerciante(int IDCommerciante){
        this.portafoglio = 0.0;
        this.IDCommerciante = IDCommerciante;
        negozi = new ListaNegozi();
        this.listaOrdini = new ArrayList<>();
        this.listaLockers = new ListaLockers();
    }



    @Override
    public void addPromozioni(int IDNegozio, Promozioni promozione) {
        for(Negozio n : negozi.getNegozi()){
            if(n.getIDNegozio()==IDNegozio) n.addPromozione(promozione);
        }
    }

    @Override
    public void eliminaPromozione(int IDNegozio,int IDPromozione) {
        for(Negozio n : negozi.getNegozi()){
            if(n.getIDNegozio()==IDNegozio) n.rimuoviPromozione(IDPromozione);
        }
    }

    @Override
    public void modificaStatoPromozione(int IDNegozio, int  IDPromozione, boolean nuovoStato) {
        for(Negozio n : negozi.getNegozi()){
            if(n.getIDNegozio()==IDNegozio) n.aggiornaStatoPromozioni(IDPromozione, nuovoStato);
        }
    }

    @Override
    public void modificaScontoPromozione(int IDNegozio, int IDPromozione, double nuovoSconto) {
        for(Negozio n : negozi.getNegozi()){
            if(n.getIDNegozio()==IDNegozio){
                n.aggiornaScontoPromozioni(IDPromozione, nuovoSconto);
            }
        }
    }

    @Override
    public void creaOrdine() {
        //TODO implementare
    }

    @Override
    public List<Corriere> getCorrieriDisponibili(ListaCorrieri listaCorrieri) {
        return new ArrayList<>(listaCorrieri.getCorrieri());
    }

    @Override
    public void selezionaDestinazioneCasa(String indirizzo, int IDOrdine) {
        for (Ordine o : this.listaOrdini) {
            if(o.getIDOrdine()==IDOrdine){
                o.setDestinazione(indirizzo);
            }
        }
    }

    @Override
    public void selezionaDestinazioneLocker(String indirizzoLocker, int IDOrdine) {
        for(Ordine o : listaOrdini){
            if(o.getIDOrdine()==IDOrdine){
                o.setDestinazione(indirizzoLocker);
            }
        }
    }

    @Override
    public ListaLockers getListaLocker() {
        return listaLockers;
    }


    @Override
    public void aggiornaStatoOrdine(Ordine ordine) {
        //TODO implementare
    }

    @Override
    public String selezionaCorriere(int IDCorriere, int IDOrdine) {
        for(Ordine o : this.listaOrdini){
            if(o.getIDOrdine()==IDOrdine) o.setCorriere(new Corriere(IDCorriere));
        }
        return "tutto ok";
    }




}
