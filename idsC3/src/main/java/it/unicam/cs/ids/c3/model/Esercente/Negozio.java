package it.unicam.cs.ids.c3.model.Esercente;

import it.unicam.cs.ids.c3.model.DBLocale;

import java.util.ArrayList;
import java.util.List;

/**
 * Questa classe implementa un negozio di default.
 * @author Davide Menghini, Francesco Allevi.
 */
public class Negozio {

    private final int IDNegozio;

    private final Commerciante propietario;

    private final String nomeNegozio;



    private final List<Promozioni> listaPromozioni;

    /**
     * Questo &egrave; un costruttore di default.
     * @param idNegozio id del negozio
     * @param IDPropietario id del propietario, che corrisponde al commerciante.
     * @param nomeNegozio nome del negozio.
     */
    public Negozio(int idNegozio, int IDPropietario, String nomeNegozio) {
        IDNegozio = idNegozio;
        this.propietario = null;
        this.nomeNegozio = nomeNegozio;
        this.listaPromozioni = new ArrayList<>();
    }

    /**
     * Questo metodo restituisce l'id del negozio.
     * @return id del negozio.
     */
    public int getIDNegozio() {
        return IDNegozio;
    }

    /**
     * Questo metodo restituisce il commerciante che possiede questo negozio.
     * @return propietario del negozio.
     */
    public Commerciante getPropietario() {
        return propietario;
    }

    /**
     * Questo metodo restituisce il nome del negozio.
     * @return nome del negozio.
     */
    public String getNomeNegozio() {
        return nomeNegozio;
    }

    /**
     * Questo metodo serve per aggiungere una promozione.
     * @param p promozione da aggiungere.
     */
    public void addPromozione(Promozioni p){
        try {
            DBLocale.getInstance().addPromozioneToProdotto(p.getSconto(),p.isStato(),p.getIDpromozione());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        this.listaPromozioni.add(p);
    }

    /**
     * Questo metodo serve per rimuovere una promozione.
     * @param IDPromozione id della promozione da rimuovere.
     */
    public void rimuoviPromozione(int IDPromozione){
        listaPromozioni.removeIf(p -> p.getIDpromozione() == IDPromozione);
    }


    /**
     * Questo metodo modifica lo stato di un promozione, ovvero se la promozione in questo momento &egrave;
     * attiva o no.
     * @param IDPromozione id della promozione.
     * @param stato nuovo stato della promozione.
     */
    public void aggiornaStatoPromozioni(int IDPromozione, boolean stato){
        for(Promozioni n : listaPromozioni){
            if(n.getIDpromozione()==IDPromozione){
                n.setStato(stato);
            }
        }
    }

    /**
     * Questo metodo aggiorna lo sconto, in percentuale, di una promozione.
     * @param IDPromozione id della promozione.
     * @param sconto nuovo sconto in percentuale.
     */
    public void aggiornaScontoPromozioni(int IDPromozione, int sconto){

        listaPromozioni.stream().filter(x -> x.getIDpromozione()==IDPromozione).forEach(x -> x.setSconto(sconto));
        /*for(Promozioni n : listaPromozioni){
            if(n.getIDpromozione()==IDPromozione){
                n.setSconto(sconto);
            }
        }*/
    }

    public List<Promozioni> getListaPromozioni() {
        return listaPromozioni;
    }







}
