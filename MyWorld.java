import greenfoot.*;  

public class MyWorld extends World{
    public int score = 0;
    public int lives = 3;
    public int speedMod = 0;  // speedMod allows the game to gradually increase speed
    
    Player player = new Player(1, 4, 1);
    Meteor basic = new Meteor(0, 0, 1, 1);
    
    
    public MyWorld(){    
        super(400, 800, 1);
    }
    
    public void act(){
        addObject(player, 200, 700);
        addObject(basic, 0, 0);
    }
    
    public void modifyScore(int points){    
        score += points;
    }
    
    // Modify lives based on meteor that was leaked
    public void modifyLives(int amount){
        lives += amount;    
    }
}
