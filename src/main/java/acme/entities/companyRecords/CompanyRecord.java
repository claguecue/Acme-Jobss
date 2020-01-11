
package acme.entities.companyRecords;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.URL;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CompanyRecord extends DomainEntity {

	// Serialization identifier -----------------------------------------------
	private static final long	serialVersionUID	= 1L;

	//Atributes----------------------------------

	@NotBlank
	private String				name;

	@NotBlank
	private String				sector;

	@NotBlank
	private String				ceo;

	@NotBlank
	private String				description;

	@NotBlank
	@URL
	private String				website;

	@NotNull
	@Pattern(regexp = "^[\\+]?[0-9]{0,3}[\\s]?[(]?[0-9]{0,4}[)]?[\\s]?[0-9]{6,10}$")
	private String				phone;

	@NotBlank
	@Email
	private String				email;

	private Boolean				isInc;

	@Min(0)
	@Max(5)
	private Integer				starNumber;

}

// Derived attributes -----------------------------------------------------

// Relationships ----------------------------------------------------------
