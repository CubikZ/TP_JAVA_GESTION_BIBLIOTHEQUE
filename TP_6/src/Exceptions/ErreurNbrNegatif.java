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
public class ErreurNbrNegatif extends Exception{
    public ErreurNbrNegatif(String objet)
    {
        super(" : Impossible d'utiliser un nombre negatif pour "+objet+" !");
    }
}
