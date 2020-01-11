
package acme.forms;

import java.io.Serializable;
import java.util.Map;

import acme.entities.applications.ApplicationStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Chart implements Serializable {

	// Serialisation identifier -------------------------------------------------------------

	private static final long		serialVersionUID	= 1L;

	// Atributes ----------------------------------------------------------------------------

	Map<String, Long>				companySector;
	Map<String, Long>				investorSector;

	Map<ApplicationStatus, Long>	applicationStatus;
	Map<Boolean, Long>				jobFinalMode;

}
