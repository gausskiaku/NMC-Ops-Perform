/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NMC;

import Entity.Ticket;
import Metier.AllMethods;
import static NMC.MTTL.Tickets;
import com.bmc.arsys.api.ARServerUser;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vdc_Dsk
 */
public class MTTH {
    static AllMethods allMethods = new AllMethods(); 
    static List<Ticket> Tickets = new ArrayList();
    
    public static long moyenneMTTH(String nomOPS, String dateStart, String dateEnd, ARServerUser ctx){
   long totalTicket = 0;
   long mtth;
   Tickets = allMethods.getAllTicketbyOps(nomOPS, dateStart, dateEnd, ctx);
   
       for (Ticket ticket : Tickets) {
          long dif_tic = (((ticket.getHO_Time_Millis() - ticket.getStart_Time_Millis()) / 1000) / 60);
          totalTicket = totalTicket + Math.abs(dif_tic); 
       }
       mtth = totalTicket / Tickets.size();
       
   return mtth; 
   }
}
