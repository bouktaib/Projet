package modeles;

import java.time.LocalDate;

public class Client {

    private int id;
    private String numero;
    private String nom;
    private String prenom;
    private LocalDate dateachete;
    private String adresse;
    private int prix;
    private Credit credit;
    private Produits prt;

    public Client(){
        credit = new Credit();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public LocalDate getDateachete() {
        return dateachete;
    }

    public void setDateachete(LocalDate dateachete) {
        this.dateachete = dateachete;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public Credit getCredit() {return credit;}

    public void setCredit(Credit credit) {
        this.credit = credit;
    }

    public Produits getPrt() {
        return prt;
    }

    public void setPrt(Produits prt) {
        this.prt = prt;
    }
}
