package pl.edu.wszib.marketplace.services;

import pl.edu.wszib.marketplace.models.Order;

import java.util.List;

public interface IOrderService
{
    List<Order> getUserOrders(int userId);
    void addOrder(Order order);

}
