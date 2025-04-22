import java.util.ArrayList;

public class Movie
{
    String title;
    int total_seats;
    ArrayList<String> booked_customers = new ArrayList<>();

    public Movie(String title, int total_seats) 
    {
        this.title = title;
        this.total_seats = 5;
    }

    public String getTitle() 
    {
        return title;
    }

    public int getTotalSeats() 
    {
        return total_seats;
    }

    public int getAvailableSeats() 
    {
        return total_seats - booked_customers.size();
    }

    public boolean bookTicket(String customer_name, int tickets)
    {
        if (booked_customers.size() + tickets <= total_seats) 
        {
            for (int i = 0; i < tickets; i++) 
            {
                booked_customers.add(customer_name);
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
            booked_customers.remove(customer_name);
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
    }

    public void showDetails(String title)
    {
        System.out.println("Movie Title: " + title);
        System.out.println("Total Seats: " + total_seats);
        System.out.println("Available Seats: " + (total_seats - booked_customers.size()));
    }
}