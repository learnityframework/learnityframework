package coreadministrationv2.sysmgmt.xml.util;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

import comv2.aunwesha.lfutil.Pair;

public class SchemaValidatation {
	private static final String _APPLICATION_TEMPLATE_XSD = "/xsd/application_template.xsd";
	private static final String _THEME_XSD = "/xsd/theme.xsd";
	private static final String _ROLE_XSD = "/xsd/Interfacerole.xsd";
	private static final String _MANIFEST_XSD = "/xsd/Manifest.xsd";
	private static final String _INTERFACE_XSD = "/xsd/Interface.xsd";

	public static Pair<Boolean, String> validateThemeXml(ServletContext servletContext, String xmlPath) {
		return validateXML(servletContext, _THEME_XSD, xmlPath);
	}

	public static Pair<Boolean, String> validateApplicationTemplateXml(ServletContext servletContext, String xmlPath) {
		return validateXML(servletContext, _APPLICATION_TEMPLATE_XSD, xmlPath);
	}

	public static Pair<Boolean, String> validateManifestXml(ServletContext servletContext, String xmlPath) {
		return validateXML(servletContext, _MANIFEST_XSD, xmlPath);
	}

	public static Pair<Boolean, String> validateRoleXml(ServletContext servletContext, String xmlPath) {
		return validateXML(servletContext, _ROLE_XSD, xmlPath);
	}

	public static Pair<Boolean, String> validateInterfaceXml(ServletContext servletContext, String xmlPath) {
		return validateXML(servletContext, _INTERFACE_XSD, xmlPath);
	}

	private static Pair<Boolean, String> validateXML(ServletContext servletContext, String relativeXsdPath, String xmlPath) {
		boolean isSuccess = false;
		String errorMessage = "";
		try {
			String xsdPath = servletContext.getRealPath(relativeXsdPath);
			File schemaFile = new File(xsdPath);
			String validationFeature = "http://xml.org/sax/features/validation";
			String schemaFeature = "http://apache.org/xml/features/validation/schema";

			XSDCustomErrorHandler errorHandler = new XSDCustomErrorHandler();

			Source xmlFile = new StreamSource(new File(xmlPath));

			SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

			Schema schema = schemaFactory.newSchema(schemaFile);
			Validator validator = schema.newValidator();

			validator.setFeature(validationFeature, true);
			validator.setFeature(schemaFeature, true);

			validator.setErrorHandler(errorHandler);

			validator.validate(xmlFile);

			isSuccess = errorHandler.isSuccess();
			errorMessage = errorHandler.getErrorMessage();
		} catch (IOException e) {
			isSuccess = false;
			errorMessage.concat(e.getMessage() + "<br/>");
			e.printStackTrace();
		} catch (SAXException e) {
			isSuccess = false;
			errorMessage.concat(e.getMessage() + "<br/>");
			e.printStackTrace();
		}
		Pair<Boolean, String> validationStatus = new Pair<Boolean, String>(isSuccess, errorMessage);
		return validationStatus;
	}
}
