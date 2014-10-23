package br.com.matheusbodo.rspt.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import br.com.matheusbodo.rspt.entity.enums.Role;

@Getter
@Setter
@EqualsAndHashCode(exclude={"user"})
@Entity
@Table(name="user_roles")
public class UserRole {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column
	@Enumerated(EnumType.STRING)
	private Role role;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
}
