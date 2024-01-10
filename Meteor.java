import greenfoot.*;  

public class Meteor extends SmoothMover{
    public double xPos;
    public double yPos;
    public int health = 1;
    public double speed = 1;
    public int points = 1;
    public int livesTaken = 1;
    public boolean isExploding = false;

    GreenfootImage[] images = new GreenfootImage[25];
    SimpleTimer explosionTimer = new SimpleTimer();

    public Meteor(String image, int scale, int rotation, int xPos, int yPos, int health, double speed, int points, int livesTaken){
        setImage(image);
        this.xPos = xPos;
        this.yPos = yPos;
        this.health = health;
        this.speed = speed;
        this.points = points;
        this.livesTaken = livesTaken;
        for(int i = 0; i < 25; i++) {
            images[i] = new GreenfootImage("images/explosions/explosion" + i +".png");
        } 
    }

    public void act(){
        SpaceShooter world = (SpaceShooter) getWorld();

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
                explosionTimer.mark();
                isExploding = true;
                world.score += points;
            }
        }            

        if(isExploding) {
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
            } else {
                setImage(images[frame]);
            }
        }

    }
}
