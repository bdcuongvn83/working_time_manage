package net.demo;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class HelloBean {
    private String name;

    private String password;

    public String getName() {

        return name;

    }

    public void setName(String name) {

        this.name = name;

    }

    public String getPassword() {

        return password;

    }

    public void setPassword(String password) {

        this.password = password;

    }

    public String checkUser() {

        String str = null;

        if (name.equalsIgnoreCase("Manu_M") && password.equalsIgnoreCase("mm")) {

            str = "success";

            return str;

        } else {

            str = "failure";

            return str;

        }

    }
}
