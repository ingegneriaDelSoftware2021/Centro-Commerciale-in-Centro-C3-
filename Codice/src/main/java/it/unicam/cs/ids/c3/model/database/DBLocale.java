package it.unicam.cs.ids.c3.model.database;

import it.unicam.cs.ids.c3.model.Clienti.Carrello;
import it.unicam.cs.ids.c3.model.Clienti.Cliente;
import it.unicam.cs.ids.c3.model.Clienti.ClienteInterface;
import it.unicam.cs.ids.c3.model.Corriere.Corriere;
import it.unicam.cs.ids.c3.model.Corriere.CorriereInterface;
import it.unicam.cs.ids.c3.model.Esercente.*;
import it.unicam.cs.ids.c3.model.Lockers.Armadietto;
import it.unicam.cs.ids.c3.model.Lockers.Locker;
import it.unicam.cs.ids.c3.model.Lockers.LockerInterface;
import it.unicam.cs.ids.c3.model.Ordini.Ordine;
import it.unicam.cs.ids.c3.model.Ordini.OrdineInterface;
import it.unicam.cs.ids.c3.model.Ordini.StatoOrdine;

import java.sql.*;
import java.util.*;


/**
 * Questa classe si occupa di collegarsi ad un database locale sulla porta 3306. Il database contiene le tabelle per
 * memorizzare gli ordini, i clienti, i commercianti, i corrieri, i prodotti, i carrelli dei clienti, le promozioni,
 * i negozi dei commercianti, i locker e gli armadietti. Questa classe &egrave; stata implementata usando il pattern
 * Singleton.
 * @author Davide Menghini, Francesco Allevi.
 */
public class DBLocale {

    private static DBLocale dbLocale;
    private Connection connessione;

