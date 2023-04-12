package it.poste.generafile.configurations.checks;

import java.text.SimpleDateFormat;

import net.sf.oval.Validator;
import net.sf.oval.configuration.annotation.AbstractAnnotationCheck;
import net.sf.oval.context.OValContext;
import net.sf.oval.exception.OValException;

public class DateFormatOvalCheck extends AbstractAnnotationCheck<DateFormatOval> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3314396012353620061L;

	@Override
	public boolean isSatisfied(Object validatedObject, Object valueToValidate, OValContext context, Validator validator)
			throws OValException {

		if (valueToValidate == null) {
			return true;
		}

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		formatter.setLenient(false);

		try {
			formatter.parse((String) valueToValidate);
		} catch (Exception e) {
			return false;
		}

		return true;
	}
}
