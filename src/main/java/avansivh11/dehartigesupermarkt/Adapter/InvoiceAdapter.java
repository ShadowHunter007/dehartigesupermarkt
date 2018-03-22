package avansivh11.dehartigesupermarkt.Adapter;

import avansivh11.dehartigesupermarkt.model.pdf.InvoicePdf;
import com.itextpdf.text.DocumentException;

public class InvoiceAdapter implements BaseAdapter {
    private InvoicePdf Adaptee;

    public InvoiceAdapter(InvoicePdf adaptee) {
        this.Adaptee = adaptee;
    }

    @Override
    public void addHeader() throws DocumentException {
        Adaptee.generateHead();
    }

    @Override
    public void addMiddle() throws DocumentException {
        Adaptee.generateMiddle();
    }

    @Override
    public void addFoot() throws DocumentException {
        Adaptee.generateFoot();
    }
}
