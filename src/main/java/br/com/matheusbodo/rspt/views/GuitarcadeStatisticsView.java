package br.com.matheusbodo.rspt.views;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.dussan.vaadin.dcharts.data.DataSeries;
import org.dussan.vaadin.dcharts.metadata.renderers.SeriesRenderers;
import org.dussan.vaadin.dcharts.options.Legend;
import org.dussan.vaadin.dcharts.options.Options;
import org.dussan.vaadin.dcharts.options.SeriesDefaults;
import org.dussan.vaadin.dcharts.renderers.series.PieRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.vaadin.spring.navigator.VaadinView;

import br.com.matheusbodo.rspt.entity.User;
import br.com.matheusbodo.rspt.entity.enums.GuitarcadeGame;
import br.com.matheusbodo.rspt.entity.enums.Role;
import br.com.matheusbodo.rspt.layout.GuitarcadeStatisticsLayout;
import br.com.matheusbodo.rspt.repository.GuitarcadePracticeLogRepository;
import br.com.matheusbodo.rspt.security.SecuredView;
import br.com.matheusbodo.rspt.service.UserService;

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
			layout.getTbSummary().getItem(game).getItemProperty("lastPlayed").setValue((Date) summary[3]);
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

}
