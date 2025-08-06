package com.starprotect.vehicleinsurance.dao;

import com.starprotect.vehicleinsurance.bean.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

@Repository
public class VehicleDaoImpl implements VehicleDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Vehicle> vehicleRowMapper = new RowMapper<Vehicle>() {
        @Override
        public Vehicle mapRow(ResultSet rs, int rowNum) throws SQLException {
            Vehicle vehicle = new Vehicle();
            vehicle.setPolicyId(rs.getInt("policyId"));
            vehicle.setVehicleNo(rs.getString("vehicleNo"));
            vehicle.setVehicleType(rs.getString("vehicleType"));
            vehicle.setCustomerName(rs.getString("customerName"));
            vehicle.setEngineNo(rs.getInt("engineNo"));
            vehicle.setChasisNo(rs.getInt("chasisNo"));
            vehicle.setPhoneNo(rs.getLong("phoneNo"));
            vehicle.setInsuranceType(rs.getString("insuranceType"));
            vehicle.setFromDate(rs.getDate("fromDate").toLocalDate());
            vehicle.setToDate(rs.getDate("toDate").toLocalDate());
            vehicle.setPremiumAmount(rs.getBigDecimal("premiumAmount"));
            vehicle.setUnderwriterId(rs.getInt("underwriterId"));
            vehicle.setVehicleValue(rs.getBigDecimal("vehicleValue"));
            vehicle.setClaimStatus(rs.getString("claimStatus"));
            return vehicle;
        }
    };

    @Override
    public int createVehicle(Vehicle vehicle) {
        String sql = "INSERT INTO Vehicle (vehicleNo, vehicleType, customerName, engineNo, chasisNo, phoneNo, insuranceType, fromDate, toDate, premiumAmount, underwriterId, vehicleValue, claimStatus) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        KeyHolder keyHolder = new GeneratedKeyHolder();
        
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, vehicle.getVehicleNo());
            ps.setString(2, vehicle.getVehicleType());
            ps.setString(3, vehicle.getCustomerName());
            ps.setInt(4, vehicle.getEngineNo());
            ps.setInt(5, vehicle.getChasisNo());
            ps.setLong(6, vehicle.getPhoneNo());
            ps.setString(7, vehicle.getInsuranceType());
            ps.setDate(8, java.sql.Date.valueOf(vehicle.getFromDate()));
            ps.setDate(9, java.sql.Date.valueOf(vehicle.getToDate()));
            ps.setBigDecimal(10, vehicle.getPremiumAmount());
            ps.setInt(11, vehicle.getUnderwriterId());
            ps.setBigDecimal(12, vehicle.getVehicleValue());
            ps.setString(13, vehicle.getClaimStatus());
            return ps;
        }, keyHolder);
        
        return keyHolder.getKey().intValue();
    }

    @Override
    public Vehicle findByPolicyId(int policyId) {
        String sql = "SELECT * FROM Vehicle WHERE policyId = ?";
        try {
            return jdbcTemplate.queryForObject(sql, vehicleRowMapper, policyId);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Vehicle findByVehicleNo(String vehicleNo) {
        String sql = "SELECT * FROM Vehicle WHERE vehicleNo = ?";
        try {
            return jdbcTemplate.queryForObject(sql, vehicleRowMapper, vehicleNo);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Vehicle> findByUnderwriterId(int underwriterId) {
        String sql = "SELECT * FROM Vehicle WHERE underwriterId = ? ORDER BY policyId";
        return jdbcTemplate.query(sql, vehicleRowMapper, underwriterId);
    }

    @Override
    public List<Vehicle> findAll() {
        String sql = "SELECT * FROM Vehicle ORDER BY policyId";
        return jdbcTemplate.query(sql, vehicleRowMapper);
    }

    @Override
    public boolean updateInsuranceType(int policyId, String insuranceType) {
        String sql = "UPDATE Vehicle SET insuranceType = ? WHERE policyId = ?";
        int rowsAffected = jdbcTemplate.update(sql, insuranceType, policyId);
        return rowsAffected > 0;
    }

    @Override
    public boolean deleteByPolicyId(int policyId) {
        String sql = "DELETE FROM Vehicle WHERE policyId = ?";
        int rowsAffected = jdbcTemplate.update(sql, policyId);
        return rowsAffected > 0;
    }

    @Override
    public int renewPolicy(Vehicle existingVehicle, String claimStatus) {
        LocalDate currentDate = LocalDate.now();
        LocalDate fromDate, toDate;
        
        if (currentDate.isAfter(existingVehicle.getToDate())) {
            // Policy expired
            fromDate = currentDate;
            toDate = fromDate.plusYears(1);
        } else {
            // Policy not expired
            fromDate = existingVehicle.getToDate().plusDays(1);
            toDate = fromDate.plusYears(1);
        }
        
        Vehicle renewedVehicle = new Vehicle();
        renewedVehicle.setVehicleNo(existingVehicle.getVehicleNo());
        renewedVehicle.setVehicleType(existingVehicle.getVehicleType());
        renewedVehicle.setCustomerName(existingVehicle.getCustomerName());
        renewedVehicle.setEngineNo(existingVehicle.getEngineNo());
        renewedVehicle.setChasisNo(existingVehicle.getChasisNo());
        renewedVehicle.setPhoneNo(existingVehicle.getPhoneNo());
        renewedVehicle.setInsuranceType(existingVehicle.getInsuranceType());
        renewedVehicle.setFromDate(fromDate);
        renewedVehicle.setToDate(toDate);
        renewedVehicle.setUnderwriterId(existingVehicle.getUnderwriterId());
        renewedVehicle.setVehicleValue(existingVehicle.getVehicleValue());
        renewedVehicle.setClaimStatus(claimStatus);
        
        // Calculate premium for renewal (old vehicle rates)
        BigDecimal vehicleValue = existingVehicle.getVehicleValue();
        BigDecimal premiumRate = new BigDecimal("0.05"); // 5% for old vehicles
        BigDecimal discount = new BigDecimal("0.05"); // 5% discount
        
        BigDecimal premium = vehicleValue.multiply(premiumRate);
        BigDecimal discountAmount = premium.multiply(discount);
        BigDecimal finalPremium = premium.subtract(discountAmount);
        
        renewedVehicle.setPremiumAmount(finalPremium);
        
        return createVehicle(renewedVehicle);
    }
}