package avansivh11.dehartigesupermarkt.model.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public abstract class BasePdf {
    Document document = new Document();

    //methods to be implemented by the concrete PFD classes
    abstract void generateHead() throws DocumentException;
    abstract void generateMiddle() throws DocumentException;
    abstract void generateFoot() throws DocumentException;

    abstract void generatePDF() throws ParserConfigurationException, IOException, DocumentException;


    //template method
    public final void buildPDF() throws DocumentException, IOException, ParserConfigurationException {
        generateHead();
        generateMiddle();
        generateFoot();
        generatePDF();

    }
}
