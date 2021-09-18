package it.unicam.cs.ids.c3;

import it.unicam.cs.ids.c3.model.Clienti.ClienteInterface;
import it.unicam.cs.ids.c3.model.Clienti.ListaCarrello;
import it.unicam.cs.ids.c3.model.Clienti.ListaClienti;
import it.unicam.cs.ids.c3.model.database.DBLocale;
import it.unicam.cs.ids.c3.model.Esercente.CommercianteInterface;
import it.unicam.cs.ids.c3.model.Esercente.Prodotto;
import org.junit.Test;

import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class clienteTest {


    @Test
    public void pagamentoTest() throws ClassNotFoundException {
        ClienteInterface c = ListaClienti.getInstance().getCliente(1);
        if(c.getCarrello().getProdotti().size()!=0){
            int finalC = c.getCarrello().getIDNegozio();
            int idComm = DBLocale.getInstance().getIDCommFromNegozio(finalC);
            System.out.println(finalC+" idneg pagamentotest");
            CommercianteInterface comm = DBLocale.getInstance().getAllCommercianti().parallelStream().filter(x->x.getIDCommerciante()==idComm).findFirst().orElse(null);
            float oldPortafoglioCliente = c.getPortafoglio();
            System.out.println(DBLocale.getInstance().getTotaleFromCarrello(1)+"getTotaleFromCarrello()");
            float expected = oldPortafoglioCliente- DBLocale.getInstance().getTotaleFromCarrello(1);
            float newP = oldPortafoglioCliente-12.8f;
            float oldPortComm = Objects.requireNonNull(comm).getSoldiPortafoglio();
            System.out.println(oldPortComm+" vecchio portafoglio del commerciante dentro pagamentoTest");
            float expectedcomm = oldPortComm + DBLocale.getInstance().getTotaleFromCarrello(1);
            for(Prodotto p : ListaCarrello.getInstance().ListaProdotto(1)){
                System.out.println(p.getNome()+" nome del prodotto nel carrello 1");
            }
            assertTrue(c.inviaPagamento(ListaCarrello.getInstance().getCarrello(1)));
            c = ListaClienti.getInstance().getCliente(1);
            assertEquals(expected, c.getPortafoglio(), 0.1);

            assertEquals(expectedcomm,comm.getSoldiPortafoglio(), 0.1);
        }

    }


    @Test
    public void getUserAndNameTest(){
        ClienteInterface x = ListaClienti.getInstance().getClienteFromUser("pluto");
        assertEquals("pluto",x.getUser());
        assertEquals("luca esposito",x.getNome());
    }



}
