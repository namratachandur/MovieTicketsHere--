import java.util.ArrayList;

public class Movie
{
    String title;
    private static final int TOTAL_SEATS = 5; // Constant for total seats
    ArrayList<String> booked_customers = new ArrayList<>();

    public Movie(String title) 
    {
        this.title = title; // Initialize the movie title
    }

    public String getTitle() // Gettter method for title
    {
        return title; 
    }

    public int getTotalSeats() // Getter method for total seats
    {
        return TOTAL_SEATS;
    }

    public int getAvailableSeats() // Getter method for available seats
    {
        return TOTAL_SEATS - booked_customers.size();
    }

    // Method to book tickets for a user
    public boolean bookTicket(String customer_name, int tickets)
    {
        // Check if required number of tickets are available
        if (booked_customers.size() + tickets <= TOTAL_SEATS) 
        {
            // Book the tickets for the user
            for (int i = 0; i < tickets; i++) 
            {
                booked_customers.add(customer_name.toLowerCase()); // Store user name in lowercase
            }
            System.out.println("Ticket booked for " + customer_name + " for movie " + title);
            return true;
        } 
        else 
        {
            // If not enough seats are available, display message
            System.out.println("Not enough seats available for " + customer_name + " for movie " + title);
            return false;
        }
    }

    // Method to cancel tickets for a user
    public void cancelTicket(String customer_name) 
    {
        if (booked_customers.contains(customer_name)) 
        {
            booked_customers.remove(customer_name.toLowerCase()); // Remove user name by lowercase
            System.out.println("Ticket cancelled for " + customer_name + " for movie " + title);
        } 
        else 
        {
            System.out.println("No booking found for " + customer_name + " for movie " + title);
        }
    }

    // Method to show booked customers for a selected movie
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

    // Method to show details of the movie
    public void showDetails()
    {
        System.out.println("Movie Title: " + this.title);
        System.out.println("Total Seats: " + TOTAL_SEATS);
        System.out.println("Available Seats: " + getAvailableSeats());
    }
}
