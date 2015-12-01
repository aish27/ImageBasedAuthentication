
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import java.sql.*;

/**
 * Connects to the database and makes modifications to the database.
 *
 * @author Aishwarya
 */
public class jdbcConnect
{

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/Bank";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "";

    /**
     * Returns the information of a given id.
     *
     * @param id the id of the required user.
     * @param type the string type expected by the user.
     * @return a string including the image, image name and image modifications
     * or just the name.
     */
    public static String getUserInfo(String id, String type)
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.prepareStatement("SELECT * from User where uid=?");
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            String img = null;
            String imgGivenName = null;
            String mod = null;
            String name = null;
            while (rs.next())
            {
                name = rs.getString(2);
                System.out.println(rs.getString(2));
                img = rs.getString(3);
                System.out.println(rs.getString(3));
                imgGivenName = rs.getString(4);
                System.out.println(rs.getString(4));
                mod = rs.getString(5);
            }
            if ("name".equals(type))
            {
                return name;
            }
            return img + "/" + imgGivenName + "/" + mod;
        } catch (SQLException se)
        {
            se.printStackTrace();
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                if (stmt != null)
                {
                    stmt.close();

                }
            } catch (SQLException se2)
            {

            }
            try
            {
                if (conn != null)
                {
                    conn.close();

                }
            } catch (SQLException se)
            {
                se.printStackTrace();
                return null;
            }
        }
        return null;
    }

    /**
     * Adds a user to the database.
     *
     * @param userName the name of the user to be added.
     * @param picText the name of the picture of the user.
     * @param picNameText the name provided by the user for the picture.
     * @param allModifications the modifications the user chose.
     * @return an int which is the id of the user.
     */
    public static int addUser(String userName, String picText,
            String picNameText, String allModifications)
    {
        Connection conn = null;
        Statement stmt = null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement();
             String temp =       "INSERT into User(uName,uImage,userGivenName,allModifications) values(\""+userName +"\",\"" + picText + "\",\"" + picNameText + "\",\"" + allModifications + "\")" ;

             System.out.println(temp);
            stmt.executeUpdate(temp);
            int id = -1;

            temp = "SELECT * from User where uName=\"" + userName + "\"";

            ResultSet rs = stmt.executeQuery(temp);

            while (rs.next())
            {
                id = rs.getInt(1);
                System.out.println("ID is:" + id);

            }

            return id;
        } catch (MySQLIntegrityConstraintViolationException a)
        {
            System.out.println(
                    "Username is already taken. Please enter another username.");
            return -2;

        } catch (Exception se)
        {
            //Handle errors for JDBC
            se.printStackTrace();
            return -1;
        }

    }
}
