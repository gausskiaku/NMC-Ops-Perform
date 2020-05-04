/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NMC;

import Entity.Ticket;
import Metier.AllMethods;
import com.bmc.arsys.api.ARServerUser;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;

/**
 *
 * @author Vdc_Dsk
 */
public class MTTL {
  
    static AllMethods allMethods = new AllMethods();
    
    static List<Ticket> Tickets = new ArrayList();
    
   public static long moyenneMTTL(String nomOPS, String dateStart, String dateEnd, JLabel ticketSize, ARServerUser ctx){
   long totalTicket = 0;
   long mttl;
   Tickets = allMethods.getAllTicketbyOps(nomOPS, dateStart, dateEnd, ctx);
   ticketSize.setText(String.valueOf(Tickets.size()).toString());
       for (Ticket ticket : Tickets) {
          long dif_tic = (((ticket.getCreate_Date_Millis() - ticket.getStart_Time_Millis()) / 1000) / 60);
          totalTicket = totalTicket + Math.abs(dif_tic); 
       }
       mttl = totalTicket / Tickets.size();
       
   return mttl; 
   }
    
    
}
