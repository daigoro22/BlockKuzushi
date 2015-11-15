package sample.Assets;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import sample.Controller;
import sample.Assets.drawMan;
import sample.calculation;
import sample.game;

import java.util.ArrayList;

/**
 * Created by abedaigorou on 15/09/10.
 */
public class Ball extends drawMan
{
    private double vectorX,vectorY;
    private boolean isLeft=true,isRunning=true;
    private int j=0;

    public Ball(GraphicsContext gc,double x,double y,double vectorX,double vectorY,double width,double height)
    {
        super(gc, Controller.cWidth,Controller.cHeight,width,height,x,y);
        this.x=x;
        this.y=y;
        this.vectorX=vectorX;
        this.vectorY=vectorY;
        draw();
    }

    @Override
    public void draw()
    {
        gc.setGlobalAlpha(1);
        gc.setFill(Color.RED);
        gc.fillOval(x, y, width, height);
        //isCollision();
    }

    @Override
    /*public int isTouched(double x,double y,double width,double height)
    {
        double dist=calculation.distance(0,1,-(int)Controller.cHeight,x,y);
        System.out.println(dist);
        do {
            if ((x + width > Controller.cWidth || x < 0) || (y + height > Controller.cHeight || y < 0))
            {
                if (!isLeft)
                    break;
                isLeft=false;
                if (x + width > Controller.cWidth || x < 0)
                    return Block.returnX;
                if (y + height > Controller.cHeight || y < 0)
                    return Block.returnY;
            }
            else if((x + width < Controller.cWidth && x > 0)&&(y + height < Controller.cHeight && y > 0))
                isLeft=true;
        } while(false);
        return 0;
    }*/
    //これ強いっすね

    public int isTouched(double x,double y,double width,double height,boolean isLeft)
    {
        if(isBreak)
            return 0;
        double distX=calculation.distance(1,0,-(int)Controller.cWidth,x,y);
        double distY=calculation.distance(0,1,-(int)Controller.cHeight,x,y);
        do{
            //System.out.println(dist);
            if(width/2>distX||Controller.cWidth-(width/2)<distX)
                return Block.returnX;
            else if(height/2>distY||Controller.cHeight-(height/2)<distY)
                return Block.returnY;
            else
                return Block.returnL;
        }while(false);
    }

    public boolean isCollision()
    {
        if(isBreak)
            return false;
        boolean rv=false;
        int i=0;
        for (i=0;i<game.assets.size();i++) {
            switch (game.assets.get(i).isTouched(centerX,centerY, width, height,this.isLeft)) {
                case Block.returnX:
                    this.isLeft = false;
                    vectorX = -vectorX;
                    rv=true;
                    break;
                case Block.returnY:
                    this.isLeft = false;
                    vectorY = -vectorY;
                    rv=true;
                    break;
                case Block.returnL:
                    j++;
                    if(j==game.assets.size()) {
                        j=0;
                        this.isLeft = true;
                    }
                    rv=false;
                    break;
            }
        }
        switch (isTouched(centerX,centerY,width,height,this.isLeft)){
            case Block.returnX:
                this.isLeft = false;
                vectorX = -vectorX;
                rv=true;
                break;
            case Block.returnY:
                this.isLeft = false;
                this.destroy();
                rv=false;
                break;
        }
        j=0;
        return rv;
    }

    public void stop()
    {
        destroy();
    }

    public void move()
    {
        x+=vectorX;
        y+=vectorY;
        centerX+=vectorX;
        centerY+=vectorY;
    }

}
