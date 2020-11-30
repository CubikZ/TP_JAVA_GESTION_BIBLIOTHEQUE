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
public class DocURL extends DocBibliotheque{
    //Attributs
    String url;
    String description;
    //Constructeurs
    DocURL()
    {
        super();
        url="http://www.siteweb.com/";
        description="Blablabla";
    }
    DocURL(String codeArchivage2, String titre2, String auteur2,int annee2,String url2, String description2)
    {
        super(codeArchivage2,titre2,auteur2,annee2);
        url=url2;
        description=description2;
    }
    //Accesseurs
    public String getUrl()
    {
        return url;
    }
    public String getDescription()
    {
        return description;
    }
    //Mutateurs
    public boolean setUrl(String url2) {
        url = url2;
        return true;
    }
    public boolean setDescription(String description2) {
        description = description2;
        return true;
    }
    //Outrepassement
    @Override
    public boolean emprunter(MembreBibliotheque membre)
    {
        System.out.println("Operation Impossible : Ceci est un objet non physique");
        return false;
    }
    
    ///////////////////////////////////////
    @Override
    public String toString() {
        return "Type = DocURL\n"
                +"CodeArchivage = " + this.codeArchivage
                + "\nTitre = " + this.titre
                + "\nAuteur = " + this.auteur
                + "\nDate de publication = " + this.annee
                + "\nUrl = " + this.url 
                + "\nDescription = "+ this.description;
    }
}
