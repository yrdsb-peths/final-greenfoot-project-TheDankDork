import greenfoot.*;  

public class SpaceShooter extends World{
    public int score = 0;
    public int lives = 3;
    public int speedMod = 0;  // speedMod allows the game to gradually increase speed
    
    Player player = new Player(1, 4, 1, -90);
    Meteor basic = new Meteor(0, 0, 1, 1);
    
    
    public SpaceShooter(){    
        super(400, 800, 1);
        spawnBasic();
    }
    
    public void act(){
        addObject(player, 200, 700);
    }
    
    public void modifyScore(int points){    
        score += points;
    }
    
    // Modify lives based on meteor that was leaked
    public void modifyLives(int amount){
        lives += amount;    
    }
    
    public void spawnBasic(){
        int x = Greenfoot.getRandomNumber(400);
        Meteor basic = new Meteor(x, 0, 1, 1);
        addObject(basic, x, 0);
    }  
}

