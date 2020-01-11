
package acme.entities.orems;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.entities.jobs.Job;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Orem extends DomainEntity {

	// Serialisation identifier -------------------------------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes ----------------------------------------------------------------------------------

	@NotBlank
	@Length(max = 256)
	private String				text;

	@URL
	private String				marker;

	// Relationships --------------------------------------------------------------------------------

	@NotNull
	@Valid
	@OneToOne(optional = false)
	private Job					job;

}
