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
public class ErreurEmprunter extends Exception{
    
    public ErreurEmprunter()
    {
        super(" : ce document ne peut pas etre emprunter");
    }
    
}
