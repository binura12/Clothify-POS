package service.custom.impl;

import dto.Cashier;
import entity.AdminEntity;
import entity.CashierEntity;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.AdminDao;
import repository.custom.CashierDao;
import service.custom.CashierService;
import util.DaoType;

import java.util.Random;

public class CashierServiceImpl implements CashierService {
    @Override
    public boolean loginCashier(String enteredGmail, String enteredPassword) {
        CashierDao cashierDao = DaoFactory.getInstance().getDaoType(DaoType.cashier);
        return cashierDao.loginCashier(enteredGmail, enteredPassword);
    }

    @Override
    public boolean forgotPassword(String enteredGmail) {
        CashierDao cashierDao = DaoFactory.getInstance().getDaoType(DaoType.cashier);
        return cashierDao.forgotPassword(enteredGmail);
    }

    @Override
    public int generateOTP() {
        Random random = new Random();
        int otp = random.nextInt(900000) + 100000;
        return Integer.parseInt(String.format("%06d", otp));
    }

    @Override
    public boolean addCashier(Cashier cashier) {
        CashierEntity cashierEntity = new ModelMapper().map(cashier, CashierEntity.class);

        CashierDao cashierDao = DaoFactory.getInstance().getDaoType(DaoType.cashier);
        cashierDao.save(cashierEntity);

        return true;
    }

    @Override
    public boolean resetPassword(String email, String newPsw) {
        CashierDao cashierDao = DaoFactory.getInstance().getDaoType(DaoType.cashier);
        return cashierDao.resetPassword(email, newPsw);
    }
}
