/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package performancenmc;

import Connection.Connexion;
import Fenetre.NMC_IHM;
import Metier.AllMethods;
import NMC.MTTC;
import NMC.MTTH;
import NMC.MTTL;
import com.bmc.arsys.api.ARServerUser;

/**
 *
 * @author Vdc_Dsk
 */
public class PerformanceNMC {

     static String userName = "NMCnetcool";  
     static   String userPassword = "Password0123@";
      static  String serverName = "10.200.172.111";
       static ARServerUser ctx = new ARServerUser();
    public static void main(String[] args) {
//        try {
//            ctx.setServer(serverName);
//            ctx.setUser(userName);  
//            ctx.setPassword(userPassword);
//            ctx.login();
//            System.out.println("Connexion Okkkk");
////            System.out.println("MTTL ====> "+ MTTL.moyenneMTTL("masialar", ctx));
////            System.out.println("MTTH ====> "+ MTTH.moyenneMTTH("masialar", ctx));
////            System.out.println("MTTC =====> " + MTTC.moyenneMTTC("masialar", ctx));
////            System.out.println("MTTC =====> " + MTTC.moyenneMTTC("masialar", ctx));
//            AllMethods.extraireDate("[Timestamp=1536269584]");
//        } catch (Exception e) {
//            System.out.println("Erreur : " + e.getMessage());
//        }
//        
        
        NMC_IHM nmc_ihm = new NMC_IHM();
        nmc_ihm.setVisible(true);
    }
    
    
}
