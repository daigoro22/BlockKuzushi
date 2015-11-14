package sample;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import sample.Assets.*;

import java.util.ArrayList;

/**
 * Created by abedaigorou on 15/09/10.
 */
public class game extends AnimationTimer implements Runnable {
    public static ArrayList<drawMan> assets=new ArrayList<drawMan>();
    public static ArrayList<Ball> balls=new ArrayList<Ball>();
    private GraphicsContext gc;
    public Paddle paddle;
    private Ball ball;
    public static int ballNum=1;
    private static drawMan add;
    public static Thread thread;
    private double px=0;
    private boolean isRunning=true;

    public game(GraphicsContext gc)
    {
        this.gc=gc;
        paddle=new Paddle(gc,Controller.cWidth/2-15,Controller.cHeight-50,30,10,Color.BEIGE);
        ball=new Ball(gc, Controller.cWidth / 2, Controller.cHeight / 2, 1,1, 10,10);
        balls.add(ball);
        assets.add(paddle);
        thread=new Thread(this);
        //assets.add(new Block(gc,0,0,25,25,Color.ALICEBLUE,1));
        for(int i=0;i<12;i++)
            assets.add(new Block(gc,i*25,0,25,25,Color.RED,3));
        for(int i=0;i<12;i++)
            assets.add(new Block(gc,i*25,25,25,25,Color.BLUE,2));
        for(int i=0;i<12;i++)
            assets.add(new Block(gc,i*25,50,25,25,Color.GREEN,1));
        for(int i=0;i<12;i++)
            assets.add(new spreadBlock(gc,i*25,75,25,25,Color.SILVER,1));
    }

    @Override
    public void handle(long now) {
        clearCanvas();
        drawCanvas();
        paddle.move(px);
    }

    public static void assetsSetter(Ball b)
    {
        add=b;
        ballNum++;
    }

    public static void assetsSetter(Block b)
    {
        add=b;
    }

    private static void assetsAdder()
    {
        if(add!=null) {
            if(add.getClass()==Block.class)
                assets.add(add);
            else if(add.getClass()==Ball.class)
                balls.add((Ball)add);
            add = null;
        }
    }

    private void clearCanvas()
    {
        gc.clearRect(0,0,Controller.cWidth,Controller.cHeight);
    }

    private void drawCanvas()
    {
        for(drawMan a:assets){
            a.draw();
        }
        for(drawMan b:balls){
            b.draw();
        }
    }

    public void stop()
    {
        isRunning=false;
    }

    @Override
    public void run() {
        while(isRunning) {
            assetsAdder();
            paddle.move(px);
            for(int i=0;i<balls.size();i++) {
                balls.get(i).move();
                balls.get(i).isCollision();
            }
            try {
                Thread.sleep(5);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setPoint(double x)
    {
        px=x;
    }

}
