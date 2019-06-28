import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@WebServlet(name = "CsvServlet", urlPatterns = {"/csv"})
public class CsvServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        File jsp = new File(req.getSession().getServletContext().getRealPath(req.getServletPath()));
        File dir = jsp.getParentFile();
        File[] list = dir.listFiles();

        List<CsvFileInfo> fileInfoList = new ArrayList<>();
        if (list != null) {
            for (File file : list) {
                if (file.getName().endsWith(".csv")) {
                    fileInfoList.add(getCsvFileInfo(file));
                }
            }
        }

        req.setAttribute("fileInfoList", fileInfoList);
        req.getRequestDispatcher("/csv.jsp").forward(req, resp);
    }

    private CsvFileInfo getCsvFileInfo(File file) throws IOException {
        CsvFileInfo csvFileInfo = new CsvFileInfo();
        csvFileInfo.setName(file.getName());

        LinkedList<LinkedList<String>> data = readScvFile(file);
        if (data != null && !data.isEmpty()) {
            List<String> headers = data.get(0);
            csvFileInfo.setColumns(headers);
            csvFileInfo.setNumberOfRows(data.size() - 1);

            data.removeFirst();
            csvFileInfo.setEmptyValuesInColumn(countRowsInColumn(headers, data));
        } else {
            csvFileInfo.setColumns(new ArrayList<>());
            csvFileInfo.setNumberOfRows(0);
            csvFileInfo.setEmptyValuesInColumn(new HashMap<>(0));
        }

        return csvFileInfo;
    }

    private LinkedList<LinkedList<String>> readScvFile(File file) throws IOException {
        LinkedList<LinkedList<String>> records = new LinkedList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                records.add(new LinkedList<>(Arrays.asList(values)));
            }
        }
        return records;
    }


    private Map<String, Long> countRowsInColumn(List<String> headers, LinkedList<LinkedList<String>> data) {
        Map<String, Long> rowsInColumn = new LinkedHashMap<>();

        for (int h = 0; h < headers.size(); h++) {
            String header = headers.get(h);
            long empty = 0;
            for (LinkedList<String> row : data) {
                String cell = row.get(h);
                if (cell == null || cell.isEmpty()) {
                    empty++;
                }
            }
            rowsInColumn.put(header, empty);
        }

        return rowsInColumn;
    }
}
