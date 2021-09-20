package it.unicam.cs.ids.c3.model.Autenticazione;

import it.unicam.cs.ids.c3.model.database.DBLocale;

/**
 * Questa classe gestisce il login nella piattaforma C3.
 * @author Davide Menghini, Francesco Allevi.
 */
public class GestoreAutenticazione {


    private GestoreAutenticazione() {
    }

    /**
     * Questo metodo restituisce l'instanza del gestore autenticazione.
     * @return istanza di GestoreAutenticazione
     */
    public static GestoreAutenticazione getInstance() {
        return new GestoreAutenticazione();
    }

    /**
     * Questo metodo permette di far fare il login ad un utente.
     * @param username username
     * @param password password
     * @param ruolo il ruolo dell'utente che ha nel sistema. Possono esserci 4 ruoli: Cliente,
     *              Commerciante, Locker e Corriere.
     * @return restituisce "" se le credenziali non corrispondono, oppure viene restituito il nome
     * del ruolo dell'account.
     */
    public String login(String username,String password,String ruolo){
        try {
            String risultato = DBLocale.getInstance().checklogin(username, password, ruolo);
            if(risultato.equals("")) return "";
            else return risultato;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }
}
