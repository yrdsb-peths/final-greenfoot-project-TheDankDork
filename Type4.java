import greenfoot.*;

public class Type4 extends Bullet{
    
    Type0 cluster1 = new Type0(1, 1, 5, 1);
    ClusterL cluster2 = new ClusterL(1, 1, 5, 1);
    ClusterR cluster3 = new ClusterR(1, 1, 5, 1);
    ClusterLF cluster4 = new ClusterLF(1, 1, 5, 1);
    ClusterRF cluster5 = new ClusterRF(1, 1, 5, 1);
    
    public Type4(int damage, int size, int speed, int health){
        super(damage, size, speed, health);
    }
    
    public void act(){
        SpaceShooter world = (SpaceShooter) getWorld();
        if(health <= 0){
            world.addObject(cluster1, getX(), getY() - 45);
            world.addObject(cluster2, getX() - 45, getY());
            world.addObject(cluster3, getX() + 45, getY());
            world.addObject(cluster4, getX() - 45, getY() - 45);
            world.addObject(cluster5, getX() + 45, getY() - 45);
            
            world.removeObject(this);
        }
        else{
            setLocation(getX(), getY() - speed);
        
            if(isTouching(Mini.class) || isTouching(Basic.class) || isTouching(Stage1.class) || isTouching(Stage2.class) || isTouching(MiniBoss.class) || isTouching(FinalBoss.class)){
                health--;            
            }            
            if(isAtEdge()){
                health = 0;
            }        
        }
    }
}
