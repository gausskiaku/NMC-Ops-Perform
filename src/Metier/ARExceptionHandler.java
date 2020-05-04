/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metier;

import com.bmc.arsys.api.ARException;
import com.bmc.arsys.api.ARServerUser;
import com.bmc.arsys.api.Constants;
import com.bmc.arsys.api.StatusInfo;
import java.util.List;

/**
 *
 * @author Vdc_Dsk
 */
class ARExceptionHandler {

    public ARExceptionHandler(ARException e, String errMessage, ARServerUser ctx) {
        System.out.println(errMessage);
        printStatusList(ctx.getLastStatus());
        System.out.print("Stack Trace:");
        e.printStackTrace();
    }
    
    public static void printStatusList(List<StatusInfo> statusList) {
        if (statusList == null || statusList.size()==0) {
             System.out.println("Status List is empty.");
                 return;
        }
        System.out.print("Message type: ");
        switch(statusList.get(0).getMessageType())
        {
            case Constants.AR_RETURN_OK:
                             System.out.println("Note");
                     break;
            case Constants.AR_RETURN_WARNING:
                     System.out.println("Warning");
                     break;
            case Constants.AR_RETURN_ERROR:
                     System.out.println("Error");
                         break;
            case Constants.AR_RETURN_FATAL:
                                 System.out.println("Fatal Error");
                     break;
            default:
                     System.out.println("Unknown (" +
                             statusList.get(0).getMessageType() + ")");
                     break;
        }
        System.out.println("Status List:");
        for (int i=0; i < statusList.size(); i++) {

System.out.println(statusList.get(i).getMessageText());

System.out.println(statusList.get(i).getAppendedText());
        }
    }
    
}
