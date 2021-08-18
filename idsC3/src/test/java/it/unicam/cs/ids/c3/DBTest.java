package it.unicam.cs.ids.c3;

import it.unicam.cs.ids.c3.model.Clienti.Carrello;
import it.unicam.cs.ids.c3.model.Clienti.Cliente;
import it.unicam.cs.ids.c3.model.Clienti.ClienteInterface;
import it.unicam.cs.ids.c3.model.DBLocale;
import it.unicam.cs.ids.c3.model.Esercente.Prodotto;
import it.unicam.cs.ids.c3.model.Ordini.Ordine;
import it.unicam.cs.ids.c3.model.Ordini.OrdineInterface;
import it.unicam.cs.ids.c3.model.Ordini.StatoOrdine;
import org.junit.Test;

import java.util.List;
import java.util.Objects;

import static it.unicam.cs.ids.c3.model.Ordini.StatoOrdine.DARITIRARE;
import static it.unicam.cs.ids.c3.model.Ordini.StatoOrdine.INPREPARAZIONE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DBTest {



    @Test
    public void getAllOrdini() throws ClassNotFoundException {
        DBLocale db = DBLocale.getInstance();
        List<OrdineInterface> l = db.getAllOrdini();
        checkListOrdini(l);
    }

    @Test
    public void checkIDCorriereFromOrdine() throws ClassNotFoundException {
        DBLocale db = DBLocale.getInstance();
        List<OrdineInterface> l = db.getAllOrdini();
        assertEquals(1,l.stream().filter(x->x.getIDCorriere()==1).count());
        IDcorriereFromOrdine(l);
    }



    @Test
    public void checkIDCommFromOrdine() throws ClassNotFoundException {
        DBLocale db1 = DBLocale.getInstance();
        List<OrdineInterface> list = db1.getAllOrdini();
        Objects.requireNonNull(list.stream().filter(x -> x.getIDOrdine() == 2).findFirst().orElse(null)).setIDCorriere(1);
        DBLocale db2 =  DBLocale.getInstance();
        List<OrdineInterface> list1 = db2.getAllOrdini();
        checkIDCorriere(list1);
    }




    @Test
    public void setIDLockerToOrdineTest() throws ClassNotFoundException {
        DBLocale db = DBLocale.getInstance();
        List<OrdineInterface> list = db.getAllOrdini();
        db.setIDLockerToOrdine(2,2);

        checkIDLocker(db.getAllOrdini());
    }


    @Test
    public void setIndirizzoToOrdineTest() throws ClassNotFoundException {
        DBLocale db = DBLocale.getInstance();
        List<OrdineInterface> list = db.getAllOrdini();
        db.setIndirizzoToOrdine("via madonna delle carceri",2);
        checkIndirizzoOrdine(db.getAllOrdini());
    }



    @Test
    public void setNewStatoTest() throws ClassNotFoundException {
        DBLocale db = DBLocale.getInstance();
        List<OrdineInterface> list = db.getAllOrdini();
        db.setNewStatoOrdine(2, StatoOrdine.RITIRATO);
        assertEquals(1,db.getAllOrdini().stream().filter(z -> z.getStatoOrdine()==StatoOrdine.RITIRATO)
                .count());
        db.setNewStatoOrdine(2, DARITIRARE);
    }


    @Test
    public void addOrdineTest() throws ClassNotFoundException {
        DBLocale db = DBLocale.getInstance();
        Carrello c = new Carrello(1);
        db.addOrdine(new Ordine(c));
        assertEquals(2, db.getAllOrdini().size());
    }




    @Test
    public void getProdottiFromCarrelloTest() throws ClassNotFoundException {
        DBLocale db = DBLocale.getInstance();
        List<Prodotto> list = db.getProdottiFromCarrello(1);
        assertEquals(2,list.size());
    }

    @Test
    public void cercaProdottoFromNegozioTest(){
        ClienteInterface c = new Cliente(1,"mimmo","piazza", 1);
        List<Prodotto> actual = c.cercaProdotto("astuccio", "elettrodomestici");
        checkProdottoNegozio(actual);
    }

    private void checkProdottoNegozio(List<Prodotto> actual) {
        assertEquals(1,actual.size());
        for(Prodotto c : actual){
            assertEquals("astuccio",c.getNome());
        }
    }


    private void checkIndirizzoOrdine(List<OrdineInterface> allOrdini) {
        boolean check = false;
        for(OrdineInterface o : allOrdini){
            if(o.getDestinazione().equals("via madonna delle carceri"))check = true;
        }
        assertTrue(check);
    }

    private void checkIDLocker(List<OrdineInterface> allOrdini) {
        int IDLocker = -1;
        for(OrdineInterface o : allOrdini){
            if(o.getIDLocker()==2)IDLocker = 1;
        }
        assertEquals(1,IDLocker);
    }

    private void checkIDCorriere(List<OrdineInterface> list) {
        int counter = 0;
        for(OrdineInterface o1 : list){
            if(o1.getIDCorriere()==1)counter++;
        }
        assertEquals(1,counter);
    }


    public void setIDCorriereToOrdine() throws ClassNotFoundException {
        DBLocale db = DBLocale.getInstance();
        db.setCorriereToOrdine(1,2);
        List<OrdineInterface> list = db.getAllOrdini();
        IDcorriereFromOrdine(list);
    }

    private void IDCommFromOrdine(List<OrdineInterface> list) throws ClassNotFoundException {
        int IDCommCount = 0;
        List<OrdineInterface> r = List.of(new Ordine(2,2,DARITIRARE));
        for(OrdineInterface o1 : list){
            for(OrdineInterface o2 : r){
                System.out.println("IDO1:"+o1.getIDOrdine()+" IDO2:"+o2.getIDOrdine());
                System.out.println("IDComm1:"+o1.getIDCommerciante()+" IDComm2:"+o2.getIDCommerciante());
                if(o1.getIDOrdine()==o2.getIDOrdine() && o1.getIDCommerciante()==o2.getIDCommerciante())IDCommCount++;
            }
        }
        assertEquals(1,IDCommCount);
    }


    /*
    private List<Ordine> ordini(){
        return List.of(new Ordine(0, StatoOrdine.INPREPARAZIONE),
                new Ordine(1,StatoOrdine.INPREPARAZIONE),
                new Ordine(2,StatoOrdine.INPREPARAZIONE));
    }*/

    private void checkListOrdini(List<OrdineInterface> l) throws ClassNotFoundException {
        int idOrdinecount = 0;
        List<OrdineInterface> r = List.of(new Ordine(1,1,INPREPARAZIONE),
                new Ordine(2,2,DARITIRARE));
        System.out.println(l.size()+" size");
        for(OrdineInterface o1 : l){
            for(OrdineInterface o2 : r)if(o1.getIDOrdine()==o2.getIDOrdine()) idOrdinecount++;
        }
        assertEquals(1, idOrdinecount);
    }


    private void IDcorriereFromOrdine(List<OrdineInterface> l) throws ClassNotFoundException {
        int _IDCorriereCounter = 0;
        List<OrdineInterface> r = List.of(new Ordine(2,2,DARITIRARE));
        for(OrdineInterface o1 : l)for(OrdineInterface o2 : r)if(o1.getIDCorriere()==o2.getIDCorriere())_IDCorriereCounter++;
        assertEquals(1,_IDCorriereCounter);
    }














}
