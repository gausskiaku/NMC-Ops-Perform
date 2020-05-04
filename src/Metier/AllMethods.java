/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metier;

import Entity.Ticket;
import Entity.User;
import com.bmc.arsys.api.ARException;
import com.bmc.arsys.api.ARServerUser;
import com.bmc.arsys.api.Entry;
import com.bmc.arsys.api.EntryListInfo;
import com.bmc.arsys.api.QualifierInfo;
import com.bmc.arsys.api.Timestamp;
import com.bmc.arsys.api.UserInfo;
import com.bmc.arsys.api.Value;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import org.joda.time.DateTime;
import org.joda.time.Period;

/**
 *
 * @author Vdc_Dsk
 */
public class AllMethods {
    
   
    static int total_min = 0; 
    static String formName= "MOBILE-NMC:Trouble Ticket";
    static String formUser= "User";
    static List<Ticket> ticketDeja = new ArrayList<>();

    public static List<Ticket> getTicketDeja() {
        return ticketDeja;
    }

    public static void setTicketDeja(List<Ticket> ticketDeja) {
        AllMethods.ticketDeja = ticketDeja;
    }

  
    
     public static List<Ticket> getAllTicketbyOps(String nomOPS, String dateStart, String dateEnd, ARServerUser ctx){
        ticketDeja.clear();
        Date dateS = new Date(dateStart+" 12:00 AM");
        Timestamp dtStart = new Timestamp(dateS);
        
        Date dateE = new Date(dateEnd+" 11:59 PM");
        Timestamp dtEnd = new Timestamp(dateE);
         
         
         
        List<Ticket> listTicket = new ArrayList<Ticket>(); 
        String queryString = "('Submitter' = \""+nomOPS+"\") AND ('Create Date' > "+dtStart.getValue()+" AND 'Create Date' <= "+dtEnd.getValue()+")"; // AND '+Date_1' <= \""+"04/20/2016 "+"\"
        
        try {
            QualifierInfo qual = ctx.parseQualification(formName, queryString);
            List<EntryListInfo> eListInfos = ctx.getListEntry(formName, qual, 0, Integer.MAX_VALUE, null, null, false, null);
            
            for (EntryListInfo eListInfo : eListInfos) {
                
                Ticket ticket = new Ticket();
                Entry record = ctx.getEntry(formName, eListInfo.getEntryID(), null);
                String a = record.get(536871178).toString();
                // Traitement Activity Accepted
               
                if (a.indexOf("TT Accepted") > -1 ){
                String[] b = a.split(",Text=TT Accepted");
                String[] c = b[0].split("Val=");
                int valt = c.length - 1;
                ticket.setAccepted_Time(extraireDate(c[valt]).getMillis()); //c[valt]
                }
                // Traitement Activity
                
                String[] b = a.split("Val=");
                String[] c = b[1].split(",");
                
                // Ticket String
                ticket.setTrouble_Ticket_ID(record.get(1).toString());
                ticket.setSubmitter(record.get(2).toString());
                ticket.setCreate_Date(extraireDate(record.get(3).toString()).toString("dd/MM/yyyy HH:mm:ss"));
                ticket.setStart_Time(extraireDate(record.get(710500001).toString()).toString("dd/MM/yyyy HH:mm:ss"));
                try {
                    ticket.setEnd_Time(extraireDate(record.get(710500002).toString()).toString("dd/MM/yyyy HH:mm:ss")); // 
                } catch (Exception e) {
                     ticket.setEnd_Time(null);
                }
                ticket.setHO_Time(extraireDate(record.get(536871003).toString()).toString("dd/MM/yyyy HH:mm:ss"));
                ticket.setIndividual(record.get(4).toString());
                
                 // For Status
                String status = "";
                if (record.get(7).getIntValue() == 2){
                status = "WIP";
                } else if (record.get(7).getIntValue() == 3){
                    status = "Pending";
                } else if ( record.get(7).getIntValue() == 1) {
                     status = "Assigned";
                } else if (record.get(7).getIntValue() == 5){
                     status = "Closed";
                } else if (record.get(7).getIntValue() == 4){
                     status = "Resolved";
                }
                
                ticket.setStatus(status);
                ticket.setDescription(record.get(536870941).toString());
                ticket.setSummary(record.get(536870920).toString());
                
                ticket.setClose_Time(c[0]);
                
                // Ticket Long
                try {
                    ticket.setCreate_Date_Millis(extraireDate(record.get(3).toString()).getMillis());
                } catch (Exception e) {
                    ticket.setCreate_Date_Millis(0);
                }
                
                try {
                    ticket.setStart_Time_Millis(extraireDate(record.get(710500001).toString()).getMillis());
                } catch (Exception e) {
                    ticket.setStart_Time_Millis(0);
                }
                
                try {
                    ticket.setEnd_Time_Millis(extraireDate(record.get(710500002).toString()).getMillis());
                } catch (Exception e) {
                    ticket.setEnd_Time_Millis(0);
                }
                try {
                    ticket.setHO_Time_Millis(extraireDate(record.get(536871003).toString()).getMillis());
                } catch (Exception e) {
                    ticket.setHO_Time_Millis(0);
                }
                try {
                    ticket.setClose_Time_Millis(extraireDate(c[0]).getMillis());
                } catch (Exception e) {
                    ticket.setClose_Time_Millis(0);
                }
                
                int min= (int) (((extraireDate(record.get(3).toString()).getMillis() - extraireDate(record.get(710500001).toString()).getMillis()) / 1000) / 60);
                int calMin = Math.abs(min);
                
//                Period period = new Period(extraireDate(record.get(3).toString()), extraireDate(record.get(710500001).toString()));
               
                total_min = total_min + calMin;
                listTicket.add(ticket);
                ticketDeja.add(ticket);
                
            }
        } catch (ARException e) {
            new ARExceptionHandler (e, "Problem while querying by OPS.", ctx);
            JOptionPane.showMessageDialog(null, "Error AllMerthods by OPS : " + e.getMessage());
        } 
    return listTicket;
    }
     
     
     public static List<Ticket> getAllTicketClose(String nomOPS, String dateStart, String dateEnd, ARServerUser ctx){
        
         Date dateS = new Date(dateStart+" 12:00 AM");
        Timestamp dtStart = new Timestamp(dateS);
        
        Date dateE = new Date(dateEnd+" 11:59 PM");
        Timestamp dtEnd = new Timestamp(dateE);
        
        List<Ticket> listTicket = new ArrayList<Ticket>(); 
        String queryString = "('Submitter' = \""+nomOPS+"\") AND ('Create Date' > "+dtStart.getValue()+" AND 'Create Date' <= "+dtEnd.getValue()+" AND 'Status' = 5)";
        
        try {
            QualifierInfo qual = ctx.parseQualification(formName, queryString);
            List<EntryListInfo> eListInfos = ctx.getListEntry(formName, qual, 0, Integer.MAX_VALUE, null, null, false, null);
        
            for (EntryListInfo eListInfo : eListInfos) {
                
                Ticket ticket = new Ticket();
                Entry record = ctx.getEntry(formName, eListInfo.getEntryID(), null);
                String a = record.get(536871178).toString();
                if(record.get(7).getIntValue() == 5) { 
                    
                    if (a.indexOf("TT was Resolved") > -1 ){ //TT was Resolved
                String[] b = a.split(",Text=TT was Resolved");
                String[] c = b[0].split("Val=");
                int valt = c.length - 1;
                ticket.setResolved_Time_Millis(extraireDate(c[valt]).getMillis());
                                }
                
                 String[] b = a.split("], ");
                 
                 int vv = b.length - 1;
                 String b1 = b[vv];
//                 for (String b1 : b) {
                     
                     String[] c = b1.split("User=");
                     String[] d = c[1].split(",Time Val=");
                     // d[0] = nom de User
                     
                     String[] e = d[1].split(",Text=");
               
                
                // Ticket String
                ticket.setTrouble_Ticket_ID(record.get(1).toString());
                ticket.setSubmitter(record.get(2).toString());
                ticket.setCreate_Date(record.get(3).toString());
                ticket.setStart_Time(record.get(710500001).toString());
                ticket.setEnd_Time(record.get(710500002).toString());
                ticket.setHO_Time(record.get(536871003).toString());
                ticket.setClose_Time(extraireDate(e[0]).toString());
                
                
                
                // Ticket Long
                ticket.setCreate_Date_Millis(extraireDate(record.get(3).toString()).getMillis());
                ticket.setStart_Time_Millis(extraireDate(record.get(710500001).toString()).getMillis());
                ticket.setEnd_Time_Millis(extraireDate(record.get(710500002).toString()).getMillis());
                ticket.setHO_Time_Millis(extraireDate(record.get(536871003).toString()).getMillis());
                ticket.setClose_Time_Millis(extraireDate(e[0]).getMillis());
                
                listTicket.add(ticket);
                }
            }  
        } catch (ARException e) {
            new ARExceptionHandler (e, "Problem while querying by OPS.", ctx);
        } 
        
    return listTicket;
    }
     
     
    public static DateTime extraireDate(String timestamp){
        String[] a = timestamp.split("=");
        String[] b = a[1].split("]");
        long dateTimes = Long.valueOf(b[0]).longValue();
        com.bmc.arsys.api.Timestamp dateAPITimes = new Timestamp(dateTimes);
        DateTime dateJoda = new DateTime(dateAPITimes.toDate());
        return dateJoda;
    }
    
