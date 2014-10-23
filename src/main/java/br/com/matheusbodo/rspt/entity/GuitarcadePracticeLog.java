package br.com.matheusbodo.rspt.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.vaadin.data.fieldgroup.PropertyId;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import br.com.matheusbodo.rspt.entity.enums.GuitarcadeGame;
import br.com.matheusbodo.rspt.entity.enums.GuitarcadeGameLevel;

@Getter
@Setter
@EqualsAndHashCode(callSuper=true)
@Entity
@Table(name="guitarcade_practice_logs")
public class GuitarcadePracticeLog extends PracticeLog {

	private static final long serialVersionUID = 2906995567896993449L;

	@NotNull(message="Score must be informed.")
	@Min(value=0, message="The score must be a positive number")
	@Max(value=999999999, message="The minutes practiced cannot be greater than 999999999")
	@Column(nullable=false)
	@PropertyId(value="score")
	private Long score;
	
	@NotNull(message="Guitarcade game must be informed.")
	@Column(length=30)
	@Enumerated(EnumType.STRING)
	@PropertyId(value="game")
	private GuitarcadeGame game;
	
	@Column(name="game_level", length=100)
	@Enumerated(EnumType.STRING)
	@PropertyId(value="gameLevel")
	private GuitarcadeGameLevel gameLevel;
}
