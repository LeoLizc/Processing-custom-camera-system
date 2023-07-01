import processing.core.PApplet;
import processing.core.PVector;

public abstract class WorldObject {

    PApplet p;
    PVector position;
    PVector rotation;

    public WorldObject(PApplet parent, PVector position, PVector rotation){
        p = parent;
        this.position = position.copy();
        this.rotation = rotation.copy();
    }

    public void render(){
        p.pushMatrix();
        p.translate(position.x, position.y, position.z);
        rotate();
        drawObject();
        p.popMatrix();
    }

    void rotate(){
        p.rotateY(rotation.y);
        p.rotateX(rotation.x);
        p.rotateZ(rotation.z);
    }

    public abstract void update();

    abstract void drawObject();
}
