package dsa.data;

/**
 * EDGE
 * @param length
 * @param target
 */
public class Corridor implements Comparable<Corridor> {
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
    
    /**
     * Compare corridors by length for priority ordering
     * Shorter length = higher priority (shorter paths first)
     * @param other the corridor to compare to
     * @return negative if this has higher priority, positive if lower priority, 0 if equal
     */
    @Override
    public int compareTo(Corridor other) {
        if (other == null) throw new NullPointerException("Cannot compare with null corridor");
        
        // Compare by length - shorter length has higher priority
        return Float.compare(this.length, other.length);
    }
    
    @Override
    public String toString() {
        return "Corridor{length=" + length + ", target=" + target.getId() + "}";
    }
}
