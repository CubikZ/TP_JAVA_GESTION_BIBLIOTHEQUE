/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionBibliotheque;

/**
 *
 * @author p1906724
 */
public class MembreBibliotheque implements Notifiable{
    
    private String nom;
    private String prenom;
    private String numeroTel;
    private String adresse;
    
    
    private int numeroAbonne;
    private static int id=0;
    
    protected int nbrDocEmpruntes=0;
    
    
    //Constructeurs
    
    MembreBibliotheque(String nom2,String prenom2, String numeroTel2, String adresse2)
    {
        nom=nom2;
        prenom=prenom2;
        numeroTel=numeroTel2;
        adresse=adresse2;
        this.numeroAbonne=this.id;
        this.id++;
    }
    
    //Accesseurs
    public String getNom()
    {
        return nom;
    }
    public String getPrenom()
    {
        return prenom;
    }
    public String getNumeroTel()
    {
        return numeroTel;
    }
    public int getNumeroAbonne()
    {
        return numeroAbonne;
    }
    public int getID()
    {
        return id-1;
    }
    
    public int getNbrDocEmpruntes()
    {
        return nbrDocEmpruntes;
    }
    
    //Mutateurs
    public boolean setNom(String nom2)
    {
        nom=nom2;
        return true;
    }
    public boolean setPrenom(String prenom2)
    {
        prenom=prenom2;
        return true;
    }
    public boolean setNumeroTel(String numeroTel2)
    {
        numeroTel=numeroTel2;
        return true;
    }
    public boolean setAdresse(String adresse2)
    {
        adresse=adresse2;
        return true;
    }
    
    public boolean incrementerNbrDocEmpruntes()
    {
        nbrDocEmpruntes++;
        return true;
    }
    public void decrementerNbrDocEmpruntes()
    {
        nbrDocEmpruntes--;
        
    }
    
    //Actions
    public boolean peutEmprunterAutreDocument()
    {
        return true;
    }
    @Override
    public boolean docDisponible(DocBibliotheque d)
    {
        return false;
    }
    //////
    @Override
    public String toString()
    {
        return "Numero Abo = "+this.numeroAbonne+
               "\nNom = "+this.nom+
               "\nPrenom = "+this.prenom+
               "\nNumero de telephone = "+this.numeroTel+
                "\nAdresse = "+this.adresse+
               "\nType de membre = "+
               "\nNombre de docEmprunte = "+this.getNbrDocEmpruntes()+"\n";
               
               
                       
    }

    
}
