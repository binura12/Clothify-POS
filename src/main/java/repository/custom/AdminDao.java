package repository.custom;

import entity.AdminEntity;
import repository.CrudDao;

import java.util.List;

public interface AdminDao extends CrudDao<AdminEntity> {
    boolean loginAdmin(String enteredGmail, String enteredPassword);
    boolean forgotPassword(String enteredGmail);
    boolean resetPassword(String email, String newPsw);
    List<AdminEntity> loadAdminProfile(String loggedInAdminEmail);
}
