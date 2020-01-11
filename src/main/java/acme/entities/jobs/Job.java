
package acme.entities.jobs;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.entities.roles.Employer;
import acme.framework.datatypes.Money;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(indexes = @Index(columnList = "reference"))
public class Job extends DomainEntity {

	// Serialisation identifier -------------------------------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes ----------------------------------------------------------------------------------
	//@Column(unique = true)
	@NotBlank
	@Length(min = 5, max = 10)
	private String				reference;

	@NotBlank
	private String				title;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date				deadline;

	@NotNull
	@Valid
	private Money				salary;

	@NotBlank
	private String				description;

	@URL
	private String				moreInfo;

	private Boolean				finalMode;

	// Relationships -----------------------------------------------------------------------------------

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Employer			employer;
}
