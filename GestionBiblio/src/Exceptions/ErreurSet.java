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
public class ErreurSet extends Exception{
    public ErreurSet()
    {
        super(" : erreur pendant la modification de la donnee");
    }
}
