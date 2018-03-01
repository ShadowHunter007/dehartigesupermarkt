package avansivh11.dehartigesupermarkt.model.pdf;

import com.itextpdf.text.DocumentException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public abstract class BasePdf {

    abstract void generatePDF() throws ParserConfigurationException, IOException, DocumentException;
    //abstract void somethingElse();

    public final void buildPDF() throws DocumentException, IOException, ParserConfigurationException {
        //somethingElse();
        generatePDF();

    }
}
