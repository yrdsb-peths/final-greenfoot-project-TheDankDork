import greenfoot.*;  

public class Player extends Actor{
    public static int size = 1;
    public static int speed = 1;
    public static int bullets = 1;
    
    public Player(int size, int speed, int bullets, int rotation){
        this.size = size;
        this.speed = speed;
        this.bullets = bullets;
        turn(rotation);
    }
    
    public void act(){
        if(Greenfoot.isKeyDown("a")){
            setLocation(getX() - speed, getY());
        }
        if(Greenfoot.isKeyDown("d")){
            setLocation(getX() + speed, getY());
        }
        if(Greenfoot.isKeyDown("w")){
            move(speed);
        }
        if(Greenfoot.isKeyDown("s")){
            move(-speed);
        }
    }
}
