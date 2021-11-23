import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    private static Person logged;
    private static final Scanner input = new Scanner(System.in);
    private static Admin admin;
    private static final LocationDB locationDB = LocationDB.getInstance();
    private static final UserDriverDB userDriverDB = UserDriverDB.getInstance();
    private static final RidesDB ridesDB = RidesDB.getInstance();

    public static void userPanel() throws SQLException {
        int choice = 0;
        while (choice != 7) {
            System.out.println("1- Request ride"+"\n"+"2- View requests"+"\n"+"3- Accept request"+
                    "\n"+"4- Refuse request"+"\n"+"5- Rate driver\n"+"6- View driver rate"+"\n"+"7- Exit");
            choice = input.nextInt();
            if (choice == 1) {
                ridesDB.requestRide(logged);
            }
            else if (choice == 2) {
                ridesDB.viewRequests(logged);
            }
            else if (choice == 3) {
                ridesDB.acceptRequest();
            }
            else if (choice == 4) {
                ridesDB.refuseRequest(logged);
            }
            else if (choice == 5) {
                UserDriverDB.rateDriver(logged);
            }
            else if(choice == 6){
                UserDriverDB.viewAvgRating(logged);
            }else{
                continue;
            }
        }
    }

    public static void driverPanel() throws SQLException {
        int choice = 0;
        while (choice != 5) {
            System.out.println("1- View rides"+"\n"+"2- Make offer"+"\n"+"3- Add favourite place"+
                    "\n"+"4- View active requests in favourite places"+"\n"+"5- View all rating\n"+"6- Exit");
            choice = input.nextInt();
            if (choice == 1) {
                ridesDB.loadDB();
            }
            else if (choice == 2) {
                ridesDB.makeOffer(logged);
            }
            else if(choice == 3){
                locationDB.addFavourite(logged);
            }
            else if(choice == 4){
                ridesDB.displayFavorite(logged);
            }else if( choice == 5){
                UserDriverDB.viewAllRating(logged);
            }else continue;
        }
    }

    public static void adminPanel(){
        int choice = 1;
        String userName;
        while(choice != 8) {
            System.out.println("1-List All Drivers Requests");
            System.out.println("2-List All Drivers");
            System.out.println("3-List All Users");
            System.out.println("4-Suspend Driver");
            System.out.println("5-Suspend User");
            System.out.println("6-Activate Driver");
            System.out.println("7-Activate User");
            System.out.println("8-Back");
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
        Scanner input = new Scanner(System.in);
        int choice;
        while (true) {
            System.out.println("1- Register"+"\n"+"2- Login"+"\n"+"3- Exit");
            choice = input.nextInt();
            if (choice == 1) {
                userDriverDB.register();
            }
            else if (choice == 2) {
                int choice2;
                System.out.println("1- Login as User?"+"\n"+"2- Login as Driver?");
                choice2 = input.nextInt();
                if(choice2 == 3){
                    //admin code
                    admin = Admin.getInstance();
                    adminPanel();
                }
                else if(choice2 == 1) {
                    logged = userDriverDB.loginUser();
                    if (logged.getUserName()!="null") {
                        userPanel();
                    }else{
                        System.out.println("Wrong Username or Password");
                    }
                }
                else if(choice2 == 2){
                    logged = userDriverDB.loginDriver();
                    if (logged.getUserName() != "null") {
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