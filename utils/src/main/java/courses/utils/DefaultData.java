package courses.utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DefaultData<T> implements Serializable {
    private String token = null;
    /**
     * Define the goal of the request
     */
    private ActionEnum action;
    /**
     * The type of the data on which the action is done
     */
    private DataTypeEnum dataType;
    private String login = null;
    private String mdp = null;
    private T object = null;
    private String filePath = null;
    private String message;

    public DefaultData() {
    }

    DefaultData(DataTypeEnum dataType, T object) {
        this.dataType = dataType;
        this.object = object;
    }

    public DefaultData(String token, DataTypeEnum dataType, T object) {
        this.token = token;
        this.dataType = dataType;
        this.object = object;
    }

    public DefaultData(String token) {
        this.token = token;
    }

    public void setAuth(String login, String password) {
        this.login = login;
        this.mdp = password;
    }

    public boolean isAuthSet() {
        return login != null && mdp != null;
    }

    public boolean isTokenSet() {
        return token != null;
    }

}
