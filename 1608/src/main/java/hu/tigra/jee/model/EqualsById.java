package hu.tigra.jee.model;

public abstract class EqualsById {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o instanceof Long) {
            // Mivel az alábbi helyre nem tudok a MemberConverter segítségével sem  Entity objektumot
            // eljuttatni és helyette annak az ID-ja kerül az adott helyen a compareValue lokális
            // válltozóba evvel az if ággal teszem lehetővé, hogy form-on select-be (drop-down-ba)
            // kerüljön az Entity objektumom.
            // jboss-jsf-api_2.2_spec-2.2.8-sources.jar!/javax/faces/component/SelectUtils.java:109
            Long otherId = (Long) o;
            return getId().equals(otherId);

        } else if (o instanceof EqualsById) {
            EqualsById equalsById = (EqualsById) o;
            return getId().equals(equalsById.getId());

        } else {
            return false;
        }
    }
/*
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EqualsById)) return false;

    }
*/

    @Override
    public int hashCode() {
        return getId().hashCode();
    }

    public abstract Long getId();

    public abstract void setId(Long id);
}
