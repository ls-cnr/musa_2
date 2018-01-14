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
 * Process generated by hbm2java.
 */
@Entity
@Table(name = "process", catalog = "musa_db")
public class Process implements java.io.Serializable {

	/** The id workflow. */
	private Integer idWorkflow;
	
	/** The description. */
	private String description;
	
	/** The file wf. */
	private byte[] fileWf;
	
	/** The functional reqs. */
	private Set<FunctionalReq> functionalReqs = new HashSet<FunctionalReq>(0);

	/**
	 * Instantiates a new process.
	 */
	public Process() {
	}

	/**
	 * Instantiates a new process.
	 *
	 * @param description the description
	 * @param fileWf the file wf
	 * @param functionalReqs the functional reqs
	 */
	public Process(String description, byte[] fileWf,
			Set<FunctionalReq> functionalReqs) {
		this.description = description;
		this.fileWf = fileWf;
		this.functionalReqs = functionalReqs;
	}

	/**
	 * Gets the id workflow.
	 *
	 * @return the id workflow
	 */
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idWorkflow", unique = true, nullable = false)
	public Integer getIdWorkflow() {
		return this.idWorkflow;
	}

	/**
	 * Sets the id workflow.
	 *
	 * @param idWorkflow the new id workflow
	 */
	public void setIdWorkflow(Integer idWorkflow) {
		this.idWorkflow = idWorkflow;
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

	/**
	 * Gets the file wf.
	 *
	 * @return the file wf
	 */
	@Column(name = "fileWF")
	public byte[] getFileWf() {
		return this.fileWf;
	}

	/**
	 * Sets the file wf.
	 *
	 * @param fileWf the new file wf
	 */
	public void setFileWf(byte[] fileWf) {
		this.fileWf = fileWf;
	}

	/**
	 * Gets the functional reqs.
	 *
	 * @return the functional reqs
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "process")
	public Set<FunctionalReq> getFunctionalReqs() {
		return this.functionalReqs;
	}

	/**
	 * Sets the functional reqs.
	 *
	 * @param functionalReqs the new functional reqs
	 */
	public void setFunctionalReqs(Set<FunctionalReq> functionalReqs) {
		this.functionalReqs = functionalReqs;
	}

}
