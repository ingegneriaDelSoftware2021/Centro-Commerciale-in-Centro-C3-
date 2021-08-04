package it.unicam.cs.ids.c3.model;

import it.unicam.cs.ids.c3.model.Clienti.Cliente;
import it.unicam.cs.ids.c3.model.Clienti.ClienteInterface;
import it.unicam.cs.ids.c3.model.Corriere.Corriere;
import it.unicam.cs.ids.c3.model.Corriere.CorriereInterface;
import it.unicam.cs.ids.c3.model.Esercente.Commerciante;
import it.unicam.cs.ids.c3.model.Esercente.CommercianteInterface;
import it.unicam.cs.ids.c3.model.Esercente.Negozio;
import it.unicam.cs.ids.c3.model.Ordini.Ordine;
import it.unicam.cs.ids.c3.model.Ordini.OrdineInterface;
import it.unicam.cs.ids.c3.model.Ordini.StatoOrdine;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    private DBLocale(){
        try{
            connessione = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?"+ "user=root&password=pippo");
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
    public static DBLocale getInstance(){
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
     * @return
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



    public List<OrdineInterface> getAllOrdini(){
        List<OrdineInterface> rit = new ArrayList<>();
        try{
            PreparedStatement ps = connessione.prepareStatement("SELECT * FROM ordine");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                aggiungiOrdini(rit, rs);
            }
        }catch (SQLException errore){
            errore.printStackTrace();
        }
        return rit;
    }

    private void aggiungiOrdini(List<OrdineInterface> rit, ResultSet rs) throws SQLException {
        StatoOrdine s = StatoOrdine.valueOf(rs.getString("StatoOrdine"));
        if(rs.getString("indirizzo").equals("") && rs.getInt("IDLocker")==-1){
            ordineSenzaDestinazione(rit, rs, s);
        }else if(rs.getInt("IDLocker")!=-1){
            ordineConDestLocker(rit,rs,s);
        }else{
            rit.add(new Ordine(rs.getInt("IDOrdine"),
                    s,
                    rs.getString("indirizzo")));
        }
    }


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

    private void aggiungiCommerciante(List<CommercianteInterface> rit, ResultSet rs) throws SQLException {
        rit.add(new Commerciante(rs.getInt("IDCommerciante")));
    }

    private void aggiungiNegozio(List<Negozio> rit, ResultSet rs) throws SQLException {
        rit.add(new Negozio(rs.getInt("IDNegozio"),
                rs.getInt("IDCommerciante"),
                rs.getString("nomeNegozio")));
    }

    private void ordineConDestLocker(List<OrdineInterface> rit, ResultSet rs, StatoOrdine s) throws SQLException {
        rit.add(new Ordine(rs.getInt("IDOrdine"),
                s,
                rs.getInt("IDLocker")));
    }

    private void ordineSenzaDestinazione(List<OrdineInterface> rit, ResultSet rs, StatoOrdine statoOrdine) throws SQLException {
        rit.add(new Ordine(rs.getInt("IDOrdine"),
                statoOrdine));
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




}
