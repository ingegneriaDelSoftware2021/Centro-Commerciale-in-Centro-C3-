package it.unicam.cs.ids.c3.model.Clienti;


import it.unicam.cs.ids.c3.model.Esercente.*;
import it.unicam.cs.ids.c3.model.Ordini.ListaOrdini;
import it.unicam.cs.ids.c3.model.Ordini.OrdineInterface;
import it.unicam.cs.ids.c3.model.Utente.Utente;
import it.unicam.cs.ids.c3.model.Esercente.ListaNegozi;
import java.util.ArrayList;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Questa classe &egrave; un'implementazione di default dell'interfaccia ClienteInterface
 * e della classe astratta Utente.
 * @author Davide Menghini, Francesco Allevi.
 */
public class Cliente extends Utente implements ClienteInterface {

    private final int IDcliente;


    private Carrello carrello;



    private float portafoglio;




    /**
     * Questo costruttore di deafult serve per inizializzare un cliente.
     * @param iDcliente id di questo cliente.
     * @param nome nome di questo cliente.
     * @param indirizzo indirizzo dell'abitazione del cliente.
     * @param IDCarrello -id del carrello del cliente.
     */
    public Cliente(int iDcliente, String nome, String indirizzo, int IDCarrello, float portafoglio, String utente) {
        super();
        this.IDcliente = iDcliente;
        setUtente(utente);
        setNome(nome);
        super.setIndirizzo(indirizzo);
        this.portafoglio = portafoglio;
        this.carrello = ListaCarrello.getInstance().getCarrello(IDCarrello);

    }








    @Override
    public List<Negozio> visualizzaNegozi() {
        return ListaNegozi.getInstance().getNegozi();

    }


    @Override
    public List<Promozioni> visualizzaPromozioni() {

        List<Negozio> listaNegozi = ListaNegozi.getInstance().getNegozi();
        List<Promozioni> rit = new ArrayList<>();
        for(Negozio n : listaNegozi){
            rit.addAll(n.getListaPromozioni());
        }
        return rit;

    }

    @Override
    public boolean inviaPagamento(Carrello carrello) {
        CommercianteInterface c = ListaCommercianti.getInstance().getCommerciante(carrello.getIDCommerciante());
        if(carrello.getTotale()>this.portafoglio) return false;
        float totale = carrello.getTotale();
        List<Prodotto> listaProdotti = carrello.getProdotti();
        carrello.svuotaCarrello();
        ListaClienti.getInstance().sottraiDenaroPortafoglio(this.IDcliente, totale);
        return Objects.requireNonNull(c).riceviPagamento(this.IDcliente, totale, listaProdotti);
    }



    @Override
    public List<Prodotto> cercaProdotto(String nomeProdotto, int idNegozio) {
        if(nomeProdotto.equals("") || idNegozio<0) throw new NullPointerException();
        Negozio negozio = ListaNegozi.getInstance().getNegozi().stream().filter(x -> x.getIDNegozio()==idNegozio).findFirst().orElse(null);
        return ListaNegozi.getInstance().cercaProdottoFromNegozio(Objects.requireNonNull(negozio).getIDNegozio(),nomeProdotto);
    }

    @Override
    public int getIDcliente() {
        return this.IDcliente;
    }




    @Override
    public void aggiungiCredito(float portafoglio) {
        if(portafoglio<=0) return;
        float newPortafoglio = getPortafoglio()+portafoglio;
        ListaClienti.getInstance().AddCredito(this.IDcliente,newPortafoglio);
    }


    @Override
    public float getPortafoglio() {
        return ListaClienti.getInstance().getPortafoglioFromIDC(this.IDcliente);
    }

    @Override
    public Carrello getCarrello() {
        return this.carrello;
    }

    @Override
    public String getUser() {
        return getUtente();
    }

    @Override
    public String getNome(){
        return getNomeCognome();
    }

    @Override
    public List<OrdineInterface> getAllOrdiniCliente() {
        return ListaOrdini.getInstance().getOrdiniFromCliente(this.IDcliente);
    }

    @Override
    public void aggiungiProdottoAlCarrello(Prodotto prodotto, int quantita) {
        if(this.carrello==null){
            try {
                this.carrello = new Carrello(this.IDcliente,0);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        if(this.carrello.getProdotti().isEmpty()){
            this.carrello.aggiungiProdotto(prodotto,quantita);
        }else{
            int idNegozioGiusto = this.carrello.getProdotti().stream().map(Prodotto::getIDNegozio).findFirst().orElse(0);
            if(idNegozioGiusto== prodotto.getIDNegozio())this.carrello.aggiungiProdotto(prodotto,quantita);
            else{
                throw new IllegalArgumentException();
            }
        }


    }

    @Override
    public void rimuoviProdottoAlCarrello(Prodotto prodotto) {
        this.carrello.rimuoviProdotto(prodotto.getIDprodotto(), 0);
    }

    @Override
    public void modificaQuantitaAlCarrello(Prodotto p, int nuovaQuantita) {
        this.carrello.rimuoviProdotto(p.getIDprodotto(), nuovaQuantita);
    }

    @Override
    public String getIndirizzo(){
        return super.getIndirizzo();
    }


}
