package jvm.addressbook.actions.utils;

import java.text.MessageFormat;
import java.util.Iterator;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;

public class Utils {

	public static String getResourceString(String rotuloId, Object params[]) {
		FacesContext facesContext = FacesContext.getCurrentInstance();

		Locale locale = facesContext.getViewRoot().getLocale();

		String bundleName = (facesContext != null ) ? facesContext.getApplication().getMessageBundle() : null;

		String text = null;
		ResourceBundle bundle =
			ResourceBundle.getBundle(bundleName, locale,
					getCurrentClassLoader(params));
		try	{
			text = bundle.getString(rotuloId);
		} catch (MissingResourceException e) {
			text = "?? " + rotuloId + " ??";
		}
		if (params != null) {
			MessageFormat mf = new MessageFormat(text, locale);
			text = mf.format(params, new StringBuffer(), null).toString();
		}
		return text;		
	}
	
	public static boolean isMensagensEmpty(){
		Iterator messages = FacesContext.getCurrentInstance().getMessages(null);
		if (messages == null)
			return true;

		return !messages.hasNext();
	}

	private static ClassLoader getCurrentClassLoader(Object defaultObject) {
		ClassLoader loader =
			Thread.currentThread().getContextClassLoader();
		if (loader == null) {
			loader = defaultObject.getClass().getClassLoader();
		}
		return loader;
	}
}
