import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Scanner;

public class Main {
    private static Person logged;
    private static final Scanner input = new Scanner ( System.in );
    private static Admin admin;
    private static Database database;

    public static void userPanel ( ) throws SQLException {
        int choice = 0;
        int id = 0;
        while (choice != 7) {
            System.out.println ( "1- Request ride" + "\n" + "2- View requests" + "\n" + "3- Accept request" +
                    "\n" + "4- Cancel request" + "\n" + "5- Rate driver\n" + "6- View driver rate" + "\n" + "7- Exit" );
            choice = input.nextInt ( );
            switch (choice) {
                case 1:
                    System.out.println ( "Source: " );
                    String source = input.nextLine ( );
                    System.out.println ( "Destination: " );
                    String destination = input.nextLine ( );
                    System.out.println ( "No. Of Passengers: " );
                    int numOfPassengers = input.nextInt ( );
                    LocalDate date = LocalDate.now ( );
                    Request.requestRide ( logged , source , destination , date , numOfPassengers );
                    break;
                case 2:
                    Request.viewRequests ( logged );
                    break;
                case 3:
                    System.out.println ( "Please enter request id" );
                    id = input.nextInt ( );
                    Request.acceptRequest ( id );
                    break;
                case 4:
                    System.out.println ( "Please enter request id" );
                    id = input.nextInt ( );
                    Request.cancelRequest ( logged , id );
                    break;
                case 5:
                    System.out.print ( "Enter the username: " );
                    String username = input.nextLine ( );
                    System.out.print ( "Enter the rating from 1 to 5 (1 worst, 5 best) :" );
                    double newRate = input.nextDouble ( );
                    Rating.rateDriver ( logged , username , newRate );
                    break;
                case 6:
                    System.out.println ( "Enter driver user name" );
                    String in = input.nextLine ( );
                    Rating.viewAvgRating ( logged , in );
                    break;
                case 7:
                    break;
                default:
                    System.out.println ( "Undefined" );
                    break;
            }
        }
    }

    public static void driverPanel ( ) throws SQLException {
        int choice = 0;
        int id = 0;
        while (choice != 6) {
            System.out.println ( "1- View rides" + "\n" + "2- Make offer" + "\n" + "3- Add favourite place" +
                    "\n" + "4- View active requests in favourite places" + "\n" + "5- View all rating\n" + "6- Exit" );
            choice = input.nextInt ( );
            switch (choice) {
                case 1:
                    Rides.viewRides ( );
                    break;
                case 2:
                    System.out.println ( "Please enter ride id" );
                    id = input.nextInt ( );
                    System.out.println ( "Enter a suitable price:" );
                    int price = input.nextInt ( );
                    Request.makeOffer ( logged , id , price );
                    break;
                case 3:
                    System.out.println ( "Opened LOCATIONS successfully" );
                    System.out.println ( "Source: " );
                    String in = input.nextLine ( );
                    Locations.addFavourite ( logged , in );
                    break;
                case 4:
                    Locations.displayFavorite ( logged );
                    break;
                case 5:
                    Rating.viewAllRating ( logged );
                    break;
                case 6:
                    break;
                default:
                    System.out.println ( "Undefined" );
                    break;
            }
        }
    }

    public static void adminPanel ( ) {
        int choice = 1;
        String userName;
        while (choice != 8) {
            System.out.println ( "1-List All Drivers Requests\n2-List All Drivers\n3-List All Users\n4-Suspend Driver\n" +
                    "5-Suspend User\n6-Activate Driver\n7-Activate User\n8-display All Events\n9-display Event by date\n10-display Event by ride id" +
                    "\n11-display event by username\n12-display event by driver's username\n13-display event by Event Name\n14-Back" );
            choice = input.nextInt ( );
            switch (choice) {
                case 1:
                    admin.listAllDriverRequests ( );
                    break;
                case 2:
                    admin.listAllDrivers ( );
                    break;
                case 3:
                    admin.listAllUsers ( );
                    break;
                case 4:
                    System.out.println ( "Enter Driver UserName" );
                    userName = input.next ( );
                    admin.suspendDriver ( userName );
                    break;
                case 5:
                    System.out.println ( "Enter UserName" );
                    userName = input.next ( );
                    admin.suspendUser ( userName );
                    break;
                case 6:
                    System.out.println ( "Enter Driver UserName" );
                    userName = input.next ( );
                    admin.activateDriver ( userName );
                    break;
                case 7:
                    System.out.println ( "Enter UserName" );
                    userName = input.next ( );
                    admin.activateUser ( userName );
                    break;
                case 8:
                    System.out.println ( "Display All Events" );
                    admin.displayEvent ( );
                    break;
                case 9:
                    System.out.println ( "Display Event by date" );
                    System.out.println ( "Enter the date in the following format: (YYYY/MM/DD)" );
                    String date = input.nextLine ( );
                    admin.displayEventByDate ( (Date.from ( Instant.parse ( date ) )) );
                    break;
                case 10:
                    System.out.println ( "Display Event by ride id" );
                    System.out.println ( "Enter the ride ID:" );
                    int id = input.nextInt ( );
                    admin.displayEventByRideID ( id );
                    break;
                case 11:
                    System.out.println ( "Display Event by user's username" );
                    System.out.println ( "Enter the username" );
                    String user = input.nextLine ( );
                    admin.displayEventByUser ( user );
                    break;
                case 12:
                    System.out.println ( "Display Event by driver's username" );
                    System.out.println ( "Enter the username" );
                    String driver = input.nextLine ( );
                    admin.displayEventByDriver ( driver );
                    break;
                case 13:
                    System.out.println ( "Display Event by Event Name" );
                    System.out.println ( "Enter the event name" );
                    EventName eventName = EventName.valueOf ( input.nextLine ( ) );
                    admin.displayEventByEventName ( eventName );
                    break;
                case 14:
                    break;
                default:
                    System.out.println ( "Undefined" );
                    break;
            }
        }
    }

