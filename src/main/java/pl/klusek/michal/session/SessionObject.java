package pl.klusek.michal.session;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import pl.klusek.michal.model.User;

@Component
@SessionScope
public class SessionObject {
    private User user = null;
    private String info = null;
    private String findPattern;
    private String transmissionTypeFilter;
    private String vehicleTypeFilter;

    public SessionObject() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getInfo() {
        String temp = this.info;
        this.info = null;
        return temp;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getFindPattern() {
        return findPattern;
    }

    public void setFindPattern(String findPattern) {
        this.findPattern = findPattern;
    }

    public boolean isLogged(){
        return this.user != null; //zwraca true jeśli użytkownik jest zalogowany
    }

    public void logout(){
        this.user = null;
    }

    public String getTransmissionTypeFilter() {
        return transmissionTypeFilter;
    }

    public void setTransmissionTypeFilter(String transmissionTypeFilter) {
        this.transmissionTypeFilter = transmissionTypeFilter;
    }

    public String getVehicleTypeFilter() {
        return vehicleTypeFilter;
    }

    public void setVehicleTypeFilter(String vehicleTypeFilter) {
        this.vehicleTypeFilter = vehicleTypeFilter;
    }
}
