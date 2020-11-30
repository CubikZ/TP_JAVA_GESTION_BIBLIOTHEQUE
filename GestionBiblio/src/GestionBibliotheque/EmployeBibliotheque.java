/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionBibliotheque;
import Exceptions.ErreurSuppression;
import static GestionBibliotheque.TestBibliotheque.catalogue;

/**
 *
 * @author loann
 */
public class EmployeBibliotheque implements Notifiable{
    private String nom;
    private int identification=0;
    private static int id=0;
            
    EmployeBibliotheque(String nom)
    {
        this.nom=nom;
        this.identification=id;
        id++;
    }
    
    @Override
    public String getNom()
    {
        return this.nom;
    }
    
    public int getId()
    {
        return this.identification;
    }
    public void setNom(String nom)
    {
        this.nom=nom;
    }
    
    @Override
    public boolean docDisponible(DocBibliotheque d){
        System.out.println("Ce document "+d.getTitre()+" a ete retourner, suppression du document du catalogue...");
        try {
        if(catalogue.supprimer(d));
            System.out.println(d.getTitre()+" a bien ete supprimer du catalogue.");
        }catch(ErreurSuppression e)
        {
            System.out.println(e);
        }
        return false;
        
        
    }
    @Override
    public String toString()
    {
        return "nom = "+this.nom
            + "\nidentification = " +this.identification;
    }
}
