import processing.core.PApplet;
import processing.core.PVector;

public abstract class WorldObject {

    PApplet p;
    PVector position;
    PVector rotation;
    PVector scale;

    public WorldObject(PApplet parent, PVector position, PVector rotation) {
        p = parent;
        this.position = position.copy();
        this.rotation = rotation.copy();
        scale = new PVector(1, 1, 1);
    }

    public WorldObject(PApplet parent, PVector scale, PVector position, PVector rotation) {
        p = parent;
        this.position = position.copy();
        this.rotation = rotation.copy();
        this.scale = scale;
    }

    public void render() {
        p.pushMatrix();
        p.translate(position.x, position.y, position.z);
        rotate();
        p.scale(scale.x, scale.y, scale.z);
        drawObject();
        p.popMatrix();
    }

    void rotate() {
        p.rotateY(rotation.y);
        p.rotateX(rotation.x);
        p.rotateZ(rotation.z);
    }

    public abstract void update();

    abstract void drawObject();
}
