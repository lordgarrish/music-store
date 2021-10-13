package lordgarrish.data;

import lordgarrish.business.*;

import java.util.*;
import java.sql.*;

//Data access class for getting albums objects from database
public class AlbumDB {

    //Get album by its code
    public static MusicAlbum selectAlbum(String productCode) {
        ConnectionPool pool = ConnectionPool.getInstance();

        String query = "SELECT code, title, artist, year, description, genre, price " +
                        "FROM albums " +
                        "JOIN artists USING(artist_id) " +
                        "JOIN genres USING(genre_id) " +
                        "WHERE code = ? ";

        try(Connection connection = pool.getConnection()) {
            PreparedStatement stat = connection.prepareStatement(query);
            stat.setString(1, productCode);
            ResultSet resultSet = stat.executeQuery();
            MusicAlbum album = new MusicAlbum();
            if(resultSet.next()) {
                album.setCode(resultSet.getString("code"));
                album.setTitle(resultSet.getString("title"));
                album.setArtist(resultSet.getString("artist"));
                album.setYear(resultSet.getInt("year"));
                album.setDescription(resultSet.getString("description"));
                album.setGenre(resultSet.getString("genre"));
                album.setPrice(resultSet.getDouble("price"));
            } else {
                return null;
            }
            return album;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    //Get all albums
    public static List<MusicAlbum> selectAlbums() {
        ConnectionPool pool = ConnectionPool.getInstance();

        String query = "SELECT code, title, artist, year, description, genre, price " +
                        "FROM albums " +
                        "JOIN artists USING(artist_id) " +
                        "JOIN genres USING(genre_id)";

        try(Connection connection = pool.getConnection()) {
            PreparedStatement stat = connection.prepareStatement(query);
            ResultSet resultSet = stat.executeQuery();
            List<MusicAlbum> musicAlbums = new ArrayList<>();
            while(resultSet.next()) {
                MusicAlbum album = new MusicAlbum();
                album.setCode(resultSet.getString("code"));
                album.setTitle(resultSet.getString("title"));
                album.setArtist(resultSet.getString("artist"));
                album.setYear(resultSet.getInt("year"));
                album.setDescription(resultSet.getString("description"));
                album.setGenre(resultSet.getString("genre"));
                album.setPrice(resultSet.getDouble("price"));
                musicAlbums.add(album);
            }
            return musicAlbums;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }
}
