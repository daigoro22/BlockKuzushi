package sample.Assets;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import sample.game;

/**
 * Created by abedaigorou on 15/09/29.
 */
public class spreadBlock extends Block {
    private GraphicsContext gc;
    public spreadBlock(GraphicsContext gc, double x, double y, double width, double height, Color c, int HP) {
        super(gc, x, y, width, height, c, HP);
        this.gc=gc;
    }

    @Override
    public int isTouched(double x, double y, double height,double width,boolean isLeft)
    {
        //game.assetsSetter(new Ball(gc,x,y,1,1,5,5));
        int t=super.isTouched(x,y,height,width,isLeft);
        if(t==Block.returnX||t==Block.returnY)
            game.assetsSetter(new Ball(gc,x,y,0.5,1,10,10));
        return t;
    }
}
