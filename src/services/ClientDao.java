package services;

import javafx.scene.chart.ScatterChart;
import modeles.Client;
import modeles.Credit;
import modeles.Produits;
import utilitaires.DatabaseHelper;

import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ClientDao implements IClient {
    @Override
    public String genererQunt() {
        DatabaseHelper db = DatabaseHelper.getInstance();
        int id = db.lastIdFor("client");
        String qut = "Numero: " + new DecimalFormat("00").format(id);
        return qut;
    }

    @Override
    public boolean addClient(Client clt) {
        DatabaseHelper db = DatabaseHelper.getInstance();
        String sql = "INSERT INTO client VALUES(" + db.lastIdFor("client") + ", ?, ?, ?, ?, " + clt.getPrix() + ", " + clt.getCredit().getId() + ") ";
        try {
            db.prepareStatement(sql);
            Object[] tab = {clt.getNumero(), clt.getPrenom(), clt.getNom(), clt.getDateachete()};
            db.bindParams(tab);
            db.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println(e);
            return false;
        }

    }

    public List<Client> getAllClt() {
        String sql = "SELECT * FROM client";
        DatabaseHelper bd = DatabaseHelper.getInstance();
        List<Client> listclt = new ArrayList<>();
        try {
            bd.prepareStatement(sql);
            ResultSet rs = bd.executeQuery();
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            while (rs.next()) {
                Client ct = new Client();
                ct.setId(rs.getInt(1));
                ct.setNumero(rs.getString(2));
                ct.setPrenom(rs.getString(3));
                ct.setNom(rs.getString(4));
                ct.setDateachete(LocalDate.parse(rs.getString(5), df));
                ct.setPrix(rs.getInt(6));


                sql = "SELECT * FROM credit WHERE id =  " + rs.getInt(7);

                bd.prepareStatement(sql);
                ResultSet rs1 = bd.executeQuery();
                Credit Cre = new Credit();
                int idPord = 0;

                while (rs1.next()) {

                    Cre.setId(rs1.getInt(1));
                    Cre.setType(rs1.getString(2));
                    idPord = rs1.getInt(3);
                }

                sql = "SELECT * FROM produits WHERE id =  " + idPord;

                bd.prepareStatement(sql);
                ResultSet rs2 = bd.executeQuery();
                Produits prt = new Produits();

                while (rs2.next()) {

                    prt.setId(rs2.getInt(1));
                    prt.setType(rs2.getString(2));
                }


                ct.setCredit(Cre);
                ct.setPrt(prt);
                listclt.add(ct);


            }
            return listclt;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

    }

    public boolean exist(Client clt)
    {
        String sql = "SELECT * FROM client WHERE numero = ?";
        DatabaseHelper bd = DatabaseHelper.getInstance();
        try {
            bd.prepareStatement(sql);
            Object[] tab = {clt.getNumero()};
            bd.bindParams(tab);
            ResultSet rs = bd.executeQuery();
            while (rs.next())
            {
                return true;
            }
            return false;
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return false;

    }

    public boolean updateClient(Client clt)
    {
        DatabaseHelper db = DatabaseHelper.getInstance();
        String sql = "UPDATE client SET nom = ?,prenom = ?,date = ?,prix = ?,idCredit = "+clt.getCredit().getId()+" WHERE numero = ?";
        try {
            db.prepareStatement(sql);
            Object[] tab = {clt.getNom(), clt.getPrenom(), clt.getDateachete(), clt.getPrix(), clt.getNumero()};
            db.bindParams(tab);
            db.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean deleteClt(String numero)
    {
        DatabaseHelper db = DatabaseHelper.getInstance();
        String sql = "DELETE FROM client WHERE numero = ?";
        try {
            db.prepareStatement(sql);
            Object[] tab = {numero};
            db.bindParams(tab);
            db.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println(e);
            return false;
        }

    }

}
