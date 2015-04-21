package swip.le;

public class CellLocation {
    public final int rowNumber, columnNumber;

    public CellLocation(int rowNumber, int columnNumber) {
        this.rowNumber = rowNumber;
        this.columnNumber = columnNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CellLocation that = (CellLocation) o;

        return columnNumber == that.columnNumber && rowNumber == that.rowNumber;
    }
}
