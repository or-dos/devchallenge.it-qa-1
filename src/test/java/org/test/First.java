package org.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

/**
 * Created by Billy_the_frog on 28.05.2019.
 */
public class First {
    public ChromeDriver driver;

    @Before
    public void run()
    {
        System.setProperty("webdriver.chrome.driver", "e:\\chromedriver32.exe");
        driver = new ChromeDriver();
    }

    @Test

    public void newHotelPageDisplayed() //Verify that Register new Hotel page is displayed when user selects Article-&gt;New-&gt;Hotel
    {
        driver.get("http://localhost:8080/article/faces/welcome.xhtml");
        WebElement article = driver.findElementByXPath("//*[@id=\"header_form:j_idt9\"]/ul/li[1]/a");
        WebElement newItem = driver.findElementByXPath("//*[@id=\"header_form:j_idt9\"]/ul/li[1]/ul/li[1]");
        Actions mouse = new Actions(driver);
        mouse.moveToElement(article).build().perform();
        mouse.moveToElement(newItem).build().perform();
        WebElement hotel = driver.findElementByXPath("//*[@id=\"header_form:j_idt9\"]/ul/li[1]/ul/li[1]/ul/li[1]");
        mouse.click(hotel).build().perform();
        String title = driver.findElementByXPath("//*[@id=\"title\"]").getText();
        Assert.assertTrue(title.equals("Register new Hotel"));
    }

    @Test

    public void dataSectionOnRegisterNewHotel() //Verify that Data section is displayed on Register new Hotel
    {
        driver.get("http://localhost:8080/article/faces/hotel.xhtml");
        Assert.assertTrue("No Data section!!!", !driver.findElements(By.id("add_hotel")).isEmpty());
    }

    @Test
    public void saveButtonIsDisplayedOnRegisterHotel() //Verify that save button is displayed on Register new Hotel
    {
        driver.get("http://localhost:8080/article/faces/hotel.xhtml");
        Assert.assertTrue("No Save button!!!", !driver.findElements(By.id("add_hotel:j_idt62")).isEmpty());
    }

    @Test
    public void nameFieldDisplayedOnRegistrationHotel() //Verify that Name field is displayed in Data section of Register new Hotel page
    {
        driver.get("http://localhost:8080/article/faces/hotel.xhtml");
        Assert.assertTrue("No Name field!!!", !driver.findElements(By.id("add_hotel:name")).isEmpty());
    }


    @Test
    public void nameFieldAsterisk() //Verify that Name field is marked with asterisk
    {
        driver.get("http://localhost:8080/article/faces/hotel.xhtml");
        String asterisk = driver.findElementByXPath("//*[@id=\"add_hotel:j_idt42\"]/span").getText();
        String ast = "*";
        System.out.println(asterisk);
        Assert.assertEquals(ast, asterisk);
    }

    @Test
    public void nameFieldIsEditableOnRegistrationHotel() //Verify that Name field is editable
    {
        Actions cursor = new Actions(driver);

        driver.get("http://localhost:8080/article/faces/hotel.xhtml");
        WebElement nameField = driver.findElementById("add_hotel:name");
        cursor.click(nameField).build().perform();
        cursor.sendKeys("Grand Hotel Budapest").build().perform();

    }

    @Test
    public void nameFieldIsAllowsAlfaNumeric() //Verify that Name field allows to input alphanumeric characters
    {
        Actions cursor = new Actions(driver);

        driver.get("http://localhost:8080/article/faces/hotel.xhtml");
        WebElement nameField = driver.findElementById("add_hotel:name");
        cursor.click(nameField).build().perform();
        cursor.sendKeys("Aqsd 0987654321").build().perform();

    }

    @Test
    public void nameFieldNotSavedIfEmptyErrorDisplayed() //Verify that it is not possible to save the empty Name field and a meaningful error message is displayed
    {
        Actions cursor = new Actions(driver);

        driver.get("http://localhost:8080/article/faces/hotel.xhtml");
        WebElement nameField = driver.findElementById("add_hotel:name");
        WebElement saveButt = driver.findElementById("add_hotel:j_idt62");
        cursor.click(nameField).build().perform();
        cursor.sendKeys("").build().perform();
        cursor.click(saveButt).build().perform();
        Assert.assertTrue(!driver.findElements(By.id("add_hotel:j_idt43")).isEmpty());
    }

