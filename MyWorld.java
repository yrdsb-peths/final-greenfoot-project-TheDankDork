import greenfoot.*;  

public class MyWorld extends World{
    Player player = new Player(1, 4, 1);
    
    
    public MyWorld(){    
        super(400, 800, 1);
    }
    
    public void act(){
        addObject(player, 200, 700);
    }
}
