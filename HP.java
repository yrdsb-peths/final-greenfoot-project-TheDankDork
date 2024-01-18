import greenfoot.*;  

public class HP extends Actor{
    GreenfootImage[] hpBar = new GreenfootImage[3];        
    
    // Set images for the health bar display
    public HP(){
        for(int i = 0; i < 3; i++) {    
            hpBar[i] = new GreenfootImage("images/hp_bar/hp" + i +".png");
        }
    }
    
    public void act(){
        SpaceShooter world = (SpaceShooter) getWorld();
        if(world.lives < 1){
            world.removeObject(this);
        }
        else{
            setImage(hpBar[world.lives - 1]);
        
            // Adjust location depending on the current image
            if(world.lives == 3){
                setLocation(300, 690);
            }
            else if(world.lives == 2){
                setLocation(275, 690);
            }
            else if(world.lives == 1){
                setLocation(250, 690);
            }        
        }

    }
}
