package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private Controller c;
    private FXMLLoader loader=new FXMLLoader(getClass().getResource("kuzushi.fxml"));
    private Parent root;
    @Override
    public void start(Stage primaryStage) throws Exception{
        root=(Parent)loader.load();
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root,Controller.cWidth,Controller.cHeight));
        primaryStage.show();
        c=loader.getController();
    }


    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void stop()
    {
        c.stop();
    }
}
