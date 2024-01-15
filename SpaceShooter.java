import greenfoot.*;  

public class SpaceShooter extends World{
    public int score = 0;
    public int lives = 10;
    public int level = 0;
    public int speedMod = 0;  // speedMod allows the game to gradually increase speed
    
    public int basicGauge = 0;
    public int Stage1Gauge = -500;
    public int Stage2Gauge = -2000;
    
    boolean gameActive = false;
    
    public int wave = 0;
    
    Player player = new Player(1, 4, 1, 100, -90);
    
    Background bg1 = new Background();
    Background bg2 = new Background();
    
    Text displayScore; 
    Text displayLevel;
    
    Button title = new Button("title.png", 600, 450);
    Button rules = new Button("rules.png", 400, 800);
    Button frame = new Button("frame.png", 400, 200);
    
    Button startButton = new Button("start_button.png", 180, 160);
    Button ruleButton = new Button("rule_button.png", 184, 164);
    Button backButton = new Button("back_button.png", 100, 35);
    
    public SpaceShooter(){    
        super(400, 800, 1, false);
        
        displayScore = new Text("Score: ", score, 30);
        displayLevel = new Text("XP", 30);
        
        addObject(bg1, 200, -390);
        addObject(bg2, 200, 400);
        
        addObject(frame, 200, 700);
        addObject(title, 200, 250);
        addObject(startButton, 110, 550);
        addObject(ruleButton, 300, 550);
    }
    
    public void act(){
        checkStartPressed();
        checkRulesPressed();
        checkBackPressed();
        
        if(gameActive){
            increaseGauge();
            spawnMeteor();
            
            // Constantly update the score and lives display
            displayScore.setValue(score);
                    
            if(((score % 10) == 0) && (speedMod < 10)){
                speedMod = score/10;
            }
        }
    }
    
    public void checkStartPressed(){        
        if(Greenfoot.mouseClicked(startButton)){
            removeObject(startButton);
            removeObject(ruleButton);
            removeObject(title); 
            
            addObject(player, 200, 700);
            addObject(displayScore, 100, 690);
            addObject(displayLevel, 70, 730);
            
            initGame();
        }
    } 
    
    public void checkRulesPressed(){
        if(Greenfoot.mouseClicked(ruleButton)){
            removeObject(startButton);
            removeObject(ruleButton);
            removeObject(title);
            
            addObject(rules, 200, 400);
            addObject(backButton, 50, 17);
        }
    }
    
    public void checkBackPressed(){
        if(Greenfoot.mouseClicked(backButton)){
            removeObject(rules);
            removeObject(backButton);
            
            addObject(frame, 200, 700);
            addObject(title, 200, 250);
            addObject(startButton, 110, 550);
            addObject(ruleButton, 300, 550);
        }
    }
    
    public void initGame(){               
        score = 0;
        lives = 10;
        level = 0;
        speedMod = 0;
        
        basicGauge = 0;
        Stage1Gauge = -500;
        Stage2Gauge = -2000;
        
        gameActive = true;
    }
    
    public void modifyScore(int points){    
        score += points;
    }
    
    // Modify lives based on meteor that was leaked
    public void modifyLives(int amount){
        lives += amount;    
    }
    
    private void increaseGauge(){
        basicGauge++;
        Stage1Gauge++;
        Stage2Gauge++;
    }
    
    public void spawnMeteor(){
        if(basicGauge >= (200 - speedMod * 10)){
            spawnBasic();
            basicGauge = 0;
        }
        if(Stage1Gauge >= (500 - speedMod * 10)){
            spawnStage1();
            Stage1Gauge = 0;
        }
        if(Stage2Gauge >= (1000 - speedMod * 10)){
            spawnStage2();
            Stage2Gauge = 0;
        }
        if(score >= 5 && wave == 0){
            spawnMiniBoss();
            wave++;
        }
    }
    
    public void spawnBasic(){
        int x = Greenfoot.getRandomNumber(400);
        int n = Greenfoot.getRandomNumber(100);
        Basic basic = new Basic("basic_meteor", n, 0, x, -50, 1, 1, 1, 1);
        addObject(basic, x, 0);
    }    
    public void spawnStage1(){
        int x = Greenfoot.getRandomNumber(400);
        int n = Greenfoot.getRandomNumber(100);
        Stage1 stage1 = new Stage1("stage_1", n, 0, x, -50, 2, 2, 2, 2);
        addObject(stage1, x, 0);
    }    
    public void spawnStage2(){
        int x = Greenfoot.getRandomNumber(400);
        int n = Greenfoot.getRandomNumber(100);
        Stage2 stage2 = new Stage2("stage_2", n, 0, x, -50, 2, 4, 4, 4);
        addObject(stage2, x, 0);
    }    
    public void spawnMiniBoss(){
        MiniBoss miniBoss = new MiniBoss("mini_boss", 0, 200, 0, 30, 0.1, 30, 30);
        addObject(miniBoss, 200, 0);
    }
}

