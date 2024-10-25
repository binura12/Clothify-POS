package repository;

import repository.custom.impl.AdminDaoImpl;
import repository.custom.impl.CashierDaoImpl;
import repository.custom.impl.ProductDaoImpl;
import repository.custom.impl.SupplierDaoImpl;
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
            case supplier:
                return (T) new SupplierDaoImpl();
            case product:
                return (T) new ProductDaoImpl();
        }
        return null;
    }
}
