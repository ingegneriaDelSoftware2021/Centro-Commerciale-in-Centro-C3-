package it.unicam.cs.ids.c3.model.Corriere;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ListaCorrieri {

    private Set<Corriere> corrieri;

    public ListaCorrieri(){
        corrieri = new HashSet<>();
    }

    public Set<Corriere> getCorrieri(){
        return this.corrieri;
    }

    public void addCorriere(Corriere corriere){
        if(corriere==null) return;
        this.corrieri.add(corriere);
    }

    public void removeCorriere(int IDCorriere){
        if(IDCorriere<0) return;
        this.corrieri.removeIf(x -> x.getIDCorriere()==IDCorriere);
    }

    public List<Corriere> getCorrieriDisponibili(){
        return this.corrieri.parallelStream().filter(Corriere::getDisponibilita).collect(Collectors.toList());
    }
}
