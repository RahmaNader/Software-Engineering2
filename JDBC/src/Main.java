import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    private static Person logged;
    private static final Scanner input = new Scanner(System.in);
    private static Admin admin;

    public static void userPanel() throws SQLException {
        int choice = 0;
        while (choice != 7) {
            System.out.println("1- Request ride"+"\n"+"2- View requests"+"\n"+"3- Accept request"+
                    "\n"+"4- Cancel request"+"\n"+"5- Rate driver\n"+"6- View driver rate"+"\n"+"7- Exit");
            choice = input.nextInt();
            switch (choice) {
                case 1:
                    Request.requestRide(logged);
                    break;
                case 2:
                    Request.viewRequests(logged);
                    break;
                case 3:
                    Request.acceptRequest();
                    break;
                case 4:
                    Request.cancelRequest(logged);
                    break;
                case 5:
                    Rating.rateDriver(logged);
                    break;
                case 6:
                    Rating.viewAvgRating(logged);
                    break;
                case 7:
                    break;
                default:
                    System.out.println("Undefined");
                    break;
            }
        }
    }

    public static void driverPanel() throws SQLException {
        int choice = 0;
        while (choice != 6) {
            System.out.println("1- View rides"+"\n"+"2- Make offer"+"\n"+"3- Add favourite place"+
                    "\n"+"4- View active requests in favourite places"+"\n"+"5- View all rating\n"+"6- Exit");
            choice = input.nextInt();
            switch (choice){
                case 1:
                    Rides.viewRides();
                    break;
                case 2:
                    Request.makeOffer(logged);
                    break;
                case 3:
                    Locations.addFavourite(logged);
                    break;
                case 4:
                    Locations.displayFavorite(logged);
                    break;
                case 5:
                    Rating.viewAllRating(logged);
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Undefined");
                    break;
            }
        }
    }

    public static void adminPanel(){
        int choice = 1;
        String userName;
        while(choice != 8) {
            System.out.println("1-List All Drivers Requests\n2-List All Drivers\n3-List All Users\n4-Suspend Driver\n" +
                    "5-Suspend User\n6-Activate Driver\n7-Activate User\n8-Back");
            choice = input.nextInt();
            switch (choice) {
                case 1:
                    admin.listAllDriverRequests();
                    break;
                case 2:
                    admin.listAllDrivers();
                    break;
                case 3:
                    admin.listAllUsers();
                    break;
                case 4:
                    System.out.println("Enter Driver UserName");
                    userName = input.next();
                    admin.suspendDriver(userName);
                    break;
                case 5:
                    System.out.println("Enter UserName");
                    userName = input.next();
                    admin.suspendUser(userName);
                    break;
                case 6:
                    System.out.println("Enter Driver UserName");
                    userName = input.next();
                    admin.activateDriver(userName);
                    break;
                case 7:
                    System.out.println("Enter UserName");
                    userName = input.next();
                    admin.activateUser(userName);
                    break;
                case 8:
                    break;
                default:
                    System.out.println("Undefined");
                    break;
            }
        }
    }

    public static void main(String[] args) throws SQLException {
        Database myDatabase = new Database();
        Scanner input = new Scanner(System.in);
        int choice;
        while (true) {
            System.out.println("1- Register"+"\n"+"2- Login"+"\n"+"3- Exit");
            choice = input.nextInt();
            if (choice == 1) {
                Account.register();
            }
            else if (choice == 2) {
                int choice2;
                System.out.println("1- Login as User?"+"\n"+"2- Login as Driver?");
                choice2 = input.nextInt();
                if(choice2 == 3){
                    //admin code
                    String username = "" , password = "";
                    admin = Admin.getInstance();
                    System.out.println("Username: ");
                    input.nextLine();
                    username = input.nextLine();
                    System.out.println("Password: ");
                    password = input.nextLine();
                    if(username.equals(admin.getUsername())&& password.equals(admin.getPassword())) adminPanel();
                    else System.out.println("Wrong Username or Password");
                }
                else if(choice2 == 1) {
                    logged = new User();
                    logged = Account.loginUser();
                    if (logged.getUserName()!=null) {
                        userPanel();
                    }else{
                        System.out.println("Wrong Username or Password or Your account is suspended");
                    }
                }
                else if(choice2 == 2){
                    logged = new Driver();
                    logged = Account.loginDriver();
                    if (logged.getUserName() != null) {
                        driverPanel();
                    }else{
                        System.out.println("Wrong Username or Password or Your account isn't activated yet");
                    }
                }
            }
            else if (choice == 3){
                System.exit(0);
            }
            else{
                System.out.println("Invalid Input\n=======================");
            }
        }
    }
}