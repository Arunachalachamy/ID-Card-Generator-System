-- =====================================================
-- Star Protect Vehicle Insurance Database Queries
-- Apache Derby Database Scripts
-- =====================================================

-- 1. CREATE DATABASE SCHEMA
-- =====================================================

-- Create UnderWriter table
CREATE TABLE UnderWriter (
    underwriterId INTEGER NOT NULL PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    dob DATE NOT NULL,
    joiningDate DATE NOT NULL,
    password VARCHAR(30) NOT NULL
);

-- Create Vehicle table  
CREATE TABLE Vehicle (
    policyId INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1000, INCREMENT BY 1),
    vehicleNo VARCHAR(10) NOT NULL,
    vehicleType VARCHAR(20) NOT NULL,
    customerName VARCHAR(50) NOT NULL,
    engineNo INTEGER NOT NULL,
    chasisNo INTEGER NOT NULL,
    phoneNo BIGINT NOT NULL,
    insuranceType VARCHAR(20) NOT NULL,
    fromDate DATE NOT NULL,
    toDate DATE NOT NULL,
    premiumAmount DECIMAL(10,2) NOT NULL,
    underwriterId INTEGER NOT NULL,
    vehicleValue DECIMAL(10,2),
    claimStatus VARCHAR(10) DEFAULT 'No',
    FOREIGN KEY (underwriterId) REFERENCES UnderWriter(underwriterId) ON DELETE CASCADE
);

-- Create sequence for UnderWriter ID (for auto generation)
CREATE SEQUENCE underwriter_seq START WITH 1001 INCREMENT BY 1;

-- 2. INSERT SAMPLE DATA
-- =====================================================

-- Insert sample UnderWriter data
INSERT INTO UnderWriter (underwriterId, name, dob, joiningDate, password) VALUES 
(1001, 'John Smith', '1985-06-15', '2023-01-15', 'john123'),
(1002, 'Sarah Johnson', '1990-03-22', '2023-02-10', 'sarah456'),
(1003, 'Mike Davis', '1988-11-08', '2023-03-05', 'mike789'),
(1004, 'Emily Brown', '1992-12-03', '2023-04-20', 'emily321'),
(1005, 'David Wilson', '1987-09-18', '2023-05-12', 'david654');

-- Insert sample Vehicle data
INSERT INTO Vehicle (vehicleNo, vehicleType, customerName, engineNo, chasisNo, phoneNo, insuranceType, fromDate, toDate, premiumAmount, underwriterId, vehicleValue, claimStatus) VALUES 
('KA01AB1234', '4-wheeler', 'Rajesh Kumar', 123456, 789012, 9876543210, 'Full Insurance', '2024-01-01', '2024-12-31', 35000.00, 1001, 1000000, 'No'),
('KA02CD5678', '2-wheeler', 'Priya Sharma', 234567, 890123, 9876543211, 'Third Party', '2024-02-01', '2025-01-31', 7500.00, 1002, 250000, 'No'),
('TN03EF9012', '4-wheeler', 'Suresh Reddy', 345678, 901234, 9876543212, 'Full Insurance', '2024-03-01', '2025-02-28', 42000.00, 1003, 1200000, 'Yes'),
('MH04GH3456', '2-wheeler', 'Anjali Patel', 456789, 012345, 9876543213, 'Third Party', '2024-04-01', '2025-03-31', 8750.00, 1004, 300000, 'No');

-- 3. USEFUL QUERIES FOR TESTING
-- =====================================================

-- View all UnderWriters
SELECT * FROM UnderWriter ORDER BY underwriterId;

-- View all Vehicles with UnderWriter details
SELECT 
    v.policyId,
    v.vehicleNo,
    v.vehicleType,
    v.customerName,
    v.insuranceType,
    v.premiumAmount,
    u.name AS underwriterName,
    u.underwriterId
FROM Vehicle v
JOIN UnderWriter u ON v.underwriterId = u.underwriterId
ORDER BY v.policyId;

-- Find UnderWriter by ID
SELECT * FROM UnderWriter WHERE underwriterId = 1001;

-- Find Vehicle by Policy ID
SELECT * FROM Vehicle WHERE policyId = 1000;

-- Find Vehicle by Vehicle Number
SELECT * FROM Vehicle WHERE vehicleNo = 'KA01AB1234';

-- Find all Vehicles by UnderWriter ID
SELECT * FROM Vehicle WHERE underwriterId = 1001;

-- Count total vehicles per UnderWriter
SELECT 
    u.underwriterId,
    u.name,
    u.joiningDate,
    COUNT(v.policyId) as totalVehicles
FROM UnderWriter u
LEFT JOIN Vehicle v ON u.underwriterId = v.underwriterId
GROUP BY u.underwriterId, u.name, u.joiningDate
ORDER BY u.underwriterId;

