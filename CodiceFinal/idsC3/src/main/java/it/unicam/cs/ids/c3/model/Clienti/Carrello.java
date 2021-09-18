package it.unicam.cs.ids.c3.model.Clienti;
import it.unicam.cs.ids.c3.model.Esercente.Prodotto;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * Questa classe rappresenta il carrello del cliente. Un cliente possiede un carrello che contiene vari prodotti
 * che il cliente vorrebbe acquistare.
 * @author Davide Menghini, Francesco Allevi.
 */
public class Carrello {

    private float totale;

    private final int IDCarrello;


    private int IDCommerciante;

    private List<Prodotto> prodotti;

    /**
     * Questo costruttore inizializza un carrello.
     * @param IDCliente id del cliente che possiede il carrello.
     */
    public Carrello(int IDCliente, float totale) throws ClassNotFoundException {
        this.totale = totale;
        prodotti = ListaCarrello.getInstance().ListaProdotto(IDCliente);
        if(prodotti==null) this.prodotti = new ArrayList<>();
        this.IDCarrello = IDCliente;
        int idN = prodotti.parallelStream().map(x->x.getIDNegozio()).findFirst().orElse(0);

        if(idN!=0)this.IDCommerciante = ListaCarrello.getInstance().getIDCommFromCarrID(idN);
        checkIfSaled();
    }

    private void checkIfSaled() {
        for(Prodotto p : this.prodotti){
            this.totale = this.totale - ListaCarrello.getInstance().isSaled(p);
        }
    }

    /**
     * Costruttore carrello
     * @param IDCLiente
     * @param prodotti
     * @param totale
     * @param idCommerciante
     */
    public Carrello(int IDCLiente, List<Prodotto> prodotti, float totale, int idCommerciante){
        this.IDCarrello = IDCLiente;
        if(prodotti==null) this.prodotti = new ArrayList<>();
        else this.prodotti = new ArrayList<>(prodotti);
        this.totale = totale;
        this.IDCommerciante = idCommerciante;
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
        for(int i=0; i<quantita;i++){
            this.prodotti.add(p);
        }
        totale = totale + (p.getPrezzo()*quantita);
        ListaCarrello.getInstance().addToCarrello(this.IDCarrello,p,quantita);

    }

    /**
     * Questo metodo serve per rimuovere un prodotto dal carrello. Per "rimuovere un prodotto" si intende anche
     * diminuire la quantit&agrave; presente nel carrello.
     * @param IDProdotto id del prodotto.
     * @param quantita Quantit&agrave; da rimuovere.
     */
    public void rimuoviProdotto(int IDProdotto, int quantita){
        if(IDProdotto<0) return;
        ListaCarrello.getInstance().removeFromCarrello(this.IDCarrello,IDProdotto,quantita);
        float prodottoPrezzo = this.prodotti.parallelStream().filter(x -> x.getIDprodotto()==IDProdotto).map(Prodotto::getPrezzo).findFirst().orElse(0.0f);
        prodottoPrezzo = prodottoPrezzo * quantita;
        prodotti.removeIf(z -> z.getIDprodotto() == IDProdotto);
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

    /**
     * getter idcommerciante
     * @return
     */
    public int getIDCommerciante() {
        return IDCommerciante;
    }

    /**
     * getter idnegozio
     * @return
     * @throws ClassNotFoundException
     */
    public int getIDNegozio() throws ClassNotFoundException {
        return this.prodotti.stream().map(Prodotto::getIDNegozio).findFirst().orElse(0);

    }



    public long getQuantitaProdotto(Prodotto p){
        return this.prodotti.stream().filter(x->x.getIDprodotto()==p.getIDprodotto()).count();
    }


    public void svuotaCarrello(){
        Prodotto previous;
        Iterator<Prodotto> iterator = new ArrayList<>(prodotti).iterator();
        if(prodotti.size()>0){
            previous = new Prodotto(0,"",0,"",0,"",0,0,0);
            while(iterator.hasNext()){
                Prodotto p = iterator.next();
                if(!p.equals(previous)){
                    rimuoviProdotto(p.getIDprodotto(),0);
                }
                previous = p;
            }
        }

    }








}
