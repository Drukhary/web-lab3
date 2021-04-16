package drukhary.web.laba3.model;

import javax.persistence.Table;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import lombok.Data;

import static drukhary.web.laba3.constant.AreaCheckConstants.NEGATIVE_RESULT;
import static drukhary.web.laba3.constant.AreaCheckConstants.POSITIVE_RESULT;

//@Entity
//@Table(name= "elements")
@Data
public class ElementInfo implements Serializable {
    public ElementInfo() {
    }
    private int id;
    private double x;
    private double y;
    private double radius;
    private boolean result;
    private java.sql.Date date;
    private double processTime;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "element_id_seq")
    @SequenceGenerator(name="id",sequenceName = "element_id_seq")
    public int getId() {
        return id;
    }

    public String getFormatDate() {
        return date.toString();
    }
    public String getFormatResult() {
        return this.result ? POSITIVE_RESULT : NEGATIVE_RESULT;
    }
    public String getFormatProcessTime() { return java.math.BigDecimal.valueOf(this.processTime).toPlainString() + "c"; }
}
