/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionBibliotheque;

import java.util.ArrayList;
import java.util.Scanner;
import Exceptions.*; //on import le package Exceptions crée par nous pour gérer les exceptions
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author p1906724 loann MR
 * Cette version contient toutes les operations en utilisant QUE le catalogue et de la listeMembre (pas d'objet DocBibliotheque static a part le catalogue)
 * 
 * Exceptions vérifiés gérer :
 * -java.lang.NullPointerException --> pour les operation avec null
 * -java.lang.IndexOutOfBoundsException -> Pour les indices
 * -java.util.InputMismatchException --> pour les tromperies de demande d'entier
 * 
 */
public class TestBibliotheque{
    //Creation Catalogue Bibliotheque
     static CatalogueBibliotheque catalogue=new CatalogueBibliotheque();
    //Creation liste de Membre
    static ListeMembres listeMembres = new ListeMembres();
    //Pour le scanner
    static Scanner sc= new Scanner(System.in);
    //EmployeBibliotheque
    static EmployeBibliotheque employe1 = new EmployeBibliotheque("Chef");
    static EmployeBibliotheque employe2 = new EmployeBibliotheque("Sous-Chef");
    
        
    public static void main(String[] args)
    {
        /////////////////////////////////////////////////////////////////////////////////////////
        initialisation();
        //////////////////////////////////////////////////////////////////////////////////////////
        //Variables
        
        int choix1;
          
        //////////////////////////////////////////////////////////////////////////////////////
        System.out.println("///////////////////////////////////////////////////////"
                           +"\nBienvenue sur le programme Gestion de bibliotheque");
        do{
            System.out.println("///////////////////////////////////////////////////////"
                            + "\n1  : Afficher des informations sur un document"
                            + "\n2  : Afficher des informations sur un membre"
                            + "\n3  : Connaitre l'etat d'un document"
                            + "\n4  : Faire une action sur un document"
                            + "\n5  : Faire une modification sur un document"
                            + "\n6  : Faire une modification sur un membre"
                            + "\n7  : Afficher des informations de la bibliotheque"
                            + "\n8  : Faire une action / Obtenir des informations depuis le CatalogueBibliotheque"
                            + "\n9  : Faire une action / Obtenir des informations depuis la ListeMembres"
                            + "\n10 : ActionEmployeBibliotheque (EmployeBibliotheque)"
                            + "\n13 : TEST"
                            + "\n0  : Quitter"
                            + "\n///////////////////////////////////////////////////////"
                            + "\nQue voulez-vous faire ? ");
            choix1=choisirEntier();
            switch(choix1)
            {
                case 1: //Afficher des infos sur un doc
                    System.out.println(catalogue.accesDoc(choixIdDocument()));
                    break;
                    
                case 2: //info membre
     
                    System.out.println(listeMembres.accesMembre(choixIdMembre()));
                    break;
             
                case 3: //etat doc
                    try {
                    System.out.println("\n Ce livre "+ catalogue.accesDoc(choixIdDocument()).getEmplacement());
                    }catch(java.lang.NullPointerException e)
                    {
                        System.out.println("");//une erreur d'indice est deja retourné depuis le catalogue
                    }
                    break;
                    
                case 4: //action doc
                    try {
                    if(actionDocument())
                        System.out.println("Operation Reussie !");
                    else
                        System.out.println("Erreur, Operation non Reussie !");
                    }catch(java.lang.NullPointerException e)
                    {
                        System.out.println("");//une erreur d'indice est deja retourné depuis le catalogue
                    }catch(ErreurChoix e)
                    {
                        System.out.println(e);
                    }
                    
                        
                    break;
                    
                case 5: //modif doc
                    try {
                    if(modifierDocumentChoix(choixIdDocument(),choixTypeDocument(false)))
                        System.out.println("Operation Reussie !");
                    else
                        System.out.println("Erreur, Operation non Reussie !");
                    }catch(ErreurChoix  | java.lang.NullPointerException e)
                    {
                        System.out.println(e);
                        System.out.println("Erreur, Operation non Reussie !");
                    }
                    break;
                    
                case 6: //modif membre
                    try {
                    if(modifierMembreChoix(choixIdMembre()))
                        System.out.println("Operation Reussie !");
                    else
                        System.out.println("Erreur, Operation non Reussie !");
                    }catch(java.lang.NullPointerException e)
                    {
                        System.out.println("");
                    }
                    break;
                    
                    
                case 7: //info Bilibliotheque
                    System.out.println("Informations sur la bibliotheque :");
                    System.out.println("Nombre de livre inscrits = "+catalogue.accesDoc(0).getNbrDoc()
                                    + "\nNombre de livre emprunter = "+catalogue.accesDoc(0).getNbrEmprunt()
                                    +"\nNombre de livre sur la pile = "+catalogue.accesDoc(0).getNbrDocPile()
                                    +"\nNombre de livre sur la section reservation = "+catalogue.accesDoc(0).getNbrDocSectReservation()
                                    +"\nNombre de membre inscrits = "+listeMembres.accesMembre(0).getID());
                    break;
                    
                case 8: //catalogue biblio
                    if(actionCatalogueChoix())
                        System.out.println("Operation Reussie !");
                    
                    else
                        System.out.println("Erreur, Operation non Reussie !");
                    break;
                    
                case 9: //listeMembre
                    if(actionListeMembres())
                        System.out.println("Operation Reussie !");
                    else
                        System.out.println("Erreur, Operation non Reussie !");
                        
                    break;
                case 10://EmployeBibliotehque
                    if(actionEmployeBibliotheque())
                        System.out.println("Operation Reussie");
                    else
                        System.out.println("Erreur, Operation non Reussie !");
                        
                    break;
                    
                case 13: //Case Test
                    break;
                     
                case 0:
                    System.out.println("Fin du programme.");
                    System.exit(0);
                    break;
                    
                default:
                    System.out.println("Erreur de saisie");
                    break;
                
                
        }}while(choix1!=0);
        
    }
    // Choix des données/////////////////////////////////////////////////////////////////////////////////
    
    public static boolean modifierDocumentChoix(int indiceDoc,int type) throws ErreurChoix
    {
        
        int choixModif;
        do{
            System.out.println("///////////////////////////////////////////////////////"
                                +"\nQuel Modification voulez-vous realisez ?"
                                +"\n1 : CodeArchivage"
                                +"\n2 : Titre"
                                +"\n3 : Auteur"
                                +"\n4 : Annee");
            if(type==2)
                System.out.println("5 : Ajouter un Morceau"
                                  +"\n6 : Supprimer un Morceau");
            if(type==3)                    
                System.out.println("5 : Url ( docUrl )"
                                  +"\n6 : Description ( docUrl )");
            System.out.println("\n///////////////////////////////////////////////////////"
                                +"\nQuel modification voulez-vous exécutez ? ");
            choixModif=choisirEntier();
            if((choixModif>0 && choixModif<5 && type==1) || ((choixModif>0 && choixModif<7) && (type==2 || type==3)))
                System.out.println("Entrez la modification : ");
            else 
                throw new ErreurChoix();
            
            switch(type)
            {
                case 1://Livre
                    if(modifierLivre(indiceDoc,choixModif))
                        return true;
                    break;
                case 2://cd
                    if(modifierCd(indiceDoc,choixModif))
                        return true;
                    break;
                    
                case 3://DocUrl
                    if(modifierDocUrl(indiceDoc,choixModif))
                        return true;
                    break;
                default:
                    System.out.println("Erreur type de document");
                    break;     
                    
            }
            
        }while (choixModif<=0 || choixModif>=7);
        return false;
        
    }
    public static int choixIdDocument()
    {
        
        int choix;
        System.out.println("///////////////////////////////////////////////////////"
                                +"\nIl existe actuellement "+catalogue.compteTout()+" documents dans le catalogue. \nVoici leur id suivit de leur nom");
                                catalogue.afficherNomDoc();   
        System.out.println("///////////////////////////////////////////////////////"
                                +"\nDonnez l'id pour le document en question :");
        choix=choisirEntier();
       
        return choix;
    }
    public static int choixTypeDocument(boolean tout) //Pour la modifications des objets ou l'ajout ou l'affichage
    {
        
        int choix;
        do{
            System.out.println("Merci de donnez le type de document choisi :"
                                +"\n1 : Livre"
                                +"\n2 : Cd"
                                +"\n3 : DocUrl");
            if(tout)
                System.out.println("4 : Tout");
            System.out.print("choix : ");
            choix=choisirEntier();
            if((tout==false && choix==4)|| choix<=0 || choix>4)
            {
                System.out.println("Erreur : ce choix est indisponible");
            }
        }while(choix<1 || choix>4);
        return choix;
    }
    
    public static boolean modifierMembreChoix(int indiceMembre)
    {
        
        int choixModif;
        String choixString;
        do{
            System.out.println("///////////////////////////////////////////////////////"
                                +"\nQuel Modification voulez-vous realisez ?"
                                +"\n1 : Nom"
                                +"\n2 : Prenom"
                                +"\n3 : Numero de telephone"
                                +"\n4 : Adresse"
                                +"\n///////////////////////////////////////////////////////"
                                +"\nQuel action voulez-vous exécutez ? ");
            choixModif=choisirEntier();
            System.out.println("Entrez la modification : ");
            switch(choixModif)
            {
                case 1:
                    choixString=choisirString();
                    if(listeMembres.accesMembre(indiceMembre).setNom(choixString))
                        return true;
                    break;
                case 2:
                    choixString=choisirString();
                    if(listeMembres.accesMembre(indiceMembre).setPrenom(choixString))
                        return true;
                    break;
                case 3:
                    choixString=choisirString();
                    if(listeMembres.accesMembre(indiceMembre).setNumeroTel(choixString))
                        return true;
                    break;
                case 4:
                    choixString=choisirString();
                    if(listeMembres.accesMembre(indiceMembre).setAdresse(choixString))
                        return true;
                    break;
                default:
                    System.out.println("Erreur choix");
                    break;
            }
         
        }while (choixModif<1 && choixModif>4);
       return false;
        
    }
    public static int choixIdMembre()
    {
        
        int choix;
        System.out.println("///////////////////////////////////////////////////////"
                                +"\nIl existe actuellement "+listeMembres.CompteMembres()+" membres dans le catalogue : \nVoici leur id suivit de leur nom");
                                listeMembres.afficherNomMembres();   
        System.out.println("///////////////////////////////////////////////////////"
                                +"\nDonnez l'id pour le membre en question :");
        choix=choisirEntier();
        return choix;
    }
    public static int choixTypeMembre() //Pour la modifications des objets ou l'ajout ou l'affichage
    {
        
        int choix;
        do{
            System.out.println("Merci de donnez le type de membre choisi :"
                                +"\n1 : membre Etudiant"
                                +"\n2 : Membre Personnel");
            System.out.print("Choix :");choix=choisirEntier();
            
            if(choix <1 || choix>2)
            {
                System.out.println("Erreur : ce choix est indisponible");         
            }
        }while(choix<1 || choix>2);
        return choix;
    }
    
    public static boolean actionCatalogueChoix() 
    {
        
        int choix;
        do{
            
            System.out.println("///////////////////////////////////////////////////////"
                                +"\nQuel action voulez-vous realisez ?"
                                +"\n1  : Ajouter un document"
                                +"\n2  : Supprimer un document"
                                +"\n3  : Afficher les informations des documents"
                                +"\n4  : Afficher les documents actuellement empruntes"
                                +"\n5  : Afficher le nombre de document (livre, cd ou docUrl)"
                                +"\n///////////////////////////////////////////////////////"
                                +"\nQuel action voulez-vous exécutez ? ");
            choix=choisirEntier();
            switch(choix)
            {
                case 1:
                    if(ajoutCatalogue(choixTypeDocument(false)))
                        return true;
                    break;
                        
                case 2:
                    if(suppressionCatalogue(choixTypeDocument(false)))
                        return true;
                    break;
                   
                case 3:
                    try{
                        catalogue.afficherDocuments(choixTypeDocument(true));
                        return true;
                    }catch(ErreurChoix e)
                    {
                        System.out.println(e);
                    }break;
                    
                case 4:
                    catalogue.afficheDocsEmpruntes();
                    return true;
    
                case 5:
                    switch(choixTypeDocument(true))
                    {
                        case 1:
                            catalogue.compteLivres();
                            return true;
                        case 2:
                            catalogue.compteCDs();
                            return true;
                        case 3:
                            catalogue.compteDocUrls();
                            return true;
                        case 4:
                            catalogue.compteLivres();
                            catalogue.compteCDs();
                            catalogue.compteDocUrls();
                            return true;
                        default:
                            System.out.println("Erreur de saisie");
                            break;
                        
                    }break;
                    
                default:
                    System.out.println("Erreur de saisie");
                    break;
            }
        }while (choix<1 || choix>5);
        return false;
    }
    public static boolean ajoutCatalogue(int type)
    {
        String codeArchivage,titre,auteur; int annee;   //Tout
        String isbn,editeur; int nbrPages;              //Livre
        String morceau;ArrayList<String> Morceaux = new ArrayList<>(); //CD
        String url,description;                         //DocUrl;
        System.out.println("///////////////////////////////////////////////////////");
        System.out.println("CodeArchivage ? "); codeArchivage=choisirString();
        System.out.println("Titre ? ")        ; titre=choisirString();
        System.out.println("Auteur ? ")       ; auteur=choisirString();
        System.out.println("Annee ? ")        ; annee=choisirEntier();
        try {    
        switch (type) {
        //Livre
            case 1:
                System.out.println("ISBN ? ")      ;
                isbn=choisirString();
                System.out.println("Editeur ? ")   ;
                editeur=choisirString();
                System.out.println("Nombre de Pages ? ");
                nbrPages=choisirEntier();
                if(catalogue.ajouter(new Livre(isbn,codeArchivage,titre,auteur,editeur,annee, nbrPages)))
                {
                    System.out.println("Ajout reussie du livre");
                    return true;
                }
                else
                {
                    System.out.println("Erreur, Ajout non reussie du livre");
                }
                break;
        //Cd
            case 2:
                while(true)
                {
                    System.out.println("Voulez-vous ajoutez un Morceau ? (oui : Entree un nom / non : tapez 0")      ; morceau=choisirString();
                    if(morceau.equals("0"))
                        break; // On sort du while
                    else
                        Morceaux.add(morceau);
                }   if(catalogue.ajouter(new CD(codeArchivage,titre,auteur,annee,Morceaux)))
                {
                    System.out.println("Ajout reussie du cd");
                    return true;
                }
                else
                {
                    System.out.println("Erreur, Ajout non reussie du livre");
                }
                break;
        //DocUrl
            case 3:
                System.out.println("Url ? ")              ;
                url=choisirString();
                System.out.println("Description ? ")      ;
                description=choisirString();
                if(catalogue.ajouter(new DocURL(codeArchivage, titre, auteur, annee, url, description)))
                {
                    System.out.println("Ajout reussie du docUrl");
                    return true;
                }
                else
                    System.out.println("Erreur, Ajout non reussie du docUrl");
                break;
            default:
                System.out.println("Erreur de type");
                break;
        }
        }catch(ErreurDoublon | ErreurAjout e)
        {
            System.out.println(e);
        }
        System.out.println("///////////////////////////////////////////////////////");
        return false;
    }
    
    public static boolean suppressionCatalogue(int type)
    {
        
        String titre;
        System.out.println("Titre du document a supprimer ? "); titre=choisirString();
        try {
        if(catalogue.supprimer(titre))
        {
            System.out.println("Suppression reussie");
            return true;
        }
        }catch(ErreurSuppression e)
        {
            System.out.println(e);
        }
        return false;
        
    }
    
    public static boolean actionListeMembres()
    {
        
        int choix;
        do{
            
            System.out.println("///////////////////////////////////////////////////////"
                                +"\nQuel action voulez-vous realisez ?"
                                +"\n1  : Ajouter un membre"
                                +"\n2  : Supprimer un membre"
                                +"\n3  : Afficher des informations sur les membres (Membre Etudiant et Membre Personnel)"
                                +"\n///////////////////////////////////////////////////////"
                                +"\nQuel action voulez-vous exécutez ? ");
            choix=choisirEntier();
            switch(choix)
            {
                case 1:
                    if(ajoutListeMembres(choixTypeMembre()))
                        return true;
                    break;
                case 2:
                    if(SuppressionListeMembres())
                        return true;
                    break;
                case 3:
                    try {
                    listeMembres.accesMembreTout(3);
                    }catch(ErreurChoix e)
                    {
                        System.out.println(e);
                    }
                    return true;
                default:
                    System.out.println("Erreur de saisie");
                    break;
            }
        }while(choix<1 || choix>4);
        return false;
    }
    public static boolean ajoutListeMembres(int type)
    {
        String nom,prenom,tel,adresse;
        System.out.println("///////////////////////////////////////////////////////");
        System.out.println("Nom ?"); nom=choisirString();
        System.out.println("Prenom ?"); prenom=choisirString();
        System.out.println("Numero de telephone ?"); tel=choisirString();
        System.out.println("Adresse ?"); adresse=choisirString();
        System.out.println("///////////////////////////////////////////////////////");
        try {
        switch (type) {
        //MembreEtudiant
            case 1:
                if(listeMembres.ajouter(new MembreEtudiant(nom, prenom, tel, adresse)))
                    return true;
                break;
        //MembrePersonnel
            case 2:
                if(listeMembres.ajouter(new MembrePersonnel(nom, prenom, tel, adresse)))
                    return true;
                break;
            default:
                System.out.println("Erreur type");
                break;
        }}catch(ErreurDoublon | ErreurAjout e)
        {
            System.out.println(e);
        }
        return false;
    }
    
    public static boolean SuppressionListeMembres()
    {
        
        String nom;
        System.out.println("Nom du membre a supprimer ? "); nom=choisirString();
        try {
        if(listeMembres.supprimer(nom))
        {
            System.out.println("Suppression reussie");
            return true;
        }
        else
        {
            System.out.println("Erreur nom inconnu ou type inconnu, suppression non reussie");
            return false;
        }
        }catch(ErreurSuppression e)
        {
            System.out.println(e);
        }
        return false;
    }
    
    //Choix Scanner
    public static int choisirEntier() //on gere l'exception java.util.InputMismatchException
    {
        int nombre;
        try
        {
            nombre=sc.nextInt();
            sc.nextLine(); //On vide la ligne
        }
        catch(java.util.InputMismatchException e)
        {
            sc=new Scanner(System.in);
            nombre=-1;
            System.out.println("Erreur, cet argument est incorrect : entier positif obligatoire !");
        }
        
        return nombre;
    }
    public static String choisirString()
    {
        String chaine;
        try
        {
            chaine=sc.nextLine();
        }
        catch(java.util.InputMismatchException e)
        {
            sc=new Scanner(System.in);
            chaine="";
            System.out.println("Erreur, cet argument est incorrect : chaine de caractere obligatoire !");
        }
        return chaine;
    }
    
    //Modification des données ////////////////////////////////////////////////////////////////
    public static boolean actionDocument() throws ErreurChoix
    {
        
        int choix;
        
        do{
            
            System.out.println("///////////////////////////////////////////////////////"
                                +"\nQuel action voulez-vous realisez ?"
                                +"\n1 : Emprunter"
                                +"\n2 : Souscrire (reserver)"
                                +"\n3 : AnnulerSouscription "
                                +"\n4 : Retourner"
                                +"\n5 : Archiver"
                                +"\n///////////////////////////////////////////////////////"
                                +"\nQuel action voulez-vous exécutez ? ");
            
            choix=choisirEntier();
            
            switch(choix)
            {
                case 1:
                    if(catalogue.emprunteDoc(choixIdDocument(),listeMembres.accesMembre(choixIdMembre())))
                        return true;
                    break;
                case 2:
                    if(catalogue.souscriptionDoc(choixIdDocument(),listeMembres.accesMembre(choixIdMembre())))
                        return true;
                    break;
                case 3:
                    if(catalogue.annulerSouscriptionDoc(choixIdDocument(),listeMembres.accesMembre(choixIdMembre())))
                        return true;
                    break;
                case 4:
                    if(catalogue.retournerDoc(choixIdDocument(),listeMembres.accesMembre(choixIdMembre())))
                        return true;
                    break;
                case 5:
                    if(catalogue.archiverDoc(choixIdDocument(), listeMembres.accesMembre(choixIdMembre())))
                        return true;
                    break;
                default:
                    throw new ErreurChoix();
            }
        }while (choix<1 || choix>5);
        return false;
        
    }
    

    public static boolean modifierLivre(int indiceDoc,int action) //On gere l'exception ErreurSet
    {
        
        String choixString;
        int choixInt;
        try {
                switch(action)
                {
                    case 1:
                        choixString=choisirString();
                        if(catalogue.accesDoc(indiceDoc).setCodeArchivage(choixString))
                            return true;
                        break;
                    case 2:
                        choixString=choisirString();
                        if(catalogue.accesDoc(indiceDoc).setTitre(choixString))
                            return true;
                        break;
                    case 3:
                        choixString=choisirString();
                        if(catalogue.accesDoc(indiceDoc).setAuteur(choixString))
                            return true;
                        break;
                    case 4:
                        choixInt=choisirEntier();
                        if(catalogue.accesDoc(indiceDoc).setAnnee(choixInt))
                            return true;
                        break;


                    default :
                        System.out.println("Erreur de choix modification");
                        break;
                 }
            }
            catch(ErreurSet e)
            {
               System.out.println(e);
            }
            return false;
        
    }
    public static boolean modifierCd(int indiceDoc,int action) //On gere l'exception ErreurSet
    {
        
        String choixString;
        int choixInt;
        CD cd;
        try {
        switch(action)
        {
            case 1:
                choixString=choisirString();
                if(catalogue.accesDoc(indiceDoc).setCodeArchivage(choixString))
                    return true;
                break;
            case 2:
                choixString=choisirString();
                if(catalogue.accesDoc(indiceDoc).setTitre(choixString))
                    return true;
                break;
            case 3:
                choixString=choisirString();
                if(catalogue.accesDoc(indiceDoc).setAuteur(choixString))
                    return true;
                break;
            case 4:
                choixInt=choisirEntier();
                if(catalogue.accesDoc(indiceDoc).setAnnee(choixInt))
                    return true;
                break;
            case 5://Ajout morceau
                System.out.println("Saississez un titre de ce morceau a ajouter :");
                choixString=choisirString();
                cd=(CD)catalogue.accesDoc(indiceDoc); //On le convertie en CD pour pouvoir recuperer ces propriete
                cd.ajouterMorceau(choixString);
                if(catalogue.remplacer(cd,indiceDoc))//On met a jour le doc du catalogue a son ancien i
                    return true;
                break;
            case 6://Suppression morceau
                System.out.println("Saississez un titre a effacer de ce cd");
                choixString=choisirString();
                System.out.println("");
                cd=(CD)catalogue.accesDoc(indiceDoc); //On le convertie en CD pour pouvoir recuperer ces propriete
                if(cd.supprimerMorceau(choixString))
                {
                    if(catalogue.remplacer(cd, indiceDoc));//On met a jour le doc du catalogue a son ancien id
                        return true;
                }
                else
                    System.out.println("Erreur titre inconnu");
                break;
            default :
                System.out.println("Erreur de choix modification");
                break;
         }}
        catch(ErreurSet e)
        {
              System.out.println(e); 
        }
        return false;
    }
    public static boolean modifierDocUrl(int indiceDoc,int action) //On gere l'exception ErreurSet
    {
        
        String choixString;
        int choixInt;
        DocURL docUrl;
        try {
            switch(action)
            {
                case 1:
                    choixString=choisirString();
                    if(catalogue.accesDoc(indiceDoc).setCodeArchivage(choixString))
                        return true;
                    break;
                case 2:
                    choixString=choisirString();
                    if(catalogue.accesDoc(indiceDoc).setTitre(choixString))
                        return true;
                    break;
                case 3:
                    choixString=choisirString();
                    if(catalogue.accesDoc(indiceDoc).setAuteur(choixString))
                        return true;
                    break;
                case 4:
                    choixInt=choisirEntier();
                    if(catalogue.accesDoc(indiceDoc).setAnnee(choixInt))
                        return true;
                    break;
                case 5:
                    choixString=choisirString();
                    docUrl=(DocURL)catalogue.accesDoc(indiceDoc); //On cast (Converti type DocBibliotheque and DocURL)
                    docUrl.setUrl(choixString);
                    if(catalogue.remplacer(docUrl, indiceDoc))//On met a jout le docUrl a l'emplacement ancien
                        return true;
                    break;
                case 6:
                    choixString=choisirString();
                    docUrl=(DocURL)catalogue.accesDoc(indiceDoc); //On cast (Converti type DocBibliotheque and DocURL)
                    docUrl.setDescription(choixString);
                    if(catalogue.remplacer(docUrl, indiceDoc))//On met a jout le docUrl a l'emplacement ancien
                        return true;
                    break;
                default :
                        System.out.println("Erreur de choix modification");
                        break;
            }
        }catch(ErreurSet e)
        {
            System.out.println(e);
        }
        return false;
        
    }
    
    
    public static boolean modifierMembre(int indiceMembre,int choix)
    {
        
        String choixString;
        switch(choix)
        {
            case 1:
                choixString=choisirString();
                if(listeMembres.accesMembre(choixIdMembre()).setNom(choixString))
                    return true;
                break;
            case 2:
                choixString=choisirString();
                if(listeMembres.accesMembre(choixIdMembre()).setPrenom(choixString))
                    return true;
                break;
            case 3:
                choixString=choisirString();
                if(listeMembres.accesMembre(choixIdMembre()).setNumeroTel(choixString))
                    return true;
                break;
            case 4:
                choixString=choisirString();
                if(listeMembres.accesMembre(choix).setAdresse(choixString))
                    return true;
                break;
            default:
                System.out.println("Erreur choix");
                break;
        }
        return false;
        
    }
    
    //EmployeBibliotheque
    public static boolean actionEmployeBibliotheque()
    {
        int nombre1=0;
        int nombre2=0;
       
        do{
            System.out.println("1 : Afficher des informations sur un employe"
                             + "\n2 : Signaler un document"
                             + "\nQue voulez-vous faire ?");
            nombre1=choisirEntier();
        }while(nombre1 <1 || nombre1 >2);
        do{
            System.out.println("Il existe actuellement deux employe dans la bibliotheque :"
                             + "\n1 : "+employe1.getNom()
                             + "\n2 : "+employe2.getNom()
                             + "\nQui ?");
            nombre2 = choisirEntier();
        }while(nombre2 <1 || nombre2 >2);
        
        if(nombre1==1)
        {
            if(nombre2==1)
                System.out.println(employe1.toString());
            else if (nombre2==2)
                System.out.println(employe2.toString());
            return true;
        }
        else if(nombre1==2)
        { 
            int indiceDoc=choixIdDocument();
            //On vérifie sur le document est emprunte ou non (si oui, on le souscrit, sinon on le supprime directement.
            if(!catalogue.accesDoc(indiceDoc).getMembreEmprunteur().equals("Ce document n'est pas emprunter"))
            {   //Supression après retour (Souscription)
                System.out.println("Ce document est actuellement emprunte par = "+catalogue.accesDoc(indiceDoc).getMembreEmprunteur());
                if(nombre2==1)
                {
                    try {
                    if(catalogue.accesDoc(indiceDoc).souscription(employe1))
                        System.out.println("Dès que le document sera retourne, le livre sera supprimer du catalogue.");return true;
                    }catch(ErreurSouscription e)
                    {
                    System.out.println(e);
                    }
                }
                else if (nombre2==2)
                {
                    try {
                    if(catalogue.accesDoc(indiceDoc).souscription(employe2))
                        System.out.println("Dès que le document sera retourne, le livre sera supprimer du catalogue.");return true;
                    }catch(ErreurSouscription e)
                    {
                    System.out.println(e);
                    }
                
                }
            }
            else //Suppression
            {
                System.out.println("Ce document n'est emprunte par personne. Suppression du catalogue ce document...");
                try {
                    catalogue.supprimer(catalogue.accesDoc(indiceDoc));
                    return true;
                } catch (ErreurSuppression e) {
                    System.out.println(e);
                }
            }
            
            
        }
        return false;      
    }
    ///////////////////////////////////////////////
    //Inititalisation
    public static void initialisation()
    {
        //initialisation du catalogue/////////////////////////////////////
        //Ajout de 3 livres
        try { catalogue.ajouter(new Livre("978-3-16-148410-0","004. 178 K20PM","Introduction à Java","J. Leblanc","Didier",2015,-1)) ;}
        catch(ErreurDoublon | ErreurAjout e)
            {System.out.println(e);}
        try { catalogue.ajouter(new Livre("999-9-99-999999-9","967. 4987 T248O","Structures de Données","M. Machin","Paul",2017,5)) ;}
        catch(ErreurDoublon | ErreurAjout e)
            {System.out.println(e);}
        try {catalogue.ajouter(new Livre("test","test","test","test","test",2010,18)); }//Test pour le quota
        catch(ErreurDoublon | ErreurAjout e)
            {System.out.println(e);}
        

        //Ajout de 2 cds sans morceaux
        ArrayList<String> m=new ArrayList();
        m.add("chanson1");
        try {catalogue.ajouter(new CD("On sait pas","CD1","P Patrique",2018,m));}
        catch(ErreurDoublon | ErreurAjout e)
            {System.out.println(e);}
        m.remove(0);
        m.add("chanson2");
        try {catalogue.ajouter(new CD("On sait pas 2","CD2","C Cantine",2018,m));}
        catch(ErreurDoublon | ErreurAjout e)
            {System.out.println(e);}
        //On test l'exception NullPointerException
        try {   
            catalogue.ajouter(new CD("testNull","testNull","testNull",10,null));
        }catch(java.lang.NullPointerException e)
        {
            System.out.println("Erreur : impossible d'instancier une liste \"morceaux\" null");
        }catch(ErreurDoublon | ErreurAjout e)
            { System.out.println(e); }
        //Ajout d'un doc url
        try {
        catalogue.ajouter(new DocURL("On sait pas 3","DocURL 1","D Danone",1978,"http://www.siteweb.com/","blablabla")) ;}
        catch(ErreurDoublon | ErreurAjout e)
            {System.out.println(e);}
        
        //Initialisation de la listeMembre /////////////////////////////////////////////////
        //Ajout de 2 Etudiants
        try {
        listeMembres.ajouter(new MembreEtudiant("Patric","Frita","0687532136","15 rue des champignons"));
        }catch(ErreurDoublon | ErreurAjout e)
        {
            System.out.println(e);
        }
        try {
        listeMembres.ajouter(new MembreEtudiant("Jacque","Jean","0687532136","33 allee du pain"));
        }catch(ErreurDoublon | ErreurAjout e)
        {
            System.out.println(e);
        }
        //Ajout de 2 Personnel
        try {
        listeMembres.ajouter(new MembrePersonnel("PartDieu","Jean","0784563210","3 rue des Potiers"));
        }catch(ErreurDoublon | ErreurAjout e)
        {
            System.out.println(e);
        }
        try {
        listeMembres.ajouter(new MembrePersonnel("Nini","Pa","0784563210","18 route des rats"));
        }catch(ErreurDoublon | ErreurAjout e)
        {
            System.out.println(e);
        }
        
    }
    
    
        
}
    
