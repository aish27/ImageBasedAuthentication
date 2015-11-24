
import java.util.HashMap;
import java.util.Random;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Class representing a login page that can be used to log into the account.
 * Parts of code obtained from Oracle's JAVAFX documentation.
 *
 * @author Aishwarya
 */
public class LoginPage extends Application 
{

    static ImageModifier a = new ImageModifier();
    static UserLogin ul = new UserLogin();


    /*Starts a Login Page and shows inital UI.
     *@param the current stage.
     */
    @Override
    public void start(Stage primaryStage)
    {
        primaryStage.setResizable(true);
        primaryStage.setTitle("Login");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setPadding(new Insets(100, 100, 100, 100));

        Text scenetitle = new Text("Login");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label userName = new Label("User ID [7 digit user id]:");
        grid.add(userName, 0, 1);

        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);

        Button btn = new Button("Sign in");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 4);
        //handles the button click by calling appropriate methods.
        btn.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                clearGrid(grid);
                if ("".equals(userTextField.getText()))
                {
                    start(primaryStage);
                }

                printTheLoginAndPassword(userTextField, grid, scenetitle,
                        primaryStage);
            }
        });

        Scene scene = new Scene(grid, 800, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /*After checking with the database regarding the existence of the values, 
     *shows the credentials.
     *
     * @param userTextField the user's name
     * @param grid the current grid
     * @param sceneTitle the title of the scene
     * @param primaryStage the current stage.
     */
    public static void printTheLoginAndPassword(TextField userTextField,
            GridPane grid, Text sceneTitle, Stage primaryStage)
    {

        String temp = ul.userExists(userTextField.getText());
        System.out.println(temp);
        if (!"null/null/null".equals(temp))
        {
            int firstSlash = temp.indexOf("/");
            int secondSlash = temp.indexOf("/", firstSlash + 1);

            String actualImage = temp.substring(0, firstSlash);
            String actualImageName = temp.substring(firstSlash + 1, secondSlash);
            String mod = temp.substring(secondSlash + 1);

            System.out.println(actualImage + " " + actualImageName + " " + mod);

            sceneTitle.setText("Logging in");
            sceneTitle.setVisible(true);
            String[] selectedImages = randomizePictures(actualImage);

            Button btn = new Button("Let's do image authentication");
            HBox hbBtn = new HBox(10);
            hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
            hbBtn.getChildren().add(btn);
            grid.add(hbBtn, 1, 4);

            btn.setOnAction(new EventHandler<ActionEvent>()
            {

                @Override
                public void handle(ActionEvent e)
                {
                    btn.setVisible(false);
                    authenticateImage(userTextField, grid, sceneTitle,
                            primaryStage, selectedImages, actualImage,
                            actualImageName, 0, mod);

                }

            });
        } else
        {
            Text actiontarget1 = new Text();
            actiontarget1.setFill(Color.FIREBRICK);
            grid.add(actiontarget1, 1, 6);
            actiontarget1.setText("User not found");
        }

    }

    /* Authenticates an image by checking user correctly identifies his image.
     * 
     * @param userTextField the user's name
     * @param grid1 the current grid.
     * @param scenetitle the title of the scene.
     * @param primaryStage the current stage.
     * @param selectedImages the randomized pictures that have been chosen to show the user.
     * @param actualImage the actual image associated with this account.
     * @param actualImagename the actual image customized name associated with this account.
     * @param attempt the total number of completed attempts.
     * @param mod all the modifications associated with this account    
     */
    public static void authenticateImage(TextField userTextField,
            GridPane grid, Text sceneTitle, Stage primaryStage,
            String[] selectedImages, String actualImage,
            String actualImageName, int attempt, String mod)
    {

        Label imgText = new Label("Is this your image?");
        grid.add(imgText, 1, 2);
        //randomly show one image.

        //add radio buttons
        ToggleGroup group1 = new ToggleGroup();
        RadioButton rb1 = new RadioButton("Yes");
        rb1.setToggleGroup(group1);
        rb1.setUserData("Yes");
        RadioButton rb2 = new RadioButton("No");
        rb2.setToggleGroup(group1);
        rb2.setUserData("No");
        rb2.setSelected(true);
        grid.add(rb1, 2, 2);
        grid.add(rb2, 3, 2);

        ImageView iv1 = new ImageView();
        iv1.setImage(new Image(selectedImages[attempt]));
        iv1.setFitWidth(100);
        iv1.setPreserveRatio(true);
        iv1.setSmooth(true);
        iv1.setCache(true);
        StackPane hbBtn = new StackPane();
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(iv1);
        grid.add(hbBtn, 1, 3);

        //grid.setGridLinesVisible(true);
        Text actiontarget2 = new Text();
        grid.add(actiontarget2, 1, 4);
        actiontarget2.setText("What is your image's name?");

        TextField givenImageName = new TextField();
        grid.add(givenImageName, 2, 4);

        Button btn = new Button("Sign in");
        HBox hbBtn2 = new HBox(10);
        hbBtn2.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn2.getChildren().add(btn);
        grid.add(hbBtn2, 1, 5);

        btn.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {

                String a1 = group1.getSelectedToggle().getUserData().toString();
                //System.out.println(a1);
                clearGrid(grid);
                Text scenetitle = new Text();
                scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
                //check if given picture and picture name matches.
                if (attempt <= 2 && a1.equals("Yes") && selectedImages[attempt].
                        equals(actualImage)
                        && givenImageName.getText().equals(actualImageName))
                {

                    scenetitle.setText("Logging in");
                    grid.add(scenetitle, 0, 0);
                    Text actiontarget1 = new Text();
                    grid.add(actiontarget1, 1, 3);
                    actiontarget1.setText("Go to last step");

                    a.goToModificationsPage(grid,
                            scenetitle, primaryStage, actualImage);

                    lastAccountCreationPage(grid, actualImage, mod,
                            userTextField, scenetitle, primaryStage);
                    //do last level of image modification authentication.
                    //imageModificationAuthentication();
                } else if (attempt < 2)
                //User has more attempts. If he did not authenticate it properly, give fake chances till 3. 
                //This to make sure n info about account is revealed.
                {
                    //show another image and test again.

                    scenetitle.setText("Try again");
                    grid.add(scenetitle, 0, 0);
                    authenticateImage(userTextField, grid, sceneTitle,
                            primaryStage, selectedImages, actualImage,
                            actualImageName, attempt + 1, mod);
                } else //all attempts have been exhausted.
                {

                    scenetitle.setText("Unsuccessful");
                    grid.add(scenetitle, 0, 0);
                    Text actiontarget1 = new Text();
                    actiontarget1.setFill(Color.FIREBRICK);
                    grid.add(actiontarget1, 1, 3);
                    actiontarget1.setText(
                            "Username and password do not match.");

                }
            }
        });
    }

    /* Does the final step of login - verify all the given values are correct.
     *
     * @param allModifications all the modifications done by a user.
     * @param grid1 the current grid.
     * @param actualImage the actual image associated with this account.
     * @param expectedMod the expected modifications on the picture.
     * @param userName the user's username
     * @param scenetitle the title of the scene.
     * @param primaryStage the current stage.
     *    
     */
    public static void lastAccountCreationPage(
            GridPane grid1, String actualImage, String expectedMod,
            TextField userName, Text scenetitle,
            Stage primaryStage)
    {

        Button btn = new Button("Submit");
        HBox hbBtn13 = new HBox(10);
        hbBtn13.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn13.getChildren().add(btn);
        grid1.add(hbBtn13, 1, 8);

        btn.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                String allModifications = a.getAllModifications();
                System.out.println("Got " + allModifications);
                System.out.println(allModifications);
                clearGrid(grid1);
                if (allModifications.equals(expectedMod))
                {
                    UserLogin ul = new UserLogin();
                    //System.out.println("Login successful");
                    scenetitle.setText("Welcome, " + ul.getUser(userName.
                            getText()));
                    scenetitle.setVisible(true);

                } else //all attempts have been exhausted.
                {
                    scenetitle.setText("Unsuccessful");
                    scenetitle.setVisible(true);
                }

            }
        });
    }

    /*Find three images to test the user.
     *randomly find two images.
     *allocate an order to show the images.
     * @param actualImage the actual image associated with this user's account.
     * @return a string containing the randomly chosen pictures in the order to show them.
     */
    public static String[] randomizePictures(String actualImage)
    {

        HashMap<Integer, String> pictures = new HashMap();
        pictures.put(0, "butterfly.jpg");
        pictures.put(1, "city.jpg");
        pictures.put(2, "desert.jpg");
        pictures.put(3, "flower.jpg");
        pictures.put(4, "flowers.jpg");
        pictures.put(5, "night.jpg");
        pictures.put(6, "sunset.jpg");
        pictures.put(7, "surf.jpg");
        pictures.put(8, "tiger.jpg");
        pictures.put(9, "water.jpg");

        Random rand = new Random();
        int val1 = rand.nextInt(9);
        int val2 = rand.nextInt(9);
        while (pictures.get(val1).equals(actualImage))
        {
            val1 = rand.nextInt(9);
        }

        while (pictures.get(val2).equals(actualImage) || pictures.get(val1).
                equals(val2))
        {
            val2 = rand.nextInt(9);
        }

        String img1 = pictures.get(val1);
        String img2 = pictures.get(val2);
        String img0 = actualImage;

        //System.out.println("Allocated images are: " + img0 + " " + img1 + " " + img2);
        //generate a random order and save values in that order.
        int pos0 = rand.nextInt(2);
        int pos1 = rand.nextInt(2);
        int pos2 = rand.nextInt(2);
        while (pos1 == pos0)
        {
            pos1 = rand.nextInt(2);
        }
        pos2 = 3 - pos1 - pos0;

        //System.out.println(pos0 + " " + pos1 + " " + pos2);
        String[] selectedImages = new String[3];
        selectedImages[pos0] = img0;
        selectedImages[pos1] = img1;
        selectedImages[pos2] = img2;
        return selectedImages;
    }

    /**
     * Clears all the items from the grid.
     *
     * @param grid1 the current grid
     */
    public static void clearGrid(GridPane grid1)
    {
        for (Node a : grid1.getChildren())
        {
            a.setVisible(false);
        }

    }
    


}