    @Test
    public void nameFieldPossibleToSaveValid() //Verify that it is possible to save the valid name field
    {
        Actions cursor = new Actions(driver);
        driver.get("http://localhost:8080/article/faces/hotel.xhtml");
        WebElement nameField = driver.findElementById("add_hotel:name");
        WebElement saveButt = driver.findElementById("add_hotel:j_idt62");
        cursor.click(nameField).build().perform();
        cursor.sendKeys("Grand Hotel Budapest 090").build().perform();
        cursor.click(saveButt).build().perform();
        boolean b = driver.findElementById("add_hotel:j_idt43").isDisplayed();
        Assert.assertFalse(b);
    }


    @Test
    public void globalRatingFieldDisplayed() //Verify that Global Rating field is displayed in Data section of Register new Hotel page
    {
        driver.get("http://localhost:8080/article/faces/hotel.xhtml");
        Assert.assertTrue("No Global Rating field!!!", !driver.findElements(By.id("add_hotel:rate")).isEmpty());
    }

    @Test
    public void dateOfConstructionIsDisplayed() //Verify that Date of Construction field is displayed in Data section of Register new Hotel page
    {
        driver.get("http://localhost:8080/article/faces/hotel.xhtml");
        Assert.assertTrue("No Date of Construction field!!!", !driver.findElements(By.id("add_hotel:dateOfConstruction_input")).isEmpty());
    }

    @Test
    public void dateOfConstructionFieldAsterisk() //Verify that Date of Construction field is marked with asterisk
    {
        driver.get("http://localhost:8080/article/faces/hotel.xhtml");
        String asterisk = driver.findElementByXPath("//*[@id=\"add_hotel:j_idt46\"]/span").getText();
        String ast = "*";
        System.out.println(asterisk);
        Assert.assertEquals(ast, asterisk);
    }
        @Test
        public void dateOfConstFieldIsEditable() //Verify that Date of Construction is editable
        {
            Actions cursor = new Actions(driver);

            driver.get("http://localhost:8080/article/faces/hotel.xhtml");
            WebElement nameField = driver.findElementById("add_hotel:dateOfConstruction_input");
            cursor.click(nameField).build().perform();
            cursor.sendKeys("23562346").build().perform();
        }


        @Test
        public void dateOfConstFieldIsAllowDateFormat() //Verify Date of Construction field allows to input in date format
        {
            Actions cursor = new Actions(driver);

            driver.get("http://localhost:8080/article/faces/hotel.xhtml");
            WebElement nameField = driver.findElementById("add_hotel:dateOfConstruction_input");
            cursor.click(nameField).build().perform();
            cursor.sendKeys("22.11.16").build().perform();
        }

        @Test
        public void doCFieldNotSaveIncorrectFormat() //Verify that it is not possible to save incorrect date format value Date of Construction field and a meaningful error message is displayed
        {
            Actions cursor = new Actions(driver);

            driver.get("http://localhost:8080/article/faces/hotel.xhtml");
            WebElement nameField = driver.findElementById("add_hotel:dateOfConstruction_input");
            WebElement saveButt = driver.findElementById("add_hotel:j_idt62");
            cursor.click(nameField).build().perform();
            cursor.sendKeys("12.31.1982").build().perform();
            cursor.click(saveButt).build().perform();
            Assert.assertTrue(!driver.findElements(By.id("add_hotel:j_idt47")).isEmpty());
        }


        @Test
        public void doCFieldNotSaveEmpty() //Verify that it is not possible to save the empty Date of Construction field and a meaningful error message is displayedVerify that it is not possible to save incorrect date format value Date of Construction field and a meaningful error message is displayed
        {
            Actions cursor = new Actions(driver);

            driver.get("http://localhost:8080/article/faces/hotel.xhtml");
            WebElement nameField = driver.findElementById("add_hotel:dateOfConstruction_input");
            WebElement saveButt = driver.findElementById("add_hotel:j_idt62");
            cursor.click(nameField).build().perform();
            cursor.sendKeys("").build().perform();
            cursor.click(saveButt).build().perform();
            Assert.assertTrue(!driver.findElements(By.id("add_hotel:j_idt47")).isEmpty());
        }


        @Test
        public void doCFieldPossibleToSaveValidDate() //Verify that it is possible to save valid Date of Construction field
        {
            Actions cursor = new Actions(driver);
            driver.get("http://localhost:8080/article/faces/hotel.xhtml");
            WebElement nameField = driver.findElementById("add_hotel:dateOfConstruction_input");
            WebElement saveButt = driver.findElementById("add_hotel:j_idt62");
            cursor.click(nameField).build().perform();
            cursor.sendKeys("31.12.82").build().perform();
            cursor.click(saveButt).build().perform();
            boolean b = driver.findElementById("add_hotel:j_idt47").isDisplayed();
            Assert.assertFalse(b);
        }


