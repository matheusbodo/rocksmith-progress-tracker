package br.com.matheusbodo.rspt.views;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import org.dussan.vaadin.dcharts.base.elements.XYaxis;
import org.dussan.vaadin.dcharts.data.DataSeries;
import org.dussan.vaadin.dcharts.metadata.XYaxes;
import org.dussan.vaadin.dcharts.metadata.renderers.AxisRenderers;
import org.dussan.vaadin.dcharts.metadata.renderers.LabelRenderers;
import org.dussan.vaadin.dcharts.options.Axes;
import org.dussan.vaadin.dcharts.options.AxesDefaults;
import org.dussan.vaadin.dcharts.options.Options;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.vaadin.spring.navigator.VaadinView;

import br.com.matheusbodo.rspt.entity.LearnSongPracticeLog;
import br.com.matheusbodo.rspt.entity.Song;
import br.com.matheusbodo.rspt.entity.User;
import br.com.matheusbodo.rspt.entity.enums.GamePath;
import br.com.matheusbodo.rspt.entity.enums.Role;
import br.com.matheusbodo.rspt.layout.LearnSongStatisticsLayout;
import br.com.matheusbodo.rspt.repository.LearnSongPracticeLogRepository;
import br.com.matheusbodo.rspt.repository.SongRepository;
import br.com.matheusbodo.rspt.security.SecuredView;
import br.com.matheusbodo.rspt.service.UserService;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.VerticalLayout;

@Component
@Scope("prototype")
@VaadinView(name="statisticsLearnSong")
public class LearnSongStatisticsView extends VerticalLayout implements SecuredView {

	private static final long serialVersionUID = -2189532509815825094L;

	private LearnSongStatisticsLayout layout;
	
	@Autowired
	private LearnSongPracticeLogRepository learnSongPracticeLogRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SongRepository songRepository;
	
	public LearnSongStatisticsView() {
		layout = new LearnSongStatisticsLayout();
		addComponent(layout);
		
		DataSeries dataSeries = new DataSeries();
		dataSeries.newSeries();
		dataSeries.add(0, 0);
		dataSeries.add(1, 0);
		layout.getChartScoreEvolution().setDataSeries(dataSeries).show();
		
		layout.getComboSong().addValueChangeListener(new ValueChangeListener() {
			private static final long serialVersionUID = -3672849431308112062L;
			@Override
			public void valueChange(ValueChangeEvent event) {
				Song song = (Song) event.getProperty().getValue();
				GamePath path = (GamePath) layout.getComboPath().getValue();
				createSongEvolutionChart(song, path);
			}
		});
		
		layout.getComboPath().addValueChangeListener(new ValueChangeListener() {
			private static final long serialVersionUID = -3672849431308112062L;
			@Override
			public void valueChange(ValueChangeEvent event) {
				Song song = (Song) layout.getComboSong().getValue();
				GamePath path = (GamePath) event.getProperty().getValue();
				createSongEvolutionChart(song, path);
			}
		});
		
		layout.getComboSummaryPath().addValueChangeListener(new ValueChangeListener() {
			private static final long serialVersionUID = -135079700170748171L;
			@Override
			public void valueChange(ValueChangeEvent event) {
				updateSummaryTable();
			}
		});
	}

	@Override
	public void enter(ViewChangeEvent event) {
		layout.getComboSummaryPath().select(GamePath.LEAD);
		layout.getComboPath().select(GamePath.LEAD);
		
		layout.getComboSong().removeAllItems();
		List<Song> userSongs = songRepository.findByUser(userService.findLoggedUser());
		for (Song song : userSongs) {
			layout.getComboSong().addItem(song);
			layout.getComboSong().setItemCaption(song, song.getArtist() + " - " + song.getTitle());
		}
		if (userSongs != null && !userSongs.isEmpty()) {
			layout.getComboSong().select(userSongs.get(0));
		}
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
		
		layout.getTbSummary().removeAllItems();
		List<Song> songs = songRepository.findByUser(user);
		for (Song song : songs) {
			layout.getTbSummary().addItem(song);
			layout.getTbSummary().getItem(song).getItemProperty("artist").setValue(song.getArtist());
			layout.getTbSummary().getItem(song).getItemProperty("title").setValue(song.getTitle());
		}
		
		GamePath path = (GamePath) layout.getComboSummaryPath().getValue();
		List<Object[]> summaryList = learnSongPracticeLogRepository.findSummaryByPathAndUserAndPeriod(path, user, dateFrom, dateTo);
		for (Object[] summary : summaryList) {
			Long songID = (Long) summary[0];
			Song song = songRepository.findByIdAndUser(songID, user);
			layout.getTbSummary().getItem(song).getItemProperty("minutesPlayed").setValue((Long) summary[1]);
			layout.getTbSummary().getItem(song).getItemProperty("completed").setValue((BigDecimal) summary[2]);
			layout.getTbSummary().getItem(song).getItemProperty("lastPlayed").setValue(((Calendar) summary[3]).getTime());
		}
	}
	
	private void createSongEvolutionChart(Song song, GamePath path) {
		if (song == null || path == null) {
			return;
		}
		List<LearnSongPracticeLog> logs = learnSongPracticeLogRepository.findBySongAndPathOrderByPracticeDate(song, path);
		
		AxesDefaults axesDefaults = new AxesDefaults().setLabelRenderer(LabelRenderers.CANVAS);

		Axes axes = new Axes()
		.addAxis(new XYaxis(XYaxes.X).setLabel("Session").setPad(1).setRenderer(AxisRenderers.CATEGORY))
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
				LearnSongPracticeLog log = logs.get(i);
				dataSeries.add(i+1, log.getCompleted());
			}
			layout.getChartScoreEvolution().setOptions(options).setDataSeries(dataSeries).show();
		}
	}

}
