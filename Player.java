import greenfoot.*;  

public class Player extends Actor{
    public static int size = 1;
    public static int speed = 1;
    public static int bullets = 1;
    public static int atkSpd = 1;
    int attackGauge;
    
    public Player(int size, int speed, int bullets, int atkSpd, int rotation){
        this.size = size;
        this.speed = speed;
        this.bullets = bullets;
        this.atkSpd = atkSpd;
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
        
        attackGauge++;
        if(attackGauge >= atkSpd){
            attack();
            attackGauge = 0;
        }

    }
    
    private void attack(){
        SpaceShooter world = (SpaceShooter) getWorld();
        Bullet smallBullet = new Bullet(1, 1, 3);
        for(int i = 0; i < bullets; i++){
            world.addObject(smallBullet, getX(), getY() - 40);
        }
        
    }
}
