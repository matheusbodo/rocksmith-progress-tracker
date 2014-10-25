package br.com.matheusbodo.rspt.views;

import java.util.Calendar;
import java.util.List;

import org.dussan.vaadin.dcharts.base.elements.XYaxis;
import org.dussan.vaadin.dcharts.data.DataSeries;
import org.dussan.vaadin.dcharts.metadata.XYaxes;
import org.dussan.vaadin.dcharts.metadata.renderers.AxisRenderers;
import org.dussan.vaadin.dcharts.metadata.renderers.LabelRenderers;
import org.dussan.vaadin.dcharts.metadata.renderers.SeriesRenderers;
import org.dussan.vaadin.dcharts.options.Axes;
import org.dussan.vaadin.dcharts.options.AxesDefaults;
import org.dussan.vaadin.dcharts.options.Legend;
import org.dussan.vaadin.dcharts.options.Options;
import org.dussan.vaadin.dcharts.options.SeriesDefaults;
import org.dussan.vaadin.dcharts.renderers.series.PieRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.vaadin.spring.navigator.VaadinView;

import br.com.matheusbodo.rspt.entity.GuitarcadePracticeLog;
import br.com.matheusbodo.rspt.entity.User;
import br.com.matheusbodo.rspt.entity.enums.GuitarcadeGame;
import br.com.matheusbodo.rspt.entity.enums.GuitarcadeGameLevel;
import br.com.matheusbodo.rspt.entity.enums.Role;
import br.com.matheusbodo.rspt.layout.GuitarcadeStatisticsLayout;
import br.com.matheusbodo.rspt.repository.GuitarcadePracticeLogRepository;
import br.com.matheusbodo.rspt.security.SecuredView;
import br.com.matheusbodo.rspt.service.UserService;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.VerticalLayout;

@Component
@Scope("prototype")
@VaadinView(name="statisticsGuitarcade")
public class GuitarcadeStatisticsView extends VerticalLayout implements SecuredView {

	private static final long serialVersionUID = -2189532509815825094L;

	private GuitarcadeStatisticsLayout layout;
	
	@Autowired
	private GuitarcadePracticeLogRepository guitarcadePracticeLogRepository;
	
	@Autowired
	private UserService userService;
	
	public GuitarcadeStatisticsView() {
		layout = new GuitarcadeStatisticsLayout();
		addComponent(layout);
		
		DataSeries dataSeries = new DataSeries();
		dataSeries.newSeries();
		dataSeries.add(0, 0);
		dataSeries.add(1, 0);
		layout.getChartScoreEvolution().setDataSeries(dataSeries).show();
		
		layout.getComboGame().addValueChangeListener(new ValueChangeListener() {
			private static final long serialVersionUID = -3672849431308112062L;
			@Override
			public void valueChange(ValueChangeEvent event) {
				GuitarcadeGame game = (GuitarcadeGame) event.getProperty().getValue();
				List<GuitarcadeGameLevel> levels = GuitarcadeGameLevel.findByGame(game);
				if (levels.isEmpty()) {
					createScoreEvolutionChart(game, null);
					layout.getComboLevel().setVisible(false);
				} else {
					layout.getComboLevel().removeAllItems();
					for (GuitarcadeGameLevel level : levels) {
						layout.getComboLevel().addItem(level);
						layout.getComboLevel().setItemCaption(level, level.getCaption());
					}
					layout.getComboLevel().setVisible(true);
				}
			}
		});
		
		layout.getComboLevel().addValueChangeListener(new ValueChangeListener() {
			private static final long serialVersionUID = -3672849431308112062L;
			@Override
			public void valueChange(ValueChangeEvent event) {
				GuitarcadeGame game = (GuitarcadeGame) layout.getComboGame().getValue();
				GuitarcadeGameLevel level = (GuitarcadeGameLevel) event.getProperty().getValue();
				createScoreEvolutionChart(game, level);
			}
		});
	}

