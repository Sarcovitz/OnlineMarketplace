package pl.edu.wszib.marketplace.models;

public class Order
{
    private int id;
    private int userId;
    private int sellerId;
    private int offerId;
    private double price;
    private long timestamp;
    private String title;

    public Order() { }

    public Order(int id, int userId, int sellerId, int offerId, double price, long timestamp, String title)
    {
        this.id = id;
        this.userId = userId;
        this.sellerId = sellerId;
        this.offerId = offerId;
        this.price = price;
        this.timestamp = timestamp;
        this.title = title;
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

    public int getSellerId()
    {
        return sellerId;
    }

    public void setSellerId(int sellerId)
    {
        this.sellerId = sellerId;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public long getTimestamp()
    {
        return timestamp;
    }

    public void setTimestamp(long timestamp)
    {
        this.timestamp = timestamp;
    }

    public int getOfferId()
    {
        return offerId;
    }

    public void setOfferId(int offerId)
    {
        this.offerId = offerId;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }
}
