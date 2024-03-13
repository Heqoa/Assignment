package GalleryDisplay4;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GalleryDisplay4 extends Application {

    private final String[] imagePaths = {"lesotho.png", "USA.jpg","brazil.jpg", "Portugal.jpg"};
    private ImageView fullImageView;
    private int currentIndex = 0;

    @Override
    public void start(Stage primaryStage) {
        GridPane gridPane = new GridPane();
        gridPane.getStyleClass().add("grid-pane");
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        for (int i = 0; i < imagePaths.length; i++) {
            Image thumbnail = new Image(getClass().getResourceAsStream(imagePaths[i]));
            ImageView thumbnailView = new ImageView(thumbnail);
            thumbnailView.getStyleClass().add("thumbnail");
            thumbnailView.setFitWidth(150);
            thumbnailView.setFitHeight(100);
            int index = i;
            thumbnailView.setOnMouseClicked(event -> showFullImage(index));
            gridPane.add(thumbnailView, i % 3, i / 3);
        }

        fullImageView = new ImageView();
        fullImageView.getStyleClass().add("fullImageView");
        fullImageView.setPreserveRatio(true);
        fullImageView.setFitWidth(300);
        fullImageView.setFitHeight(200);

        Button returnButton = new Button("Back to Thumbnails");
        returnButton.setOnAction(event -> showThumbnailView(gridPane));

        VBox root = new VBox(gridPane, fullImageView, returnButton); 

        Scene scene = new Scene(root, 800, 600);
        

        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        primaryStage.setTitle("GalleryApplication");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    

    private void showFullImage(int index) {
        currentIndex = index;
        Image fullImage = new Image(getClass().getResourceAsStream(imagePaths[index]));
        fullImageView.setImage(fullImage);
    }

    private void showThumbnailView(GridPane gridPane) {
        fullImageView.setImage(null);
        Scene scene = gridPane.getScene();
        scene.setRoot(gridPane);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
