/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author Vdc_Dsk
 */
public class Ticket {
    
    
    
    
    private String Trouble_Ticket_ID;
    private String submitter;
    private String summary;
    private String description;
    private String status;
    private String Create_Date; 
    private String Start_Time;
    private String End_Time;
    private String HO_Time;
    private String Close_Time;
    private String individual;
    private User user;
    private long Create_Date_Millis;
    private long Start_Time_Millis;
    private long End_Time_Millis;
    private long HO_Time_Millis;
    private long Close_Time_Millis;
    private long Accepted_Time;
    private long Resolved_Time_Millis;

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
    public long getResolved_Time_Millis() {
        return Resolved_Time_Millis;
    }
    public void setResolved_Time_Millis(long Resolved_Time_Millis) {
        this.Resolved_Time_Millis = Resolved_Time_Millis;
    }
    public long getAccepted_Time() {
        return Accepted_Time;
    }

    public void setAccepted_Time(long Accepted_Time) {
        this.Accepted_Time = Accepted_Time;
    }

    public String getClose_Time() {
        return Close_Time;
    }

    public void setClose_Time(String Close_Time) {
        this.Close_Time = Close_Time;
    }

    public long getClose_Time_Millis() {
        return Close_Time_Millis;
    }

    public void setClose_Time_Millis(long Close_Time_Millis) {
        this.Close_Time_Millis = Close_Time_Millis;
    }

    public long getCreate_Date_Millis() {
        return Create_Date_Millis;
    }

    public void setCreate_Date_Millis(long Create_Date_Millis) {
        this.Create_Date_Millis = Create_Date_Millis;
    }

    public long getStart_Time_Millis() {
        return Start_Time_Millis;
    }

    public void setStart_Time_Millis(long Start_Time_Millis) {
        this.Start_Time_Millis = Start_Time_Millis;
    }

    public long getEnd_Time_Millis() {
        return End_Time_Millis;
    }

    public void setEnd_Time_Millis(long End_Time_Millis) {
        this.End_Time_Millis = End_Time_Millis;
    }

    public long getHO_Time_Millis() {
        return HO_Time_Millis;
    }

    public void setHO_Time_Millis(long HO_Time_Millis) {
        this.HO_Time_Millis = HO_Time_Millis;
    }

    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    

    public String getTrouble_Ticket_ID() {
        return Trouble_Ticket_ID;
    }

    public void setTrouble_Ticket_ID(String Trouble_Ticket_ID) {
        this.Trouble_Ticket_ID = Trouble_Ticket_ID;
    }

    public String getIndividual() {
        return individual;
    }

    public void setIndividual(String individual) {
        this.individual = individual;
    }

    
    public String getSubmitter() {
        return submitter;
    }

    public void setSubmitter(String submitter) {
        this.submitter = submitter;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
    public String getCreate_Date() {
        return Create_Date;
    }

    public void setCreate_Date(String Create_Date) {
        this.Create_Date = Create_Date;
    }

    public String getStart_Time() {
        return Start_Time;
    }

    public void setStart_Time(String Start_Time) {
        this.Start_Time = Start_Time;
    }

    public String getEnd_Time() {
        return End_Time;
    }

    public void setEnd_Time(String End_Time) {
        this.End_Time = End_Time;
    }

    public String getHO_Time() {
        return HO_Time;
    }

    public void setHO_Time(String HO_Time) {
        this.HO_Time = HO_Time;
    }
    
}
