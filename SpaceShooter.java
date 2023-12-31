import greenfoot.*;  
import java.util.*;

public class SpaceShooter extends World{
    public int score = 0;
    public int lives = 3;
    public int speedMod = 0;  // speedMod allows the game to gradually increase speed
    
    public int basicGauge = 0;
    public int Stage1Gauge = -500;
    public int Stage2Gauge = -1000;
    
    Player player = new Player(1, 4, 1, 50, -90);
    
    public SpaceShooter(){    
        super(400, 800, 1);
<<<<<<< Updated upstream
        spawnBasic();
    }
    
    public void act(){
        addObject(player, 200, 700);
        basicGauge++;
        spawnRandom();
=======
        
        displayScore = new Text("Score: ", score, 30);
        displayLives = new Text("Lives: ", lives, 30);
        
        initGame();
    }
    
    public void act(){            
        // Constantly update the score and lives display
        displayScore.setValue(score);
        displayLives.setValue(lives);
        
        increaseGauge();
        spawnMeteor();
        
        detectHit();
        
        if(((score % 10) == 0) && (speedMod < 10)){
            speedMod = score/10;
        }
>>>>>>> Stashed changes
    }
    
    public void modifyScore(int points){    
        score += points;
    }
    
    // Modify lives based on meteor that was leaked
    public void modifyLives(int amount){
        lives += amount;    
    }
    
<<<<<<< Updated upstream
    public void spawnRandom(){
        if(basicGauge >= 200){
=======
    public void increaseGauge(){
        basicGauge++;
        Stage1Gauge++;
        Stage2Gauge++;
    }
    
    public void spawnMeteor(){
        if(basicGauge >= (200 - speedMod * 10)){
>>>>>>> Stashed changes
            spawnBasic();
            basicGauge = 0;
        }
        if(Stage1Gauge >= (600 - speedMod * 10)){
            spawnStage1();
            Stage1Gauge = 0;
        }
        if(Stage2Gauge >= (1200 - speedMod * 10)){
            spawnStage2();
            Stage2Gauge = 0;
        }        
    }
    
    private void spawnBasic(){
        int x = Greenfoot.getRandomNumber(400);
        Meteor basic = new Meteor(x, 0, 1, 1, 1);
        addObject(basic, x, 0);
    }
<<<<<<< Updated upstream
=======
    
    private void spawnStage1(){
        int x = Greenfoot.getRandomNumber(400);
        Meteor basic = new Meteor("stage_1.png", 50, 0, x, 0, 2, 2, 2, 1);
        addObject(basic, x, 0);
    }
    
    private void spawnStage2(){
        int x = Greenfoot.getRandomNumber(400);
        Meteor basic = new Meteor("stage_2.png", 50, 0, x, 0, 2, 5, 3, 1);
        addObject(basic, x, 0);
    }    
>>>>>>> Stashed changes

    private void detectHit(){
        List<Meteor> meteorList = getObjects(Meteor.class);
        List<Bullet> bulletList = getObjects(Bullet.class);
        for(int i = 0; i < meteorList.size(); i++){
            Meteor meteor = meteorList.get(i);
            if(meteor.isHit()){
                meteor.changeHealth(-1);
            }
        }

        for(int i = 0; i < bulletList.size(); i++){
            Bullet bullet = bulletList.get(i);
            if(bullet.isHit()){
                removeObject(bullet);
            }
        }
    }
    
    public void initGame(){
        // Display score and lives for user
        addObject(displayScore, 70, 40);
        addObject(displayLives, 330, 40); 
        
        addObject(player, 200, 700);
        
        spawnBasic();
    } 
}

