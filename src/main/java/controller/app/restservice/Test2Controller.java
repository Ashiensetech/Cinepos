package controller.app.restservice;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import dao.CategoryDao;

import dao.viewDao.BoxOfficeSchedulingViewDao;
import entity.entityView.BoxOfficeSchedulingView;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by mi on 12/21/16.
 */
@RestController
@RequestMapping("/api/test/")
public class Test2Controller {
    @Autowired
    CategoryDao categoryDao;

    @Autowired
    BoxOfficeSchedulingViewDao boxOfficeSchedulingViewDao;

    @RequestMapping(value="/pdf/download")
    public void generateSamplePdf(HttpServletResponse response){
        String fileName = "report.pdf";
        Document document = new Document();
        // response.setContentType("application/force-download");
        // response.setHeader("Content-Transfer-Encoding", "binary");
        // response.setHeader("Content-Disposition","attachment; filename=\"" + fileName+"\" ");




        //     response.setContentLength(10);
        //response.setContentLength(-1);

        int counter = 1;
        try {
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

            Font regular = new Font(Font.FontFamily.HELVETICA, 12);
            Font bold = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
            Phrase p = new Phrase("REPORT TITLE ", bold);


            document.add(p);

            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(50);
            table.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.setWidths(new int[]{1, 1});
            table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            table.addCell("Name: " );
            table.addCell("Jhone  ");
            table.addCell("Surname: ");
            table.addCell("Doe");
            document.add(table);
            document.add(new Paragraph("First Line"));
            document.add(new Paragraph("Second Line"));
            document.add( Chunk.NEWLINE );
            document.add(new Paragraph("Third Line"));
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
        }catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        document.close();
    }

    @RequestMapping(value="/schedule_view")
    public ResponseEntity<?> scheduleView(){
        return ResponseEntity.status(HttpStatus.OK).body(boxOfficeSchedulingViewDao.getByScheduleId(18 ));
    }


}
