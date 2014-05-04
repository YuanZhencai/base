package com.wcs.autosave;

import java.util.Map;

import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

public class ViewRestorer implements java.io.Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void restoreCurrentView() {
        // Get the faces context of the current request.
        FacesContext ctx = FacesContext.getCurrentInstance();
        // Get the root component of the current view.
        UIViewRoot root = ctx.getViewRoot();
        // Get the managed bean instance wrapping the data repository.
        RepositoryWrapper wrapper = RepositoryWrapper.getManagedBean(ctx);
        // Get the data map for the current context.
        Map<String, Object> dataMap = wrapper.getDataMap(ctx);
        // Use the data map to restore the values of the JSF components.
        DataMapRepository.restoreValues(root, dataMap);
        // Signal the JSF framework to go to the Render Response phase.
        ctx.renderResponse();
    }
    
    public void actionListener(ActionEvent e) {
        restoreCurrentView();
    }
    
    public void valueChangeListener(ValueChangeEvent e) {
        restoreCurrentView();
    }

    public boolean isCurrentViewRestorable() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        RepositoryWrapper wrapper = RepositoryWrapper.getManagedBean(ctx);
        return wrapper.hasDataMap(ctx);
    }
    
}
