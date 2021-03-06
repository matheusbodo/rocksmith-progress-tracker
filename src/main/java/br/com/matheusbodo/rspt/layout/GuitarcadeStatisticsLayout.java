package br.com.matheusbodo.rspt.layout;

import java.util.Date;

import lombok.Getter;

import org.dussan.vaadin.dcharts.DCharts;

import br.com.matheusbodo.rspt.entity.enums.GuitarcadeGame;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@Getter
public class GuitarcadeStatisticsLayout extends CustomComponent {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private VerticalLayout mainLayout;

	@AutoGenerated
	private VerticalLayout verticalLayout_4;

	@AutoGenerated
	private DCharts chartScoreEvolution;

	@AutoGenerated
	private HorizontalLayout horizontalLayout_2;

	@AutoGenerated
	private ComboBox comboLevel;

	@AutoGenerated
	private ComboBox comboGame;

	@AutoGenerated
	private Label lblScoreEvolution;

	@AutoGenerated
	private Label lblHr;

	@AutoGenerated
	private HorizontalLayout horizontalLayout_1;

	@AutoGenerated
	private VerticalLayout verticalLayout_2;

	@AutoGenerated
	private DCharts chartGamesPlayed;

	@AutoGenerated
	private Label lblGamesPlayed;

	@AutoGenerated
	private VerticalLayout verticalLayout_3;

	@AutoGenerated
	private Table tbSummary;

	@AutoGenerated
	private Label lblSummary;

	@AutoGenerated
	private Label lblTitle;

	private static final long serialVersionUID = -4457385113175348941L;
	
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	@SuppressWarnings("unchecked")
	public GuitarcadeStatisticsLayout() {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		lblTitle.addStyleName(ValoTheme.LABEL_H1);
		
		lblGamesPlayed.addStyleName(ValoTheme.LABEL_BOLD);
		lblSummary.addStyleName(ValoTheme.LABEL_BOLD);
		tbSummary.addStyleName(ValoTheme.TABLE_SMALL);
		
		tbSummary.addContainerProperty("game", String.class, null);
		tbSummary.setColumnHeader("game", "Game");
		tbSummary.addContainerProperty("minutesPlayed", Long.class, 0L);
		tbSummary.setColumnHeader("minutesPlayed", "Minutes Played");
		tbSummary.addContainerProperty("highScore", Long.class, 0L);
		tbSummary.setColumnHeader("highScore", "High Score");
		tbSummary.addContainerProperty("lastPlayed", Date.class, null);
		tbSummary.setColumnHeader("lastPlayed", "Last Played");
		tbSummary.setPageLength(GuitarcadeGame.values().length);
		
		comboLevel.setVisible(false);
		lblHr.setContentMode(ContentMode.HTML);
		lblScoreEvolution.addStyleName(ValoTheme.LABEL_BOLD);
		
		for (GuitarcadeGame game : GuitarcadeGame.values()) {
			tbSummary.addItem(game);
			tbSummary.getItem(game).getItemProperty("game").setValue(game.getCaption());
			
			comboGame.addItem(game);
			comboGame.setItemCaption(game, game.getCaption());
		}
	}

	@AutoGenerated
	private VerticalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new VerticalLayout();
		mainLayout.setImmediate(true);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("-1px");
		mainLayout.setMargin(false);
		
		// top-level component properties
		setWidth("100.0%");
		setHeight("-1px");
		
		// lblTitle
		lblTitle = new Label();
		lblTitle.setImmediate(true);
		lblTitle.setWidth("-1px");
		lblTitle.setHeight("-1px");
		lblTitle.setValue("Guitarcade Statistics");
		mainLayout.addComponent(lblTitle);
		mainLayout.setComponentAlignment(lblTitle, new Alignment(20));
		
		// horizontalLayout_1
		horizontalLayout_1 = buildHorizontalLayout_1();
		mainLayout.addComponent(horizontalLayout_1);
		
		// lblHr
		lblHr = new Label();
		lblHr.setImmediate(true);
		lblHr.setWidth("100.0%");
		lblHr.setHeight("-1px");
		lblHr.setValue("<hr/>");
		mainLayout.addComponent(lblHr);
		mainLayout.setComponentAlignment(lblHr, new Alignment(20));
		
		// verticalLayout_4
		verticalLayout_4 = buildVerticalLayout_4();
		mainLayout.addComponent(verticalLayout_4);
		
