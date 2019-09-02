package automation.utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.message.BasicHttpEntityEnclosingRequest;
import org.openqa.selenium.remote.HttpCommandExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;

public final class GridInfoExtractor {

	  private GridInfoExtractor() {
	  }
	  
	  public static GridInfo getHostNameAndPort(RemoteWebDriver driver){
		  HttpCommandExecutor ce = (HttpCommandExecutor) ((RemoteWebDriver) driver).getCommandExecutor();		
		  return getHostNameAndPort(ce.getAddressOfRemoteServer().getHost(), ce.getAddressOfRemoteServer().getPort(), ((RemoteWebDriver) driver).getSessionId());
	  }

	  public static GridInfo getHostNameAndPort(String hubHost, int hubPort, SessionId session) {

	    GridInfo retVal = null;

	    return retVal;
	  }

	  private static Object extractObject(HttpResponse resp) throws IOException {
	    
		 BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resp.getEntity().getContent()));
		 try {
	      StringBuilder stringBuilder = new StringBuilder();
	      String line;
	      while ((line = bufferedReader.readLine()) != null) {
	        stringBuilder.append(line);
	      }
	      return null;
	    } catch (Exception e) {
	      System.out.println("error" + e.toString());
	    } finally {
	    	bufferedReader.close();
	    }
	    return null;
	  }	  
}