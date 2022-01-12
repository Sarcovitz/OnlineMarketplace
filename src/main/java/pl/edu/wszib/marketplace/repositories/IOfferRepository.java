package pl.edu.wszib.marketplace.repositories;

import pl.edu.wszib.marketplace.models.Offer;

import java.util.List;
import java.util.Optional;

public interface IOfferRepository
{
    Optional<Offer> getOfferById(int offerId);
    void addOffer(Offer offer);
    List<Offer> getUserOffers(int userId);
    List<Offer> getActiveOffers();
}
