package br.com.matheusbodo.rspt.entity.enums;

public enum GuitarcadeGame {

	DUCKS_REDUX("Ducks Redux"),
	GONE_WAILING("Gone Wailing"),
	HARMONIC_HEIST("Harmonic Heist"),
	HURTIN_HURDLES("Hurtin Hurdles"),
	NINJA_SLIDE("Ninja Slide"),
	NINJA_WARRIORS("Ninja Warriors"),
	RETURN_CASTLE_CHORDEAD("Return to the Castle of Chordead"),
	SCALE_RACER("Scale Racer"),
	STAR_CHORDS("Star Chords"),
	STRING_SKIP_SALOON("String Skip Saloon"),
	TEMPLE_OF_BENDS("Temple of Bends");
	
	private String caption;
	
	private GuitarcadeGame(String caption) {
		this.caption = caption;
	}

	public String getCaption() {
		return caption;
	}
	
}
