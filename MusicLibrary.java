import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.control.TextField;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.geometry.Insets;
import javafx.scene.text.Font;

import java.util.ArrayList;

/**
 * This file creates the MusicKibrary Application.
 * MusicLibrary extends Application and uses JavaFX.
 * @author Pritesh Rajyaguru
 * @version 1.0
 */
public class MusicLibrary extends Application {
    protected String sText;
    protected String aText;
    protected String gText;

    protected StackPane getPane() {
        ImageView musicImage = new ImageView("musicImage.jpg");
        musicImage.setPreserveRatio(false);

        ArrayList<String> songsList = new ArrayList<>();
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        Button button = new Button("Add Song");
        button.setPrefSize(75, 50);

        VBox song = new VBox();
        Text songName = new Text("Song Name:");
        songName.setFill(Color.WHITE);
        TextField songText = new TextField("Song Name:");
        song.getChildren().addAll(songName, songText);

        VBox album = new VBox();
        Text albumName = new Text("Album Name:");
        albumName.setFill(Color.WHITE);
        TextField albumText = new TextField("Album Name:");
        album.getChildren().addAll(albumName, albumText);

        VBox genre = new VBox();
        Text genreName = new Text("Select Song Genre:");
        genreName.setFill(Color.WHITE);
        ComboBox<Genres> genreText = new ComboBox<Genres>();
        genreText.getItems().addAll(Genres.POP, Genres.ROCK,
                Genres.HIP_HOP, Genres.R_AND_B, Genres.CLASSICAL, Genres.RAP,
                Genres.COUNTRY, Genres.ALTERNATIVE);
        genre.getChildren().addAll(genreName, genreText);

        hbox.getChildren().addAll(song, album, genre, button);
        hbox.setMargin(song, new Insets(0, 7, 0, 0));
        hbox.setMargin(album, new Insets(0, 7, 0, 7));
        hbox.setMargin(genre, new Insets(0, 7, 0, 7));
        hbox.setMargin(button, new Insets(0, 0, 0, 7));

        GridPane tile = new GridPane();
        tile.setAlignment(Pos.CENTER);
        tile.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        tile.setHgap(10);
        tile.setVgap(10);
        tile.setGridLinesVisible(true);
        Button[][] keep = new Button[3][2];
        for (int i = 0; i <= 1; ++i) {
            for (int j = 0; j <= 2; ++j) {
                int k = j;
                int m = i;
                keep[j][i] = new Button("Empty");
                keep[j][i].setPrefSize(75, 75);
                keep[j][i].setStyle("-fx-background-color: white;");
                tile.add(keep[j][i], j, i);

                keep[j][i].setOnMouseClicked(
                    new EventHandler<MouseEvent>() {
                        public void handle(MouseEvent e) {
                            keep[k][m].setText("Empty");
                            keep[k][m].setStyle("-fx-background-color: white;");
                        }
                    });
            }
        }

        button.setOnAction(e -> {
            int count = 0;
            if (songText == null || songText.getText().isEmpty()) {
                sText = "Untitled";
            } else {
                sText = songText.getText();
            }
            if (albumText == null || albumText.getText().isEmpty()) {
                aText = "I don't like to follow directions :(";
            } else {
                aText = albumText.getText();
            }
            if (genreText.getValue() == null) {
                gText = Genres.POP.name();
            } else {
                gText = genreText.getValue().name();
            }
            if (keep[0][0].getText().equals("Empty")) {
                keep[0][0].setText((String.format("%s,\n", sText)
                    + String.format("%s,\n", aText) + String.format("%s\n", gText)));
                keep[0][0].setStyle("-fx-background-color: grey;");
            } else if (keep[1][0].getText().equals("Empty")) {
                keep[1][0].setText(String.format("%s,\n", sText)
                    + String.format("%s,\n", aText) + String.format("%s,\n", gText));
                keep[1][0].setStyle("-fx-background-color: grey;");
            } else if (keep[2][0].getText().equals("Empty")) {
                keep[2][0].setText(String.format("%s,\n", sText)
                    + String.format("%s,\n", aText) + String.format("%s,\n", gText));
                keep[2][0].setStyle("-fx-background-color: grey;");
            } else if (keep[0][1].getText().equals("Empty")) {
                keep[0][1].setText(String.format("%s,\n", sText)
                    + String.format("%s,\n", aText) + String.format("%s,\n", gText));
                keep[0][1].setStyle("-fx-background-color: grey;");
            } else if (keep[1][1].getText().equals("Empty")) {
                keep[1][1].setText(String.format("%s,\n", sText)
                    + String.format("%s,\n", aText) + String.format("%s,\n", gText));
                keep[1][1].setStyle("-fx-background-color: grey;");
            } else if (keep[2][1].getText().equals("Empty")) {
                keep[2][1].setText(String.format("%s,\n", sText)
                    + String.format("%s,\n", aText) + String.format("%s,\n", gText));
                keep[2][1].setStyle("-fx-background-color: grey;");
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Your music library is full!");
                alert.show();
            }

            songText.clear();
            albumText.clear();
        });

        Text title = new Text("My Music Library");
        title.setFont(new Font(30));
        title.setFill(Color.WHITE);
        title.setUnderline(true);

        BorderPane bP = new BorderPane();
        bP.setTop(title);
        bP.setAlignment(title, Pos.CENTER);
        bP.setMargin(title, new Insets(120, 0, 0, 0));
        bP.setBottom(hbox);
        bP.setMargin(hbox, new Insets(0, 0, 50, 0));
        bP.setCenter(tile);
        StackPane sP = new StackPane();
        sP.getChildren().addAll(musicImage, bP);
        return sP;
    }

    /**
     * This is the start method that is Overrided from Application.
     * @param primaryStage This is the stage that will be used to display
     *                     MusicLibrary
     */
    public void start(Stage primaryStage) {
        Scene scene = new Scene(getPane(), 800, 500);
        primaryStage.setTitle("My Music Library");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * This is the main method that launches the MusicLibrary.
     * @param args this is the argument for the main method
     */
    public static void main(String[] args) {
        launch(args);
    }
}
