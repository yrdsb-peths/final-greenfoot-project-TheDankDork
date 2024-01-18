import greenfoot.*;  

public class SpaceShooter extends World{
    // Set default scores, shield, lives
    public int score = 0;
    public int shield = 5;
    public int lives = 3;
    
    public int speedMod = 0;  // speedMod allows the game to gradually increase speed
    
    // Set gauges to determine when to spawn meteors
    public int miniGauge = 0;
    public int basicGauge = -500;
    public int Stage1Gauge = -2000;
    public int Stage2Gauge = -5000;
    public int miniBossGauge = -100000;
    public int finalBossGauge = -300000;
    
    boolean gameActive = false;
    
    // Wave determines what bosses to spawn alongside the basic gauge and scores
    public int wave = 0;
    
    public static int highscore;
    
    /* The code below creates all objects in the world.
     * This allows them to be ready for use at any time and ensures they are only created once.
     */
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
    
    GreenfootSound titleSound = new GreenfootSound("sounds/title_audio.mp3");
    GreenfootSound gameSound = new GreenfootSound("sounds/game_audio.mp3");
    
    public SpaceShooter(){
        // Generate the basic world with the necessary components
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
        // Play the sound effect and constantly check for buttons pressed
        titleSound.playLoop(); 
        
        checkStartPressed();
        checkRulesPressed();
        checkBackPressed();
        checkHomePressed();
        checkRestartPressed();
        
        // Once the game is detected to be active it will enter the game loop until game over
        if(gameActive){
            gameLoop();
        }
    }
    
    /* The following methods check for buttons pressed and determines what to do with them
     * Depending on the button pressed, the method will add or remove variables, images, button, etc.
     */
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
            
            titleSound.stop();
            
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
            
            gameSound.stop();
            titleSound.playLoop();
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
            
            
            miniGauge = 0;
            basicGauge = -500;
            Stage1Gauge = -2000;
            Stage2Gauge = -5000;
            wave = 0;
            
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
    
    // Initializes the game by resetting all variables
    public void initGame(){ 
        gameSound.playLoop();
        
        score = 0;
        shield = 5;
        lives = 3;
        speedMod = 0;
        
        miniGauge = 0;
        basicGauge = -500;
        Stage1Gauge = -2000;
        Stage2Gauge = -5000;
        wave = 0;
        
        XP.currentXP = 0;
        XP.levelXP = 2;
        XP.level = 0;
        
        Player.damage = 1;
        Player.atkSpd = 100;
        
        gameActive = true;
    }
    
    // Game loop
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
            // Constantly increase the gauge and check if a meteor can be spawned
            increaseGauge();
            spawnMeteor();
            
            // Constantly update the score and shield display
            displayScore.setValue(score);
            displayLevel.setValue(XP.level);
            
            // Increase the speedMod (with a maximum value)
            if(((score % 20) == 0) && (speedMod < 20)){
                speedMod = score/20;
            }
        }

    }
    
    // Determines what to do once game is over
    public void gameOver(){
        addObject(gameover, 200, 200);
        addObject(homeButton, 100, 440);
        addObject(restartButton, 300, 440);
        
        miniGauge = 0;
        basicGauge = -500;
        Stage1Gauge = -2000;
        Stage2Gauge = -5000;
        miniBossGauge = -100000;
        finalBossGauge = -300000;
        wave = 0;
        
        gameActive = false;
    }
    
    // Modify score based on the points a meteor carries
    public void modifyScore(int points){    
        score += points;
    }
    
    // Modify shield based on meteor that was leaked
    public void modifyShield(int amount){
        shield += amount;    
    }
    
    // Increase gauge for meteor spawn
    private void increaseGauge(){
        miniGauge++;
        basicGauge++;
        Stage1Gauge++;
        Stage2Gauge++;
        miniBossGauge++;
        finalBossGauge++;
    }
    
    // Determines whether or not a meteor should be spawned.  A meteor is spawned once its gauge has been filled.
    public void spawnMeteor(){
        if(miniGauge >= (200 - speedMod * 5)){
            spawnMini();
            miniGauge = 0;
        }
        if(basicGauge >= (200 - speedMod * 5)){
            spawnBasic();
            basicGauge = 0;
        }
        if(Stage1Gauge >= (500 - speedMod * 20)){
            spawnStage1();
            Stage1Gauge = 0;
        }
        if(Stage2Gauge >= (1000 - speedMod * 40)){
            spawnStage2();
            Stage2Gauge = 0;
        }
        if(score >= 15 && wave == 0){
            spawnMiniBoss();
            wave++;
        }
        if(score >= 100 && wave == 1){
            spawnMiniBoss();
            wave++;
        }
        if(score >= 250 && wave == 2){
            spawnFinalBoss();
            miniBossGauge = -100000;
            finalBossGauge = -300000;
            wave++;
        }
        if(score >= 500 && wave >= 3){
            if(miniBossGauge >= 100000){
                spawnMiniBoss();
                miniBossGauge = 0;
            }
            if(finalBossGauge >= 300000){
                spawnMiniBoss();
                finalBossGauge = 0;
            }            
        }
    }
    
    // Creates the meteors with the correct values
    public void spawnMini(){
        int x = Greenfoot.getRandomNumber(400);
        Mini mini = new Mini("mini_meteor", x, 0, 1, 1, 1, 1);
        addObject(mini, 200, 0);
    }
    public void spawnBasic(){
        int x = Greenfoot.getRandomNumber(400);
        int n = Greenfoot.getRandomNumber(100);
        Basic basic = new Basic("basic_meteor", n, x, -50, 2, 1, 1, 2);
        addObject(basic, x, 0);
    }    
    public void spawnStage1(){
        int x = Greenfoot.getRandomNumber(400);
        int n = Greenfoot.getRandomNumber(100);
        Stage1 stage1 = new Stage1("stage_1", n, x, -50, 3, 2, 2, 3);
        addObject(stage1, x, 0);
    }    
    public void spawnStage2(){
        int x = Greenfoot.getRandomNumber(400);
        int n = Greenfoot.getRandomNumber(100);
        Stage2 stage2 = new Stage2("stage_2", n, x, -50, 3, 4, 4, 4);
        addObject(stage2, x, 0);
    }    
    public void spawnMiniBoss(){
        MiniBoss miniBoss = new MiniBoss("mini_boss", 200, 0, 100, 0.15, 30, 30);
        addObject(miniBoss, 200, 0);
    }
    public void spawnFinalBoss(){
        FinalBoss finalBoss = new FinalBoss("big_boss", 200, 0, 2000, 0.1, 30, 100);
        addObject(finalBoss, 200, 0);
    }
}

