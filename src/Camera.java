import processing.core.PApplet;
import processing.core.PVector;

public class Camera {
    static final float CAMERA_Z_DISTANCE = 311.717f;
    final boolean[] keysPressed = new boolean[6];

    PApplet p;
    PVector position;
    float velocity = 3;

    PVector rotation;
    float aVelocity = 0.23f * 0.02f;

    int xOffset, yOffset;

    public Camera(PApplet parent) {
        p = parent;
        position = new PVector();
        rotation = new PVector(0, 0);
    }

    public void startMove(char key) {
        updateMove(key, true);
    }

    public void stopMove(char key) {
        updateMove(key, false);
    }

    public void updateMove(char key, boolean value) {

        switch (key) {
            case 'w' -> keysPressed[CameraKey.W.ordinal()] = value;
            case 'a' -> keysPressed[CameraKey.A.ordinal()] = value;
            case 's' -> keysPressed[CameraKey.S.ordinal()] = value;
            case 'd' -> keysPressed[CameraKey.D.ordinal()] = value;
            case ' ' -> keysPressed[CameraKey.SPACE.ordinal()] = value;
            case 'q' -> keysPressed[CameraKey.Q.ordinal()] = value;
        }

    }

    public void startRotation() {
        xOffset = p.mouseX;
        yOffset = p.mouseY;
    }

    public void rotate() {
        float xMovement = -(p.mouseX - xOffset) * aVelocity;// positive = right, negative = left
        float yMovement = (yOffset - p.mouseY) * aVelocity;// positive = up, negative = down

        // X movement implies y-axis rotation
        // Positive xMovement implies positive rotation
        rotation.y = (rotation.y + xMovement < p.PI) ? (rotation.y + xMovement > -p.PI) ? (rotation.y + xMovement) : p.TWO_PI - (rotation.y + xMovement) : (rotation.y + xMovement) - p.TWO_PI;

        // Y movement implies x-axis rotation
        // Positive yMovement implies positive rotation
        // should be between -PI/2 and PI/2
        rotation.x = PApplet.constrain(rotation.x + yMovement, -p.HALF_PI, p.HALF_PI);

        xOffset = p.mouseX;
        yOffset = p.mouseY;

        System.out.println("x: " + rotation.x + ", y: " + rotation.y);
    }

    public void updateCamera() {
//        Update position

        PVector fordwareDirection;
        fordwareDirection = (new PVector(0, 1)).rotate(-rotation.y).mult(velocity);

        if (keysPressed[CameraKey.W.ordinal()]) {//Move Left
            position.z += fordwareDirection.y;
            position.x += fordwareDirection.x;
        }
        if (keysPressed[CameraKey.S.ordinal()]) {//Move Right
            position.z -= fordwareDirection.y;
            position.x -= fordwareDirection.x;
        }

        fordwareDirection.rotate(-p.HALF_PI);
        if (keysPressed[CameraKey.A.ordinal()]) {//Move forward
            position.z -= fordwareDirection.y;
            position.x -= fordwareDirection.x;
        }
        if (keysPressed[CameraKey.D.ordinal()]) {//Move backward
            position.z += fordwareDirection.y;
            position.x += fordwareDirection.x;
        }

        if (keysPressed[CameraKey.SPACE.ordinal()]) {//Move Up
            position.y += velocity;
        }
        if (keysPressed[CameraKey.Q.ordinal()]) {//Move Down
            position.y -= velocity;
        }

    }

    public void processCamera() {
        p.translate(p.width / 2f, p.height / 2f, CAMERA_Z_DISTANCE);

        //process Horizontal rotation
        p.rotateY(rotation.y);

        // Process vertical rotation
        PVector sight = new PVector(0, 1);

        sight = sight.rotate(rotation.y);
        sight.z = sight.y;
        sight.y = 0;

        PVector axis = sight.cross(new PVector(0, 1, 0)).normalize();

        p.rotate(-rotation.x, axis.x, axis.y, axis.z);

        // process tanslation
        p.translate(-position.x, position.y, position.z);
    }

}
