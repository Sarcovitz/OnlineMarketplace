package pl.edu.wszib.marketplace.repositories.implementation;

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
    Connection connection;

    @Override
    public List<Order> getUserOrders(int userId)
    {
        List<Order> orders = new ArrayList<>();

        try
        {
            String sql = "SELECT * FROM torder WHERE order_userId = ?";

            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setInt(1, userId);

            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next())
            {
                Order order = new Order();
                order.setId(rs.getInt("order_id"));
                order.setUserId(rs.getInt("order_userId"));
                order.setSellerId(rs.getInt("order_sellerId"));
                order.setPrice(rs.getInt("order_offerId"));
                order.setPrice(rs.getDouble("order_price"));
                order.setTimestamp(rs.getLong("order_timestamp"));
                order.setTitle(rs.getString("order_title"));

                orders.add(order);
            }

        } catch (SQLException throwables) { throwables.printStackTrace(); }

        return orders;
    }

    @Override
    public void addOrder(Order order)
    {
        try
        {
            String sql = "INSERT INTO torder VALUES (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setNull(1, Types.INTEGER);
            preparedStatement.setInt(2, order.getUserId());
            preparedStatement.setInt(3, order.getSellerId());
            preparedStatement.setInt(4, order.getOfferId());
            preparedStatement.setDouble(5, order.getPrice());
            preparedStatement.setLong(6, order.getTimestamp());
            preparedStatement.setString(7, order.getTitle());

            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();

            if (rs.next()) order.setId(rs.getInt(1));

        } catch (SQLException throwables) { throwables.printStackTrace(); }
    }
}
