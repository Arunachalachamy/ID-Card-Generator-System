package com.starprotect.vehicleinsurance.controller;

import com.starprotect.vehicleinsurance.bean.UnderWriter;
import com.starprotect.vehicleinsurance.service.UnderWriterService;
import com.starprotect.vehicleinsurance.service.VehicleService;
import com.starprotect.vehicleinsurance.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/underwriter")
@CrossOrigin(origins = "http://localhost:4200")
public class UnderWriterController {

    @Autowired
    private UnderWriterService underWriterService;
    
    @Autowired
    private VehicleService vehicleService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> registerUnderWriter(@RequestBody UnderWriter underWriter) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            // Validate name length
            if (!ValidationUtil.isValidName(underWriter.getName())) {
                response.put("success", false);
                response.put("message", "Name must be between 1-30 characters");
                return ResponseEntity.badRequest().body(response);
            }
            
            // Validate password
            if (!underWriterService.validatePassword(underWriter.getPassword())) {
                response.put("success", false);
                response.put("message", ValidationUtil.getPasswordValidationMessage());
                return ResponseEntity.badRequest().body(response);
            }
            
            int result = underWriterService.registerUnderWriter(underWriter);
            if (result > 0) {
                response.put("success", true);
                response.put("message", "UnderWriter registered successfully");
                response.put("underwriterId", underWriter.getUnderwriterId());
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "Failed to register UnderWriter");
                return ResponseEntity.badRequest().body(response);
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error occurred: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<Map<String, Object>> searchUnderWriter(@PathVariable int id) {
        Map<String, Object> response = new HashMap<>();
        
        UnderWriter underWriter = underWriterService.getUnderWriterById(id);
        if (underWriter != null) {
            response.put("success", true);
            response.put("underwriter", underWriter);
            return ResponseEntity.ok(response);
        } else {
            response.put("success", false);
            response.put("message", "No Such UnderWriter Exist with the Given Id.");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> getAllUnderWriters() {
        Map<String, Object> response = new HashMap<>();
        
        List<UnderWriter> underWriters = underWriterService.getAllUnderWriters();
        response.put("success", true);
        response.put("underwriters", underWriters);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update-password")
    public ResponseEntity<Map<String, Object>> updatePassword(@RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            int underwriterId = (Integer) request.get("underwriterId");
            String newPassword = (String) request.get("password");
            
            // Check if underwriter exists
            UnderWriter underWriter = underWriterService.getUnderWriterById(underwriterId);
            if (underWriter == null) {
                response.put("success", false);
                response.put("message", "No Such UnderWriter Exist with the Given Id");
                return ResponseEntity.badRequest().body(response);
            }
            
            // Validate password
            if (!underWriterService.validatePassword(newPassword)) {
                response.put("success", false);
                response.put("message", ValidationUtil.getPasswordValidationMessage());
                return ResponseEntity.badRequest().body(response);
            }
            
            boolean updated = underWriterService.updateUnderWriterPassword(underwriterId, newPassword);
            if (updated) {
                response.put("success", true);
                response.put("message", "Password updated for Id: " + underwriterId);
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "Failed to update password");
                return ResponseEntity.badRequest().body(response);
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error occurred: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteUnderWriter(@PathVariable int id) {
        Map<String, Object> response = new HashMap<>();
        
        // Check if underwriter exists
        UnderWriter underWriter = underWriterService.getUnderWriterById(id);
        if (underWriter == null) {
            response.put("success", false);
            response.put("message", "No Such UnderWriter Exist with the Given Id");
            return ResponseEntity.badRequest().body(response);
        }
        
        boolean deleted = underWriterService.deleteUnderWriter(id);
        if (deleted) {
            response.put("success", true);
            response.put("message", "UnderWriter with Id: " + id + " is deleted");
            return ResponseEntity.ok(response);
        } else {
            response.put("success", false);
            response.put("message", "Failed to delete UnderWriter");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> underWriterLogin(@RequestBody Map<String, Object> loginRequest) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            int underwriterId = (Integer) loginRequest.get("underwriterId");
            String password = (String) loginRequest.get("password");
            
            UnderWriter underWriter = underWriterService.authenticateUnderWriter(underwriterId, password);
            if (underWriter != null) {
                response.put("success", true);
                response.put("message", "Login successful");
                response.put("underwriter", underWriter);
                response.put("role", "underwriter");
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "Invalid Credential, please try again");
                return ResponseEntity.badRequest().body(response);
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Invalid Credential, please try again");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/vehicles/{underwriterId}")
    public ResponseEntity<Map<String, Object>> getVehiclesByUnderwriterId(@PathVariable int underwriterId) {
        Map<String, Object> response = new HashMap<>();
        
        // Check if underwriter exists
        UnderWriter underWriter = underWriterService.getUnderWriterById(underwriterId);
        if (underWriter == null) {
            response.put("success", false);
            response.put("message", "Invalid UnderWriter Id");
            return ResponseEntity.badRequest().body(response);
        }
        
        var vehicles = vehicleService.getVehiclesByUnderwriterId(underwriterId);
        if (vehicles.isEmpty()) {
            response.put("success", false);
            response.put("message", "No Vehicle is insured by the UnderWriter");
        } else {
            response.put("success", true);
            response.put("underwriter", underWriter);
            response.put("vehicles", vehicles);
            response.put("totalVehicles", vehicles.size());
        }
        
        return ResponseEntity.ok(response);
    }
}