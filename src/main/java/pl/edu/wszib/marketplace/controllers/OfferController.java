package pl.edu.wszib.marketplace.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.edu.wszib.marketplace.models.Offer;
import pl.edu.wszib.marketplace.services.IOfferService;
import pl.edu.wszib.marketplace.session.Session;

import javax.annotation.Resource;

@Controller
public class OfferController
{
    @Resource
    Session session;

    @Autowired
    IOfferService offerService;

    @RequestMapping(value = "/marketplace", method = RequestMethod.GET)
    public String marketplace(Model model)
    {
        model.addAttribute("logged", this.session.isLogged());
        model.addAttribute("userId", this.session.isLogged() ? this.session.getUser().getId() : -1);
        model.addAttribute("offers", offerService.getActiveOffers());

        return "marketplace";
    }

    @RequestMapping(value = "/myOffers", method = RequestMethod.GET)
    public String myOffers(Model model)
    {
        model.addAttribute("logged", this.session.isLogged());
        model.addAttribute("offers", offerService.getUserOffers(session.getUser().getId()));

        return "myOffers";
    }

    @RequestMapping(value = "/addOffer", method = RequestMethod.GET)
    public String addOffers(Model model)
    {
        model.addAttribute("logged", this.session.isLogged());

        return "addOffer";
    }

    @RequestMapping(value = "/offer/add", method = RequestMethod.POST)
    public String addOffer(Model model, @RequestParam String title, @RequestParam String description, @RequestParam double price, @RequestParam int quantity)
    {
        offerService.addOffer(new Offer(1, session.getUser().getId(), title, price, quantity, description));
        model.addAttribute("logged", this.session.isLogged());
        model.addAttribute("offers", offerService.getUserOffers(session.getUser().getId()));

        return "redirect:/myOffers";
    }

}
