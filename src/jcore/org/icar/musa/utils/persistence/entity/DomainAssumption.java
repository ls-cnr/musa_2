package org.icar.musa.utils.persistence.entity;

import static javax.persistence.GenerationType.IDENTITY;

// Generated 22-nov-2016 14.40.56 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

// TODO: Auto-generated Javadoc
/**
 * DomainAssumption generated by hbm2java.
 */
@Entity
@Table(name = "domain_assumption", catalog = "musa_db")
public class DomainAssumption implements java.io.Serializable {

	/** The id assumption. */
	private Integer idAssumption;
	
	/** The domain. */
	private Domain domain;
	
	/** The name. */
	private String name;
	
	/** The type. */
	private String type;
	
	/** The description. */
	private String description;

	/**
	 * Instantiates a new domain assumption.
	 */
	public DomainAssumption() {
	}

	/**
	 * Instantiates a new domain assumption.
	 *
	 * @param domain the domain
	 * @param name the name
	 * @param type the type
	 * @param description the description
	 */
	public DomainAssumption(Domain domain, String name, String type,
			String description) {
		this.domain = domain;
		this.name = name;
		this.type = type;
		this.description = description;
	}

	/**
	 * Gets the id assumption.
	 *
	 * @return the id assumption
	 */
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idAssumption", unique = true, nullable = false)
	public Integer getIdAssumption() {
		return this.idAssumption;
	}

	/**
	 * Sets the id assumption.
	 *
	 * @param idAssumption the new id assumption
	 */
	public void setIdAssumption(Integer idAssumption) {
		this.idAssumption = idAssumption;
	}

	/**
	 * Gets the domain.
	 *
	 * @return the domain
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idDomain")
	public Domain getDomain() {
		return this.domain;
	}

	/**
	 * Sets the domain.
	 *
	 * @param domain the new domain
	 */
	public void setDomain(Domain domain) {
		this.domain = domain;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	@Column(name = "name", length = 45)
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
	 * Gets the type.
	 *
	 * @return the type
	 */
	@Column(name = "type", length = 45)
	public String getType() {
		return this.type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	@Column(name = "description", length = 45)
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

}
