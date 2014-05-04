package com.wcs.autosave;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class DataMapPersistence implements ServletContextListener {

    private File getDataFile(ServletContext sctx) {
        String path = sctx.getRealPath("/WEB-INF/repository.ser");
        if (path == null)
            return null;
        return new File(path);
    }
    
    public void contextInitialized(ServletContextEvent e) {
        ServletContext sctx = e.getServletContext();
        File dataFile = getDataFile(sctx);
        if (dataFile == null || !dataFile.exists())
            return;
        try {
            ObjectInputStream in = new ObjectInputStream(
                    new BufferedInputStream(
                    new FileInputStream(dataFile)));
            try {
                // Read the data repository from the file.
                Object repository = in.readObject();
                // Store the loaded repository into the application scope.
                sctx.setAttribute("loadedRepository", repository);
            } finally {
                in.close();
            }
        } catch (Exception x) {
            sctx.log("Loading Error", x);
        }
    }

    public void contextDestroyed(ServletContextEvent e) {
        ServletContext sctx = e.getServletContext();
        File dataFile = getDataFile(sctx);
        if (dataFile == null)
            return;
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new BufferedOutputStream(
                    new FileOutputStream(dataFile)));
            try {
                // Get a copy of the data repository from the wrapper bean.
                RepositoryWrapper wrapper
                    = RepositoryWrapper.getManagedBean(sctx);
                Object repository = wrapper.getRepository();
                // Serialize the data repository into the file.
                out.writeObject(repository);
            } finally {
                out.close();
            }
        } catch (Exception x) {
            sctx.log("Saving Error", x);
        }
    }

}
