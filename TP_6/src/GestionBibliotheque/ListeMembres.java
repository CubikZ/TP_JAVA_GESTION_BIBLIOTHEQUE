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
public class ListeMembres {
    
    ArrayList<MembreBibliotheque> listeMembres;   //Stock les membres etudiants & personnel
    
    ListeMembres()
    {
        listeMembres = new ArrayList<>();
    }
    
    public boolean ajouter(MembreBibliotheque membre) throws ErreurDoublon,ErreurAjout
    {
        
        if(verifier(membre))
            throw new ErreurDoublon();
        else {
            if(listeMembres.add(membre))
                return true;
            else
                throw new ErreurAjout();
        }
                
    }
    
    public boolean supprimer(MembreBibliotheque membre)throws ErreurSuppression
    {
        if(listeMembres.contains(membre))
        {
            listeMembres.remove(membre);
            return true;
        }
            
        else
            throw new ErreurSuppression();
            
    }
    public boolean verifier(MembreBibliotheque membre)
    {
        if(membre==null)
            return true;
        for(int i=0;i<listeMembres.size();i++)
        {
            if(listeMembres.get(i).getNom().equals(membre.getNom()))
                return true;
        }
        return false; //Si pas d'erreur
    }
    
    public boolean supprimer(String nom) throws ErreurSuppression
    {
        for(int i=0;i<listeMembres.size();i++)
        {
            if(listeMembres.get(i).getNom().equals(nom))
            {
                System.out.println(listeMembres.get(i).getNom()+" a ete supprimer");
                listeMembres.remove(i);
                return true;
            }
        }
        throw new ErreurSuppression();
    }
    
    
    public MembreBibliotheque accesMembre(int indice)
    {
        try {
            return listeMembres.get(indice);
        } catch (java.lang.IndexOutOfBoundsException e) {
            System.out.println("Erreur indice : document inaccessible");
            return null;
        }
        
    }
    public void accesMembreTout(int choix) throws ErreurChoix
    {
        boolean une1=false; //Pour faire 1 seul fois
        boolean une2=false;
        
        if(choix<0 || choix>3)
        for(int i=0;i<listeMembres.size();i++)
        {
            switch(listeMembres.get(i).getClass().getName()) //On recupere la classe de l'objet puis on l'a compare
            {
                case "GestionBibliotheque.MembreEtudiant":
                    if(choix==1 || choix==3)
                    {
                        System.out.println(listeMembres.get(i));
                        break;
                    }
                   
                case "GestionBibliotheque.MembrePersonnel":
                    if(choix==2 || choix==3)
                    {
                        System.out.println(listeMembres.get(i));
                        break;
                    }
                    
                   
            }
        }   
    }
    
    public void afficherNomMembres()
    {
        
        for(int i=0;i<listeMembres.size();i++)
        {
            switch(listeMembres.get(i).getClass().getName()) //On recupere la classe de l'objet puis on l'a compare
            {
                case "GestionBibliotheque.MembreEtudiant":
                    
                    System.out.println(i+" = "+listeMembres.get(i).getNom()+" (Membre Etudiant)");
                    break;
                   
                case "GestionBibliotheque.MembrePersonnel":
                    System.out.println(i+" = "+listeMembres.get(i).getNom()+ "(Membre Personnel)");
                    break;
                   
            }
        }          
            
    }
    public int accesPositionMembre(MembreBibliotheque membre)
    {
        return listeMembres.indexOf(membre);
    }
    public int CompteMembres()
    {
        return listeMembres.size();
    }

}
