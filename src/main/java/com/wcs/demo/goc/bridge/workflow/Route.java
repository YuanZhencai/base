package com.wcs.demo.goc.bridge.workflow;


public abstract class Route implements IRoute {

	private String name;

	private String start;
	private String end;
	private String status;

	@Override
	public void excute() {

	}

	public enum RouteEnd {
		DOC_0_PASS("1"),
		DOC_1_PASS("2"),
		REPORT_0_PASS("1"),
		REPORT_1_PASS("2");

		private String end;

		RouteEnd(String end) {
			this.end = end;
		}
		
		public String getEnd() {
			return end;
		}

	}

	public String findEndBy(String flowType) {
		String f_s_s = flowType + "_" + start + "_" + status;
		return Enum.valueOf(RouteEnd.class, f_s_s).getEnd();
	}

	public String getResult(String flowType) {
		String findEndBy = findEndBy(flowType);
		return findEndBy == null ? name : start + " → " + findEndBy;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public static void main(String[] args) {
		System.out.println(Enum.valueOf(RouteEnd.class, "DOC_0_P").getEnd());
	}

}
