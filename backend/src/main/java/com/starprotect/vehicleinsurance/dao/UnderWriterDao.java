package com.starprotect.vehicleinsurance.dao;

import com.starprotect.vehicleinsurance.bean.UnderWriter;
import java.util.List;

public interface UnderWriterDao {
    int createUnderWriter(UnderWriter underWriter);
    UnderWriter findById(int underwriterId);
    List<UnderWriter> findAll();
    boolean updatePassword(int underwriterId, String newPassword);
    boolean deleteById(int underwriterId);
    UnderWriter authenticateUnderWriter(int underwriterId, String password);
    int generateUnderWriterId();
}