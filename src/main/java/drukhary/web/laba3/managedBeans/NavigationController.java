package drukhary.web.laba3.managedBeans;

import lombok.Data;

import java.io.Serializable;
@Data
public class NavigationController implements Serializable {
    private String pageId;
    public String moveToIndex(){
        return "index";
    }
    public String moveToStart(){
        return "start";
    }
}
