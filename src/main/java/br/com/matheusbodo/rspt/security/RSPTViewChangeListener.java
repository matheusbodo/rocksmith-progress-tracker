package br.com.matheusbodo.rspt.security;

import org.springframework.stereotype.Component;
import org.vaadin.googleanalytics.tracking.GoogleAnalyticsTracker;

import br.com.matheusbodo.rspt.entity.enums.Role;
import br.com.matheusbodo.rspt.util.SecurityUtil;

import com.vaadin.navigator.ViewChangeListener;

@Component(value="rsptViewChangeListener")
public class RSPTViewChangeListener implements ViewChangeListener {

	private static final long serialVersionUID = -1645544112790565575L;
	
	private GoogleAnalyticsTracker tracker;
	
	@Override
	public boolean beforeViewChange(ViewChangeEvent event) {
		if (event.getNewView() instanceof SecuredView) {
			SecuredView securedView = (SecuredView) event.getNewView();
			for (Role role : securedView.getAuthorizedRoles()) {
				if (SecurityUtil.hasRole(role)) {
					trackPageview(event.getViewName());
					return true;
				}
			}
			event.getNavigator().navigateTo("acessoNegado");
			trackPageview("acessoNegado");
			return false;
		} else {
			trackPageview(event.getViewName());
			return true;
		}
	}

	@Override
	public void afterViewChange(ViewChangeEvent event) {

	}
	
	public void setTracker(GoogleAnalyticsTracker tracker) {
		this.tracker = tracker;
	}
	
	private void trackPageview(String pageId) {
		if (tracker != null) {
			tracker.trackPageview(pageId);
		}
	}
	
}
