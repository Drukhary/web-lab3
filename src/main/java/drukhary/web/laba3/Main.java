package drukhary.web.laba3;

import drukhary.web.laba3.model.AreaChecker;
import drukhary.web.laba3.model.ElementInfo;
import drukhary.web.laba3.model.Node;
import drukhary.web.laba3.util.ElementInfoDAO;

import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        ElementInfoDAO elementInfoDAO = new ElementInfoDAO();
//        ElementInfo elementInfo = new ElementInfo();
//        elementInfo.setDate(java.sql.Date.valueOf(LocalDateTime.now().toLocalDate()));
//        elementInfo.setRadius(1);
//        elementInfo.setProcessTime(0.1);
//        elementInfo.setResult(true);
//        elementInfo.setX(0.);
//        elementInfo.setY(0.);
////        elementsDAO.saveElementInfo(elementInfo);
////        elementsDAO.saveElementInfo(elementInfo);
//        List<ElementInfo> elementInfoList = elementInfoDAO.findAllElementInfos();
//        for (ElementInfo i:elementInfoList){
//            System.out.println(i.getId());
//        };
        Node node = new Node();
        node.setRadius(5.0);
        node.setX(4.25);
        node.setY(2.75);
        System.out.println(AreaChecker.AreaCheckCondition(node));
    }
}
