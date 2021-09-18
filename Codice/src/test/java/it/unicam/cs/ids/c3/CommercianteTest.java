package it.unicam.cs.ids.c3;
import it.unicam.cs.ids.c3.model.database.DBLocale;
import it.unicam.cs.ids.c3.model.Esercente.*;
import it.unicam.cs.ids.c3.model.Ordini.ListaOrdini;
import it.unicam.cs.ids.c3.model.Ordini.OrdineInterface;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Objects;


import static org.junit.Assert.*;

public class CommercianteTest {


    @Test
    public void addPromozioneTest(){
        try {
            CommercianteInterface c = getCommerciante(3);
            long oldPromozioni = DBLocale.getInstance().getAllPromozioni().stream().filter(x->x.getSconto()==19).count();
            Objects.requireNonNull(c).addPromozioni(7,12,19,true);
            assertEquals(oldPromozioni+1, DBLocale.getInstance().getAllPromozioni().stream().filter(x -> x.getSconto() == 19).count());
            Negozio n = c.getListaNegozi().stream().filter(x->x.getIDNegozio()==7).findFirst().orElse(null);
            int promozioni = 0;
            for( Prodotto p : n.getListaProdotti()){
                if(p.getIDprodotto()==12)promozioni = p.getPromozione();
            }
            c.eliminaPromozione(7,promozioni,12);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
/*
@Test
public void sadfsdfav(){
    CommercianteInterface c = getCommerciante(4);
    Promozioni promozioni = null;
    c.addPromozioni(6,13,12,false);
}*/


    @Test
    public void deletePromozioneTest(){
        try{
            CommercianteInterface c = getCommerciante(1);
            Objects.requireNonNull(c).eliminaPromozione(3,1,1);
            assertEquals(0,DBLocale.getInstance().getAllPromozioni().stream().filter(x->x.getIDpromozione()==1).count());
        }catch (ClassNotFoundException throwables){
            throwables.printStackTrace();
        }
    }



    @Test
    public void updatePromozioneTest(){
        try{
            CommercianteInterface c = getCommerciante(1);
            Objects.requireNonNull(c).modificaStatoPromozione(2,3,false);
            assertFalse(Objects.requireNonNull(DBLocale.getInstance().getAllPromozioni().stream().filter(x -> x.getIDpromozione() == 3).findFirst().orElse(null)).getStato());
            Objects.requireNonNull(c).modificaStatoPromozione(6,7,true);
            Objects.requireNonNull(c).modificaScontoPromozione(6,7,33);
            assertEquals(1,DBLocale.getInstance().getAllPromozioni().stream().filter(x-> x.getSconto()==33).count());
        }catch (ClassNotFoundException throwables){
            throwables.printStackTrace();
        }
    }


    @Test
    public void testSelezionaIdLocker(){
        CommercianteInterface c = getCommerciante(1);
        Objects.requireNonNull(c).selezionaDestinazioneLocker(4,3);
        assertEquals(4, ListaOrdini.getInstance().getOrdine(3).getIDLocker());

    }



    @Test
    public void testSelezionaIndirizzoOrdine(){
            CommercianteInterface c = getCommerciante(2);
            Objects.requireNonNull(c).selezionaDestinazioneCasa("via madonnina 33",4);
            assertNotNull(ListaOrdini.getInstance().getOrdine(4));
            assertEquals("via madonnina 33", Objects.requireNonNull(ListaOrdini.getInstance().getOrdini().stream().filter(x -> x.getIDOrdine() == 4).findFirst().orElse(null)).getDestinazione());

    }


    @Test
    public void testSelezionaCorriere() throws ClassNotFoundException {
        CommercianteInterface c = getCommerciante(1);
        Objects.requireNonNull(c).selezionaCorriere(7,3);
        assertEquals("via madonna delle carceri",DBLocale.getInstance().getAllOrdini(3).get(0).getDestinazione());
        assertEquals(7, ListaOrdini.getInstance().getOrdine(3).getIDCorriere());
    }


    @Test
    public void testGetStatProdottiVenduti(){
        Prodotto p = new Prodotto(1,"astuccio",20,"libreria",7.8f,"e' un'astuccio che contien cose",1,1,648326081);
        try{
            CommercianteInterface c = getCommerciante(2);
            Map<Prodotto,Integer> map = Objects.requireNonNull(c).getProdottiPVenduti(1);
            assertTrue(map.containsKey(p));
            OrdineInterface n = DBLocale.getInstance().getAllOrdini(4).stream().findFirst().orElse(null);
            Map<Prodotto,Integer> m = c.getProdottiPVenduti(1);
            p = new Prodotto(1,"astuccio",20,"libreria",7.8f,"e' un'astuccio che contien cose",1,1,648326081);
            int oldValue = m.get(p);

            Objects.requireNonNull(n).updateCounterProdotti(4);
            c = getCommerciante(2);
            Negozio x = ListaNegozi.getInstance().getNegozio(1);
            map = x.getProdottiVenduti();
            assertEquals(oldValue+1, (int) map.get(p));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testGetStatClienti(){
        CommercianteInterface c = getCommerciante(1);
        ListaNegozi l = ListaNegozi.getInstance();
        Negozio n = l.getNegozio(1);
        assertNotNull(n);

        int[] expected = checkStatCliente();
        assertEquals(expected[0],n.getStatClienti()[0]);
        assertEquals(expected[1],n.getStatClienti()[1]);
    }

    private int[] checkStatCliente() {
        int [] rit = {0,0};
        try {
            List<OrdineInterface> listaOrdine = DBLocale.getInstance().getAllOrdiniFromNegozio(1);
            for(OrdineInterface p : listaOrdine){
                if(p.getIDLocker()!=0)rit[0]++;
                else if(p.getDestinazione()!=null && !p.getDestinazione().equals(""))rit[1]++;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return rit;
    }


    private CommercianteInterface getCommerciante(int IDCommerciante){
        return ListaCommercianti.getInstance().getCommerciante(IDCommerciante);
    }





}
