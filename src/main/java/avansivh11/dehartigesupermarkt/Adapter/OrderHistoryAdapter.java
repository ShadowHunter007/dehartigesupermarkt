package avansivh11.dehartigesupermarkt.Adapter;

import avansivh11.dehartigesupermarkt.model.pdf.OrderHistoryPdf;
import com.itextpdf.text.DocumentException;

public class OrderHistoryAdapter implements BaseAdapter {
    private OrderHistoryPdf Adaptee;

    public OrderHistoryAdapter(OrderHistoryPdf adaptee) {
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
