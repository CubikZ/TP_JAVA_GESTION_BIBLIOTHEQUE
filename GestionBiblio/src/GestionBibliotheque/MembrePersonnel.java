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
public class MembrePersonnel extends MembreBibliotheque{
    
    //Constructeurs
    MembrePersonnel(String nom2,String prenom2, String numeroTel2, String adresse2)
    {
        super(nom2,prenom2,numeroTel2,adresse2);
    }
    //Actions
    @Override
    public boolean peutEmprunterAutreDocument()
    {
        return nbrDocEmpruntes<8;
    }
    @Override
    public String toString()
    {
        return "Type = Membre Etudiant\n"+super.toString();
    }
    
    @Override
    public boolean docDisponible(DocBibliotheque d)
    {
        System.out.println("Le document \" "+d.getTitre()+" \" qui a été réservé par le membre personnel \" "+this.getNom()+" \" est désormais disponible à l'emprunt au bureau des réservations");
        return false;
    }
}
