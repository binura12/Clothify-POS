package repository.custom.impl;

import entity.AdminEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;
import repository.custom.AdminDao;
import util.EncryptionUtil;
import util.HibernateUtil;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public class AdminDaoImpl implements AdminDao {

    @Override
    public boolean loginAdmin(String enteredGmail, String enteredPassword){
        try {
            enteredGmail = EncryptionUtil.md5Hash(enteredGmail);
            enteredPassword = EncryptionUtil.md5Hash(enteredPassword);
            List<AdminEntity> adminList = null;
            Session session = HibernateUtil.getSession();
            session.beginTransaction();
            adminList = session.createQuery("from AdminEntity").list();
            session.getTransaction().commit();
            session.close();
            for (AdminEntity adminEntity : adminList) {
                if (adminEntity.getEmail().equals(enteredGmail) && adminEntity.getPassword().equals(enteredPassword)) {
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
        try {
            enteredGmail = EncryptionUtil.md5Hash(enteredGmail);
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
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public boolean resetPassword(String email, String newPsw) {
        try {
            Session session = HibernateUtil.getSession();
            session.beginTransaction();
            email = EncryptionUtil.md5Hash(email);
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
    public boolean save(AdminEntity admin) {
        try {
            String hashedEmail = EncryptionUtil.md5Hash(admin.getEmail());
            String hashedPassword = EncryptionUtil.md5Hash(admin.getPassword());
            admin.setEmail(hashedEmail);
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
}
