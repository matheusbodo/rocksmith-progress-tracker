package br.com.matheusbodo.rspt.entity.enums;

public enum GuitarcadeGame {

	GONE_WAILING("Gone Wailing"),
	STRING_SKIP_SALOON("String Skip Saloon"),
	DUCKS_REDUX("Ducks Redux"),
	NINJA_SLIDE("Ninja Slide"),
	RETURN_CASTLE_CHORDEAD("Return to the Castle of Chordead"),
	HURTIN_HURDLES("Hurtin Hurdles"),
	TEMPLE_OF_BENDS("Temple of Bends"),
	SCALE_RACER("Scale Racer"),
	STAR_CHORDS("Star Chords"),
	HARMONIC_HEIST("Harmonic Heist");
	
	private String caption;
	
	private GuitarcadeGame(String caption) {
		this.caption = caption;
	}

	public String getCaption() {
		return caption;
	}
	
}
