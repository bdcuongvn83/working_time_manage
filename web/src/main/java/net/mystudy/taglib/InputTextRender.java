/**
 * 
 */
package net.mystudy.taglib;

import java.io.IOException;
import java.util.Date;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import utils.DateUtils;

/**
 * @author cuongbd
 *
 */
@FacesComponent(value = "components.CustomComponent", createTag = true, tagName = "helloComponent", namespace = "http://mystudy.com/tags")
public class InputTextRender extends UIComponentBase {

	@Override
	public String getFamily() {
		// TODO Auto-generated method stub
		 return "Greeting";
	}
	
	@Override
	  public void encodeBegin(FacesContext context) throws IOException {
	      String message = (String) getAttributes().get("message");
	      Date time = (Date) getAttributes().get("time");
	      

	      ResponseWriter writer = context.getResponseWriter();
	      writer.startElement("p", this);
	      writer.write("Message: " + message);
	      writer.endElement("p");

	      writer.startElement("p", this);
	      writer.write("Time: " + DateUtils.formatDate(time, DateUtils.YYYY_MM_DD_HHMMSS));
	      writer.endElement("p");
	  }

}
