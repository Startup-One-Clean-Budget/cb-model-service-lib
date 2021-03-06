package br.com.brazilcode.cb.libs.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Class responsible to map the table "purchase_request" on database.
 *
 * @author Brazil Code - Gabriel Guarido
 * @since Apr 25, 2020 11:19:28 PM
 * @version 2.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity(name = "purchase_request")
public class PurchaseRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "purchase_request_seq")
	@GenericGenerator(name = "purchase_request_seq", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "purchase_request_seq"), @Parameter(name = "initial_value", value = "1"),
			@Parameter(name = "increment_size", value = "1") })
	private Long id;

	@NotNull(message = "Creation user is mandatory!")
	@ManyToOne
	@JoinColumn(name = "id_create_user", referencedColumnName = "id", nullable = false)
	private User createUser;

	@ManyToOne
	@JoinColumn(name = "id_approval_user", referencedColumnName = "id")
	private User approvalUser;

	@NotEmpty(message = "Purchase item is mandatory")
	@Column(length = 150, nullable = false)
	private String purchaseItem;

	@NotNull(message = "Status is mandatory!")
	@Column(nullable = false)
	private int status;

	@NotNull(message = "Purchase requests must have at least 3 price quotations")
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "purchase_request_price_quotation", joinColumns = {
			@JoinColumn(name = "id_purchase_request", nullable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "id_price_quotation", nullable = false) })
	private Set<PriceQuotation> priceQuotations = new HashSet<>();

	@NotNull(message = "Creation date is mandatory!")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

}
