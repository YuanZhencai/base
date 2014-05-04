package com.wcs.autosave;

import javax.faces.component.EditableValueHolder;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import javax.servlet.http.HttpServletRequest;

import java.security.Principal;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class DataMapRepository extends LinkedHashMap<String, Map<String, Object>> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_MAX_DATA_MAPS = 1000;
	private int maxDataMaps;

	public DataMapRepository() {
		maxDataMaps = DEFAULT_MAX_DATA_MAPS;
	}

	public DataMapRepository(DataMapRepository repository) {
		maxDataMaps = repository.maxDataMaps;
		putAll(repository);
	}

	public int getMaxDataMaps() {
		return maxDataMaps;
	}

	public void setMaxDataMaps(int maxDataMaps) {
		this.maxDataMaps = maxDataMaps;
	}

	protected boolean removeEldestEntry(Map.Entry eldest) {
		return size() > maxDataMaps;
	}

	public Map<String, Object> getDataMap(FacesContext ctx) {
		String id = getDataMapId(ctx);
		if (id == null)
			return null;
		return get(id);
	}

	public void setDataMap(FacesContext ctx, Map<String, Object> dataMap) {
		String id = getDataMapId(ctx);
		if (id == null)
			return;
		if (dataMap != null)
			put(id, dataMap);
		else
			remove(id);
	}

	public static String getDataMapId(FacesContext ctx) {
		UIViewRoot root = ctx.getViewRoot();
		if (root == null)
			return null;
		ExternalContext ectx = ctx.getExternalContext();
		String userId = null;
		Principal principal = ectx.getUserPrincipal();
		if (principal != null) {
			// Use the name of the authenticated user.
			userId = principal.getName();
		} else {
			// Use the browser ID of the anonymous user.
			userId = BrowserIdFilter.getBrowserId((HttpServletRequest) ectx.getRequest());
		}
		if (userId == null)
			return null;
		// Concatenate the user ID and the JSF view ID
		return userId + root.getViewId();
	}

	public static void saveValues(UIComponent comp, Map<String, Object> dataMap) {
		if (comp == null)
			return;
		if (comp instanceof EditableValueHolder) {
			// Input component. Put its value into the data map
			EditableValueHolder evh = (EditableValueHolder) comp;
			dataMap.put(comp.getId(), evh.getValue());
		}
		// Iterate over the children of the current component
		Iterator children = comp.getChildren().iterator();
		while (children.hasNext()) {
			UIComponent child = (UIComponent) children.next();
			// Recursive call
			saveValues(child, dataMap);
		}
	}

	public static void restoreValues(UIComponent comp, Map<String, Object> dataMap) {
		if (comp == null || dataMap == null)
			return;
		if (comp instanceof EditableValueHolder) {
			// Input component. Get its value from the data map
			// and clear any submitted value
			EditableValueHolder evh = (EditableValueHolder) comp;
			evh.setValue(dataMap.get(comp.getId()));
			evh.setSubmittedValue(null);
		}
		// Iterate over the children of the current component
		Iterator children = comp.getChildren().iterator();
		while (children.hasNext()) {
			UIComponent child = (UIComponent) children.next();
			// Recursive call
			restoreValues(child, dataMap);
		}
	}

}
