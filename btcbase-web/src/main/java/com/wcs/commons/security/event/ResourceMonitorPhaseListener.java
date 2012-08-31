package com.wcs.commons.security.event;

import java.util.List;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.apache.commons.lang3.StringUtils;

import com.wcs.base.collections.GenericTreeNode;
import com.wcs.base.util.JSFUtils;
import com.wcs.commons.conf.WebappConfig;
import com.wcs.commons.security.model.Resource;
import com.wcs.commons.security.vo.ResourceTree;

public class ResourceMonitorPhaseListener implements PhaseListener {
	public void beforePhase(PhaseEvent pe) {
		//if (pe.getPhaseId() == PhaseId.RENDER_RESPONSE) {
		//	System.out.println("Processing new Request!");
		//}
		//System.out.println("before - " + pe.getPhaseId().toString());
		
		String resCode = (String)JSFUtils.getSession().get(WebappConfig.NEXT_DISPLAY_RES_CODE);
		String viewId = JSFUtils.getViewId();

		ResourceTree tree = (ResourceTree)JSFUtils.getApplicationMap().get( WebappConfig.RES_TREE );
		if (tree==null) return;

		GenericTreeNode<Resource> node = null;
		
		if (StringUtils.isNotEmpty(resCode)){
			// 通过root 和 resource Code 查找节点
			node = tree.findByCode(resCode);
		} else if (StringUtils.isNotEmpty(viewId)){
			// 通过root 和 resource Uri 查找节点
			node = tree.findByUri(viewId);
		}
		
		List<String> parentCodeChain = tree.findParentChain(node);

		JSFUtils.getApplicationMap().put(WebappConfig.NEXT_DISPLAY_RES_PARENT_CODES,parentCodeChain);
	}

	public void afterPhase(PhaseEvent pe) {}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RENDER_RESPONSE; 
	}
}
