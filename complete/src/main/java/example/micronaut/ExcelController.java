package example.micronaut;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.server.types.files.AttachedFile;

@Controller("/excel") // <1>
public class ExcelController {

    protected final BookRepository bookRepository;
    protected final BookExcelService bookExcelService;

    public ExcelController(BookRepository bookRepository,  // <2>
                           BookExcelService bookExcelService) {
        this.bookRepository = bookRepository;
        this.bookExcelService = bookExcelService;
    }

    @Get // <3>
    AttachedFile excel() { // <4>
        return bookExcelService.excelFileFromBooks(bookRepository.findAll());
    }
}
