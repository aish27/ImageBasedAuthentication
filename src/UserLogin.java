
/**
 * This class interacts with the classes having the user interfaces as well as
 * the jdbc connections.
 *
 * @author Aishwarya
 */
class UserLogin
{

    static jdbcConnect c = new jdbcConnect();
    /* Checks if a user exists in the database and 
     * returns a string describing that user.
     * @return String value containing the several attributes describing a user.
     */
    public static String userExists(String userID)
    {
        //findUserName from database.
        
        String str = c.getUserInfo(userID, "");
        return str;
    }

    /* Adds a user to the database and returns that person's id.
     *
     * @param username the username of the user
     * @param picText the picture chosen by the user
     * @param picNameText the picture's name provided by the user.
     * @param allModifications all the modifications provided by the user.
     * @return an integer value containing the user id.
     */
    public static int addUser(String userName, String picText,
            String picNameText, String allModifications)
    {
        //findUserName from database.
        
        int id = c.addUser(userName, picText, picNameText, allModifications);
        return id;
    }

    /* Gets the user name of a particular id.
     * @param id the user id of the user.
     * @return String value containing the username.
     */
    public static String getUser(String id)
    {
        //findUserName from database.
        
        String name = c.getUserInfo(id, "name");
        return name;
    }

}
