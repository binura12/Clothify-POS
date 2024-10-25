package service;

import service.custom.impl.AdminServiceImpl;
import service.custom.impl.CashierServiceImpl;
import service.custom.impl.ProductServiceImpl;
import service.custom.impl.SupplierServiceImpl;
import util.ServiceType;

public class ServiceFactory {
    private static ServiceFactory instance;
    private ServiceFactory(){}

    public static ServiceFactory getInstance() {
        return instance == null? instance = new ServiceFactory() : instance;
    }

    public <T extends SuperService> T getServiceType(ServiceType type){
        switch (type) {
            case admin:
                return (T) new AdminServiceImpl();
            case cashier:
                return (T) new CashierServiceImpl();
            case product:
                return (T) new ProductServiceImpl();
            case supplier:
                return (T) new SupplierServiceImpl();
        }
        return null;
    }
}
