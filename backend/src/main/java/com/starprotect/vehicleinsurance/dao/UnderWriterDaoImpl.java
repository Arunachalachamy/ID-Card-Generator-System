package com.starprotect.vehicleinsurance.dao;

import com.starprotect.vehicleinsurance.bean.UnderWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Repository
public class UnderWriterDaoImpl implements UnderWriterDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<UnderWriter> underWriterRowMapper = new RowMapper<UnderWriter>() {
        @Override
        public UnderWriter mapRow(ResultSet rs, int rowNum) throws SQLException {
            UnderWriter underWriter = new UnderWriter();
            underWriter.setUnderwriterId(rs.getInt("underwriterId"));
            underWriter.setName(rs.getString("name"));
            underWriter.setDob(rs.getDate("dob").toLocalDate());
            underWriter.setJoiningDate(rs.getDate("joiningDate").toLocalDate());
            underWriter.setPassword(rs.getString("password"));
            return underWriter;
        }
    };

    @Override
    public int createUnderWriter(UnderWriter underWriter) {
        String sql = "INSERT INTO UnderWriter (underwriterId, name, dob, joiningDate, password) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, 
            underWriter.getUnderwriterId(),
            underWriter.getName(),
            underWriter.getDob(),
            underWriter.getJoiningDate(),
            underWriter.getPassword());
    }

    @Override
    public UnderWriter findById(int underwriterId) {
        String sql = "SELECT * FROM UnderWriter WHERE underwriterId = ?";
        try {
            return jdbcTemplate.queryForObject(sql, underWriterRowMapper, underwriterId);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<UnderWriter> findAll() {
        String sql = "SELECT * FROM UnderWriter ORDER BY underwriterId";
        return jdbcTemplate.query(sql, underWriterRowMapper);
    }

    @Override
    public boolean updatePassword(int underwriterId, String newPassword) {
        String sql = "UPDATE UnderWriter SET password = ? WHERE underwriterId = ?";
        int rowsAffected = jdbcTemplate.update(sql, newPassword, underwriterId);
        return rowsAffected > 0;
    }

    @Override
    public boolean deleteById(int underwriterId) {
        String sql = "DELETE FROM UnderWriter WHERE underwriterId = ?";
        int rowsAffected = jdbcTemplate.update(sql, underwriterId);
        return rowsAffected > 0;
    }

    @Override
    public UnderWriter authenticateUnderWriter(int underwriterId, String password) {
        String sql = "SELECT * FROM UnderWriter WHERE underwriterId = ? AND password = ?";
        try {
            return jdbcTemplate.queryForObject(sql, underWriterRowMapper, underwriterId, password);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public int generateUnderWriterId() {
        String sql = "VALUES NEXT VALUE FOR underwriter_seq";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
}