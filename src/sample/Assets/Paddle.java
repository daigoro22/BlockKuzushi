package sample.Assets;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import sample.Assets.Block;
import sample.calculation;

import static java.lang.Math.abs;

/**
 * Created by abedaigorou on 15/09/17.
 */
public class Paddle extends Block {
    public Paddle(GraphicsContext gc, double x, double y, double width,double height, Color c) {
        super(gc, x, y, width,height,c,100);
    }

    @Override
    int isTouched(double centerX, double centerY, double height,double width,boolean isLeft) {
        int r=super.isTouched(centerX,centerY,height,width,isLeft);
        return (r==Block.returnX)?(Block.returnY):(r);
    }

    public void move(double x)
    {
        //System.out.println(left+":"+right+":"+up+":"+down);
        super.x=x;
        left=super.x;
        right=super.x+width;
    }
}
