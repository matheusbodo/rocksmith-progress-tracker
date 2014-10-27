package br.com.matheusbodo.rspt.layout;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;

import org.dussan.vaadin.dcharts.DCharts;
import org.tepi.filtertable.FilterTable;

import br.com.matheusbodo.rspt.entity.enums.GamePath;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.shared.ui.combobox.FilteringMode;
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
public class LearnSongStatisticsLayout extends CustomComponent {

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
	private ComboBox comboSong;

	@AutoGenerated
	private ComboBox comboPath;

	@AutoGenerated
	private Label lblLearnSongEvolution;

	@AutoGenerated
	private Label lblHr;

	@AutoGenerated
	private HorizontalLayout horizontalLayout_1;

	@AutoGenerated
	private VerticalLayout verticalLayout_3;

	@AutoGenerated
	private FilterTable tbSummary;

	@AutoGenerated
	private ComboBox comboSummaryPath;

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
	public LearnSongStatisticsLayout() {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		lblTitle.addStyleName(ValoTheme.LABEL_H1);
		
		lblSummary.addStyleName(ValoTheme.LABEL_BOLD);
		tbSummary.addStyleName(ValoTheme.TABLE_SMALL);
		
		tbSummary.addContainerProperty("artist", String.class, null);
		tbSummary.setColumnHeader("artist", "Artist");
		tbSummary.addContainerProperty("title", String.class, null);
		tbSummary.setColumnHeader("title", "Title");
		tbSummary.addContainerProperty("minutesPlayed", Long.class, 0L);
		tbSummary.setColumnHeader("minutesPlayed", "Minutes Played");
		tbSummary.addContainerProperty("completed", BigDecimal.class, BigDecimal.ZERO);
		tbSummary.setColumnHeader("completed", "Completed");
		tbSummary.addContainerProperty("lastPlayed", Date.class, null);
		tbSummary.setColumnHeader("lastPlayed", "Last Played");
		tbSummary.setPageLength(10);
		
		lblHr.setContentMode(ContentMode.HTML);
		lblLearnSongEvolution.addStyleName(ValoTheme.LABEL_BOLD);
		
		for (GamePath path : GamePath.values()) {
			comboPath.addItem(path);
			comboPath.setItemCaption(path, path.name());
			comboSummaryPath.addItem(path);
			comboSummaryPath.setItemCaption(path, path.name());
		}
		
		comboPath.setFilteringMode(FilteringMode.CONTAINS);
		comboSong.setFilteringMode(FilteringMode.CONTAINS);
		comboSummaryPath.setFilteringMode(FilteringMode.CONTAINS);
		
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
		lblTitle.setValue("Learn Song Statistics");
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
		
		// comboSummaryPath
		comboSummaryPath = new ComboBox();
		comboSummaryPath.setImmediate(true);
		comboSummaryPath.setWidth("-1px");
		comboSummaryPath.setHeight("24px");
		verticalLayout_3.addComponent(comboSummaryPath);
		verticalLayout_3.setComponentAlignment(comboSummaryPath, new Alignment(
				20));
		
		// tbSummary
		tbSummary = new FilterTable();
		tbSummary.setImmediate(true);
		tbSummary.setWidth("90.0%");
		tbSummary.setHeight("-1px");
		verticalLayout_3.addComponent(tbSummary);
		verticalLayout_3.setComponentAlignment(tbSummary, new Alignment(20));
		
		return verticalLayout_3;
	}

	@AutoGenerated
	private VerticalLayout buildVerticalLayout_4() {
		// common part: create layout
		verticalLayout_4 = new VerticalLayout();
		verticalLayout_4.setImmediate(true);
		verticalLayout_4.setWidth("100.0%");
		verticalLayout_4.setHeight("-1px");
		verticalLayout_4.setMargin(true);
		
		// lblLearnSongEvolution
		lblLearnSongEvolution = new Label();
		lblLearnSongEvolution.setImmediate(true);
		lblLearnSongEvolution.setWidth("-1px");
		lblLearnSongEvolution.setHeight("-1px");
		lblLearnSongEvolution.setValue("Learn Song Evolution");
		verticalLayout_4.addComponent(lblLearnSongEvolution);
		verticalLayout_4.setComponentAlignment(lblLearnSongEvolution,
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
		
		// comboPath
		comboPath = new ComboBox();
		comboPath.setCaption("Choose the level:");
		comboPath.setImmediate(true);
		comboPath.setWidth("250px");
		comboPath.setHeight("-1px");
		horizontalLayout_2.addComponent(comboPath);
		
		// comboSong
		comboSong = new ComboBox();
		comboSong.setCaption("Choose the song:");
		comboSong.setImmediate(true);
		comboSong.setWidth("250px");
		comboSong.setHeight("-1px");
		horizontalLayout_2.addComponent(comboSong);
		
		return horizontalLayout_2;
	}

}
