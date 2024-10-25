package repository.custom.impl;

import entity.AdminEntity;
import entity.SupplierEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;
import repository.custom.AdminDao;
import util.EncryptionUtil;
import util.HibernateUtil;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public class AdminDaoImpl implements AdminDao {

    public static String AdminLoggedInEmail;

    @Override
    public boolean loginAdmin(String enteredGmail, String enteredPassword) {
        try {
            enteredPassword = EncryptionUtil.md5Hash(enteredPassword);
            List<AdminEntity> adminList = null;
            Session session = HibernateUtil.getSession();
            session.beginTransaction();
            adminList = session.createQuery("from AdminEntity").list();
            session.getTransaction().commit();
            session.close();
            for (AdminEntity adminEntity : adminList) {
                if (adminEntity.getEmail().equals(enteredGmail) && adminEntity.getPassword().equals(enteredPassword)) {
                    AdminLoggedInEmail = adminEntity.getEmail();
                    return true;
                }
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public boolean forgotPassword(String enteredGmail) {
        List<AdminEntity> adminList = null;
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        adminList = session.createQuery("from AdminEntity").list();
        session.getTransaction().commit();
        session.close();
        for (AdminEntity adminEntity : adminList) {
            if (adminEntity.getEmail().equals(enteredGmail)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean resetPassword(String email, String newPsw) {
        try {
            Session session = HibernateUtil.getSession();
            session.beginTransaction();
            newPsw = EncryptionUtil.md5Hash(newPsw);
            String hql = "update AdminEntity set password = :newPsw where email = :email";
            Query query = session.createQuery(hql);
            query.setParameter("newPsw", newPsw);
            query.setParameter("email", email);
            int result = query.executeUpdate();
            session.getTransaction().commit();
            session.close();
            return result > 0;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<AdminEntity> loadAdminProfile(String loggedInAdminEmail) {
        Session session = HibernateUtil.getSession();
        String hql = "from AdminEntity where email = :loggedInAdminEmail";
        Query query = session.createQuery(hql);
        query.setParameter("loggedInAdminEmail", loggedInAdminEmail);
        List<AdminEntity> adminList = query.list();
        session.close();
        return adminList;
    }

    @Override
    public boolean save(AdminEntity admin) {
        try {
            String hashedPassword = EncryptionUtil.md5Hash(admin.getPassword());
            admin.setPassword(hashedPassword);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        Session session = HibernateUtil.getSession();

        session.beginTransaction();
        session.persist(admin);
        session.getTransaction().commit();
        session.close();

        return true;
    }
    @Override
    public boolean updateSupplier(SupplierEntity updatedSupplier) {
        return false;
    }

    @Override
    public boolean deleteSupplier(SupplierEntity deletedSupplierEntity) {
        return false;
    }
}
