package sample.Assets;

import javafx.scene.canvas.GraphicsContext;

/**
 * Created by abedaigorou on 15/09/11.
 */
public abstract class drawMan
{
    protected GraphicsContext gc;
    protected double cWidth,cHeight;
    protected double x,y,width,height,centerX,centerY;
    protected boolean isBreak=false;

    public drawMan(GraphicsContext gc,double canvasWidth,double canvasHeight,double width,double height,double x,double y)
    {
        this.gc=gc;
        this.cWidth=canvasWidth;
        this.cHeight=canvasHeight;
        this.width=width;
        this.height=height;
        centerX=x+(width/2);
        centerY=y+(height/2);
    }

    public abstract void draw();
    abstract int isTouched(double x,double y,double width,double height,boolean isLeft);
    protected void destroy()
    {
        gc.clearRect(x,y,width,height);
        isBreak=true;
    }
}
