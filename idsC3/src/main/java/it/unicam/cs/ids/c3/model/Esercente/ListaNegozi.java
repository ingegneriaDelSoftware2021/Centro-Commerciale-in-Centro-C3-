package it.unicam.cs.ids.c3.model.Esercente;

import it.unicam.cs.ids.c3.model.DBLocale;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Questa classe serve per gestire la lista di negozi presenti nel database.
 * @author Davide Menghini, Francesco Allevi.
 */
public class ListaNegozi {

    private List<Negozio> negozi;
    private static ListaNegozi instance;
    private DBLocale db;

    /**
     * Questo &egrave; un costruttore di default.
     */
    public ListaNegozi(){
        try {
            db = DBLocale.getInstance();
            this.negozi = db.getAllNegozi();
        } catch (ClassNotFoundException e) {
            this.negozi = new ArrayList<>();
            e.printStackTrace();
        }
    }


    public static ListaNegozi getInstance(){
        if(instance==null){
            instance = new ListaNegozi();
        }
        return instance;
    }


    /**
     * Questo metodo restituisce la lista di tutti i negozi presenti nel database.
     * @return la lista dei negozi.
     */
    public List<Negozio> getNegozi(){
        return negozi;
    }

    /**
     * Questo metodo serve per avere la lista dei negozi di un commerciante.
     * @param IDCommerciante id del commerciante.
     * @return una lista di negozi dello stesso commerciante.
     */
    public List<Negozio> getNegozi(int IDCommerciante){
        return this.negozi.parallelStream()
                .filter(x -> x.getPropietario().getIDCommerciante()==IDCommerciante)
                .collect(Collectors.toList());
    }


    /**
     * Questo metodo serve per aggiungere un negozio al database.
     * @param negozio negozio da aggiungere
     */
    public void addNegozio(Negozio negozio){
        negozi.add(negozio);
    }

    /**
     * Questo metodo serve per rimuovere un negozio al database.
     * @param IDnegozio negozio da rimuovere
     */
    public void removeNegozio(int IDnegozio){
        for(Negozio n : negozi){
            if(n.getIDNegozio()==IDnegozio){
                negozi.remove(new Negozio(IDnegozio, n.getPropietario().getIDCommerciante(), n.getNomeNegozio()));
            }
        }
    }




}
