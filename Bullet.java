import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Bullet extends Actor{
    public int damage = 1;
    public int size = 1;
    public int speed = 1;
    public int health = 1;
    
    public Bullet(int damage, int size, int speed, int health){
        this.damage = damage;
        this.size = size;
        this.speed = speed;
        this.health = health;
    }
    
    public void act(){
        SpaceShooter world = (SpaceShooter) getWorld();
        if(health <= 0){
            world.removeObject(this);
        }
        else{
            setLocation(getX(), getY() - speed);
        
            if(isTouching(Basic.class) || isTouching(Stage1.class) || isTouching(Stage2.class) || isTouching(MiniBoss.class)){
                health--;            
            }            
            if(isAtEdge()){
                health = 0;
            }        
        }
    }
}
