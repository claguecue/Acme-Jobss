
package acme.entities.banners;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.CreditCardNumber;

import acme.entities.roles.Sponsor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CommercialBanner extends Banner {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	@CreditCardNumber
	private String				creditCardNumber;

	@NotBlank
	private String				holderName;

	@NotNull
	@Min(1)
	@Max(12)
	private Integer				expirationMonth;

	@NotNull
	@Min(0)
	@Max(9999)
	private Integer				expirationYear;

	@NotNull
	@Min(1)
	@Max(9999)
	private Integer				cvv;

	// Relationships -----------------------------------------------------------------------------------
	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Sponsor				sponsor;

}
