package service.custom;

import dto.Admin;
import service.SuperService;

public interface AdminService extends SuperService {
    boolean loginAdmin(String enteredGmail, String enteredPassword);
    boolean forgotPassword(String enteredGmail);
    int generateOTP();
    boolean resetPassword(String email, String newPsw);
    boolean addAdmin(Admin admin);
    Admin loadAdminProfile(String loggedInAdminEmail);
}
