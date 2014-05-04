package com.wcs.autosave;

import javax.faces.component.EditableValueHolder;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class AutoSaveListener implements PhaseListener {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PhaseId getPhaseId() {
        return PhaseId.PROCESS_VALIDATIONS;
    }
    
    public void beforePhase(PhaseEvent e) {
    }

    public void afterPhase(PhaseEvent e) {
        if (!e.getPhaseId().equals(PhaseId.PROCESS_VALIDATIONS))
            return;
        FacesContext ctx = e.getFacesContext();
        Map headers = ctx.getExternalContext().getRequestHeaderMap();
        if ("Auto-Save".equals(headers.get("Ajax-Request"))) {
            // Auto-Save Request. Save data into the repository.
            saveCurrentView();
        } else {
            // The user must have clicked the Submit button.
            if (!ctx.getMessages().hasNext()) {
                // There are no error messages.
                // This means the final submitted data is valid and
                // the temporary auto-saved data is no longer needed.
                RepositoryWrapper wrapper
                    = RepositoryWrapper.getManagedBean(ctx);
                wrapper.clearDataMap(ctx);
            }
        }
    }

    public void saveCurrentView() {
        // Get the faces context of the current request.
        FacesContext ctx = FacesContext.getCurrentInstance();
        // Get the root component of the current view.
        UIViewRoot root = ctx.getViewRoot();
        // Create a new data map.
        Map<String, Object> dataMap = new HashMap<String, Object>();
        // Store the component values into the data map.
        DataMapRepository.saveValues(root, dataMap);
        // Make the data map unmodifiable.
        dataMap = Collections.unmodifiableMap(dataMap);
        // Get the managed bean instance wrapping the data repository.
        RepositoryWrapper wrapper = RepositoryWrapper.getManagedBean(ctx);
        // Store the data map into the repository.
        wrapper.setDataMap(ctx, dataMap);
        // Stop request processing.
        ctx.responseComplete();
    }

    /** Utility method useful for debugging. */
    public void printTree(UIComponent comp, int level) {
        if (comp == null)
            return;
        Object value = null;
        if (comp instanceof EditableValueHolder)
            value = ((EditableValueHolder) comp).getValue();

        for (int i = 0; i < level; i++)
            System.out.print("    ");
        System.out.print(comp.getFamily());
        System.out.print(" - ");
        System.out.print(comp.getRendererType());
        System.out.print(" - [");
        System.out.print(comp.getId());
        System.out.print("]");
        if (value != null) {
            System.out.print(" - ");
            if (value instanceof Object[]) {
                Object array[] = (Object[]) value;
                for (int i = 0; i < array.length; i++) {
                    System.out.print(array[i]);
                    System.out.print(" ");
                }
            } else
                System.out.print(value);
        }
        System.out.println();

        Iterator children = comp.getChildren().iterator();
        while (children.hasNext()) {
            UIComponent child = (UIComponent) children.next();
            printTree(child, level + 1);
        }
    }

}
