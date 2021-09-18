package it.unicam.cs.ids.c3.model.Corriere;

import it.unicam.cs.ids.c3.model.Ordini.ListaOrdini;
import it.unicam.cs.ids.c3.model.Ordini.Ordine;
import it.unicam.cs.ids.c3.model.Ordini.OrdineInterface;
import it.unicam.cs.ids.c3.model.Ordini.StatoOrdine;
import it.unicam.cs.ids.c3.model.Utente.Utente;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Questa classe &egrave; un implementazione di default di un corriere.
 * @author Davide Menghini, Francesco Allevi.
 */
public class Corriere extends Utente implements CorriereInterface {

    private boolean disponibilita;

    private final List<Ordine> ordini;

    private final int IDCorriere;

    /**
     * Questo &egrave; un costruttore di default.
     * @param IDCorriere id del corriere
     * @param nome nome del corriere.
     * @param indirizzo indirizzo della casa del corriere.
     */
    public Corriere(int IDCorriere, String nome, String indirizzo, String user, int disponibilita){
        super();
        super.setNome(nome);
        super.setUtente(user);
        this.IDCorriere = IDCorriere;
        this.disponibilita = disponibilita != 0;
        ordini = new ArrayList<>();
    }


    /**
     * Questo metodo restituisce l'id del corriere.
     * @return id del corriere.
     */
    public int getIDCorriere() {
        return IDCorriere;
    }

    @Override
    public List<OrdineInterface> getListaOrdine() {
        return ListaOrdini.getInstance().getOrdini().stream().filter(x->x.getIDCorriere()==this.IDCorriere).collect(Collectors.toList());
    }

    @Override
    public OrdineInterface getOrdine(int IDOrdine) {
        return ListaOrdini.getInstance().getOrdine(IDOrdine);
    }


    @Override
    public boolean getDisponibilita() {
        return this.disponibilita;
    }

    @Override
    public void setDisponibilita(boolean nuovaDisponibilita) {
        this.disponibilita = nuovaDisponibilita;
        ListaCorrieri.getInstance().cambiaDisponibilita(this.IDCorriere,nuovaDisponibilita);
    }

    @Override
    public String getNome() {
        return super.getNomeCognome();
    }

    @Override
    public String getUser() {
        return getUtente();
    }

    @Override
    public void setOrdineToINTRANSITO(int idOrdine) {
        ListaOrdini.getInstance().updateStatoOrdine(idOrdine,StatoOrdine.INTRANSITO);
    }

    @Override
    public void setOrdineToCONSEGNATO(int idOrdine) {
        OrdineInterface o = ListaOrdini.getInstance().getOrdine(idOrdine);
        if(o.getDestinazione()!=null && !o.getDestinazione().equals(""))ListaOrdini.getInstance().updateStatoOrdine(idOrdine,StatoOrdine.RITIRATO);
        else ListaOrdini.getInstance().updateStatoOrdine(idOrdine,StatoOrdine.DARITIRARE);
    }
}
