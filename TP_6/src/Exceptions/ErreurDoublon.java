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
public class ErreurDoublon extends Exception{
    public ErreurDoublon()
    {
        super(" : Ce document ne peut pas etre ajoute, il est surement deja dans le catalogue");
    }
}
