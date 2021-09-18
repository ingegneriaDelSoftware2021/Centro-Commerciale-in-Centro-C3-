package it.unicam.cs.ids.c3.model.Esercente;

import it.unicam.cs.ids.c3.model.Corriere.CorriereInterface;

import java.util.List;
import java.util.Map;

/**
 * Questa interfaccia definisce il comportamento di un commerciante.
 * @author Davide Menghini, Francesco Allevi.
 */
public interface CommercianteInterface {


    /**
     * Questo metodo permette di aggiungere una nuova promozione ad un prodotto.
     * @param IDNegozio id del negozio in cui &egrave; attivare la promozione
     * @param IDProdotto id del prodotto su cui attivare la promozione
     * @param sconto sconto in percentuale della promozione
     * @param stato stato della promozione. Se &egrave; true la promozione &egrave;
     *              attiva, altrimenti no.
     */
    void addPromozioni(int IDNegozio, int IDProdotto, int sconto, boolean stato);


    /**
     * Questo metodo permette di eliminare una promozione in un negozio.
     * @param IDNegozio id del negozio
     * @param IDPromozione id della promozione.
     * @param IDProdotto id del prodotto a cui viene applicata la promozione.
     */
    void eliminaPromozione(int IDNegozio,int IDPromozione, int IDProdotto);

    /**
     * Questo metodo permette di modificare lo stato della promozione.
     * @param IDNegozio id del negozio.
     * @param IDPromozione id della promozione.
     * @param nuovoStato nuovo stato della promozione. Se &egrave; true la promozione &egrave;
     *      *              attiva, altrimenti no.
     */
    void modificaStatoPromozione(int IDNegozio, int  IDPromozione, boolean nuovoStato);

    /**
     * Questo metodo permette di modificare lo sconto della promozione.
     * @param IDNegozio id del negozio.
     * @param IDPromozioni id della promozione.
     * @param nuovoSconto nuovo sconto in percentuale della promozione.
     */
    void modificaScontoPromozione(int IDNegozio, int IDPromozioni, int nuovoSconto);


    /**
     * questo metodo permette di creare un nuovo ordine da spedire.
     * @param IDcliente id del cliente.
     * @param prodotti una lista contenenti i prodotti dell'ordine da creare.Se un prodotto &egrave;
     *                 stato acquistato con una quantit&agrave; maggiore di 1 allora nella lista sar&agrave;
     *                 presente pi&ugrave; di un instanza del prodotto.
     */
    void creaOrdine(int IDcliente, List<Prodotto> prodotti);


    /**
     * Questo metodo restituisce una lista di tutti i corrieri che in questo momento sono
     * disponibili per effettuare una consegna.
     * @return una lista contenente i corrieri disponibili.
     */
    List<CorriereInterface> getCorrieriDisponibili();

    /**
     * Questo metodo permette di impostare la casa del cliente come destinazione.
     * @param indirizzoCliente indirizzo del cliente.
     * @param IDOrdine id dell'ordine.
     */
    void selezionaDestinazioneCasa(String indirizzoCliente, int IDOrdine);


    /**
     * Questo metodo permette di impostare un locker come destinazione.
     * @param IDLocker indirizzo del locker
     * @param IDOrdine id dell'ordine.
     */
    void selezionaDestinazioneLocker(int IDLocker, int IDOrdine);



    /**
     * Questo metodo permette di selezionare un corriere per trasportare l'ordine
     * al cliente.
     * @param IDCorriere id del corriere.
     * @param IDOrdine id dell'ordine.
     */
    void selezionaCorriere(int  IDCorriere, int IDOrdine);


    /**
     * Questo metodo permette di sapere l'id del commerciante.
     * @return id del commerciante.
     */
    int getIDCommerciante();

    /**
     * Questo metodo permette di sapere quanti soldi ha il commerciante nel portafoglio.
     * @return i soldi contenuti nel portafoglio.
     */
    float getSoldiPortafoglio();

    /**
     * Questo metodo permette di conoscere la lista dei negozi di cui il commerciante
     * &egrave; titolare.
     * @return una lista contenente negozi.
     */
    List<Negozio> getListaNegozi();



    /**
     * Questo metodo permette di ricevere i soldi dal portafoglio del cliente.
     * @param idCliente id del cliente.
     * @param importo soldi da prelevare dal portafoglio del cliente e da aggiungere al portafoglio
     *                del commerciante.
     * @param prodotti una lista contenente i prodotti dell'ordine contenuti nel carrello del cliente.Se un prodotto
     *                 &egrave; stato acquistato con una quantit&agrave; maggiore di 1 allora nella lista sar&agrave;
     *                 presente pi&ugrave; di un instanza del prodotto.
     * @return true se l'importo &egrave; stato ricevuto correttamente,
     * false altrimenti.
     */
    boolean riceviPagamento(int idCliente, float importo, List<Prodotto> prodotti);


    /**
     * Questo metodo restituisce tutte le promozioni che ha il commerciante in questo momento in tutti
     * i negozi che possiede.
     * @return lista di promozioni dei negozi.
     */
    List<Promozioni> getAllPromozioni(int idNegozio);


    /**
     * Questo metodo permette di sapere il numero delle vendite di tutti i prodotti del negozio.
     * @param IDNegozio id del negozio su cui vedere le vendite dei prodotti.
     * @return un Map che ha come chiave la classe prodotto e un integer come valore.
     */
    Map<Prodotto,Integer> getProdottiPVenduti(int IDNegozio);

    /**
     * Questo metodo restituisce il nome del commerciante.
     * @return una string contenente nome e cognome del commerciante.
     */
    String getNome();

    /**
     * Questo metodo permette di restituire il nome utente usato dal commerciante per fare il login.
     * @return il nome utente del commerciante per fare il login.
     */
    String getUser();
}
