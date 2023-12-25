import greenfoot.*;  

public class SpaceShooter extends World{
    public int score = 0;
    public int lives = 3;
    public int speedMod = 0;  // speedMod allows the game to gradually increase speed
    
    public int basicGauge = 0;
    
    Player player = new Player(1, 4, 1, 100, -90);
    
    Text displayScore; 
    Text displayLives;
    
    public SpaceShooter(){    
        super(400, 800, 1);
        
        displayScore = new Text("Score: ", score, 30);
        displayLives = new Text("Lives: ", lives, 30);
        
        spawnBasic();
    }
    
    public void act(){
        // Display score and lives for user
        addObject(displayScore, 70, 40);
        addObject(displayLives, 330, 40);         
        
        // Constantly update the score and lives display
        displayScore.setValue(score);
        displayLives.setValue(lives);
        
        addObject(player, 200, 700);
        
        if(((score % 10) == 0) && (speedMod < 10)){
            speedMod = score/10;
        }
        
        basicGauge++;
        spawnRandom();
    }
    
    public void modifyScore(int points){    
        score += points;
    }
    
    // Modify lives based on meteor that was leaked
    public void modifyLives(int amount){
        lives += amount;    
    }
    
    public void spawnRandom(){
        if(basicGauge >= (200 - speedMod * 10)){
            spawnBasic();
            basicGauge = 0;
        }
    }
    
    public void spawnBasic(){
        int x = Greenfoot.getRandomNumber(400);
        Meteor basic = new Meteor("basic_meteor.png", 50, 0, x, 0, 1, 1, 1, 1);
        addObject(basic, x, 0);
    }
    
    public void spawnStage1(){
        int x = Greenfoot.getRandomNumber(400);
        Meteor basic = new Meteor("basic_meteor.png", 50, 0, x, 0, 1, 1, 1, 1);
        addObject(basic, x, 0);
    }
    
    public void spawnStage2(){
        int x = Greenfoot.getRandomNumber(400);
        Meteor basic = new Meteor("basic_meteor.png", 50, 0, x, 0, 1, 1, 1, 1);
        addObject(basic, x, 0);
    }    

}

