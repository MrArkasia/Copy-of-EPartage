package domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Class representing users
 * 
 * @author
 *
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_U")
	private Integer id;

	@Lob
	@Column(name = "AVATAR")
	private byte[] avatar;

	@Column(name = "FIRSTNAME")
	// , nullable = false)
	@NotEmpty(message = "Veuillez saisir votre pr�nom !")
	@NotNull
	private String firstName;

	@Column(name = "LASTNAME", nullable = false)
	@NotEmpty(message = "Veuillez saisir votre nom !")
	private String lastName;

	@Column(name = "ADRESS", nullable = false)
	@NotEmpty(message = "Veuillez saisir votre adresse !")
	private String adress;

	@Column(name = "PHONE")
	@Length(min = 10, max = 10, message = "Veuillez saisir un num�ro de t�l�phone valide !")
	private String phone;

	@Column(name = "BIRTHDATE")
	@Temporal(TemporalType.DATE)
	@Past(message = "Veuillez saisir une date dans le pass� !")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotNull(message = "Veuillez saisir votre date de naissance !")
	private Date birthDate;

	@Column(name = "INSCRIPTAPPDATE")
	@Temporal(TemporalType.DATE)
	private Date inscriptAppDate;

	@Column(name = "PASSWORD", nullable = false)
	@NotEmpty(message = "Veuillez saisir votre mot de passe !")
	private String password;

	@Column(name = "EMAIL", nullable = false, unique = true)
	@Email(message = "Veuillez saisir une adresse �lectronique valide !")
	@NotEmpty(message = "Veuillez saisir votre adresse �lectronique !")
	private String email;

	@Column(name = "STATUS")
	@Enumerated(EnumType.STRING)
	private Status status;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
	private Set<AcademicPeriod> academicPeriods;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
	private Set<ProfessionalPeriod> professionnalPeriods;

	@ManyToMany
	@JoinTable(
		      name="UserHobby",
		      joinColumns={@JoinColumn(name="id_u", referencedColumnName="ID_U")},
		      inverseJoinColumns={@JoinColumn(name="nameH", referencedColumnName="nameH")})
	private Set<Hobby> hobbys;

	@ManyToMany
	@JoinTable(
		      name="MembershipGroup",
		      joinColumns={@JoinColumn(name="id_u", referencedColumnName="ID_U")},
		      inverseJoinColumns={@JoinColumn(name="nameG", referencedColumnName="nameG")})
	private Set<Group> groups;

	public User() {
		super();
	}

	public byte[] getAvatar() {
		return avatar;
	}

	public void setAvatar(byte[] avatar) {
		this.avatar = avatar;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Date getInscriptAppDate() {
		return inscriptAppDate;
	}

	public void setInscriptAppDate(Date inscriptAppDate) {
		this.inscriptAppDate = inscriptAppDate;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public Set<AcademicPeriod> getAcademicPeriods() {
		return academicPeriods;
	}

	public void setAcademicPeriods(Set<AcademicPeriod> academicPeriods) {
		this.academicPeriods = academicPeriods;
	}

	public Set<ProfessionalPeriod> getProfessionnalPeriods() {
		return professionnalPeriods;
	}

	public void setProfessionnalPeriods(
			Set<ProfessionalPeriod> professionnalPeriods) {
		this.professionnalPeriods = professionnalPeriods;
	}

	public Set<Hobby> getHobbys() {
		return hobbys;
	}

	public void setHobbys(Set<Hobby> hobbys) {
		this.hobbys = hobbys;
	}

	public Set<Group> getGroups() {
		return groups;
	}

	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}
}
