package com.starprotect.vehicleinsurance.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController {

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> adminLogin(@RequestBody Map<String, String> loginRequest) {
        String userId = loginRequest.get("userId");
        String password = loginRequest.get("password");
        
        Map<String, Object> response = new HashMap<>();
        
        // Check admin credentials
        if ("admin".equals(userId) && "admin123".equals(password)) {
            response.put("success", true);
            response.put("message", "Admin login successful");
            response.put("role", "admin");
            return ResponseEntity.ok(response);
        } else {
            response.put("success", false);
            response.put("message", "Invalid Credential, please try again");
            return ResponseEntity.badRequest().body(response);
        }
    }
}