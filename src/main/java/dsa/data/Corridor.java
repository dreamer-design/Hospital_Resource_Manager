package dsa.data;

/**
 * EDGE
 * @param length
 * @param target
 */
public class Corridor {
    float length;
    Department target;

    public Corridor(float length, Department target) {
        this.length = length;
        this.target = target;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public Department getTarget() {
        return target;
    }

    public void setTarget(Department target) {
        this.target = target;
    }
    
    
    
}
