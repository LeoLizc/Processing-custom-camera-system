import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

public class Main extends PApplet {
    Camera camera;

    ArrayList<WorldObject> objects;

    public static void main(String[] args) {
        PApplet.main("Main");
    }

    public void settings() {
        size(400, 400, P3D);
    }

    public void setup() {
//        size(400,400, PConstants.P3D);
        camera = new Camera(this);
        objects = new ArrayList<>();
        PVector cSize, position, rotation;
        cSize = new PVector(160, 160, 160);
        position = cSize.copy().set(232, 192, -100 - Camera.CAMERA_Z_DISTANCE);
        rotation = position.copy().set(0, 0, 0);

        objects.add(new Cube(this, cSize, position, rotation, color(0)));

        position = position.set(232 + 200, 192, -100 - Camera.CAMERA_Z_DISTANCE);
        objects.add(new Cube(this, cSize, position, rotation, color(0, 0, 255)));

        position = position.set(232 + 400, 192, -100 - Camera.CAMERA_Z_DISTANCE);
        rotation = rotation.set(0, 0, 0);
        objects.add(new Cube(this, cSize, position, rotation, color(255, 0, 0)));

        position = position.set(232 + 600, 192, -100 - Camera.CAMERA_Z_DISTANCE);
        rotation = rotation.set(0, 0, 0);
        objects.add(new Cube(this, cSize, position, rotation, color(0, 255, 0)));

        position = position.set(232 + 800, 192, -100 - Camera.CAMERA_Z_DISTANCE);
        rotation = rotation.set(0, 0, 0);
        objects.add(new Cube(this, cSize, position, rotation, color(0, 255, 0)));

        position = position.set(232 + 800, 192, 0);
        rotation = rotation.set(0, 0, 0);
        objects.add(new Cube(this, cSize, position, rotation, color(0, 0, 0)) {
            @Override
            void rotate() {
                p.rotateX(rotation.x);
                p.rotateY(rotation.y);
                p.rotateZ(rotation.z);
            }
        });
    }

    public void draw() {
        update();
        background(240, 240, 240);
        camera.processCamera();
        render();
    }

    void update() {
        camera.updateCamera();
        for (WorldObject element : objects) {
            element.update();
        }
    }

    void render() {
        for (WorldObject element : objects) {
            element.render();
        }
    }

    public void mouseDragged() {
        camera.rotate();
    }

    public void mousePressed() {
        camera.startRotation();
    }

    public void keyPressed() {
        camera.startMove(key);
    }

    public void keyReleased() {
        camera.stopMove(key);
    }

}
