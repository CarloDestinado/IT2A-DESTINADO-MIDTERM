package carlomidterm;

import java.util.Scanner;

public class Event{

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String resp;
       
        Event test = new Event();  // Create a single instance for the main menu

        do {
            System.out.println("1. ADD");
            System.out.println("2. VIEW");
            System.out.println("3. UPDATE");
            System.out.println("4. DELETE");
            System.out.println("5. EXIT");

            System.out.print("Enter Action: ");
            int action = sc.nextInt();
            sc.nextLine();  // Consume the newline character

            switch (action) {
                case 1:
                    test.addEvent();
                    break;
                case 2:
                    test.viewEvent();
                    break;
                case 3:
                    test.updateEvent();
                    break;
                case 4:
                    test.deleteEvent();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;  // Exit the program
                default:
                    System.out.println("Invalid action. Please choose again.");
            }

            System.out.print("Continue? (yes/no): ");
            resp = sc.nextLine();
        } while (resp.equalsIgnoreCase("yes"));

        System.out.println("Thank You!");
    }
    
    public void addEvent() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        System.out.print("Event Name: ");
        String name = sc.nextLine();
        System.out.print("Event Date: ");
        String date = sc.nextLine();
        System.out.print("Event Location: ");
        String location = sc.nextLine();
        System.out.print("Event Description: ");
        String description = sc.nextLine();
        System.out.print("Event Organizer: ");
        String organizer = sc.nextLine();

        String sql = "INSERT INTO eventtable(event_name, event_date, location, description, organizer) VALUES (?, ?, ?, ?, ?)";
       
        conf.addEvent(sql, name, date, location, description, organizer);
    }
    
    private void viewEvent() {
        String qry = "SELECT * FROM eventtable";
        String[] hdrs = {"ID", "Name", "Date", "Location", "Description", "Organizer"};
        String[] clms = {"id", "event_name", "event_date", "location", "description", "organizer"};

        config conf = new config();
        conf.viewEvent(qry, hdrs, clms);
    }
    
    private void updateEvent() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the ID to Update: ");
        int id = sc.nextInt();
        sc.nextLine();  // Consume newline

        System.out.print("New Name: ");
        String nname = sc.nextLine();
        System.out.print("New Date: ");
        String ndate = sc.nextLine();
        System.out.print("New Location: ");
        String nlocation = sc.nextLine();
        System.out.print("New Description: ");
        String ndescription = sc.nextLine();
        System.out.print("New Organizer: ");
        String norganizer = sc.nextLine();

        String qry = "UPDATE event SET event_name = ?, event_date = ?, location = ?, description = ?, organizer = ? WHERE id = ?";
        config conf = new config();
        conf.updateEvent(qry, nname, ndate, nlocation, ndescription, norganizer, id);
    }
    
    private void deleteEvent() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the ID to Delete: ");
        int id = sc.nextInt();

        String qry = "DELETE FROM event WHERE id = ?";
        config conf = new config();
        conf.deleteEvent(qry, id);
    }
}