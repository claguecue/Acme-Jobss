/*
 * AcmeRequest.java
 *
 * Copyright (c) 2019 Aureliano Piqueras, based on Rafael Corchuelo's DP Starter project.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.entities.customizations;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(indexes = {
	@Index(columnList = "activeID", unique = true)
})
public class Customization extends DomainEntity {

	// Serialization identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotNull
	@Digits(integer = 3, fraction = 2)
	@Min(0)
	@Max(100)
	private Double				spamThreshold;

	@NotBlank
	//	@Pattern(regexp = "^(\\S+(\\s\\S+)*(,\\S)*)*$")
	@Pattern(regexp = "^(.+?)(?:,|$)$")
	private String				spamWords;

	@NotNull
	private Integer				activeID;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}
