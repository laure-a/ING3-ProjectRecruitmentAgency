/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author laure
 */
public class Agency{
    
    private String name, password;
    
    public Agency (String name, String password){
        this.name=name;
        this.password=password;
    }
    
    public String getName(){
        return name;
    }
    
    public String getPassword(){
        return password;
    }
    
}
