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
public class ErreurSuppression extends Exception{
    
    public ErreurSuppression()
    {
        super(" : impossible de supprimer ce document/membre du catalogue : nom inexistant ou document/membre inconnue");
    }
}
