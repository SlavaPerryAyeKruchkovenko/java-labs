package lab5.models;

public class Area {
    private final Field[] fields;
    public final int FieldCount = 4;
    public Area() {
        this.fields = new Field[]{new Field(), new Field(), new Field(), new Field()};
    }

    public Field getField(int index) {
        if (index >= 0 && index < 4)
            return this.fields[index];
        else
            throw new IndexOutOfBoundsException("incorrect index of Field");
    }
}
