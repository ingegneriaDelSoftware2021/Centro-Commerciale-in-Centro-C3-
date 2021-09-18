package it.unicam.cs.ids.c3.model.Esercente;
import it.unicam.cs.ids.c3.model.Ordini.OrdineInterface;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Questa classe implementa un negozio di default.
 * @author Davide Menghini, Francesco Allevi.
 */
public class    Negozio {

    private final int IDNegozio;

    private final int propietario;

    private final String nomeNegozio;

    private List<Prodotto> listaProdotti;

    private List<Promozioni> listaPromozioni;

    /**
     * Questo &egrave; un costruttore di default.
     * @param idNegozio id del negozio
     * @param IDPropietario id del propietario, che corrisponde al commerciante.
     * @param nomeNegozio nome del negozio.
     */
    public Negozio(int idNegozio, int IDPropietario, String nomeNegozio) {
        this.IDNegozio = idNegozio;
        this.propietario = IDPropietario;
        this.nomeNegozio = nomeNegozio;
        this.listaProdotti = ListaNegozi.getInstance().getAllProdottiFromNegozio(idNegozio);
        this.listaPromozioni = ListaNegozi.getInstance().getAllPromozioni(this.IDNegozio);/*.stream().filter(x->x.getProdotto().getIDNegozio()==this.IDNegozio).collect(Collectors.toList())*/;

    }

    /**
     * Questo metodo restituisce l'id del negozio.
     * @return id del negozio.
     */
    public int getIDNegozio() {
        return IDNegozio;
    }

    /**
     * Questo metodo restituisce il commerciante che possiede questo negozio.
     * @return propietario del negozio.
     */
    public int getPropietario() {
        return propietario;
    }

    /**
     * Questo metodo restituisce il nome del negozio.
     * @return nome del negozio.
     */
    public String getNomeNegozio() {
        return nomeNegozio;
    }

    /**
     * Questo metodo serve per aggiungere una promozione.
     * @param p promozione da aggiungere.
     */
    public void addPromozione(Promozioni p, int IDProdotto){
        this.listaProdotti= ListaNegozi.getInstance().cercaProdottoFromNegozio(this.IDNegozio);
        if(this.listaProdotti.stream().filter(x->x.getPromozione()==0).anyMatch(x->x.getIDprodotto()==IDProdotto)){
            ListaNegozi.getInstance().addPromozioneToProdotto(p.getSconto(),p.getStato(),p.getIDpromozione(),IDProdotto);
            this.listaPromozioni.add(p);
        }else throw new IllegalStateException();


    }

    /**
     * Questo metodo serve per rimuovere una promozione.
     * @param IDPromozione id della promozione da rimuovere.
     */
    public void rimuoviPromozione(int IDPromozione, int IDProdotto){
        ListaNegozi.getInstance().deletePromozione(IDPromozione, IDProdotto);
        listaPromozioni.removeIf(p -> p.getIDpromozione() == IDPromozione);

    }


    /**
     * Questo metodo modifica lo stato di un promozione, ovvero se la promozione in questo momento &egrave;
     * attiva o no.
     * @param IDPromozione id della promozione.
     * @param stato nuovo stato della promozione.
     */
    public void aggiornaStatoPromozioni(int IDPromozione, boolean stato){
        for(Promozioni n : listaPromozioni){
            if(n.getIDpromozione()==IDPromozione){
                n.setStato(stato);
            }
        }
        ListaNegozi.getInstance().updateStatoPromozione(IDPromozione,stato);
    }

    /**
     * Questo metodo aggiorna lo sconto, in percentuale, di una promozione.
     * @param IDPromozione id della promozione.
     * @param sconto nuovo sconto in percentuale.
     */
    public void aggiornaScontoPromozioni(int IDPromozione, int sconto){
        updateListaProdotti();
        listaPromozioni.stream().filter(x -> x.getIDpromozione()==IDPromozione).forEach(x -> x.setSconto(sconto));
        ListaNegozi.getInstance().updateScontoPromozione(IDPromozione,sconto);
    }

    /**
     * Questo metodo mostra le promozioni del negozio
     * @return lista promozioni
     */
    public List<Promozioni> getListaPromozioni() {
        updateListaProdotti();
        return listaPromozioni;
    }

    /**
     * Questo metodo mostra i prodotti del negozio
     * @return lista dei prodotti
     */
    public List<Prodotto> getListaProdotti(){
        updateListaProdotti();
        return this.listaProdotti;
    }

    /**
     * Questo metodo mostra la classifica dei prodotti piu' venduti
     * @return lista di prodotti piu' venduti
     */
    public Map<Prodotto, Integer> getProdottiVenduti() {
        Map<Prodotto,Integer> map = new HashMap<>();
        List<Prodotto> list = ListaNegozi.getInstance().getAllProdottiFromNegozio(this.IDNegozio);
        list.forEach(x -> map.put(x,x.getCounterVendita()));
        return map;
    }

    /**
     * Questo metodo mostra tutti gli ordini del negozio
     * @return lista di ordini
     */
    public List<OrdineInterface> getAllOrdini(){
        return ListaNegozi.getInstance().getAllOrdiniFromNegozio(this.IDNegozio);
    }

    /**
     * questo metodo mostra le statistiche dei clienti che si fanno spedire l'ordine a casa e quelli che lo ritirano al locker
     *
     *  @return lista delle statistiche
     */
    public int[] getStatClienti(){
        int[] rit = {0,0};
        List<OrdineInterface> list = getAllOrdini().stream().filter(x->x.getIDNegozio()==this.IDNegozio).collect(Collectors.toList());
        for(OrdineInterface o : list){
            if(o.getIDLocker()!=0)rit[0]++;
            else if(o.getDestinazione()!=null &&!o.getDestinazione().equals(""))rit[1]++;
        }
        return rit;
    }

    private void updateListaProdotti() {
        this.listaProdotti = ListaNegozi.getInstance().getAllProdottiFromNegozio(this.IDNegozio);

    }
}
