package example.micronaut;

import java.util.List;
import io.micronaut.http.server.types.files.SystemFile;

public interface BookExcelService {
    static final String SHEET_NAME = "Books";
    static final String HEADER_ISBN = "Isbn";
    static final String HEADER_NAME = "Name";
    static final String HEADER_EXCEL_FILE_SUFIX = ".xlsx";
    static final String HEADER_EXCEL_FILE_PREFIX = "books";
    static final String HEADER_EXCEL_FILENAME = HEADER_EXCEL_FILE_PREFIX + HEADER_EXCEL_FILE_SUFIX;

    SystemFile excelFileFromBooks(List<Book> bookList); // <1>
}
