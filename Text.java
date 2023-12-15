import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/*
 * Create a Text class to display text.
 * It is overloaded to take in two different types of inputs.
 */
public class Text extends Actor{

    // Set variables and colours
    private String context = "";
    private String value;
    private int fontSize;
    private Color lineColor = Color.BLACK;
    private Color fillColor = Color.WHITE;
    
    private static final Color transparent = new Color(0, 0, 0, 0);

    public Text(String context, int value, int fontSize){ // For score and lives display with a context and a number
        this.value = Integer.toString(value);
        this.context = context; 
        
        this.fontSize = fontSize;
        updateImage();
    }

    public Text(String value, int fontSize){ // For plain text
        this.value = value;
        this.fontSize = fontSize;
        updateImage();
    }

    public void setValue(String value){ // Updates the value for a text
        this.value = value;
        updateImage();
    }
    
    public void setValue(int value){ // Updates the value for an integer
        this.value = Integer.toString(value);
        updateImage();
    }
    
    public void setLineColor(Color lineColor){ // Set the line colour of the text
        this.lineColor = lineColor;
        updateImage();
    }
    
    public void setFillColor(Color fillColor){ // Set the fill colour of the text
        this.fillColor = fillColor;
        updateImage();
    }
    
    private void updateImage(){ // Update the image and refresh it with correct parameters
        value = this.context + this.value;
        setImage(new GreenfootImage(value, fontSize, fillColor, transparent, lineColor));
    }
}
