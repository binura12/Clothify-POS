package repository;

import repository.custom.impl.AdminDaoImpl;
import repository.custom.impl.CashierDaoImpl;
import util.DaoType;

public class DaoFactory {
    private static DaoFactory instance;
    private DaoFactory(){}

    public static DaoFactory getInstance() {
        return instance == null? instance = new DaoFactory() : instance;
    }

    public <T extends Superdao> T getDaoType(DaoType type){
        switch (type){
            case admin:
                return (T) new AdminDaoImpl();
            case cashier:
                return (T) new CashierDaoImpl();
        }
        return null;
    }
}