	@Override
	public void enter(ViewChangeEvent event) {
		updateSummaryTable();
		createGamesPlayedChart();
	}

	@Override
	public Role[] getAuthorizedRoles() {
		return new Role[] { Role.ADMIN, Role.USER };
	}
	
	@SuppressWarnings("unchecked")
	private void updateSummaryTable() {
		Calendar dateFrom = Calendar.getInstance();
		dateFrom.add(Calendar.MONTH, -1);
		Calendar dateTo = Calendar.getInstance();
		User user = userService.findLoggedUser();
		List<Object[]> summaryList = guitarcadePracticeLogRepository.findSummaryByUserAndPeriod(user, dateFrom, dateTo);
		for (Object[] summary : summaryList) {
			Object game = summary[0];
			layout.getTbSummary().getItem(game).getItemProperty("minutesPlayed").setValue((Long) summary[1]);
			layout.getTbSummary().getItem(game).getItemProperty("highScore").setValue((Long) summary[2]);
			layout.getTbSummary().getItem(game).getItemProperty("lastPlayed").setValue(((Calendar) summary[3]).getTime());
		}
	}
	
	private void createGamesPlayedChart() {
		Calendar dateFrom = Calendar.getInstance();
		dateFrom.add(Calendar.MONTH, -1);
		Calendar dateTo = Calendar.getInstance();
		User user = userService.findLoggedUser();
		List<Object[]> minutesPlayed = guitarcadePracticeLogRepository.findMinutesPlayedByUserAndPeriod(user, dateFrom, dateTo);
		DataSeries dataSeries = new DataSeries();
		for (Object[] objects : minutesPlayed) {
			dataSeries.newSeries().add(((GuitarcadeGame) objects[0]).getCaption(), (Long) objects[1]);
		}
		
		SeriesDefaults seriesDefaults = new SeriesDefaults()
		.setRenderer(SeriesRenderers.PIE)
		.setRendererOptions(new PieRenderer().setShowDataLabels(true));
		
		Legend legend = new Legend().setShow(true);
		
		Options options = new Options().setSeriesDefaults(seriesDefaults).setLegend(legend);
		
		layout.getChartGamesPlayed().setOptions(options).setDataSeries(dataSeries).show();
	}
	
	private void createScoreEvolutionChart(GuitarcadeGame game, GuitarcadeGameLevel level) {
		List<GuitarcadePracticeLog> logs = null;  
		if (level == null) {
			logs = guitarcadePracticeLogRepository.findByGameOrderByPracticeDate(game);
		} else {
			logs = guitarcadePracticeLogRepository.findByGameAndGameLevelOrderByPracticeDate(game, level);
		}
		
		AxesDefaults axesDefaults = new AxesDefaults().setLabelRenderer(LabelRenderers.CANVAS);

		Axes axes = new Axes()
		.addAxis(new XYaxis(XYaxes.X).setLabel("Session").setPad(0).setRenderer(AxisRenderers.CATEGORY))
		.addAxis(new XYaxis(XYaxes.Y).setLabel("Score").setPad(0));

		Options options = new Options()
		.setAxesDefaults(axesDefaults)
		.setAxes(axes);
		
		if (logs == null || logs.isEmpty()) {
			layout.getChartScoreEvolution().hide();
			DataSeries dataSeries = new DataSeries();
			dataSeries.newSeries();
			dataSeries.add(0, 0);
			dataSeries.add(1, 0);
			layout.getChartScoreEvolution().setOptions(options).setDataSeries(dataSeries).show();
		} else {
			DataSeries dataSeries = new DataSeries();
			dataSeries.newSeries();
			for (int i = 0; i < logs.size(); i++) {
				GuitarcadePracticeLog log = logs.get(i);
				dataSeries.add(i+1, log.getScore());
			}
			layout.getChartScoreEvolution().setOptions(options).setDataSeries(dataSeries).show();
		}
	}

}
