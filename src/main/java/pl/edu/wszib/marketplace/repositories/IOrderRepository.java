package pl.edu.wszib.marketplace.repositories;

import pl.edu.wszib.marketplace.models.Order;

import java.util.List;

public interface IOrderRepository
{
    List<Order> getUserOrders(int userId);
    void addOrder(Order order);
}
