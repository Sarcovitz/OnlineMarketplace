package pl.edu.wszib.marketplace.services;

import pl.edu.wszib.marketplace.models.Offer;

import java.util.List;
import java.util.Optional;

public interface IOfferService
{
    Optional<Offer> getOfferById(int offerId);
    void addOffer(Offer offer);
    List<Offer> getUserOffers(int userId);
    List<Offer> getActiveOffers();
}
