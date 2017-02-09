package controller.web.admin.page;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import controller.web.admin.AdminUriPreFix;
import dao.reportDao.ProductSummaryReportViewDao;
import entity.AuthCredential;
import entity.entityView.report.ProductSummaryReportView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
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
                                      HttpServletResponse response){

        AuthCredential authCredential =  (AuthCredential)authentication.getPrincipal();
        String fileName = "product_sales_summary.pdf";
        Document document = new Document();

        List<ProductSummaryReportView> productSummaryReportViewList=productSummaryReportViewDao.getAll();

        System.out.println(productSummaryReportViewList);

        int counter = 1;
        try {
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

            Font regular = new Font(Font.FontFamily.HELVETICA, 12);
            Font bold = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
            Phrase p = new Phrase("Products Sales Summary", bold);


            document.add(p);

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
