package br.com.matheusbodo.rspt.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import br.com.matheusbodo.rspt.entity.enums.GamePath;

import com.vaadin.data.fieldgroup.PropertyId;

@Getter
@Setter
@EqualsAndHashCode(callSuper=true)
@Entity
@Table(name="learn_song_practice_logs")
public class LearnSongPracticeLog extends PracticeLog {

	private static final long serialVersionUID = 2906995567896993449L;

	@NotNull(message="% completed must be informed.")
	@Min(value=0, message="The % completed must be a positive number")
	@Max(value=110, message="The % completed cannot be greater than 110")
	@Column(nullable=false)
	@PropertyId(value="completed")
	private BigDecimal completed;
	
	@NotNull(message="% of accuracy must be informed.")
	@Min(value=0, message="The % of accuracy must be a positive number")
	@Max(value=100, message="The % of accuracy cannot be greater than 100")
	@Column(nullable=false)
	@PropertyId(value="accuracy")
	private BigDecimal accuracy;
	
	@NotNull(message="Path must be informed.")
	@Column(length=30)
	@Enumerated(EnumType.STRING)
	@PropertyId(value="path")
	private GamePath path;
	
	@NotNull(message="Song must be informed.")
	@ManyToOne
	@JoinColumn(name="song_id")
	private Song song;
	
}