      public static List<User> AllUser(ARServerUser ctx){
        
        
        List<User> all = new ArrayList<>();
        
        try {
            List<UserInfo> listUser = new ArrayList<UserInfo>();
            
            listUser = ctx.getListUser(1);
            
            for (UserInfo listUser1 : listUser) {
               User user = new User();
                user.setUserName(listUser1.getUserName());
               all.add(user);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erreur to get All user : " + e.getMessage());
        }
         
         return all;
    }
      
      
      public static String User (ARServerUser ctx, String username){
     
        String userName = "NMCnetcool";  
        String userPassword = "Password0123@"; 
        String serverName = "10.200.172.111";
        ctx = new ARServerUser();  
            ctx.setServer(serverName);  
            ctx.setUser(userName);  
            ctx.setPassword(userPassword);
            
            String queryString = "'Login Name' = \""+username+"\" ";
            String resultat = "Error! please contact the admnistrateur in 4084 because the user does not exist";
           try { 
               ctx.login();
              QualifierInfo qual = ctx.parseQualification(formUser, queryString);
                List<EntryListInfo> eListInfos =  ctx.getListEntry(formUser, qual, 0, Integer.MAX_VALUE, null, null, false, null);
               
                for (EntryListInfo eListInfo : eListInfos) {
                  
                    Entry entry = ctx.getEntry(formUser, eListInfo.getEntryID(), null);
                    if( entry  == null ){
                                                   return resultat = "Probleme" ;
                    } else {
                    
                                 Value valFullName = entry.get(8);
//                                 Value valLoginName = entry.get(101);
//                                 Value valEmail = entry.get(103);
                                 resultat = valFullName.toString();      
               } 
                }
        } catch (ARException ex) {
           resultat = "Probleme User : " + ex.getMessage();
        }
    
   return resultat; 
    }  
      
      
       public static List<Ticket> getAllTicketOPSAccept(String nomOPS, String dateStart, String dateEnd, ARServerUser ctx){
        
        Date dateS = new Date(dateStart+" 12:00 AM");
        Timestamp dtStart = new Timestamp(dateS); 
        Date dateE = new Date(dateEnd+" 11:59 PM");
        Timestamp dtEnd = new Timestamp(dateE);
        
        List<Ticket> listTicketAccepted = new ArrayList<Ticket>(); 
        String queryString = "'Dispatch To' LIKE \"%"+nomOPS+"%\" AND ('Create Date' > "+dtStart.getValue()+" AND 'Create Date' <= "+dtEnd.getValue()+")";
        
        try {
            QualifierInfo qual = ctx.parseQualification(formName, queryString);
            List<EntryListInfo> eListInfos = ctx.getListEntry(formName, qual, 0, 200, null, null, false, null);
            for (EntryListInfo eListInfo : eListInfos) {
                
                Ticket ticket = new Ticket();
                Entry record = ctx.getEntry(formName, eListInfo.getEntryID(), null);
                String a = record.get(536871178).toString();
                
                // Ticket String
                ticket.setTrouble_Ticket_ID(record.get(1).toString());
                ticket.setSubmitter(record.get(2).toString());
                ticket.setCreate_Date(record.get(3).toString());
                ticket.setStart_Time(record.get(710500001).toString());
                ticket.setEnd_Time(record.get(710500002).toString());
                ticket.setHO_Time(record.get(536871003).toString());
                System.out.println("Ho Time ++>  " + extraireDate(record.get(536871003).toString()));

                try {
                    ticket.setCreate_Date_Millis(extraireDate(record.get(3).toString()).getMillis());
                } catch (Exception e) {
                    ticket.setCreate_Date_Millis(0);
                }
                
                try {
                    ticket.setStart_Time_Millis(extraireDate(record.get(710500001).toString()).getMillis());
                } catch (Exception e) {
                    ticket.setStart_Time_Millis(0);
                }
                
                try {
                    ticket.setEnd_Time_Millis(extraireDate(record.get(710500002).toString()).getMillis());
                } catch (Exception e) {
                    ticket.setEnd_Time_Millis(0);
                }
                try {
                    ticket.setHO_Time_Millis(extraireDate(record.get(536871003).toString()).getMillis());
                } catch (Exception e) {
                    ticket.setHO_Time_Millis(0);
                }
               
                // Traitement Activity Accepted
               
                if (a.indexOf("TT Accepted") > -1 ){
                String[] b = a.split(",Text=TT Accepted");
                System.out.println(b.length);
                String[] c = b[0].split("Val=");
                int valt = c.length - 1;
                System.out.println("Traitement Accepted : " + c[valt] );
                ticket.setAccepted_Time(extraireDate(c[valt]).getMillis()); //c[valt]
                System.out.println("Time Accepted : " + extraireDate(c[valt]));
                listTicketAccepted.add(ticket);
                } 
            }  
        } catch (ARException e) {
            new ARExceptionHandler (e, "Problem while querying by OPS.", ctx);
            JOptionPane.showMessageDialog(null, "Error AllMerthods by OPS : " + e.getMessage());
        }
         return listTicketAccepted;
    }
       
        public static List<Ticket> getAllTicketOPSResolve(String nomOPS, String dateStart, String dateEnd, ARServerUser ctx){
        
        Date dateS = new Date(dateStart+" 12:00 AM");
        Timestamp dtStart = new Timestamp(dateS); 
        Date dateE = new Date(dateEnd+" 11:59 PM");
        Timestamp dtEnd = new Timestamp(dateE); 
        List<Ticket> listTicketResolve = new ArrayList<Ticket>();  
        String queryString = "'Dispatch To' LIKE \"%"+nomOPS+"%\" AND ('Create Date' > "+dtStart.getValue()+" AND 'Create Date' <= "+dtEnd.getValue()+")";
        // 'Dispatch To' LIKE \"%"+nomOPS+"%\" AND ('Status' = 1 OR 'Status' = 2 OR 'Status' = 3)
       
        try {
            QualifierInfo qual = ctx.parseQualification(formName, queryString);
            List<EntryListInfo> eListInfos = ctx.getListEntry(formName, qual, 0, 200, null, null, false, null);
//            JOptionPane.showMessageDialog(null, "Ticket Accepte +++> "+eListInfos.size());
            for (EntryListInfo eListInfo : eListInfos) {
                
                Ticket ticket = new Ticket();
                Entry record = ctx.getEntry(formName, eListInfo.getEntryID(), null);
                String a = record.get(536871178).toString();
                
                // Ticket String
                ticket.setTrouble_Ticket_ID(record.get(1).toString());
                ticket.setSubmitter(record.get(2).toString());
                ticket.setCreate_Date(record.get(3).toString());
                ticket.setStart_Time(record.get(710500001).toString());
                ticket.setEnd_Time(record.get(710500002).toString());
                ticket.setHO_Time(record.get(536871003).toString());
                System.out.println("Ho Time ++>  " + extraireDate(record.get(536871003).toString()));

                try {
                    ticket.setCreate_Date_Millis(extraireDate(record.get(3).toString()).getMillis());
                } catch (Exception e) {
                    ticket.setCreate_Date_Millis(0);
                }
                
                try {
                    ticket.setStart_Time_Millis(extraireDate(record.get(710500001).toString()).getMillis());
                } catch (Exception e) {
                    ticket.setStart_Time_Millis(0);
                }
                
                try {
                    ticket.setEnd_Time_Millis(extraireDate(record.get(710500002).toString()).getMillis());
                } catch (Exception e) {
                    ticket.setEnd_Time_Millis(0);
                }
                try {
                    ticket.setHO_Time_Millis(extraireDate(record.get(536871003).toString()).getMillis());
                } catch (Exception e) {
                    ticket.setHO_Time_Millis(0);
                }
                if (a.indexOf("TT was Resolved") > -1 ){
                String[] b = a.split(",Text=TT was Resolved");
                System.out.println(b.length);
                String[] c = b[0].split("Val=");
                int valt = c.length - 1;
                System.out.println("Traitement Resolve : " + c[valt] );
                ticket.setResolved_Time_Millis(extraireDate(c[valt]).getMillis()); //c[valt]
                System.out.println("Time resolve : " + extraireDate(c[valt]));
                listTicketResolve.add(ticket);
                }   
            }  
        } catch (ARException e) {
            new ARExceptionHandler (e, "Problem while querying by OPS.", ctx);
            JOptionPane.showMessageDialog(null, "Error AllMerthods by OPS : " + e.getMessage());
        } 
        return listTicketResolve;
    }
     
     
}
