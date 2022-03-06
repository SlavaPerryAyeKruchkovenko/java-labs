package lab5.models;

public class Area {
    private final Field[] fields = new Field[4];

    public Field getField(int index) {
        if(index >= 0 && index < 4)
            return this.fields[index];
        else
            throw new IndexOutOfBoundsException("incorrect index of Field");
    }
}
