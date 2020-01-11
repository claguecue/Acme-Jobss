
package acme.entities.investors;

import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Investor extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	private String				investorName;

	@NotBlank
	private String				sector;

	@NotBlank
	private String				investingStatement;

	@Min(value = 0)
	@Max(value = 5)
	private Integer				starsNumber;

}
