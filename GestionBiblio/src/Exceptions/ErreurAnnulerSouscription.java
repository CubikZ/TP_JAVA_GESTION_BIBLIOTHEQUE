/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions;
/**
 *
 * @author loann
 */
public class ErreurAnnulerSouscription extends Exception{
    
    public ErreurAnnulerSouscription()
    {
        super(" : Annulation de la reservation impossible de ce document : vous n etes pas le reservant de ce document");
    }
    
}
