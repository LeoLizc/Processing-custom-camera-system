import processing.core.PApplet;
import processing.core.PConstants.*;

public class Main extends PApplet{

    public void settings(){
        size(400, 400, P3D);
    }

    public void setup(){
//        size(400,400, PConstants.P3D);
        translate(232, 192, 0);
        rotateY(0.5f);
        noFill();
        box(160);
    }

    public void draw(){

    }
    public static void main(String[] args){
        PApplet.main("Main");
    }

}
