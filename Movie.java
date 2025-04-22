import java.util.ArrayList;

public class Movie
{
    String title;
    private static final int TOTAL_SEATS = 5;
    ArrayList<String> booked_customers = new ArrayList<>();

    public Movie(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }

    public int getTotalSeats() 
    {
        return TOTAL_SEATS;
    }

    public int getAvailableSeats() 
    {
        return TOTAL_SEATS - booked_customers.size();
    }

    public boolean bookTicket(String customer_name, int tickets)
    {
        if (booked_customers.size() + tickets <= TOTAL_SEATS) 
        {
            // Book the tickets for the customer
            for (int i = 0; i < tickets; i++) 
            {
                booked_customers.add(customer_name.toLowerCase()); // Store customer name in lowercase
            }
            System.out.println("Ticket booked for " + customer_name + " for movie " + title);
            return true;
        } 
        else 
        {
            System.out.println("Not enough seats available for " + customer_name + " for movie " + title);
            return false;
        }
    }

    public void cancelTicket(String customer_name) 
    {
        if (booked_customers.contains(customer_name)) 
        {
            booked_customers.remove(customer_name.toLowerCase()); // Remove customer name by lowercase
            System.out.println("Ticket cancelled for " + customer_name + " for movie " + title);
        } 
        else 
        {
            System.out.println("No booking found for " + customer_name + " for movie " + title);
        }
    }

    public void showBookedCustomers() 
    {
        System.out.println("Booked customers for " + title + ": ");
        for (String customer : booked_customers) 
        {
            System.out.println(customer);
        }
        if (booked_customers.isEmpty()) 
        {
            System.out.println("No customers booked for this movie.");
        }
    }

    public void showDetails(String title)
    {
        System.out.println("Movie Title: " + title);
        System.out.println("Total Seats: " + TOTAL_SEATS);
        System.out.println("Available Seats: " + (TOTAL_SEATS - booked_customers.size()));
    }
}
