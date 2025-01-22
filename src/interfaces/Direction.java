package interfaces;

public enum Direction {
    NORTH(0, -1),
    SOUTH(0, 1),
    EAST(-1, 0),
    WEST(1, 0);

    private final int dx;
    private final int dy;

    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public int getX() {
        return dx;
    }

    public int getY() {
        return dy;
    }
}
