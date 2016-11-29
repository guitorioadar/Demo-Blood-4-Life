package applytheme.example.vaidu.demoblood4life;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MemberLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_login);

        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final Button bLogin = (Button) findViewById(R.id.bLogin);



        /*final TextView registerLink = (TextView) findViewById(R.id.tvRegisterHere);

        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(MemberLoginActivity.this, MemberRegisterActivity.class);
                MemberLoginActivity.this.startActivity(registerIntent);
            }
        });*/


        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String username = etUsername.getText().toString();
                final String password = etPassword.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if(success){

                                String name = jsonResponse.getString("name");
                                int age = jsonResponse.getInt("age");

                                Intent i = new Intent(MemberLoginActivity.this, MemberAreaActivity.class);
                                i.putExtra("name", name);
                                i.putExtra("username", username);
                                i.putExtra("age", age);
                                i.putExtra("password", password);

                                MemberLoginActivity.this.startActivity(i);

                            }else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(MemberLoginActivity.this);
                                builder.setMessage("Login Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };

                MemberLoginRequest loginRequest = new MemberLoginRequest(username, password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(MemberLoginActivity.this);
                queue.add(loginRequest);


            }
        });





    }
}
