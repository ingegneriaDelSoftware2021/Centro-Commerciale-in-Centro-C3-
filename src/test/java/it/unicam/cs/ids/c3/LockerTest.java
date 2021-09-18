package it.unicam.cs.ids.c3;

import it.unicam.cs.ids.c3.model.database.DBLocale;
import it.unicam.cs.ids.c3.model.Lockers.ListaLockers;
import it.unicam.cs.ids.c3.model.Lockers.LockerInterface;
import it.unicam.cs.ids.c3.model.Ordini.ListaOrdini;
import it.unicam.cs.ids.c3.model.Ordini.OrdineInterface;
import it.unicam.cs.ids.c3.model.Ordini.StatoOrdine;
import org.junit.Test;

import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class LockerTest {

    @Test
    public void testGetOrdineFromArmadietto(){
        LockerInterface l = ListaLockers.getInstance().getLockers().stream().filter(x->x.getID()==2).findFirst().orElse(null);
        try {
            DBLocale.getInstance().aggiornaStatoOrdine(5,StatoOrdine.DARITIRARE);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        OrdineInterface o = ListaOrdini.getInstance().getOrdine(5);
        Objects.requireNonNull(l).aggiungiOrdine(2,o);
        int o1 = Objects.requireNonNull(l).cercaOrdine(5);

        assertEquals(2,o1);
    }

    @Test
    public void testConsegnaOrdineACliente(){
        LockerInterface l = ListaLockers.getInstance().getLockers().stream().filter(x->x.getID()==2).findFirst().orElse(null);
        LockerInterface locker = ListaLockers.getInstance().getLockers().stream().filter(x->x.getID()==2).findFirst().orElse(null);
        int idArmadietto = Objects.requireNonNull(locker).cercaOrdine(5);
        assertEquals(2,idArmadietto);
        locker.aggiornaStatoOrdine(5);
        assertEquals(0,locker.cercaOrdine(5));
        try {
            OrdineInterface ordine = DBLocale.getInstance().getAllOrdini(5).get(0);
            assertEquals(StatoOrdine.RITIRATO,ordine.getStatoOrdine());
            DBLocale.getInstance().aggiornaStatoOrdine(5,StatoOrdine.DARITIRARE);
            locker.aggiungiOrdine(2,ordine);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
