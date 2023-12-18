import greenfoot.*;  

/* 
 * The button class allows buttons to be created.
 * The button takes in an image as a string and two integer values to set its size.
 * These values are then put in a constructor.
 */

public class Button extends Actor{
    public int scaleX;
    public int scaleY;
    
    public Button(String theImage, int scaleX, int scaleY){
        GreenfootImage image = new GreenfootImage(theImage);
        image.scale(scaleX, scaleY);
        setImage(image);
                
    }
}
