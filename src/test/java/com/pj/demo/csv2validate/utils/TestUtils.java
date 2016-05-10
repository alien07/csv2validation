/**
 * 
 */
package com.pj.demo.csv2validate.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import com.pj.demo.csv2validate.csv.beans.CarBean;

/**
 * @author alien_admin
 *
 */
public final class TestUtils {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

	public static String printRows(List<String[]> allRows) {
		StringBuilder result = new StringBuilder(100);

		for (String[] row : allRows) {
			result.append(Arrays.toString(row));
			result.append(System.lineSeparator());
		}

		return result.toString();
	}

	public static <T> String printBeans(List<T> beans) throws IllegalArgumentException, IllegalAccessException {
		StringBuilder result = new StringBuilder(100);

		for (T bean : beans) {
			result.append("[");
			for (Field field : bean.getClass().getDeclaredFields()) {
				field.setAccessible(true);
				result.append(field.getName());
				result.append("=");
				Object value = field.get(bean);
				result.append(String.valueOf(value));
				result.append(", ");
			}
			result.deleteCharAt(result.lastIndexOf(", "));
			result.append("]");
			result.append(System.lineSeparator());
		}
		return result.toString();
	}

	public static String printValidation(Set<ConstraintViolation<CarBean>> constraintViolations) {
		StringBuilder result = new StringBuilder(100);

		if (constraintViolations == null || constraintViolations.isEmpty()) {
			result.append("All validated Pass!");
			result.append(System.lineSeparator());
		} else {
			for (ConstraintViolation<CarBean> constraintViolation : constraintViolations) {
				result.append(constraintViolation.getPropertyPath());
				result.append("=");
				result.append(constraintViolation.getInvalidValue());
				result.append("=>");
				result.append(constraintViolation.getMessage());
				result.append(System.lineSeparator());
			}
		}

		return result.toString();
	}

	public static Reader getReader(String fileName) throws FileNotFoundException {
		Reader reader = new BufferedReader(new FileReader(fileName));
		return reader;
	}

}
