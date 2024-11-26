package repository.custom.impl;

import entity.CashierEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;
import repository.custom.CashierDao;
import util.EncryptionUtil;
import util.HibernateUtil;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public class CashierDaoImpl implements CashierDao {

    public static String CashierLoggedInEmail;
    @Override
    public boolean save(CashierEntity cashier) {
        try {
            String hashedPassword = EncryptionUtil.md5Hash(cashier.getPassword());
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
    public boolean update(CashierEntity cashier) {
        return false;
    }

    @Override
    public boolean delete(CashierEntity cashier) {
        return false;
    }

    @Override
    public boolean loginCashier(String enteredGmail, String enteredPassword) {
        try {
            enteredPassword = EncryptionUtil.md5Hash(enteredPassword);
            List<CashierEntity> cashierList = null;
            Session session = HibernateUtil.getSession();
            session.beginTransaction();
            cashierList = session.createQuery("from CashierEntity").list();
            session.getTransaction().commit();
            session.close();
            for (CashierEntity cashierEntity : cashierList) {
                if (cashierEntity.getEmail().equals(enteredGmail) && cashierEntity.getPassword().equals(enteredPassword)) {
                    CashierLoggedInEmail = cashierEntity.getEmail();
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
        return false;
    }

    @Override
    public boolean resetPassword(String email, String newPsw) {
        try {
            Session session = HibernateUtil.getSession();
            session.beginTransaction();
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

    @Override
    public List<CashierEntity> loadCashierProfile(String loggedInCashierEmail) {
        Session session = HibernateUtil.getSession();
        String hql = "from CashierEntity where email = :loggedInCashierEmail";
        Query query = session.createQuery(hql);
        query.setParameter("loggedInCashierEmail", loggedInCashierEmail);
        List<CashierEntity> cashierList = query.list();
        session.close();
        return cashierList;
    }
}
