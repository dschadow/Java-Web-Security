/*
 * Copyright (C) 2013 Dominik Schadow, dominikschadow@gmail.com
 *
 * This file is part of JavaWebAppSecurity.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *  http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.dominikschadow.webappsecurity;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * 
 * @author Dominik Schadow
 */
@ManagedBean(name = "customer")
@SessionScoped
public class CustomerBean implements Serializable {
	private static final long serialVersionUID = 600561947836364528L;

	private String status1;
	private String status2;

	// first String: key (label), second String value
	private static Map<String, String> status1Values = new LinkedHashMap<String, String>();
	static {
		status1Values.put("<script>alert('XSS with JSF (Map)')</script>", "<script>alert('XSS with JSF (Map)')</script>");
		status1Values.put("Status 2", "Status 2");
		status1Values.put("Status 3", "Status 3");
	}

	// first String: key (label), second String value
	private static Status[] status2Values = new Status[3];
	static {
		status2Values[0] = new Status("<script>alert('XSS with JSF (Array)')</script>", "<script>alert('XSS with JSF (Array)')</script>");
		status2Values[1] = new Status("Status 2", "Status 2");
		status2Values[2] = new Status("Status 3", "Status 3");
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
		return status1Values;
	}

    public Status[] getStatus2Vals() {
        return status2Values;
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
}