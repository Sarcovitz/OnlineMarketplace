package pl.edu.wszib.marketplace.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.marketplace.models.Order;
import pl.edu.wszib.marketplace.repositories.IOrderRepository;
import pl.edu.wszib.marketplace.services.IOrderService;

import java.util.List;


@Service
public class OrderService implements IOrderService
{
    @Autowired
    IOrderRepository orderRepository;

    @Override
    public List<Order> getUserOrders(int userId)
    {
        return orderRepository.getUserOrders(userId);
    }

    @Override
    public void addOrder(Order order)
    {
        orderRepository.addOrder(order);
    }
}
