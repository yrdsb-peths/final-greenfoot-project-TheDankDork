import greenfoot.*;  

public class Meteor extends Actor{
    public int xPos;
    public int yPos;
<<<<<<< Updated upstream
    public static int health = 1;
    public static int points = 1;
    public static int livesTaken = 1;
=======
    public int health = 1;
    public int speed = 1;
    public int points = 1;
    public int livesTaken = 1;
>>>>>>> Stashed changes
    
    public Meteor(int xPos, int yPos, int health, int points, int livesTaken){
        this.xPos = xPos;
        this.yPos = yPos;
        this.health = health;
        this.points = points;
        this.livesTaken = livesTaken;
    }
    
    public void act(){
        setLocation(xPos, yPos); // Set location of the meteor
        
        SpaceShooter world = (SpaceShooter) getWorld();
        yPos += (2 + world.speedMod); // Allows the meteor to slowly fall down
        
        // Remove self and take away lives if touching ground
        if(isTouchingGround()){
            world.modifyLives(-livesTaken);
            world.removeObject(this);
        }
<<<<<<< Updated upstream
        
=======
        checkHealth();
>>>>>>> Stashed changes
    }
    
    private boolean isTouchingGround(){
        if(yPos >= 790){ // Check the y position for the ground
            return true;
        }
        return false;
    }
    
    private void checkHealth(){
        if(health <= 0){
            SpaceShooter world = (SpaceShooter) getWorld();
            world.score += points;
            world.removeObject(this);
        }
    }
    
    public boolean isHit(){
        return isTouching(Bullet.class);
    }
    
    public void changeHealth(int amount){
        health += amount;
    }
}
