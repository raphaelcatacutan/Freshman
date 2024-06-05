package com.plm.studentdb.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBRemove {
    public static void removeStudentRecord(int id) {
        String deleteQuery = "DELETE FROM studentdb.student WHERE id=?";
        try (PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(deleteQuery)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}