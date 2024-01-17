import greenfoot.*;  

public class Player extends Actor{
    public static int size = 1;
    public static int speed = 1;
    public static int bullets = 1;
    public static int atkSpd = 1;
    public static int damage = 1;
    public static int type = 0;
    
    int attackGauge;
    
    public Player(int size, int speed, int bullets, int atkSpd){
        this.size = size;
        this.speed = speed;
        this.bullets = bullets;
        this.atkSpd = atkSpd;
    }
    
    public void act(){
        if(Greenfoot.isKeyDown("a") && getX() > 0){
            setLocation(getX() - speed, getY());
        }
        if(Greenfoot.isKeyDown("d") && getX() < 400){
            setLocation(getX() + speed, getY());
        }
        if(Greenfoot.isKeyDown("w") && getY() > 0){
            setLocation(getX(), getY() - speed);
        }
        if(Greenfoot.isKeyDown("s") && getY() < 800){
            setLocation(getX(), getY() + speed);
        }
        setType();
        
        attackGauge++;
        attack();
        
    }
    
    private void attack(){
        if(type == 0){
            if(attackGauge >= atkSpd){
                attack0();
                attackGauge = 0;
            }
        }
        else if(type == 1){
            if(attackGauge >= atkSpd){
                attack1();
                attackGauge = 0;
            }
        }
        else if(type == 2){
            if(attackGauge >= atkSpd){
                attack2();
                attackGauge = 0;
            }
        }
        else if(type == 3){
            if(attackGauge >= (atkSpd/2)){
                attack3();
                attackGauge = 0;
            }
        }
        else if(type == 4){
            if(attackGauge >= (atkSpd - 20)){
                attack4();
                attackGauge = 0;
            }
        }
    }
    
    private void attack0(){
        SpaceShooter world = (SpaceShooter) getWorld();
        Type0 bullet = new Type0(damage, 1, 3, 1);
        
        world.addObject(bullet, getX(), getY() - 40);                
    }
    
    private void attack1(){
        SpaceShooter world = (SpaceShooter) getWorld();
        Type1 bullet1 = new Type1(damage, 1, 3, 1);
        Type1 bullet2 = new Type1(damage, 1, 3, 1);
        
        world.addObject(bullet1, getX() - 10, getY() - 40);
        world.addObject(bullet2, getX() + 10, getY() - 40);
    }
    
    private void attack2(){
        SpaceShooter world = (SpaceShooter) getWorld();
        Type2 bullet1 = new Type2(damage, 1, 3, 1);
        Type2 bullet2 = new Type2(damage, 1, 3, 1);
        
        world.addObject(bullet1, getX() - 10, getY() - 40);
        world.addObject(bullet2, getX() + 10, getY() - 40);
    }
    
    private void attack3(){
        SpaceShooter world = (SpaceShooter) getWorld();
        Type3 bullet = new Type3(damage, 1, 3, 1);
        world.addObject(bullet, getX(), getY() - 40);
    }
    
    private void attack4(){
        SpaceShooter world = (SpaceShooter) getWorld();
        Type4 bullet = new Type4(damage, 1, 5, 1);
        world.addObject(bullet, getX(), getY() - 40);
    }
    
    private void setType(){
        if(XP.level < 5){
            type = 0;
            setImage("player0.png");
        }
        else if(XP.level < 9){
            type = 1;
            setImage("player1.png");
        }
        else if(XP.level < 12){
            type = 2;
            setImage("player2.png");
        }
        else if(XP.level < 14){
            type = 3;
            setImage("player3.png");
        }
        else{
            type = 4;
            setImage("player4.png");
        }
    }
}
