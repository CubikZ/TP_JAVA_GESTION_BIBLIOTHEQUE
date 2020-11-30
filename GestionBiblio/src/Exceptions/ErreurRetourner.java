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
public class ErreurRetourner extends Exception{
    
    public ErreurRetourner()
    {
        super(" : ce document ne peut pas etre retourner : vous n etes pas l emprunteur de ce document");
    }
}
