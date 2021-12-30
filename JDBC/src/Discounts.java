import java.sql.SQLException;
import java.util.Date;

/*
        Adding discounts functions:
            adding dicounts of Areas (by admin) - holiday
        BUT
        First ride, no. of passengers, birthday -> by functions that access: user db && request db
 */

public class Discounts implements IDiscounts
{
    double discounts;

    IDBDiscounts IDBdiscounts;   // for database discounts
    IDBAccount IDBaccounts; // to check if first ride (by 2 databases)
    static IDBRequest IDBrequests = new DBRequest (); // to check numOfPassengers

    public double getAllDiscounts ( Date date, Rides ride, String username) throws SQLException {
        // when take it from request database -> send current date, ride id , username of user
        discounts = IDBdiscounts.getAllDiscounts (date, ride );
        if (IDBaccounts.checkBD ( date , username )) discounts += 10;
        if (IDBrequests.checkFirstRide( ride.getID ( ) ) ) discounts += 10;
        if (IDBrequests.checkNumOfPassengers ( ride.getID ( ) , date ) == 2) discounts +=5;

        return discounts;

    }

    public void addDiscountByDate (Date date, double discount)
    {
        IDBdiscounts.addDiscountByDate (date, discount);
    }

    public void addDiscountByArea (String area, double discount)
    {
        // which is 10 -> but make it variable if admin want to change it
        IDBdiscounts.addDiscountByArea (area , discount);
    }


}
