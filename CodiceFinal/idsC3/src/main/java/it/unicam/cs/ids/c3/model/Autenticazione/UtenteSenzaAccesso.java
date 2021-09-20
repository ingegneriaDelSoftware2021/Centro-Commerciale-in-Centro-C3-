package it.unicam.cs.ids.c3.model.Autenticazione;

/**
 * Questa classe rappresenta un utente che ancora non ha effettuato il login.
 * @author Davide Menghini, Francesco Allevi.
 */
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

    /**
     * Questo metodo permette di effettuare il login.
     * @return restituisce "" se le credenziali non corrispondono, oppure viene restituito il nome
     * del ruolo dell'account.
     */
    public String login(){
        return GestoreAutenticazione.getInstance().login(this.username, this.password, this.ruolo);
    }
}
