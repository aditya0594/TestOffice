package testcases;

import Base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLOutput;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;

public class Edit_about_me_section extends TestBase {

    Register register = new Register();

    static By profileBtn = By.xpath("//android.widget.FrameLayout[@content-desc=\"Profile\"]/android.view.ViewGroup/android.widget.TextView");
    static By sideAddBtn =  By.id("com.wavar:id/add_joddhanda_lyt");

    static By sectonName = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[3]/android.view.ViewGroup/android.view.ViewGroup/android.widget.RelativeLayout/androidx.viewpager.widget.ViewPager/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.widget.LinearLayout/android.widget.TextView");
    static By sectionTitleName = By.id("com.wavar:id/header_title");
    static By addYourbussiness = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout[1]/android.widget.TextView[1]");

    static By sectionTaddYourbussinessDropDown= By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout[1]/android.widget.Spinner/android.widget.TextView");
    static By dropdownValueAnimal = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.TextView[1]");
    static By dropdownValueOther = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.TextView[2]");
    static By sinceWhenDropdown = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout[1]/android.widget.LinearLayout/android.widget.Spinner/android.widget.TextView");
    static By sinceWhenDropdownValue1 = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.TextView[2]");
    static By sinceWhenDropdownValue2 = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.TextView[3]");

    static By AddBtn  = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout[1]/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.LinearLayout");
    static By addBusinessViewAnimal = By.id("com.wavar:id/item_tv");
    static By addBusinessViewOther = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.GridView/android.widget.LinearLayout[2]/android.widget.RelativeLayout/android.widget.TextView");
    static By toast_3to5 = By.xpath("/hierarchy/android.widget.Toast");
    static By Other_5to10 = By.xpath("/hierarchy/android.widget.Toast");
    static By saveBnt = By.id("com.wavar:id/btn_save_jodhnda");
        public void myProfle() throws InterruptedException {
            waitForElement(profileBtn);
            driver.findElement(profileBtn).click();
            Thread.sleep(2000);
            swipeUp(580,2144,514,367);
        }
        public void sideBussiness_section() throws InterruptedException {
            //String sectionNameVerify = driver.findElement(sectonName).getText();
            //   Assert.assertEquals("Side business",sectionNameVerify);
            waitForElement(sideAddBtn);
            driver.findElement(sideAddBtn).click();
            String sectionTileNameverify = driver.findElement(sectionTitleName).getText();
            Assert.assertEquals("Side business", sectionTileNameverify);
            String addYourbussinessVerify = driver.findElement(addYourbussiness).getText();
            Assert.assertEquals("Add your Side business", addYourbussinessVerify);

            driver.findElement(sectionTaddYourbussinessDropDown).click();
            waitForElement(dropdownValueAnimal);
            driver.findElement(dropdownValueAnimal).click();

            driver.findElement(sinceWhenDropdown).click();
            waitForElement(sinceWhenDropdownValue1);
            driver.findElement(sinceWhenDropdownValue1).click();
            driver.findElement(AddBtn).click();
            String ViewBusiness = driver.findElement(addBusinessViewAnimal).getText();
            System.out.println("Added business card : "+ ViewBusiness);
            Assert.assertEquals("Animal Husbandry (Goat/Sheep/Poultry/Fish)   [ 3 to 5 Years ]", ViewBusiness);

            try {
                driver.findElement(AddBtn).click();
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
                wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(toast_3to5));
                String toast_3to5Verify = driver.findElement(toast_3to5).getText();
                Assert.assertEquals("Animal Husbandry (Goat/Sheep/Poultry/Fish) already added", toast_3to5Verify);
                System.out.println("Toast message verified : " + toast_3to5Verify);
            } catch (NoSuchElementException e) {
                System.out.println("Element not found or wait timed out: " + e.getMessage());
            }

            driver.findElement(sectionTaddYourbussinessDropDown).click();
            waitForElement(dropdownValueOther);
            driver.findElement(dropdownValueOther).click();

            driver.findElement(sinceWhenDropdown).click();
            waitForElement(sinceWhenDropdownValue2);
            driver.findElement(sinceWhenDropdownValue2).click();
            driver.findElement(AddBtn).click();
            String ViewBusinessOtherVerify = driver.findElement(addBusinessViewOther).getText();
            System.out.println("Added business card : "+ ViewBusinessOtherVerify);
            Assert.assertEquals("Others   [ 5 to 10 Years ]", ViewBusinessOtherVerify);

