import java.util.HashMap;
import javafx.application.Application;
import javafx.collections.FXCollections;
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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;

/**
 * Class representing an account creation page. Code obtained from Oracle's
 * JAVAFX documentation.
 *
 * @author Aishwarya
 */
public class AccountCreationPage extends Application 
{

    static ImageModifier a = new ImageModifier();
    static UserLogin ul = new UserLogin();
    
    
    
    /*Starts an Account Creation Page and shows inital UI.
     *@param primaryStage represents a stage.
     */

    @Override
    public void start(Stage primaryStage)
    {
        primaryStage.setResizable(true);
        primaryStage.setTitle("Create an account");

        GridPane grid1 = new GridPane();
        grid1.setAlignment(Pos.CENTER);
        grid1.setHgap(10);
        grid1.setVgap(10);
        grid1.setPadding(new Insets(1, 1, 1, 1));

        Text scenetitle = new Text("Create a new account");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));

        Label userName1 = new Label("Full Name");
        userName1.autosize();

        TextField userNameText = new TextField();
        userNameText.autosize();

        Label pic = new Label(
                "Please choose a picture [Pictures are named A-J from left to right]:");
        pic.autosize();

        Image img = new Image("butterfly.jpg");
        StackPane hbBtn = setValues(img);
        grid1.add(hbBtn, 1, 3);

        Image img1 = new Image("city.jpg");
        StackPane hbBtn1 = setValues(img1);
        grid1.add(hbBtn1, 2, 3);

        Image img2 = new Image("desert.jpg");
        StackPane hbBtn2 = setValues(img2);
        grid1.add(hbBtn2, 3, 3);

        Image img3 = new Image("flower.jpg");
        StackPane hbBtn3 = setValues(img3);
        grid1.add(hbBtn3, 4, 3);

        Image img4 = new Image("flowers.jpg");
        StackPane hbBtn4 = setValues(img4);
        grid1.add(hbBtn4, 5, 3);

        Image img5 = new Image("night.jpg");
        StackPane hbBtn5 = setValues(img5);
        grid1.add(hbBtn5, 1, 4);

        Image img6 = new Image("sunset.jpg");
        StackPane hbBtn6 = setValues(img6);
        grid1.add(hbBtn6, 2, 4);

        Image img7 = new Image("surf.jpg");
        StackPane hbBtn7 = setValues(img7);
        grid1.add(hbBtn7, 3, 4);

        Image img8 = new Image("tiger.jpg");
        StackPane hbBtn8 = setValues(img8);
        grid1.add(hbBtn8, 4, 4);

        Image img9 = new Image("water.jpg");
        StackPane hbBtn9 = setValues(img9);
        grid1.add(hbBtn9, 5, 4);

        ChoiceBox picText = new ChoiceBox(FXCollections.observableArrayList(
                "A", "B", "C", "D", "E", "F", "G", "H", "I", "J"));

        HashMap<String, String> pictures = new HashMap();
        pictures.put("A", "butterfly.jpg");
        pictures.put("B", "city.jpg");
        pictures.put("C", "desert.jpg");
        pictures.put("D", "flower.jpg");
        pictures.put("E", "flowers.jpg");
        pictures.put("F", "night.jpg");
        pictures.put("G", "sunset.jpg");
        pictures.put("H", "surf.jpg");
        pictures.put("I", "tiger.jpg");
        pictures.put("J", "water.jpg");

        Label picName = new Label("Your caption for the picture");
        picName.autosize();
        TextField picNameText = new TextField();
        picNameText.autosize();

        grid1.add(scenetitle, 0, 0, 2, 1);
        grid1.add(userName1, 0, 1);
        grid1.add(userNameText, 1, 1);
        grid1.add(pic, 0, 2);
        grid1.add(picText, 1, 2);
        grid1.add(picName, 0, 5);
        grid1.add(picNameText, 1, 5);

        //create a button to customize the account.
        Button btn14 = new Button("Create Account");
        HBox hbBtn14 = new HBox(10);
        hbBtn14.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn14.getChildren().add(btn14);
        grid1.add(hbBtn14, 1, 7);

        btn14.setOnAction(new EventHandler<ActionEvent>()
        {

            @Override
            public void handle(ActionEvent e)
            {
                //System.out.println(picText.getValue() + " " + userNameText.
                //getText() + " " + picNameText.getText());
                if (picText.getValue() == null || userNameText.getText() == null
                        || picNameText.getText().equals(""))
                {
                    AccountCreationPage acc = new AccountCreationPage();
                    acc.start(primaryStage);
                } else
                {
                    String actualImage = pictures.get((String) picText.
                            getValue());
                    //System.out.println(actualImage + "" + pictures.get(
                    //(String) picText.getValue()));

                    a.goToModificationsPage(grid1,
                            scenetitle, primaryStage, actualImage);
                    
                    lastAccountCreationPage( grid1, actualImage, userNameText,
                            picNameText, scenetitle, primaryStage);
                }

            }
        }
        );

        //grid1.setGridLinesVisible(true);
        Scene scene = new Scene(grid1, 1000, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /*After checking with the database regarding the pre-existence of the values, 
     *shows the next information.
     *
     * @param userName the username of the user
     * @param picText the picture's name
     * @param picNameText the picture's customized name given by the user.
     * @param grid the current grid.
     * @param scenetitle the title of the scene.
     * @param primaryStage the current stage.
     * @param allModifications all the modifications done by a user.
     */
    public static void createLoginAndPassword(TextField userName,
            String picText, TextField picNameText,
            GridPane grid, Text sceneTitle, Stage primaryStage,
            String allModifications)
    {

        System.out.println("Got " + allModifications);
        //add the entry to the database.
        int id = ul.addUser(userName.getText(), picText, picNameText.
                getText(), allModifications);

        System.out.println("Id I finally got is: " + id);
        //if return is -2. print user name is taken and repeat the process.
        if (id == -2)
        {
            sceneTitle.setText("Account creation unsuccessful");
            sceneTitle.setVisible(true);
            Label imgText = new Label(
                    " Username is already taken (Please provide a new username) ");
            grid.add(imgText, 1, 1);

            Button btn = new Button("Create Account>");
            HBox hbBtn = new HBox(10);
            hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
            hbBtn.getChildren().add(btn);
            grid.add(hbBtn, 1, 7);

            btn.setOnAction(new EventHandler<ActionEvent>()
            {
                @Override
                public void handle(ActionEvent e)
                {
                    new AccountCreationPage().start(primaryStage);
                }
            });
        } else
        {
            sceneTitle.setText("Account created successfully");
            sceneTitle.setVisible(true);
            Label imgText = new Label(" Your id is: " + id);
            grid.add(imgText, 1, 1);

            Button btn = new Button("Login");
            HBox hbBtn13 = new HBox(10);
            hbBtn13.setAlignment(Pos.BOTTOM_RIGHT);
            hbBtn13.getChildren().add(btn);
            grid.add(hbBtn13, 1, 6);

            btn.setOnAction(new EventHandler<ActionEvent>()
            {
                @Override
                public void handle(ActionEvent e)
                {
                    clearGrid(grid);
                    LoginPage a = new LoginPage();
                    a.start(primaryStage);
                    //System.out.println(pictures.get(val));
                }
            });
        }
    }

    /* Does the final step of login - verify all the given values are correct
     * and adds it to the database.
     *
     * @param allModifications all the modifications done by a user.
     * @param grid1 the current grid.
     * @param actualImage the actual image associated with the account.
     * @param userNameText the actual username associated with the account.
     * @param picNameText the picture's name associated with the account.
     * @param scenetitle the title of the scene.
     * @param primaryStage the current stage.
     */
    public static void lastAccountCreationPage(
            GridPane grid1, String actualImage, TextField userNameText,
            TextField picNameText, Text scenetitle,
            Stage primaryStage)
    {
        
        //System.out.println(allModifications);
        Button btn = new Button("Create Account");
        HBox hbBtn13 = new HBox(10);
        hbBtn13.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn13.getChildren().add(btn);
        grid1.add(hbBtn13, 1, 7);

        btn.setOnAction(new EventHandler<ActionEvent>()
        {

            @Override
            public void handle(ActionEvent e)
            {
                String allModifications = a.getAllModifications();
                clearGrid(grid1);
                String val = actualImage;
                //System.out.println(val);
                createLoginAndPassword(userNameText, actualImage,
                        picNameText, grid1,
                        scenetitle, primaryStage, allModifications);
                //System.out.println(pictures.get(val));

            }
        });
    }

    /**
     * Clears a grid completely.
     *
     * @param grid1 the grid.
     */
    public static void clearGrid(GridPane grid1)
    {
        for (Node a : grid1.getChildren())
        {
            a.setVisible(false);
        }

    }

    /**
     * Sets some attributes of an image so that it is presented appropriately.
     *
     * @param img an image
     * @return a Stack pane containing this image.
     */
   
    public StackPane setValues(Image img)
    { 
        ImageView iv = new ImageView();
        iv.setImage(img);
        iv.setFitWidth(100);
        iv.setPreserveRatio(true);
        iv.setSmooth(true);
        iv.setCache(true);
        StackPane hbBtn = new StackPane();
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(iv);
        return hbBtn;
    }

}
