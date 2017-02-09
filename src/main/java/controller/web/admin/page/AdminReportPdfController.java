package controller.web.admin.page;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import controller.web.admin.AdminUriPreFix;
import dao.reportDao.ProductSummaryReportViewDao;
import entity.AuthCredential;
import entity.entityView.report.ProductSummaryReportView;
import helper.DateHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by Sarwar on 2/9/2017.
 */
@Controller
@RequestMapping(AdminUriPreFix.pageUriPrefix+"/report-pdf")
public class AdminReportPdfController {
    @Autowired
    ProductSummaryReportViewDao productSummaryReportViewDao;



    @RequestMapping(value="/product-sale-summary/download")
    public void productSaleSummaryPdf(Authentication authentication,
                                      HttpServletResponse response,
                                      @RequestParam(value = "startDate",required = false) String startDate,
                                      @RequestParam(value = "endDate",required = false) String endDate){

        AuthCredential authCredential =  (AuthCredential)authentication.getPrincipal();
        String fileName = "product_sales_summary.pdf";
        Document document = new Document();


        Date sDate = null;
        Date eDate = null;
        Date fDate=null;
        if(startDate!=null && !startDate.trim().equals("")){
            try {
                sDate = DateHelper.getStringToDate(startDate+" 23:59:59", "yyyy-MM-dd H:m:s");
                System.out.print(sDate);
            } catch (ParseException e) {

            }
        }
        if(endDate!=null && !endDate.trim().equals("")){
            try {
                eDate = DateHelper.getStringToDate(endDate+" 23:59:59", "yyyy-MM-dd H:m:s");
            } catch (ParseException e) {

            }
        }


        List<ProductSummaryReportView> productSummaryReportViewList;

        System.out.println(sDate);

        if(sDate!=null && eDate!=null){
            productSummaryReportViewList = productSummaryReportViewDao.getByDateRange(sDate, eDate);
        }else if(sDate!=null) {
            //fDate=sDate;
            // sDate=null;
            productSummaryReportViewList = productSummaryReportViewDao.getByDateRange(sDate, sDate);
        }else{
            productSummaryReportViewList = productSummaryReportViewDao.getAll();
        }


        Calendar calendar = new GregorianCalendar();

        int year       = calendar.get(Calendar.YEAR);
        int month      = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        String printingDate=year+"-"+month+"-"+dayOfMonth;
        int printedTime  = calendar.get(Calendar.HOUR_OF_DAY);



        int counter = 1;
        try {
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

            Font regular = new Font(Font.FontFamily.HELVETICA, 12);
            Font bold = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
            Phrase p = new Phrase("Products Sales Summary", bold);


            document.add(p);

            document.add(new Paragraph("Printed By: "+authCredential.getUserName()));
            document.add(new Paragraph("Date Printed: "+printingDate));
            document.add(new Paragraph("Time Printed: "+printedTime));
            document.add(new Paragraph("Start Date: "+sDate));
            document.add(new Paragraph("End Date: "+eDate));

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


        System.out.println(productSummaryReportViewList.size());

        document.close();

    }
}
