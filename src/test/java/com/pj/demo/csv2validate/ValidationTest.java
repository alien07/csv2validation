/**
 * 
 */
package com.pj.demo.csv2validate;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.pj.demo.csv2validate.csv.beans.CarBean;
import com.pj.demo.csv2validate.utils.TestUtils;
import com.univocity.parsers.common.processor.BeanListProcessor;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

/**
 * @author alien_admin
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ValidationTest {

	private static final String _FILE = "C:\\Users\\alien_admin\\workspace\\csv2validate\\target\\classes\\employee.csv";

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * t
	 * 
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void validate() throws FileNotFoundException, IllegalArgumentException, IllegalAccessException {
		// BeanListProcessor converts each parsed row to an instance of a given
		// class, then stores each instance into a list.
		BeanListProcessor<CarBean> rowProcessor = new BeanListProcessor<CarBean>(CarBean.class);

		CsvParserSettings parserSettings = new CsvParserSettings();
		parserSettings.setRowProcessor(rowProcessor);
		parserSettings.setHeaderExtractionEnabled(true);

		CsvParser parser = new CsvParser(parserSettings);
		parser.parse(TestUtils.getReader(_FILE));

		// The BeanListProcessor provides a list of objects extracted from the
		// input.
		List<CarBean> allRows = rowProcessor.getBeans();
		System.out.println(TestUtils.printBeans(allRows));

		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();

		for (CarBean carBean : allRows) {
			Set<ConstraintViolation<CarBean>> constraintViolations = validator.validate(carBean);
			System.out.println(TestUtils.printValidation(constraintViolations));
		}
	}
}
