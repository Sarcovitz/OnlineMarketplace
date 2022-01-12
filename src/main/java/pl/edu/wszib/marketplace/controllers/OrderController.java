package pl.edu.wszib.marketplace.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.edu.wszib.marketplace.models.Offer;
import pl.edu.wszib.marketplace.models.Order;
import pl.edu.wszib.marketplace.services.IOfferService;
import pl.edu.wszib.marketplace.services.IOrderService;
import pl.edu.wszib.marketplace.session.Session;

import javax.annotation.Resource;
import java.util.Optional;

@Controller
public class OrderController
{
    @Resource
    Session session;

    @Autowired
    IOrderService orderService;
    @Autowired
    IOfferService offerService;

    @RequestMapping(value = "/order/{offerId}", method = RequestMethod.GET)
    public String order(Model model, @PathVariable int offerId)
    {
        Optional<Offer> offer = offerService.getOfferById(offerId);
        orderService.addOrder(
                new Order(1, session.getUser().getId(), offer.get().getUserId(), offerId, offer.get().getPrice(),
                        System.currentTimeMillis() / 1000L, offer.get().getTitle()));
        model.addAttribute("logged", session.isLogged());
        model.addAttribute("orders", orderService.getUserOrders(session.getUser().getId()));
        return "redirect:/myOrders";
    }

    @RequestMapping(value = "/myOrders", method = RequestMethod.GET)
    public String order(Model model)
    {
        model.addAttribute("logged", session.isLogged());
        model.addAttribute("orders",orderService.getUserOrders(session.getUser().getId()));

        return "myOrders";
    }


}
