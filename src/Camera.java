import processing.core.PApplet;
import processing.core.PVector;

public class Camera {
    static final int W=0,A=1,S=2,D=3;
    static final float CAMERA_Z_DISTANCE = 311.717f;
    final boolean[] keysPressed = new boolean[4];

    PApplet p;
    PVector position;
    float velocity = 3;

    PVector rotation;
    float aVelocity = 0.23f*0.02f;

    int xOffset, yOffset;

    public Camera(PApplet parent) {
        p = parent;
        position = new PVector();
        rotation = new PVector(0,0);
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

    public void startRotation(){
        xOffset = p.mouseX;
        yOffset = p.mouseY;
    }

    public void rotate(){
        float xMovement = -(p.mouseX - xOffset)*aVelocity;// positive = right, negative = left
        float yMovement = (yOffset - p.mouseY)*aVelocity;// positive = up, negative = down

        // X movement implies y-axis rotation
        // Positive xMovement implies positive rotation
        rotation.y = (rotation.y + xMovement < p.PI)? (rotation.y + xMovement > -p.PI)? (rotation.y + xMovement) : p.TWO_PI-(rotation.y + xMovement) : (rotation.y + xMovement)- p.TWO_PI;

        // Y movement implies x-axis rotation
        // Positive yMovement implies positive rotation
        // should be between -PI/2 and PI/2
        // rotation.x = p.constrain(rotation.x + yMovement, -p.HALF_PI, p.HALF_PI);

        xOffset = p.mouseX;
        yOffset = p.mouseY;

        System.out.println("x: "+rotation.x+", y: "+rotation.y);
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
        p.translate(p.width/2,p.height/2, CAMERA_Z_DISTANCE);

        //process rotation
        p.rotateY(rotation.y);
        p.rotateX(rotation.x);

        // process tanslation
        p.translate(-position.x, position.y, position.z);
    }
}
