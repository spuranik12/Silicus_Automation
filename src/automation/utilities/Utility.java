package automation.utilities;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringEscapeUtils;
import org.openqa.selenium.WebElement;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

public class Utility {

	public URI jiraServerUri;
	private static Calendar cal=Calendar.getInstance();;
	private static SimpleDateFormat simpleDateFormat;
	private static final String DATE_FORMAT_NOW = "MM-dd-yy";
	private static final String TIME_FORMAT_NOW = "HH:mm a";

	public static HashMap<String, String> compareStringsIntoDictionary(String actual, String expected,
			HashMap<String, String> result) {
		if (!actual.equals(expected))
			result.put(actual, expected);
		return result;
	}

	public static String mapToString(HashMap<String, String> hashMap) {
		String result = null;
		for (String key : hashMap.keySet()) {
			result += String.join(": ", key, hashMap.get(key), "\n");
		}
		return result;
	}

	/**
	 * @Description - This method returns random number .
	 */
	public static int getRandomNumber(int size){
		Random rand = new Random();
		if (size==0){
			return 0;
		}else
			return rand.nextInt(size);
	}
	/**
	 *
	 * @param str
	 * @return Float
	 */
	public static Float convertPriceStrToFloat(String str){

		if(str.contains("$")) {
			str = str.split("\\$")[1];
		}
		Float price=Float.valueOf(0);
		try{
			price = Float.valueOf(str);
		} catch(NumberFormatException ex){
			DecimalFormat decimalFormat = new DecimalFormat();
			Number number = null;
			try{
				number = decimalFormat.parse(str);
			} catch(ParseException ex2){
			}
			if(number != null)
				price = number.floatValue();
		}
		return price;
	}

	/**
	 * @Description - This method returns parse Currency .
	 */
	public static double parseCurrency(String currency) throws Exception{
		NumberFormat cf = NumberFormat.getCurrencyInstance();
		return cf.parse(currency).doubleValue();
	}

	/**
	 * @Description - This method finds specified pattern in the string.
	 * @param - patternToCompile = pattern to search in the string
	 * @param - stringToFind match = the string in which to find this pattern.
	 */
	public static Matcher matchPattern(String patternToCompile,String stringToFindMatch){
		Pattern pattern= Pattern.compile(patternToCompile);
		return pattern.matcher(stringToFindMatch);
	}

