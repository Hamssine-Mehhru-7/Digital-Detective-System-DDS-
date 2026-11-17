
package com.digitaldetective.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;

public class CaseDAO {

    // Create and save a new investigation case
    public static boolean createCase(
            int userId, String title, String description)
            throws SQLException {

        String sql = "INSERT INTO cases "
                + "(user_id, case_title, description) "
                + "VALUES (?, ?, ?)";

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, userId);
            statement.setString(2, title);
            statement.setString(3, description);

            int rowsInserted = statement.executeUpdate();

            return rowsInserted > 0;
        }
    }
    public static List<String> getCasesByUserId(int userId)
        throws SQLException {

    List<String> cases = new ArrayList<>();

    String sql = "SELECT id, case_title, description, status "
            + "FROM cases WHERE user_id = ?";

    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql)) {

        statement.setInt(1, userId);

        try (ResultSet result = statement.executeQuery()) {

            while (result.next()) {

                String caseDetails =
                        "Case ID: " + result.getInt("id")
                        + "\nTitle: " + result.getString("case_title")
                        + "\nDescription: " + result.getString("description")
                        + "\nStatus: " + ("Analysed".equalsIgnoreCase(result.getString("status"))
                                ? "Analysed" : "Pending");

                cases.add(caseDetails);
            }
        }
    }

    return cases;
    }
    public static Map<Integer, String> getCaseTitlesByUserId(int userId)
        throws SQLException {

    Map<Integer, String> cases = new LinkedHashMap<>();

    String sql = "SELECT id, case_title FROM cases WHERE user_id = ?";

    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql)) {

        statement.setInt(1, userId);

        try (ResultSet result = statement.executeQuery()) {

            while (result.next()) {
                cases.put(
                        result.getInt("id"),
                        result.getString("case_title")
                );
            }
        }
    }

    return cases;
}
public static void checkCases(int userId) throws SQLException {

    String sql = "SELECT id, user_id, case_title FROM cases";

    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql);
         ResultSet result = statement.executeQuery()) {

        System.out.println("\n--- ALL SAVED CASES ---");

        while (result.next()) {
            System.out.println(
                "ID: " + result.getInt("id") +
                " | User ID: " + result.getInt("user_id") +
                " | Title: " + result.getString("case_title")
            );
        }
    }
}

public static int createCaseAndGetId(
        int userId, String title, String description)
        throws SQLException {

    String sql = "INSERT INTO cases (user_id, case_title, description) "
            + "VALUES (?, ?, ?)";

    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement statement = connection.prepareStatement(
                 sql, java.sql.Statement.RETURN_GENERATED_KEYS)) {

        statement.setInt(1, userId);
        statement.setString(2, title);
        statement.setString(3, description);

        if (statement.executeUpdate() == 0) {
            throw new SQLException("Case creation failed.");
        }

        try (ResultSet keys = statement.getGeneratedKeys()) {
            if (keys.next()) {
                return keys.getInt(1);
            }
        }
    }

    throw new SQLException("Could not retrieve the new Case ID.");
}


public static String getCaseTitle(int caseId, int userId) throws SQLException {
    String sql = "SELECT case_title FROM cases WHERE id = ? AND user_id = ?";
    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setInt(1, caseId);
        statement.setInt(2, userId);
        try (ResultSet rs = statement.executeQuery()) {
            return rs.next() ? rs.getString("case_title") : null;
        }
    }
}

public static String getCaseStatus(int caseId, int userId) throws SQLException {
    String sql = "SELECT status FROM cases WHERE id = ? AND user_id = ?";
    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setInt(1, caseId);
        statement.setInt(2, userId);
        try (ResultSet rs = statement.executeQuery()) {
            if (!rs.next()) return null;
            return "Analysed".equalsIgnoreCase(rs.getString("status")) ? "Analysed" : "Pending";
        }
    }
}

public static void markCaseAnalysed(int caseId, int userId) throws SQLException {
    String sql = "UPDATE cases SET status = 'Analysed' WHERE id = ? AND user_id = ?";
    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setInt(1, caseId);
        statement.setInt(2, userId);
        if (statement.executeUpdate() == 0) {
            throw new SQLException("The selected case could not be updated.");
        }
    }
}

public static boolean userOwnsCase(int caseId, int userId)
        throws SQLException {

    String sql = "SELECT id FROM cases WHERE id = ? AND user_id = ?";

    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql)) {

        statement.setInt(1, caseId);
        statement.setInt(2, userId);

        try (ResultSet rs = statement.executeQuery()) {
            return rs.next();
        }
    }
}

}

