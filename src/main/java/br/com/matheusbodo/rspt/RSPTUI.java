package br.com.matheusbodo.rspt;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.vaadin.googleanalytics.tracking.GoogleAnalyticsTracker;
import org.vaadin.spring.VaadinUI;
import org.vaadin.spring.navigator.SpringViewProvider;

import br.com.matheusbodo.rspt.entity.enums.Role;
import br.com.matheusbodo.rspt.layout.RSPTLayout;
import br.com.matheusbodo.rspt.security.RSPTViewChangeListener;
import br.com.matheusbodo.rspt.util.SecurityUtil;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@VaadinUI
@Theme("rspt")
@Widgetset(value="br.com.rspt.RSPTWidgetSet")
@PreserveOnRefresh
public class RSPTUI extends UI {
	
	private static final long serialVersionUID = -9076059861749713811L;
	
	@Autowired
    private SpringViewProvider viewProvider;
	
	@Autowired
	@Qualifier(value="rsptViewChangeListener")
	private ViewChangeListener viewChangeListener;
	
	private Navigator navigator;
	
	private RSPTLayout rootLayout = new RSPTLayout();
	private ComponentContainer viewDisplay = rootLayout.getContentContainer();
	private LinkedHashMap<String, String> menuItems = new LinkedHashMap<String, String>();
	private CssLayout menu = new CssLayout();
    private CssLayout menuItemsLayout = new CssLayout();
    
    private GoogleAnalyticsTracker tracker;
	
	
	@Override
    protected void init(VaadinRequest vaadinRequest) {
		getPage().getJavaScript().execute("document.head.innerHTML += '<meta name=\"viewport\" content=\"initial-scale = 1.0,maximum-scale = 1.0\">'");
		Responsive.makeResponsive(this);
		tracker = new GoogleAnalyticsTracker("UA-56035202-1");
		tracker.extend(this);
		((RSPTViewChangeListener) viewChangeListener).setTracker(tracker);
        displayApplication();
    }

	public void displayApplication() {
		getPage().setTitle("Rocksmith Progress Tracker");
		setContent(rootLayout);
		rootLayout.setWidth("100%");
		
		rootLayout.addMenu(buildMenu());
		
		navigator = new Navigator(this, viewDisplay);
		setNavigator(navigator);
		navigator.addProvider(viewProvider);
		navigator.setErrorView(new ErrorView());
		navigator.addViewChangeListener(viewChangeListener);
	}
	
	public static RSPTUI getCurrent() {
		return (RSPTUI) UI.getCurrent();
	}

	private class ErrorView extends VerticalLayout implements View {
		private static final long serialVersionUID = -3314357145844606629L;
		private Label message;

        ErrorView() {
            setMargin(true);
            addComponent(message = new Label());
        }

        @Override
        public void enter(ViewChangeListener.ViewChangeEvent event) {
            message.setValue(String.format("Error opening view: %s", event.getViewName()));
        }
    }
	
	private CssLayout buildMenu() {
        // Add items
        menuItems.put("", "Home");
        menuItems.put("manageSongs", "Songs");
        menuItems.put("practiceLearnSong", "Learn a Song");
        menuItems.put("practiceGuitarcade", "Guitarcade");
//        menuItems.put("practiceScoreAttack", "Score Attack");
//        menuItems.put("practiceSessionMode", "Session Mode");
        menuItems.put("statisticsLearnSong", "Learn a Song");
        menuItems.put("statisticsGuitarcade", "Guitarcade");
//        menuItems.put("statisticsScoreAttack", "Score Attack");
//        menuItems.put("statisticsSessionMode", "Session Mode");
        if (SecurityUtil.hasRole(Role.ADMIN)) {
        	menuItems.put("manageUsers", "Users");
        }

        HorizontalLayout top = new HorizontalLayout();
        top.setWidth("100%");
        top.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        top.addStyleName("valo-menu-title");
        menu.addComponent(top);

        Button showMenu = new Button("Menu", new ClickListener() {
			private static final long serialVersionUID = -1693496208065516221L;

			@Override
            public void buttonClick(ClickEvent event) {
                if (menu.getStyleName().contains("valo-menu-visible")) {
                    menu.removeStyleName("valo-menu-visible");
                } else {
                    menu.addStyleName("valo-menu-visible");
                }
            }
        });
        showMenu.addStyleName(ValoTheme.BUTTON_PRIMARY);
        showMenu.addStyleName(ValoTheme.BUTTON_SMALL);
        showMenu.addStyleName("valo-menu-toggle");
        showMenu.setIcon(FontAwesome.LIST);
        menu.addComponent(showMenu);

        Label title = new Label("<h3>Rocksmith Progress Tracker</h3><br/><center>Version 0.1</center>", ContentMode.HTML);
        title.setSizeUndefined();
        top.addComponent(title);
        top.setExpandRatio(title, 1);

        MenuBar settings = new MenuBar();
        settings.addStyleName("user-menu");
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        MenuItem settingsItem = settings.addItem(username, FontAwesome.USER, null);
        settingsItem.addItem("Edit Profile", new Command() {
			private static final long serialVersionUID = 3618553404417268850L;
			@Override
			public void menuSelected(MenuItem selectedItem) {
				navigator.navigateTo("editProfile");
			}
        });
        settingsItem.addSeparator();
        settingsItem.addItem("Logout", new Command() {
			private static final long serialVersionUID = 3618553404417268850L;
			@Override
			public void menuSelected(MenuItem selectedItem) {
				SecurityContextHolder.getContext().setAuthentication(null);
				getPage().setLocation("/");
			}
		});
        menu.addComponent(settings);

        menuItemsLayout.setPrimaryStyleName("valo-menuitems");
        menu.addComponent(menuItemsLayout);

        Label label = null;
        for (final Entry<String, String> item : menuItems.entrySet()) {
            if (item.getKey().equals("manageSongs")) {
                label = new Label("Manage", ContentMode.HTML);
                label.setPrimaryStyleName("valo-menu-subtitle");
                label.addStyleName("h4");
                label.setSizeUndefined();
                menuItemsLayout.addComponent(label);
            } else if (item.getKey().equals("practiceLearnSong")) {
            	label = new Label("Practice", ContentMode.HTML);
                label.setPrimaryStyleName("valo-menu-subtitle");
                label.addStyleName("h4");
                label.setSizeUndefined();
                menuItemsLayout.addComponent(label);
            } else if (item.getKey().equals("statisticsLearnSong")) {
            	label = new Label("Statistics", ContentMode.HTML);
                label.setPrimaryStyleName("valo-menu-subtitle");
                label.addStyleName("h4");
                label.setSizeUndefined();
                menuItemsLayout.addComponent(label);
            } else if (item.getKey().equals("manageUsers")) {
            	label = new Label("Admin", ContentMode.HTML);
                label.setPrimaryStyleName("valo-menu-subtitle");
                label.addStyleName("h4");
                label.setSizeUndefined();
                menuItemsLayout.addComponent(label);
            }
            Button b = new Button(item.getValue(), new ClickListener() {
				private static final long serialVersionUID = -2527834245686618379L;
				@Override
                public void buttonClick(ClickEvent event) {
                    navigator.navigateTo(item.getKey());
                }
            });
            b.setHtmlContentAllowed(true);
            b.setPrimaryStyleName("valo-menu-item");
            menuItemsLayout.addComponent(b);
        }

        return menu;
    }
	
}
