package pl.edu.wszib.marketplace.repositories.implementation.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.marketplace.models.Offer;
import pl.edu.wszib.marketplace.repositories.IOfferRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class OfferRepository2 implements IOfferRepository
{
    @Autowired
    Connection connection;

    @Override
    public Optional<Offer> getOfferById(int offerId)
    {
        try {
            String sql = "SELECT * FROM toffer WHERE offer_id = ?";

            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setInt(1, offerId);

            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next()) {
                Offer offer = new Offer();
                offer.setId(rs.getInt("offer_id"));
                offer.setUserId(rs.getInt("offer_userId"));
                offer.setTitle(rs.getString("offer_title"));
                offer.setPrice(rs.getDouble("offer_price"));
                offer.setQuantity(rs.getInt("offer_quantity"));
                offer.setDescription(rs.getString("offer_description"));

                return Optional.of(offer);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public void addOffer(Offer offer)
    {
        try
        {
            String sql = "INSERT INTO toffer VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setNull(1, Types.INTEGER);
            preparedStatement.setInt(2, offer.getUserId());
            preparedStatement.setString(3, offer.getTitle());
            preparedStatement.setDouble(4, offer.getPrice());
            preparedStatement.setInt(5, offer.getQuantity());
            preparedStatement.setString(6, offer.getDescription());

            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();

            if (rs.next()) offer.setId(rs.getInt(1));

        } catch (SQLException throwables) { throwables.printStackTrace(); }
    }


    @Override
    public List<Offer> getUserOffers(int userId)
    {
        List<Offer> offers = new ArrayList<>();

        try
        {
            String sql = "SELECT * FROM toffer WHERE offer_userId = ?";

            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setInt(1, userId);

            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next())
            {
                Offer offer = new Offer();
                offer.setId(rs.getInt("offer_id"));
                offer.setUserId(rs.getInt("offer_userId"));
                offer.setTitle(rs.getString("offer_title"));
                offer.setPrice(rs.getDouble("offer_price"));
                offer.setQuantity(rs.getInt("offer_quantity"));
                offer.setDescription(rs.getString("offer_description"));

                offers.add(offer);
            }

        } catch (SQLException throwables) { throwables.printStackTrace(); }

        return offers;
    }

    @Override
    public List<Offer> getActiveOffers()
    {
        List<Offer> offers = new ArrayList<>();

        try
        {
            String sql = "SELECT * FROM toffer WHERE offer_quantity > 0";

            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);

            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next())
            {
                Offer offer = new Offer();
                offer.setId(rs.getInt("offer_id"));
                offer.setUserId(rs.getInt("offer_userId"));
                offer.setTitle(rs.getString("offer_title"));
                offer.setPrice(rs.getDouble("offer_price"));
                offer.setQuantity(rs.getInt("offer_quantity"));
                offer.setDescription(rs.getString("offer_description"));

                offers.add(offer);
            }

        } catch (SQLException throwables) { throwables.printStackTrace(); }

        return offers;
    }
}
