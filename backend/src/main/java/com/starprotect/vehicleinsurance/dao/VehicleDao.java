package com.starprotect.vehicleinsurance.dao;

import com.starprotect.vehicleinsurance.bean.Vehicle;
import java.util.List;

public interface VehicleDao {
    int createVehicle(Vehicle vehicle);
    Vehicle findByPolicyId(int policyId);
    Vehicle findByVehicleNo(String vehicleNo);
    List<Vehicle> findByUnderwriterId(int underwriterId);
    List<Vehicle> findAll();
    boolean updateInsuranceType(int policyId, String insuranceType);
    boolean deleteByPolicyId(int policyId);
    int renewPolicy(Vehicle existingVehicle, String claimStatus);
}