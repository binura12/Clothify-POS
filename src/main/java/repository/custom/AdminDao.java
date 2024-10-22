package repository.custom;

import entity.AdminEntity;
import repository.CrudDao;

public interface AdminDao extends CrudDao<AdminEntity> {
    boolean loginAdmin(String enteredGmail, String enteredPassword);
    boolean forgotPassword(String enteredGmail);
    boolean resetPassword(String email, String newPsw);
}
