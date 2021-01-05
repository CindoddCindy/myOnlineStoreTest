package mytokoonlinetest.myonliestoretest.auth.message.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
public class LoginForm {
    @NotBlank
    @Size(min=3, max = 60)
    private String name;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    public String getName() {
        return name;
    }

    public void setUsername(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