    public static void main ( String[] args ) throws SQLException {
        database = Database.getInstance ( );
        database.start ( );
        Scanner input = new Scanner ( System.in );
        int choice;
        while (true) {
            System.out.println ( "1- Register" + "\n" + "2- Login" + "\n" + "3- Exit" );
            choice = input.nextInt ( );
            //register
            if ( choice == 1 ) {
                int in;
                System.out.println ( "1- Register as User" + "\n" + "2- Register as Driver" );
                in = input.nextInt ( );
                input.nextLine ( );
                System.out.println ( "UserName: " );
                String userName = input.nextLine ( );
                System.out.println ( "Name: " );
                String name = input.nextLine ( );
                System.out.println ( "Mobile number: " );
                String mobile = input.nextLine ( );
                System.out.println ( "Password: " );
                String password = input.nextLine ( );
                System.out.println ( "Email: (Keep it empty if you want it's optional) " );
                String email = input.nextLine ( );
                String birthDate="";
                if (in == 1) {
                    System.out.println ( "Birthdate: (in this format:YYYY/MM/DD) " );
                    birthDate = input.nextLine ( );
                }
                String natId = null;
                String lic = null;
                if ( in == 2 ) {
                    //driver
                    System.out.println ( "National ID: " );
                    natId = input.nextLine ( );
                    System.out.println ( "Driver License: " );
                    lic = input.nextLine ( );
                    Account.register ( in , userName , name , mobile , password , email , null , natId , lic );
                } else Account.register ( in , userName , name , mobile , password , email , birthDate , null , null );
            }
            //login
            else if ( choice == 2 ) {
                int choice2;
                System.out.println ( "1- Login as User?" + "\n" + "2- Login as Driver?" );
                choice2 = input.nextInt ( );
                //admin
                if ( choice2 == 3 ) {
                    //admin code
                    String username = "", password = "";
                    admin = Admin.getInstance ( );
                    System.out.println ( "Username: " );
                    input.nextLine ( );
                    username = input.nextLine ( );
                    System.out.println ( "Password: " );
                    password = input.nextLine ( );
                    if ( username.equals ( admin.getUsername ( ) ) && password.equals ( admin.getPassword ( ) ) )
                        adminPanel ( );
                    else System.out.println ( "Wrong Username or Password" );
                }
                //user
                else if ( choice2 == 1 ) {
                    logged = new User ( );
                    System.out.println ( "User name: " );
                    input.nextLine ( );
                    String username = input.nextLine ( );
                    System.out.println ( "Password: " );
                    String password = input.nextLine ( );
                    logged = Account.loginUser ( username , password );
                    if ( logged.getUserName ( ) != null ) {
                        userPanel ( );
                    } else {
                        System.out.println ( "Wrong Username or Password or Your account is suspended" );
                    }
                }
                //driver
                else if ( choice2 == 2 ) {
                    logged = new Driver ( );
                    System.out.println ( "User name: " );
                    input.nextLine ( );
                    String username = input.nextLine ( );
                    System.out.println ( "Password: " );
                    String password = input.nextLine ( );
                    logged = Account.loginDriver ( username , password );
                    if ( logged.getUserName ( ) != null ) {
                        driverPanel ( );
                    } else {
                        System.out.println ( "Wrong Username or Password or Your account isn't activated yet" );
                    }
                }
            } else if ( choice == 3 ) {
                input.close ( );
                System.exit ( 0 );
            } else {
                System.out.println ( "Invalid Input\n=======================" );
            }
        }
    }
}