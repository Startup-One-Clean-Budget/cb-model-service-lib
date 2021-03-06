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
import javax.validation.constraints.Email;
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
 * Class responsible to map the table "sys_user" on database.
 *
 * @author Brazil Code - Gabriel Guarido
 * @since Apr 25, 2020 11:18:05 PM
 * @version 2.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString(exclude = "password")
@Entity(name = "sys_user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
	@GenericGenerator(name = "user_seq", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "user_seq"), @Parameter(name = "initial_value", value = "1"),
			@Parameter(name = "increment_size", value = "1") })
	private Long id;

	@NotNull(message = "Area reference is mandatory!")
	@ManyToOne
	@JoinColumn(name = "id_area", referencedColumnName = "id", nullable = false)
	private Area area;

	@NotEmpty(message = "Username is mandatory!")
	@Column(length = 20, unique = true, nullable = false)
	private String username;

	@NotEmpty(message = "Password is mandatory!")
	@Column(nullable = false)
	private String password;

	@NotEmpty(message = "First name is mandatory!")
	@Column(length = 30, nullable = false)
	private String firstName;

	@Column(length = 30)
	private String lastName;

	@NotEmpty(message = "E-mail is mandatory!")
	@Column(length = 100, unique = true, nullable = false)
	@Email
	private String email;

	private String token;

	@NotNull(message = "Flag disabled is mandatory!")
	@Column(nullable = false)
	private boolean disabled = false;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_profile", joinColumns = { @JoinColumn(name = "id_user") }, inverseJoinColumns = {
			@JoinColumn(name = "id_profile") })
	private Set<Profile> profiles = new HashSet<>();

	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

	@NotNull(message = "Creation date is mandatory!")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date createdAt;

}
