import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;

public class App extends Application {

    private MediaPlayer mediaPlayer;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane root = new Pane();
        loadUI(root);
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.show();
    }

    private void loadUI(Pane root) {
        String path = "C:\\Users\\Andrey\\Desktop\\Kadebostany-Castle_in_the_snow_(radio_edit).mp3";
        mediaPlayer = new MediaPlayer(new Media(new File(path).toURI().toString()));
        mediaPlayer.setVolume(0.3);

        Slider slider = new Slider(0,100, 0);
        slider.setTranslateX(100);
        slider.setTranslateY(10);
        slider.setPrefSize(300, 20);
        slider.setOnMouseReleased(event -> {
            long mediaDurationMs = (long) mediaPlayer.getMedia().getDuration().toMillis();
            long nextDuration = (long) (mediaDurationMs * (slider.getValue()/100));
            mediaPlayer.seek(Duration.millis(nextDuration));
        });

        Button button = new Button("Play");
        button.setTranslateY(10);
        button.setTranslateX(10);
        button.setOnAction(event -> mediaPlayer.play());


        Button button1 = new Button("Pause");
        button1.setTranslateX(10);
        button1.setTranslateY(60);
        button1.setOnAction(event -> mediaPlayer.pause());

        root.getChildren().addAll(button, button1, slider);
    }

}
