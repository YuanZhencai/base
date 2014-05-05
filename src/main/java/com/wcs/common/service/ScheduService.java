package com.wcs.common.service;

import javax.annotation.PostConstruct;
import javax.ejb.*;
import java.io.Serializable;

/**
 * <p>Project: BTC</p>
 * <p>Description: 同步任务调度</p>
 * <p>Copyright (c) 2012 Wilmar Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:hujianguang@wcs-global.com">胡建光</a>
 */

@Startup
@Singleton
public class ScheduService implements Serializable {

    private static final long serialVersionUID = -4531023608569097125L;

    @EJB
    public SyncJsonService syncService;

    @PostConstruct
    public void init() {
//        this.syncTask();
    }

    /**
     * <p>Description: 执行同步任务</p>
     */
//   @Schedule(hour="0",dayOfWeek="*")
    public void syncTask() {
        syncService.process();

    }

}
