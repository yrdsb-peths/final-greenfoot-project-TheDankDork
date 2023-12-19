import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Bullet extends Actor{
    public static int damage = 1;
    public static int size = 1;
    public static int speed = 1;
    
    public Bullet(int damage, int size, int speed){
        this.damage = damage;
        this.size = size;
        this.speed = speed;
    }
    
    public void act(){
        SpaceShooter world = (SpaceShooter) getWorld();
        setLocation(getX(), getY() - speed);
        
        hitTarget();
        
        if(isAtEdge()){
            world.removeObject(this);
        } else if (isTouching(Meteor.class)){
            world.removeObject(this);
        }
    }
    
    private void hitTarget(){
        SpaceShooter world = (SpaceShooter) getWorld();

        if(isTouching(Meteor.class)){
            Meteor.health--;
            if(Meteor.health <= 0){
                removeTouching(Meteor.class);
            }
            
        }

    }
}
