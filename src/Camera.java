import processing.core.PApplet;
import processing.core.PVector;

public class Camera {
    static final int W=0,A=1,S=2,D=3;
    final boolean[] keysPressed = new boolean[4];

    PApplet p;
    PVector position;
    float velocity = 3;

    public Camera(PApplet parent) {
        p = parent;
        position = new PVector();
    }

    public void startMove(char key){
        updateMove(key, true);
    }

    public void stopMove(char key){
        updateMove(key, false);
    }

    public void updateMove(char key, boolean value) {

        switch (key) {
            case 'w' -> {
                position.y += 5;
                keysPressed[W] = value;
            }
            case 'a' -> {
                position.x -= 5;
                keysPressed[A] = value;
            }
            case 's' -> {
                position.y -= 5;
                keysPressed[S] = value;
            }
            case 'd' -> {
                position.x += 5;
                keysPressed[D] = value;
            }
        }

    }

    public void updateCamera(){
//        Update position
        if(keysPressed[W]){
            position.y += velocity;
        }
        if(keysPressed[A]){
            position.x -= velocity;
        }
        if(keysPressed[S]){
            position.y -= velocity;
        }
        if(keysPressed[D]){
            position.x += velocity;
        }
    }

    public void processCamera(){
        p.translate(-position.x, position.y, position.z);
    }
}
