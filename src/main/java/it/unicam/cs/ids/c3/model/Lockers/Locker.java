package it.unicam.cs.ids.c3.model.Lockers;


import it.unicam.cs.ids.c3.model.Ordini.ListaOrdini;
import it.unicam.cs.ids.c3.model.Ordini.OrdineInterface;
import it.unicam.cs.ids.c3.model.Ordini.StatoOrdine;
import it.unicam.cs.ids.c3.model.Utente.Utente;
import java.util.ArrayList;
import java.util.List;

/**
 * Questa classe &egrave; un'implementazione di default di un locker.
 * @author Davide Menghini, Francesco Allevi.
 */
public class Locker extends Utente implements LockerInterface{

    private final List<Armadietto> armadietti;

    private final int IDLocker;

    private final String indirizzo;

    /**
     * Questo &egrave; un costruttore di default.
     * @param idLocker id del locker.
     * @param indirizzo indirizzo del locker.
     * @param user nome utente del locker per l'accesso al sistema.
     */
    public Locker(int idLocker, String indirizzo, String user) {
        super();
        this.armadietti = new ArrayList<>();
        this.IDLocker = idLocker;
        this.indirizzo = indirizzo;
        setUtente(user);
    }




    @Override
    public List<Armadietto> visualizzaArmadietti() {
        return ListaLockers.getInstance().getArmadietti(this.IDLocker);
    }

    @Override
    public int cercaOrdine(int IDOrdine) {
        List<Armadietto> list = ListaLockers.getInstance().getArmadietti(this.IDLocker);
        for(Armadietto a : list){
            if(a!=null && a.getOrdine()!=null){
                if(a.getOrdine().getIDOrdine()==IDOrdine) {
                    return a.getIDArmadietto();
                }
            }
        }
        return 0;
    }



    @Override
    public String aggiornaStatoOrdine(int IDOrdine) {
        ListaOrdini.getInstance().updateStatoOrdine(IDOrdine,StatoOrdine.RITIRATO);
        ListaLockers.getInstance().rimuoviOrdineFromArmadietto(IDOrdine);
        if(ListaLockers.getInstance().getArmadietti(this.IDLocker).stream().anyMatch(x->x.getOrdine()!=null && x.getOrdine().getIDOrdine()==IDOrdine))return "qualcosa e' andato storto!";
        else return "ordine consegnato";
    }


    @Override
    public void aggiungiOrdine(int idArmadietto ,OrdineInterface ordine) {
        ListaLockers.getInstance().addOrdine(idArmadietto,this.IDLocker,ordine);
    }


    @Override
    public int getID(){
        return IDLocker;
    }



    public String getIndirizzo(){
        return this.indirizzo;
    }

    @Override
    public String getUser() {
        return super.getUtente();
    }


}
