package example.micronaut

import builders.dsl.spreadsheet.query.api.SpreadsheetCriteria
import builders.dsl.spreadsheet.query.api.SpreadsheetCriteriaResult
import builders.dsl.spreadsheet.query.poi.PoiSpreadsheetCriteria
import geb.spock.GebSpec
import io.micronaut.context.ApplicationContext
import io.micronaut.runtime.server.EmbeddedServer
import spock.lang.IgnoreIf
import spock.util.concurrent.PollingConditions

class DownloadExcelSpec extends GebSpec {

    EmbeddedServer embeddedServer = ApplicationContext.run(EmbeddedServer, [:]) // <1>

    @IgnoreIf({ !sys['download.folder'] || sys['geb.env'] != 'chrome' })
    def "books can be downloaded as an excel file"() {
        given:
        PollingConditions conditions = new PollingConditions(timeout: 5)
        browser.baseUrl = "http://localhost:${embeddedServer.port}" // <2>

        when:
        browser.to HomePage

        then:
        browser.at HomePage

        when: 'clicking excel button'
        String expectedPath = System.getProperty('download.folder') + "/" + BookExcelService.HEADER_EXCEL_FILENAME
        File outputFile = new File(expectedPath)
        browser.page(HomePage).downloadExcel()

        then: 'an excel file is downloaded'
        conditions.eventually { outputFile.exists() }

        when: 'if we search for a row with a particular value (Building Microservices)'
        SpreadsheetCriteria query = PoiSpreadsheetCriteria.FACTORY.forFile(outputFile)
        SpreadsheetCriteriaResult result = query.query( { workbookCriterion ->
                workbookCriterion.sheet(BookExcelService.SHEET_NAME, { sheetCriterion ->
                        sheetCriterion.row({ rowCriterion ->
                                rowCriterion.cell({ cellCriterion ->
                                        cellCriterion.value('Building Microservices')
                                })
                        })
                    })
        })
        then: 'a row is found'
        result.cells.size() == 1

        cleanup:
        outputFile?.delete()
    }
}
