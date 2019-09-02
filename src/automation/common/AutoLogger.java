package automation.common;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.WebDriverEventListener;

public class AutoLogger implements WebDriverEventListener{
	
	private Class<?> currentPageClass;
	private static String pageURL;
	private static String elementTag;
	private String logs = "";

	public String getLogs() {
		return logs;
	}
	
	public void setLogs(String logs) {
		this.logs = logs;
	}

	@Override
	public void afterNavigateTo(String url, WebDriver driver) {
		System.out.println("Application navigates to: " + driver.getCurrentUrl());
		logs = logs + "\n " + "Application navigates to: " + driver.getCurrentUrl();
	}

	@Override
	public void afterNavigateBack(WebDriver driver) {
		System.out.println("Application navigates back to: " + driver.getCurrentUrl());	
		logs = logs + "\n " + "Application navigates back to: " + driver.getCurrentUrl();
	}

	@Override
	public void afterClickOn(WebElement element, WebDriver driver) {
		Field field = getFieldForAWebElement(element);
		if (field != null){
			String fieldName = convertCamelCaseToRegularText(field.getName());
			if(!elementTag.toLowerCase().contains("option")){
				System.out.println("Clicked on '" + fieldName + "'");
				logs = logs + "\n " + "Clicked on '" + fieldName + "'";
			}
			else{ 
				System.out.println("Clicked on dropdown '" + fieldName + "'");
				logs = logs + "\n " + "Clicked on dropdown '" + fieldName + "'";
			}
		}
		else{
			System.out.println("Clicked on '" + element.toString().split("->")[1].trim() + "'");
			logs = logs + "\n " + "Clicked on '" + element.toString().split("->")[1].trim() + "'";
		}
		if(!pageURL.toLowerCase().equals(driver.getCurrentUrl().toLowerCase())){
			this.afterNavigateTo(pageURL, driver);
		}
	}
	
	@Override
	public void afterScript(String script, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeNavigateTo(String url, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeNavigateBack(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeNavigateForward(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterNavigateForward(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeFindBy(By by, WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterFindBy(By by, WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeClickOn(WebElement element, WebDriver driver) {	
		elementTag = element.getTagName();
		pageURL = driver.getCurrentUrl();
	}

	@Override
	public void beforeScript(String script, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onException(Throwable throwable, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public Class<?> getCurrentPageClass() {
		return currentPageClass;
	}

	public void setCurrentPageClass(Class<?> currentPageClass) {
		this.currentPageClass = currentPageClass;
	}
	
	/**
	 * Description - Method returns FindBy annotation text of the field of a class.
	 */
	private String getFindByTextOfField(Field field){
		Annotation annotation = field.getAnnotation(FindBy.class);
		if(annotation instanceof FindBy){
			FindBy fieldAnnotation = (FindBy) annotation;
			if (!fieldAnnotation.id().isEmpty())
				return "id: " + fieldAnnotation.id();
			else if (!fieldAnnotation.css().isEmpty())
				return "css selector: " + fieldAnnotation.css();
			else if (!fieldAnnotation.xpath().isEmpty())
				return "xpath: " + fieldAnnotation.xpath();
			else if (!fieldAnnotation.className().isEmpty())
				return "class name: " + fieldAnnotation.className();
			else if (!fieldAnnotation.name().isEmpty())
				return "name: " + fieldAnnotation.name();
			else if (!fieldAnnotation.linkText().isEmpty())
				return "link text: " + fieldAnnotation.linkText();
			else if (!fieldAnnotation.partialLinkText().isEmpty())
				return "partial link text: " + fieldAnnotation.partialLinkText();
			else if (!fieldAnnotation.tagName().isEmpty())
				return "tagName: " + fieldAnnotation.tagName();
		}
		return null;
	}
	
	/**
	 * Description - Method used to convert Camel Case text to Regular text.
	 */
	private String convertCamelCaseToRegularText(String camelCase){
		camelCase = camelCase.replaceAll("(.)(\\p{Upper})", "$1_$2").replaceAll("_", " ").toLowerCase();
		String firstChar = camelCase.substring(0, 1).toUpperCase();
		return (firstChar + camelCase.substring(1));
	}
	
	/**
	 * Description - Method returns all the fields of a class and its superclasses.
	 */
	private ArrayList<Field> getListofFieldsOfClass(){
		Class<?> currentClass = currentPageClass;
		ArrayList<Field> fields = new ArrayList<Field>();
		for(Field field :currentClass.getFields()){
			fields.add(field);
		}
		while(currentClass.getSuperclass()!= null){
			for (Field field: currentClass.getDeclaredFields()){
				fields.add(field);
			}
		    currentClass = currentClass.getSuperclass();
		}
		
		return fields;
	}
	
	/**
	 * Description - Method returns field  object which is used for a webelement in a page class.
	 */
	public Field getFieldForAWebElement(WebElement element){
		ArrayList<Field> fields = getListofFieldsOfClass();
		for (Field field : fields) {
			field.setAccessible(true);
			if(field.getType() ==  WebElement.class || field.getType() ==  List.class){
				String findByText = getFindByTextOfField(field);	
				if (findByText!= null && element.toString().contains(findByText.trim())){
						return field;
					}
			}
        }
		return null;
	}

	public void afterChangeValueOf(WebElement element, WebDriver arg1, CharSequence[] arg2) {
		Field field = getFieldForAWebElement(element);
		if (field != null){
			String fieldName = convertCamelCaseToRegularText(field.getName());
			if (arg2 != null){
				System.out.println("Entered '" + arg2[0].toString() + "' in '" + fieldName + "'");
				logs = logs + "\n " + "Entered '" + arg2[0].toString() + "' in '" + fieldName + "'";
			}
			else {
				System.out.println("Cleared text from field '" + fieldName + "'");
				logs = logs + "\n " + "Cleared text from field '" + fieldName + "'";
			}
		}
		else{
			if (arg2 != null){
				System.out.println("Entered '" + arg2[0].toString() + "' in '" + element.toString().split("->")[1].trim() + "'");
				logs = logs + "\n " + "Entered '" + arg2[0].toString() + "' in '" + element.toString().split("->")[1].trim() + "'";
			}
			else{
				System.out.println("Cleared text from field '" + element.toString().split("->")[1].trim() + "'");
				logs = logs + "\n " + "Cleared text from field '" + element.toString().split("->")[1].trim() + "'";
			}
		}
		
	}

	@Override
	public void afterNavigateRefresh(WebDriver arg0) {
		// TODO Auto-generated method stub
	}

	public void beforeChangeValueOf(WebElement arg0, WebDriver arg1, CharSequence[] arg2) {
		// TODO Auto-generated method stub
	}

	@Override
	public void beforeNavigateRefresh(WebDriver arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void afterAlertAccept(WebDriver arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterAlertDismiss(WebDriver arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeAlertAccept(WebDriver arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeAlertDismiss(WebDriver arg0) {
		// TODO Auto-generated method stub
		
	}
}
