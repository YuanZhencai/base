package com.wcs.autosave;

public class SupportBean implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
    private String email;
    private String versions[];
    private String platform;
    private String browser;
    private boolean crash;
    private String problem;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String[] getVersions() {
        return versions;
    }

    public void setVersions(String[] versions) {
        this.versions = versions;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public boolean getCrash() {
        return crash;
    }

    public void setCrash(boolean crash) {
        this.crash = crash;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String submit() {
        return "submit";
    }

}
