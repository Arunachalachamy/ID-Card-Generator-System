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