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

/**
 *
 * @author Vdc_Dsk
 */
public class MTTR {
    
    static AllMethods allMethods = new AllMethods(); 
 static List<Ticket> Tickets = new ArrayList();  
 
 public static long moyenneMTTR(String nomOPS, String dateStart, String dateEnd, ARServerUser ctx){
   long totalTicket = 0;
   long mttr = 0;
   Tickets = allMethods.getAllTicketOPSResolve(nomOPS,dateStart, dateEnd, ctx);
   
       for (Ticket ticket : Tickets) {
           
          long dif_tic = (((ticket.getResolved_Time_Millis() - ticket.getHO_Time_Millis()) / 1000) / 60);
          // Si ca revoie quelque chose
          if(ticket.getResolved_Time_Millis() != 0){
          totalTicket = totalTicket + Math.abs(dif_tic);  
          }
       }
       mttr = totalTicket / Tickets.size();
       
   return mttr; 
 }
    
}
