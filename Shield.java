import greenfoot.*;  

public class Shield extends Actor{
    GreenfootImage[] shieldBar = new GreenfootImage[11];        
    
    public Shield(){
        for(int i = 0; i < 11; i++) {    
            shieldBar[i] = new GreenfootImage("images/shield_bar/shield" + i +".png");
        }
    }
    
    public void act(){
        SpaceShooter world = (SpaceShooter) getWorld();
        setImage(shieldBar[world.shield]);
    
        // Adjust location depending on the current image
        for(int i = 0; i < 11; i++){
            if(world.shield == i){
                setLocation(270 + (5 * i), 730);
            }
        }        

    }
}