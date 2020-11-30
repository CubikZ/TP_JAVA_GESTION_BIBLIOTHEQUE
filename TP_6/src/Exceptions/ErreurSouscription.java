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
public class ErreurSouscription extends Exception{
    
    public ErreurSouscription()
    {
        super(" : ce document ne peut pas etre reserver");
    }
    
}
