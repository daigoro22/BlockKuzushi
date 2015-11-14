package sample;

import com.sun.tools.corba.se.idl.ExceptionEntry;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller  implements Initializable
{
    @FXML
    Canvas canvas;
    @FXML
    Button button;
    @FXML
    public void Click()
    {
        game.start();
        game.thread.start();
    }

    @FXML
    public void mouse(MouseEvent e)
    {
        game.setPoint(e.getX());
    }

    GraphicsContext gc;
    public static double cWidth=300,cHeight=300;
    private game game;

    public void stop()
    {
        game.stop();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        gc=canvas.getGraphicsContext2D();
        cWidth=canvas.getWidth();
        cHeight=canvas.getHeight();
        game=new game(gc);
    }

}

