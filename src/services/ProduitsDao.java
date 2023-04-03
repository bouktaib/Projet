package services;

import modeles.Produits;
import modeles.Credit;
import utilitaires.DatabaseHelper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProduitsDao implements IProduits {

    @Override
    public List<Produits> findAll() throws Exception {
        String sql = "SELECT * FROM produits";

        DatabaseHelper db = DatabaseHelper.getInstance();
        db.prepareStatement(sql);

        ResultSet rs = db.executeQuery();

        List<Produits> listeProduits = new ArrayList<>();

        while(rs.next()){
            Produits d = new Produits();
            d.setId(rs.getInt(1));
            d.setType(rs.getString(2));

            sql = "SELECT * FROM credit WHERE idPord =  " + d.getId();
            db.prepareStatement(sql);
            ResultSet rs1 = db.executeQuery();
            List<Credit> listeCredit = new ArrayList<>();

            while (rs1.next()){
                Credit s = new Credit();
                s.setId(rs1.getInt(1));
                s.setType(rs1.getString(2));
                listeCredit.add(s);
            }
            rs1.close();
            d.setListeCredit(listeCredit);
            listeProduits.add(d);
        }

        return listeProduits;
    }
}
