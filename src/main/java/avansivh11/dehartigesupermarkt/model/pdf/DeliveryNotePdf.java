package avansivh11.dehartigesupermarkt.model.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DeliveryNotePdf extends BasePdf {
    //Document document = new Document();

    @Override
    public void generateHead() throws DocumentException {
        /* -----------------text - test--------------------- */
        Font font = FontFactory.getFont(FontFactory.ZAPFDINGBATS, 16, BaseColor.CYAN);
        Chunk chunk = new Chunk("This is the Header", font);

        document.add(chunk);
        /* -----------------text - test--------------------- */

    }

    @Override
    public void generateMiddle() throws DocumentException {
        /* -----------------text - test--------------------- */
        Font font = FontFactory.getFont(FontFactory.ZAPFDINGBATS, 16, BaseColor.CYAN);
        Chunk chunk = new Chunk("This is the middle!", font);

        document.add(chunk);
        /* -----------------text - test--------------------- */

    }

    @Override
    public void generateFoot() throws DocumentException {
        /* -----------------text - test--------------------- */
        Font font = FontFactory.getFont(FontFactory.ZAPFDINGBATS, 16, BaseColor.CYAN);
        Chunk chunk = new Chunk("This is the footer!", font);

        document.add(chunk);
        /* -----------------text - test--------------------- */

    }

    void generatePDF() throws IOException, DocumentException {
        String HTML = "src/main/resources/templates/views/pdf/DeliveryNote.html";
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("src/output/deliverynote"+ timeStamp +".pdf"));
        //document.open();
        /* -----------------text - test---------------------
        Font font = FontFactory.getFont(FontFactory.ZAPFDINGBATS, 16, BaseColor.CYAN);
        Chunk chunk = new Chunk("Hello World!", font);
        document.add(chunk);
         -----------------text - test--------------------- */

        XMLWorkerHelper.getInstance().parseXHtml(writer, document, new FileInputStream(HTML));
        document.close();
    }
}
