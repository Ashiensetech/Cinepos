package controller.web.admin.page;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import controller.web.admin.AdminUriPreFix;
import dao.SellsDao;
import dao.reportDao.ProductSummaryReportViewDao;
import entity.AuthCredential;
import entity.Sells;
import entity.SellsDetails;
import entity.tableview.report.ProductSummaryReportView;
import helper.DateHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping(AdminUriPreFix.pageUriPrefix+"/report-pdf")
public class AdminReportPdfController {
    @Autowired
    ProductSummaryReportViewDao productSummaryReportViewDao;

    @Autowired
    SellsDao sellsDao;



    @RequestMapping(value="/product-sale-summary/download")
    public void productSaleSummaryPdf(Authentication authentication,
                                      HttpServletResponse response,
                                      @RequestParam(value = "startDate",required = false) String startDate,
                                      @RequestParam(value = "endDate",required = false) String endDate){

        AuthCredential authCredential =  (AuthCredential)authentication.getPrincipal();
        String fileName = "product_sales_summary.pdf";
        Document document = new Document();


        Timestamp sDate = null;
        Timestamp eDate = null;

        Timestamp fDate=null;

        String sDateStr="";
        String eDateStr="";

        if(startDate!=null && !startDate.trim().equals("")){
            try {
                sDate = DateHelper.getStringToTimeStamp(startDate + " 00:00:00", "yyyy-MM-dd H:m:s");
                System.out.println(sDate);
            } catch (ParseException e) {

            }
        }
        if(endDate!=null && !endDate.trim().equals("")){
            try {
                eDate = DateHelper.getStringToTimeStamp(endDate + " 23:59:59", "yyyy-MM-dd H:m:s");
                System.out.println(eDate);
            } catch (ParseException e) {

            }
        }


        List<ProductSummaryReportView> productSummaryReportViewList = new ArrayList<>();

        System.out.println(sDate);

        if(sDate!=null && eDate!=null){
            productSummaryReportViewList = productSummaryReportViewDao.getByDateRange(sDate, eDate);
        }else if(sDate!=null) {

            productSummaryReportViewList = productSummaryReportViewDao.getByDateRange(sDate, sDate);

            try {
                Timestamp tmpSData = DateHelper.getStringToTimeStamp(startDate+ " 00:00:00", "yyyy-MM-dd H:m:s");
                Timestamp tmpEData = DateHelper.getStringToTimeStamp(startDate + " 23:59:59", "yyyy-MM-dd H:m:s");
                productSummaryReportViewList = productSummaryReportViewDao.getByDateRange(tmpSData, tmpEData);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }else{
            productSummaryReportViewList = productSummaryReportViewDao.getAll();
        }


        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm:ss");

        String printingDate = formatDate.format( new java.util.Date());
        String printedTime = formatTime.format( new java.util.Date());


        if(sDate!=null)
            sDateStr = formatDate.format(sDate);


        if(eDate!=null)
            eDateStr = formatDate.format(eDate);



        int counter = 1;
        try {
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

            Font regular = new Font(Font.FontFamily.HELVETICA, 12);
            Font bold = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
            Phrase p = new Phrase("Products Sales Summary", bold);


            document.add(p);

            System.out.println(authCredential.getUserName());

            document.add(new Paragraph("Printed By: "+authCredential.getUserName()));
            document.add(new Paragraph("Date Printed: "+printingDate));
            document.add(new Paragraph("Time Printed: "+printedTime));
            document.add(new Paragraph("Start Date: "+sDateStr));
            document.add(new Paragraph("End Date: "+eDateStr));

            document.add(Chunk.NEWLINE);
            PdfPTable table = new PdfPTable(4);
           // table.setWidthPercentage(50);
            //table.setHorizontalAlignment(Element.ALIGN_LEFT);
            //table.setWidths(new int[]{1, 1});
            //table.getDefaultCell().setBorder(Rectangle.NO_BORDER);

            table.addCell("Product" );
            table.addCell("Unit Price");
            table.addCell("Unit");
            table.addCell("Total");

            if(productSummaryReportViewList!=null && productSummaryReportViewList.size()>0){
                for (ProductSummaryReportView productSaleSummaryTgt:productSummaryReportViewList) {
                    table.addCell(productSaleSummaryTgt.getProductName());
                    table.addCell(Float.toString(productSaleSummaryTgt.getSalePrice()));
                    table.addCell(Integer.toString(productSaleSummaryTgt.getSaleUnit()));
                    table.addCell(productSaleSummaryTgt.getSaleValue());
                }
            }

            document.add(table);
//            document.add(new Paragraph("First Line"));
//            document.add(new Paragraph("Second Line"));
//            document.add(new Paragraph("Third Line"));
//            document.add(new Paragraph("Fourth Line"));
//            document.add( Chunk.NEWLINE );
//            document.add(new Paragraph("Third Line"));
//            document.add(Chunk.NEWLINE);
//            document.add(Chunk.NEWLINE);
        }catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        document.close();

    }



    @RequestMapping(value="/transaction-summary-audit/download")
    public void  transactionSummaryAuditPdf(Authentication authentication,
                                            HttpServletResponse response,
                                            @RequestParam(value = "startDate",required = false) String startDate,
                                            @RequestParam(value = "endDate",required = false) String endDate){
        List<Sells>  sells = new ArrayList<>();
        AuthCredential authCredential =  (AuthCredential)authentication.getPrincipal();

        String fileName = "transaction_summary_audit.pdf";
        Document document = new Document();

        Timestamp sDate = null;
        Timestamp eDate = null;

        String sDateStr="";
        String eDateStr="";

        if(startDate!=null && !startDate.trim().equals("")){
            try {
                sDate = DateHelper.getStringToTimeStamp(startDate + " 00:00:00", "yyyy-MM-dd H:m:s");
                System.out.println(sDate);
            } catch (ParseException e) {

            }
        }
        if(endDate!=null && !endDate.trim().equals("")){
            try {
                eDate = DateHelper.getStringToTimeStamp(endDate + " 23:59:59", "yyyy-MM-dd H:m:s");
                System.out.println(eDate);
            } catch (ParseException e) {

            }
        }

        if(sDate!=null && eDate!=null){
            sells = sellsDao.getByDateRange(sDate,eDate);
        }else if(sDate!=null) {
            try {
                Timestamp tmpSData = DateHelper.getStringToTimeStamp(startDate+ " 00:00:00", "yyyy-MM-dd H:m:s");
                Timestamp tmpEData = DateHelper.getStringToTimeStamp(startDate + " 23:59:59", "yyyy-MM-dd H:m:s");
                sells = sellsDao.getByDateRange(tmpSData, tmpEData);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }else{
            sells = sellsDao.getAll();
        }



        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm:ss");

        String printingDate = formatDate.format( new java.util.Date());
        String printedTime = formatTime.format( new java.util.Date());

        if(sDate!=null)
             sDateStr = formatDate.format(sDate);


        if(eDate!=null)
             eDateStr = formatDate.format(eDate);




        int counter = 1;
        try {
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

            Font regular = new Font(Font.FontFamily.HELVETICA, 12);
            Font bold = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
            Phrase p = new Phrase("Transaction Summary Audit", bold);


            document.add(p);

            System.out.println(authCredential.getUserName());

            document.add(new Paragraph("Printed By: "+authCredential.getUserName()));
            document.add(new Paragraph("Date Printed: "+printingDate));
            document.add(new Paragraph("Time Printed: "+printedTime));
            document.add(new Paragraph("Start Date: "+sDateStr));
            document.add(new Paragraph("End Date: "+eDateStr));

            document.add(Chunk.NEWLINE);
            PdfPTable table = new PdfPTable(7);

            table.addCell("Trans code" );
            table.addCell("Time");
            table.addCell("Operator");
            table.addCell("Terminal");
            table.addCell("Product");
            table.addCell("Qty");
            table.addCell("Total");

            System.out.println(sells);



            if(sells!=null && sells.size()>0){
                for (Sells sellsTgt:sells) {
                    String products="";
                    table.addCell(Integer.toString(sellsTgt.getId()));
                    table.addCell(sellsTgt.getCreatedAt().toString());
                    table.addCell(sellsTgt.getAuthCredential().getUserName());
                    table.addCell(sellsTgt.getTerminal().getName());
                    for (SellsDetails sellsDetailsTgt:sellsTgt.getSellDetails()) {
                        if(sellsDetailsTgt.getConcessionProduct()!=null)
                            products+= sellsDetailsTgt.getConcessionProduct().getName()+"- Q X "+sellsDetailsTgt.getProductQuantity();
                    }
                    table.addCell(products);
                    table.addCell(Integer.toString(sellsTgt.getQuantity()));
                    table.addCell(Double.toString(sellsTgt.getSellingAmount()));
                }
            }

            document.add(table);

        }catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        document.close();


    }
}
