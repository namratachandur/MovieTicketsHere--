import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class MovieTicketBookingSystem 
{
    private static Scanner input = new Scanner(System.in); // Scanner used across the class to read user input
    private static ArrayList<Movie> movies = new ArrayList<Movie>();

    public static void main(String[] args) 
    {
        // Initialize movies
        movies.add(new Movie("The Dark Knight", "Action Drama", "PG-13", 2008, "Christopher Nolan"));
        movies.add(new Movie("Notting Hill", "Romantic Comedy", "PG-13", 1999, "Roger Michell"));
        movies.add(new Movie("The Holiday", "Romantic Comedy", "PG-13", 2006, "Nancy Meyers"));
        movies.add(new Movie("Kung Fu Panda", "Animation", "PG", 2008, "Mark Osborne, John Stevenson"));
        movies.add(new Movie("The Lion King(1994)", "Animation", "G", 1994, "Roger Allers, Rob Minkoff"));
        movies.add(new Movie("Interstellar", "Sci-Fi", "PG-13", 2014, "Christopher Nolan"));

        System.out.println("Welcome to the Movie Ticket Booking System!");
        showAvailableMovies();

        boolean exit = false;
        // Display menu and handle user input
        while (!exit) 
        {
            System.out.println("\nWhat would you like to do?");
            System.out.println("1. Book a ticket");
            System.out.println("2. Cancel a ticket");
            System.out.println("3. View booked customers");
            System.out.println("4. Show details of a movie");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            
            try
            {
                int choice = input.nextInt();
                input.nextLine();

                switch (choice) 
                {
                    case 1 -> bookTickets();
                    case 2 -> cancelTicket();
                    case 3 -> showBookedCustomers();
                    case 4 -> showDetails();
                    case 5 -> 
                    {
                        System.out.println("Thank you for using the Movie Ticket Booking System!");
                        exit = true; // Exit the loop
                    }
                    default -> System.out.println("Invalid choice! Please try again.");
                }
            }
            catch (InputMismatchException e) 
            {
                // Handle invalid input (non-integer)
                System.out.println("Invalid input! Please enter a number.");
                input.nextLine(); // Clear the invalid input
            }
        }
    }

    private static void showAvailableMovies() 
    {
        // Display available movies and their available seats
        System.out.println("\nAvailable Movies:");
        for (int i = 0; i < movies.size(); i++) 
        {
            System.out.println((i + 1) + ". " + movies.get(i).getTitle() + " - " + movies.get(i).getAvailableSeats() + " seats available");
        }
    }

    // Method to book tickets for a movie
    private static void bookTickets() 
    {
        showAvailableMovies(); 
        // Ask user to select a movie
        System.out.print("\nEnter the serial number of the movie you want to book: ");
        int movieIndex = input.nextInt() - 1;
        input.nextLine();

        if (movieIndex < 0 || movieIndex >= movies.size()) 
        {
            // Validate movie selection
            System.out.println("Invalid movie selection!");
        }

        Movie selectedMovie = movies.get(movieIndex);
        // Ask user how many tickets to book
        System.out.println("How many tickets do you want to book? (Max: " + selectedMovie.getAvailableSeats() + ")");
        int ticketsToBook = input.nextInt();
        input.nextLine(); 
        if (ticketsToBook > selectedMovie.getAvailableSeats()) 
        {
            // Validate ticket count
            System.out.println("Only " + selectedMovie.getAvailableSeats() + " seats left!");
        }
        else if (ticketsToBook <= 0) 
        {
            // Validate ticket count
            System.out.println("Invalid number of tickets! Please try again.");
        }
        else
        {
            // Ask for customer name and book tickets
            System.out.print("Enter your name: ");
            String customerName = input.nextLine();
            customerName = customerName.trim(); // Remove leading/trailing spaces
            customerName = customerName.toLowerCase(); // Convert to lowercase
            if (selectedMovie.bookTicket(customerName, ticketsToBook)) 
            {
                System.out.println(ticketsToBook + " tickets booked successfully for " + selectedMovie.getTitle()); // Display success message
            }
        }
    }

    // Method to cancel tickts for a movie
    private static void cancelTicket() 
    {
        System.out.print("\nEnter your name: ");
        String customerName = input.nextLine();
        customerName = customerName.trim(); // Remove leading/trailing spaces
        customerName = customerName.toLowerCase(); // Convert to lowercase
        // Check if the user has any bookings
        ArrayList<Movie> customerBookings = new ArrayList<>();
        for (Movie movie : movies) 
        {
            if (movie.booked_customers.contains(customerName)) 
            {
                customerBookings.add(movie);
            }
        }
    
        if (customerBookings.isEmpty()) 
        {
            // If no bookings found, display message
            System.out.println("No bookings found for " + customerName);
        }
    
        // Display movies the user has booked
        System.out.println("\nYou have booked tickets for the following movies:");
        for (int i = 0; i < customerBookings.size(); i++) 
        {
            System.out.println((i + 1) + ". " + customerBookings.get(i).getTitle());
        }
    
        // Ask which movie to cancel
        System.out.print("\nEnter the serial number of the movie to cancel tickets for: ");
        int movieChoice = input.nextInt() - 1; // Convert to 0-based index
        input.nextLine(); 

        if (movieChoice < 0 || movieChoice >= customerBookings.size()) 
        {
            // Validate movie selection
            System.out.println("Invalid movie selection!");
        }
        Movie selectedMovie = customerBookings.get(movieChoice);
    
        // Ask how many tickets to cancel
        System.out.print("Enter the number of tickets to cancel (max " + selectedMovie.booked_customers.size() + "): ");
        int ticketsToCancel = input.nextInt();
        input.nextLine();
    
        // Validate ticket count
        if (ticketsToCancel <= 0 || ticketsToCancel > selectedMovie.booked_customers.size()) 
        {
            System.out.println("Invalid number of tickets! Please try again.");
        }
    
        // Cancel tickets (remove customer entries)
        for (int i = 0; i < ticketsToCancel; i++) 
        {
            selectedMovie.booked_customers.remove(customerName);
        }
    
        System.out.println("Successfully cancelled " + ticketsToCancel + " ticket(s) for " + selectedMovie.getTitle());
    }

    // Method to show booked customers for a selected movie
    private static void showBookedCustomers() 
    {
        System.out.println("\nSelect a movie to view booked customers:");
        showAvailableMovies();
        System.out.print("\nEnter the serial number of the movie: ");
        int movieIndex = input.nextInt() - 1;
        input.nextLine();

        if (movieIndex < 0 || movieIndex >= movies.size()) 
        {
            // Validate movie selection
            System.out.println("Invalid movie selection!");
        }
        Movie selectedMovie = movies.get(movieIndex);
        selectedMovie.showBookedCustomers();
    }

    // Method to show details of a selected movie
    public static void showDetails() 
    {
        System.out.println("\nSelect a movie to view details:");
        showAvailableMovies();
        System.out.print("\nEnter the number of the movie: ");
        int movieIndex = input.nextInt() - 1;
        input.nextLine();

        if (movieIndex < 0 || movieIndex >= movies.size()) 
        {
            // Validate movie selection
            System.out.println("Invalid movie selection!");
        }
        Movie selectedMovie = movies.get(movieIndex);
        selectedMovie.showDetails();
    }
}
