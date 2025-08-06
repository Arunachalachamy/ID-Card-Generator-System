package com.starprotect.vehicleinsurance.service;

import com.starprotect.vehicleinsurance.bean.Vehicle;
import com.starprotect.vehicleinsurance.dao.VehicleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleDao vehicleDao;

    @Override
    public int createVehicleInsurance(Vehicle vehicle) {
        // Set auto dates
        vehicle.setFromDate(LocalDate.now());
        vehicle.setToDate(LocalDate.now().plusYears(1));
        
        // Calculate premium
        BigDecimal premium = calculatePremium(vehicle.getVehicleType(), vehicle.getVehicleValue(), true);
        vehicle.setPremiumAmount(premium);
        
        // Set default claim status
        if (vehicle.getClaimStatus() == null) {
            vehicle.setClaimStatus("No");
        }
        
        return vehicleDao.createVehicle(vehicle);
    }

    @Override
    public Vehicle getVehicleByPolicyId(int policyId) {
        return vehicleDao.findByPolicyId(policyId);
    }

    @Override
    public Vehicle getVehicleByVehicleNo(String vehicleNo) {
        return vehicleDao.findByVehicleNo(vehicleNo);
    }

    @Override
    public List<Vehicle> getVehiclesByUnderwriterId(int underwriterId) {
        return vehicleDao.findByUnderwriterId(underwriterId);
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        return vehicleDao.findAll();
    }

    @Override
    public boolean updateVehicleInsuranceType(int policyId, String insuranceType) {
        return vehicleDao.updateInsuranceType(policyId, insuranceType);
    }

    @Override
    public int renewVehiclePolicy(int policyId, String claimStatus) {
        Vehicle existingVehicle = vehicleDao.findByPolicyId(policyId);
        if (existingVehicle == null) {
            return -1; // Vehicle not found
        }
        return vehicleDao.renewPolicy(existingVehicle, claimStatus);
    }

    @Override
    public BigDecimal calculatePremium(String vehicleType, BigDecimal vehicleValue, boolean isNewVehicle) {
        BigDecimal premiumRate;
        
        if (isNewVehicle) {
            // For new vehicles: 3.5% of vehicle value
            premiumRate = new BigDecimal("0.035");
            return vehicleValue.multiply(premiumRate);
        } else {
            // For old vehicles: 5% of vehicle value minus 5% discount
            premiumRate = new BigDecimal("0.05");
            BigDecimal discount = new BigDecimal("0.05");
            
            BigDecimal premium = vehicleValue.multiply(premiumRate);
            BigDecimal discountAmount = premium.multiply(discount);
            return premium.subtract(discountAmount);
        }
    }
}