package com.shop.model.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;

public class LaunchYearBeforeMfgDateValidator  {
	private int MfgYear;
	
	public void initialize(LaunchYearBeforeMfgDate launchYearBeforeMfgDate) {
		this.MfgYear=launchYearBeforeMfgDate.MfgYear();
	}

	public boolean isValid(Integer launchYear, ConstraintValidatorContext context) {		
		if(launchYear == null) {
			return false;
		}
		if(MfgYear > launchYear  ) {
			return true;
		}
		return false;
	}
		
//	private String MfgYearField;
//	@Override
//	public void initialize(LaunchYearBeforeMfgDate launchYearBeforeMfgDate) {
//		this.MfgYearField = launchYearBeforeMfgDate.MfgYearField();
//	}
//	@Override
//	public boolean isValid(Integer launchYear, ConstraintValidatorContext context) {
//		try {
//			Field field = launchYear.getClass().getDeclaredField(MfgYearField);
//			field.setAccessible(true);
//			Integer MfgYear = (Integer) field.get(launchYear);
//				
//			if (launchYear == null || MfgYear == null) {
//				return false;
//			}	
//			if (MfgYear > launchYear) {
//				return true;
//			}	
//		} catch (NoSuchFieldException | IllegalAccessException e) {
//			
//		}
//			return false;

}
