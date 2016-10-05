package swb.ch02locatingelements;

public class CellLocation {
    private final int rowNumber;
    private final int columnNumber;

    public CellLocation(int rowNumber, int columnNumber) {
        this.rowNumber = rowNumber;
        this.columnNumber = columnNumber;
    }

    @Override
    public int hashCode() {
        int result = rowNumber;
        result = 31 * result + columnNumber;
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CellLocation that = (CellLocation) o;

        return columnNumber == that.columnNumber && rowNumber == that.rowNumber;
    }
}
