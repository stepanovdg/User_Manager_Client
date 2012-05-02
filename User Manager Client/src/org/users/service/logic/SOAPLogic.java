package org.users.service.logic;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

/**
 * Provide soap client logic of application
 * 
 * @author Andrey Kovrigin
 * @since 2012
 * @version 1.0 04/25/2012
 */
public class SOAPLogic {

	/**
	 * Send request to service
	 * 
	 * @param methodName method name
	 * @param namespace namespace
	 * @param url url
	 * @param pi property info
	 * @param value property info value
	 * @return String result of request
	 */
	public String doRequest(String methodName, String namespace, String url,
			PropertyInfo pi, long value) {
		String soapAction = namespace + methodName;
		String resultData = null;
		try {
			SoapObject request = new SoapObject(namespace, methodName);
			request.addProperty(pi, value);
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);
			envelope.setOutputSoapObject(request);
			HttpTransportSE androidHttpTransport = new HttpTransportSE(url);
			androidHttpTransport.call(soapAction, envelope);
			SoapPrimitive result = (SoapPrimitive) envelope.getResponse();
			resultData = result.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultData;
	}

	/**
	 * Create request parameter
	 * 
	 * @param name parameter name
	 * @param type parameter type
	 * @return PropertyInfo
	 */
	public PropertyInfo createProperty(String name, Object type) {
		PropertyInfo pi = new PropertyInfo();
		pi.name = name;
		pi.type = type;
		return pi;
	}
}
