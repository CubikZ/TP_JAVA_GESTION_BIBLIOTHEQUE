/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionBibliotheque;

import static GestionBibliotheque.TestBibliotheque.catalogue;
import static GestionBibliotheque.TestBibliotheque.listeMembres;
import java.util.Scanner;

/**
 *
 * @author loann
 */
public class MembreEtudiant extends MembreBibliotheque{
    
    //Constructeurs
    MembreEtudiant(String nom2,String prenom2, String numeroTel2, String adresse2)
    {
        super(nom2,prenom2,numeroTel2,adresse2);
    }
    
    //Actions
    @Override
    public boolean peutEmprunterAutreDocument()
    {
        return nbrDocEmpruntes<4;
    }
    
    @Override
    public String toString()
    {
        return "Type = Membre Etudiant\n"+super.toString();
    }
    @Override
    public boolean docDisponible(DocBibliotheque d)
    {
        Scanner sc = new Scanner(System.in);
        int choix=0;
        System.out.println("Le document \" "+d.getTitre()+" \" qui a été réservé par le membre étudiant \" "+this.getNom()+" \" est désormais disponible.");
        System.out.println(this.getNom()+", Voulez-vous empruntez le document ? 1:Oui / 2:Non ");
        while (choix<1 || choix >2){
            try {
                choix=sc.nextInt();
                sc.nextLine();
            }
            catch(java.util.InputMismatchException e)
            {
                sc=new Scanner(System.in);
                choix=0;
                System.out.println("Erreur, cet argument est incorrect : uniquement 1 ou 2 !");
            }
        }
        if(choix==1)
        {
            catalogue.emprunteDoc(catalogue.accesPositionDoc(d),listeMembres.accesMembre(listeMembres.accesPositionMembre(this)));
            return true;
        }
        return false;
    }
    
}
