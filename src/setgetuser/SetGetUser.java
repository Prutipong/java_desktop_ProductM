/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package setgetuser;

/**
 *
 * @author nuzromio
 */
public class SetGetUser {
    public static String Susername;
    
    public void setUsername(String username){
        Susername = username ;
    }
    
    public static String getUsername(){
        return Susername;
    }
}
