public class Robot implements Comparable<Robot>{
    int position;
    int health;
    char direction;
    int index;

    public Robot(int position, int health, char direction, int index) {
        this.position = position;
        this.health = health;
        this.direction = direction;
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public char getDirection() {
        return direction;
    }

    public void setDirection(char direction) {
        this.direction = direction;
    }

    @Override
    public String toString() {
        return "Robot{" +
                "position=" + position +
                ", health=" + health +
                ", direction=" + direction +
                '}';
    }

    @Override
    public int compareTo(Robot o) {
        return this.position - o.position;
    }
}
