/**
 * 
 */
package com.pj.demo.csv2validate;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
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
public class CsvTest {

	private static final String _FILE = "C:\\Users\\alien_admin\\workspace\\csv2validate\\target\\classes\\employee.csv";

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	private Reader getReader(String fileName) throws FileNotFoundException {
		Reader reader = new BufferedReader(new FileReader(fileName));
		return reader;
	}

	@Ignore
	@Test
	public void normalPasre() throws FileNotFoundException {
		CsvParserSettings settings = new CsvParserSettings();
		// the file used in the example uses '\n' as the line separator
		// sequence.
		// the line separator sequence is defined here to ensure systems such as
		// MacOS and Windows
		// are able to process this file correctly (MacOS uses '\r'; and Windows
		// uses '\r\n').
		settings.getFormat().setLineSeparator("\n");

		// creates a CSV parser
		CsvParser parser = new CsvParser(settings);

		// URL resource = this.getClass().getResource("/");
		// System.out.println(resource.toString());
		// parses all rows in one go.
		List<String[]> allRows = parser.parseAll(getReader(_FILE));
		System.out.println(TestUtils.printRows(allRows));
	}

	@Test
	public void beanPasre() throws FileNotFoundException, IllegalArgumentException, IllegalAccessException {
		// BeanListProcessor converts each parsed row to an instance of a given
		// class, then stores each instance into a list.
		BeanListProcessor<CarBean> rowProcessor = new BeanListProcessor<CarBean>(CarBean.class);

		CsvParserSettings parserSettings = new CsvParserSettings();
		parserSettings.setRowProcessor(rowProcessor);
		parserSettings.setHeaderExtractionEnabled(true);

		CsvParser parser = new CsvParser(parserSettings);
		parser.parse(getReader(_FILE));

		// The BeanListProcessor provides a list of objects extracted from the
		// input.
		List<CarBean> allRows = rowProcessor.getBeans();
		System.out.println(TestUtils.printBeans(allRows));
	}
}
