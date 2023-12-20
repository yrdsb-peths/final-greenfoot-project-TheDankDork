import greenfoot.*;  

public class Meteor extends Actor{
    public int xPos;
    public int yPos;
    public static int health = 1;
    public static int speed = 1;
    public static int points = 1;
    public static int livesTaken = 1;
    
    public Meteor(int xPos, int yPos, int health, int speed, int points, int livesTaken){
        this.xPos = xPos;
        this.yPos = yPos;
        this.health = health;
        this.speed = speed;
        this.points = points;
        this.livesTaken = livesTaken;
    }
    
    public void act(){
        setLocation(xPos, yPos); // Set location of the meteor
        
        SpaceShooter world = (SpaceShooter) getWorld();
        yPos += (speed); // Allows the meteor to slowly fall down
        
        // Remove self and take away lives if touching ground
        if(isTouchingGround()){
            world.modifyLives(-livesTaken);
            world.removeObject(this);
        }
        
    }
    
    private boolean isTouchingGround(){
        if(yPos >= 790){ // Check the y position for the ground
            return true;
        }
        return false;
    }
}
