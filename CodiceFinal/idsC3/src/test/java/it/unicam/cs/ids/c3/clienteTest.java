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
            ClienteInterface c = ListaClienti.getInstance().getCliente(2);
            if(c.getCarrello().getIDNegozio()!=0){
                int idn = c.getCarrello().getIDNegozio();
                int idComm = DBLocale.getInstance().getIDCommFromNegozio(idn);
                CommercianteInterface comm = DBLocale.getInstance().getAllCommercianti().parallelStream().filter(x->x.getIDCommerciante()==idComm).findFirst().orElse(null);
                float oldPortafoglioCliente = c.getPortafoglio();
                float expected = oldPortafoglioCliente- DBLocale.getInstance().getTotaleFromCarrello(1);
                float oldPortComm = Objects.requireNonNull(comm).getSoldiPortafoglio();
                float expectedcomm = oldPortComm + DBLocale.getInstance().getTotaleFromCarrello(2);
                assertTrue(c.inviaPagamento(ListaCarrello.getInstance().getCarrello(2)));
                c = ListaClienti.getInstance().getCliente(2);
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
