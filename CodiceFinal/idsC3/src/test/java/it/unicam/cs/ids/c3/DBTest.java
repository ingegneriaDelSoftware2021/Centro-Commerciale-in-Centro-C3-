package it.unicam.cs.ids.c3;

import it.unicam.cs.ids.c3.model.Clienti.*;
import it.unicam.cs.ids.c3.model.database.DBLocale;
import it.unicam.cs.ids.c3.model.Esercente.Prodotto;
import it.unicam.cs.ids.c3.model.Ordini.ListaOrdini;
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
    public void getAllClienti() throws ClassNotFoundException
    {
        DBLocale db = DBLocale.getInstance();
        List<ClienteInterface> l = db.getAllClienti();
        assertEquals(3,l.size());
    }

    @Test
    public void getAllOrdini() throws ClassNotFoundException {
        DBLocale db = DBLocale.getInstance();
        List<OrdineInterface> l = db.getAllOrdini(0);
        checkListOrdini(l);
    }

    @Test
    public void checkIDCorriereFromOrdine() throws ClassNotFoundException {
        DBLocale db = DBLocale.getInstance();
        List<OrdineInterface> l = db.getAllOrdini(2);
        assertTrue(l.stream().anyMatch(x->x.getIDCorriere()==1 && x.getIDOrdine()==2));
    }



    @Test
    public void checkIDCommFromOrdine() throws ClassNotFoundException {
        DBLocale db1 = DBLocale.getInstance();
        List<OrdineInterface> list = db1.getAllOrdini(0);
        Objects.requireNonNull(list.stream().filter(x -> x.getIDOrdine() == 2).findFirst().orElse(null)).setIDCorriere(1);
        DBLocale db2 =  DBLocale.getInstance();
        List<OrdineInterface> list1 = db2.getAllOrdini(2);
        checkIDCorriere(list1);
    }




    @Test
    public void setIDLockerToOrdineTest() throws ClassNotFoundException {
        DBLocale db = DBLocale.getInstance();
        List<OrdineInterface> list = db.getAllOrdini(0);
        db.setIDLockerToOrdine(2,2);

        checkIDLocker(db.getAllOrdini(0));
    }


    @Test
    public void setIndirizzoToOrdineTest() throws ClassNotFoundException {
        DBLocale db = DBLocale.getInstance();
        List<OrdineInterface> list = db.getAllOrdini(0);
        db.setIndirizzoToOrdine("via madonna delle carceri",3);
        checkIndirizzoOrdine(db.getAllOrdini(3));
    }



    @Test
    public void setNewStatoTest() throws ClassNotFoundException {
        DBLocale db = DBLocale.getInstance();
        List<OrdineInterface> list = db.getAllOrdini(0);
        db.setNewStatoOrdine(2, StatoOrdine.RITIRATO);
        assertEquals(StatoOrdine.RITIRATO, ListaOrdini.getInstance().getOrdine(2).getStatoOrdine());
        db.setNewStatoOrdine(2, DARITIRARE);
    }


    @Test
    public void addOrdineTest() throws ClassNotFoundException {
        DBLocale db = DBLocale.getInstance();
        Carrello c = new Carrello(3,DBLocale.getInstance().getTotaleFromCarrello(3));
        int oldSize = db.getAllOrdini(0).size();
        db.addOrdine(new Ordine(c.getProdotti(),3));
        assertEquals(oldSize+1, db.getAllOrdini(0).size());
    }




    @Test
    public void getProdottiFromCarrelloTest() throws ClassNotFoundException {
        DBLocale db = DBLocale.getInstance();
        int oldListSize = db.getProdottiFromCarrello(1).size();
        Prodotto p = db.getAllProdotti().get(0);
        Carrello c = db.getAllCarrello().stream().filter(x->x.getIDCarrello()==1).findFirst().orElse(null);
        db.addProdottoToCarrello(1,p,(int)c.getQuantitaProdotto(p)+1);
        int oldQuantita = (int) db.getAllCarrello().stream().filter(x->x.getIDCarrello()==1).findFirst().orElse(null).getQuantitaProdotto(p);
        List<Prodotto> list = db.getProdottiFromCarrello(1);
        assertEquals(oldListSize+1,list.size());

        db.removeProdottoToCarrello(1,1,oldQuantita);
    }

    @Test
    public void cercaProdottoFromNegozioTest(){
        ClienteInterface c = new Cliente(2,"alessandro","mimmo", 2,68.3f,"topolino");
        List<Prodotto> actual = c.cercaProdotto("o", 5);
        checkProdottoNegozio(actual);
    }

    private void checkProdottoNegozio(List<Prodotto> actual) {
        assertEquals(2,actual.size());
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
        List<OrdineInterface> list = db.getAllOrdini(0);
        IDcorriereFromOrdine(list);
    }

    private void IDCommFromOrdine(List<OrdineInterface> list) throws ClassNotFoundException {
        int IDCommCount = 0;
        List<OrdineInterface> r = List.of(new Ordine(2,2,DARITIRARE,5));
        for(OrdineInterface o1 : list){
            for(OrdineInterface o2 : r){
                if(o1.getIDOrdine()==o2.getIDOrdine() && o1.getIDCommerciante()==o2.getIDCommerciante())IDCommCount++;
            }
        }
        assertEquals(1,IDCommCount);
    }




    private void checkListOrdini(List<OrdineInterface> l) throws ClassNotFoundException {
        int idOrdinecount = 0;
        List<OrdineInterface> r = List.of(new Ordine(1,1,INPREPARAZIONE,2),
                new Ordine(2,2,DARITIRARE,5));
        for(OrdineInterface o1 : l){
            for(OrdineInterface o2 : r)if(o1.getIDOrdine()==o2.getIDOrdine()) idOrdinecount++;
        }
        assertEquals(1, idOrdinecount);
    }


    private void IDcorriereFromOrdine(List<OrdineInterface> l) throws ClassNotFoundException {
        int _IDCorriereCounter = 0;
        List<OrdineInterface> r = List.of(new Ordine(2,2,DARITIRARE,2));
        for(OrdineInterface o1 : l)for(OrdineInterface o2 : r)if(o1.getIDCorriere()==o2.getIDCorriere())_IDCorriereCounter++;
        assertEquals(1,_IDCorriereCounter);
    }














}
