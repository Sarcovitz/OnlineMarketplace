package pl.edu.wszib.marketplace.repositories.implementation.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.marketplace.models.Offer;
import pl.edu.wszib.marketplace.models.User;
import pl.edu.wszib.marketplace.repositories.IUserRepository;

import javax.persistence.NoResultException;
import java.sql.*;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository implements IUserRepository
{
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Optional<User> getUserByLogin(String login)
    {
        Session session = this.sessionFactory.openSession();
        Query<User> query = session.createQuery("FROM pl.edu.wszib.marketplace.models.User WHERE login  = :login");
        query.setParameter("login", login);
        List<User> result = query.getResultList();
        try
        {
            User user = query.getSingleResult();
            session.close();
            return Optional.of(user);
        }
        catch (NoResultException ex)
        {
            session.close();
            return Optional.empty();
        }
    }

    @Override
    public void addUser(User user)
    {
        Session session = this.sessionFactory.openSession();
        Transaction tran = null;
        try
        {
            tran = session.beginTransaction();
            session.save(user);
            tran.commit();
        }
        catch (Exception ex)
        {
            if(tran!=null) tran.rollback();
        }
        finally
        {
            session.close();
        }
    }

    @Override
    public Optional<User> getUserById(int id)
    {
        Session session = this.sessionFactory.openSession();
        Query<User> query = session.createQuery("FROM pl.edu.wszib.marketplace.models.User WHERE id  = :id");
        query.setParameter("id", id);
        List<User> result = query.getResultList();
        try
        {
            User user = query.getSingleResult();
            session.close();
            return Optional.of(user);
        }
        catch (NoResultException ex)
        {
            session.close();
            return Optional.empty();
        }
    }
}
