import java.sql.SQLException;
import java.util.Date;

public interface IDiscounts
{
    double getAllDiscounts ( Date date, int id, String username) throws SQLException; // take date and ride id, and check all discounts on this date

    void addDiscountByDate (Date date , double discount); // to add new discount by date

    void addDiscountByArea (String area , double discount); // to add new discount by Area


}
