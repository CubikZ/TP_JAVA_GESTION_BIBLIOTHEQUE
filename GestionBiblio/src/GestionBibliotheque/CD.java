/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionBibliotheque;

import java.util.ArrayList;
import Exceptions.*;

/**
 *
 * @author loann
 */
public class CD extends DocBibliotheque{
    //Atributs
    ArrayList<String> listeMorceaux;
    String morceaux;    //Utile pour toString
    //Constructeurs
    CD()
    {
        super();
        listeMorceaux = new ArrayList();
    }
    CD(String codeArchivage2, String titre2, String auteur2,int annee2, ArrayList<String> Morceaux)
    {
        super(codeArchivage2,titre2,auteur2,annee2);
        if(Morceaux.isEmpty())
            listeMorceaux = new ArrayList<>();
        else 
        {
            listeMorceaux = new ArrayList<>();
            listeMorceaux.addAll(Morceaux);
        }
    }
    //Pour pouvoir mettre null
    CD(String codeArchivage2, String titre2, String auteur2,int annee2,int novalue)
    {
        super(codeArchivage2,titre2,auteur2,annee2);
        listeMorceaux = new ArrayList<>();
        
    }
    
    //Accesseurs
    public void afficheTousLesMorceaux()
    {
        for(int i=0;i<listeMorceaux.size();i++)    
            {
                System.out.println(listeMorceaux.get(i));
            }
    }
    //Mutateurs
    public boolean ajouterMorceau(String morceau) throws ErreurSet
    {
        if (listeMorceaux.contains(morceau)) //Si la liste contient le document ou pas
        {
            throw new ErreurSet();
        }
        else
        {
            listeMorceaux.add(morceau);
            return true;
        }
            
    }
    public boolean supprimerMorceau(String morceau) throws ErreurSet
    {
        if (listeMorceaux.contains(morceau)) //Si la liste contient le document ou pas
        {
            for(int i=0;i<listeMorceaux.size();i++)    
            {
                if (listeMorceaux.get(i).equals(morceau) )    //Si l'objet est = à l'objet demandé
                {
                    listeMorceaux.remove(i);       //On enleve l'objet correspondant à i
                    break;                          //Pour sortir de la boucle
                }
            }
            return true;
        }
        else
        {
            throw new ErreurSet();
        }
            
    }
    
    //////////////////////////////
    @Override
    public String toString() {
        String resultat="Type = CD\n"
                        + "CodeArchivage = " + this.codeArchivage
                        + "\nTitre = " + this.titre
                        + "\nAuteur = " + this.auteur
                        + "\nAnnee = " + this.annee
                        + "\nNombre de morceaux = "+this.listeMorceaux.size()
                        + "\nListe Morceaux : ";
                                     
                        if(!this.listeMorceaux.isEmpty())
                            for(int i=0;i<listeMorceaux.size();i++){
                                resultat=resultat+listeMorceaux.get(i)+"\n                 ";
                            }
                        
                resultat=resultat+"\n"+super.toString();
        
        return resultat;
    
    }
    
}
