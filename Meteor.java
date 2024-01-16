import greenfoot.*;  

public class Meteor extends SmoothMover{
    public int powerup = 0;
    public int type = 0;
    public String image;
    
    public double xPos;
    public double yPos;
    public int health = 1;
    public double speed = 1;
    public int points = 1;
    public int damage = 1;
    public boolean isExploding = false;

    GreenfootImage[] images = new GreenfootImage[25];
    SimpleTimer explosionTimer = new SimpleTimer();

    public Meteor(String image, int powerup, int rotation, int xPos, int yPos, int health, double speed, int points, int damage){
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
        
        else if(!world.gameActive){
            world.removeObject(this);
        }
        
        else if(isTouching(Bullet.class)){
            health--;
            if(health == 0){
                applyPowerup();
                world.score += points;
                XP.currentXP += points;
                
                explosionTimer.mark();
                isExploding = true;
            }
        }            

        if(isExploding){
            explode(50, 50);
        }
    }

    public boolean isTouchingGround(){
        if(yPos >= 790){ // Check the y position for the ground
            return true;
        }
        return false;
    }

    int frame = 0;
    public void explode(int scaleX, int scaleY){ 
        if(explosionTimer.millisElapsed() > 10){
            frame += 1;
            explosionTimer.mark();
            if(frame == images.length){
                getWorld().removeObject(this);
            } 
            else{
                setImage(images[frame]);
            }
        }
    }
    
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
                Player.damage++;
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
                Player.damage++;
                if(Player.atkSpd > 45){
                    Player.atkSpd -= 5;
                }
                break;                
        }
    }
}
