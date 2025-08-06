package com.starprotect.vehicleinsurance.controller;

import com.starprotect.vehicleinsurance.bean.Vehicle;
import com.starprotect.vehicleinsurance.service.VehicleService;
import com.starprotect.vehicleinsurance.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/vehicle")
@CrossOrigin(origins = "http://localhost:4200")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createVehicleInsurance(@RequestBody Vehicle vehicle) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            // Validate vehicle number
            if (!ValidationUtil.isValidVehicleNo(vehicle.getVehicleNo())) {
                response.put("success", false);
                response.put("message", "Vehicle number must be alphanumeric and max 10 characters");
                return ResponseEntity.badRequest().body(response);
            }
            
            // Validate customer name
            if (!ValidationUtil.isValidCustomerName(vehicle.getCustomerName())) {
                response.put("success", false);
                response.put("message", "Customer name must be between 1-50 characters");
                return ResponseEntity.badRequest().body(response);
            }
            
            // Validate phone number
            if (!ValidationUtil.isValidPhoneNumber(String.valueOf(vehicle.getPhoneNo()))) {
                response.put("success", false);
                response.put("message", "Phone number must be 10 digits");
                return ResponseEntity.badRequest().body(response);
            }
            
            int policyId = vehicleService.createVehicleInsurance(vehicle);
            if (policyId > 0) {
                response.put("success", true);
                response.put("message", "Vehicle insurance created successfully");
                response.put("policyId", policyId);
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "Failed to create vehicle insurance");
                return ResponseEntity.badRequest().body(response);
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error occurred: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/policy/{policyId}")
    public ResponseEntity<Map<String, Object>> getVehicleByPolicyId(@PathVariable int policyId) {
        Map<String, Object> response = new HashMap<>();
        
        Vehicle vehicle = vehicleService.getVehicleByPolicyId(policyId);
        if (vehicle != null) {
            response.put("success", true);
            response.put("vehicle", vehicle);
            return ResponseEntity.ok(response);
        } else {
            response.put("success", false);
            response.put("message", "No such policy exists, please try again");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/vehicleNo/{vehicleNo}")
    public ResponseEntity<Map<String, Object>> getVehicleByVehicleNo(@PathVariable String vehicleNo) {
        Map<String, Object> response = new HashMap<>();
        
        Vehicle vehicle = vehicleService.getVehicleByVehicleNo(vehicleNo);
        if (vehicle != null) {
            response.put("success", true);
            response.put("vehicle", vehicle);
            return ResponseEntity.ok(response);
        } else {
            response.put("success", false);
            response.put("message", "No such policy exists, please try again");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/update-insurance-type")
    public ResponseEntity<Map<String, Object>> updateInsuranceType(@RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            int policyId = (Integer) request.get("policyId");
            String newInsuranceType = (String) request.get("insuranceType");
            
            // Check if policy exists
            Vehicle vehicle = vehicleService.getVehicleByPolicyId(policyId);
            if (vehicle == null) {
                response.put("success", false);
                response.put("message", "No such policy exists, please try again");
                return ResponseEntity.badRequest().body(response);
            }
            
            // Check if current policy is Third Party
            if ("Third Party".equals(vehicle.getInsuranceType())) {
                response.put("success", false);
                response.put("message", "There's no provision to update the policy type from Third party to full Insurance");
                return ResponseEntity.badRequest().body(response);
            }
            
            // Update insurance type
            boolean updated = vehicleService.updateVehicleInsuranceType(policyId, newInsuranceType);
            if (updated) {
                response.put("success", true);
                response.put("message", "Policy Updated Successfully");
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "Invalid choice");
                return ResponseEntity.badRequest().body(response);
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error occurred: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/renew")
    public ResponseEntity<Map<String, Object>> renewPolicy(@RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            int policyId = (Integer) request.get("policyId");
            String claimStatus = (String) request.get("claimStatus");
            
            // Check if policy exists
            Vehicle existingVehicle = vehicleService.getVehicleByPolicyId(policyId);
            if (existingVehicle == null) {
                response.put("success", false);
                response.put("message", "No such policy exists, please try again");
                return ResponseEntity.badRequest().body(response);
            }
            
            // Calculate premium for renewal
            BigDecimal renewalPremium = vehicleService.calculatePremium(
                existingVehicle.getVehicleType(), 
                existingVehicle.getVehicleValue(), 
                false // false for renewal (old vehicle)
            );
            
            response.put("success", true);
            response.put("message", "Premium calculated for renewal");
            response.put("existingVehicle", existingVehicle);
            response.put("renewalPremium", renewalPremium);
            response.put("claimStatus", claimStatus);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error occurred: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/confirm-renewal")
    public ResponseEntity<Map<String, Object>> confirmRenewal(@RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            int policyId = (Integer) request.get("policyId");
            String claimStatus = (String) request.get("claimStatus");
            
            int newPolicyId = vehicleService.renewVehiclePolicy(policyId, claimStatus);
            if (newPolicyId > 0) {
                response.put("success", true);
                response.put("message", "Policy Renewal is successful");
                response.put("newPolicyId", newPolicyId);
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "Failed to renew policy");
                return ResponseEntity.badRequest().body(response);
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error occurred: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/calculate-premium")
    public ResponseEntity<Map<String, Object>> calculatePremium(@RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            String vehicleType = (String) request.get("vehicleType");
            BigDecimal vehicleValue = new BigDecimal(request.get("vehicleValue").toString());
            
            BigDecimal premium = vehicleService.calculatePremium(vehicleType, vehicleValue, true);
            
            response.put("success", true);
            response.put("premiumAmount", premium);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error calculating premium: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}