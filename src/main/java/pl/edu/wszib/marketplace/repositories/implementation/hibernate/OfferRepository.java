package pl.edu.wszib.marketplace.repositories.implementation.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.marketplace.models.Offer;
import pl.edu.wszib.marketplace.repositories.IOfferRepository;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

@Repository
public class OfferRepository implements IOfferRepository
{
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Optional<Offer> getOfferById(int offerId)
    {
        Session session = this.sessionFactory.openSession();
        Query<Offer> query = session.createQuery("FROM pl.edu.wszib.marketplace.models.Offer WHERE id = :offerId");
        query.setParameter("offerId", offerId);
        List<Offer> result = query.getResultList();
        try
        {
            Offer offer = query.getSingleResult();
            session.close();
            return Optional.of(offer);
        }
        catch (NoResultException ex)
        {
            session.close();
            return Optional.empty();
        }
    }

    @Override
    public void addOffer(Offer offer)
    {
        Session session = this.sessionFactory.openSession();
        Transaction tran = null;
        try
        {
            tran = session.beginTransaction();
            session.save(offer);
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
    public List<Offer> getUserOffers(int userId)
    {
        Session session = this.sessionFactory.openSession();
        Query<Offer> query = session.createQuery("FROM pl.edu.wszib.marketplace.models.Offer WHERE userId = :userId");
        query.setParameter("userId", userId);
        List<Offer> result = query.getResultList();
        session.close();
        return result;
    }

    @Override
    public List<Offer> getActiveOffers()
    {
        Session session = this.sessionFactory.openSession();
        Query<Offer> query = session.createQuery("FROM pl.edu.wszib.marketplace.models.Offer WHERE quantity > 0");
        List<Offer> result = query.getResultList();
        session.close();
        return result;
    }
}
