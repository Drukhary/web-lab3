package drukhary.web.laba3.managedBeans;


import drukhary.web.laba3.AreaCheckingExeption.OutOfRangeException;
import drukhary.web.laba3.constant.AreaCheckConstants;
import drukhary.web.laba3.model.ElementInfo;
import drukhary.web.laba3.model.AreaChecker;
import lombok.Data;

import javax.naming.NamingException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Data
public class ManagedPointBean implements Serializable {
    private ElementInfoDAO elemInfoDao;
    private Node node;
    private List<ElementInfo> points;
    private String resultMessage;

    public synchronized void addPoint() {
        try {
            AreaChecker.CheckRange(node);
            ElementInfo elementInfo = AreaChecker.AreaCheck(node);
            elemInfoDao.saveElementInfo(elementInfo);
            points.add(elementInfo);
            resultMessage = elementInfo.isResult() ? AreaCheckConstants.POSITIVE_RESULT:AreaCheckConstants.NEGATIVE_RESULT;
            node = new Node();
        } catch (OutOfRangeException| SQLException|NamingException e){
            resultMessage=e.getMessage();
        }
    }
    public synchronized void deleteAllElements(){
        try {
            elemInfoDao.deleteElementInfos();
            points = new ArrayList<>();
            node = new Node();
            resultMessage = null;
        } catch (SQLException|NamingException e){
            resultMessage = e.getMessage();
        }
    }
}
