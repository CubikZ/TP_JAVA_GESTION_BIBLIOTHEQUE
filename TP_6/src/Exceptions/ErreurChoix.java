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
public class ErreurChoix extends Exception{
    public ErreurChoix()
    {
        super(" : ce choix est indisponible");
    }
}
