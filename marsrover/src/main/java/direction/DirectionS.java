package direction;

class DirectionS extends Direction{

    DirectionS() {
    }

    @Override
    char getDirection() {
        return 'S';
    }

    @Override
    public Direction turnLeft() {
        return Direction.newDirection('E');
    }

    @Override
    public Direction turnRight() {
        return Direction.newDirection('W');
    }
}
