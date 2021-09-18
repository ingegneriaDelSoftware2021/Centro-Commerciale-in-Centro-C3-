package it.unicam.cs.ids.c3.model.Utente;

/**
 * Questa Classe rapressenta un utente generale.
 * @author Davide Menghini, Francesco Allevi
 */
public abstract class Utente {



    private String utente;

    private String nome;

    private String password;

    private String indirizzo;

    private String tipo;

    /**
     * Questo &egrave; un costruttore di default.
     */
    public Utente(){
        this.utente = "";
        this.indirizzo = "";
        this.nome = "";
        this.password = "";
        this.tipo ="";
    }



    /**
     * Questo metodo ritorna il nome dell'utente
     * @return nome dell'utente
     */
    public String getNomeCognome() {
        return this.nome;
    }

    /**
     * Questo metodo permette di modificare il nome dell'utente
     * @param nome il nuovo nome.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }



    /**
     * Questo metodo ritorna l'indirizzo dell'utente.
     * @return indirizzo.
     */
    public String getIndirizzo() {
        return this.indirizzo;
    }

    /**
     * Questo metodo permette di impostare l'indirizzo dell'utente.
     * @param indirizzo il nuovo indirizzo.
     */
    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    /**
     * Questo metodo permette di ritornare il nome utente per l'accesso al sistema
     */
    public String getUtente(){
        return this.utente;
    }
    /**
     * Questo metodo permette di impostare il nome utente per l'accesso al sistema.
     * @param utente il nuovo nome utente per l'accesso.
     */
    public void setUtente(String utente){
        this.utente = utente;
    }


    /**
     * Questo metodo permette di ritornare la password di questo utente.
     * @return la password.
     */
    public String getPassword(){
        return this.password;
    }

    /**
     * Questo metodo permette di impostare la password di questo utente.
     * @param password la nuova password.
     */
    public void setPassword(String password){
        this.password = password;
    }

    /**
     * Questo metodo permette di ritornare il tipo  di questo utente.
     * @return il tipo .
     */
    public String getTipo(){
        return this.tipo;
    }

    /**
     * Questo metodo permette di impostare il tipo  di questo utente.
     * @param tipo il nuovo tipo .
     */
    public void setTipo(String tipo){
        this.tipo = tipo;
    }
}
