package service.custom;

import dto.Cashier;
import service.SuperService;

public interface CashierService extends SuperService {
    boolean loginCashier(String enteredGmail, String enteredPassword);
    boolean forgotPassword(String enteredGmail);
    int generateOTP();
    boolean addCashier(Cashier cashier);
    boolean resetPassword(String text, String text1);
    Cashier loadCashierProfile(String loggedInCashierEmail);
}