-- 4. ADMINISTRATIVE QUERIES
-- =====================================================

-- Update UnderWriter password
UPDATE UnderWriter SET password = 'newpassword123' WHERE underwriterId = 1001;

-- Delete UnderWriter (will also delete associated vehicles due to CASCADE)
-- DELETE FROM UnderWriter WHERE underwriterId = 1005;

-- Update Vehicle insurance type
UPDATE Vehicle SET insuranceType = 'Third Party' WHERE policyId = 1000;

-- Update Vehicle claim status
UPDATE Vehicle SET claimStatus = 'Yes' WHERE policyId = 1001;

-- 5. BUSINESS LOGIC QUERIES
-- =====================================================

-- Find expired policies (where current date > toDate)
SELECT 
    v.policyId,
    v.vehicleNo,
    v.customerName,
    v.toDate,
    u.name AS underwriterName
FROM Vehicle v
JOIN UnderWriter u ON v.underwriterId = u.underwriterId
WHERE v.toDate < CURRENT_DATE;

-- Find policies expiring in next 30 days
SELECT 
    v.policyId,
    v.vehicleNo,
    v.customerName,
    v.toDate,
    u.name AS underwriterName
FROM Vehicle v
JOIN UnderWriter u ON v.underwriterId = u.underwriterId
WHERE v.toDate BETWEEN CURRENT_DATE AND CURRENT_DATE + 30;

-- Calculate total premium collected
SELECT SUM(premiumAmount) AS totalPremium FROM Vehicle;

-- Calculate total premium per UnderWriter
SELECT 
    u.underwriterId,
    u.name,
    SUM(v.premiumAmount) AS totalPremium,
    COUNT(v.policyId) AS totalPolicies
FROM UnderWriter u
LEFT JOIN Vehicle v ON u.underwriterId = v.underwriterId
GROUP BY u.underwriterId, u.name;

-- Find vehicles with claims
SELECT 
    v.policyId,
    v.vehicleNo,
    v.customerName,
    v.claimStatus,
    u.name AS underwriterName
FROM Vehicle v
JOIN UnderWriter u ON v.underwriterId = u.underwriterId
WHERE v.claimStatus = 'Yes';

-- 6. VALIDATION QUERIES
-- =====================================================

-- Check for duplicate vehicle numbers
SELECT vehicleNo, COUNT(*) 
FROM Vehicle 
GROUP BY vehicleNo 
HAVING COUNT(*) > 1;

-- Check for UnderWriters without vehicles
SELECT u.underwriterId, u.name
FROM UnderWriter u
LEFT JOIN Vehicle v ON u.underwriterId = v.underwriterId
WHERE v.underwriterId IS NULL;

-- Check password validation (should contain both letters and numbers)
SELECT underwriterId, name, password
FROM UnderWriter
WHERE password NOT SIMILAR TO '%[0-9]%[A-Za-z]%|%[A-Za-z]%[0-9]%';

-- 7. CLEANUP QUERIES
-- =====================================================

-- Drop tables (use carefully!)
-- DROP TABLE Vehicle;
-- DROP TABLE UnderWriter;
-- DROP SEQUENCE underwriter_seq;

-- 8. SEQUENCE OPERATIONS
-- =====================================================

-- Get next value from sequence
-- VALUES NEXT VALUE FOR underwriter_seq;

-- Reset sequence (Derby doesn't support ALTER SEQUENCE RESTART, so drop and recreate)
-- DROP SEQUENCE underwriter_seq;
-- CREATE SEQUENCE underwriter_seq START WITH 1001 INCREMENT BY 1;

-- 9. PREMIUM CALCULATION EXAMPLES
-- =====================================================

-- New vehicle premium calculation (3.5% of vehicle value)
-- For 2-wheeler worth 250,000: 250,000 * 0.035 = 8,750
-- For 4-wheeler worth 1,000,000: 1,000,000 * 0.035 = 35,000

-- Renewal premium calculation (5% - 5% discount = 4.75% of vehicle value)
-- For 2-wheeler worth 250,000: 250,000 * 0.05 * 0.95 = 11,875
-- For 4-wheeler worth 1,000,000: 1,000,000 * 0.05 * 0.95 = 47,500

-- 10. INDEX CREATION (Optional for Performance)
-- =====================================================

-- Create indexes for better performance
CREATE INDEX idx_vehicle_underwriter ON Vehicle(underwriterId);
CREATE INDEX idx_vehicle_vehicleno ON Vehicle(vehicleNo);
CREATE INDEX idx_vehicle_policyid ON Vehicle(policyId);
CREATE INDEX idx_underwriter_id ON UnderWriter(underwriterId);

-- =====================================================
-- END OF DERBY SQL SCRIPTS
-- =====================================================