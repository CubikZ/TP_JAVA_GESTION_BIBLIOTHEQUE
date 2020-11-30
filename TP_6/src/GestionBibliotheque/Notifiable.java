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
public interface Notifiable {
    
    public boolean docDisponible(DocBibliotheque d);//false --> l'etudiant n'emprunte pas / true --> l'etudiant emprunte
    public String getNom(); //Permet d'avoir le nom d'un type Notifiable
}
