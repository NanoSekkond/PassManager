public class SiteUserPassword {
    private String site;
    private String user;
    private String password;
    
    SiteUserPassword(String site, String user, String password) {
        this.site = site;
        this.user = user;
        this.password = password;
    }

    public String getSite() {
        return site;
    }

    public String getUser() {
        return user;
    }
    
    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return getSite() + " " + getUser() + " " + getPassword();
    }
}
