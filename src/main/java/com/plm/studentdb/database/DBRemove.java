package com.plm.studentdb.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBRemove {
    public static void removeStudentRecord(int id) {
        String deleteQuery = "DELETE FROM studentdb.student WHERE student_id=?";
        try (PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(deleteQuery)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void removeAccountRecord(int id) {
        String deleteQuery = "DELETE FROM accounts WHERE id=?";
        try (PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(deleteQuery)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void removeCourseRecord(int id) {
        String deleteQuery = "DELETE FROM course WHERE id=?";
        try (PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(deleteQuery)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void removeEnrolledRecord(int id) {
        String deleteQuery = "DELETE FROM enrolled WHERE id=?";
        try (PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(deleteQuery)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void removeProgramRecord(int id) {
        String deleteQuery = "DELETE FROM college WHERE id=?";
        try (PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(deleteQuery)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}