package service.custom.impl;

import dto.Admin;
import entity.AdminEntity;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.AdminDao;
import service.custom.AdminService;
import util.DaoType;

import java.util.Random;

public class AdminServiceImpl implements AdminService {
    @Override
    public boolean loginAdmin(String enteredGmail, String enteredPassword) {
        AdminDao adminDao = DaoFactory.getInstance().getDaoType(DaoType.admin);
        return adminDao.loginAdmin(enteredGmail, enteredPassword);
    }

    @Override
    public boolean forgotPassword(String enteredGmail) {
        AdminDao adminDao = DaoFactory.getInstance().getDaoType(DaoType.admin);
        return adminDao.forgotPassword(enteredGmail);
    }

    @Override
    public int generateOTP() {
        Random random = new Random();
        int otp = random.nextInt(900000) + 100000;
        return Integer.parseInt(String.format("%06d", otp));
    }

    @Override
    public boolean resetPassword(String email, String newPsw) {
        AdminDao adminDao = DaoFactory.getInstance().getDaoType(DaoType.admin);
        return adminDao.resetPassword(email, newPsw);
    }

    @Override
    public boolean addAdmin(Admin admin) {
        AdminEntity adminEntity = new ModelMapper().map(admin, AdminEntity.class);

        AdminDao adminDao = DaoFactory.getInstance().getDaoType(DaoType.admin);
        adminDao.save(adminEntity);

        return true;
    }
}
