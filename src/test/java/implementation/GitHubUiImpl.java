package implementation;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebElement;
import utils.WebAction;

import java.util.List;
import java.util.Optional;

public class GitHubUiImpl {
    GitHubUiPage gitHubUiPage = new GitHubUiPage();
    WebAction action = new WebAction();

    public void searchGithubUserId(String input) {
        if(StringUtils.isEmpty(input))  {
           throw new RuntimeException("Input should not be null or empty");
        }
        action.enter(gitHubUiPage.txt_Search,input);
    }

    public void clickRepo(String input1,String input2) {
        if(StringUtils.isAllEmpty(input1,input2))  {
            throw new RuntimeException("Inputs should not be null or empty");
        }
        List<WebElement> elements = action.getElements(gitHubUiPage.lst_repoNames);
        if(elements.size() == 0) {
            throw new RuntimeException("No elements retrieved");
        }
        Optional<WebElement> first = elements
                .stream()
                .filter(e -> e.getText().contains(input1))
                .findFirst();
        if(first.isPresent()) {
            first.get().click();
        } else {
            throw new RuntimeException("Element not presented");
        }
    }

    public String  getStartCount() {
        return action.getText(gitHubUiPage.lbl_starCount);
    }
}
