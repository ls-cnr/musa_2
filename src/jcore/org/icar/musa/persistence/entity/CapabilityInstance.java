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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

// TODO: Auto-generated Javadoc
/**
 * CapabilityInstance generated by hbm2java.
 */
@Entity
@Table(name = "capability_instance", catalog = "musa_db")
public class CapabilityInstance implements java.io.Serializable {

	/** The id capability instance. */
	private Integer idCapabilityInstance;
	
	/** The concrete capability. */
	private ConcreteCapability concreteCapability;
	
	/** The state. */
	private String state;
	
	/** The agent. */
	private String agent;
	
	/** The id case. */
	private Integer idCase;
	
	/** The capability logs. */
	private Set<CapabilityLog> capabilityLogs = new HashSet<CapabilityLog>(0);

	/**
	 * Instantiates a new capability instance.
	 */
	public CapabilityInstance() {
	}

	/**
	 * Instantiates a new capability instance.
	 *
	 * @param concreteCapability the concrete capability
	 * @param state the state
	 * @param agent the agent
	 * @param idCase the id case
	 * @param capabilityLogs the capability logs
	 */
	public CapabilityInstance(ConcreteCapability concreteCapability,
			String state, String agent, Integer idCase,
			Set<CapabilityLog> capabilityLogs) {
		this.concreteCapability = concreteCapability;
		this.state = state;
		this.agent = agent;
		this.idCase = idCase;
		this.capabilityLogs = capabilityLogs;
	}

	/**
	 * Gets the id capability instance.
	 *
	 * @return the id capability instance
	 */
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idCapability_Instance", unique = true, nullable = false)
	public Integer getIdCapabilityInstance() {
		return this.idCapabilityInstance;
	}

	/**
	 * Sets the id capability instance.
	 *
	 * @param idCapabilityInstance the new id capability instance
	 */
	public void setIdCapabilityInstance(Integer idCapabilityInstance) {
		this.idCapabilityInstance = idCapabilityInstance;
	}

	/**
	 * Gets the concrete capability.
	 *
	 * @return the concrete capability
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idCapability")
	public ConcreteCapability getConcreteCapability() {
		return this.concreteCapability;
	}

	/**
	 * Sets the concrete capability.
	 *
	 * @param concreteCapability the new concrete capability
	 */
	public void setConcreteCapability(ConcreteCapability concreteCapability) {
		this.concreteCapability = concreteCapability;
	}

	/**
	 * Gets the state.
	 *
	 * @return the state
	 */
	@Column(name = "state", length = 45)
	public String getState() {
		return this.state;
	}

	/**
	 * Sets the state.
	 *
	 * @param state the new state
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * Gets the agent.
	 *
	 * @return the agent
	 */
	@Column(name = "agent", length = 45)
	public String getAgent() {
		return this.agent;
	}

	/**
	 * Sets the agent.
	 *
	 * @param agent the new agent
	 */
	public void setAgent(String agent) {
		this.agent = agent;
	}

	/**
	 * Gets the id case.
	 *
	 * @return the id case
	 */
	@Column(name = "idCase")
	public Integer getIdCase() {
		return this.idCase;
	}

	/**
	 * Sets the id case.
	 *
	 * @param idCase the new id case
	 */
	public void setIdCase(Integer idCase) {
		this.idCase = idCase;
	}

	/**
	 * Gets the capability logs.
	 *
	 * @return the capability logs
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "capabilityInstance")
	public Set<CapabilityLog> getCapabilityLogs() {
		return this.capabilityLogs;
	}

	/**
	 * Sets the capability logs.
	 *
	 * @param capabilityLogs the new capability logs
	 */
	public void setCapabilityLogs(Set<CapabilityLog> capabilityLogs) {
		this.capabilityLogs = capabilityLogs;
	}

}
