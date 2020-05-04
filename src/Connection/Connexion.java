/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import com.bmc.arsys.api.ARServerUser;





/**
 *
 * @author Vdc_Dsk
 */
public class Connexion {
    
        String userName = "NMCnetcool";  
        String userPassword = "Password0123@";
        String serverName = "10.200.172.111";
        ARServerUser ctx = new ARServerUser();

    public ARServerUser getConnexion() {
        String result = null;
        
        try {
            ctx.setServer(serverName);
            ctx.setUser(userName);  
            ctx.setPassword(userPassword);
            ctx.login();
            result = "Connection succefully"; 
        } catch (Exception e) {
            result = e.getMessage();
        }
        
       return ctx;
    }
     
       
    
}
