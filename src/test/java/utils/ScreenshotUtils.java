package utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtils {

    public static String takeScreenshot(
            WebDriver driver,
            String fileName) throws IOException {

        File source =
                ((TakesScreenshot) driver)
                        .getScreenshotAs(OutputType.FILE);

        String path =
                "screenshots/" + fileName + ".png";

        File destination = new File(path);

        FileUtils.copyFile(source, destination);

        return destination.getAbsolutePath();
    }
}