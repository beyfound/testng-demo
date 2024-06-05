package org.example.listener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;


public class ExcelReporter implements IReporter {
    private int rownum = 0;
    private SXSSFWorkbook workbook;
    private Sheet sheet;

    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        excelExport(xmlSuites, suites, outputDirectory);
    }

    /** Export run result data to excel table */
    private void excelExport(List<XmlSuite> xml, List<ISuite> suites, String outdir) {
        for (ISuite suite : suites) {
            Map<String, ISuiteResult> r = suite.getResults();
            for (ISuiteResult r2 : r.values()) {
                ITestContext testContext = r2.getTestContext();
                IResultMap passedResultMap = testContext.getPassedTests();
                analyticalData(passedResultMap);
                IResultMap failedResultMap = testContext.getFailedTests();
                analyticalData(failedResultMap);
                IResultMap skippedResultMap = testContext.getSkippedTests();
                analyticalData(skippedResultMap);
                IResultMap failedConfigurationResultMap = testContext.getFailedConfigurations();
                analyticalData(failedConfigurationResultMap);
                IResultMap skippedConfigurationResultMap = testContext.getSkippedConfigurations();
                analyticalData(skippedConfigurationResultMap);
            }
        }
        saveData();
    }

    private void writeRowData(String... text) {
        if (rownum == 0) {
            workbook = new SXSSFWorkbook();
            sheet = workbook.createSheet();
            sheet.setColumnWidth(0, 256 * 20 + 184);
            sheet.setColumnWidth(1, 256 * 21 + 184);
            sheet.setColumnWidth(2, 256 * 6 + 184);
            sheet.setColumnWidth(3, 256 * 7 + 184);
            sheet.setColumnWidth(4, 256 * 80 + 184);
            Row row = sheet.createRow(rownum++);
            CellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            cellStyle.setAlignment(HorizontalAlignment.CENTER);
            CellUtil.createCell(row, 0, "Test Class", cellStyle);
            CellUtil.createCell(row, 1, "Test Method", cellStyle);
            CellUtil.createCell(row, 2, "Locale", cellStyle);
            CellUtil.createCell(row, 3, "Status", cellStyle);
            CellUtil.createCell(row, 4, "Exception", cellStyle);
        }
        Row row = sheet.createRow(rownum++);
        for (int i = 0; i < text.length; i++) {
            CellUtil.createCell(row, i, text[i]);
        }
    }

    /** Save data to excel file */
    private void saveData() {
        try {
            File folder = new File("test-output/excelExport");
            if (!folder.exists()) {
                folder.mkdirs();
            }

            File file = new File(folder, "sort-automation-test-excel-reporter.xlsx");
            if (!file.exists()) {
                file.createNewFile();
            }

            FileOutputStream out = new FileOutputStream(file);
            workbook.write(out);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Data analysis */
    private void analyticalData(IResultMap resultMap) {
        for (ITestResult testResult : resultMap.getAllResults()) {
            String testClassName = "";
            String testMethodName = "";
            String status = "";
            StringBuffer exceptionText = new StringBuffer("");
            try {

                testClassName = testResult.getTestClass().getName();
                testMethodName = testResult.getName() + "()";
                status = testResult.getStatus() == ITestResult.SUCCESS ? "Success" : "Failed";
                Throwable exception = testResult.getThrowable();
                if (exception != null) {
                    exceptionText.append(exception.getClass().getName() + " : " + exception.getMessage() + "\n");
                    for (StackTraceElement stackTraceElement : exception.getStackTrace()) {
                        exceptionText.append(stackTraceElement + "\n");
                    }
                }
                writeRowData(testClassName, testMethodName, "en_US", status, exceptionText.toString());
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
        }
    }
}