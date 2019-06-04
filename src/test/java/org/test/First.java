package org.test;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class First {
    public ChromeDriver driver;

    @Before
    public void run()
    {
        System.setProperty("webdriver.chrome.driver", "/opt/chromedriver");
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
        WebElement dateOfConstruct = driver.findElementById("add_hotel:dateOfConstruction_input");
        WebElement triggerCountry = driver.findElementByClassName("ui-selectonemenu-trigger");
        cursor.click(triggerCountry).build().perform();
        WebElement country = driver.findElementByXPath("//*[@id=\"add_hotel:country_panel\"]/div/ul/li[2]");
        cursor.click(country).build().perform();
        WebElement shortDescr = driver.findElementById("add_hotel:short_description");
        cursor.click(shortDescr).build().perform();
        cursor.sendKeys("Very good hotel 666 ()@.").build().perform();
        WebElement rate = driver.findElementByXPath("//*[@id=\"add_hotel:rate\"]/div[6]");
        cursor.click(rate).build().perform();
        WebElement triggerCity = driver.findElementByXPath("//*[@id=\"add_hotel:city\"]/div[3]/span");
        cursor.click(triggerCity).build().perform();
        WebElement city = driver.findElementByXPath("//*[@id=\"add_hotel:city_panel\"]/div/ul/li[4]");
        cursor.click(city).build().perform();
        cursor.click(nameField).build().perform();
        cursor.sendKeys("").build().perform();
        cursor.click(dateOfConstruct).build().perform();
        cursor.sendKeys("22.11.16").build().perform();
        WebElement descr = driver.findElementById("add_hotel:description");
        cursor.click(descr).build().perform();
        cursor.sendKeys("Valid test description field Very very good hotel !@# 6758").build().perform();
        WebElement notes = driver.findElementById("add_hotel:notes");
        cursor.click(notes).build().perform();
        cursor.sendKeys("Filled note field 5898954895489").build().perform();
        WebElement saveButt = driver.findElementById("add_hotel:j_idt62");
        cursor.click(saveButt).build().perform();
        Assert.assertTrue(!driver.findElements(By.id("add_hotel:j_idt43")).isEmpty());
    }

    @Test
    public void nameValidFieldSaved() //Verify that it is possible to save the valid Name field
    {
        Actions cursor = new Actions(driver);

        driver.get("http://localhost:8080/article/faces/hotel.xhtml");
        WebElement nameField = driver.findElementById("add_hotel:name");
        WebElement dateOfConstruct = driver.findElementById("add_hotel:dateOfConstruction_input");
        WebElement triggerCountry = driver.findElementByClassName("ui-selectonemenu-trigger");
        cursor.click(triggerCountry).build().perform();
        WebElement country = driver.findElementByXPath("//*[@id=\"add_hotel:country_panel\"]/div/ul/li[2]");
        cursor.click(country).build().perform();
        WebElement shortDescr = driver.findElementById("add_hotel:short_description");
        cursor.click(shortDescr).build().perform();
        cursor.sendKeys("Very good hotel 666 ()@.").build().perform();
        WebElement rate = driver.findElementByXPath("//*[@id=\"add_hotel:rate\"]/div[6]");
        cursor.click(rate).build().perform();
        WebElement triggerCity = driver.findElementByXPath("//*[@id=\"add_hotel:city\"]/div[3]/span");
        cursor.click(triggerCity).build().perform();
        WebElement city = driver.findElementByXPath("//*[@id=\"add_hotel:city_panel\"]/div/ul/li[4]");
        cursor.click(city).build().perform();
        cursor.click(nameField).build().perform();
        cursor.sendKeys("Valid Name").build().perform();
        cursor.click(dateOfConstruct).build().perform();
        cursor.sendKeys("22.11.16").build().perform();
        WebElement descr = driver.findElementById("add_hotel:description");
        cursor.click(descr).build().perform();
        cursor.sendKeys("Valid test description field Very very good hotel !@# 6758").build().perform();
        WebElement notes = driver.findElementById("add_hotel:notes");
        cursor.click(notes).build().perform();
        cursor.sendKeys("Filled note field 5898954895489").build().perform();
        WebElement saveButt = driver.findElementById("add_hotel:j_idt62");
        cursor.click(saveButt).build().perform();
        String title = driver.findElementByXPath("//*[@id=\"title\"]/div/h2").getText();
        String titleExp = "List of all hotels";
        System.out.println(title);
        Assert.assertEquals(titleExp, title);
    }

    @Test
    public void globalRatingFieldDisplayed() //Verify that Global Rating field is displayed in Data section of Register new Hotel page
    {
        driver.get("http://localhost:8080/article/faces/hotel.xhtml");
        Assert.assertTrue("No Global Rating field!!!", !driver.findElements(By.id("add_hotel:rate")).isEmpty());
    }

    @Test
    public void globalRatingFieldallowToRate() //Verify that Global Rating field allows to rating of the hotel (0-5 stars)
    {
        Actions cursor = new Actions(driver);

        driver.get("http://localhost:8080/article/faces/hotel.xhtml");

        WebElement rate1 = driver.findElementByXPath("//*[@id=\"add_hotel:rate\"]/div[2]");
        cursor.click(rate1).build().perform();
        WebElement rate2 = driver.findElementByXPath("//*[@id=\"add_hotel:rate\"]/div[3]");
        cursor.click(rate2).build().perform();
        WebElement rate3 = driver.findElementByXPath("//*[@id=\"add_hotel:rate\"]/div[4]");
        cursor.click(rate3).build().perform();
        WebElement rate4 = driver.findElementByXPath("//*[@id=\"add_hotel:rate\"]/div[5]");
        cursor.click(rate4).build().perform();
        WebElement rate5 = driver.findElementByXPath("//*[@id=\"add_hotel:rate\"]/div[6]");
        cursor.click(rate5).build().perform();
        WebElement rate = driver.findElementByXPath("//*[@id=\"add_hotel:rate\"]/div[1]");
        cursor.click(rate).build().perform();
    }

    @Test
    public void globalRatingPossibleToSaveEmpty() // Verify that it is possible to save the Global Rating field
    {
        Actions cursor = new Actions(driver);

        driver.get("http://localhost:8080/article/faces/hotel.xhtml");
        WebElement nameField = driver.findElementById("add_hotel:name");
        WebElement dateOfConstruct = driver.findElementById("add_hotel:dateOfConstruction_input");
        WebElement triggerCountry = driver.findElementByClassName("ui-selectonemenu-trigger");
        cursor.click(triggerCountry).build().perform();
        WebElement country = driver.findElementByXPath("//*[@id=\"add_hotel:country_panel\"]/div/ul/li[2]");
        cursor.click(country).build().perform();
        WebElement shortDescr = driver.findElementById("add_hotel:short_description");
        cursor.click(shortDescr).build().perform();
        cursor.sendKeys("Very good hotel 666 ()@.").build().perform();
        WebElement triggerCity = driver.findElementByXPath("//*[@id=\"add_hotel:city\"]/div[3]/span");
        cursor.click(triggerCity).build().perform();
        WebElement city = driver.findElementByXPath("//*[@id=\"add_hotel:city_panel\"]/div/ul/li[4]");
        cursor.click(city).build().perform();
        cursor.click(nameField).build().perform();
        cursor.sendKeys("Empty rate").build().perform();
        cursor.click(dateOfConstruct).build().perform();
        cursor.sendKeys("22.11.16").build().perform();
        WebElement descr = driver.findElementById("add_hotel:description");
        cursor.click(descr).build().perform();
        cursor.sendKeys("Valid test description field Very very good hotel !@# 6758").build().perform();
        WebElement notes = driver.findElementById("add_hotel:notes");
        cursor.click(notes).build().perform();
        cursor.sendKeys("Filled note field 5898954895489").build().perform();
        WebElement saveButt = driver.findElementById("add_hotel:j_idt62");
        cursor.click(saveButt).build().perform();
        String title = driver.findElementByXPath("//*[@id=\"title\"]/div/h2").getText();
        String titleExp = "List of all hotels";
        System.out.println(title);
        Assert.assertEquals(titleExp, title);
    }

    @Test
    public void globalRatingPossibleToSave4() // Verify that it is possible to save valid Global Rating field
    {
        Actions cursor = new Actions(driver);

        driver.get("http://localhost:8080/article/faces/hotel.xhtml");
        WebElement nameField = driver.findElementById("add_hotel:name");
        WebElement dateOfConstruct = driver.findElementById("add_hotel:dateOfConstruction_input");
        WebElement triggerCountry = driver.findElementByClassName("ui-selectonemenu-trigger");
        cursor.click(triggerCountry).build().perform();
        WebElement country = driver.findElementByXPath("//*[@id=\"add_hotel:country_panel\"]/div/ul/li[2]");
        cursor.click(country).build().perform();
        WebElement shortDescr = driver.findElementById("add_hotel:short_description");
        cursor.click(shortDescr).build().perform();
        cursor.sendKeys("Very good hotel 666 ()@.").build().perform();
        WebElement rate1 = driver.findElementByXPath("//*[@id=\"add_hotel:rate\"]/div[2]");
        cursor.click(rate1).build().perform();
        WebElement rate2 = driver.findElementByXPath("//*[@id=\"add_hotel:rate\"]/div[3]");
        cursor.click(rate2).build().perform();
        WebElement rate3 = driver.findElementByXPath("//*[@id=\"add_hotel:rate\"]/div[4]");
        cursor.click(rate3).build().perform();
        WebElement rate4 = driver.findElementByXPath("//*[@id=\"add_hotel:rate\"]/div[5]");
        cursor.click(rate4).build().perform();
        WebElement triggerCity = driver.findElementByXPath("//*[@id=\"add_hotel:city\"]/div[3]/span");
        cursor.click(triggerCity).build().perform();
        WebElement city = driver.findElementByXPath("//*[@id=\"add_hotel:city_panel\"]/div/ul/li[4]");
        cursor.click(city).build().perform();
        cursor.click(nameField).build().perform();
        cursor.sendKeys("Valid4 Rate 4").build().perform();
        cursor.click(dateOfConstruct).build().perform();
        cursor.sendKeys("22.11.16").build().perform();
        WebElement descr = driver.findElementById("add_hotel:description");
        cursor.click(descr).build().perform();
        cursor.sendKeys("Valid test description field Very very good hotel !@# 6758").build().perform();
        WebElement notes = driver.findElementById("add_hotel:notes");
        cursor.click(notes).build().perform();
        cursor.sendKeys("Filled note field 5898954895489").build().perform();
        WebElement saveButt = driver.findElementById("add_hotel:j_idt62");
        cursor.click(saveButt).build().perform();
        String title = driver.findElementByXPath("//*[@id=\"title\"]/div/h2").getText();
        String titleExp = "List of all hotels";
        System.out.println(title);
        Assert.assertEquals(titleExp, title);
    }

    @Test
    public void dateOfConstructionFieldIsDisplayed() //Verify that Date of Construction field is displayed in Data section of Register new Hotel page
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
    public void dateOfConstFieldNotSaveIncorrectFormat() //Verify that it is not possible to save incorrect date format value Date of Construction field and a meaningful error message is displayed
    {
        Actions cursor = new Actions(driver);

        driver.get("http://localhost:8080/article/faces/hotel.xhtml");
        WebElement nameField = driver.findElementById("add_hotel:name");
        WebElement dateOfConstruct = driver.findElementById("add_hotel:dateOfConstruction_input");
        WebElement triggerCountry = driver.findElementByClassName("ui-selectonemenu-trigger");
        cursor.click(triggerCountry).build().perform();
        WebElement country = driver.findElementByXPath("//*[@id=\"add_hotel:country_panel\"]/div/ul/li[2]");
        cursor.click(country).build().perform();
        WebElement shortDescr = driver.findElementById("add_hotel:short_description");
        cursor.click(shortDescr).build().perform();
        cursor.sendKeys("Very good hotel 666 ()@.").build().perform();
        WebElement rate = driver.findElementByXPath("//*[@id=\"add_hotel:rate\"]/div[6]");
        cursor.click(rate).build().perform();
        WebElement triggerCity = driver.findElementByXPath("//*[@id=\"add_hotel:city\"]/div[3]/span");
        cursor.click(triggerCity).build().perform();
        WebElement city = driver.findElementByXPath("//*[@id=\"add_hotel:city_panel\"]/div/ul/li[4]");
        cursor.click(city).build().perform();
        cursor.click(nameField).build().perform();
        cursor.sendKeys("Valid Name").build().perform();
        cursor.click(dateOfConstruct).build().perform();
        cursor.sendKeys("11.22.16").build().perform();
        WebElement descr = driver.findElementById("add_hotel:description");
        cursor.click(descr).build().perform();
        cursor.sendKeys("Valid test description field Very very good hotel !@# 6758").build().perform();
        WebElement notes = driver.findElementById("add_hotel:notes");
        cursor.click(notes).build().perform();
        cursor.sendKeys("Filled note field 5898954895489").build().perform();
        WebElement saveButt = driver.findElementById("add_hotel:j_idt62");
        cursor.click(saveButt).build().perform();
        Assert.assertTrue(!driver.findElements(By.id("add_hotel:j_idt47")).isEmpty());
    }

    @Test
    public void dateOfConstNotSaveEmpty() //Verify that it is not possible to save the empty Date of Construction field and a meaningful error message is displayedVerify that it is not possible to save incorrect date format value Date of Construction field and a meaningful error message is displayed
    {
        Actions cursor = new Actions(driver);

        driver.get("http://localhost:8080/article/faces/hotel.xhtml");
        WebElement nameField = driver.findElementById("add_hotel:name");
        WebElement dateOfConstruct = driver.findElementById("add_hotel:dateOfConstruction_input");
        WebElement triggerCountry = driver.findElementByClassName("ui-selectonemenu-trigger");
        cursor.click(triggerCountry).build().perform();
        WebElement country = driver.findElementByXPath("//*[@id=\"add_hotel:country_panel\"]/div/ul/li[2]");
        cursor.click(country).build().perform();
        WebElement shortDescr = driver.findElementById("add_hotel:short_description");
        cursor.click(shortDescr).build().perform();
        cursor.sendKeys("Very good hotel 666 ()@.").build().perform();
        WebElement rate = driver.findElementByXPath("//*[@id=\"add_hotel:rate\"]/div[6]");
        cursor.click(rate).build().perform();
        WebElement triggerCity = driver.findElementByXPath("//*[@id=\"add_hotel:city\"]/div[3]/span");
        cursor.click(triggerCity).build().perform();
        WebElement city = driver.findElementByXPath("//*[@id=\"add_hotel:city_panel\"]/div/ul/li[4]");
        cursor.click(city).build().perform();
        cursor.click(nameField).build().perform();
        cursor.sendKeys("Valid Name").build().perform();
        cursor.click(dateOfConstruct).build().perform();
        cursor.sendKeys("").build().perform();
        WebElement descr = driver.findElementById("add_hotel:description");
        cursor.click(descr).build().perform();
        cursor.sendKeys("Valid test description field Very very good hotel !@# 6758").build().perform();
        WebElement notes = driver.findElementById("add_hotel:notes");
        cursor.click(notes).build().perform();
        cursor.sendKeys("Filled note field 5898954895489").build().perform();
        WebElement saveButt = driver.findElementById("add_hotel:j_idt62");
        cursor.click(saveButt).build().perform();
        Assert.assertTrue(!driver.findElements(By.id("add_hotel:j_idt47")).isEmpty());
    }

    @Test
    public void dateOfConstValid() //Verify that it is possible to save valid Date of Construction field
    {
        Actions cursor = new Actions(driver);

        driver.get("http://localhost:8080/article/faces/hotel.xhtml");
        WebElement nameField = driver.findElementById("add_hotel:name");
        WebElement dateOfConstruct = driver.findElementById("add_hotel:dateOfConstruction_input");
        WebElement triggerCountry = driver.findElementByClassName("ui-selectonemenu-trigger");
        cursor.click(triggerCountry).build().perform();
        WebElement country = driver.findElementByXPath("//*[@id=\"add_hotel:country_panel\"]/div/ul/li[2]");
        cursor.click(country).build().perform();
        WebElement shortDescr = driver.findElementById("add_hotel:short_description");
        cursor.click(shortDescr).build().perform();
        cursor.sendKeys("Very good hotel 666 ()@.").build().perform();
        WebElement rate = driver.findElementByXPath("//*[@id=\"add_hotel:rate\"]/div[6]");
        cursor.click(rate).build().perform();
        WebElement triggerCity = driver.findElementByXPath("//*[@id=\"add_hotel:city\"]/div[3]/span");
        cursor.click(triggerCity).build().perform();
        WebElement city = driver.findElementByXPath("//*[@id=\"add_hotel:city_panel\"]/div/ul/li[4]");
        cursor.click(city).build().perform();
        cursor.click(nameField).build().perform();
        cursor.sendKeys("Valid Date of Construction").build().perform();
        cursor.click(dateOfConstruct).build().perform();
        cursor.sendKeys("11.12.16").build().perform();
        WebElement descr = driver.findElementById("add_hotel:description");
        cursor.click(descr).build().perform();
        cursor.sendKeys("Valid test description field Very very good hotel !@# 6758").build().perform();
        WebElement notes = driver.findElementById("add_hotel:notes");
        cursor.click(notes).build().perform();
        cursor.sendKeys("Filled note field 5898954895489").build().perform();
        WebElement saveButt = driver.findElementById("add_hotel:j_idt62");
        cursor.click(saveButt).build().perform();
        String title = driver.findElementByXPath("//*[@id=\"title\"]/div/h2").getText();
        String titleExp = "List of all hotels";
        System.out.println(title);
        Assert.assertEquals(titleExp, title);
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
    public void countryFieldEditable() //Verify that Country field is editable
    {
        Actions cursor = new Actions(driver);
        driver.get("http://localhost:8080/article/faces/hotel.xhtml");
        WebElement triggerCountry = driver.findElementByClassName("ui-selectonemenu-trigger");
        cursor.click(triggerCountry).build().perform();
        cursor.sendKeys("u").build().perform();
    }

    @Test
    public void countryFieldNotSaveEmpty() //Verify that it is not possible to save the empty (with default value “Select me”) country field and a meaningful error message is displayed
    {
        Actions cursor = new Actions(driver);

        driver.get("http://localhost:8080/article/faces/hotel.xhtml");
        WebElement nameField = driver.findElementById("add_hotel:name");
        WebElement dateOfConstruct = driver.findElementById("add_hotel:dateOfConstruction_input");
        WebElement shortDescr = driver.findElementById("add_hotel:short_description");
        cursor.click(shortDescr).build().perform();
        cursor.sendKeys("Very good hotel 666 ()@.").build().perform();
        WebElement rate4 = driver.findElementByXPath("//*[@id=\"add_hotel:rate\"]/div[5]");
        cursor.click(rate4).build().perform();
        cursor.click(nameField).build().perform();
        cursor.sendKeys("Empty city").build().perform();
        cursor.click(dateOfConstruct).build().perform();
        cursor.sendKeys("22.11.16").build().perform();
        WebElement descr = driver.findElementById("add_hotel:description");
        cursor.click(descr).build().perform();
        cursor.sendKeys("Valid test description field Very very good hotel !@# 6758").build().perform();
        WebElement notes = driver.findElementById("add_hotel:notes");
        cursor.click(notes).build().perform();
        cursor.sendKeys("Filled note field 5898954895489").build().perform();
        WebElement saveButt = driver.findElementById("add_hotel:j_idt62");
        cursor.click(saveButt).build().perform();
        Assert.assertTrue(!driver.findElements(By.id("add_hotel:j_idt51")).isEmpty());
    }

    @Test
    public void saveValidCountry() //Verify that it is possible to save the valid Country field
    {
        Actions cursor = new Actions(driver);

        driver.get("http://localhost:8080/article/faces/hotel.xhtml");
        WebElement nameField = driver.findElementById("add_hotel:name");
        WebElement dateOfConstruct = driver.findElementById("add_hotel:dateOfConstruction_input");
        WebElement triggerCountry = driver.findElementByClassName("ui-selectonemenu-trigger");
        cursor.click(triggerCountry).build().perform();
        WebElement country = driver.findElementByXPath("//*[@id=\"add_hotel:country_panel\"]/div/ul/li[2]");
        cursor.click(country).build().perform();
        WebElement shortDescr = driver.findElementById("add_hotel:short_description");
        cursor.click(shortDescr).build().perform();
        cursor.sendKeys("Very good hotel 666 ()@.").build().perform();
        WebElement rate4 = driver.findElementByXPath("//*[@id=\"add_hotel:rate\"]/div[5]");
        cursor.click(rate4).build().perform();
        WebElement triggerCity = driver.findElementByXPath("//*[@id=\"add_hotel:city\"]/div[3]/span");
        cursor.click(triggerCity).build().perform();
        WebElement city = driver.findElementByXPath("//*[@id=\"add_hotel:city_panel\"]/div/ul/li[4]");
        cursor.click(city).build().perform();
        cursor.click(nameField).build().perform();
        cursor.sendKeys("Valid country").build().perform();
        cursor.click(dateOfConstruct).build().perform();
        cursor.sendKeys("22.11.16").build().perform();
        WebElement descr = driver.findElementById("add_hotel:description");
        cursor.click(descr).build().perform();
        cursor.sendKeys("Valid test description field Very very good hotel !@# 6758").build().perform();
        WebElement notes = driver.findElementById("add_hotel:notes");
        cursor.click(notes).build().perform();
        cursor.sendKeys("Filled note field 5898954895489").build().perform();
        WebElement saveButt = driver.findElementById("add_hotel:j_idt62");
        cursor.click(saveButt).build().perform();
        String title = driver.findElementByXPath("//*[@id=\"title\"]/div/h2").getText();
        String titleExp = "List of all hotels";
        System.out.println(title);
        Assert.assertEquals(titleExp, title);
    }

    @Test
    public void cityDropDownDisplayed() //Verify that City field is displayed in Data section of Register new Hotel page
    {
        driver.get("http://localhost:8080/article/faces/hotel.xhtml");
        Assert.assertTrue("No Country menu!!!", !driver.findElements(By.id("add_hotel:city")).isEmpty());
    }

    @Test
    public void cityMenuAsterisk() //Verify that City fields is marked with asterisk
    {
        driver.get("http://localhost:8080/article/faces/hotel.xhtml");
        String asterisk = driver.findElementByXPath("//*[@id=\"add_hotel:j_idt52\"]/span").getText();
        String ast = "*";
        System.out.println(asterisk);
        Assert.assertEquals(ast, asterisk);
    }

    @Test
    public void cityFieldEditable() //Verify that City field is editable
    {
        Actions cursor = new Actions(driver);
        driver.get("http://localhost:8080/article/faces/hotel.xhtml");
        WebElement triggerCountry = driver.findElementByClassName("ui-selectonemenu-trigger");
        cursor.click(triggerCountry).build().perform();
        WebElement country = driver.findElementByXPath("//*[@id=\"add_hotel:country_panel\"]/div/ul/li[2]");
        cursor.click(country).build().perform();
        WebElement triggerCity = driver.findElementByXPath("//*[@id=\"add_hotel:city\"]/div[3]/span");
        cursor.click(triggerCity).build().perform();
        cursor.sendKeys("l").build().perform();
    }

    @Test
    public void cityFieldNotsaveEmpty() //Verify that it is not possible to save the empty (with default value “Select me”) City field and a meaningful error message is displayed
    {
        Actions cursor = new Actions(driver);

        driver.get("http://localhost:8080/article/faces/hotel.xhtml");
        WebElement nameField = driver.findElementById("add_hotel:name");
        WebElement dateOfConstruct = driver.findElementById("add_hotel:dateOfConstruction_input");
        WebElement triggerCountry = driver.findElementByClassName("ui-selectonemenu-trigger");
        cursor.click(triggerCountry).build().perform();
        WebElement country = driver.findElementByXPath("//*[@id=\"add_hotel:country_panel\"]/div/ul/li[2]");
        cursor.click(country).build().perform();
        WebElement shortDescr = driver.findElementById("add_hotel:short_description");
        cursor.click(shortDescr).build().perform();
        cursor.sendKeys("Very good hotel 666 ()@.").build().perform();
        WebElement rate4 = driver.findElementByXPath("//*[@id=\"add_hotel:rate\"]/div[5]");
        cursor.click(rate4).build().perform();
        cursor.click(nameField).build().perform();
        cursor.sendKeys("Empty city").build().perform();
        cursor.click(dateOfConstruct).build().perform();
        cursor.sendKeys("22.11.16").build().perform();
        WebElement descr = driver.findElementById("add_hotel:description");
        cursor.click(descr).build().perform();
        cursor.sendKeys("Valid test description field Very very good hotel !@# 6758").build().perform();
        WebElement notes = driver.findElementById("add_hotel:notes");
        cursor.click(notes).build().perform();
        cursor.sendKeys("Filled note field 5898954895489").build().perform();
        WebElement saveButt = driver.findElementById("add_hotel:j_idt62");
        cursor.click(saveButt).build().perform();
        Assert.assertTrue(!driver.findElements(By.id("add_hotel:j_idt55")).isEmpty());
    }

    @Test
    public void saveValidCity() //Verify that it is possible to save the valid City field
    {
        Actions cursor = new Actions(driver);

        driver.get("http://localhost:8080/article/faces/hotel.xhtml");
        WebElement nameField = driver.findElementById("add_hotel:name");
        WebElement dateOfConstruct = driver.findElementById("add_hotel:dateOfConstruction_input");
        WebElement triggerCountry = driver.findElementByClassName("ui-selectonemenu-trigger");
        cursor.click(triggerCountry).build().perform();
        WebElement country = driver.findElementByXPath("//*[@id=\"add_hotel:country_panel\"]/div/ul/li[2]");
        cursor.click(country).build().perform();
        WebElement shortDescr = driver.findElementById("add_hotel:short_description");
        cursor.click(shortDescr).build().perform();
        cursor.sendKeys("Very good hotel 666 ()@.").build().perform();
        WebElement rate4 = driver.findElementByXPath("//*[@id=\"add_hotel:rate\"]/div[5]");
        cursor.click(rate4).build().perform();
        WebElement triggerCity = driver.findElementByXPath("//*[@id=\"add_hotel:city\"]/div[3]/span");
        cursor.click(triggerCity).build().perform();
        WebElement city = driver.findElementByXPath("//*[@id=\"add_hotel:city_panel\"]/div/ul/li[4]");
        cursor.click(city).build().perform();
        cursor.click(nameField).build().perform();
        cursor.sendKeys("Valid city").build().perform();
        cursor.click(dateOfConstruct).build().perform();
        cursor.sendKeys("22.11.16").build().perform();
        WebElement descr = driver.findElementById("add_hotel:description");
        cursor.click(descr).build().perform();
        cursor.sendKeys("Valid test description field Very very good hotel !@# 6758").build().perform();
        WebElement notes = driver.findElementById("add_hotel:notes");
        cursor.click(notes).build().perform();
        cursor.sendKeys("Filled note field 5898954895489").build().perform();
        WebElement saveButt = driver.findElementById("add_hotel:j_idt62");
        cursor.click(saveButt).build().perform();
        String title = driver.findElementByXPath("//*[@id=\"title\"]/div/h2").getText();
        String titleExp = "List of all hotels";
        System.out.println(title);
        Assert.assertEquals(titleExp, title);
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
        WebElement nameField = driver.findElementById("add_hotel:name");
        WebElement dateOfConstruct = driver.findElementById("add_hotel:dateOfConstruction_input");
        WebElement triggerCountry = driver.findElementByClassName("ui-selectonemenu-trigger");
        cursor.click(triggerCountry).build().perform();
        WebElement country = driver.findElementByXPath("//*[@id=\"add_hotel:country_panel\"]/div/ul/li[2]");
        cursor.click(country).build().perform();
        WebElement shortDescr = driver.findElementById("add_hotel:short_description");
        cursor.click(shortDescr).build().perform();
        cursor.sendKeys("Very good hotel 666 ()@.").build().perform();
        WebElement rate = driver.findElementByXPath("//*[@id=\"add_hotel:rate\"]/div[6]");
        cursor.click(rate).build().perform();
        WebElement triggerCity = driver.findElementByXPath("//*[@id=\"add_hotel:city\"]/div[3]/span");
        cursor.click(triggerCity).build().perform();
        WebElement city = driver.findElementByXPath("//*[@id=\"add_hotel:city_panel\"]/div/ul/li[4]");
        cursor.click(city).build().perform();
        cursor.click(nameField).build().perform();
        cursor.sendKeys("Valid short description").build().perform();
        cursor.click(dateOfConstruct).build().perform();
        cursor.sendKeys("22.11.16").build().perform();
        WebElement descr = driver.findElementById("add_hotel:description");
        cursor.click(descr).build().perform();
        cursor.sendKeys("Very very good hotel !@# 6758").build().perform();
        WebElement notes = driver.findElementById("add_hotel:notes");
        cursor.click(notes).build().perform();
        cursor.sendKeys("Not Very very good hotel !@# 6758").build().perform();
        WebElement saveButt = driver.findElementById("add_hotel:j_idt62");
        cursor.click(saveButt).build().perform();
        String title = driver.findElementByXPath("//*[@id=\"title\"]/div/h2").getText();
        String titleExp = "List of all hotels";
        System.out.println(title);
        Assert.assertEquals(titleExp, title);
    }

    @Test
    public void descrIsDisplayed() //Verify that Description field is displayed in Data section of Register new Hotel
    {
        driver.get("http://localhost:8080/article/faces/hotel.xhtml");
        Assert.assertTrue("No Description!!!", !driver.findElements(By.id("add_hotel:description")).isEmpty());
    }

    @Test
    public void descrFieldAsterisk() //Verify that Description field is marked with asterisk
    {
        driver.get("http://localhost:8080/article/faces/hotel.xhtml");
        String asterisk = driver.findElementByXPath("//*[@id=\"add_hotel:j_idt58\"]/span").getText();
        String ast = "*";
        System.out.println(asterisk);
        Assert.assertEquals(ast, asterisk);
    }

    @Test
    public void descrFieldIsEditable() //Verify that Description field is editable
    {
        Actions cursor = new Actions(driver);

        driver.get("http://localhost:8080/article/faces/hotel.xhtml");
        WebElement nameField = driver.findElementById("add_hotel:description");
        cursor.click(nameField).build().perform();
        cursor.sendKeys("Not Very good hotel 666.").build().perform();
    }

    @Test
    public void descrIsAllowsAlfaNumeric() //Verify that Description field allows to input alphanumeric characters
    {
        Actions cursor = new Actions(driver);

        driver.get("http://localhost:8080/article/faces/hotel.xhtml");
        WebElement descr = driver.findElementById("add_hotel:description");
        WebElement saveButt = driver.findElementById("add_hotel:j_idt62");
        cursor.click(descr).build().perform();
        cursor.sendKeys("Aqsuyhgud 0987654321").build().perform();
        cursor.click(saveButt).build().perform();
        Assert.assertTrue(!driver.findElements(By.id("add_hotel:j_idt59")).isEmpty());
    }

    @Test
    public void descrNotSaveEmpty() //Verify that it is not possible to save the empty Description field and a meaningful error message is displayed
    {
        Actions cursor = new Actions(driver);

        driver.get("http://localhost:8080/article/faces/hotel.xhtml");
        WebElement nameField = driver.findElementById("add_hotel:description");
        WebElement saveButt = driver.findElementById("add_hotel:j_idt62");
        cursor.click(nameField).build().perform();
        cursor.sendKeys("").build().perform();
        cursor.click(saveButt).build().perform();
        Assert.assertTrue(!driver.findElements(By.id("add_hotel:j_idt59")).isEmpty());
    }

    @Test
    public void descrFieldIsSavedValidData() // Verify that it is possible to save the valid Description field
    {
        Actions cursor = new Actions(driver);

        driver.get("http://localhost:8080/article/faces/hotel.xhtml");
        WebElement nameField = driver.findElementById("add_hotel:name");
        WebElement dateOfConstruct = driver.findElementById("add_hotel:dateOfConstruction_input");
        WebElement triggerCountry = driver.findElementByClassName("ui-selectonemenu-trigger");
        cursor.click(triggerCountry).build().perform();
        WebElement country = driver.findElementByXPath("//*[@id=\"add_hotel:country_panel\"]/div/ul/li[2]");
        cursor.click(country).build().perform();
        WebElement shortDescr = driver.findElementById("add_hotel:short_description");
        cursor.click(shortDescr).build().perform();
        cursor.sendKeys("Very good hotel 666 ()@.").build().perform();
        WebElement rate = driver.findElementByXPath("//*[@id=\"add_hotel:rate\"]/div[6]");
        cursor.click(rate).build().perform();
        WebElement triggerCity = driver.findElementByXPath("//*[@id=\"add_hotel:city\"]/div[3]/span");
        cursor.click(triggerCity).build().perform();
        WebElement city = driver.findElementByXPath("//*[@id=\"add_hotel:city_panel\"]/div/ul/li[4]");
        cursor.click(city).build().perform();
        cursor.click(nameField).build().perform();
        cursor.sendKeys("Valid description").build().perform();
        cursor.click(dateOfConstruct).build().perform();
        cursor.sendKeys("22.11.16").build().perform();
        WebElement descr = driver.findElementById("add_hotel:description");
        cursor.click(descr).build().perform();
        cursor.sendKeys("Valid test description field Very very good hotel !@# 6758").build().perform();
        WebElement notes = driver.findElementById("add_hotel:notes");
        cursor.click(notes).build().perform();
        cursor.sendKeys("Not Very very good hotel !@# 6758").build().perform();
        WebElement saveButt = driver.findElementById("add_hotel:j_idt62");
        cursor.click(saveButt).build().perform();
        String title = driver.findElementByXPath("//*[@id=\"title\"]/div/h2").getText();
        String titleExp = "List of all hotels";
        System.out.println(title);
        Assert.assertEquals(titleExp, title);
    }

    @Test
    public void notesIsDisplayed() //Verify that Notes field is displayed in Data section of Register new Hotel
    {
        driver.get("http://localhost:8080/article/faces/hotel.xhtml");
        Assert.assertTrue("No Description!!!", !driver.findElements(By.id("add_hotel:notes")).isEmpty());
    }

    @Test
    public void notesFieldIsEditable() //Verify that Notes field is editable
    {
        Actions cursor = new Actions(driver);

        driver.get("http://localhost:8080/article/faces/hotel.xhtml");
        WebElement nameField = driver.findElementById("add_hotel:notes");
        cursor.click(nameField).build().perform();
        cursor.sendKeys("Not Very good hotel 666.").build().perform();
    }

    @Test
    public void notesSaveEmptyField() // Verify that it is possible to save the empty Notes field
    {
        Actions cursor = new Actions(driver);

        driver.get("http://localhost:8080/article/faces/hotel.xhtml");
        WebElement nameField = driver.findElementById("add_hotel:name");
        WebElement dateOfConstruct = driver.findElementById("add_hotel:dateOfConstruction_input");
        WebElement triggerCountry = driver.findElementByClassName("ui-selectonemenu-trigger");
        cursor.click(triggerCountry).build().perform();
        WebElement country = driver.findElementByXPath("//*[@id=\"add_hotel:country_panel\"]/div/ul/li[2]");
        cursor.click(country).build().perform();
        WebElement shortDescr = driver.findElementById("add_hotel:short_description");
        cursor.click(shortDescr).build().perform();
        cursor.sendKeys("Very good hotel 666 ()@.").build().perform();
        WebElement rate = driver.findElementByXPath("//*[@id=\"add_hotel:rate\"]/div[6]");
        cursor.click(rate).build().perform();
        WebElement triggerCity = driver.findElementByXPath("//*[@id=\"add_hotel:city\"]/div[3]/span");
        cursor.click(triggerCity).build().perform();
        WebElement city = driver.findElementByXPath("//*[@id=\"add_hotel:city_panel\"]/div/ul/li[4]");
        cursor.click(city).build().perform();
        cursor.click(nameField).build().perform();
        cursor.sendKeys("Empty notes test").build().perform();
        cursor.click(dateOfConstruct).build().perform();
        cursor.sendKeys("22.11.16").build().perform();
        WebElement descr = driver.findElementById("add_hotel:description");
        cursor.click(descr).build().perform();
        cursor.sendKeys("Valid test description field Very very good hotel !@# 6758").build().perform();
        WebElement notes = driver.findElementById("add_hotel:notes");
        cursor.click(notes).build().perform();
        cursor.sendKeys("").build().perform();
        WebElement saveButt = driver.findElementById("add_hotel:j_idt62");
        cursor.click(saveButt).build().perform();
        String title = driver.findElementByXPath("//*[@id=\"title\"]/div/h2").getText();
        String titleExp = "List of all hotels";
        System.out.println(title);
        Assert.assertEquals(titleExp, title);
    }

    @Test
    public void notesSaveFilledField() // Verify that it is possible to save the filled Notes field
    {
        Actions cursor = new Actions(driver);

        driver.get("http://localhost:8080/article/faces/hotel.xhtml");
        WebElement nameField = driver.findElementById("add_hotel:name");
        WebElement dateOfConstruct = driver.findElementById("add_hotel:dateOfConstruction_input");
        WebElement triggerCountry = driver.findElementByClassName("ui-selectonemenu-trigger");
        cursor.click(triggerCountry).build().perform();
        WebElement country = driver.findElementByXPath("//*[@id=\"add_hotel:country_panel\"]/div/ul/li[2]");
        cursor.click(country).build().perform();
        WebElement shortDescr = driver.findElementById("add_hotel:short_description");
        cursor.click(shortDescr).build().perform();
        cursor.sendKeys("Very good hotel 666 ()@.").build().perform();
        WebElement rate = driver.findElementByXPath("//*[@id=\"add_hotel:rate\"]/div[6]");
        cursor.click(rate).build().perform();
        WebElement triggerCity = driver.findElementByXPath("//*[@id=\"add_hotel:city\"]/div[3]/span");
        cursor.click(triggerCity).build().perform();
        WebElement city = driver.findElementByXPath("//*[@id=\"add_hotel:city_panel\"]/div/ul/li[4]");
        cursor.click(city).build().perform();
        cursor.click(nameField).build().perform();
        cursor.sendKeys("Filled in notes").build().perform();
        cursor.click(dateOfConstruct).build().perform();
        cursor.sendKeys("22.11.16").build().perform();
        WebElement descr = driver.findElementById("add_hotel:description");
        cursor.click(descr).build().perform();
        cursor.sendKeys("Valid test description field Very very good hotel !@# 6758").build().perform();
        WebElement notes = driver.findElementById("add_hotel:notes");
        cursor.click(notes).build().perform();
        cursor.sendKeys("Filled note field 5898954895489").build().perform();
        WebElement saveButt = driver.findElementById("add_hotel:j_idt62");
        cursor.click(saveButt).build().perform();
        String title = driver.findElementByXPath("//*[@id=\"title\"]/div/h2").getText();
        String titleExp = "List of all hotels";
        System.out.println(title);
        Assert.assertEquals(titleExp, title);
    }

    @After
    public void end()
        {
        driver.quit();
    }
}


