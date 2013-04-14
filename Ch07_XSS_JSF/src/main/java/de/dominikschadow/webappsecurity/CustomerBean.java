package de.dominikschadow.webappsecurity;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "customer")
@SessionScoped
public class CustomerBean implements Serializable {
	private static final long serialVersionUID = 600561947836364528L;

	private String status1;
	private String status2;

	private static Map<String, String> status1Vals;
	static {
		status1Vals = new LinkedHashMap<String, String>();
		status1Vals.put("<script>alert('XSS via Map')</script>", "XSS");
		status1Vals.put("Status 2", "Status 2");
		status1Vals.put("Status 3", "Status 3");
	}

	private static Status[] status2List = new Status[3];
	static {
		status2List[0] = new Status("<script>alert('XSS via Array')</script>", "XSS");
		status2List[1] = new Status("Status 2", "Status 2");
		status2List[2] = new Status("Status 3", "Status 3");
	}

	public String getStatus1() {
		return status1;
	}

	public void setStatus1(String status1) {
		this.status1 = status1;
	}

	public String getStatus2() {
		return status2;
	}

	public void setStatus2(String status2) {
		this.status2 = status2;
	}

	public Map<String, String> getStatus1Vals() {
		return status1Vals;
	}

	public static class Status {
		public String statusLabel;
		public String statusValue;

		public Status(String statusLabel, String statusValue) {
			this.statusLabel = statusLabel;
			this.statusValue = statusValue;
		}

		public String getStatusLabel() {
			return statusLabel;
		}

		public String getStatusValue() {
			return statusValue;
		}
	}

	public Status[] getStatus2Vals() {
		return status2List;
	}
}