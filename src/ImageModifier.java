
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Image modifier class has methods to modify an image.
 *
 * @author Aishwarya
 */
public class ImageModifier
{

    private static String allModifications ="";
    /*
     * Depending on the user's choices, actually does modification on the image
     * and displays it to the user.
     * @param picText the name of the picture.
     * @param grid  the grid to be modified.
     * @param sceneTitle the title of the scene.
     * @param primaryStage the primary stage of the scene.
     * @param darker boolean value denoting whether the image has to be darker.
     * @param brighter boolean value denoting whether the image has to be brighter.
     * @param saturate boolean value denoting whether the image has to be saturated.
     * @param desaturate boolean value denoting whether the image has to be desaturated.
     * @param grayscale boolean value denoting whether the image has to be made grayscale.
     */

    public static void modifyImage(String picText,
            GridPane grid, Text sceneTitle, Stage primaryStage, Boolean darker,
            Boolean brighter, Boolean saturate, Boolean desaturate,
            Boolean grayscale)
    {
        sceneTitle.setText("Modified image");
        sceneTitle.setVisible(true);
        System.out.println(picText);
        Image image = new Image(picText);

        //object to read pixels
        PixelReader pixelReader = image.getPixelReader();
        //make an object we can write to.
        WritableImage wImage = new WritableImage(
                (int) image.getWidth(),
                (int) image.getHeight());

        //object to write pixels
        PixelWriter pixelWriter = wImage.getPixelWriter();
        for (int readY = 0; readY < image.getHeight(); readY++)
        {
            for (int readX = 0; readX < image.getWidth(); readX++)
            {
                Color color = pixelReader.getColor(readX, readY);
                // depedning on the modifications, perform the modifications
                if (darker)
                {
                    color = color.darker();
                }
                if (brighter)
                {
                    color = color.brighter();
                }
                if (grayscale)
                {
                    color = color.grayscale();
                }
                if (saturate)
                {
                    color = color.saturate();
                }
                if (desaturate)
                {
                    color = color.desaturate();
                }

                pixelWriter.setColor(readX, readY, color);

            }
        }

        ImageView v = new ImageView(wImage);
        grid.add(v, 3, 1, 1, 2);
        v.setFitWidth(200);
        v.setPreserveRatio(true);
        v.setSmooth(true);
        v.setCache(true);

        allModifications = intVal(darker) + "" + intVal(brighter) + ""
                + intVal(saturate) + "" + intVal(desaturate) + "" + intVal(
                        grayscale);

        System.out.println("Modified all mod to: " + allModifications);
    }

    /*
     * Takes the user to the modifications page where he can do modifications on the image.
     * @param grid1 the grid
     * @param scenetitle the title of the scene
     * @param primaryStage the primary stage.
     * @param actualImage the actual image of that user.
     * @param userNameText the actual user name of that user.
     * @param picNameText the actual picture of that user.
     * @param job the page/task to which this method is applied.
     */
    public static void goToModificationsPage(GridPane grid1,
            Text scenetitle, Stage primaryStage, String actualImage)
    {
        //clear page.
        for (Node a : grid1.getChildren())
        {
            a.setVisible(false);
        }

        //customize the image.
        Image img8 = new Image(actualImage);
        ImageView iv8 = new ImageView();
        iv8.setImage(img8);
        iv8.setFitWidth(200);
        iv8.setPreserveRatio(true);
        iv8.setSmooth(true);
        iv8.setCache(true);
        StackPane hbBtn8 = new StackPane();
        hbBtn8.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn8.getChildren().add(iv8);
        grid1.add(hbBtn8, 1, 1, 1, 2);

        scenetitle.setText("Last step of account creation");
        scenetitle.setVisible(true);

        //check boxes to modify image.
        CheckBox cb1 = new CheckBox("Darker");
        CheckBox cb2 = new CheckBox("Brighter");
        CheckBox cb3 = new CheckBox("Saturate");
        CheckBox cb4 = new CheckBox("Desaturate");
        CheckBox cb5 = new CheckBox("Grayscale");

        grid1.add(cb1, 0, 1);
        grid1.add(cb2, 0, 2);
        grid1.add(cb3, 0, 3);
        grid1.add(cb4, 0, 4);
        grid1.add(cb5, 0, 5);
        //grid1.setGridLinesVisible(true);

        Button btn = new Button("Customize image");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid1.add(hbBtn, 1, 6);

        //when sutomize image is pressed, grab the values of customizations.
        btn.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                System.out.println(cb1.selectedProperty().getValue());

                modifyImage(actualImage,
                        grid1,
                        scenetitle, primaryStage,
                        cb1.selectedProperty().getValue(),
                        cb2.selectedProperty().getValue(),
                        cb3.selectedProperty().getValue(),
                        cb4.selectedProperty().getValue(),
                        cb5.selectedProperty().getValue()
                );
            }
        });
    }

    /*
     * Converts an boolean value to an integer value.
     * @param value is a boolean value.
     * @return an int value denoting the integer value of the boolean.
     */
    private static int intVal(Boolean value)
    {
        if (value)
        {
            return 1;
        } else
        {
            return 0;
        }
    }

    /**Returns all the modifications done on the current image.
     * @return the allModifications
     */
    public static String getAllModifications()
    {
        return allModifications;
    }

}
