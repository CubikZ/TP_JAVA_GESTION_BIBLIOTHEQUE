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
public class ErreurArchiver extends Exception{
    
    public ErreurArchiver()
    {
        super("Erreur,ce document ne peut pas etre archiver car il n est pas dans la pile des retours");
    }
    
}
