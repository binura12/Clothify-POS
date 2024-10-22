package repository.custom.impl;

import entity.AdminEntity;
import entity.CashierEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;
import repository.custom.CashierDao;
import util.EncryptionUtil;
import util.HibernateUtil;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public class CashierDaoImpl implements CashierDao {
    @Override
    public boolean save(CashierEntity cashier) {
        try {
            String hashedEmail = EncryptionUtil.md5Hash(cashier.getEmail());
            String hashedPassword = EncryptionUtil.md5Hash(cashier.getPassword());
            cashier.setEmail(hashedEmail);
            cashier.setPassword(hashedPassword);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        Session session = HibernateUtil.getSession();

        session.beginTransaction();
        session.persist(cashier);
        session.getTransaction().commit();
        session.close();

        return true;
    }

    @Override
    public boolean loginCashier(String enteredGmail, String enteredPassword) {
        try {
            enteredGmail = EncryptionUtil.md5Hash(enteredGmail);
            enteredPassword = EncryptionUtil.md5Hash(enteredPassword);
            List<CashierEntity> cashierList = null;
            Session session = HibernateUtil.getSession();
            session.beginTransaction();
            cashierList = session.createQuery("from CashierEntity").list();
            session.getTransaction().commit();
            session.close();
            for (CashierEntity cashierEntity : cashierList) {
                if (cashierEntity.getEmail().equals(enteredGmail) && cashierEntity.getPassword().equals(enteredPassword)) {
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
            List<CashierEntity> cashierList = null;
            Session session = HibernateUtil.getSession();
            session.beginTransaction();
            cashierList = session.createQuery("from CashierEntity").list();
            session.getTransaction().commit();
            session.close();
            for (CashierEntity cashierEntity : cashierList) {
                if (cashierEntity.getEmail().equals(enteredGmail)) {
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
            String hql = "update CashierEntity set password = :newPsw where email = :email";
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
}
