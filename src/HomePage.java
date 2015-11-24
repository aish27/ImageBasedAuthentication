
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Class representing the home page through which the user can navigate the
 * interface. Base Code obtained from Oracle's JAVAFX documentation.
 *
 * @author Aishwarya
 */
public class HomePage extends Application
{

    LoginPage loginPage = new LoginPage();
    AccountCreationPage accPage = new AccountCreationPage();
    /*Represents the homepage from which the user can choose any options.
     * @param primaryStage represents the stage.
     */
    @Override
    public void start(Stage primaryStage)
    {
        //declares the primary stage.
        primaryStage.setResizable(true);
        primaryStage.setTitle("Welcome");
        //declares the grid.
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setPadding(new Insets(10, 10, 10, 10));
        //adds required fields and buttons.
        Text scenetitle = new Text("Welcome");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 2, 0, 2, 1);
        Label userName = new Label("What do you want to do?");
        grid.add(userName, 2, 2);

        Button btn1 = new Button("Sign in");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn1);
        grid.add(hbBtn, 2, 4);
        //Handler for the button. Goes to login page.
        btn1.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                loginPage.start(primaryStage);
            }
        });

        Button btn2 = new Button("Create Account");
        HBox hbBtn2 = new HBox(10);
        hbBtn2.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn2.getChildren().add(btn2);
        grid.add(hbBtn2, 3, 4);
        //Handler for the button. Goes to account creation page.
        btn2.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                accPage.start(primaryStage);
            }
        });

        Scene scene = new Scene(grid, 1000, 500);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    /**
     * Shows the initial UI.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }

}
