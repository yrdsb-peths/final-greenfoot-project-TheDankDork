import greenfoot.*;  

public class SpaceShooter extends World{
    public int score = 0;
    public int shield = 5;
    public int lives = 3;
    
    public int speedMod = 0;  // speedMod allows the game to gradually increase speed
    
    public int basicGauge = 0;
    public int Stage1Gauge = -500;
    public int Stage2Gauge = -2000;
    
    boolean gameActive = false;
    
    public int wave = 0;
    
    public static int highscore;
    
    Player player = new Player(1, 4, 1, 100);
    
    Background bg1 = new Background();
    Background bg2 = new Background();
    
    Text displayScore; 
    Text displayLevel;
    Text highScore;
    
    Button title = new Button("title.png", 600, 450);
    Button rules = new Button("rules.png", 400, 800);
    Button frame = new Button("frame.png", 400, 200);
    Button playerImage = new Button("player0.png", 100, 100);
    
    HP hpBar = new HP();
    XP xpBar = new XP();
    Button shieldIcon = new Button("shield.png", 35, 40);
    Shield shieldBar = new Shield();
    
    Button gameover = new Button("gameover.png", 400, 400);
    Button homeButton = new Button("home_button.png", 180, 180);
    Button restartButton = new Button("playagain_button.png", 180, 180);
    
    Button startButton = new Button("start_button.png", 180, 160);
    Button ruleButton = new Button("rule_button.png", 184, 164);
    Button backButton = new Button("back_button.png", 100, 35);
    
    public SpaceShooter(){    
        super(400, 800, 1, false);
        
        displayScore = new Text("Score: ", score, 30);
        displayLevel = new Text("Lvl ", XP.level, 24);
        highScore = new Text("Highscore: ", highscore, 40);
        
        addObject(bg1, 200, -390);
        addObject(bg2, 200, 400);
        
        addObject(frame, 200, 700);
        addObject(title, 200, 250);
        addObject(startButton, 110, 450);
        addObject(ruleButton, 300, 450);
        addObject(highScore, 185, 600);
        addObject(playerImage, 200, 700);
    }
    
    public void act(){
        checkStartPressed();
        checkRulesPressed();
        checkBackPressed();
        checkHomePressed();
        checkRestartPressed();
        
        if(gameActive){
            gameLoop();
        }
    }
    
    public void checkStartPressed(){        
        if(Greenfoot.mouseClicked(startButton)){
            removeObject(startButton);
            removeObject(ruleButton);
            removeObject(title); 
            removeObject(playerImage);
            removeObject(highScore);
            
            addObject(player, 200, 700);
            addObject(displayScore, 100, 690);
            addObject(displayLevel, 76, 730);
            addObject(hpBar, 300, 690);
            addObject(xpBar, 90, 690);
            addObject(shieldIcon, 250, 730);
            addObject(shieldBar, 240, 730);
            
            initGame();
        }
    } 
    
    public void checkRulesPressed(){
        if(Greenfoot.mouseClicked(ruleButton)){
            removeObject(startButton);
            removeObject(ruleButton);
            removeObject(title);
            removeObject(playerImage);
            removeObject(highScore);
            
            addObject(rules, 200, 400);
            addObject(backButton, 50, 17);
        }
    }
    
    public void checkBackPressed(){
        if(Greenfoot.mouseClicked(backButton)){
            removeObject(rules);
            removeObject(backButton);
            
            addObject(title, 200, 250);
            addObject(startButton, 110, 450);
            addObject(ruleButton, 300, 450);
            addObject(highScore, 185, 600);
            addObject(playerImage, 200, 700);
        }
    }
    
    public void checkHomePressed(){
        if(Greenfoot.mouseClicked(homeButton)){
            removeObject(gameover);
            removeObject(homeButton);
            removeObject(restartButton);
            removeObject(displayScore);
            removeObject(displayLevel);
            removeObject(hpBar);
            removeObject(shieldIcon);
            removeObject(shieldBar);
            removeObject(xpBar);
            
            addObject(title, 200, 250);
            addObject(startButton, 110, 450);
            addObject(ruleButton, 300, 450);
            addObject(highScore, 185, 600);
            addObject(playerImage, 200, 700);
        } 
    }
    
    public void checkRestartPressed(){
        if(Greenfoot.mouseClicked(restartButton)){
            removeObject(gameover);
            removeObject(homeButton);
            removeObject(restartButton);
            
            score = 0;
            shield = 5;
            lives = 3;
            speedMod = 0;
            
            XP.currentXP = 0;
            XP.levelXP = 2;
            XP.level = 0;
            
            Player.damage = 1;
            Player.atkSpd = 100;
            
            addObject(player, 200, 700);
            addObject(hpBar, 300, 690);
            
            gameActive = true;    
        }
        
    }
    
    public void initGame(){               
        score = 0;
        shield = 5;
        lives = 3;
        speedMod = 0;
        
        basicGauge = 0;
        Stage1Gauge = -500;
        Stage2Gauge = -2000;
        
        XP.currentXP = 0;
        XP.levelXP = 2;
        XP.level = 0;
        
        Player.damage = 1;
        Player.atkSpd = 100;
        
        gameActive = true;
    }
    
    public void gameLoop(){
        if(lives < 1){
            if(score > highscore){
                highscore = score;
                highScore.setValue(highscore);
            }
            gameOver();
            removeObject(player);
        }
        else{
            increaseGauge();
            spawnMeteor();
            
            // Constantly update the score and shield display
            displayScore.setValue(score);
            displayLevel.setValue(XP.level);
                    
            if(((score % 10) == 0) && (speedMod < 10)){
                speedMod = score/10;
            }
        }

    }
    
    public void gameOver(){
        addObject(gameover, 200, 200);
        addObject(homeButton, 100, 440);
        addObject(restartButton, 300, 440);
        
        basicGauge = 0;
        Stage1Gauge = -500;
        Stage2Gauge = -2000;
        wave = 0;
        
        gameActive = false;
    }
    
    public void modifyScore(int points){    
        score += points;
    }
    
    // Modify shield based on meteor that was leaked
    public void modifyShield(int amount){
        shield += amount;    
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
        if(Stage1Gauge >= (500 - speedMod * 20)){
            spawnStage1();
            Stage1Gauge = 0;
        }
        if(Stage2Gauge >= (1000 - speedMod * 30)){
            spawnStage2();
            Stage2Gauge = 0;
        }
        if(score >= 15 && wave == 0){
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

