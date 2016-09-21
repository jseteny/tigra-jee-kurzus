package hu.tigra.jee.data;

import hu.tigra.jee.model.Member;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Az Owner select-hez (dropdown-hoz)
 */
@Named("memberConverterBean")
@FacesConverter(value = "memberConverter")
public class MemberConverter implements Converter {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Object getAsObject(FacesContext ctx, UIComponent component, String value) {
        // entityManager == null mert a MemberConverter-be nem sikerült beinjektáltatni
        // semmilyen EntityManager-t. evaluateExpressionGet() hatására az újonnan
        // keletkező MemberConverter-be viszont ki lesz töltve.
        EntityManager em = FacesContext.getCurrentInstance().getApplication().
                evaluateExpressionGet(ctx, "#{memberConverterBean}", MemberConverter.class).entityManager;
        return em.find(Member.class, Long.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext ctx, UIComponent component, Object obj) {
        return String.valueOf(obj);
    }
}