import processing.core.PApplet;
import processing.core.PVector;

public class Cube extends WorldObject {

    public PVector size;
    int color;

    public Cube(PApplet parent, PVector size, PVector position, PVector rotation, int color) {
        super(parent, position, rotation);
        this.size = size;
        this.color = color;
    }

    @Override
    public void update() {
    }

    @Override
    void drawObject() {
        p.stroke(color);
        p.noFill();
        p.box(size.x, size.y, size.z);
    }
}
