package avansivh11.dehartigesupermarkt.Adapter;

import avansivh11.dehartigesupermarkt.model.pdf.DeliveryNotePdf;
import com.itextpdf.text.DocumentException;

public class DeliveryNoteAdapter implements BaseAdapter {
    private DeliveryNotePdf Adaptee;

    public DeliveryNoteAdapter(DeliveryNotePdf adaptee) {
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
