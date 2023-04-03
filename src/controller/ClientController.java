package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import modeles.Client;
import modeles.Credit;
import modeles.Produits;
import services.ClientDao;
import services.IClient;
import services.IProduits;
import services.ProduitsDao;
import utilitaires.Utils;

import javax.swing.text.DateFormatter;
import java.net.URL;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import static java.util.Collections.addAll;

public class ClientController implements Initializable {

    @FXML
    private TextField nomTfd;

    @FXML
    private TextField prenomTfd;

    @FXML
    private TextField numeroTfd;

    @FXML
    private TextField dateTfd;

    @FXML
    private TextField prixTfd;

    @FXML
    private ChoiceBox<Produits> produitsCbx;

    @FXML
    private ChoiceBox<Credit> creditCbx;

    @FXML
    private Button btnEnregistrer;

    @FXML
    private Button btnModifier;

    @FXML
    private Button btnSupprimer;

    @FXML
    private Button btnVider;


    @FXML
    private TableView<Client> tableClient;

    private TableColumn<Client, String> colCredit;

    @FXML
    private TableColumn<Client, String> colDate;

    @FXML
    private TableColumn<Client, String> colNom;

    @FXML
    private TableColumn<Client, String> colNumero;

    @FXML
    private TableColumn<Client, String> colPrenom;

    @FXML
    private TableColumn<Client, String> colPrix;

    @FXML
    private TableColumn<Client, Integer> colProduits;



    IClient IC = new ClientDao();

    @FXML
    void saveHandler(ActionEvent event) throws Exception {

//        Utils.showMessage("Gestion des client","Ajout client","ok");
        String numr = numeroTfd.getText().trim();
        String nom = nomTfd.getText().trim();
        String pnom = prenomTfd.getText().trim();
        LocalDate dat = null;
        int prix = 0;
        boolean datOk = false;

        if(numr.equals("") || nom.equals("") || pnom.equals("") || dateTfd.getText().trim().equals("")
                || prixTfd.getText().trim().equals("") || produitsCbx.getSelectionModel().getSelectedIndex() == -1
                || creditCbx.getSelectionModel().getSelectedIndex() == -1){
            Utils.showMessage("Gestion des client","Ajout client","VEUILLEZ REMPLIR TOUS LES CHAMPS !!");
        }
        else {
            try{
                DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                dat = LocalDate.parse(dateTfd.getText().trim(),df);
                datOk = true;}

            catch (Exception e){
                datOk =false;
            }

            if(datOk == false){
                Utils.showMessage("Gestion des client","Ajout client","DATE INVALIDE (YYYY-MM-DD)!");
            }
            else{

                Client clt = new Client();
                clt.setNumero(numr);
                clt.setNom(nom);
                clt.setPrenom(pnom);
                clt.setPrix(prix);
                clt.setDateachete(dat);
                clt.setCredit(creditCbx.getValue());
                if(IC.exist(clt))
                {
                    if(IC.updateClient(clt))
                    {
                        Utils.showMessage("Gestion des client", "Modification client", "SUCCEE!");
                        viderChamps();
                        actualiserTab();
                    }
                    else
                    {
                        Utils.showMessage("Gestion des client","Modification client","ECHEC!");

                    }
                }
                else{

                if(IC.addClient(clt)){
                    Utils.showMessage("Gestion des client","Ajout client","SUCCEE!");
                    viderChamps();
                    actualiserTab();

                }
                else{
                    Utils.showMessage("Gestion des client","Ajout client","ECHEC!");

                }
                }
            }

        }
    }

    @FXML
    void modifierClt(ActionEvent event) throws Exception {

        if(tableClient.getSelectionModel().getSelectedIndex() == -1)
        {
            Utils.showMessage("Gestion des client","Modification client","SELECTIONNEZ LE CLIENT A MODIFIER !!");
        }
        else
        {
            Client clt = tableClient.getSelectionModel().getSelectedItem();
            numeroTfd.setText(clt.getNumero());
            nomTfd.setText(clt.getNom());
            prenomTfd.setText(clt.getPrenom());
            dateTfd.setText(""+clt.getDateachete());
            prixTfd.setText(""+clt.getPrix());
            produitsCbx.getSelectionModel().select(clt.getPrt().getId()-1);
            //creditCbx.getSelectionModel().select(clt.getCredit().getId());
            List<Credit> listC = new ArrayList<>();
            listC = produitsCbx.getSelectionModel().getSelectedItem().getListeCredit();
            for (Credit sc : listC)
            {
                if(sc.getId() == clt.getCredit().getId())
                {
                    creditCbx.getSelectionModel().select(listC.indexOf(sc));
                }
            }
        }


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            numeroTfd.setText(IC.genererQunt());
            IProduits iProduits  =  new ProduitsDao();
            List<Produits> listeProduits = iProduits.findAll();
            produitsCbx.setItems(FXCollections.observableArrayList(listeProduits));

            produitsCbx.valueProperty().addListener(new ChangeListener<Produits>() {
                @Override
                public void changed(ObservableValue<? extends Produits> observable, Produits oldValue, Produits newValue) {

                    try{
                        if(produitsCbx.getValue() != null) {
                            int id = produitsCbx.getValue().getId();

                            List<Credit> listeCredit = produitsCbx.getValue().getListeCredit();
                            creditCbx.setItems(FXCollections.observableArrayList(listeCredit));
                        }

                    }catch(Exception e){
                        e.printStackTrace();
                    }

                }
            });
            actualiserTab();


        }catch (Exception e){

        }

    }
    @FXML
    void deleteClt(ActionEvent event) throws Exception
    {
        if(tableClient.getSelectionModel().getSelectedIndex() == -1)
        {
            Utils.showMessage("Gestion des client","Suppression client","SELECTIONNEZ LE CLIENT A SUPPRIMER !!");
        }
        else
        {
           boolean rep = Utils.confirmMessage("Gestion des client","Suppression client", "ETSA VOUS SUR ??");
           if(rep)
           {
               if(IC.deleteClt(tableClient.getSelectionModel().getSelectedItem().getNumero()))
               {
                   Utils.showMessage("Gestion des client","Suppresion client","SUCCEE!");
                   viderChamps();
                   actualiserTab();
               }
               else
               {
                   Utils.showMessage("Gestion des client","Suppresion client","ECHEC!");

               }

           }
        }

    }
//    @FXML
//    void vider(ActionEvent event) throws Exception
//    {
//        viderChamps();
//
//    }
    public void viderChamps(){
        numeroTfd.setText(IC.genererQunt());
        nomTfd.setText("");
        prenomTfd.setText("");
        dateTfd.setText("");
        prixTfd.setText("");
        creditCbx.getSelectionModel().clearSelection();
        produitsCbx.getSelectionModel().clearSelection();
    }

    public void actualiserTab(){
        ObservableList<Client> list = FXCollections.observableArrayList();
        list.addAll(IC.getAllClt());

        colNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("dateachete"));
        colPrix.setCellValueFactory(new PropertyValueFactory<>("prix"));
//            colProduits.setCellValueFactory(new PropertyValueFactory<>("prt"));
//            colCredit.setCellValueFactory(new PropertyValueFactory<>("credit"));


        tableClient.setItems(list);
    }
}
