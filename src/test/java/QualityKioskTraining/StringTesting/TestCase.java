package QualityKioskTraining.StringTesting;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;



public class TestCase {
	
	ConcatString object;
	String result;
	
	@BeforeClass
	public void init() {
		object=new ConcatString();
	}
	
	@BeforeGroups("SmokeTest")
	public void initBeforGroup() {
		object=new ConcatString();
	}
	@BeforeMethod
	public void reinitialisedResultVar() {
		result=null;
	}
	
	@Test(priority=1,groups="SmokeTest",dataProvider="testCaseData")
	public void concatWithString(String first,String second,String expectedResult) {
		result=object.concatString(first, second);
		Assert.assertEquals(result, expectedResult);
	}
	

	@Test(priority=2)
	public void concatNumbers() {
		result=object.concatString(30,50);
		Assert.assertEquals(result,"Not a String value");
	}
	
	
	@Test
	public void concatNullString() {
		result=object.concatString(null,null);
		Assert.assertEquals(result,"Not a String value");
	}
	
	@DataProvider
	public Object[][] testCaseData() {
		return new Object[][]{
			{ "kajal","shele","kajal shele"}, 
			{"Raj","Shelke","Raj Shelke"}};
	}


	@AfterClass
	public void tearDown() {
		object=null;
	}
	
	@BeforeSuite
	@Parameters({"RequestID"})
	public void createResultFolder(String RequestID) {
		try {
			Files.createDirectories(Paths.get("./"+RequestID));
		} catch (IOException e) {
		System.out.println("There is problem  to create folder");
		}
	}
	
	@AfterSuite
	@Parameters({"RequestID"})
	public void copyReportFile(String RequestID) {
		try {
			Files.copy(Paths.get(".\\target\\surefire-reports\\emailable-report.html"), Paths.get("./"+RequestID+"/Report.html"),StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			System.out.println("Not able to perform the task");}
	}



}
