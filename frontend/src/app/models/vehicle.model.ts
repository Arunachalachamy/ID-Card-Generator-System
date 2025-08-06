export interface Vehicle {
  policyId?: number;
  vehicleNo: string;
  vehicleType: string;
  customerName: string;
  engineNo: number;
  chasisNo: number;
  phoneNo: number;
  insuranceType: string;
  fromDate?: string;
  toDate?: string;
  premiumAmount?: number;
  underwriterId: number;
  vehicleValue: number;
  claimStatus?: string;
}