package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import utils.EmailUtils;
import utils.ExcelUtils;

public class SendEmailTest {

    @Test
    public void sendEmailsFromExcel() throws Exception {

        // Excel file
        String excelPath =
                "C:\\Users\\krish\\Downloads\\HR_Recruiter_Emails.xlsx";

        // Resume file
        String resumePath =
                "C:\\Users\\krish\\Downloads\\Krishnareddy_T_Resume.pdf";

        // Load Excel
        ExcelUtils.loadExcel(
                excelPath,
                "Emails"
        );

        // Get row count
        int rowCount =
                ExcelUtils.getRowCount();

        System.out.println(
                "Total recipients: " + (rowCount - 1)
        );

        Assert.assertTrue(
                rowCount > 1,
                "No email data found in Excel."
        );

        // Row 0 = Header
        for (int row = 1; row < rowCount; row++) {

            String email =
                    ExcelUtils.getCellData(row, 0).trim();

            String subject =
                    ExcelUtils.getCellData(row, 1).trim();

            String message =
                    ExcelUtils.getCellData(row, 2).trim();

            if (email.isEmpty()) {
                continue;
            }

            System.out.println(
                    "Sending email to: " + email
            );

            EmailUtils.sendEmail(
                    email,
                    subject,
                    message,
                    resumePath
            );
        }

        // Close Excel
        ExcelUtils.closeExcel();

        System.out.println(
                "All emails processed successfully."
        );
    }
}