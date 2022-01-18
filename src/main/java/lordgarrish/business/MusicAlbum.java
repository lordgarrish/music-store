package lordgarrish.business;

import java.io.Serializable;

//Java Bean class representing single album
public class MusicAlbum implements Serializable {

    private String code;
    private String title;
    private String artist;
    private String genre;
    private int year;
    private String description;
    private double price;

    public MusicAlbum() {}

    public MusicAlbum(String code, String title, String artist, String genre, int year, String description, double price) {
        this.code = code;
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.year = year;
        this.description = description;
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImageURL() {
        return "/images/" + code + "_cover.jpg";
    }
}
