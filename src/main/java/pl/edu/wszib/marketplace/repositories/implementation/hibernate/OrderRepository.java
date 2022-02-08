package pl.edu.wszib.marketplace.repositories.implementation.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.marketplace.models.Offer;
import pl.edu.wszib.marketplace.models.Order;
import pl.edu.wszib.marketplace.repositories.IOrderRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderRepository implements IOrderRepository
{
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Order> getUserOrders(int userId)
    {
        Session session = this.sessionFactory.openSession();
        Query<Order> query = session.createQuery("FROM pl.edu.wszib.marketplace.models.Order WHERE userId = :userId");
        query.setParameter("userId", userId);
        List<Order> result = query.getResultList();
        session.close();
        return result;
    }

    @Override
    public void addOrder(Order order)
    {
        Session session = this.sessionFactory.openSession();
        Transaction tran = null;
        try
        {
            tran = session.beginTransaction();
            session.save(order);
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
}
