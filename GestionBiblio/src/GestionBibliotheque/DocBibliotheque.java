/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionBibliotheque;
import Exceptions.*;

/**
 *
 * @author p1906724
 */
public class DocBibliotheque {

    //Attributs
    protected String codeArchivage;
    protected String titre;
    protected String auteur;
    protected int annee;
    //Emplacement
    private boolean etageres=true;
    private boolean emprunt=false;
    private boolean pilesDesRetours=false;
    private boolean sectionReservation=false;
    //Action
    private boolean reserver=false; // Si emprunt==1 et Reservation!=1
    //Emprunter
    //Archiver
    //Retour
    
    //Nombre
    private static int nbrDoc=0;
    private static int nbrEmprunt=0;
    private static int nbrDocPile=0;
    private static int nbrDocSectReservation=0;  //Nombre de doc dans section reservation
    
    
    //Lien avec membre
    protected MembreBibliotheque membreEmprunteur=null;
    protected Notifiable membreSouscrit=null;
    //Souscription
    
    
    
    DocBibliotheque()
    {
        codeArchivage="123JKMM3";
        titre="LeMonde";
        auteur="Jean";
        annee=1999;
        nbrDoc++;
        
     
    }
    
    DocBibliotheque(String codeArchivage2, String titre2, String auteur2,int annee2)
    {
       codeArchivage=codeArchivage2;
       titre=titre2;
       auteur=auteur2;
       annee=annee2;
       nbrDoc++;
    }
    
    //Accesseurs
    public String getCodeArchivage()  {
        return codeArchivage;
    }

    public String getTitre() {
        return titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public int getAnnee() {
        return annee;
    }
    
    //Mutateurs
    public boolean setCodeArchivage(String codeArchivage2) throws ErreurSet{
        codeArchivage = codeArchivage2;
        if(codeArchivage.equals(codeArchivage2))
            return true;
        else
            throw new ErreurSet();
    }

    public boolean setTitre(String titre2) throws ErreurSet{
        titre = titre2;
        if(titre.equals(titre2))
            return true;
        else
            throw new ErreurSet();
    }

    public boolean setAuteur(String auteur2) throws ErreurSet {
        auteur = auteur2;
        if(auteur.equals(auteur2))
            return true;
        else
            throw new ErreurSet();
    }

    public boolean setAnnee(int annee2) throws ErreurSet{
        annee = annee2;
        if(annee==annee2)
            return true;
        else
            throw new ErreurSet();
    }
   

    //Accesseurs indications de la bibliotheque
    public int getNbrDoc()
    {
        return nbrDoc;
    }
    public int getNbrEmprunt()
    {
        return nbrEmprunt;
    }
    public int getNbrDocPile()
    {
        return nbrDocPile;
    }
    public int getNbrDocSectReservation()
    {
        return nbrDocSectReservation;
    }
    
    //Membre
    public String getMembreEmprunteur()
    {
        if (membreEmprunteur==null)
            return "Ce document n'est pas emprunter";
        else
            return membreEmprunteur.getNom();
    }
    public String getMembreSouscrit()
    {
        if (membreSouscrit==null)
            return "Ce document n'est pas souscrit";
        else
            return membreSouscrit.getNom();
    }
    public String getMembres()   //Accesseurs Membre
    {
        if (membreEmprunteur==null && membreSouscrit==null)
            return "Ce document n'est ni souscrit, ni emprunter";
        else if (membreEmprunteur!=null && membreSouscrit==null)
            return "Ce document est emprunte par "+getMembreEmprunteur()+" et n'est pas souscrit";
        else if (membreEmprunteur==null && membreSouscrit!=null)
            return "Ce document n'est pas emprunter et est reserver par "+getMembreSouscrit();
        else
            return "Ce document est emprunte par "+getMembreEmprunteur()+" et est souscrit par "+getMembreSouscrit();
            
    }
    
            
    
    //Ascensseur
    public String getEmplacement()
    {
        if (etageres==true)
            return "sur Etagere";
        else if (emprunt==true)
            return "emprunter";
        else if (pilesDesRetours==true)
            return "sur pile des retours";
        else if (sectionReservation==true)
            return "sur section speciale reservations";
        else
            return "Erreur";
    } 
    
    //Action
    public boolean emprunter(MembreBibliotheque membreEmprunteur) throws ErreurEmprunter
    {
        if (etageres==true)
        {
            emprunt=true;
            etageres=false;
            nbrEmprunt++;
            this.membreEmprunteur=membreEmprunteur;
            
            membreEmprunteur.incrementerNbrDocEmpruntes();
            return true;
            
            
        }
        else if (sectionReservation==true)
        {
            emprunt=true;
            sectionReservation=false;
            nbrDocSectReservation--;
            nbrEmprunt++;
            this.membreEmprunteur=membreEmprunteur;
            this.membreSouscrit=null;
            return true;
            
        }
        else
        {
            throw new ErreurEmprunter();
        }
    }
    
    public boolean souscription(Notifiable membre) throws ErreurSouscription //Souscription
    {
        if (membre!=this.membreEmprunteur)
        {
            if (emprunt==true)
            {
                reserver=true;
                this.membreSouscrit=membre;
                return true;
            }
            else
            {
                throw new ErreurSouscription();
            }
        }
        else
        {
            throw new ErreurSouscription();
        }
        
            
    }
    
    public boolean annulationSouscription(Notifiable membre) throws ErreurAnnulerSouscription
    {
        if (this.membreSouscrit==membre)
        {
            if (reserver==true)
            {
                if (sectionReservation=true)
                {
                    sectionReservation=false;
                    pilesDesRetours=true;
                    nbrDocPile++;
                    nbrDocSectReservation--;
                
                }
                reserver=false;
                this.membreSouscrit=null;
                return true;
            }
            else
            {
                throw new ErreurAnnulerSouscription();
            }
        }
        else
        {
            throw new ErreurAnnulerSouscription();
        }
       
                           
    }
    
    public boolean retourner(MembreBibliotheque membre)throws ErreurRetourner
    {
       boolean emprunter=false;
       if (this.membreEmprunteur==membre)
       {
            if (emprunt==true)
            {
                emprunt=false;
                nbrEmprunt--;
                if (reserver==true)
                {
                    sectionReservation=true;
                    nbrDocSectReservation++;
                    emprunter=membreSouscrit.docDisponible(this);
                }
                else
                {
                    pilesDesRetours=true;
                    nbrDocPile++;
                }
                if(!emprunter)//Si l'étudiant avertie n'emprunte pas le document
                {
                membre.decrementerNbrDocEmpruntes();
                membreEmprunteur=null;
                }
                return true;
            }
            else
            {
                throw new ErreurRetourner();
            }
       }
       else
       {
           //System.out.println("Operation Impossible car vous n'etes pas l emprunteur de ce document");
           throw new ErreurRetourner();
         
       }
    }
    public boolean archiver() throws ErreurArchiver
    {
        if (pilesDesRetours==true)
        {
            etageres=true;
            pilesDesRetours=false;
            nbrDocPile--;
            return true;
        }
        else
        {
            throw new ErreurArchiver();
        }
    }
    
    
  
    ///////////////////////////////////////
    
    
    
    @Override
    public String toString()
    {
        return "\nMembreEmprunteur = "+getMembreEmprunteur()+
               "\nMembreSouscrit = "+getMembreSouscrit()+
               "\nEmplacement = "+getEmplacement()+"\n";
    }

    
            
 }
        
    
 
