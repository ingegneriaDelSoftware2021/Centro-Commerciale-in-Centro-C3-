package it.unicam.cs.ids.c3.model;

public class Negozio {

    private final int IDNegozio;

    private final Commerciante propietario;

    private final String nomeNegozio;

    public Negozio(int idNegozio, Commerciante propietario, String nomeNegozio) {
        IDNegozio = idNegozio;
        this.propietario = propietario;
        this.nomeNegozio = nomeNegozio;
    }

    public int getIDNegozio() {
        return IDNegozio;
    }

    public Commerciante getPropietario() {
        return propietario;
    }

    public String getNomeNegozio() {
        return nomeNegozio;
    }





}
