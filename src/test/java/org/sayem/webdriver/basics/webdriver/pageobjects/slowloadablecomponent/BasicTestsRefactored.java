package org.sayem.webdriver.basics.webdriver.pageobjects.slowloadablecomponent;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.sayem.webdriver.basics.webdriver.examples.Driver;
import org.sayem.webdriver.basics.webdriver.pageobjects.slowloadablecomponent.pages.BasicAjaxPageObject;
import org.sayem.webdriver.basics.webdriver.pageobjects.slowloadablecomponent.pages.ProcessedFormPage;

import static junit.framework.Assert.assertEquals;
import static org.sayem.webdriver.basics.webdriver.pageobjects.slowloadablecomponent.pages.BasicAjaxPageObject.Category;
import static org.sayem.webdriver.basics.webdriver.pageobjects.slowloadablecomponent.pages.BasicAjaxPageObject.Language;

public class BasicTestsRefactored {

    private WebDriver driver;
    private BasicAjaxPageObject basicAjaxPage;

    @Before
    public void setupTest() {

        driver = Driver.get();
        basicAjaxPage = new BasicAjaxPageObject(driver);
        basicAjaxPage.get();
    }

    @Test
    public void chooseToCodeInJavaOnTheServerFromCombosSyncOnAjaxBusyExample() {

        basicAjaxPage.selectCategory(Category.SERVER);
        basicAjaxPage.selectLanguage(Language.JAVA);
        basicAjaxPage.clickCodeInIt();

        ProcessedFormPage processedForm = new ProcessedFormPage(driver);
        processedForm.get();

        assertEquals("Expected Java code", Language.JAVA.value() + "", processedForm.getValueFor("language_id"));

    }

    @Test
    public void chooseToCodeInJavascriptOnTheWeb() {

        // workaround for the bug
        basicAjaxPage.selectCategory(Category.SERVER);
        basicAjaxPage.selectCategory(Category.WEB);

        basicAjaxPage.selectLanguage(Language.JAVASCRIPT);
        basicAjaxPage.clickCodeInIt();

        ProcessedFormPage processedForm = new ProcessedFormPage(driver);
        processedForm.get();

        //TODO: this is a known bug, when the page is first created it has JavaScript 1, but server call is JavaScript 0
        assertEquals("Expected JavaScript code", String.valueOf(Language.JAVASCRIPT.value()), processedForm.getValueFor("language_id"));
    }

    @Test
    public void chooseToCodeInCppOnDesktop() {

        basicAjaxPage.selectCategory(Category.DESKTOP);

        basicAjaxPage.selectLanguage(Language.DESKTOP_Cpp);
        basicAjaxPage.clickCodeInIt();

        ProcessedFormPage processedForm = new ProcessedFormPage(driver);
        processedForm.get();

        assertEquals("Expected Desktop CPP code", String.valueOf(Language.DESKTOP_Cpp.value()), processedForm.getValueFor("language_id"));
    }

}
