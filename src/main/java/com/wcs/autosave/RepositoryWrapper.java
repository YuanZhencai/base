package com.wcs.autosave;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;

import javax.servlet.ServletContext;

import java.util.Map;

public class RepositoryWrapper implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DataMapRepository repository;
    
    public RepositoryWrapper() {
        repository = new DataMapRepository();
    }
    
    public synchronized DataMapRepository getRepository() {
        return new DataMapRepository(repository);
    }

    public synchronized void setRepository(
            DataMapRepository repository) {
        if (repository != null)
            this.repository = new DataMapRepository(repository);
        else
            this.repository.clear();
    }

    public synchronized Map<String, Object> getDataMap(
            FacesContext ctx) {
        return repository.getDataMap(ctx);
    }
    
    public synchronized void setDataMap(FacesContext ctx,
            Map<String, Object> dataMap) {
        repository.setDataMap(ctx, dataMap);
    }
    
    public synchronized boolean hasDataMap(FacesContext ctx) {
        return getDataMap(ctx) != null;
    }
    
    public synchronized void clearDataMap(FacesContext ctx) {
        setDataMap(ctx, null);
    }
    
    public synchronized int getMaxDataMaps() {
        return repository.getMaxDataMaps();
    }

    public synchronized void setMaxDataMaps(int maxDataMaps) {
        repository.setMaxDataMaps(maxDataMaps);
    }

    public static RepositoryWrapper getManagedBean(FacesContext ctx) {
        Application app = ctx.getApplication();
        ValueBinding vb = app.createValueBinding("#{repositoryWrapper}");
        return (RepositoryWrapper) vb.getValue(ctx);
    }

    public static RepositoryWrapper getManagedBean(ServletContext ctx) {
        return (RepositoryWrapper) ctx.getAttribute("repositoryWrapper");
    }

}
