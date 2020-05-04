/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metier;

import Entity.Ticket;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import jxl.write.WritableCellFormat;
import jxl.CellView;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 *
 * @author Vdc_Dsk
 */
public class WriteExcel {
 
    private WritableCellFormat timesBoldUnderline;
    private WritableCellFormat times;
    private String inputFile;
    
    public void setOutputFile(String inputFile) {
        this.inputFile = inputFile;
    }
    
    public void write(int mttl, int mtth, int mttc, String duration) throws IOException, WriteException {
    File file = new File(inputFile);
    WorkbookSettings wbSettings = new WorkbookSettings();

    wbSettings.setLocale(new Locale("en", "EN"));

    WritableWorkbook workbook = Workbook.createWorkbook(file, wbSettings);
    workbook.createSheet("Report", 0);
    WritableSheet excelSheet = workbook.getSheet(0);
    createLabel(excelSheet);
    createContent(excelSheet, mttl, mtth, mttc, duration);

    workbook.createSheet("All tickets", 1);
    WritableSheet excelSheetTicket = workbook.getSheet(1);
    createLabelTicket(excelSheetTicket);
    createContentAllTicket(excelSheetTicket);
    
    workbook.write();
    workbook.close();
    
  }
    
    private void createLabel(WritableSheet sheet) throws WriteException {
    // Lets create a times font
    WritableFont times10pt = new WritableFont(WritableFont.TIMES, 10);
    // Define the cell format
    times = new WritableCellFormat(times10pt);
    // Lets automatically wrap the cells
    times.setWrap(true);

    // create create a bold font with unterlines
    WritableFont times10ptBoldUnderline = new WritableFont(WritableFont.TIMES, 12, WritableFont.BOLD, false,
        UnderlineStyle.SINGLE);
    timesBoldUnderline = new WritableCellFormat(times10ptBoldUnderline);
    // Lets automatically wrap the cells
    timesBoldUnderline.setWrap(true);

    CellView cv = new CellView();
    cv.setFormat(times);
    cv.setFormat(timesBoldUnderline);
    cv.setAutosize(true);

    // Write a few headers
    addCaption(sheet, 0, 0, "NMC Workforce for Network Controller");
    addCaption(sheet, 0, 1, "MTTL");
    addCaption(sheet, 1, 1, "MTTH");
    addCaption(sheet, 2, 1, "MTTC");
  }
    
      private void createLabelTicket(WritableSheet sheet) throws WriteException {
    // Lets create a times font
    WritableFont times10pt = new WritableFont(WritableFont.TIMES, 11);
    // Define the cell format
    times = new WritableCellFormat(times10pt);
    // Lets automatically wrap the cells
    times.setWrap(true);

    // create create a bold font with unterlines
    WritableFont times10ptBoldUnderline = new WritableFont(WritableFont.TIMES, 10, WritableFont.BOLD, false,
        UnderlineStyle.NO_UNDERLINE);
    timesBoldUnderline = new WritableCellFormat(times10ptBoldUnderline);
    // Lets automatically wrap the cells
    timesBoldUnderline.setWrap(true);

    CellView cv = new CellView();
    cv.setFormat(times);
    cv.setFormat(timesBoldUnderline);
    cv.setAutosize(true);

    // Write a few headers
    addCaption(sheet, 0, 0, "Resume all Ticket");
    addCaption(sheet, 0, 3, "Informations");
    addCaption(sheet, 0, 5, "ID TT");
    addCaption(sheet, 0, 6, "User");
    addCaption(sheet, 0, 7, "Summary");
    addCaption(sheet, 0, 8, "Description");
    addCaption(sheet, 0, 9, "Status");
    addCaption(sheet, 0, 10, "Create Date");
    addCaption(sheet, 0, 11, "Start Time");
    addCaption(sheet, 0, 12, "End Time");
    addCaption(sheet, 0, 13, "HO Time");
    addCaption(sheet, 0, 14, "Assign to");
  }
    private void createContentAllTicket(WritableSheet sheet) throws WriteException, RowsExceededException {
      
        List<Ticket> Tickets = new ArrayList<>();
        Tickets = AllMethods.getTicketDeja();
        int parc = 1;
        for (Ticket ticket : Tickets) {
            addLabel(sheet, parc, 5, ticket.getTrouble_Ticket_ID());
            addLabel(sheet, parc, 6, ticket.getSubmitter());
            addLabel(sheet, parc, 7, ticket.getSummary());
            addLabel(sheet, parc, 8, ticket.getDescription());
            addLabel(sheet, parc, 9, ticket.getStatus());
            addLabel(sheet, parc, 10, ticket.getCreate_Date());
            addLabel(sheet, parc, 11, ticket.getStart_Time());
            addLabel(sheet, parc, 12, ticket.getEnd_Time());
            addLabel(sheet, parc, 13, ticket.getHO_Time());
            addLabel(sheet, parc, 14, ticket.getIndividual());
            parc++;
        }
      
     // addLabel(sheet, 3, 4, duration);
  }

    private void createContent(WritableSheet sheet, int mttl, int mtth, int mttc, String duration) throws WriteException,
      RowsExceededException {
    // Write a few number
//    for (int i = 2; i < 11; i++) {
      // First column
      addNumber(sheet, 0, 2, mttl);
      // Second column
      addNumber(sheet, 1, 2, mtth);
      // Three column
      addNumber(sheet, 2, 2, mttc);
//    }
    // Lets calculate the sum of it
//    StringBuffer buf = new StringBuffer();
//    buf.append("SUM(A2:A10)");
//    Formula f = new Formula(0, 10, buf.toString());
//    sheet.addCell(f);
//    buf = new StringBuffer();
//    buf.append("SUM(B2:B10)");
//    f = new Formula(1, 10, buf.toString());
//    sheet.addCell(f);

//    for (int i = 13; i < 21; i++) {
//      // First column
//      addLabel(sheet, 0, i, "Boring text " + i);
//      // Second column
      addLabel(sheet, 2, 4, "Duration");
      addLabel(sheet, 3, 4, duration);
//    }
  }
  
 private void addCaption(WritableSheet sheet, int column, int row, String s)
      throws RowsExceededException, WriteException {
    Label label;
    label = new Label(column, row, s, timesBoldUnderline);
    sheet.addCell(label);
  }

  private void addNumber(WritableSheet sheet, int column, int row, Integer integer) throws WriteException, RowsExceededException {
    Number number;
    number = new Number(column, row, integer, times);
    sheet.addCell(number);
  }
  
  private void addLabel(WritableSheet sheet, int column, int row, String s)
      throws WriteException, RowsExceededException {
    Label label;
    label = new Label(column, row, s, times);
    sheet.addCell(label);
  }

}
