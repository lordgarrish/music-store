package lordgarrish.data;

import lordgarrish.business.MusicAlbum;

import java.sql.*;
import java.util.*;

public class AlbumDao implements AbstractDao<MusicAlbum, String> {
    private static AbstractDao<MusicAlbum, String> albumDao;

    private AlbumDao() {}

    public static synchronized AbstractDao<MusicAlbum, String> getInstance() {
        if(albumDao == null) {
            albumDao = new AlbumDao();
        }
        return albumDao;
    }

    @Override
    public boolean save(MusicAlbum album) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public MusicAlbum update(MusicAlbum album) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public boolean delete(String id) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public List<MusicAlbum> getAll() throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();

        String query = "SELECT code, title, artist, year, description, genre, price " +
                "FROM albums " +
                "JOIN artists USING(artist_id) " +
                "JOIN genres USING(genre_id)";

        try(Connection connection = pool.getConnection()) {
            PreparedStatement stat = connection.prepareStatement(query);
            ResultSet resultSet = stat.executeQuery();
            List<MusicAlbum> musicAlbums = new ArrayList<>();
            while (resultSet.next()) {
                MusicAlbum album = createAlbum(resultSet);
                musicAlbums.add(album);
            }
            return musicAlbums;
        }
    }

    @Override
    public MusicAlbum getById(String id) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();

        String query = "SELECT code, title, artist, year, description, genre, price " +
                "FROM albums " +
                "JOIN artists USING(artist_id) " +
                "JOIN genres USING(genre_id) " +
                "WHERE code = ? ";

        try(Connection connection = pool.getConnection()) {
            PreparedStatement stat = connection.prepareStatement(query);
            stat.setString(1, id);
            ResultSet resultSet = stat.executeQuery();
            return resultSet.next() ? createAlbum(resultSet) : null;
        }
    }

    private MusicAlbum createAlbum(ResultSet resultSet) throws SQLException {
        MusicAlbum album = new MusicAlbum();
        album.setCode(resultSet.getString("code"));
        album.setTitle(resultSet.getString("title"));
        album.setArtist(resultSet.getString("artist"));
        album.setYear(resultSet.getInt("year"));
        album.setDescription(resultSet.getString("description"));
        album.setGenre(resultSet.getString("genre"));
        album.setPrice(resultSet.getDouble("price"));
        return album;
    }
}
