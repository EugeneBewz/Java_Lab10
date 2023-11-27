package ua.edu.ucu.apps.task2;

import java.sql.*;
import java.util.Optional;

public class SQLiteLocalCache implements LocalCache {
    private static final String DATABASE_URL = "jdbc:sqlite:cache.db";
    private static final String CREATE_TABLE_QUERY = "CREATE DATABASE IF NOT EXISTS cache (id TEXT PRIMARY KEY, result TEXT)";

    public SQLiteLocalCache() {
        initializeData();
    }

    void initializeData() {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(CREATE_TABLE_QUERY);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<String> retrieveFromCache(Document doc) {
        String docId = getDocumentId(doc);

        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT result FROM cache WHERE id = ?")) {
            preparedStatement.setString(1, docId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return Optional.of(resultSet.getString("result"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void saveToCache(Document doc, String result) {
        String docId = getDocumentId(doc);

        try (
                Connection connection = DriverManager.getConnection(DATABASE_URL);
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT OR REPLACE INTO cache (id, result) VALUES (?, ?)")
                ) {
            preparedStatement.setString(1, docId);
            preparedStatement.setString(2, result);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String getDocumentId(Document document) {
        return Integer.toHexString(document.hashCode());
    }
}