            try {
                driver.findElement(AddBtn).click();
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
                wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(Other_5to10));
                String toast_Other_5to10 = driver.findElement(Other_5to10).getText();
                Assert.assertEquals("Others already added", toast_Other_5to10);
                System.out.println("Toast message verified : " + toast_Other_5to10);
            } catch (NoSuchElementException e) {
                System.out.println("Element not found or wait timed out: " + e.getMessage());
            }
            try {
                driver.findElement(saveBnt).click();
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
                wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(SuccessfullyAddedToast));
                String toast_Successfully = driver.findElement(SuccessfullyAddedToast).getText();
                Assert.assertEquals("User Category Business added successfully.", toast_Successfully);
                System.out.println("Toast message verified : " + toast_Successfully);
            } catch (NoSuchElementException e) {
                System.out.println("Element not found or wait timed out: " + e.getMessage());
            }

        }
        static By ediBtn = By.id("com.wavar:id/edit_joddhanda_tv");
        static By cardCrossBtn = By.id("com.wavar:id/remove_skills");
        static By SuccessfullyAddedToast = By.xpath("/hierarchy/android.widget.Toast");
        public void Edit_Side_business(){
            driver.findElement(ediBtn).click();
            driver.findElement(cardCrossBtn).click();
            try {
                driver.findElement(saveBnt).click();
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
                wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(SuccessfullyAddedToast));
                String toast_Successfully = driver.findElement(SuccessfullyAddedToast).getText();
                Assert.assertEquals("User Category Business added successfully.", toast_Successfully);
                System.out.println("Toast message verified : " + toast_Successfully);
            } catch (NoSuchElementException e) {
                System.out.println("Element not found or wait timed out: " + e.getMessage());
            }

        }
        static By ABsaveBtn = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.widget.RelativeLayout/androidx.viewpager.widget.ViewPager/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.TextView");
    static By ABAboutBussinessTile = By.id("com.wavar:id/about_us_title");
    static By ABBest_educational_qualification = By.id("com.wavar:id/lblSprEducation");
    static By ABBest_educational_qualificationDrop = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.widget.Spinner[1]/android.widget.TextView");
    static By ABBest_educational_qualificationDropOption = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.TextView[2]");
    static By ABSkillSet = By.id("com.wavar:id/lblSprSkillSet");
    static By ABSkillSetDrop = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.widget.Spinner[2]/android.widget.TextView");
    static By ABSkillSetDropOption = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.TextView[2]");
    static By ABlblAgriLand = By.id("com.wavar:id/lblAgriLand");
    static By ABlblAgriLandDrop = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.widget.Spinner[3]/android.widget.TextView");
    static By ABlblAgriLandDropOption = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.TextView[2]");
    static By ABlblFarmerSinceTitle = By.id("com.wavar:id/lblFarmerSince");

    static By ABlblFarmerSinceTextBox = By.id("com.wavar:id/edt_farmer_since");
    static By ABlblInputPurchaseTitle = By.id("com.wavar:id/lblInputPurchase");
    static By ABlblInputPurchaseTitleTextBox = By.id("com.wavar:id/edt_input_purchase");
    static By rbtn_drone_yes = By.id("com.wavar:id/rbtn_drone_yes");

    static By rbtn_register_yes = By.id("com.wavar:id/rbtn_register_yes");
    static By ABAgriAward = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]/android.widget.RelativeLayout/android.widget.ImageView");
   // static By ABsaveBtn = By.id("com.wavar:id/btn_save_sheti_vyavayik");


        public void Aboutbussiness(){
            driver.findElement(ABsaveBtn).click();
            String TitleAboutBusiness = driver.findElement(ABAboutBussinessTile).getText();
            Assert.assertEquals("About business", TitleAboutBusiness);

            String Best_educational_qualification = driver.findElement(ABBest_educational_qualification).getText();
            Assert.assertEquals("Best educational qualification", Best_educational_qualification);
            driver.findElement(ABBest_educational_qualificationDrop).click();
            driver.findElement(ABBest_educational_qualificationDropOption).click();


            String ABSkillSetTile = driver.findElement(ABSkillSet).getText();
            Assert.assertEquals("Skill Set", ABSkillSetTile);
            driver.findElement(ABSkillSetDrop).click();
            driver.findElement(ABSkillSetDropOption).click();

            String ABlblAgriLandTitle = driver.findElement(ABlblAgriLand).getText();
            Assert.assertEquals("Agri Land Owned or Rented", ABlblAgriLandTitle);
            driver.findElement(ABlblAgriLandDrop).click();
            driver.findElement(ABlblAgriLandDropOption).click();

            String ABlblFarmerSinceVerifyTitle = driver.findElement(ABlblFarmerSinceTitle).getText();
            Assert.assertEquals("How many years of farming experience?", ABlblFarmerSinceVerifyTitle);
            driver.findElement(ABlblFarmerSinceTextBox).sendKeys("5 years");

            String ABlblInputPurchaseVerifyTitle = driver.findElement(ABlblInputPurchaseTitle).getText();
            Assert.assertEquals("Input purchase from ?", ABlblInputPurchaseVerifyTitle);
            driver.findElement(ABlblInputPurchaseTitleTextBox).sendKeys("locals shops");

            swipeUp(589,1803,632,1127);

            driver.findElement(rbtn_drone_yes).click();
            driver.findElement(rbtn_register_yes).click();

            swipeUp(617,1983,589,1803);

            driver.findElement(ABAgriAward).click();
           // driver.findElement(ABsaveBtn).click();
















        }

    @Test()
        public void Edit_about_me_Section() throws IOException, InterruptedException {
        //register.Select_lang();
        //register.enterNumber();
       // register.Notificationbaropen();
       // register.ReadOTP();
       // register.register_yourself();
        myProfle();
        //sideBussiness_section();
       // Edit_Side_business();
        Aboutbussiness();
    }

}
