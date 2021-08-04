package it.unicam.cs.ids.c3.model.Lockers;

import java.util.ArrayList;
import java.util.List;

public  class ListaLockers {
    private final List<Locker> lockers;

    public ListaLockers(){
        lockers = new ArrayList<>();
    }


    public void addLocker(Locker locker){
        if(locker == null) return;
        lockers.add(locker);
    }

    public void removeLocker(int IDLocker){
        this.lockers.removeIf(l -> l.getID() == IDLocker);
    }

    public List<Locker> getLockers(){
        return lockers;
    }

}
