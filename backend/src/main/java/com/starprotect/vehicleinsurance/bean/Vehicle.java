package com.starprotect.vehicleinsurance.bean;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Vehicle {
    private int policyId;
    private String vehicleNo;
    private String vehicleType;
    private String customerName;
    private int engineNo;
    private int chasisNo;
    private long phoneNo;
    private String insuranceType;
    private LocalDate fromDate;
    private LocalDate toDate;
    private BigDecimal premiumAmount;
    private int underwriterId;
    private BigDecimal vehicleValue;
    private String claimStatus;

    // Default constructor
    public Vehicle() {}

    // Parameterized constructor
    public Vehicle(int policyId, String vehicleNo, String vehicleType, String customerName, 
                   int engineNo, int chasisNo, long phoneNo, String insuranceType, 
                   LocalDate fromDate, LocalDate toDate, BigDecimal premiumAmount, 
                   int underwriterId, BigDecimal vehicleValue, String claimStatus) {
        this.policyId = policyId;
        this.vehicleNo = vehicleNo;
        this.vehicleType = vehicleType;
        this.customerName = customerName;
        this.engineNo = engineNo;
        this.chasisNo = chasisNo;
        this.phoneNo = phoneNo;
        this.insuranceType = insuranceType;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.premiumAmount = premiumAmount;
        this.underwriterId = underwriterId;
        this.vehicleValue = vehicleValue;
        this.claimStatus = claimStatus;
    }

    // Getters and Setters
    public int getPolicyId() {
        return policyId;
    }

    public void setPolicyId(int policyId) {
        this.policyId = policyId;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getEngineNo() {
        return engineNo;
    }

    public void setEngineNo(int engineNo) {
        this.engineNo = engineNo;
    }

    public int getChasisNo() {
        return chasisNo;
    }

    public void setChasisNo(int chasisNo) {
        this.chasisNo = chasisNo;
    }

    public long getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(long phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getInsuranceType() {
        return insuranceType;
    }

    public void setInsuranceType(String insuranceType) {
        this.insuranceType = insuranceType;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public BigDecimal getPremiumAmount() {
        return premiumAmount;
    }

    public void setPremiumAmount(BigDecimal premiumAmount) {
        this.premiumAmount = premiumAmount;
    }

    public int getUnderwriterId() {
        return underwriterId;
    }

    public void setUnderwriterId(int underwriterId) {
        this.underwriterId = underwriterId;
    }

    public BigDecimal getVehicleValue() {
        return vehicleValue;
    }

    public void setVehicleValue(BigDecimal vehicleValue) {
        this.vehicleValue = vehicleValue;
    }

    public String getClaimStatus() {
        return claimStatus;
    }

    public void setClaimStatus(String claimStatus) {
        this.claimStatus = claimStatus;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "policyId=" + policyId +
                ", vehicleNo='" + vehicleNo + '\'' +
                ", vehicleType='" + vehicleType + '\'' +
                ", customerName='" + customerName + '\'' +
                ", engineNo=" + engineNo +
                ", chasisNo=" + chasisNo +
                ", phoneNo=" + phoneNo +
                ", insuranceType='" + insuranceType + '\'' +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                ", premiumAmount=" + premiumAmount +
                ", underwriterId=" + underwriterId +
                ", vehicleValue=" + vehicleValue +
                ", claimStatus='" + claimStatus + '\'' +
                '}';
    }
}