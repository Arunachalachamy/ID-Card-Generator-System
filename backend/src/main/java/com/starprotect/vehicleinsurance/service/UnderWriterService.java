package com.starprotect.vehicleinsurance.service;

import com.starprotect.vehicleinsurance.bean.UnderWriter;
import java.util.List;

public interface UnderWriterService {
    int registerUnderWriter(UnderWriter underWriter);
    UnderWriter getUnderWriterById(int underwriterId);
    List<UnderWriter> getAllUnderWriters();
    boolean updateUnderWriterPassword(int underwriterId, String newPassword);
    boolean deleteUnderWriter(int underwriterId);
    UnderWriter authenticateUnderWriter(int underwriterId, String password);
    boolean validatePassword(String password);
}