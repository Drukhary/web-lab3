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
import java.util.List;

@Data
public class ManagedPointBean implements Serializable {
    public ManagedPointBean(){
        points = elementInfoDAO.findAllElementInfos();
        resultMessage="";
    }
    private Node node = new Node();
    private ElementInfoDAO elementInfoDAO = new ElementInfoDAO();
    private List<ElementInfo> points;
    private boolean result;
    private String resultMessage;


    public synchronized void addPoint() {
        try {
//            node.setY(Double.parseDouble(node.getRaw_y()));
            AreaChecker.CheckRange(node);
            ElementInfo elementInfo = AreaChecker.AreaCheck(node);
            elementInfoDAO.saveElementInfo(elementInfo);
            points.add(elementInfo);
            result = elementInfo.isResult();
            resultMessage = result ? AreaCheckConstants.POSITIVE_RESULT:AreaCheckConstants.NEGATIVE_RESULT;
            node = new Node();
        } catch (OutOfRangeException e){
            resultMessage=e.getMessage();
        }
    }
}
