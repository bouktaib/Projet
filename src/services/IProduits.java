package services;

import modeles.Produits;

import java.util.List;

public interface IProduits {

    public List<Produits> findAll() throws Exception;
}