        @Test

        public void countryDropDownDisplayed() //Verify that Country field is displayed in Data section of Register new Hotel page
        {
            driver.get("http://localhost:8080/article/faces/hotel.xhtml");
            Assert.assertTrue("No Country menu!!!", !driver.findElements(By.id("add_hotel:country")).isEmpty());
        }


        @Test
        public void countryMenuAsterisk() //Verify that Country fields is marked with asterisk
        {
            driver.get("http://localhost:8080/article/faces/hotel.xhtml");
            String asterisk = driver.findElementByXPath("//*[@id=\"add_hotel:j_idt48\"]/span").getText();
            String ast = "*";
            System.out.println(asterisk);
            Assert.assertEquals(ast, asterisk);
        }



        @Test
        public void countryFieldNotSaveEmpty() //Verify that it is not possible to save the empty Country field and a meaningful error message is displayedVerify that it is not possible to save incorrect date format value Date of Construction field and a meaningful error message is displayed
        {
            Actions cursor = new Actions(driver);

            driver.get("http://localhost:8080/article/faces/hotel.xhtml");
            WebElement saveButt = driver.findElementById("add_hotel:j_idt62");
            cursor.click(saveButt).build().perform();
            Assert.assertTrue(!driver.findElements(By.id("add_hotel:j_idt51")).isEmpty());
        }




        @Test
        public void shortDescrIsDisplayed() //Verify that Short Description field is displayed in Data section of Register new Hotel
        {
            driver.get("http://localhost:8080/article/faces/hotel.xhtml");
            Assert.assertTrue("No Short Description!!!", !driver.findElements(By.id("add_hotel:short_description")).isEmpty());
        }


        @Test
        public void shortDescrFieldAsterisk() //Verify that Short Description field is marked with asterisk
        {
            driver.get("http://localhost:8080/article/faces/hotel.xhtml");
            String asterisk = driver.findElementByXPath("//*[@id=\"add_hotel:j_idt56\"]/span").getText();
            String ast = "*";
            System.out.println(asterisk);
            Assert.assertEquals(ast, asterisk);
        }

        @Test
        public void shortDescrFieldIsEditable() //Verify that Short Description field is editable
        {
            Actions cursor = new Actions(driver);

            driver.get("http://localhost:8080/article/faces/hotel.xhtml");
            WebElement nameField = driver.findElementById("add_hotel:short_description");
            cursor.click(nameField).build().perform();
            cursor.sendKeys("Very good hotel 666.").build().perform();
        }




        @Test
        public void shortDescrIsAllowsAlfaNumeric() //Verify that Short Description field allows to input alphanumeric characters
        {
            Actions cursor = new Actions(driver);

            driver.get("http://localhost:8080/article/faces/hotel.xhtml");
            WebElement nameField = driver.findElementById("add_hotel:short_description");
            WebElement saveButt = driver.findElementById("add_hotel:j_idt62");
            cursor.click(nameField).build().perform();
            cursor.sendKeys("Aqsd 0987654321").build().perform();
            cursor.click(saveButt).build().perform();
            Assert.assertTrue(!driver.findElements(By.id("add_hotel:j_idt57")).isEmpty());

        }


        @Test
        public void shortDescrNotSaveEmpty() //Verify that it is not possible to save the empty Short Description field and a meaningful error message is displayed
        {
            Actions cursor = new Actions(driver);

            driver.get("http://localhost:8080/article/faces/hotel.xhtml");
            WebElement nameField = driver.findElementById("add_hotel:short_description");
            WebElement saveButt = driver.findElementById("add_hotel:j_idt62");
            cursor.click(nameField).build().perform();
            cursor.sendKeys("").build().perform();
            cursor.click(saveButt).build().perform();
            Assert.assertTrue(!driver.findElements(By.id("add_hotel:j_idt57")).isEmpty());
        }

        @Test
        public void shortDescrFieldIsSavedValidData() // Verify that it is possible to save the valid Short Description field
        {
            Actions cursor = new Actions(driver);

            driver.get("http://localhost:8080/article/faces/hotel.xhtml");
            WebElement nameField = driver.findElementById("add_hotel:short_description");
            cursor.click(nameField).build().perform();
            cursor.sendKeys("Very good hotel 666.").build().perform();
        }







        @After
    public void end()
        {
        //driver.quit();
    }
}


