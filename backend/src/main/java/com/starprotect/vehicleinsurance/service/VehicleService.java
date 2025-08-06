package com.starprotect.vehicleinsurance.service;

import com.starprotect.vehicleinsurance.bean.Vehicle;
import java.math.BigDecimal;
import java.util.List;

public interface VehicleService {
    int createVehicleInsurance(Vehicle vehicle);
    Vehicle getVehicleByPolicyId(int policyId);
    Vehicle getVehicleByVehicleNo(String vehicleNo);
    List<Vehicle> getVehiclesByUnderwriterId(int underwriterId);
    List<Vehicle> getAllVehicles();
    boolean updateVehicleInsuranceType(int policyId, String insuranceType);
    int renewVehiclePolicy(int policyId, String claimStatus);
    BigDecimal calculatePremium(String vehicleType, BigDecimal vehicleValue, boolean isNewVehicle);
}