package it.unicam.cs.ids.c3.model;

import it.unicam.cs.ids.c3.model.Clienti.Carrello;
import it.unicam.cs.ids.c3.model.Clienti.Cliente;
import it.unicam.cs.ids.c3.model.Clienti.ClienteInterface;
import it.unicam.cs.ids.c3.model.Corriere.Corriere;
import it.unicam.cs.ids.c3.model.Corriere.CorriereInterface;
import it.unicam.cs.ids.c3.model.Esercente.*;
import it.unicam.cs.ids.c3.model.Lockers.Armadietto;
import it.unicam.cs.ids.c3.model.Lockers.Locker;
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
    private ResultSet resultSet;

    private DBLocale() throws ClassNotFoundException {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connessione = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?"
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
     * Questo metodo serve per eseguire una query al database e restituisce il risulato dell'interrogazione.
     * @param query la query da eseguire.
     * @return il resultset contenente i dati richiesti dalla query.
     */
    public ResultSet query(String query){
        try{
            Statement st = connessione.createStatement();
            resultSet = st.executeQuery(query);
        }catch (SQLException errore){
            System.out.println("SQLException: " + errore.getMessage());
            System.out.println("SQLState: " + errore.getSQLState());
            System.out.println("VendorError: " + errore.getErrorCode());
        }
        return resultSet;
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

    public List<OrdineInterface> getAllOrdini(){
        List<OrdineInterface> rit = new ArrayList<>();
        try{
            PreparedStatement ps = connessione.prepareStatement("SELECT * FROM ordine");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                aggiungiOrdini(rit, rs);
            }
        }catch (SQLException | ClassNotFoundException errore){
            errore.printStackTrace();
        }
        return rit;
    }

    public void setCorriereToOrdine(int idCorriere, int IDordine){
        try{
            PreparedStatement ps = connessione.prepareStatement("UPDATE `mydb`.`ordine` SET `IDCorriere` =? WHERE (`IDOrdine` =?)");
            ps.setInt(1,idCorriere);
            ps.setInt(2,IDordine);
            ps.executeUpdate();
        }catch (SQLException errore){
            errore.printStackTrace();
        }
    }



    private void aggiungiOrdini(List<OrdineInterface> rit, ResultSet rs) throws SQLException, ClassNotFoundException {
        StatoOrdine s = StatoOrdine.valueOf(rs.getString("StatoOrdine"));
        String indirizzo = rs.getString("indirizzo");
        int idlocker = rs.getInt("IDLocker");
        if((Objects.isNull(indirizzo) ||indirizzo.equals("")) && idlocker==0){
            ordineSenzaDestinazione(rit, rs, s);
        }else if(idlocker!=0 && Objects.isNull(indirizzo)){
            ordineConDestLocker(rit,rs,s);
        }else{
            rit.add(new Ordine(rs.getInt("IDOrdine"),
                    rs.getInt("IDCLiente"),
                    s,
                    rs.getString("indirizzo")));
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

            PreparedStatement query = connessione.prepareCall("INSERT into ordine(IDOrdine, IDCorriere,IDCarrello,IDCliente," +
                    "IDCommerciante,IDLocker,statoOrdine,indirizzo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            query.setInt(1,ordine.getIDOrdine());
            query.setInt(2,ordine.getIDCorriere());
            query.setInt(3,ordine.getCarrello().getIDCarrello());
            query.setInt(4,ordine.getIDCliente());
            query.setInt(5,ordine.getIDCommerciante());
            query.setNull(6,ordine.getIDLocker());
              query.setString(7,ordine.getStatoOrdine().toString());
            query.setString(8, ordine.getDestinazione());
            int risultato = query.executeUpdate();
        }catch (SQLException errore){
            errore.printStackTrace();
        }
    }


    /**
     * Questo metodo restituisce una lista di tutti i carrelli presenti nel database.
     * @return lista di carrelli presenti nel database.
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

    public Carrello getCarrelloFromID(int ID){
        Carrello rit = null;
        try{
            PreparedStatement ps = connessione.prepareStatement("SELECT IDCarrello FROM carrello WHERE ('IDCarrello' = ?)");
            ps.setInt(1,ID);
            ResultSet rs = ps.executeQuery();
            rs.next();
            rit = new Carrello(rs.getInt("IDCarrello"));
        }catch (SQLException | ClassNotFoundException errore){
            errore.printStackTrace();
        }
        return rit;
    }


    /**
     *
     * @param idNegozio
     * @return
     */
    public int getIDCommFromNegozio(int idNegozio){
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
     *
     * @param prodotto
     */
    public void addProdotto(Prodotto prodotto){
        try{
            PreparedStatement query = connessione.prepareCall("INSERT INTO prodotto(IDProdotto,quantitaDisponibile,nome" +
                    "descrizione,IDPromozione,categoria,prezzo)");
            query.setInt(1,prodotto.getIDprodotto());
            query.setInt(2,prodotto.getQuantita());
            query.setString(3, prodotto.getNome());
            query.setString(4, prodotto.getDescrizione());
            query.setInt(5,prodotto.getPromozione().getIDpromozione());
            query.setString(6,prodotto.getCategoria());
            query.setFloat(7,prodotto.getPrezzo());
            int risultato = query.executeUpdate();
        }catch (SQLException errore){
            errore.printStackTrace();
        }
    }



    /**
     * Questo metodo aggiunge
     * @param cliente
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
            int risultato = pr.executeUpdate();
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
            PreparedStatement ps = connessione.prepareStatement("UPDATE `mydb`.`ordine` SET `IDLocker` =? WHERE (`IDOrdine` =?)");
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
                    rs.getInt("IDNegozio")));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void aggiungiCarrello(List<Carrello> rit, ResultSet rs) {
        try {
            rit.add(new Carrello(rs.getInt("IDCarrello")));
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }


    private void aggiungiCommerciante(List<CommercianteInterface> rit, ResultSet rs) throws SQLException {
        rit.add(new Commerciante(rs.getInt("IDCommerciante")));
    }

    private void aggiungiNegozio(List<Negozio> rit, ResultSet rs) throws SQLException {
        rit.add(new Negozio(rs.getInt("IDNegozio"),
                rs.getInt("IDCommerciante"),
                rs.getString("nomeNegozio")));
    }

    private void ordineConDestLocker(List<OrdineInterface> rit, ResultSet rs, StatoOrdine s) throws SQLException, ClassNotFoundException {

        rit.add(new Ordine(rs.getInt("IDOrdine"),
                rs.getInt("IDCLiente"),
                s,
                rs.getInt("IDLocker")));
    }

    private void ordineSenzaDestinazione(List<OrdineInterface> rit, ResultSet rs, StatoOrdine statoOrdine) throws SQLException, ClassNotFoundException {
        rit.add(new Ordine(rs.getInt("IDOrdine"),
                rs.getInt("IDCliente"),
                StatoOrdine.valueOf(rs.getString("statoOrdine"))));
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

    public List<Prodotto> getProdottiFromCarrello(int IDCarrello){
        List<Prodotto> rit = new ArrayList<>();
        try{
            PreparedStatement ps = connessione.prepareStatement("SELECT *" +
                    "FROM prodotto " +
                    "LEFT JOIN carrelloprodotto " +
                    "ON prodotto.IDProdotto=carrelloprodotto.idP WHERE idC = ?");
            ps.setInt(1,IDCarrello);
            resultSet = ps.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt("IDProdotto");
                String nome = resultSet.getString("nome");
                float prezzo = resultSet.getFloat("prezzo");
                int q = resultSet.getInt("quantitaDisponibile");
                String cat = resultSet.getString("categoria");
                String descr = resultSet.getString("descrizione");
                int idn = resultSet.getInt("IDNegozio");
                rit.add(new Prodotto(id,nome,q,cat,prezzo,descr,idn));
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return rit;
    }


    public List<Prodotto> getAllProdottiFromNegozio(int idnegozio, String nomeProdotto){
        List<Prodotto> rit = new ArrayList<>();
        try {
            PreparedStatement ps = connessione.prepareStatement("SELECT * FROM prodotto WHERE IDNegozio = ?");
            ps.setInt(1,idnegozio);
            resultSet = ps.executeQuery();
            while(resultSet.next()){
                prodottiNomeList(rit,resultSet,nomeProdotto);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return rit;

    }

    private void prodottiNomeList(List<Prodotto> rit, ResultSet resultSet, String nomeProdotto){
        try {
            String rsString = resultSet.getString("nome");
            if(rsString.equals(nomeProdotto)){
                rit.add(new Prodotto(resultSet.getInt("IDProdotto"),
                        resultSet.getString("nome"),
                        resultSet.getInt("quantitaDisponibile"),
                        resultSet.getString("categoria"),
                        resultSet.getFloat("prezzo"),
                        resultSet.getString("descrizione"),
                        resultSet.getInt("IDNegozio")));
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
                if(rs.getInt("IDOrdine")==IDOrdine) return rs.getInt("IDCorriere");
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
            PreparedStatement ps = connessione.prepareStatement("UPDATE `mydb`.`ordine` SET `indirizzo` =? WHERE (`IDOrdine` =?)");
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
            PreparedStatement ps = connessione.prepareStatement("UPDATE `mydb`.`ordine` SET `statoOrdine` =? WHERE (`IDOrdine` =?)");
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
                rs.getString("indirizzo")));
    }

    private void aggiungiClienti(List<ClienteInterface> rit, ResultSet rs) throws SQLException {
        rit.add(new Cliente(rs.getInt("IDCliente"),
                rs.getString("nome"),
                rs.getString("indirizzo"),
                rs.getInt("IDCarrello")));
    }


    public List<Prodotto> getAllProdotti() {
        List<Prodotto> rit = new ArrayList<>();
        try{
            PreparedStatement ps = connessione.prepareStatement("SELECT * FROM prodotto");
            resultSet = ps.executeQuery();
            while(resultSet.next()){
                aggiungiProdotto(rit, resultSet);
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }



        return rit;
    }

    private void aggiungiProdotto(List<Prodotto> rit, ResultSet rs) throws SQLException {
        rit.add(new Prodotto(rs.getInt("IDProdotto"),
                rs.getString("nome"),
                rs.getInt("quantitaDisponibile"),
                rs.getString("categoria"),
                rs.getFloat("prezzo"),
                rs.getString("descrizione"),
                rs.getInt("IDNegozio")));

    }

    public List<Locker> getAllLockers() {
        List<Locker> rit = new ArrayList<>();
        try{
            PreparedStatement ps = connessione.prepareStatement("SELECT * FROM ordine");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                aggiungiLocker(rit, rs);
            }
        }catch (SQLException errore){
            errore.printStackTrace();
        }
        return rit;
    }

    private void aggiungiLocker(List<Locker> rit, ResultSet rs) {
        try {
            rit.add(new Locker(setOfArmadietti(rs.getInt("IDLocker")),rs.getInt("IDLocker"),rs.getString("indirizzo")));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    private Set<Armadietto> setOfArmadietti(int id){
        List<Armadietto> ris = new ArrayList<>();
        try{
            ResultSet rs;
            PreparedStatement ps = connessione.prepareStatement("SELECT * FROM armadietto WHERE idlocker = ?");
            ps.setInt(1,id);
            rs = ps.executeQuery();
            while(rs.next()){
                ris.add(new Armadietto(rs.getInt("IDArmadietto")));
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return new HashSet<>(ris);
    }


    public List<Promozioni> getAllPromozioni(){
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
        return null;
    }

    private void addPromToList(List<Promozioni> rit, ResultSet rs) {
        try {
            int IDP = rs.getInt("IDPromozione");
            System.out.println(" idpromozioneTrovata " + IDP);
            rit.add(new Promozioni(IDP,
                    checkStatoPromozione(rs.getInt("stato")),
                    rs.getInt("sconto"),
                    getProdottoFromPromozione(IDP)));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


    public Prodotto getProdottoFromPromozione(int IDPromozione){
        try{
            PreparedStatement ps = connessione.prepareStatement("SELECT * FROM prodotto WHERE IDPromozione = ?");
            ps.setInt(1,IDPromozione);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                return newProdotto(rs);
            }

        }catch (SQLException throwables){

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
                    rs.getInt("IDNegozio"));
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

    public void addPromozioneToProdotto(int sconto,boolean stato, int iDpromozione) {
        try{
            int app;
            PreparedStatement ps = connessione.prepareStatement("INSERT INTO promozione VALUES (?,?,?)");
            ps.setInt(1,iDpromozione);
            if(stato) app = 1;
            else app=0;
            ps.setInt(2,app);
            ps.setInt(3,sconto);
            int risultato = ps.executeUpdate();
            updateIDPromToProdottoTable(iDpromozione, getProdottoFromPromozione(iDpromozione).getIDprodotto());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void updateIDPromToProdottoTable(int iDpromozione, int IDProdotto) {
        try{
            PreparedStatement ps = connessione.prepareStatement("UPDATE `mydb`.`prodotto` SET `IDPromozione` = ? WHERE (`IDProdotto` = ?)");
            ps.setInt(1,iDpromozione);
            ps.setInt(2,IDProdotto);
            int risultato = ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
