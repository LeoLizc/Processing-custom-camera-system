import processing.core.PApplet;

public class Main extends PApplet{
    Camera camera;
    public void settings(){
        size(400, 400, P3D);
        camera = new Camera(this);
    }

    public void setup(){
//        size(400,400, PConstants.P3D);
    }

    public void draw(){
        update();
//        rotateX(PI/0.4f);
        camera.processCamera();
        render();
    }

    void update(){
        camera.updateCamera();
    }

    void render(){
        background(240,240,240);
        drawCube();
    }

    void drawCube(){
        translate(232, 192, 0);
        rotateY(0.5f);
        noFill();
        box(160);
    }
    public void keyPressed(){
        camera.startMove(key);
    }

    public void keyReleased(){
        camera.stopMove(key);
    }

    public static void main(String[] args){
        PApplet.main("Main");
    }

}
