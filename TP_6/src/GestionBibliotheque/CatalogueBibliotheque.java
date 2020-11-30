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
public class CatalogueBibliotheque {

    //DocBibliotheque
    private ArrayList<DocBibliotheque> listeDocuments; //Type 1:livre 2:cd 3:docUrl

    CatalogueBibliotheque() {
        listeDocuments = new ArrayList<>();
    }

    //Ajouter
    boolean ajouter(DocBibliotheque doc) throws ErreurDoublon,ErreurAjout
    {
        try {
        if(verifier(doc))
            throw new ErreurDoublon();
        else {
            if(listeDocuments.add(doc))
                return true;
            else
                throw new ErreurAjout();
        }
        }catch(ErreurNbrNegatif e)
        {
            System.out.println(e);
            return false;
        }
        
    }
    //Supprimer
    boolean supprimer(DocBibliotheque doc) throws ErreurSuppression
    {
        if(listeDocuments.contains(doc))
        {
            listeDocuments.remove(doc);
            return true;
        }  
        else
            throw new ErreurSuppression();          
    }
   
    //Supprimer avec un titre
    boolean supprimer(String titre) throws ErreurSuppression 
    {
        for(int i=0;i<listeDocuments.size();i++)
        {
            if(listeDocuments.get(i).getTitre().equals(titre))
            {
                System.out.println(listeDocuments.get(i).getTitre()+" a ete supprimer");
                listeDocuments.remove(i);
                return true;
            }
        }
        throw new ErreurSuppression();
    }
    //Remplacer
    boolean remplacer(DocBibliotheque doc,int indice)
    {
        try {
            listeDocuments.set(indice, doc);
            return true;
        }catch(java.lang.IndexOutOfBoundsException e)
        {
            System.out.println("Erreur indice : document inaccessible");
            return false;
        }
    }
    //Vérifier pendant l'ajout (Doublons ?)
    boolean verifier(DocBibliotheque doc) throws ErreurNbrNegatif
    {
        Livre livre; //On verifie le nombre de page si c un livre
        //On verifie le nombre de page (Livre)
        if(doc.getClass().getName().equals("GestionBibliotheque.Livre"))
        {
            livre=(Livre)doc;
            if(livre.getNbrPages()<0)
            {
                throw new ErreurNbrNegatif("nombre de pages");
            }
        }
        for(int i=0;i<listeDocuments.size();i++)
        {
            //On verifie doublon (on cherche une egalité dans toute la liste)
            if(listeDocuments.get(i).getCodeArchivage().equals(doc.getCodeArchivage()))
                return true;
            if(listeDocuments.get(i).getTitre().equals(doc.getTitre()))
                return true;
            
        }
        return false; //Si pas d'erreur
    }
    
    //Actions
    public DocBibliotheque accesDoc(int indiceDoc) //Exception indice gerer
    {
       
        try {
            return listeDocuments.get(indiceDoc);
        } catch (java.lang.IndexOutOfBoundsException e) {
            System.out.println("Erreur indice : document inaccessible");
            return null;
        }
    }
    public int accesPositionDoc(DocBibliotheque d)
    {
        return listeDocuments.indexOf(d);
    }

    public void afficherDocuments(int choix) throws ErreurChoix //choix = livre 1 / cd 2 / docurl 3 / TOUT 4 //Exception choix gerer
    {
        if(choix>4 || choix<1)
            throw new ErreurChoix();
        
        for(int i=0;i<listeDocuments.size();i++)
        {
            switch(listeDocuments.get(i).getClass().getName())
            {
                case "GestionBibliotheque.Livre":
                    if(choix==1 || choix==4)
                    {
                        System.out.println(listeDocuments.get(i));
                    }
                    break;
                    
                case "GestionBibliotheque.CD":
                    if(choix==2 || choix==4)
                    {
                        System.out.println(listeDocuments.get(i));
                    }
                    break;
                case "GestionBibliotheque.DocURL":
                    if(choix==3 || choix==4)
                    {
                        System.out.println(listeDocuments.get(i));
                    }
                    break;
            }
        }
    }
            
