package br.com.matheusbodo.rspt.layout;

import lombok.Getter;

import org.dussan.vaadin.dcharts.DCharts;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@Getter
public class GuitarcadeStatisticsLayout extends CustomComponent {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private VerticalLayout mainLayout;

	@AutoGenerated
	private HorizontalLayout horizontalLayout_1;

	@AutoGenerated
	private VerticalLayout verticalLayout_2;

	@AutoGenerated
	private DCharts chartGamesPlayed;

	@AutoGenerated
	private Label lblGamesPlayed;

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
	public GuitarcadeStatisticsLayout() {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		lblTitle.addStyleName(ValoTheme.LABEL_H1);
	}

	@AutoGenerated
	private VerticalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new VerticalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("-1px");
		mainLayout.setMargin(false);
		
		// top-level component properties
		setWidth("100.0%");
		setHeight("-1px");
		
		// lblTitle
		lblTitle = new Label();
		lblTitle.setImmediate(false);
		lblTitle.setWidth("-1px");
		lblTitle.setHeight("-1px");
		lblTitle.setValue("Guitarcade Statistics");
		mainLayout.addComponent(lblTitle);
		mainLayout.setComponentAlignment(lblTitle, new Alignment(20));
		
		// horizontalLayout_1
		horizontalLayout_1 = buildHorizontalLayout_1();
		mainLayout.addComponent(horizontalLayout_1);
		
		return mainLayout;
	}

	@AutoGenerated
	private HorizontalLayout buildHorizontalLayout_1() {
		// common part: create layout
		horizontalLayout_1 = new HorizontalLayout();
		horizontalLayout_1.setImmediate(false);
		horizontalLayout_1.setWidth("100.0%");
		horizontalLayout_1.setHeight("-1px");
		horizontalLayout_1.setMargin(false);
		
		// verticalLayout_2
		verticalLayout_2 = buildVerticalLayout_2();
		horizontalLayout_1.addComponent(verticalLayout_2);
		
		return horizontalLayout_1;
	}

	@AutoGenerated
	private VerticalLayout buildVerticalLayout_2() {
		// common part: create layout
		verticalLayout_2 = new VerticalLayout();
		verticalLayout_2.setImmediate(false);
		verticalLayout_2.setWidth("100.0%");
		verticalLayout_2.setHeight("-1px");
		verticalLayout_2.setMargin(false);
		
		// lblGamesPlayed
		lblGamesPlayed = new Label();
		lblGamesPlayed.setImmediate(false);
		lblGamesPlayed.setWidth("-1px");
		lblGamesPlayed.setHeight("-1px");
		lblGamesPlayed.setValue("Games Played");
		verticalLayout_2.addComponent(lblGamesPlayed);
		
		// chartGamesPlayed
		chartGamesPlayed = new DCharts();
		chartGamesPlayed.setImmediate(true);
		chartGamesPlayed.setWidth("100.0%");
		chartGamesPlayed.setHeight("-1px");
		verticalLayout_2.addComponent(chartGamesPlayed);
		
		return verticalLayout_2;
	}

}
