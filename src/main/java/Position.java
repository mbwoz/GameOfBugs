public class Position {
    private int x;
    private int y;

    public Position() {
        this(0, 0);
    }

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Position setX(int x) {
        this.x = x;
        return this;
    }

    public Position sexY(int y) {
        this.y = y;
        return this;
    }

    public Position setXY(int x, int y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public Position add(Position toAdd) {
        return new Position(this.x + toAdd.x, this.y + toAdd.y);
    }

    public ArrayList<Position> getNeighbors() {
        ArrayList<Position> ans = new ArrayList<>();
        ans.add(new Position(x, y + 1));
        ans.add(new Position(x - 1, y + 1));
        ans.add(new Position(x - 1, y));
        ans.add(new Position(x, y - 1));
        ans.add(new Position(x + 1, y - 1));
        ans.add(new Position(x + 1, y));
        return ans;
    }

    public ArrayList<Position> getConnectedToBoth(Position second) {
        ArrayList<Position> firstNeighbors = getNeighbors();
        ArrayList<Position> secondNeighbors = second.getNeighbors();
        firstNeighbors.retainAll(secondNeighbors);
        return firstNeighbors;
    }

    public boolean equals(Object o) {
        if(o == null || !(o instanceof Position))
            return false;
        return x == ((Position)o).x && y == ((Position)o).y;
    }
}
