package it.unicam.cs.ids.c3.model;

import java.util.ArrayList;
import java.util.List;

public class Negozio {

    private final int IDNegozio;

    private final Commerciante propietario;

    private final String nomeNegozio;

    private List<Promozioni> listaPromozioni;

    public Negozio(int idNegozio, Commerciante propietario, String nomeNegozio) {
        IDNegozio = idNegozio;
        this.propietario = propietario;
        this.nomeNegozio = nomeNegozio;
        this.listaPromozioni = new ArrayList<>();
    }

    public int getIDNegozio() {
        return IDNegozio;
    }

    public Commerciante getPropietario() {
        return propietario;
    }

    public String getNomeNegozio() {
        return nomeNegozio;
    }

    public void addPromozione(Promozioni p){
        this.listaPromozioni.add(p);
    }

    public void rimuoviPromozione(int IDPromozione){
        listaPromozioni.removeIf(p -> p.getIDpromozione() == IDPromozione);
    }


    public void aggiornaStatoPromozioni(int IDPromozione, boolean stato){
        for(Promozioni n : listaPromozioni){
            if(n.getIDpromozione()==IDPromozione){
                n.setStato(stato);
            }
        }
    }

    public void aggiornaScontoPromozioni(int IDPromozione, double sconto){

        listaPromozioni.stream().filter(x -> x.getIDpromozione()==IDPromozione).forEach(x -> x.setSconto(sconto));
        /*for(Promozioni n : listaPromozioni){
            if(n.getIDpromozione()==IDPromozione){
                n.setSconto(sconto);
            }
        }*/
    }







}
