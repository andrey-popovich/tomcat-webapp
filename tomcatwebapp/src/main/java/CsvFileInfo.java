import java.util.List;
import java.util.Map;

public class CsvFileInfo {

    private String name;
    private List<String> columns;
    private long numberOfRows;
    private Map<String, Long> emptyValuesInColumn;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getColumns() {
        return columns;
    }

    public void setColumns(List<String> columns) {
        this.columns = columns;
    }

    public long getNumberOfRows() {
        return numberOfRows;
    }

    public void setNumberOfRows(long numberOfRows) {
        this.numberOfRows = numberOfRows;
    }

    public Map<String, Long> getEmptyValuesInColumn() {
        return emptyValuesInColumn;
    }

    public void setEmptyValuesInColumn(Map<String, Long> emptyValuesInColumn) {
        this.emptyValuesInColumn = emptyValuesInColumn;
    }

    @Override
    public String toString() {
        return "CsvFileInfo{" +
                "name='" + name + '\'' +
                ", columns=" + columns +
                ", numberOfRows=" + numberOfRows +
                ", emptyValuesInColumn=" + emptyValuesInColumn +
                '}';
    }
}
