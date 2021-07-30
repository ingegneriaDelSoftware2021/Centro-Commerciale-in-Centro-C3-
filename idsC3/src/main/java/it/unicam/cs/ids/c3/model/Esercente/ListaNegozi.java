package it.unicam.cs.ids.c3.model.Esercente;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ListaNegozi {

    private final Set<Negozio> negozi;



    public ListaNegozi(){
        this.negozi = new HashSet<>();
    }

    public Set<Negozio> getNegozi(){
        return negozi;
    }

    public Set<Negozio> getNegozi(int IDCommerciante){
        return this.negozi.parallelStream()
                .filter(x -> x.getIDNegozio()==IDCommerciante)
                .collect(Collectors.toSet());
    }


    public void addNegozio(Negozio negozio){
        negozi.add(negozio);
    }

    public void removeNegozio(int IDnegozio){
        for(Negozio n : negozi){
            if(n.getIDNegozio()==IDnegozio){
                negozi.remove(new Negozio(IDnegozio, n.getPropietario(), n.getNomeNegozio()));
            }
        }
    }




}
