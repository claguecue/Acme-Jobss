
package acme.entities.auditorRequests;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import acme.framework.entities.DomainEntity;
import acme.framework.entities.UserAccount;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class AuditorRequest extends DomainEntity {

	// Serialisation identifier --------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes ----------------------------------------------------

	@NotBlank
	private String				firm;

	@NotBlank
	@Column(length = 1024)
	private String				statement;

	@NotBlank
	@Pattern(regexp = "^Accepted|Pending|Rejected$")
	private String				status;

	// Relationships -------------------------------------------------

	@NotNull
	@Valid
	@OneToOne
	private UserAccount			userAccount;

}
