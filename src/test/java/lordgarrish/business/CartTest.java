package lordgarrish.business;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {

    MusicAlbum album1 = new MusicAlbum("001", "Abbey Road", "The Beatles", "Rock", 1969, "Test", 45.0);
    MusicAlbum album2 = new MusicAlbum("002", "Strange Days", "The Doors", "Rock", 1967, "Test", 39.0);
    MusicAlbum album3 = new MusicAlbum("003", "Paranoid", "Black Sabbath", "Metal", 1970, "Test", 66.6);

    LineItem item1 = new LineItem(album1, 1);
    LineItem item2 = new LineItem(album2, 1);
    LineItem item3 = new LineItem(album3, 1);

    Cart cart = new Cart();

    @Test
    @DisplayName("Should add items in cart")
    void addItemTest() {
        cart.addItem(item1);
        cart.addItem(item1);
        cart.addItem(item2);
        cart.addItem(item3);
        assertEquals(3, cart.getCount());
    }

    @Test
    @DisplayName("Should delete items from cart")
    void removeItemTest() {
        cart.addItem(item2);
        cart.addItem(item3);
        cart.removeItem(item3);
        cart.removeItem(item2);
        assertEquals(0, cart.getCount());
    }

    @Test
    @DisplayName("Should display correct total price")
    void getTotalPriceTest() {
        cart.addItem(item1);
        cart.addItem(item2);
        cart.addItem(item3);
        assertEquals("150,6", cart.getTotalPrice());
    }

    @Test
    @DisplayName("Should remove all items from cart")
    void isEmptyCartTest() {
        cart.addItem(item1);
        cart.addItem(item1);
        cart.addItem(item2);
        cart.addItem(item3);
        cart.removeAllItemsFromCart();
        assertEquals(0, cart.getCount());
    }
}