package direction;

public class DirectionW extends Direction {

    DirectionW() {
    }

    @Override
    char getDirection() {
        return 'W';
    }

    @Override
    public Direction turnLeft() {
        return Direction.newDirection('S');
    }

    @Override
    public Direction turnRight() {
        return Direction.newDirection('N');
    }
}
