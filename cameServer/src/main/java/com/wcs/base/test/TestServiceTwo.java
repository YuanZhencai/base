/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.wcs.base.test;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;


/**
 * @version $Revision: 211 $
 */
@Stateless
public class TestServiceTwo  implements Serializable{
    @EJB
    DatasourceService datasourceService;

	public void testDatasourceService() {
		//testfindAll
		System.out.println("***********************testDatasourceService:Start******************");
		Datasourcemstr datasourcemstr = new Datasourcemstr();
		datasourcemstr.setDatasourceDesc("test1");
		datasourceService.creatDatasource(datasourcemstr);
		datasourcemstr = new Datasourcemstr();
		datasourcemstr.setDatasourceDesc("test2");
		datasourceService.creatDatasource(datasourcemstr);
		System.out.println("***********************testDatasourceService:Find******************");
		List<Datasourcemstr> list = datasourceService.getDatasourceListAll();

		datasourcemstr = list.get(1);
		datasourceService.deleteDatasourceById(datasourcemstr.getDatasourcemstrId());
		
		datasourcemstr = list.get(0);
		datasourcemstr.setDatasourceDesc("uptest1");
		datasourceService.updateDatasource(datasourcemstr);
		
		datasourcemstr = datasourceService.getDatasourceById(datasourcemstr.getDatasourcemstrId());
		datasourcemstr =datasourceService.getDatasourceSingle(datasourcemstr.getDatasourcemstrId());
		System.out.println("***********************testDatasourceService:End******************");
		
	}

}
