/**
 * 
 */
package com.pj.demo.csv2validate.csv.beans;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import com.univocity.parsers.annotations.NullString;
import com.univocity.parsers.annotations.Parsed;
import com.univocity.parsers.annotations.Trim;
import com.univocity.parsers.annotations.UpperCase;

/**
 * @author alien_admin
 *
 */
public class CarBean implements Serializable, ErrorMsg {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4522204452944874337L;

	// if the value parsed in the quantity column is "?" or "-", it will be
	// replaced by null.
	@NullString(nulls = { "?", "-" })
	// if a value resolves to null, it will be converted to the String "0".
	@Parsed(field = "year", defaultNullRead = "0")
	private Integer year;

	@Trim
	@Parsed(field = "make")
	private String make;

	@Trim
	@UpperCase
	@Parsed(field = "model")
	private String model;

	@Trim
	@NullString(nulls = { "?", "-", "" })
	@Parsed(field = "description", defaultNullRead = "-")
	private String description;

	@Range(min = 3001, max = 4900)
	@Parsed(field = "price", defaultNullRead = "0.0")
	private BigDecimal price;

	@Min(1800)
	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	@NotBlank(message = CAN_NOT_BLANK)
	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	@NotBlank(message = CAN_NOT_BLANK)
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	@NotBlank(message = CAN_NOT_BLANK)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
