package drukhary.web.laba3.managedBeans;


import drukhary.web.laba3.AreaCheckingExeption.OutOfRangeException;
import drukhary.web.laba3.constant.AreaCheckConstants;
import drukhary.web.laba3.model.ElementInfo;
import drukhary.web.laba3.model.AreaChecker;
import drukhary.web.laba3.model.Node;
import drukhary.web.laba3.util.ElementInfoDAO;
import lombok.Data;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class ManagedPointBean implements Serializable {
    public ManagedPointBean(){
        points = ElementInfoDAO.findAllElementInfos();
        resultMessage="";
    }
    private Node node = new Node();
    private List<ElementInfo> points = new ArrayList<ElementInfo>();
    private String resultMessage;


    public synchronized void addPoint() {
        try {
            AreaChecker.CheckRange(node);
            ElementInfo elementInfo = AreaChecker.AreaCheck(node);
            ElementInfoDAO.saveElementInfo(elementInfo);
            points.add(elementInfo);
            resultMessage = elementInfo.isResult() ? AreaCheckConstants.POSITIVE_RESULT:AreaCheckConstants.NEGATIVE_RESULT;
            node = new Node();
        } catch (OutOfRangeException e){
            resultMessage=e.getMessage();
        }
    }
    public synchronized void deleteAllElements(){
        ElementInfoDAO.deleteElementInfos();
        points = new ArrayList<ElementInfo>();
        node = new Node();
        resultMessage=null;
    }
}
