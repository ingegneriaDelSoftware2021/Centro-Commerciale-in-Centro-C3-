package it.unicam.cs.ids.c3.model.Clienti;

import it.unicam.cs.ids.c3.model.DBLocale;

import java.util.List;

/**
 * Questa classe serve per gestire i clienti presenti nel database.
 * @author Davide Menghini, Francesco Allevi.
 */
public class ListaClienti {


    private DBLocale db;
    private List<ClienteInterface> listaClienti;
    private static ListaClienti instance;


    public ListaClienti(){
        try {
            db = DBLocale.getInstance();
            listaClienti = db.getAllClienti();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }



    public static ListaClienti getInstance(){
        if(instance==null){
            instance = new ListaClienti();
        }
        return instance;
    }

    public List<ClienteInterface> getClienti(){return this.listaClienti;}

    public void aggiungiCliente(){
        //TODO completare
    }

    public void rimuoviCliente(){
        //TODO completare
    }

    public ClienteInterface getCliente(int IDCliente){
        return this.listaClienti.stream().filter(z -> z.getIDcliente()==IDCliente).findFirst().orElse(null);
    }

}
