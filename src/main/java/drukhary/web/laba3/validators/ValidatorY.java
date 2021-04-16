package drukhary.web.laba3.validators;

import drukhary.web.laba3.AreaCheckingExeption.OutOfRangeException;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("ValidatorY")
public class ValidatorY implements Validator {

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object object) throws ValidatorException {
        FacesMessage msg;
        try{
//            String value = (String)object;
            Double y = (Double)object;
//                    Double.parseDouble(value);
            if (y<-3. || y>3)
                throw new OutOfRangeException();
        } catch (NumberFormatException e){
            msg = new FacesMessage("Неверный формат Y",
                            "Пожалуйста, введите вещественное число от -3.0 до 3.0 Например, -1.234");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        } catch (OutOfRangeException e) {
            msg = new FacesMessage("Y выходит из допустимого диапазона",
                    "Пожалуйста, введите вещественное число от -3.0 до 3.0 Например, -1.233");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
}
