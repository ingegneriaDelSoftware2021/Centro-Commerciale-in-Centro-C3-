package it.unicam.cs.ids.c3.model.Autenticazione;

public class UtenteSenzaAccesso {

    private String username;
    private String password;
    private String ruolo;


    public UtenteSenzaAccesso(String username, String password, String ruolo) {
        if(username.equals("")||password.equals("")|| ruolo.equals("")) throw new NullPointerException("valori passati nulli");
        this.username = username;
        this.password = password;
        this.ruolo = ruolo;
    }

    public String login(){
        return GestoreAutenticazione.getInstance().login(this.username, this.password, this.ruolo);
    }
}
