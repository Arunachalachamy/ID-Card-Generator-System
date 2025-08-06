package com.starprotect.vehicleinsurance.service;

import com.starprotect.vehicleinsurance.bean.UnderWriter;
import com.starprotect.vehicleinsurance.dao.UnderWriterDao;
import com.starprotect.vehicleinsurance.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UnderWriterServiceImpl implements UnderWriterService {

    @Autowired
    private UnderWriterDao underWriterDao;

    @Override
    public int registerUnderWriter(UnderWriter underWriter) {
        // Generate auto ID
        int generatedId = underWriterDao.generateUnderWriterId();
        underWriter.setUnderwriterId(generatedId);
        
        // Set joining date to current date
        underWriter.setJoiningDate(LocalDate.now());
        
        return underWriterDao.createUnderWriter(underWriter);
    }

    @Override
    public UnderWriter getUnderWriterById(int underwriterId) {
        return underWriterDao.findById(underwriterId);
    }

    @Override
    public List<UnderWriter> getAllUnderWriters() {
        return underWriterDao.findAll();
    }

    @Override
    public boolean updateUnderWriterPassword(int underwriterId, String newPassword) {
        if (!validatePassword(newPassword)) {
            return false;
        }
        return underWriterDao.updatePassword(underwriterId, newPassword);
    }

    @Override
    public boolean deleteUnderWriter(int underwriterId) {
        return underWriterDao.deleteById(underwriterId);
    }

    @Override
    public UnderWriter authenticateUnderWriter(int underwriterId, String password) {
        return underWriterDao.authenticateUnderWriter(underwriterId, password);
    }

    @Override
    public boolean validatePassword(String password) {
        return ValidationUtil.isValidPassword(password);
    }
}