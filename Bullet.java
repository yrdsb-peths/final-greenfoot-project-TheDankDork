import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Bullet extends Actor{

    public void act(){
        SpaceShooter world = (SpaceShooter) getWorld();
        setLocation(getX(), getY() - 5);
        if(isOutOfBounds()){
            world.removeObject(this);
        }
    }
    
    private boolean isOutOfBounds(){
        if(getY() == 0){ // Check the position of the bullet
            return true;
        }
        return false;
    }
}
