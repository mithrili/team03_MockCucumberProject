package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import io.cucumber.java.en.But;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;
import pages.admin.AdminDashboard;
import pages.admin.AdminSignIn;
import pages.user.UserSignIn;
import pages.user.UserSignUp;
import pages.user.registeredUser.ChangePassword;
import pages.user.registeredUser.Profile;
import pages.visitor.VisitorContact;
import pages.visitor.VisitorHomePage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

public class EasyBusTicketUser {
    String actualUrl, expectedUrl, actualError, expectedError, actualText, expectedText;
    Actions actions = new Actions(Driver.getDriver());
    Profile profile = new Profile();
    ChangePassword changePassword = new ChangePassword();
    VisitorHomePage visitorHomePage = new VisitorHomePage();
    UserSignIn userSignIn = new UserSignIn();
    UserSignUp userSignUp= new UserSignUp();
    SoftAssert softAssert = new SoftAssert();
    VisitorContact visitorContact =new VisitorContact();
  
    @Given("User goes to the easybusticket homepage.")
    public void userGoesToTheHomepage() {
        Driver.getDriver().get(ConfigReader.getProperty("toUrl"));
    }

    @Then("User clicks on Sign In button and displays the Sign In page.")
    public void userClicksOnSignInButtonAndDisplaysTheSignInPage() {
        visitorHomePage.singInButton.click();

        actualUrl = Driver.getDriver().getCurrentUrl();
        expectedUrl = "https://qa.easybusticket.com/login";
        softAssert.assertEquals(actualUrl, expectedUrl, "User DID NOT display the 'Sign In' page!");
    }

    @Then("User clicks on Username box and enters a valid {string}.")
    public void user_clicks_on_username_box_and_enters_a_valid(String username) {
        userSignIn.usernameBox.click();
        userSignIn.usernameBox.sendKeys(ConfigReader.getProperty(username));
    }

    @Then("User clicks on Password box and enters a valid {string}.")
    public void user_clicks_on_password_box_and_enters_a_valid(String password) {
        userSignIn.passwordBox.click();
        userSignIn.passwordBox.sendKeys(ConfigReader.getProperty(password));
    }

    @Then("User clicks the Login button.")
    public void user_clicks_the_login_button() {
        userSignIn.loginButton.click();
    }

    @Then("User displays the Dashboard page.")
    public void user_displays_the_dashboard_page() {
        actualUrl = Driver.getDriver().getCurrentUrl();
        expectedUrl = "https://qa.easybusticket.com/user/dashboard";
        softAssert.assertEquals(actualUrl, expectedUrl, "User DID NOT display the 'Dashboard' page!");
    }

    @Then("User verifies that -These credentials do not match our records.- error text appeared.")
    public void userVerifiesThatTheseCredentialsDoNotMatchOurRecordsErrorTextAppeared() {
        actualError = userSignIn.labelError.getText();
        expectedError = "These credentials do not match our records.";
        softAssert.assertEquals(actualError, expectedError, "Error text DID NOT appear!");
    }

    @Then("User closes the page.")
    public void userClosesThePage() {
        softAssert.assertAll();
        Driver.closeDriver();
    }

