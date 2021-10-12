package lordgarrish.business;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

public class Cart implements Serializable {

    private final List<LineItem> items;

    public Cart() {
        items = new ArrayList<>();
    }

    public List<LineItem> getItems() {
        return items;
    }

    public int getCount() {
        return items.size();
    }

    public void addItem(LineItem item) {
        String code = item.getAlbum().getCode();
        int quantity = item.getQuantity();
        for (int i = 0; i < items.size(); i++) {
            LineItem lineItem = items.get(i);
            if (lineItem.getAlbum().getCode().equals(code)) {
                lineItem.setQuantity(quantity);
                return;
            }
        }
        items.add(item);
    }

    public void removeItem(LineItem item) {
        String code = item.getAlbum().getCode();
        for (int i = 0; i < items.size(); i++) {
            LineItem lineItem = items.get(i);
            if (lineItem.getAlbum().getCode().equals(code)) {
                items.remove(i);
                return;
            }
        }
    }

    public String getTotalPrice() {
        double totalPrice = 0.0;
        for(LineItem item : items) {
            totalPrice += item.getTotal();
        }
        DecimalFormat decFormat = new DecimalFormat();
        decFormat.setMaximumFractionDigits(2);
        return decFormat.format(totalPrice);
    }

    public boolean isEmptyCart() {
        return items.isEmpty();
    }
}
