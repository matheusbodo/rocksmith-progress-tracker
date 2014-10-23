package br.com.matheusbodo.rspt.entity.enums;

import java.util.ArrayList;
import java.util.List;

public enum GuitarcadeGameLevel {

	NINJA_WARRIORS_1_1(GuitarcadeGame.NINJA_WARRIORS, "Set 1 - Level 1"),
	NINJA_WARRIORS_1_2(GuitarcadeGame.NINJA_WARRIORS, "Set 1 - Level 2"),
	NINJA_WARRIORS_1_3(GuitarcadeGame.NINJA_WARRIORS, "Set 1 - Level 3"),
	NINJA_WARRIORS_2_1(GuitarcadeGame.NINJA_WARRIORS, "Set 2 - Level 1"),
	NINJA_WARRIORS_2_2(GuitarcadeGame.NINJA_WARRIORS, "Set 2 - Level 2"),
	NINJA_WARRIORS_2_3(GuitarcadeGame.NINJA_WARRIORS, "Set 2 - Level 3"),
	NINJA_WARRIORS_3_1(GuitarcadeGame.NINJA_WARRIORS, "Set 3 - Level 1"),
	NINJA_WARRIORS_3_2(GuitarcadeGame.NINJA_WARRIORS, "Set 3 - Level 2"),
	NINJA_WARRIORS_3_3(GuitarcadeGame.NINJA_WARRIORS, "Set 3 - Level 3"),
	
	CASTLE_CHORDEAD_PROGRESSION_1_1(GuitarcadeGame.RETURN_CASTLE_CHORDEAD, "Progression 1 - 1"),
	CASTLE_CHORDEAD_PROGRESSION_1_2(GuitarcadeGame.RETURN_CASTLE_CHORDEAD, "Progression 1 - 2"),
	CASTLE_CHORDEAD_PROGRESSION_1_3(GuitarcadeGame.RETURN_CASTLE_CHORDEAD, "Progression 1 - 3"),
	CASTLE_CHORDEAD_PROGRESSION_2_1(GuitarcadeGame.RETURN_CASTLE_CHORDEAD, "Progression 2 - 1"),
	CASTLE_CHORDEAD_PROGRESSION_2_2(GuitarcadeGame.RETURN_CASTLE_CHORDEAD, "Progression 2 - 2"),
	CASTLE_CHORDEAD_PROGRESSION_2_3(GuitarcadeGame.RETURN_CASTLE_CHORDEAD, "Progression 2 - 3"),
	CASTLE_CHORDEAD_PROGRESSION_3_1(GuitarcadeGame.RETURN_CASTLE_CHORDEAD, "Progression 3 - 1"),
	CASTLE_CHORDEAD_PROGRESSION_3_2(GuitarcadeGame.RETURN_CASTLE_CHORDEAD, "Progression 3 - 2"),
	CASTLE_CHORDEAD_PROGRESSION_3_3(GuitarcadeGame.RETURN_CASTLE_CHORDEAD, "Progression 3 - 3"),
	CASTLE_CHORDEAD_PROGRESSION_4_1(GuitarcadeGame.RETURN_CASTLE_CHORDEAD, "Progression 4 - 1"),
	CASTLE_CHORDEAD_PROGRESSION_4_2(GuitarcadeGame.RETURN_CASTLE_CHORDEAD, "Progression 4 - 2"),
	CASTLE_CHORDEAD_PROGRESSION_4_3(GuitarcadeGame.RETURN_CASTLE_CHORDEAD, "Progression 4 - 3"),
	CASTLE_CHORDEAD_PROGRESSION_5_1(GuitarcadeGame.RETURN_CASTLE_CHORDEAD, "Progression 5 - 1"),
	CASTLE_CHORDEAD_PROGRESSION_5_2(GuitarcadeGame.RETURN_CASTLE_CHORDEAD, "Progression 5 - 2"),
	CASTLE_CHORDEAD_PROGRESSION_5_3(GuitarcadeGame.RETURN_CASTLE_CHORDEAD, "Progression 5 - 3"),
	CASTLE_CHORDEAD_PROGRESSION_6_1(GuitarcadeGame.RETURN_CASTLE_CHORDEAD, "Progression 6 - 1"),
	CASTLE_CHORDEAD_PROGRESSION_6_2(GuitarcadeGame.RETURN_CASTLE_CHORDEAD, "Progression 6 - 2"),
	CASTLE_CHORDEAD_PROGRESSION_6_3(GuitarcadeGame.RETURN_CASTLE_CHORDEAD, "Progression 6 - 3"),
	CASTLE_CHORDEAD_PROGRESSION_7_1(GuitarcadeGame.RETURN_CASTLE_CHORDEAD, "Progression 7 - 1"),
	CASTLE_CHORDEAD_PROGRESSION_7_2(GuitarcadeGame.RETURN_CASTLE_CHORDEAD, "Progression 7 - 2"),
	CASTLE_CHORDEAD_PROGRESSION_7_3(GuitarcadeGame.RETURN_CASTLE_CHORDEAD, "Progression 7 - 3"),
	CASTLE_CHORDEAD_PROGRESSION_8_1(GuitarcadeGame.RETURN_CASTLE_CHORDEAD, "Progression 8 - 1"),
	CASTLE_CHORDEAD_PROGRESSION_8_2(GuitarcadeGame.RETURN_CASTLE_CHORDEAD, "Progression 8 - 2"),
	CASTLE_CHORDEAD_PROGRESSION_8_3(GuitarcadeGame.RETURN_CASTLE_CHORDEAD, "Progression 8 - 3"),
	CASTLE_CHORDEAD_PROGRESSION_9_1(GuitarcadeGame.RETURN_CASTLE_CHORDEAD, "Progression 9 - 1"),
	CASTLE_CHORDEAD_PROGRESSION_9_2(GuitarcadeGame.RETURN_CASTLE_CHORDEAD, "Progression 9 - 2"),
	CASTLE_CHORDEAD_PROGRESSION_9_3(GuitarcadeGame.RETURN_CASTLE_CHORDEAD, "Progression 9 - 3"),
	CASTLE_CHORDEAD_PROGRESSION_10_1(GuitarcadeGame.RETURN_CASTLE_CHORDEAD, "Progression 10 - 1"),
	CASTLE_CHORDEAD_PROGRESSION_10_2(GuitarcadeGame.RETURN_CASTLE_CHORDEAD, "Progression 10 - 2"),
	CASTLE_CHORDEAD_PROGRESSION_10_3(GuitarcadeGame.RETURN_CASTLE_CHORDEAD, "Progression 10 - 3"),
	CASTLE_CHORDEAD_PROGRESSION_11_1(GuitarcadeGame.RETURN_CASTLE_CHORDEAD, "Progression 11 - 1"),
	CASTLE_CHORDEAD_PROGRESSION_11_2(GuitarcadeGame.RETURN_CASTLE_CHORDEAD, "Progression 11 - 2"),
	CASTLE_CHORDEAD_PROGRESSION_11_3(GuitarcadeGame.RETURN_CASTLE_CHORDEAD, "Progression 11 - 3"),
	CASTLE_CHORDEAD_PROGRESSION_12_1(GuitarcadeGame.RETURN_CASTLE_CHORDEAD, "Progression 12 - 1"),
	CASTLE_CHORDEAD_PROGRESSION_12_2(GuitarcadeGame.RETURN_CASTLE_CHORDEAD, "Progression 12 - 2"),
	CASTLE_CHORDEAD_PROGRESSION_12_3(GuitarcadeGame.RETURN_CASTLE_CHORDEAD, "Progression 12 - 3"),
	CASTLE_CHORDEAD_PROGRESSION_13_1(GuitarcadeGame.RETURN_CASTLE_CHORDEAD, "Progression 13 - 1"),
	CASTLE_CHORDEAD_PROGRESSION_13_2(GuitarcadeGame.RETURN_CASTLE_CHORDEAD, "Progression 13 - 2"),
	CASTLE_CHORDEAD_PROGRESSION_13_3(GuitarcadeGame.RETURN_CASTLE_CHORDEAD, "Progression 13 - 3"),
	
