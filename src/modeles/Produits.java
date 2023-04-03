package modeles;

import java.util.ArrayList;
import java.util.List;

public class Produits {

    private int id;
    private String type;
    private List<Credit> listeCredit;

    public Produits() {
        listeCredit = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Credit> getListeCredit() {
        return listeCredit;
    }

    public void setListeCredit(List<Credit> listeCredit) {
        this.listeCredit = listeCredit;
    }

    @Override
    public String toString() {
        return type;
    }
}
