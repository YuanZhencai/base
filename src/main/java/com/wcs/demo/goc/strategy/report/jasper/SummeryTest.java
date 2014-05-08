package com.wcs.demo.goc.strategy.report.jasper;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.wcs.common.model.Resourcemstr;

public class SummeryTest {

	@Test
	public void testResourceSummery() {
		List<Resourcemstr> data = new ArrayList<Resourcemstr>();
		Resourcemstr r = null;
		for (int i = 0; i < 10; i++) {
			r = new Resourcemstr();
			r.setName("prant" + i);
			r.setSeqNo(i + "");
			r.setUri("uri" + i);
			data.add(r);
		}
		SummeryInterface summery = new ResourceSummery(data);
		summery.summery();
	}

}
