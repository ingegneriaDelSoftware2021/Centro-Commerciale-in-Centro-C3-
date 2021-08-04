package it.unicam.cs.ids.c3.model.Esercente;

import it.unicam.cs.ids.c3.model.Clienti.Cliente;
import it.unicam.cs.ids.c3.model.Corriere.Corriere;
import it.unicam.cs.ids.c3.model.Corriere.ListaCorrieri;
import it.unicam.cs.ids.c3.model.Lockers.ListaLockers;
import it.unicam.cs.ids.c3.model.Ordini.Ordine;

import java.util.List;

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
     */
    void eliminaPromozione(int IDNegozio,int IDPromozione);

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
    void modificaScontoPromozione(int IDNegozio, int IDPromozioni, double nuovoSconto);


    /**
     * questo metodo permette di creare un nuovo ordine da spedire.
     * @param IDcliente id del cliente.
     */
    void creaOrdine(int IDcliente);


    /**
     * Questo metodo restituisce una lista di tutti i corrieri che in questo momento sono
     * disponibili per effettuare una consegna.
     * @return una lista contenente i corrieri disponibili.
     */
    List<Corriere> getCorrieriDisponibili();

    /**
     * Questo metodo permette di impostare la casa del cliente come destinazione.
     * @param indirizzoCliente indirizzo del cliente.
     * @param IDOrdine id dell'ordine.
     */
    void selezionaDestinazioneCasa(String indirizzoCliente, int IDOrdine);


    /**
     * Questo metodo permette di impostare un locker come destinazione.
     * @param indirizzoLocker indirizzo del locker
     * @param IDOrdine id dell'ordine.
     */
    void selezionaDestinazioneLocker(String indirizzoLocker, int IDOrdine);


    /**
     * Questo metodo permette di ritornare una lista dei lockers.
     * @return una lista contenente i lockers.
     */
    ListaLockers getListaLocker();

    /**
     * Questo metodo permette di aggiornare lo stato dell'ordine come INPREPARAZIONE.
     * @param IDOrdine id dell'ordine
     */
    void aggiornaStatoOrdine(int IDOrdine);

    /**
     * Questo metodo permette di selezionare un corriere per trasportare l'ordine
     * al cliente.
     * @param IDCorriere id del corriere.
     * @param IDOrdine id dell'ordine.
     * @return un messaggio di confemra se l'operazione &egrave; andata a buon fine.
     */
    String selezionaCorriere(int  IDCorriere, int IDOrdine);


    /**
     * Questo metodo permette di sapere l'id del commerciante.
     * @return id del commerciante.
     */
    int getIDCommerciante();

    /**
     * Questo metodo permette di sapere quanti soldi ha il commerciante nel portafoglio.
     * @return i soldi contenuti nel portafoglio.
     */
    float getPortafoglio();

    /**
     * Questo metodo permette di conoscere la lista dei negozi di cui il commerciante
     * &egrave; titolare.
     * @return una lista contenente negozi.
     */
    List<Negozio> getListaNegozi();

    /**
     * Questo metodo permette di aggiungere un nuovo negozio di cui il commerciante &egrave;
     * titolare.
     * @param indirizzo indirizzo del negozio.
     */
    void addNegozio(String indirizzo);

    /**
     * Questo metodo permette di ricevere i soldi dal portafoglio del cliente.
     * @param idCliente id del cliente.
     * @param importo soldi da prelevare dal portafoglio del cliente e da aggiungere al portafoglio
     *                del commerciante.
     */
    void riceviPagamento(int idCliente, float importo);
}