		return mainLayout;
	}

	@AutoGenerated
	private HorizontalLayout buildHorizontalLayout_1() {
		// common part: create layout
		horizontalLayout_1 = new HorizontalLayout();
		horizontalLayout_1.setImmediate(true);
		horizontalLayout_1.setWidth("100.0%");
		horizontalLayout_1.setHeight("-1px");
		horizontalLayout_1.setMargin(false);
		
		// verticalLayout_3
		verticalLayout_3 = buildVerticalLayout_3();
		horizontalLayout_1.addComponent(verticalLayout_3);
		
		// verticalLayout_2
		verticalLayout_2 = buildVerticalLayout_2();
		horizontalLayout_1.addComponent(verticalLayout_2);
		
		return horizontalLayout_1;
	}

	@AutoGenerated
	private VerticalLayout buildVerticalLayout_3() {
		// common part: create layout
		verticalLayout_3 = new VerticalLayout();
		verticalLayout_3.setImmediate(true);
		verticalLayout_3.setWidth("100.0%");
		verticalLayout_3.setHeight("-1px");
		verticalLayout_3.setMargin(false);
		
		// lblSummary
		lblSummary = new Label();
		lblSummary.setImmediate(true);
		lblSummary.setWidth("-1px");
		lblSummary.setHeight("-1px");
		lblSummary.setValue("Summary");
		verticalLayout_3.addComponent(lblSummary);
		verticalLayout_3.setComponentAlignment(lblSummary, new Alignment(20));
		
		// tbSummary
		tbSummary = new Table();
		tbSummary.setImmediate(true);
		tbSummary.setWidth("90.0%");
		tbSummary.setHeight("-1px");
		verticalLayout_3.addComponent(tbSummary);
		verticalLayout_3.setComponentAlignment(tbSummary, new Alignment(20));
		
		return verticalLayout_3;
	}

	@AutoGenerated
	private VerticalLayout buildVerticalLayout_2() {
		// common part: create layout
		verticalLayout_2 = new VerticalLayout();
		verticalLayout_2.setImmediate(true);
		verticalLayout_2.setWidth("100.0%");
		verticalLayout_2.setHeight("-1px");
		verticalLayout_2.setMargin(false);
		
		// lblGamesPlayed
		lblGamesPlayed = new Label();
		lblGamesPlayed.setImmediate(true);
		lblGamesPlayed.setWidth("-1px");
		lblGamesPlayed.setHeight("-1px");
		lblGamesPlayed.setValue("Time Played");
		verticalLayout_2.addComponent(lblGamesPlayed);
		verticalLayout_2.setComponentAlignment(lblGamesPlayed,
				new Alignment(20));
		
		// chartGamesPlayed
		chartGamesPlayed = new DCharts();
		chartGamesPlayed.setImmediate(true);
		chartGamesPlayed.setWidth("90.0%");
		chartGamesPlayed.setHeight("-1px");
		verticalLayout_2.addComponent(chartGamesPlayed);
		verticalLayout_2.setComponentAlignment(chartGamesPlayed, new Alignment(
				20));
		
		return verticalLayout_2;
	}

	@AutoGenerated
	private VerticalLayout buildVerticalLayout_4() {
		// common part: create layout
		verticalLayout_4 = new VerticalLayout();
		verticalLayout_4.setImmediate(true);
		verticalLayout_4.setWidth("100.0%");
		verticalLayout_4.setHeight("-1px");
		verticalLayout_4.setMargin(true);
		
		// lblScoreEvolution
		lblScoreEvolution = new Label();
		lblScoreEvolution.setImmediate(true);
		lblScoreEvolution.setWidth("-1px");
		lblScoreEvolution.setHeight("-1px");
		lblScoreEvolution.setValue("Score Evolution");
		verticalLayout_4.addComponent(lblScoreEvolution);
		verticalLayout_4.setComponentAlignment(lblScoreEvolution,
				new Alignment(20));
		
		// horizontalLayout_2
		horizontalLayout_2 = buildHorizontalLayout_2();
		verticalLayout_4.addComponent(horizontalLayout_2);
		
		// chartScoreEvolution
		chartScoreEvolution = new DCharts();
		chartScoreEvolution.setImmediate(true);
		chartScoreEvolution.setWidth("100.0%");
		chartScoreEvolution.setHeight("-1px");
		verticalLayout_4.addComponent(chartScoreEvolution);
		verticalLayout_4.setComponentAlignment(chartScoreEvolution,
				new Alignment(20));
		
		return verticalLayout_4;
	}

	@AutoGenerated
	private HorizontalLayout buildHorizontalLayout_2() {
		// common part: create layout
		horizontalLayout_2 = new HorizontalLayout();
		horizontalLayout_2.setImmediate(true);
		horizontalLayout_2.setWidth("-1px");
		horizontalLayout_2.setHeight("-1px");
		horizontalLayout_2.setMargin(true);
		horizontalLayout_2.setSpacing(true);
		
		// comboGame
		comboGame = new ComboBox();
		comboGame.setCaption("Choose the game:");
		comboGame.setImmediate(true);
		comboGame.setWidth("250px");
		comboGame.setHeight("-1px");
		horizontalLayout_2.addComponent(comboGame);
		
		// comboLevel
		comboLevel = new ComboBox();
		comboLevel.setCaption("Choose the level:");
		comboLevel.setImmediate(true);
		comboLevel.setWidth("250px");
		comboLevel.setHeight("-1px");
		horizontalLayout_2.addComponent(comboLevel);
		
		return horizontalLayout_2;
	}

}
