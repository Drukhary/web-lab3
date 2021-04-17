package drukhary.web.laba3.managedBeans;
import lombok.Data;

import java.io.Serializable;

@Data
public class Node implements Serializable {
    private double x;
    private double y;
    private double radius;
}
