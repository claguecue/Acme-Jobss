
package acme.entities.threads;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import acme.framework.entities.Authenticated;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Thread extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	/*
	 * threads, for which the system must store a title,
	 * the moment when it is created, and the users involved in it
	 */
	@NotBlank
	private String				title;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date				creationMoment;

	// users involved in it = relación entre hilos y usuarios

	// Relationships

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Authenticated		authenticated;

	//	@NotEmpty
	//	@Valid
	//	@OneToMany
	//	private Collection<Message>	message;

}
