package it.unicam.cs.ids.c3.model.Clienti;

import it.unicam.cs.ids.c3.model.database.DBLocale;

import java.util.List;

/**
 * Questa classe serve per gestire i clienti presenti nel database.
 * @author Davide Menghini, Francesco Allevi.
 */
public class ListaClienti {


    private DBLocale db;
    private List<ClienteInterface> listaClienti;
    private static ListaClienti instance;


    private ListaClienti(){
        List<ClienteInterface> o = null;
        try {
            db = DBLocale.getInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        listaClienti = o;
    }


    /**
     * ritorna l'istanza della classe
     * @return
     */
    public static ListaClienti getInstance(){
        if(instance==null){
            instance = new ListaClienti();
        }
        return instance;
    }




    public ClienteInterface getCliente(int IDCliente){
        this.listaClienti = db.getAllClienti();
        return this.listaClienti.stream().filter(z -> z.getIDcliente()==IDCliente).findFirst().orElse(null);
    }

    /**
     * Questo metodo toglie il il totale da pagare dal portafoglio del cliente
     * @param iDcliente id del cliente di cui sottrarre il totale
     * @param totale totale da pagare
     */
    public void sottraiDenaroPortafoglio(int iDcliente, float totale) {
        db.sottraiDenaro(iDcliente, totale);
    }



    /**
     * Questo metodo restituisce il portafoglio del cliente
     * @param iDcliente id del cliente
     * @return portafoglio
     */
    public float getPortafoglioFromIDC(int iDcliente) {
        return db.getPortaglioFromIDCliente(iDcliente);
    }

    /**
     * Questo metodo restituisce l'id del cliente dall'utente senza accesso
     * @param user utente senza accesso
     * @return il cliente
     */
    public ClienteInterface getClienteFromUser(String user) {
        return db.getAllClienti().stream().filter(x->x.getUser().equals(user)).findFirst().orElse(null);
    }


    /**
     * Questo metodo aggiunge credito al portafoglio del cliente
     * @param idCliente id del cliente a cui aggiungere credito
     * @param newPortafoglio portafoglio aggiornato
     */
    public void AddCredito(int idCliente, float newPortafoglio) {
        db.addCreditoToCliente(idCliente,newPortafoglio);
    }


}
