package repository.custom;

import entity.CashierEntity;
import repository.CrudDao;

public interface CashierDao extends CrudDao<CashierEntity> {
    boolean loginCashier(String enteredGmail, String enteredPassword);
    boolean forgotPassword(String enteredGmail);
    boolean resetPassword(String email, String newPsw);
}
