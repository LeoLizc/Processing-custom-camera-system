import processing.core.PApplet;
import processing.core.PVector;

public class Plane extends WorldObject{
    public Plane(PApplet parent, PVector position, PVector rotation) {
        super(parent, position, rotation);
    }

    public Plane(PApplet parent, PVector scale, PVector position, PVector rotation) {
        super(parent, scale, position, rotation);
    }

    @Override
    public void update() {}

    @Override
    void drawObject() {
        p.rotateX(p.HALF_PI);
        p.noStroke();
        p.fill(255);
        p.beginShape();
        p.vertex(-100, -100, 0, 0, 0);
        p.vertex(100, -100, 0, 100, 0);
        p.vertex(100, 100, 0, 100, 100);
        p.vertex(-100, 100, 0, 0, 100);
        p.endShape(p.CLOSE);
    }
}
