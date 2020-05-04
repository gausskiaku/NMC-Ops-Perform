/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OPS;

import Entity.Ticket;
import Metier.AllMethods;
import com.bmc.arsys.api.ARServerUser;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Vdc_Dsk
 */
public class MTTA {
    static AllMethods allMethods = new AllMethods(); 
 static List<Ticket> Tickets = new ArrayList();  
 
 public static long moyenneMTTA(String nomOPS, String dateStart, String dateEnd, ARServerUser ctx){
   long totalTicket = 0;
   long mtta = 0;
   Tickets = allMethods.getAllTicketOPSAccept(nomOPS, dateStart, dateEnd, ctx);
   
       for (Ticket ticket : Tickets) {
           
          long dif_tic = (((ticket.getAccepted_Time() - ticket.getHO_Time_Millis()) / 1000) / 60);
//          JOptionPane.showMessageDialog(null, "Afficher ==> "+ ticket.getAccepted_Time()+ "  -  " + ticket.getHO_Time_Millis());
          // Si ca revoie quelque chose
          if(ticket.getAccepted_Time() != 0){
          totalTicket = totalTicket + Math.abs(dif_tic);  
          }
       }
       mtta = totalTicket / Tickets.size();
       
   return mtta; 
   }
}
