package think.near.app.api.util;

public class SimpleResponse {
	private String status;
	private String message;

	public SimpleResponse(String s, String m) {
		setStatus(s);
		setMessage(m);
	}
	
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String s) {
		this.status = s;
	}
	public String getMessage() {
		return this.message;
	}

	public void setMessage(String m) {
		this.message = m;
	}
}
