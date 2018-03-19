package avansivh11.dehartigesupermarkt.Adapter;

import com.itextpdf.text.DocumentException;

public interface BaseAdapter {
    void addHeader() throws DocumentException;//maybe add parameters???
    void addMiddle() throws DocumentException;//maybe add parameters???
    void addFoot() throws DocumentException;//maybe add parameters???
}
