import greenfoot.*;

public class Background extends SmoothMover{

    public void act(){
        setLocation(getExactX(), getExactY() + 0.5);
        if(getY() > 1190){
            setLocation(getX(), -390);
        }
    }
}
