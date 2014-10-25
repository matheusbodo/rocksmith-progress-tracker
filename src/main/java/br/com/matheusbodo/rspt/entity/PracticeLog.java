package br.com.matheusbodo.rspt.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.vaadin.data.fieldgroup.PropertyId;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@MappedSuperclass
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class PracticeLog implements Serializable {

	private static final long serialVersionUID = 1943375838137162650L;

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	@ManyToOne
	@JoinColumn(name="user_id", nullable=false)
	private User user;
	
	@NotNull
	@Column(nullable=false, name="date")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar practiceDate;
	
	@Min(value=0, message="The minutes practiced must be a positive number")
	@Max(value=1440, message="The minutes practiced cannot be greater than 1440")
	@Column(nullable=true)
	@PropertyId(value="minutes")
	private Integer minutes;
}