	/**
	 * @Description - This method format the date in user defined format.
	 * @param - inputDate - should be always in 'MM/dd/yyyy'
	 * @param - dateFormate - should be expected date format.
	 */
	public String formatDate(String inputDate, String dateFormat) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date d = sdf.parse(inputDate);
		sdf.applyPattern(dateFormat);
		return sdf.format(d);
	}

	/**
	 *  @Description -generate random email ids
	 */
	public static String generateEmail() {
		String alphabet = new String("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz");
		String randomEmail = new String();
		Random r = new Random();
		for (int i = 0; i < 10; i++)
			randomEmail = randomEmail+ alphabet.charAt(r.nextInt(alphabet.length()));
		randomEmail = randomEmail + "@mailinator.com";
		return randomEmail;
	}

	public static double modifyPrice(double actualPrice, double money, String action){
		double modifiedPrice = 0;
		if (action == "add"){
			modifiedPrice = actualPrice + money;
		}else if(action == "minus"){
			modifiedPrice = actualPrice - money;
		}
		//
		//...else if (action == "multiply")

		return modifiedPrice ;
	}

	/*
	 * This method will decode any UTF8 encoded URL
	 * @param linkUrl
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String decodeURL(String linkUrl) throws UnsupportedEncodingException {
		return java.net.URLDecoder.decode(linkUrl, "UTF-8");
	}

	/*
	 * Method return file extension.
	 * */
	public static String getFileExtension(File file) {
		String fileName = file.getName();
		if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
			return fileName.substring(fileName.lastIndexOf(".") + 1);
		else return "";
	}

	/**
	 * @Description - Get connection to JIRA
	 */
	public void getJIRAIssueClient() throws URISyntaxException{
		jiraServerUri = new URI(PropertyDictionary.map.get("JIRA_URL"));
	}

	/**
	 * @Description - Get coonection to JIRA
	 */
	public HashMap<String,String> getOpenIssues(List<String> linkedIssues) throws URISyntaxException{
		String bug=null;
		HashMap<String, String> openIssues=new HashMap<String,String>();
		if(linkedIssues.size()>0){
			for(int i=0;i<linkedIssues.size();i++){
				bug=linkedIssues.get(i).toString().trim();
				//String bugStatus=issueClient.getIssue(linkedIssues.get(i),pm).getStatus().getName();
			}
		}
		return openIssues;
	}

	/**
	 * @Description- get current Date
	 */
	public static String getCurrentDate(){
		simpleDateFormat = new SimpleDateFormat(DATE_FORMAT_NOW);
		return simpleDateFormat.format(cal.getTime());
	}

	/**
	 * @Description- get current Time
	 */
	public static String getCurrentTime(){
		simpleDateFormat = new SimpleDateFormat(TIME_FORMAT_NOW);
		return simpleDateFormat.format(cal.getTime());
	}

	public static long getTimeDiffInSeconds(long start){
		long end = System.currentTimeMillis();
		long diff = ((end - start) / 1000);
		return diff;
	}

	public static String formatDBDate(String dBDate) throws ParseException{
		dBDate = dBDate.replaceAll("00:00:00.0", "").replace(" ", "");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = format.parse(dBDate);
		SimpleDateFormat format2 = new SimpleDateFormat("M/d/yy");  
		return format2.format(date);
	}

	public static String extractTextUsingRegEx(String stringPattern, String searchText){
		Pattern pattern = Pattern.compile(stringPattern);
		Matcher matcher = pattern.matcher(searchText);
		if (matcher.find()){
			return matcher.group(1);
		} else{
			return "NOT FOUND";
		}
	}

	public static String convertToHtmlFormat(String valueToCovert){		
		return StringEscapeUtils.unescapeHtml4(valueToCovert).trim();
	}

	public static String DBDateformat(String dBDate,String dateformat) throws ParseException{
		dBDate = dBDate.split(" ")[0];
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = format.parse(dBDate);
		SimpleDateFormat format2 = new SimpleDateFormat(dateformat);
		return format2.format(date);
	}

	public static String generateRandomEmailAddress(String emailAddress,String emailDomain) {
		String getAppName = null;
		if(emailAddress != null && emailAddress.equalsIgnoreCase("QAAutomation"))
			getAppName=emailAddress;
		else if(!emailDomain.contains("gmail"))
			getAppName = PropertyDictionary.map.get("default.siteName");
		else
			getAppName=PropertyDictionary.map.get("SERVICE-EMAIL_USERNAME")+"+";
		return  getAppName+ System.currentTimeMillis() + "@" + emailDomain ;
	}

	/**
	 * @Description - This method returns last 4 characters of String .
	 */
	public static String getLastFourCharactersOfString(String value){
		return value.trim().substring(value.length() - 4);
	}

	/**
	 *@Description This method will get the size of image from the image url
	 * @param imageUrl
	 * @return
	 * @throws IOException
	 */
	public static int getImageHeight(String imageUrl) throws IOException {
		InputStream url = new URL(imageUrl).openStream();
		try(ImageInputStream in = ImageIO.createImageInputStream(url)){
			final Iterator<ImageReader> readers = ImageIO.getImageReaders(in);
			if (readers.hasNext()) {
				ImageReader reader = readers.next();
				try {
					reader.setInput(in);
					return reader.getHeight(0);
				} finally {
					reader.dispose();
				}
			}
		}
		return 0;
	}

	/**
	 * @Description This method will remove px from text and return as float
	 * @param text
	 * @return
	 */
	public static float removePX(String text ) {
		return Float.parseFloat(text.replaceAll("px", ""));
	}

	public static List<String> convertListOfOjectToListOfString(List<Object> collection){
		List<String> stringCollection = new ArrayList<String>();
		for (Object object : collection) {
			stringCollection.add(object.toString());
		}
		return stringCollection;
	}
	
	public static Map<String, Integer> addSkuAndQuantityInMap(List<Object> skuList, int quantity){
		Map<String, Integer> mapOfItems = new LinkedHashMap<String, Integer>();
		for(int i = 0 ; i < skuList.size() ; i++)
			mapOfItems.put(skuList.get(i).toString(), quantity);
		return mapOfItems;
	}

	public static Float roundOffValueToTwoDigits(Float value){
		return Float.parseFloat(new DecimalFormat("###.##").format(value));
	}
	
	public static Float valueToTwoDigitsWithoutRound(Float value){
		value = ((float) ((int) (Float.parseFloat(new DecimalFormat("###.##").format(value * 100)))))/100;
		return value;
	}
	
	public static String generateRandomString(String text) {
		return text + System.currentTimeMillis();
	}
	
	/**
	 *  @Description -generate random String
	 */
	public static String generateRandomString() {
		String getAppName = null;
		getAppName = PropertyDictionary.map.get("default.siteName");
		return  getAppName+ System.currentTimeMillis() ;
	}

	public static int getItemTextPosition(String itemText, List<WebElement> elements){

		for (int x = 0; x < elements.size(); x++){
			if (elements.get(x).getText().contains(itemText)){
				return x + 1;
			}
		}

		return 0;
	}
	
	public static String normalizeSpace(String text){
		return "";
	}
}

