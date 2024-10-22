package service;

import service.custom.impl.AdminServiceImpl;
import service.custom.impl.CashierServiceImpl;
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
        }
        return null;
    }
}
