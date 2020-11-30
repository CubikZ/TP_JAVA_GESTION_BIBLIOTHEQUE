/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionBibliotheque;

/**
 *
 * @author loann
 */
public class Livre extends DocBibliotheque {

    private String ISBN;

    private String editeur;

    private int nbrPages;

    //Constructeurs
    Livre() {
        super();    //Appel le constructeur de la classe mère
        ISBN = "978-3-16-148410-0";

        editeur = "Didier";

        nbrPages = 50;

    }

    Livre(String ISBN2, String codeArchivage2, String titre2, String auteur2, String editeur2, int annee2, int nbrPages2) {
        super(codeArchivage2, titre2, auteur2, annee2);
        ISBN = ISBN2;

        editeur = editeur2;

        nbrPages = nbrPages2;

    }

    //Accesseurs
    
    public String getISBN()
    {
        return ISBN;
    }
    public String getEditeur()
    {
        return editeur;
    }
    public int getNbrPages()
    {
        return nbrPages;
    }

    //Mutateurs
    public boolean setISBN(String ISBN2) {
        ISBN = ISBN2;
        return true;
    }
    public boolean setEditeur(String editeur2) {
        editeur = editeur2;
        return true;
    }
    public boolean setNbrPages(int nbrPages2) {
        nbrPages = nbrPages2;
        return true;
    }

    ///////////////////////////////////////
    @Override
    public String toString() {
        return "Type = Livre\n"
                +"CodeArchivage = " + this.codeArchivage
                +"\nISBN = "+ this.ISBN
                + "\nTitre = " + this.titre
                + "\nAuteur = " + this.auteur
                + "\nEditeur = "+this.editeur
                + "\nAnnee = " + this.annee
                + "\nNombre de pages = "+ this.nbrPages
                
                + super.toString(); //Appel la methode toString de la classe mère
    }

}
