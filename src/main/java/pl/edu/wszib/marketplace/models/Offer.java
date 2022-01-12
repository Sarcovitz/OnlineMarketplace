package pl.edu.wszib.marketplace.models;

import org.springframework.stereotype.Component;

public class Offer
{
    private int id;
    private int userId;
    private String title;
    private double price;
    private int quantity;
    private String description;

    public Offer() { }

    public Offer(int id, int userId, String title, double price, int quantity, String description)
    {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
}
