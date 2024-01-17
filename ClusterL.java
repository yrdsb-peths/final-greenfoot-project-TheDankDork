import greenfoot.*;

public class ClusterL extends Bullet{
    public ClusterL(int damage, int size, int speed, int health){
        super(damage, size, speed, health);
    }
    
    public void act(){
        SpaceShooter world = (SpaceShooter) getWorld();
        if(health <= 0){
            world.removeObject(this);
        }
        else{
            setLocation(getX() - speed, getY());
        
            if(isTouching(Basic.class) || isTouching(Stage1.class) || isTouching(Stage2.class) || isTouching(MiniBoss.class)){
                health--;            
            }            
            if(isAtEdge()){
                health = 0;
            }        
        }
    }
}
