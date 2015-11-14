package sample.Assets;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import sample.Assets.drawMan;
import sample.Controller;
import sample.calculation;

import static java.lang.Math.abs;

/**
 * Created by abedaigorou on 15/09/16.
 */
public class Block extends drawMan
{
    protected double up,down,left,right;
    private Color color;
    public final static int returnX=1,returnY=2,returnL=3;
    protected int HP;

    public Block(GraphicsContext gc,double x,double y,double width,double height,Color c,int HP)
    {
        super(gc, Controller.cWidth, Controller.cHeight,width,height,x,y);
        this.x=x;
        this.y=y;
        this.width=width;
        this.color=c;
        this.HP=HP;
        up=y;
        down=y+height;
        left=x;
        right=x+width;
        draw();
    }

    @Override
    public void draw()
    {
        do {
            if(isBreak)
                break;

            gc.setFill(color);
            gc.setGlobalAlpha(1-(double)(1.0/(HP+1.0)));
            gc.fillRect(x, y, width, height);
            gc.setStroke(Color.BLACK);
            gc.strokeRect(x, y, width, height);
        }while(false);
    }

    @Override
    /*int isTouched(double x, double y, double height,double width) {
        double distXL=calculation.distance(1,0,-(int)this.x,x,y);
        double distXR=calculation.distance(1,0,-(int)this.x-(int)this.width,x,y);
        double distYU=calculation.distance(0,1,-(int)this.y,x,y);
        double distYD=calculation.distance(0,1,-(int)this.y-(int)this.height,x,y);
        final double bRight=x+width,bLeft=x,bUp=y,bDown=y+height;
        double min;
        do {
            if(isBreak)
                break;

            if ((bRight > left && bLeft < right) && (bDown > up && bUp < down)) {
                if(!isLeft)
                    break;
                min= calculation.min(abs(bRight - left), abs(right - bLeft), abs(up - bDown), abs(bUp - down));
                isLeft = false;
                if(--HP<1)
                    destroy();
                if(min==abs(up-bDown)||min==abs(bUp-down))
                    return returnY;
                if(min==abs(bRight-left)||min==abs(right-bLeft))
                    return returnX;
            } else
                isLeft = true;
        }while(false);

        return 0;
    }*/

    int isTouched(double centerX, double centerY, double height,double width,boolean isLeft)
    {
        double interX=width/2,interY=height/2;
        double distL=calculation.distance(1,0,-(int)x,centerX,centerY);
        double distR=calculation.distance(1,0,-(int)x-(int)this.width,centerX,centerY);
        double distU=calculation.distance(0,1,-(int)y,centerX,centerY);
        double distD=calculation.distance(0,1,-(int)y-(int)this.height,centerX,centerY);
        double dist=calculation.min(distU,distD,distL,distR);
        do{
            if(dist>interX||isBreak)
                return Block.returnL;

            if(dist==distU||dist==distD) {
                if(x<centerX&&centerX<x+this.width&&isLeft) {
                    if(--HP<1)
                        destroy();
                    return Block.returnY;
                }
            }
            else if(dist==distL||dist==distR) {
                if(y<centerY&&centerY<y+this.height&&isLeft) {
                    if(--HP<1)
                        destroy();
                    return Block.returnX;
                }
            }

        }while(false);
        return 0;
    }
}
