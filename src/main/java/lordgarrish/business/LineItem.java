package lordgarrish.business;

import java.io.Serializable;

//Java Bean class representing item in cart
public class LineItem implements Serializable {

    private MusicAlbum album;
    private int quantity; //total quantity

    public LineItem() {}

    public LineItem(MusicAlbum album, int quantity) {
        this.album = album;
        this.quantity = quantity;
    }

    public MusicAlbum getAlbum() {
        return album;
    }

    public void setAlbum(MusicAlbum album) {
        this.album = album;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotal() {
        return album.getPrice() * quantity;
    }
}
