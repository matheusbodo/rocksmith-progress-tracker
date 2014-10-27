package br.com.matheusbodo.rspt.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.com.matheusbodo.rspt.entity.enums.Role;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name="users")
public class User implements Serializable {

	private static final long serialVersionUID = 783532862260768570L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@NotNull
	@Column(unique=true)
	private String email;
	
	@Column
	private String password;

	@Column
	@NotNull
	@Enumerated(EnumType.STRING)
	private Role role;
	
}
