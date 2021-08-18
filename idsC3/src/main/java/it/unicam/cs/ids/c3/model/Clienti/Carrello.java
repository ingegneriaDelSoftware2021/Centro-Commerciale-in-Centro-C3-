package it.unicam.cs.ids.c3.model.Clienti;
import it.unicam.cs.ids.c3.model.DBLocale;
import it.unicam.cs.ids.c3.model.Esercente.Prodotto;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


/**
 * Questa classe rappresenta il carrello del cliente. Un cliente possiede un carrello che contiene vari prodotti
 * che il cliente vorrebbe acquistare.
 * @author Davide Menghini, Francesco Allevi.
 */
public class Carrello {

    private float totale;

    private final int IDCarrello;


    private int IDCommerciante;

    private final List<Prodotto> prodotti;

    /**
     * Questo costruttore inizializza un carrello.
     * @param IDCliente id del cliente che possiede il carrello.
     */
    public Carrello(int IDCliente) throws ClassNotFoundException {
        totale = 0;
        prodotti = DBLocale.getInstance().getProdottiFromCarrello(IDCliente);
        this.IDCarrello = IDCliente;
    }


    public Carrello(int IDCLiente, List<Prodotto> prodotti, float totale){
        this.IDCarrello = IDCLiente;
        this.prodotti = new ArrayList<>(prodotti);
        this.totale = totale;
    }

    public void setIDCommerciante(int IDCommerciante) {
        this.IDCommerciante = IDCommerciante;
    }

    /**
     * Questo metodo serve per sapere il costo totale dei prodotti presenti nel carrello
     * @return totale del carrello.
     */
    public float getTotale(){
        return totale;
    }

    /**
     * Questo metodo serve per aggiungere un prodotto al carello di una quantità specifica.
     * @param p prodotto da aggiungere.
     * @param quantita quantit&agrave; del prodotto.
     */
    public void aggiungiProdotto(Prodotto p, int quantita){
        if(p==null) return;
        if(quantita<0) return;
        this.prodotti.add(p);
        totale = totale + (p.getPrezzo()*quantita);
    }

    /**
     * Questo metodo serve per rimuovere un prodotto dal carrello. Per "rimuovere un prodotto" si intende anche
     * diminuire la quantit&agrave; presente nel carrello.
     * @param IDProdotto id del prodotto.
     * @param quantita Quantit&agrave; da rimuovere.
     */
    public void rimuoviProdotto(int IDProdotto, int quantita){
        if(IDProdotto<0) return;
        float prodottoPrezzo = this.prodotti.parallelStream().filter(x -> x.getIDprodotto()==IDProdotto).map(Prodotto::getPrezzo).findFirst().orElse(0.0f);
        prodottoPrezzo = prodottoPrezzo * quantita;
        prodotti.removeIf(p -> p.getIDprodotto() == IDProdotto);
        totale = totale-(prodottoPrezzo*quantita);
    }

    /**
     * Questo metodo serve per sapere l'id del carrello.
     * @return id del carrello.
     */
    public int getIDCarrello() {
        return IDCarrello;
    }

    /**
     * Questo metodo restituisce la lista dei prodotti presenti nel carrello
     * @return lista dei prodotti del carrello.
     */
    public List<Prodotto> getProdotti() {
        return prodotti;
    }


    public int getIDCommerciante() {
        return IDCommerciante;
    }


    public int getIDNegozio() throws ClassNotFoundException {
        int idp;
        int rit = 0;
        for(Prodotto p : prodotti){
            idp = p.getIDprodotto();
        }
        return Objects.requireNonNull(DBLocale.getInstance().getAllProdotti().stream().findFirst().orElse(null)).getIDNegozio();

    }


}
