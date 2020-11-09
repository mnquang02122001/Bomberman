public class Character extends Entity {
    public void moveL(Map map, double x) {
        if ((column - 1) > 0 && map.check[new Entity(row, column - 1).getValue()] == 0)
            column -= x;
    }

    public void moveR(Map map, double x) {
        if ((column + 1) < size && map.check[new Entity(row, column + 1).getValue()] == 0)
            column += x;
    }

    public void moveU(Map map, double x) {
        if ((row - 1) > 0 && map.check[new Entity(row - 1, column).getValue()] == 0)
            row -= x;
    }

    public void moveD(Map map, double x) {
        if ((row + 1) < size && map.check[new Entity(row + 1, column).getValue()] == 0)
            row += x;
    }
}
