package com.bicycle.racing.service;

import com.bicycle.racing.model.EventResult;
import com.bicycle.racing.model.EventResultPage;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Service
public class DownloadFileService {

    private static final String EXCEL = "excel";
    private static final String PDF = "pdf";
    private static final String APPLICATION_PDF = "application/pdf";

    private final EventResultService eventResultService;

    @Autowired
    public DownloadFileService(EventResultService eventResultService) {
        this.eventResultService = eventResultService;
    }

    public void downloadFile(Integer eventId,
                             String type,
                             HttpServletResponse response) throws IOException {

        EventResultPage resultPage = eventResultService.getListResultByEventId(eventId);
        StringBuilder builder = builderFileName(resultPage);

        if (type.equals(PDF)) {
            downloadPdf(response, resultPage, builder);
        }
        if (type.equals(EXCEL)) {
            downloadExcel(response, resultPage, builder);
        }
    }

    private StringBuilder builderFileName(EventResultPage resultPage) {
        return new StringBuilder()
                .append(resultPage.getEvent().getTitle())
                .append(".")
                .append(resultPage.getEvent().getType())
                .append(".")
                .append(resultPage.getEvent().getDistance())
                .append("km");
    }

    private void downloadPdf(HttpServletResponse response,
                             EventResultPage resultPage,
                             StringBuilder builder) throws IOException {
        String fileName;
        fileName = builder.append(".pdf").toString();
        createPdf(resultPage, fileName);

        File file = new File(fileName);
        InputStream in = new FileInputStream(file);
        response.setContentType(APPLICATION_PDF);
        response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
        response.setHeader("Content-Length", String.valueOf(file.length()));
        FileCopyUtils.copy(in, response.getOutputStream());
        file.delete();
    }

    private void createPdf(EventResultPage resultPage, String fileName) {
        Document document = new Document(PageSize.A4, 10, 10, 10, 10);

        try {
            PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();
            PdfPTable table = new PdfPTable(4);
            table.setHeaderRows(1);
            addTableHeader(table);
            addRows(table, resultPage.getEventResults());
            document.add(table);
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
        document.close();
    }

    private void addRows(PdfPTable table, List<EventResult> eventResults) {
        for (EventResult eventResult : eventResults) {
            table.addCell(String.valueOf(eventResult.getId()));
            table.addCell(eventResult.getUsername());
            table.addCell(String.valueOf(eventResult.getTime()));
            table.addCell(String.valueOf(eventResult.isStatus()));
        }
    }

    private void addTableHeader(PdfPTable table) {
        Stream.of("#", "user", "Time", "Status")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(1);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }

    private void downloadExcel(HttpServletResponse response,
                               EventResultPage resultPage,
                               StringBuilder builder) throws IOException {

        String fileName = builder.append(".xlsx").toString();

        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = setParamSheetWorkbook(resultPage, workbook);
        CellStyle headerStyle = setStyleWorkbook(workbook);
        setHeaderWorkbook(sheet, headerStyle);

        setDataToSheet(resultPage, sheet);

        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment; filename=sample-spreadsheet.xlsx");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        workbook.write(response.getOutputStream());
    }

    private void setDataToSheet(EventResultPage resultPage, Sheet sheet) {
        for (int rowIndex = 0; rowIndex < resultPage.getEventResults().size(); rowIndex++) {
            EventResult eventResult = resultPage.getEventResults().get(rowIndex);
            Row row = sheet.createRow(rowIndex + 1);

            Cell cell = row.createCell(0);
            cell.setCellValue(String.valueOf(eventResult.getId()));

            cell = row.createCell(1);
            cell.setCellValue(eventResult.getUsername());

            cell = row.createCell(2);
            cell.setCellValue(String.valueOf(eventResult.getTime()));

            cell = row.createCell(3);
            cell.setCellValue(String.valueOf(eventResult.isStatus()));
        }
    }

    private Sheet setParamSheetWorkbook(EventResultPage resultPage, XSSFWorkbook workbook) {
        Sheet sheet = workbook.createSheet(resultPage.getEvent().getTitle());
        sheet.setColumnWidth(0, 6000);
        sheet.setColumnWidth(1, 4000);

        return sheet;
    }

    private void setHeaderWorkbook(Sheet sheet, CellStyle headerStyle) {
        List<String> asList = Arrays.asList("#", "user", "Time", "Status");
        Row header = sheet.createRow(0);

        for (int i = 0; i < asList.size(); i++) {
            Cell headerCell = header.createCell(i);
            headerCell.setCellValue(asList.get(i));
            headerCell.setCellStyle(headerStyle);
        }
    }

    private CellStyle setStyleWorkbook(XSSFWorkbook workbook) {
        CellStyle headerStyle = setFillCellHeader(workbook);
        setFontHeader(workbook, headerStyle);

        return headerStyle;
    }

    private CellStyle setFillCellHeader(XSSFWorkbook workbook) {
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        return headerStyle;
    }

    private void setFontHeader(XSSFWorkbook workbook, CellStyle headerStyle) {
        XSSFFont font = workbook.createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 12);
        font.setBold(true);
        headerStyle.setFont(font);
    }
}