	SCALE_RACER_PENTATONIC_MINOR(GuitarcadeGame.SCALE_RACER, "Pentatonic Minor"),
	SCALE_RACER_PENTATONIC_MAJOR(GuitarcadeGame.SCALE_RACER, "Pentatonic Major"),
	SCALE_RACER_MAJOR(GuitarcadeGame.SCALE_RACER, "Major"),
	SCALE_RACER_AEOLIAN(GuitarcadeGame.SCALE_RACER, "Aeolian"),
	SCALE_RACER_DORIAN(GuitarcadeGame.SCALE_RACER, "Dorian"),
	SCALE_RACER_MIXOLYDIAN(GuitarcadeGame.SCALE_RACER, "Mixolydian"),
	SCALE_RACER_PHRYGIAN(GuitarcadeGame.SCALE_RACER, "Phrygian"),
	SCALE_RACER_LYDIAN(GuitarcadeGame.SCALE_RACER, "Lydian"),
	SCALE_RACER_BLUES(GuitarcadeGame.SCALE_RACER, "Blues"),
	SCALE_RACER_HARMONIC_MINOR(GuitarcadeGame.SCALE_RACER, "Harmonic Minor"),
	SCALE_RACER_PHRYGIAN_DOMINANT(GuitarcadeGame.SCALE_RACER, "Phrygian Dominant")
	;
	
	private String caption;
	private GuitarcadeGame game;
	
	private GuitarcadeGameLevel(GuitarcadeGame game, String caption) {
		this.game = game;
		this.caption = caption;
	}
	
	public GuitarcadeGame getGame() {
		return game;
	}

	public String getCaption() {
		return caption;
	}
	
	public static List<GuitarcadeGameLevel> findByGame(GuitarcadeGame game) {
		List<GuitarcadeGameLevel> levels = new ArrayList<>();
		if (game != null) {
			for (GuitarcadeGameLevel level : GuitarcadeGameLevel.values()) {
				if (game.equals(level.getGame())) {
					levels.add(level);
				}
			}
		}
		return levels;
	}
}