    private DBLocale() throws ClassNotFoundException {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connessione = DriverManager.getConnection("jdbc:mysql://localhost:3306/c3?"
                    +"user=root&password=pippo&serverTimezone=Europe/Rome");
        } catch (SQLException throwables) {
            System.out.println("SQLException: " + throwables.getMessage());
            System.out.println("SQLState: " + throwables.getSQLState());
            System.out.println("VendorError: " + throwables.getErrorCode());
        }
    }


    /**
     * Questo metodo restituisce un'instanza di questa classe.
     * @return l'istanza di questa classe.
     */
    public static DBLocale getInstance() throws ClassNotFoundException {
        if(dbLocale==null){
            dbLocale = new DBLocale();
        }
        return dbLocale;
    }





    /**
     * Questo metodo restituisce la connessione al database.
     * @return la connesione al database.
     */
    public Connection getConnessione() {
        return connessione;
    }


    /**
     * Questo metodo restituisce la lista dei clienti presenti nel database.
     * @return una lista di clienti.
     */
    public List<ClienteInterface> getAllClienti(){
        List<ClienteInterface> rit = new ArrayList<>();
        try{
            PreparedStatement ps = connessione.prepareStatement("SELECT * FROM cliente");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                aggiungiClienti(rit, rs);
            }
        }catch (SQLException errore){
            errore.printStackTrace();
        }
        return rit;
    }


    /**
     * Questo metodo restituisce la lista dei corrieri presenti nel database.
     * @return una lista di corrieri.
     */
    public List<CorriereInterface> getAllCorrieri(){
        List<CorriereInterface> rit = new ArrayList<>();
        try{
            PreparedStatement ps = connessione.prepareStatement("SELECT * FROM corriere");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                aggiungiCorrieri(rit, rs);
            }
        }catch (SQLException errore){
            errore.printStackTrace();
        }
        return rit;
    }


    /**
     * Questo metodo restituisce una lista di tutti gli ordini presenti nel database.
     * @return lista di ordini presenti nel database.
     */

    public List<OrdineInterface> getAllOrdini(int idordine){
        if(idordine!=0) return getOrdine(idordine);
        List<OrdineInterface> rit = new ArrayList<>();
        try{
            PreparedStatement ps = connessione.prepareStatement("SELECT * FROM ordine");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                aggiungiOrdine(rit, rs);
            }
        }catch (SQLException errore){
            errore.printStackTrace();
        }
        return rit;
    }

    private List<OrdineInterface> getOrdine(int idordine) {
        List<OrdineInterface> rit = new ArrayList<>();
        try{
            PreparedStatement ps = connessione.prepareStatement("SELECT * FROM ordine WHERE IDOrdine = ?");
            ps.setInt(1,idordine);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                aggiungiOrdine(rit, rs);
            }
        }catch (SQLException errore){
            errore.printStackTrace();
        }
        return rit;
    }

    private void aggiungiOrdine(List<OrdineInterface> rit, ResultSet rs) {
        try {
            rit.add(new Ordine(rs.getInt("IDOrdine"),
                    rs.getInt("IDCorriere"),
                    rs.getInt("IDCliente"),
                    rs.getInt("IDCommerciante"),
                    rs.getInt("IDLocker"),
                    rs.getString("statoOrdine"),
                    rs.getString("indirizzo"),
                    rs.getInt("IDNegozio")));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Questo metodo imposta l'id del corriere dell'ordine passato
     * @param idCorriere id del corriere da impostare 
     * @param IDordine id dell'ordine di cui inserire il corriere
     */
    public void setCorriereToOrdine(int idCorriere, int IDordine){
        try{
            PreparedStatement ps = connessione.prepareStatement("UPDATE `c3`.`ordine` SET `IDCorriere` =? WHERE (`IDOrdine` =?)");
            ps.setInt(1,idCorriere);
            ps.setInt(2,IDordine);
            ps.executeUpdate();
        }catch (SQLException errore){
            errore.printStackTrace();
        }
    }


    /**
     * Questo metodo restituisce una lista di tutti i negozi presenti nel database.
     * @return lista di negozi presenti nel database.
     */
    public List<Negozio> getAllNegozi(){
        List<Negozio> rit = new ArrayList<>();
        try{
            PreparedStatement ps = connessione.prepareStatement("SELECT * FROM negozio");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                aggiungiNegozio(rit, rs);
            }
        }catch (SQLException errore){
            errore.printStackTrace();
        }
        return rit;
    }


    /**
     * Questo metodo restituisce una lista di tutti i commercianti presenti nel database.
     * @return lista di commercianti presenti nel database.
     */
    public List<CommercianteInterface> getAllCommercianti(){
        List<CommercianteInterface> rit = new ArrayList<>();
        try{
            PreparedStatement ps = connessione.prepareStatement("SELECT * FROM commerciante");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                aggiungiCommerciante(rit, rs);
            }
        }catch (SQLException errore){
            errore.printStackTrace();
        }
        return rit;
    }

    /**
     * Questo metodo permette aggiungere un nuovo ordine al database.
     * @param ordine ordine da aggiungere.
     */
    public void addOrdine(Ordine ordine){
        try{

            PreparedStatement query = connessione.prepareCall("INSERT into ordine(IDOrdine, IDCorriere,IDCliente," +
                    "IDCommerciante,IDLocker,statoOrdine,indirizzo,IDNegozio) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            query.setInt(1,ordine.getIDOrdine());
            query.setInt(2,ordine.getIDCorriere());
            query.setInt(3,ordine.getIDCliente());
            query.setInt(4,ordine.getIDCommerciante());
            query.setNull(5,ordine.getIDLocker());
              query.setString(6,ordine.getStatoOrdine().toString());
            query.setString(7, ordine.getDestinazione());
            query.setInt(8,ordine.getIDNegozio());
            query.executeUpdate();
            updateOrdineProdotto(ordine.getListaProdotti(),ordine.getIDOrdine());
        }catch (SQLException errore){
            errore.printStackTrace();
        }
    }

    private void updateOrdineProdotto(List<Prodotto> listaProdotti, int idOrdine) {
        int i= 0;
        int size = listaProdotti.size();
        while(i<size){
            Prodotto p = listaProdotti.get(0);
            int nProd = (int) listaProdotti.stream().filter(x->x.equals(p)).count();
            try{
                PreparedStatement ps = getConnessione().prepareStatement("INSERT into ordineprodotto(idO,idP,quantita) VALUES (?,?,?)");
                ps.setInt(1,idOrdine);
                ps.setInt(2,listaProdotti.get(0).getIDprodotto());
                ps.setInt(3,nProd);
                ps.executeUpdate();
                i+=nProd;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }


    /**
     * Questo metodo restituisce tutti i carrelli
     * @return lista di tutti i carrelli
     */
    public List<Carrello> getAllCarrello(){
        List<Carrello> rit = new ArrayList<>();
        List<Prodotto> prodList = new ArrayList<>();
        try{
            PreparedStatement carrello = connessione.prepareStatement("SELECT * FROM carrello");

            ResultSet rs = carrello.executeQuery();
            while(rs.next()){
                aggiungiCarrello(rit, rs);
            }
            PreparedStatement carrelloProdotto = connessione.prepareStatement("SELECT * FROM prodotto join carrelloprodotto " +
                    "ON prodotto.IDProdotto=carrelloprodotto.IDP");

            rs = carrelloProdotto.executeQuery();
            while(rs.next()) aggiungiCarrelloProdotto(prodList,rs);
        }catch (SQLException errore){
            errore.printStackTrace();
        }
        return rit;
    }


    /**
     * Questo metodo restituisce il costo totale del carrello
     * @param idcarrello id del carrello da cui prendere il totale
     * @return il costo totale dei prodotti
     */
    public float getTotaleFromCarrello(int idcarrello){
        try {
            PreparedStatement ps2 = connessione.prepareStatement("SELECT totale FROM carrello WHERE IDCarrello = ?");
            ps2.setInt(1,idcarrello);
            ResultSet rs2 = ps2.executeQuery();
            if(rs2.next()){
                return rs2.getFloat("totale");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0.0f;

    }


    /**
     * Questo metodo mostra l'id del commerciante di un determinato negozio
     * @param idNegozio id del negozio del commerciante da trovare
     * @return id del commerciante del negozio
     */
    public int getIDCommFromNegozio(int idNegozio){
        if(idNegozio==0) return 0;
        int rit = 0;
        try{
            PreparedStatement ps = connessione.prepareStatement("SELECT IDCommerciante FROM negozio WHERE (`IDNegozio` =?) ");
            ps.setInt(1,idNegozio);
            ResultSet rs = ps.executeQuery();
            rs.next();
            rit = rs.getInt("IDCommerciante");
            return rit;
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return rit;
    }


    /**
     * Questo metodo aggiunge un nuovo prodotto al database
     * @param prodotto prodotto da aggiungere
     */
    public void addProdotto(Prodotto prodotto){
        try{
            PreparedStatement query = connessione.prepareCall("INSERT INTO prodotto(IDProdotto,quantitaDisponibile,nome" +
                    "descrizione,IDPromozione,categoria,prezzo)");
            query.setInt(1,prodotto.getIDprodotto());
            query.setInt(2,prodotto.getQuantita());
            query.setString(3, prodotto.getNome());
            query.setString(4, prodotto.getDescrizione());
            query.setInt(5,prodotto.getPromozione());
            query.setString(6,prodotto.getCategoria());
            query.setFloat(7,prodotto.getPrezzo());
            query.executeUpdate();
        }catch (SQLException errore){
            errore.printStackTrace();
        }
    }



    /**
     * Questo metodo aggiunge un nuovo cliente al database
     * @param cliente cliente da aggiungere
     *
     */
    public void addCliente(Cliente cliente){
        try {
            PreparedStatement pr = connessione.prepareStatement("INSERT INTO cliente(IDCliente,portafoglio,IDCarrello" +
                    ",nome,indirizzo)");
            pr.setInt(1,cliente.getIDcliente());
            pr.setFloat(2,0.0f);
            pr.setInt(3,cliente.getCarrello().getIDCarrello());
            pr.setString(4,cliente.getNome());
            pr.setString(5,cliente.getIndirizzo());
            pr.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Questo metodo permette di modificare l'id di un locker come indirizzo di consegna.
     * @param IDLocker nuovo id del locker.
     * @param IDOrdine id dell'ordine.
     */
    public void setIDLockerToOrdine(int IDLocker, int IDOrdine){
        try{
            System.out.println(IDLocker+" idlocker dentro DBLocale.setIDLockerToOrdine");
            System.out.println(IDOrdine+" idordine dentro DBLocale.setIDLockerToOrdine");
            PreparedStatement ps = connessione.prepareStatement("UPDATE ordine SET IDLocker =? WHERE IDOrdine =?");
            ps.setInt(1,IDLocker);
            ps.setInt(2,IDOrdine);
            ps.executeUpdate();
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }

    private void aggiungiCarrelloProdotto(List<Prodotto> rit, ResultSet rs) {
        try {
            rit.add(new Prodotto(rs.getInt("IDProdotto"),
                    rs.getString("nome"),
                    rs.getInt("quantitaDisponibile"),
                    rs.getString("categoria"),
                    rs.getFloat("prezzo"),
                    rs.getString("descrizione"),
                    rs.getInt("IDNegozio"),
                    rs.getInt("counterPVenduti"),
                    rs.getInt("IDPromozione")));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void aggiungiCarrello(List<Carrello> rit, ResultSet rs) {
        try {
            int idcarr = rs.getInt("IDCarrello");
            List<Prodotto> list = getProdottiFromCarrello(idcarr);
            Integer i = list.parallelStream().map(Prodotto::getIDNegozio).findFirst().orElse(null);
            int idn;
            if(i==null)idn=0;
            else idn = i;
            rit.add(new Carrello(idcarr,
                    list,
                    rs.getFloat("totale"),
                    getIDCommFromNegozio(idn)));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    private void aggiungiCommerciante(List<CommercianteInterface> rit, ResultSet rs) throws SQLException {
        int idComm = rs.getInt("IDCommerciante");
        String nome = rs.getString("nome");
        String user = rs.getString("user");
        rit.add(new Commerciante(idComm,nome,user));
    }

    private void aggiungiNegozio(List<Negozio> rit, ResultSet rs) throws SQLException {
        int ID = rs.getInt("IDNegozio");
        int IDComm = rs.getInt("IDCommerciante");
        String nome = rs.getString("nomeNegozio");
        rit.add(new Negozio(ID,
                IDComm,
                nome));
    }


    /**
     * Questo metodo restituisce l'id del commerciante dell'ordine.
     * @param IDOrdine id dell'ordine.
     * @return id del commerciante.
     */
    public int getIDCommercianteFromIDOrdine(int IDOrdine){
        PreparedStatement idComm;
        try {
            idComm = connessione.prepareStatement("SELECT IDOrdine,IDCommerciante FROM Ordine");
            ResultSet rs = idComm.executeQuery();
            while(rs.next()){

                if(rs.getInt("IDOrdine")==IDOrdine) return rs.getInt("IDCommerciante");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    /**
     * Questo metodo restituisce tutti i prodotti presenti dentro ad un carrello
     * @param IDCarrello id del carrello da analizzare
     * @return lista di prodotti del carrello
     */
    public List<Prodotto> getProdottiFromCarrello(int IDCarrello){
        List<Prodotto> rit = new ArrayList<>();
        try{
            PreparedStatement ps = connessione.prepareStatement("SELECT *" +
                    "FROM prodotto " +
                    "LEFT JOIN carrelloprodotto " +
                    "ON prodotto.IDProdotto=carrelloprodotto.idP WHERE idC = ?");
            ps.setInt(1,IDCarrello);
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()){
                int quantita = resultSet.getInt("quantita");
                int i= 0;
                int id = resultSet.getInt("IDProdotto");
                String nome = resultSet.getString("nome");
                float prezzo = resultSet.getFloat("prezzo");
                int q = resultSet.getInt("quantitaDisponibile");
                String cat = resultSet.getString("categoria");
                String descr = resultSet.getString("descrizione");
                int idn = resultSet.getInt("IDNegozio");
                int v = resultSet.getInt("counterPVenduti");
                int idProm = resultSet.getInt("IDPromozione");
                while(i<quantita){
                    rit.add(new Prodotto(id,nome,q,cat,prezzo,descr,idn,v,idProm));
                    i++;
                }

            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return rit;
    }



    /**
     * Questo metodo mostra tutti i prodotti di un negozio
     * @param idnegozio id del negozio da analizzare
     * @return lista di prodotti del negozio
     */
    public List<Prodotto> getAllProdottiFromNegozio(int idnegozio){
        List<Prodotto> rit = new ArrayList<>();
        try{
            PreparedStatement ps = connessione.prepareStatement("SELECT * FROM prodotto WHERE IDNegozio = ?");
            ps.setInt(1,idnegozio);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                aggiungiProdotto(rit,rs);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rit;
    }

    private void prodottiNomeList(List<Prodotto> rit, ResultSet resultSet, String nomeProdotto){
        try {
            String rsString = resultSet.getString("nome");
            if(rsString.equals(nomeProdotto) || rsString.contains(nomeProdotto)){
                rit.add(new Prodotto(resultSet.getInt("IDProdotto"),
                        resultSet.getString("nome"),
                        resultSet.getInt("quantitaDisponibile"),
                        resultSet.getString("categoria"),
                        resultSet.getFloat("prezzo"),
                        resultSet.getString("descrizione"),
                        resultSet.getInt("IDNegozio"),
                        resultSet.getInt("counterPVenduti"),
                        resultSet.getInt("IDPromozione")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    /**
     * Questo metodo restituisce l'id del corriere dell'ordine.
     * @param IDOrdine id dell'ordine .
     * @return id del corriere.
     */
    public int getIDCorriereFromIDOrdine(int IDOrdine){
        PreparedStatement idCor;
        try {
            idCor = connessione.prepareStatement("SELECT IDOrdine,IDCorriere FROM Ordine");
            ResultSet rs = idCor.executeQuery();
            while(rs.next()){
                if(rs.getInt("IDOrdine")==IDOrdine) {
                    return rs.getInt("IDCorriere");
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    /**
     * Questo metodo permette di impostare la casa del cliente come l'indirizzo
     * di consegna ad un ordine presente nel database.
     * @param indirizzo indirizzo di consegna.
     * @param idOrdine id dell'ordine.
     */
    public void setIndirizzoToOrdine(String indirizzo, int idOrdine) {
        try{
            PreparedStatement ps = connessione.prepareStatement("UPDATE ordine SET indirizzo =? WHERE IDOrdine =?");
            ps.setString(1,indirizzo);
            ps.setInt(2,idOrdine);
            ps.executeUpdate();
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }


    /**
     * Questo metodo permette di modificare lo stato di un ordine all'interno del database.
     * @param IDOrdine ID dell'ordine di cui modificare lo stato.
     * @param statoOrdine nuovo stato dell'ordine.
     */
    public void setNewStatoOrdine(int IDOrdine, StatoOrdine statoOrdine){
        try{
            PreparedStatement ps = connessione.prepareStatement("UPDATE `c3`.`ordine` SET `statoOrdine` =? WHERE (`IDOrdine` =?)");
            ps.setString(1,statoOrdine.toString());
            ps.setInt(2,IDOrdine);
            ps.executeUpdate();
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }

    private void aggiungiCorrieri(List<CorriereInterface> rit, ResultSet rs) throws SQLException {
        rit.add(new Corriere(rs.getInt("IDCorriere"),
                rs.getString("nome"),
                rs.getString("indirizzo"),
                rs.getString("user"),
                rs.getInt("disponibilita")));
    }

    private void aggiungiClienti(List<ClienteInterface> rit, ResultSet rs) throws SQLException {
        rit.add(new Cliente(rs.getInt("IDCliente"),
                rs.getString("nome"),
                rs.getString("indirizzo"),
                rs.getInt("IDCarrello"),
                rs.getFloat("portafoglio"),
                rs.getString("user")));
    }

    private void aggiungiProdotto(List<Prodotto> rit, ResultSet rs) throws SQLException {
        rit.add(new Prodotto(rs.getInt("IDProdotto"),
                rs.getString("nome"),
                rs.getInt("quantitaDisponibile"),
                rs.getString("categoria"),
                rs.getFloat("prezzo"),
                rs.getString("descrizione"),
                rs.getInt("IDNegozio"),
                rs.getInt("counterPVenduti"),
                rs.getInt("IDPromozione")));
    }

    /**
     * Questo metodo mostra la lista di tutti i locker presenti nel database
     * @return lista dei locker
     */
    public List<LockerInterface> getAllLockers() {
        List<LockerInterface> rit = new ArrayList<>();
        try{
            PreparedStatement ps = connessione.prepareStatement("SELECT * FROM locker");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                aggiungiLocker(rit, rs);
            }
        }catch (SQLException errore){
            errore.printStackTrace();
        }
        return rit;
    }

    private void aggiungiLocker(List<LockerInterface> rit, ResultSet rs) {
        try {
            rit.add(new Locker(rs.getInt("IDLocker"),
                    rs.getString("indirizzo"),
                    rs.getString("user")));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    /**
     * Questo metodo restituisce tutte le premozioni di un negozio.
     * @param idNegozio id del negozio.
     * @return lista di promozioni del negozio passato.
     */
    public List<Promozioni> getAllPromozioni(int idNegozio){
        List<Promozioni> promozioniList = new ArrayList<>();
        try{
            PreparedStatement ps = connessione.prepareStatement("select promozione.IDPromozione,promozione.stato,promozione.sconto,prodotto.IDNegozio from promozione inner join prodotto on promozione.IDPromozione = prodotto.IDPromozione;");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                if(idNegozio==rs.getInt("IDNegozio")){
                    addPromToList(promozioniList,rs);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return promozioniList;
    }

    private void addPromToList(List<Promozioni> rit, ResultSet rs) {

        try {
            int IDP = rs.getInt("IDPromozione");
            rit.add(new Promozioni(IDP,
                    checkStatoPromozione(rs.getInt("stato")),
                    rs.getInt("sconto"),
                    getProdottoFromPromozione(IDP)));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    /**
     * Questo metodo mostra il prodotto della promozione attiva
     * @param IDPromozione id della promozione legata al prodotto
     * @return il prodotto della promozione
     */
    public Prodotto getProdottoFromPromozione(int IDPromozione){
        try{
            PreparedStatement ps = connessione.prepareStatement("SELECT * FROM prodotto WHERE IDPromozione = ?");
            ps.setInt(1,IDPromozione);
            ResultSet rs = ps.executeQuery();
            if(rs.next())return  newProdotto(rs);
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return null;
    }

    private Prodotto newProdotto(ResultSet rs) {
        try {
            return new Prodotto(rs.getInt("IDProdotto"),
                    rs.getString("nome"),
                    rs.getInt("quantitaDisponibile"),
                    rs.getString("categoria"),
                    rs.getFloat("prezzo"),
                    rs.getString("descrizione"),
                    rs.getInt("IDNegozio"),
                    rs.getInt("counterPVenduti"),
                    rs.getInt("IDPromozione"));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("SQLException: " + throwables.getMessage());
            System.out.println("SQLState: " + throwables.getSQLState());
            System.out.println("VendorError: " + throwables.getErrorCode());
        }
        return null;
    }


    private boolean checkStatoPromozione(int stato){
        return stato != 0;
    }

    /**
     * Questo metodo aggiunge una promozione ad un prodotto
     * @param sconto percentuale di sconto dela promozione
     * @param stato stato della promozione (attiva "true" non attiva "false")
     * @param iDpromozione id della promozione da aggiungere
     * @param IDProdotto id del prodotto a cui aggiungere la promozione
     */
    public void addPromozioneToProdotto(int sconto,boolean stato, int iDpromozione, int IDProdotto) {
        try{

            System.out.println(stato+" stato della promozione che verra aggiunta");
            int app;
            PreparedStatement ps = connessione.prepareStatement("INSERT INTO promozione VALUES (?,?,?)");
            ps.setInt(1,iDpromozione);
            if(stato) app = 1;
            else app=0;
            ps.setInt(2,app);
            ps.setInt(3,sconto);
            ps.executeUpdate();
            updateIDPromToProdottoTable(iDpromozione, IDProdotto);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void updateIDPromToProdottoTable(int iDpromozione, int IDProdotto) {
        try{
            PreparedStatement ps = connessione.prepareStatement("UPDATE `c3`.`prodotto` SET `IDPromozione` = ? WHERE (`IDProdotto` = ?)");
            ps.setInt(1,iDpromozione);
            ps.setInt(2,IDProdotto);
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Questo metodo elimina l'id della promozione dal prodotto
     * @param idProdotto id del prodotto
     */
    public void deleteIDPromFromProdotto(int idProdotto) {

        try {
            PreparedStatement ps = connessione.prepareStatement("UPDATE prodotto SET IDPromozione = NULL where IDProdotto = ?");
            System.out.println("id del prodotto "+idProdotto);
            ps.setInt(1,idProdotto);
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    /**
     * Questo metodo elimina una promozione
     * @param IDPromozione id della promozione da eliminare
     */
    public void deletePromozione(int IDPromozione) {
        try{

            PreparedStatement ps = connessione.prepareStatement("DELETE FROM promozione WHERE IDPromozione = ?");
            ps.setInt(1,IDPromozione);
            ps.executeUpdate();
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }


    /**
     * Questo metodo modifica lo stato di una promozione da attiva a non attiva e viceversa
     * @param idPromozione id della promozione da aggiornare
     * @param nuovoStato nuovo stato della promozione
     */
    public void updateStatoPromozione(int idPromozione, boolean nuovoStato){
        try{
            PreparedStatement ps = connessione.prepareStatement("UPDATE promozione SET stato = ? where IDPromozione = ?;");
            ps.setInt(1,fromBoolToTyniInt(nuovoStato));
            ps.setInt(2,idPromozione);
            ps.executeUpdate();
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }

    private int fromBoolToTyniInt(boolean nuovoStato) {
        return nuovoStato ? 1 : 0;
    }

    /**
     * Questo metodo modifica lo percentuale di sconto di una promozione
     * @param idPromozione id della promozione in cui modificare lo sconto
     * @param sconto nuovo sconto della promozione
     */
    public void updateScontoPromozione(int idPromozione, int sconto) {
        try{
            PreparedStatement ps = connessione.prepareStatement("UPDATE promozione SET sconto = ? where IDPromozione = ?;");
            ps.setInt(1,sconto);
            ps.setInt(2,idPromozione);
            ps.executeUpdate();
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }

    /**
     * Questo metodo incrementa la quantità acquistata di un prodotto
     * @param iDprodotto id del prodotto di cui aumentare il contatore
     * @param oldcounter vecchio contatore
     */
    public void updateCounterProdotto(int iDprodotto, int oldcounter) {
        try{
            PreparedStatement ps = connessione.prepareStatement("UPDATE prodotto SET counterPVenduti = ? WHERE IDProdotto = ?;");
            ps.setInt(1,oldcounter+1);
            ps.setInt(2,iDprodotto);
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Questo metodo mostra tutti gli ordini di un negozio
     * @param idNegozio id del negozio da analizzare
     * @return lista di ordini del negozio
     */
    public List<OrdineInterface> getAllOrdiniFromNegozio(int idNegozio) {
        List<OrdineInterface> rit = new ArrayList<>();
        List<OrdineInterface> list = getAllOrdini(0);
        try{
            for(OrdineInterface o : list){
                if(o.getIDNegozio()==idNegozio){
                    rit.add(o);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rit;
    }

    /**
     * Questo metodo aggiorna lo stato di un ordine
     * @param idordine id dell'ordine di cui aggiornare lo stato
     * @param statoOrdine stato dell'ordine da inserire
     */
    public void aggiornaStatoOrdine(int idordine, StatoOrdine statoOrdine) {
        try{
            PreparedStatement ps = connessione.prepareStatement("UPDATE ordine SET  statoOrdine=? WHERE IDOrdine=?");
            ps.setString(1,statoOrdine.toString());
            ps.setInt(2,idordine);
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Questo metodo mostra tutti gli armadietti di un locker
     * @param idLocker id del locker da analizzare
     * @return lista di armadietti
     */
    public List<Armadietto> getArmadiettiFromLocker(int idLocker) {
        List<Armadietto> rit = new ArrayList<>();
        try{
            PreparedStatement ps = connessione.prepareStatement("SELECT * FROM armadietto WHERE idlocker = ?");
            ps.setInt(1,idLocker);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                if(rs.getInt("idOrdine")==0)rit.add(new Armadietto(rs.getInt("IDArmadietto"),null));
                else rit.add(new Armadietto(rs.getInt("IDArmadietto"),getOrdine(rs.getInt("idOrdine")).get(0)));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rit;
    }

    /**
     * Questo metodo elimina un ordine da un armadietto
     * @param IDOrdine id dell'ordine da eliminare
     */
    public void deleteOrdineFromArmadietto(int IDOrdine) {
        try{
            PreparedStatement ps = connessione.prepareStatement("UPDATE armadietto SET idOrdine=?  WHERE idOrdine=?");
            ps.setNull(1,Types.INTEGER);
            ps.setInt(2,IDOrdine);
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Questo metodo aggiunge l'ordine dentro l'armadietto libero
     * @param idarmadietto id dell'armadietto libero
     * @param idOrdine id dell'ordine da aggiungere all'armadietto
     */
    public void addOrdineToArmadietto(int idarmadietto, int idOrdine) {
        try{
            PreparedStatement ps = connessione.prepareStatement("UPDATE armadietto SET idOrdine=? WHERE IDArmadietto=?");
            ps.setInt(1,idOrdine);
            ps.setInt(2,idarmadietto);
            ps.executeUpdate();
            System.out.println("dentro DBLOCALE ho aggiunto l'id dell'ordine all'armadietto...");
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Questo metodo controlla l'avvenuto login
     * @param user nome utente per il login
     * @param pass password dell'utente
     * @param ruolo ruolo dell'utente (cliente,commerciate,corriere,locker)
     * @return il ruolo
     */
    public String checklogin(String user, String pass, String ruolo){
        return switch (ruolo) {
            case "CLIENTE" -> checkLoginCliente(user, pass);
            case "COMMERCIANTE" -> checkLoginCommerciante(user, pass);
            case "LOCKER" -> checkLoginLocker(user, pass);
            case "CORRIERE" -> checkLoginCorriere(user, pass);
            default -> "";
        };
    }

    private String checkLoginCorriere(String user, String pass) {
        try {
            PreparedStatement ps = connessione.prepareStatement("SELECT user,password FROM corriere WHERE user=? AND password=?");
            ps.setString(1,user);
            ps.setString(2,pass);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) return "CORRIERE";

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "";
    }

    private String checkLoginLocker(String user, String pass) {
        try {
            PreparedStatement ps = connessione.prepareStatement("SELECT user,password FROM locker WHERE user=? AND password=?");
            ps.setString(1,user);
            ps.setString(2,pass);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) return "LOCKER";
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "";
    }

    private String checkLoginCommerciante(String user, String pass) {
        try {
            PreparedStatement ps = connessione.prepareStatement("SELECT user,password FROM commerciante WHERE user=? AND password=?");
            ps.setString(1,user);
            ps.setString(2,pass);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) return "COMMERCIANTE";
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "";
    }

    private String checkLoginCliente(String user, String pass) {
        try {
            PreparedStatement ps = connessione.prepareStatement("SELECT user,password FROM cliente WHERE user=? AND password=?");
            ps.setString(1,user);
            ps.setString(2,pass);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) return "CLIENTE";
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "";
    }

    /**
     * Questo metodo aggiunge un prodotto al carrello 
     * @param idcarrello id del carrello dove aggiungere il prodotto
     * @param prodotto prodotto da aggiungere.
     * @param quantita la quantita che si vuole aggiungere.
     */
    public void addProdottoToCarrello(int idcarrello, Prodotto prodotto, int quantita) {
        getAllCarrello().forEach(x->System.out.println(x.getIDCarrello()+ " id carrello dentro add prodotto to carrello"));
        Carrello c = getAllCarrello().stream().filter(z->z.getIDCarrello()==idcarrello).findFirst().orElse(null);
        if(Objects.requireNonNull(c).getProdotti().contains(prodotto))removeProdottoToCarrello(idcarrello, prodotto.getIDprodotto(), quantita);
        else{
            try{
                PreparedStatement ps = connessione.prepareStatement("INSERT INTO carrelloprodotto(idC,idP,quantita) VALUES (?,?,?)");
                ps.setInt(1,idcarrello);
                ps.setInt(2,prodotto.getIDprodotto());
                ps.setInt(3,quantita);
                ps.executeUpdate();
                aggiornaTotaleCarrello(idcarrello);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    private void aggiornaTotaleCarrello(int idcarrello) {

        try{
            PreparedStatement ps = connessione.prepareStatement("SELECT * FROM carrelloprodotto WHERE idC = ?");
            ps.setInt(1,idcarrello);
            ResultSet rs = ps.executeQuery();
            float newTotale = 0;
            while(rs.next()){
                int idP = rs.getInt("idP");
                float prezzo= getAllProdotto(idP).getPrezzo();
                int q = rs.getInt("quantita");
                newTotale = newTotale+(prezzo*q);
            }
            ps = connessione.prepareStatement("UPDATE carrello SET totale = ? WHERE IDCarrello = ?");
            ps.setFloat(1,newTotale);
            ps.setInt(2,idcarrello);
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private Prodotto getAllProdotto(int idProdotto) {
        try{
            PreparedStatement ps = connessione.prepareStatement("SELECT * FROM prodotto WHERE IDProdotto = ?");
            ps.setInt(1,idProdotto);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return new Prodotto(rs.getInt("IDProdotto"),
                        rs.getString("nome"),
                        rs.getInt("quantitaDisponibile"),
                        rs.getString("categoria"),
                        rs.getFloat("prezzo"),
                        rs.getString("descrizione"),
                        rs.getInt("IDNegozio"),
                        rs.getInt("counterPVenduti"),
                        rs.getInt("IDPromozione"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    /**
     * Questo metodo rimuove un prodotto da un carrello
     * @param idcarrello id del carrello in cui rimuovere il prodotto
     * @param prodotto id del prodotto da rimuovere
     * @param quantita la quantita da togliere.
     */
    public void removeProdottoToCarrello(int idcarrello, int prodotto, int quantita) {
        try{
            PreparedStatement ps;
            if(quantita==0){
                ps = connessione.prepareStatement("DELETE FROM carrelloprodotto WHERE idC=? AND idP = ?");
                ps.setInt(1,idcarrello);
                ps.setInt(2,prodotto);
            }else{
                ps = connessione.prepareStatement("UPDATE carrelloprodotto SET quantita = ? WHERE idC=? AND idP = ?");
                ps.setInt(1,quantita);
                ps.setInt(2,idcarrello);
                ps.setInt(3,prodotto);
            }
            ps.executeUpdate();
            aggiornaTotaleCarrello(idcarrello);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Questo metodo sotrae il totale del carrello dal portafoglio del cliente
     * @param iDcliente id del cliente a cui sottrarre il totale
     * @param totale totale da essere sottratto
     */
    public void sottraiDenaro(int iDcliente, float totale) {
        try{
            PreparedStatement ps = connessione.prepareStatement("SELECT portafoglio FROM cliente WHERE IDCliente = ?");
            ps.setInt(1,iDcliente);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                float app = rs.getFloat("portafoglio");
                float newPort = app-totale;
                ps = connessione.prepareStatement("UPDATE cliente SET portafoglio = ? WHERE IDCliente = ?");
                ps.setFloat(1,newPort);
                ps.setInt(2,iDcliente);
                ps.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Questo metodo aggiunge il totale pagato dal cliente al portafoglio del commerciante
     * @param idcomm id del commerciante a cui aggiungere il totale
     * @param totale totale da aggiungere al portafoglio del commerciante
     */
    public void aggiungiDenaro(int idcomm, float totale) {
        try{
            PreparedStatement ps = connessione.prepareStatement("SELECT portafoglio FROM commerciante WHERE IDCommerciante = ?");
            ps.setInt(1,idcomm);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                float app = rs.getFloat("portafoglio");
                System.out.println(app+" old portafoglio dentro DBLocale.aggiungidenaro");
                float newPort = app+totale;
                System.out.println(newPort+" nuovo portafoglio dentro DBLocale.aggiungidenaro");
                ps = connessione.prepareStatement("UPDATE commerciante SET portafoglio = ? WHERE IDCommerciante = ?");
                ps.setFloat(1,newPort);
                ps.setInt(2,idcomm);
                ps.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Questo metodo mostra il portafoglio del cliente
     * @param iDcliente id del cliente da cui prendere il portafoglio
     * @return portafoglio del cliente
     */
    public float getPortaglioFromIDCliente(int iDcliente) {
        try{
            PreparedStatement ps = connessione.prepareStatement("SELECT portafoglio FROM cliente WHERE IDCliente = ?");
            ps.setInt(1,iDcliente);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return rs.getFloat("portafoglio");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    /**
     * Questo metodo mostra il portafoglio del commerciante
     * @param idCommerciante id del commerciante da cui prendere il portafoglio
     * @return portafoglio del commerciante
     */
    public float getPortaglioFromIDComm(int idCommerciante) {
        try{
            PreparedStatement ps = connessione.prepareStatement("SELECT portafoglio FROM commerciante WHERE IDCommerciante = ?");
            ps.setInt(1,idCommerciante);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return rs.getFloat("portafoglio");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }


    /**
     * Questo metodo serve per aggiungere credito al cliente selezionato tramite l'id.
     * @param idCliente id del cliente a cui aumentare il proprio portafoglio.
     */
    public void addCreditoToCliente(int idCliente, float newPortafoglio) {
        try{
            PreparedStatement ps = connessione.prepareStatement("UPDATE cliente SET portafoglio = ? WHERE IDCliente = ?");
            ps.setFloat(1,newPortafoglio);
            ps.setInt(2,idCliente);
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    /**
     *
     * @param idOrdine
     * @return
     */
    public List<Prodotto> getProdottoFromIDOrdine(int idOrdine) {
        List<Prodotto> rit = new ArrayList<>();
        try{
            PreparedStatement ps = connessione.prepareStatement("SELECT * FROM ordineprodotto WHERE idO = ?");
            ps.setInt(1,idOrdine);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int i = 0;
                int q = rs.getInt("quantita");
                while(i<q){
                    rit.add(getAllProdotto(rs.getInt("idP")));
                    i++;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        return rit;
    }


    /**
     *
     * @return
     */
    public List<Prodotto> getAllProdotti(){
        List<Prodotto> rit = new ArrayList<>();
        try{
            PreparedStatement ps = connessione.prepareStatement("SELECT * FROM prodotto");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                aggiungiProdotto(rit,rs);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rit;
    }


    /**
     *
     * @param idProdotto
     * @param nuovaquantita
     */
    public void updateQuantitaDisponibile(int idProdotto, int nuovaquantita) {
        try{
            PreparedStatement ps = connessione.prepareStatement("UPDATE prodotto SET quantitaDisponibile = ? WHERE IDProdotto = ?");
            ps.setInt(1,nuovaquantita);
            ps.setInt(2,idProdotto);
            ps.executeUpdate();
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     *
     * @param idCorriere
     * @param nuovaDisponibilita
     */
    public void updateDisponibilitaCorriere(int idCorriere, boolean nuovaDisponibilita){
        try{
            PreparedStatement ps = connessione.prepareStatement("UPDATE corriere SET disponibilita = ? WHERE IDCorriere = ?");
            if(nuovaDisponibilita)ps.setInt(1,1);
            else ps.setInt(1,0);
            ps.setInt(2,idCorriere);
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     *
     * @return
     */
    public List<Promozioni> getAllPromozioni() {
        List<Promozioni> rit = new ArrayList<>();
        try{
            PreparedStatement ps = connessione.prepareStatement("SELECT * FROM promozione");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                addPromToList(rit,rs);
            }
            return rit;
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return rit;
    }
}
