import greenfoot.*;  

public class Meteor extends SmoothMover{
    // Create the default variables all meteors will have
    public int powerup = 0;
    public int type = 0;
    public String image;
    
    public double xPos;
    public double yPos;
    public int health = 1;
    public double speed = 1;
    public int points = 1;
    public int damage = 1;
    public boolean isExploding = false; // Determines whether or not the animation for exploding should play

    GreenfootImage[] images = new GreenfootImage[25];
    SimpleTimer explosionTimer = new SimpleTimer();
    
    GreenfootSound explosionSound = new GreenfootSound("sounds/explosion_audio.mp3");

    public Meteor(String image, int powerup, int xPos, int yPos, int health, double speed, int points, int damage){
        this.image = image;
        this.powerup = powerup;
        givePowerup();
        
        this.xPos = xPos;
        this.yPos = yPos;
        this.health = health;
        this.speed = speed;
        this.points = points;
        this.damage = damage;
        for(int i = 0; i < 25; i++) {
            images[i] = new GreenfootImage("images/explosions/explosion" + i +".png");
        } 
    }

    public void act(){
        SpaceShooter world = (SpaceShooter) getWorld();

        yPos += (speed); // Allows the meteor to slowly fall down
        setLocation(xPos, yPos); // Set location of the meteor

        /* The following if statements detect the situation the meteor is in.
         * Depending on what the meteor is touching is removes lives, adds points, applies powerups, etc.
         */
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
              
        else if(isTouching(Type0.class) ||  
                isTouching(ClusterL.class) || 
                isTouching(ClusterR.class) || 
                isTouching(ClusterLF.class) || 
                isTouching(ClusterLF.class)){
                    
            health -= Player.damage;
            if(health <= 0){
                applyPowerup();
                explosionTimer.mark();
                isExploding = true;
            }
        }
        
        else if(isTouching(Type1.class)){
            health -= (Player.damage + 1);
            if(health <= 0){
                applyPowerup();
                explosionTimer.mark();
                isExploding = true;
            }
        }
        
        else if(isTouching(Type2.class)){
            health -= (Player.damage + 2);
            if(health <= 0){
                applyPowerup();
                explosionTimer.mark();
                isExploding = true;
            }
        }
        
        else if(isTouching(Type3.class)){
            health -= (Player.damage + 1);
            setLocation(getX(), getY() - 20);
            
            if(health <= 0){
                applyPowerup();
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
        
        // If the game is over, remove itself immediately
        else if(!world.gameActive){
            world.removeObject(this);
        }

        if(isExploding){
            explode();
        }
    }

    public boolean isTouchingGround(){
        if(yPos >= 790){ // Check the y position for the ground
            return true;
        }
        return false;
    }

    // Constantly set the frames of the explosion.  When completed, remove itself
    int frame = 0;
    public void explode(){
        explosionSound.play();
        
        SpaceShooter world = (SpaceShooter) getWorld();
        if(explosionTimer.millisElapsed() > 10){
            frame += 1;
            explosionTimer.mark();
            if(frame == images.length){
                world.score += points;
                XP.currentXP += points;
            
                getWorld().removeObject(this);
            } 
            else{
                setImage(images[frame]);
            }
        }
    }
    
    // Apply the necessary powerup, if needed
    public void givePowerup(){
        if(powerup < 75){
            setImage(image + ".png");
            type = 0;
        }
        else if(powerup < 83){
            setImage(image + "HP" + ".png");
            type = 1;
        }
        else if(powerup < 91){
            setImage(image + "DMG" + ".png");
            type = 2;
        }
        else if(powerup < 99){
            setImage(image + "SPD" + ".png");
            type = 3;
        }
        else{
            setImage(image + "ALL" + ".png");
            type = 4;
        }
    }
    
    // Once a powerup is given, the respective code is called to change variables and apply the changes
    public void applyPowerup(){
        SpaceShooter world = (SpaceShooter) getWorld();
        switch(type){
            case 1:
                if(world.shield < 6){
                    world.modifyShield(5);
                }
                else{
                    world.shield = 10;
                }
                break;
                
            case 2: 
                if(Player.damage < 5){
                    Player.damage++;
                }
                break;
                
            case 3:
                if(Player.atkSpd > 45){
                    Player.atkSpd -= 5;
                }
                break;
                
            case 4:
                if(world.shield < 6){
                    world.modifyShield(5);
                }
                else{
                    world.shield = 10;
                }
                if(Player.damage < 5){
                    Player.damage++;
                }
                if(Player.atkSpd > 45){
                    Player.atkSpd -= 5;
                }
                break;                
        }
    }
}
