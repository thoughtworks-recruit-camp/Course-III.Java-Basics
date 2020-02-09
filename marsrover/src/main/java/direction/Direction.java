package direction;

public abstract class Direction implements Turning {
    abstract char getDirection();

    public static Direction newDirection(char direction) {
        switch (direction) {
            case 'N':
                return new DirectionN();
            case 'E':
                return new DirectionE();
            case 'S':
                return new DirectionS();
            case 'W':
                return new DirectionW();
            default:
                throw new IllegalArgumentException();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Direction direction1 = (Direction) o;

        return this.getDirection() == direction1.getDirection();
    }

    @Override
    public int hashCode() {
        return getDirection();
    }

    public String toString() {
        return "Direction{direction=" + this.getDirection() + '}';
    }
}
