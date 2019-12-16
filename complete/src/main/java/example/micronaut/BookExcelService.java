package example.micronaut;

import java.util.List;
import io.micronaut.http.server.types.files.SystemFile;

public interface BookExcelService {
    String SHEET_NAME = "Books";
    String HEADER_ISBN = "Isbn";
    String HEADER_NAME = "Name";
    String HEADER_EXCEL_FILE_SUFIX = ".xlsx";
    String HEADER_EXCEL_FILE_PREFIX = "books";
    String HEADER_EXCEL_FILENAME = HEADER_EXCEL_FILE_PREFIX + HEADER_EXCEL_FILE_SUFIX;

    SystemFile excelFileFromBooks(List<Book> bookList); // <1>
}
