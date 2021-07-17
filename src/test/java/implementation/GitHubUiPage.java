package implementation;

import utils.PropertyLoader;

public class GitHubUiPage {
    private PropertyLoader prop = new PropertyLoader("locators");
    public String  txt_Search = prop.getProperty("txt_Search");
    public String  lst_repoNames = prop.getProperty("lst_repoNames");
    public String  lbl_starCount = prop.getProperty("lbl_starCount");
}
