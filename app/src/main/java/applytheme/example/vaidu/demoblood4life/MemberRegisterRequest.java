package applytheme.example.vaidu.demoblood4life;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Guitorio on 10/16/2016.
 */
public class MemberRegisterRequest extends StringRequest{

    private static final String REGISTER_REQUEST_URL = "http://wasisadman.net16.net/Register.php";
    private Map<String, String> params;

    public MemberRegisterRequest(String name, String username, int age, String password, Response.Listener<String> listener){
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);

        params = new HashMap<>();
        params.put("name", name);
        params.put("username", username);
        params.put("password", password);
        params.put("age", age + "");

    }

    public Map<String, String> getParams() {
        return params;
    }

}
