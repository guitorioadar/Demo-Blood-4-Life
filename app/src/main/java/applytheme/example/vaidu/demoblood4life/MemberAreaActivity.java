package applytheme.example.vaidu.demoblood4life;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class MemberAreaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_area);


        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etAge = (EditText) findViewById(R.id.etAge);
        final TextView welcomeMessage = (TextView) findViewById(R.id.tvWelcomeMsg);


        Intent i = getIntent();
        String name = i.getStringExtra("name");
        String username = i.getStringExtra("username");
        int age = i.getIntExtra("age", -1);


        String message = name + "" + " Hello You !! you are awesome :)";
        welcomeMessage.setText(message);
        etUsername.setText(username);
        etAge.setText(age + "");

    }
}
