public class Entity {
    protected static int size;
    protected double row;
    protected double column;
    protected int value;

    public Entity(double row, double column) {
        this.column = column;
        this.row = row;
        this.value = (int) this.row * Entity.size + (int) this.column;
    }

    public Entity() {
        this(0.00, 0.00);
    }

    public static int getSize() {
        return size;
    }

    public static void setSize(int size) {
        Entity.size = size;
    }

    public double getColumn() {
        return column;
    }

    public void setColumn(double column) {
        this.column = column;
    }

    public int getValue() {
        return (int) row * size + (int) column;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public double getRow() {
        return row;
    }

    public void setRow(double row) {
        this.row = row;
    }
}
