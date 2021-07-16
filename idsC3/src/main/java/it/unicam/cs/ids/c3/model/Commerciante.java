package it.unicam.cs.ids.c3.model;

public class Commerciante implements CommercianteInterface{





    @Override
    public void addPromozioni(Negozio negozio, Promozioni p) {

    }

    @Override
    public void eliminaPromozione(int IDNegozio, Promozioni promozioni) {

    }

    @Override
    public void modificaStatoPromozione(int IDNegozio, Promozioni promozione, boolean nuovoStato) {

    }

    @Override
    public void modificaScontoPromozione(int IDNegozio, Promozioni promozioni, double nuovoSconto) {

    }

    @Override
    public void creaOrdine() {

    }

    @Override
    public Corriere selezionaCorriere(ListaCorrieri listaCorrieri) {
        return null;
    }

    @Override
    public String selezionaDestinazione(Cliente cliente) {
        return null;
    }

    @Override
    public void aggiornaStatoOrdine(Ordine ordine) {

    }
}
