import greenfoot.*; 

public class Stage1 extends Meteor{
    
    public Stage1(String image, int scale, int rotation, int xPos, int yPos, int health, double speed, int points, int livesTaken){
        super(image, scale, rotation, xPos, yPos, health, speed, points, livesTaken);
    }
    
    public void act(){
        SpaceShooter world = (SpaceShooter) getWorld();
        
        if(health == 0){
            explode(50, 50);
            world.removeObject(this);
        }
        else{
            yPos += (speed); // Allows the meteor to slowly fall down
            setLocation(xPos, yPos); // Set location of the meteor
 
            // Remove self and take away lives if touching ground
            if(isTouchingGround() || isTouching(Player.class)){
                world.modifyLives(-livesTaken);
                health = 0;
            }
            if(isTouching(Bullet.class)){
                health--;
                if(health == 0){
                    world.score += points;
                }
            }            
        }

    }
}