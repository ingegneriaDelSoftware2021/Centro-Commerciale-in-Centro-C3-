package it.unicam.cs.ids.c3.model.Autenticazione;

import it.unicam.cs.ids.c3.model.database.DBLocale;

public class GestoreAutenticazione {


    private GestoreAutenticazione() {
    }

    public static GestoreAutenticazione getInstance() {
        return new GestoreAutenticazione();
    }

    public String login(String username,String password,String ruolo){
        try {
            String risultato = DBLocale.getInstance().checklogin(username, password, ruolo);
            if(risultato.equals("")) return "credenziali errate";
            else return risultato;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }
}
