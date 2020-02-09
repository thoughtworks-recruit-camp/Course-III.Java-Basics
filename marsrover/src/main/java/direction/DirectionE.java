package direction;

public class DirectionE extends Direction {

    DirectionE() {
    }

    @Override
    char getDirection() {
        return 'E';
    }

    @Override
    public Direction turnLeft() {
        return Direction.newDirection('N');
    }

    @Override
    public Direction turnRight() {
        return Direction.newDirection('S');
    }
}
