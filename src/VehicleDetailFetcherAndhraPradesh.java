import java.io.IOException;
import java.net.MalformedURLException;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTableDataCell;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;


public class VehicleDetailFetcherAndhraPradesh extends VehicleDetailFetcher {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public Vehicle getVehicleDetailsFromService(String RegNumber)
			throws FailingHttpStatusCodeException, MalformedURLException,
			IOException {
		// TODO Auto-generated method stub
		 final WebClient webClient = new WebClient();

		    // Get the first page
		    final HtmlPage page1 = webClient.getPage("https://aptransport.in/CFSTONLINE/Reports/VehicleRegistrationSearch.aspx");

		    // Get the form that we are dealing with and within that form, 
		    // find the submit button and the field that we want to change.
		    final HtmlForm form = page1.getFormByName("aspnetForm");

		    final HtmlSubmitInput button = form.getInputByName("ctl00$OnlineContent$btnGetData");
		    final HtmlTextInput textField = form.getInputByName("ctl00$OnlineContent$txtInput");
		    // Change the value of the text field
		    textField.setValueAttribute(RegNumber);
		    // Now submit the form by clicking the button and get back the second page.
		    final HtmlPage page2 = button.click();
		    Vehicle vehicleObj = new Vehicle();
		    try{
		    vehicleObj.setOwnerName(((HtmlTableDataCell)page2.getHtmlElementById("ctl00_OnlineContent_tdOwner")).asText());
		    vehicleObj.setChasisNo(((HtmlTableDataCell)page2.getHtmlElementById("ctl00_OnlineContent_tdChassisno")).asText());
		    vehicleObj.setRegistrationNumber(((HtmlTableDataCell)page2.getHtmlElementById("ctl00_OnlineContent_tdRegnNo")).asText());
		    vehicleObj.setVehicleClass(((HtmlTableDataCell)page2.getHtmlElementById("ctl00_OnlineContent_tdVehClass")).asText());
		    vehicleObj.setMfgYear(((HtmlTableDataCell)page2.getHtmlElementById("ctl00_OnlineContent_tdMfgYear")).asText());
		    vehicleObj.setEngineNo(((HtmlTableDataCell)page2.getHtmlElementById("ctl00_OnlineContent_tdEngNo")).asText());
		    vehicleObj.setPrevRegn(((HtmlTableDataCell)page2.getHtmlElementById("ctl00_OnlineContent_tdPrevRegn")).asText());
		    vehicleObj.setFuelType(((HtmlTableDataCell)page2.getHtmlElementById("ctl00_OnlineContent_tdFuelType")).asText());
		    vehicleObj.setVehicleColor(((HtmlTableDataCell)page2.getHtmlElementById("ctl00_OnlineContent_tdColor")).asText());
		    vehicleObj.setMakersName(((HtmlTableDataCell)page2.getHtmlElementById("ctl00_OnlineContent_tdMkrName")).asText());
		    vehicleObj.setMakersClass(((HtmlTableDataCell)page2.getHtmlElementById("ctl00_OnlineContent_tdMkrClass")).asText());
		    vehicleObj.setDateofRegistration(((HtmlTableDataCell)page2.getHtmlElementById("ctl00_OnlineContent_tdDOR")).asText());
		    vehicleObj.setFinancier(((HtmlTableDataCell)page2.getHtmlElementById("ctl00_OnlineContent_tdFin")).asText());
		    }
		    catch(ElementNotFoundException e)
		    {
		    	
		    	vehicleObj = null;
		    }
		return vehicleObj;
		
		
	}

}