    @But("User waits for {int} seconds.")
    public void userWaitsForSeconds(int duration) {
        try {
            Thread.sleep(duration * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Then("User clicks the -Profile- button and clicks -Change Password- from the selection menu.")
    public void userClicksTheProfileButtonAndClicksChangePasswordFromTheSelectionMenu() {
        actions.moveToElement(profile.liProfile).perform();
        ReusableMethods.bekle(1);
        profile.ChangePassword.click();
    }

    @And("User displays the -Change Password- page.")
    public void userDisplaysTheChangePasswordPageAnd() {
        actualUrl = Driver.getDriver().getCurrentUrl();
        expectedUrl = "https://qa.easybusticket.com/user/change-password";
        softAssert.assertEquals(actualUrl, expectedUrl, "User DID NOT display the 'Change Password' page!");    }

    @And("User clicks on -Change Password- button and verifies that -Password changes succesfully- text appeared.")
    public void userClicksOnChangePasswordButtonAndVerifiesThatPasswordChangesSuccesfullyTextAppeared() {
        changePassword.changePasswordButton.click();

        actualText = changePassword.labelPasswordAlert.getText();
        expectedText = "Password changes succesfully";
        softAssert.assertEquals(actualText, expectedText, "'Password changes succesfully' text DID NOT appear!");
    }

    @Then("User clicks -Current Password- box and enters {string} password.")
    public void userClicksCurrentPasswordBoxAndEntersPassword(String password) {
        changePassword.currentPasswordBox.sendKeys(ConfigReader.getProperty(password));
    }

    @And("User clicks -Password- box and enters {string} password.")
    public void userClicksPasswordBoxAndEntersPassword(String password) {
        changePassword.passwordBox.sendKeys(ConfigReader.getProperty(password));
    }

    @And("User clicks -Confirm Password- box and enters {string} password.")
    public void userClicksConfirmPasswordBoxAndEntersPassword(String password) {
        changePassword.confirmPasswordBox.sendKeys(ConfigReader.getProperty(password));
    }

    @Given("Visitor goes to the easybusticket homepage.")
    public void visitor_goes_to_the_easybusticket_homepage() {
            Driver.getDriver().get(ConfigReader.getProperty("toUrl"));
    }

    @Then("Verify that home page is visible successfully")
    public void verify_that_home_page_is_visible_successfully() {
        actualUrl = Driver.getDriver().getCurrentUrl();
        expectedUrl = "https://qa.easybusticket.com/";
        softAssert.assertEquals(actualUrl, expectedUrl, "Visitor DID NOT display the 'Sign In' page!");
        softAssert.assertAll();
    }

    @Then("User clicks on Sign up button")
    public void userClicksOnSignUpButton() {
        visitorHomePage.HeaderSignUp.click();
    }

    @Then("User displays  Sign up your account")
    public void userDisplaysSignUpYourAccount() {
        softAssert.assertTrue(userSignUp.labelSignUpYourAccount.isDisplayed(),"user did not display the text sign up your account");

    }

    @Then("User clicks on firstname box and enters a valid {string}.")
    public void userClicksOnFirstnameBoxAndEntersAValid(String firstName) {
        userSignUp.firstNameBox.click();
        userSignUp.firstNameBox.sendKeys(ConfigReader.getProperty("humeyraSignUpFirstName"));
    }

    @Then("User clicks on Lastname box and enter a valid {string}")
    public void userClicksOnLastnameBoxAndEnterAValid(String lastName) {
        userSignUp.lastNameBox.click();
        userSignUp.lastNameBox.sendKeys(ConfigReader.getProperty("humeyraSignUpLastName"));
    }

    @Then("User clicks on country box and choose a country {string}")
    public void userClicksOnCountryBoxAndChooseACountry(String Country) {
        Select selectcountry=new Select(userSignUp.countryBox);
        selectcountry.selectByValue(ConfigReader.getProperty("humeyraSignUpCountry"));
    }

    @Then("User clicks on mobile box and enter a valid mobile number {string}")
    public void userClicksOnMobileBoxAndEnterAValidMobileNumber(String mobileNumber) {
        userSignUp.mobileBox.click();
        userSignUp.mobileBox.sendKeys(ConfigReader.getProperty("humeyraSignUpMobile"));
    }

    @Then("User clicks on user name box and enter a valid user name {string}")
    public void userClicksOnUserNameBoxAndEnterAValidUserName(String userName) {
        userSignUp.usernameBox.click();
        userSignUp.usernameBox.sendKeys(ConfigReader.getProperty("humeyraSignUpUserName"));
    }

    @Then("User clicks on e mail box and enter a valid email address {string}")
    public void userClicksOnEMailBoxAndEnterAValidEmailAddress(String emailAddress) {
        userSignUp.emailBox.click();
        userSignUp.emailBox.sendKeys(ConfigReader.getProperty("humeyraSignUpEmail"));
    }

    @Then("User clicks on password box and enter invalid password {string}")
    public void userClicksOnPasswordBoxAndEnterInvalidPassword(String invalidPassword) {
        userSignUp.passwordBox.click();
        userSignUp.passwordBox.sendKeys(ConfigReader.getProperty(invalidPassword));
    }

    @Then("User clicks on confirm password box and enter password {string}")
    public void userClicksOnConfirmPasswordBoxAndEnterPassword(String confirmPassword) {
        userSignUp.confirmPasswordBox.click();
        userSignUp.confirmPasswordBox.sendKeys(ConfigReader.getProperty(confirmPassword));
    }

    @Then("User clicks on Accepting all checkbox")
    public void userClicksOnAcceptingAllCheckbox() {
        userSignUp.acceptingAllCheckbox.click();
    }

    @Then("User clicks on sign Up button")
    public void userClicksOnsignUpButton() {
        userSignUp.signUpButton.click();
    }

    @Then("Verify that user can't register {string}")
    public void verifyThatUserCanTRegister(String alert) {
        expectedError = alert;
        actualError = userSignUp.alert.getText();
        softAssert.assertEquals(expectedError, actualError, "user can register3");
    }
      
    @Then("Verify that header is visible successfully")
    public void verify_that_header_is_visible_successfully() {
        softAssert.assertTrue(visitorHomePage.singInButton.isDisplayed(),"header isn't visible successfully");
        softAssert.assertAll();
    }
      
    @Then("User clicks {string} button in pop menu.")
    public void user_clicks_button_in_pop_menu(String string) {
        visitorHomePage.cookiesAllow.click();
    }

    @Then("User scrolls all the way down.")
    public void user_scrolls_all_the_way_down() {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) Driver.getDriver();

        javascriptExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    @Then("User sees the footer menu.")
    public void user_sees_the_footer_menu() {
        Assert.assertTrue(visitorHomePage.Ticketpolicies.isDisplayed());
    }

    @Then("User sees {string} under the {string} Menu.")
    public void user_sees_under_the_menu(String string, String string2) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) Driver.getDriver();
        javascriptExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        Assert.assertTrue(visitorHomePage.footerabout.isDisplayed());
    }

    @Then("User clicks {string} on the footer menu")
    public void user_clicks_on_the_footer_menu(String string) {
        visitorHomePage.footerFAQs.click();
    }

    @Then("Blog on the footer menu click.")
    public void blog_on_the_footer_menu_click() {
        visitorHomePage.footerblog.click();
    }

    @Then("click on the contact menu on the home page.")
    public void click_on_the_contact_menu_on_the_home_page() {
        visitorHomePage.footercontact.click();
    }

    @Then("clicks on the privacy policy in the footer.")
    public void clicks_on_the_privacy_policy_in_the_footer() {
        visitorHomePage.footerprivacypolicy.click();

    }
    @Then("Visitor homapage header displays web elements")
    public void visitorHomapageHeaderDisplaysWebElements() {
        Driver.getDriver().get("https://qa.easybusticket.com");
        for (int i = 0; i < 20 ; i++) {
            softAssert.assertTrue(visitorHomePage.listr.get(i).isDisplayed());
        }
    }

    @Then("Click on the {string} button on the header.")
    public void click_on_the_button_on_the_headerPhoneNumber(String HeaderPhoneNumber ) {
        visitorHomePage.HeaderPhoneNumber.click();
    }
  
    @Then("Click on the Twitter icon button on the header.")
    public void click_on_the_twitter_icon_button_on_the_header() {
        visitorHomePage.Headertiwetterİcon.click();
    }
  
    @Then("Verify that you are navigated to twitter page.")
    public void verify_that_you_are_navigated_to_twitter_page() {
        String expectedtwitterUrl="https://twitter.com/";
        String actualtwitterUrl=Driver.getDriver().getCurrentUrl();
        softAssert.assertEquals(actualUrl,expectedUrl,"twitter page is not visible");
    }
  
    @Then("Navigate to back.")
    public void navigate_to_back() {
        Driver.getDriver().navigate().back();
    }
  
    @Then("Click on the Facebook icon button on the header.")
    public void click_on_the_facebook_icon_button_on_the_header() {
        visitorHomePage.HeaderFacebook.click();
    }
  
    @Then("Verify that you are navigated to facebook page.")
    public void verify_that_you_are_navigated_to_facebook_page() {
        String expectedfacebookUrl="https://www.facebook.com";
        String actualfacebookUrl=Driver.getDriver().getCurrentUrl();
        softAssert.assertEquals(actualUrl,expectedUrl,"facebook page is not visible");
    }
  
    @Then("Click on the YouTube icon button on the header.")
    public void click_on_the_you_tube_icon_button_on_the_header() {
        visitorHomePage.HeaderYoutube.click();
    }
  
    @Then("Verify that you are navigated to youtube page.")
    public void verify_that_you_are_navigated_to_youtube_page() {
        actualUrl = Driver.getDriver().getCurrentUrl();
        softAssert.assertTrue(actualUrl.contains("youtube.com"), "You are NOT navigated to youtube page!");
    }
  
    @Then("Click on the Instagram icon button on the header.")
    public void click_on_the_ınstagram_icon_button_on_the_header() {
        visitorHomePage.Headerİnstagramİcon.click();
    }
  
    @Then("Verify that you are navigated to instagram page.")
    public void verify_that_you_are_navigated_to_instagram_page() {
        String expectedinstagramUrl="https://www.instagram.com";
        String actualinstagramUrl=Driver.getDriver().getCurrentUrl();
        softAssert.assertEquals(actualUrl,expectedUrl,"instagram page is not visible");
    }
  
    @Then("Click on the Sign In button on the header.")
    public void clickOnTheSignInButtonOnTheHeader() {
        visitorHomePage.HeaderSignİgn.click();
    }
  
    @And("Verify that you are navigated to the Sign In page.")
    public void verifyThatYouAreNavigatedToTheSignInPage() {
        String expectedloginUrl="https://qa.easybusticket.com/login";
        String actualloginUrl=Driver.getDriver().getCurrentUrl();
        softAssert.assertEquals(actualloginUrl,expectedloginUrl,"login page is not visible");
    }
  
    @And("Click on the Sign Up button on the header.")
    public void clickOnTheSignUpButtonOnTheHeader() {
        visitorHomePage.HeaderSignUp.click();
    }
  
    @Then("Verify that you are navigated to the Sign Up page.")
    public void verifyThatYouAreNavigatedToTheSignUpPage() {
        String expectedUrl="https://qa.easybusticket.com/register";
        String actualUrl=Driver.getDriver().getCurrentUrl();
        softAssert.assertEquals(actualUrl,expectedUrl,"Sign In page is not visible");
    }
  
    @Then("Click on the Easybusticket ikon  on the header.")
    public void click_on_the_easybusticket_ikon_on_the_header() {
        visitorHomePage.HeaderLogo.click();
    }
  
    @Then("Verify that the page refreshes when the Easybusticket Logo ikon is clicked")
    public void verify_that_the_page_refreshes_when_the_easybusticket_logo_ikon_is_clicked() {
        String expectedLogoUrl="https://qa.easybusticket.com/";
        String actualLogoUrl=Driver.getDriver().getCurrentUrl();
        softAssert.assertEquals(actualLogoUrl,expectedLogoUrl,"Logo home page is not visible");
    }
  
    @Then("Click on the About button on the header.")
    public void click_on_the_about_button_on_the_header() {
        visitorHomePage.HeaderAbout.click();
    }
  
    @Then("Verify that you are navigated to the About page.")
    public void verify_that_you_are_navigated_to_the_about_page() {
        String expectedAboutUrl="https://qa.easybusticket.com/about-us";
        String actualAboutUrl=Driver.getDriver().getCurrentUrl();
        softAssert.assertEquals(actualAboutUrl,expectedAboutUrl,"About page is not visible");
    }
  
    @Then("Click on the FAQs button on the header.")
    public void click_on_the_fa_qs_button_on_the_header() {
        visitorHomePage.HeaderFaQs.click();
    }
  
    @Then("Verify that you are navigated to the FAQs page.")
    public void verify_that_you_are_navigated_to_the_fa_qs_page() {
        String expectedFAQsUrl="https://qa.easybusticket.com/faq";
        String actualFAQsUrl=Driver.getDriver().getCurrentUrl();
        softAssert.assertEquals(actualFAQsUrl,expectedFAQsUrl,"FAQs page is not visible");
    }
  
    @Then("Click on the Blog button on the header.")
    public void click_on_the_blog_button_on_the_header() {
        visitorHomePage.HeaderBlog.click();
    }
  
    @Then("Verify that you are navigated to the Blog page.")
    public void verify_that_you_are_navigated_to_the_blog_page() {
        String expectedBlogUrl="https://qa.easybusticket.com/blog";
        String actualBlogUrl=Driver.getDriver().getCurrentUrl();
        softAssert.assertEquals(actualBlogUrl,expectedBlogUrl,"Blog  page is not visible");
    }
  
    @Then("Click on the Contact button on the header.")
    public void click_on_the_contact_button_on_the_header() {
        visitorHomePage.HeaderContact.click();
    }
  
    @Then("Verify that you are navigated to the Contact page.")
    public void verify_that_you_are_navigated_to_the_contact_page() {
        String expectedContactUrl="https://qa.easybusticket.com/contact";
        String actualContactUrl=Driver.getDriver().getCurrentUrl();
        softAssert.assertEquals(actualContactUrl,expectedContactUrl,"Contact page is not visible");
    }
  
    @Then("Click on the BUY TICKETS button on the header.")
    public void click_on_the_buy_tıckets_button_on_the_header() {
        visitorHomePage.HeaderByTicket.click();
    }
  
    @Then("Verify that you are navigated to the Tickets page.")
    public void verify_that_you_are_navigated_to_the_tickets_page() {
        String expectedTicketsUrl="https://qa.easybusticket.com/tickets";
        String actualTicketsUrl=Driver.getDriver().getCurrentUrl();
        softAssert.assertEquals(actualTicketsUrl,expectedTicketsUrl,"Tickets page is not visible");
    }
  
    @And("Click on the {string}on the header.")
    public void clickOnTheOnTheHeader(String arg0) {
        visitorHomePage.HeaderLogo.click();
    }
  
    @And("Click on the {string} button  on the header.")
    public void clickOnTheButtonOnTheHeader(String arg0) {
        visitorHomePage.HeaderDasbordLinkButton.click();
    }
  
    @And("Verify that menu dasbord button is visible successfully")
    public void verifyThatMenuDasbordButtonIsVisibleSuccessfully() {
        SoftAssert softAssert=new SoftAssert();
        softAssert.assertTrue(userSignIn.HeaderDashboard.isDisplayed(),"dashboard button is not visible");

    @Then("User must click on \"Allow Cookies\"button.")
    public void user_must_click_on_allow_cookies_button() {
        visitorHomePage.cookiesAllow.click();
    }
      
    @Then("User clicks {string} button on Header menu.")
    public void user_clicks_button_on_header_menu(String string) {
        visitorHomePage.HeaderContact.click();
    }
      
    @Then("Click on contact from the footer menu.")
    public void click_on_contact_from_the_footer_menu() {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) Driver.getDriver();
        javascriptExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        visitorHomePage.footerContacınfo.click();
    }

    @Then("User clicks the allow cookies button.")
    public void user_clicks_the_allow_cookies_button() {
        visitorHomePage.cookiesAllow.click();
    }
      
    @Then("User should be landed on {string} page.")
    public void user_should_be_landed_on_page(String string) {
        visitorHomePage.HeaderContact.click();
    }
      
    @Then("User should be able to see address, phone, e-meail infos.")
    public void user_should_be_able_to_see_address_phone_e_meail_infos() {
        Assert.assertTrue(visitorHomePage.HeaderEmailAddress.isDisplayed());
        Assert.assertTrue(visitorHomePage.HeaderPhoneNumber.isDisplayed());
        Assert.assertTrue(visitorHomePage.HeaderEmailAddress.isDisplayed());
    }
      
    @Then("User clicks phone number.")
    public void user_clicks_phone_number() {
        visitorHomePage. HeaderPhoneNumber.click();
    }
      
    @Then("User displays phone alert.")
    public void user_displays_phone_alert() {
        Assert.assertTrue(visitorHomePage. phoneAlert.isDisplayed());
    }

    @Given("User clicks e-mail string.")
    public void user_clicks_e_mail_string() {
        visitorHomePage.HeaderEmailAddress.click();
    }
      
    @Given("User displays e-mail alert.")
    public void user_displays_e_mail_alert() {
        visitorHomePage. emailAlert.click();
    }

    @Then("User fill Name, Email, Subject, Your Message boxes.")
    public void user_fill_name_email_subject_your_message_boxes() {
        visitorHomePage.messageName.sendKeys(ConfigReader.getProperty("name"));
        visitorHomePage.messageEmail.sendKeys(ConfigReader.getProperty("email"));
        visitorHomePage.messageSubject.sendKeys(ConfigReader.getProperty("subject"));
        visitorContact.messageBox.sendKeys(ConfigReader.getProperty("YourMessage"));
    }
      
    @Then("User clicks {string} Button.")
    public void user_clicks_button(String string) {
        visitorHomePage.sendUsMessageButton.click();
    }

    @And("User clicks on Change Password button and verifies that {string} appeared.")
    public void userClicksOnChangePasswordButtonAndVerifiesThatAppeared(String error) {
        changePassword.changePasswordButton.click();
        ReusableMethods.bekle(1);

        actualText = changePassword.labelPasswordAlert2.getText();
        expectedText = ConfigReader.getProperty(error);
        softAssert.assertEquals(actualText, expectedText, "'The password confirmation does not match.' text DID NOT appear!");
    }
}
