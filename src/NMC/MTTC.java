/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NMC;

import Entity.Ticket;
import Metier.AllMethods;
import static NMC.MTTH.Tickets;
import com.bmc.arsys.api.ARServerUser;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Vdc_Dsk
 */
public class MTTC {
 static AllMethods allMethods = new AllMethods(); 
 static List<Ticket> Tickets = new ArrayList();  
 
 public static long moyenneMTTC(String nomOPS, String dateStart, String dateEnd, JLabel ticketSize, ARServerUser ctx){
   long totalTicket = 0;
   long mttc = 0;
   Tickets = allMethods.getAllTicketClose(nomOPS, dateStart, dateEnd, ctx);
   ticketSize.setText(String.valueOf(Tickets.size()).toString());
       for (Ticket ticket : Tickets) {
          long dif_tic = (((ticket.getClose_Time_Millis() - ticket.getEnd_Time_Millis()) / 1000) / 60);
         
           if (ticket.getClose_Time_Millis() != 0 ) {
               totalTicket = totalTicket + Math.abs(dif_tic); 
           }
           
       }
       mttc = totalTicket / Tickets.size();
       
   return mttc; 
   }
}
