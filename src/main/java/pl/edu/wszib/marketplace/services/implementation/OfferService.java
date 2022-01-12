package pl.edu.wszib.marketplace.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.marketplace.models.Offer;
import pl.edu.wszib.marketplace.repositories.IOfferRepository;
import pl.edu.wszib.marketplace.services.IOfferService;

import java.util.List;
import java.util.Optional;

@Service
public class OfferService implements IOfferService
{
    @Autowired
    IOfferRepository offerRepository;

    @Override
    public Optional<Offer> getOfferById(int offerId)
    {
        return offerRepository.getOfferById(offerId);
    }

    @Override
    public void addOffer(Offer offer)
    {
        offerRepository.addOffer(offer);
    }

    @Override
    public List<Offer> getUserOffers(int userId)
    {
        return offerRepository.getUserOffers(userId);
    }

    @Override
    public List<Offer> getActiveOffers()
    {
        return offerRepository.getActiveOffers();
    }

}
