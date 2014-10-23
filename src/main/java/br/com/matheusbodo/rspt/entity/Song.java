package br.com.matheusbodo.rspt.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(exclude={"user"})
@Entity
@Table(name="songs")
public class Song implements Serializable {

	private static final long serialVersionUID = 4327585165989919439L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@NotBlank
	@Size(min=0, max=300, message="Artist name length must be between 0 and 300")
	@Column(length=300)
	private String artist;
	
	@NotBlank
	@Size(min=0, max=300, message="Song title length must be between 0 and 300")
	@Column(length=300)
	private String title;
}
