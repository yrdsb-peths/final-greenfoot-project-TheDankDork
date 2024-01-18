import greenfoot.*; 

public class FinalBoss extends Meteor{
    
    public FinalBoss(String image, int xPos, int yPos, int health, double speed, int points, int livesTaken){
        super(image, 0, xPos, yPos, health, speed, points, livesTaken);
    }
    
    public void act(){
        SpaceShooter world = (SpaceShooter) getWorld();

        yPos += (speed); // Allows the meteor to slowly fall down
        setLocation(xPos, yPos); // Set location of the meteor

        // Remove self and take away lives if touching ground
        if(isTouching(Player.class)){
            if(world.shield > damage){
                world.modifyShield(-damage);
            }
            else if((world.shield > 0) && (world.shield <= damage)){
                world.shield = 0;
            }
            else{
                world.lives--;
            }
            
            world.removeObject(this); 
        }
        
        else if(isTouchingGround()){
            world.lives--;
            world.removeObject(this);
        }
              
        else if(isTouching(Type0.class) || isTouching(Type1.class)){
            health -= Player.damage;
            if(health <= 0){
                explosionTimer.mark();
                isExploding = true;
            }
        }   
        
        else if(isTouching(Type2.class)){
            health -= (Player.damage + 1);
            if(health <= 0){                    
                explosionTimer.mark();
                isExploding = true;
            }
        }
        
        else if(isTouching(Type3.class)){
            health -= (Player.damage + 2);
            
            if(health <= 0){
                explosionTimer.mark();
                isExploding = true;
            }
        }
        
        else if(isTouching(Type4.class)){
            health -= (Player.damage + 3);
            
            if(health <= 0){
                applyPowerup();
                explosionTimer.mark();
                isExploding = true;
            }
        }
        
        else if(!world.gameActive){
            world.removeObject(this);
        }

        if(isExploding){
            explode();
        }
    }
}
