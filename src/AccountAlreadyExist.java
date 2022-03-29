/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author BAC
 */
public class AccountAlreadyExist extends Exception{
   public AccountAlreadyExist(int code){
        super("La cuenta: "+code+"ya esta agregada al sistema");
    }
}
