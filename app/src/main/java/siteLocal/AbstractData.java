package siteLocal;

/**
 * Created by Andrei on 5/25/2017.
 */

public abstract class AbstractData implements IData{
    protected Boolean isValid;

    public Boolean isValid() {
        return isValid;
    }

    public void setValid(Boolean valid) {
        isValid = valid;
    }
}