    //Pour afficher le menu avec les noms (et les indices)
    public void afficherNomDoc() {
        
        for(int i=0;i<listeDocuments.size();i++)
        {
            switch(listeDocuments.get(i).getClass().getName()) //On recupere la classe de l'objet puis on l'a compare
            {
                case "GestionBibliotheque.Livre":
                    System.out.println(i+" = "+listeDocuments.get(i).getTitre()+" (Livre)");
                    break;
                   
                case "GestionBibliotheque.CD":
                    System.out.println(i+" = "+listeDocuments.get(i).getTitre()+" (CD)");
                    break;
                   
                case "GestionBibliotheque.DocURL":
                    System.out.println(i+" = "+listeDocuments.get(i).getTitre()+" (DocURL)");
                    break;
                   
            }
        }
    }

    public void afficheDocsEmpruntes() {
        boolean aucun=false;
        boolean aucun2=false;
        for(int i=0;i<listeDocuments.size();i++)
        {
            switch(listeDocuments.get(i).getClass().getName())
            {
                case "GestionBibliotheque.Livre":
                    if(listeDocuments.get(i).getMembreEmprunteur()!=null && aucun!=true)
                    {
                        System.out.println("Aucun livre empruntee");
                        aucun=true;
                    }
                    else if(aucun!=true)
                        System.out.println("-"+listeDocuments.get(i).getTitre()+" (Livre)");
                    break;
                   
                case "GestionBibliotheque.CD":
                    if(listeDocuments.get(i).getMembreEmprunteur()!=null && aucun2!=true)
                    {
                        System.out.println("Aucun CD empruntee");
                        aucun2=true;
                    }
                    else if(aucun2!=true)
                        System.out.println("-"+listeDocuments.get(i).getTitre()+" (CD)");
                    break;
            }
        }
        System.out.println("Aucun docURL empruntee : emprunt impossible de ce type de document");
  
    }

    public boolean emprunteDoc(int indiceDoc, MembreBibliotheque membre) //type = livre 1 / cd 2 / docurl 3 (sauf qu'on ne peut pas : va renvoyer automatiquement une erreur)
    {
        if (membre.peutEmprunterAutreDocument()) {
            try {
                return accesDoc(indiceDoc).emprunter(membre);
            } 
            catch (ErreurEmprunter e) {
                System.out.println(e);
                return false;
            }

        } else {
            System.out.println("Operation impossible : Quota de document emprunte maximum atteint");
            return false;
        }

    }

    public boolean souscriptionDoc(int indiceDoc, MembreBibliotheque membre) //type = livre 1 / cd 2 / docurl 3  (On demandera si la personne veut un livre, cd ou doc url)
    {
        try
        {
            return accesDoc(indiceDoc).souscription(membre);
        }
        catch(ErreurSouscription e)
        {
            System.out.println(e);
            return false;
        }
    }

    public boolean annulerSouscriptionDoc(int indiceDoc, MembreBibliotheque membre) {
        try
        {
        return accesDoc(indiceDoc).annulationSouscription(membre);
        }
        catch(ErreurAnnulerSouscription e)
        {
            System.out.println(e);
            return false;
        }
        
    }

    public boolean retournerDoc(int indiceDoc, MembreBibliotheque membre) {
        try
        {
            return accesDoc(indiceDoc).retourner(membre);
        }
        catch(ErreurRetourner e)
        {
            System.out.println(e);
            return false;
        }
        
    }

    public boolean archiverDoc(int indiceDoc, MembreBibliotheque membre) {
        
        if (membre.getClass().getName().equals("MembrePersonnel")) {
            try
            {
            return accesDoc(indiceDoc).archiver();
            }
            catch(ErreurArchiver e)
            {
                System.out.println(e);
            }
        } else {
            System.out.println("Seul les membres Personnel peuvent effectuer cette action");
        }
        return false;
    }

    //Compter
    public void compteLivres() {
        int total=0;
        for(int i=0;i<listeDocuments.size();i++)
        {
            if(listeDocuments.get(i).getClass().getName().equals("GestionBibliotheque.Livre"))
                total++;
        }
        System.out.println("Il y a " + total + " livres dans le catalogue.");
    }

    public void compteCDs() {
        int total=0;
        for(int i=0;i<listeDocuments.size();i++)
        {
            if(listeDocuments.get(i).getClass().getName().equals("GestionBibliotheque.CD"))
                total++;
        }
        System.out.println("Il y a " + total + " CDs dans le catalogue.");
    }

    public void compteDocUrls() {
        int total=0;
        for(int i=0;i<listeDocuments.size();i++)
        {
            if(listeDocuments.get(i).getClass().getName().equals("GestionBibliotheque.DocURL"))
                total++;
        }
        System.out.println("Il y a " + total + " DocURL dans le catalogue.");
    }

    public int compteTout() {
        return listeDocuments.size();
    }
}
