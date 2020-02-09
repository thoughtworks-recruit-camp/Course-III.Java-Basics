package direction;

public class DirectionN extends Direction {

    DirectionN() {
    }

    @Override
    char getDirection() {
        return 'N';
    }

    @Override
    public Direction turnLeft() {
        return Direction.newDirection('W');
    }

    @Override
    public Direction turnRight() {
        return Direction.newDirection('E');
    }
}
