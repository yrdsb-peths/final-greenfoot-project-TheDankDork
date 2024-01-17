import greenfoot.*;  

public class XP extends Actor{
    public static int level = 0;
    public static int levelXP = 3;
    public static int currentXP = 0;
    
    GreenfootImage[] xpBar = new GreenfootImage[10];        
    
    public XP(){
        for(int i = 0; i < 10; i++) {    
            xpBar[i] = new GreenfootImage("images/xp_bar/xp" + i +".png");
        }
    }
    
    public void act(){
        SpaceShooter world = (SpaceShooter) getWorld();
        
        if(levelXP <= currentXP){
            level++;
            levelXP = (int) (levelXP * 1.5);
            currentXP = 0;
        }
        double percentEquiv = (double) currentXP / levelXP;
        int roundedEquiv = (int) (percentEquiv * 10);
        
        setImage(xpBar[roundedEquiv]);
        
        for(int i = 0; i < 10; i++){
            if(roundedEquiv == i){
                setLocation(105 + (5 * i), 730);
            }
        }        

    }
}