package org.icar.musa.persistence.entity;

// Generated 22-nov-2016 14.40.56 by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

// TODO: Auto-generated Javadoc
/**
 * Domain generated by hbm2java.
 */
@Entity
@Table(name = "domain", catalog = "musa_db")
public class Domain implements java.io.Serializable {

	/** The id domain. */
	private Integer idDomain;
	
	/** The name. */
	private String name;
	
	/** The description. */
	private String description;
	
	/** The domain assumptions. */
	private Set<DomainAssumption> domainAssumptions = new HashSet<DomainAssumption>(
			0);
	
	/** The domain configurations. */
	private Set<DomainConfiguration> domainConfigurations = new HashSet<DomainConfiguration>(
			0);
	
	/** The abstract capabilities. */
	private Set<AbstractCapability> abstractCapabilities = new HashSet<AbstractCapability>(
			0);
	
	/** The specifications. */
	private Set<Specification> specifications = new HashSet<Specification>(0);

	/**
	 * Instantiates a new domain.
	 */
	public Domain() {
	}

	/**
	 * Instantiates a new domain.
	 *
	 * @param name the name
	 */
	public Domain(String name) {
		this.name = name;
	}

	/**
	 * Instantiates a new domain.
	 *
	 * @param name the name
	 * @param description the description
	 * @param domainAssumptions the domain assumptions
	 * @param domainConfigurations the domain configurations
	 * @param abstractCapabilities the abstract capabilities
	 * @param specifications the specifications
	 */
	public Domain(String name, String description,
			Set<DomainAssumption> domainAssumptions,
			Set<DomainConfiguration> domainConfigurations,
			Set<AbstractCapability> abstractCapabilities,
			Set<Specification> specifications) {
		this.name = name;
		this.description = description;
		this.domainAssumptions = domainAssumptions;
		this.domainConfigurations = domainConfigurations;
		this.abstractCapabilities = abstractCapabilities;
		this.specifications = specifications;
	}

	/**
	 * Gets the id domain.
	 *
	 * @return the id domain
	 */
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idDomain", unique = true, nullable = false)
	public Integer getIdDomain() {
		return this.idDomain;
	}

	/**
	 * Sets the id domain.
	 *
	 * @param idDomain the new id domain
	 */
	public void setIdDomain(Integer idDomain) {
		this.idDomain = idDomain;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	@Column(name = "name", nullable = false, length = 250)
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	@Column(name = "description", length = 250)
	public String getDescription() {
		return this.description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the domain assumptions.
	 *
	 * @return the domain assumptions
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "domain")
	public Set<DomainAssumption> getDomainAssumptions() {
		return this.domainAssumptions;
	}

	/**
	 * Sets the domain assumptions.
	 *
	 * @param domainAssumptions the new domain assumptions
	 */
	public void setDomainAssumptions(Set<DomainAssumption> domainAssumptions) {
		this.domainAssumptions = domainAssumptions;
	}

	/**
	 * Gets the domain configurations.
	 *
	 * @return the domain configurations
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "domain")
	public Set<DomainConfiguration> getDomainConfigurations() {
		return this.domainConfigurations;
	}

	/**
	 * Sets the domain configurations.
	 *
	 * @param domainConfigurations the new domain configurations
	 */
	public void setDomainConfigurations(
			Set<DomainConfiguration> domainConfigurations) {
		this.domainConfigurations = domainConfigurations;
	}

	/**
	 * Gets the abstract capabilities.
	 *
	 * @return the abstract capabilities
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "domain")
	public Set<AbstractCapability> getAbstractCapabilities() {
		return this.abstractCapabilities;
	}

	/**
	 * Sets the abstract capabilities.
	 *
	 * @param abstractCapabilities the new abstract capabilities
	 */
	public void setAbstractCapabilities(
			Set<AbstractCapability> abstractCapabilities) {
		this.abstractCapabilities = abstractCapabilities;
	}

	/**
	 * Gets the specifications.
	 *
	 * @return the specifications
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "domain")
	public Set<Specification> getSpecifications() {
		return this.specifications;
	}

	/**
	 * Sets the specifications.
	 *
	 * @param specifications the new specifications
	 */
	public void setSpecifications(Set<Specification> specifications) {
		this.specifications = specifications;
	}

}
