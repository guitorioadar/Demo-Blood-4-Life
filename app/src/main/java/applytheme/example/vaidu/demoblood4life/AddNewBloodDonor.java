package applytheme.example.vaidu.demoblood4life;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public class AddNewBloodDonor extends AppCompatActivity {

    ImageView iv;

    private RadioGroup radioSexGroup;
    private RadioButton radioButton;
//    private Button btnDisplay;


    private Button btn_submit;

    Spinner spinnerUniversityName, spinnerBloodGroup;
    ArrayAdapter<CharSequence> adptUniName, adapterBloodGroup;

    private EditText etFirstName, etLastName, etWeigth, etDateOfBirth, etLastBloodDonate, etContactNo, etEmail, etPassword, etConPassword;
    private String firstName, lastName, weight, dateOfBirth, lastBloodDonate, contactNo, email, password, conPassword, university_name;
    private String blood_gorup, gender = null, male_female_check = null;
    private Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_blood_donor);

        etFirstName = (EditText) findViewById(R.id.editText_First_name);
        etLastName = (EditText) findViewById(R.id.editText_last_name);
        etWeigth = (EditText) findViewById(R.id.editText_weight);
        etDateOfBirth = (EditText) findViewById(R.id.date_of_birth);
        etLastBloodDonate = (EditText) findViewById(R.id.last_blood_donate_date);
        etContactNo = (EditText) findViewById(R.id.contact_number);
        etEmail = (EditText) findViewById(R.id.new_email_id);
        etPassword = (EditText) findViewById(R.id.new_pass);
        etConPassword = (EditText) findViewById(R.id.new_confirmpass);

//        onClickListenerRadioButton();


        /*Spinner for Uiversity Name Starts*/

        spinnerUniversityName = (Spinner) findViewById(R.id.Spinner_Select_Uni_Area);

        adptUniName = ArrayAdapter.createFromResource(AddNewBloodDonor.this, R.array.university_name, android.R.layout.simple_spinner_item);

        adptUniName.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        spinnerUniversityName.setAdapter(adptUniName);


        spinnerUniversityName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(AddNewBloodDonor.this, parent.getItemAtPosition(position) + " is selected", Toast.LENGTH_SHORT).show();

                university_name = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /*Spinner for Uiversity Name Ends*/


        /* Spinner for Blood Group Name Starts*/

        spinnerBloodGroup = (Spinner) findViewById(R.id.spinner_Blood_Group);

        adapterBloodGroup = ArrayAdapter.createFromResource(this, R.array.blood_group, android.R.layout.simple_spinner_item);

        adapterBloodGroup.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        spinnerBloodGroup.setAdapter(adapterBloodGroup);

        spinnerBloodGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(AddNewBloodDonor.this, parent.getItemAtPosition(position) + " is selected", Toast.LENGTH_SHORT).show();

                blood_gorup = parent.getItemAtPosition(position).toString();

                if(blood_gorup.equals("A+")){
                    blood_gorup = "A_POS";
                }else if(blood_gorup.equals("A-")){
                    blood_gorup="A_NEG";
                }else if(blood_gorup.equals("B+")){
                    blood_gorup="B_POS";
                }else if(blood_gorup.equals("B-")){
                    blood_gorup="B_NEG";
                }else if(blood_gorup.equals("O+")){
                    blood_gorup="O_POS";
                }else if(blood_gorup.equals("O-")){
                    blood_gorup="O_NEG";
                }else if(blood_gorup.equals("AB+")){
                    blood_gorup="AB_POS";
                }else if(blood_gorup.equals("AB-")){
                    blood_gorup="AB_NEG";
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /*Spinner for BLood Group Name Ends*/



       /* Capture camera photo*/


        Button b1 = (Button) findViewById(R.id.btnCaptureImage);
        iv = (ImageView) findViewById(R.id.imageView);

        radioSexGroup = (RadioGroup) findViewById(R.id.radioGroup);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 0);

                int selectedId = radioSexGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(selectedId);

                male_female_check = radioButton.getText().toString();

            }
        });


       /* Submit Button Starts */


        btn_submit = (Button) findViewById(R.id.btn_submit);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_SHORT).show();

                addNewUser();


            }
        });

        /*Submit Button Ends*/


    }


    private void addNewUser() {

        firstName = etFirstName.getText().toString().trim();
        lastName = etLastName.getText().toString().trim();
        weight = etWeigth.getText().toString().trim();
        final String image = getStringImage(bitmap);
        dateOfBirth = etDateOfBirth.getText().toString().trim();
        lastBloodDonate = etLastBloodDonate.getText().toString().trim();
        contactNo = etContactNo.getText().toString().trim();
        email = etEmail.getText().toString().trim();
        password = etPassword.getText().toString().trim();
        conPassword = etConPassword.getText().toString().trim();
        //gender = "Male";
        gender = male_female_check;

        //final ProgressDialog loading = ProgressDialog.show(getApplicationContext(), "Adding new user....", "Please wait", false, false);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://wasisadman.net16.net/addUser.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(AddNewBloodDonor.this, response, Toast.LENGTH_LONG).show();
            }
            }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AddNewBloodDonor.this, error.toString(), Toast.LENGTH_LONG).show();
            }
            }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("firstName", firstName);
                params.put("lastName", lastName);
                params.put("gender", gender);
                params.put("university_name", university_name);
                params.put("image", image);
                params.put("blood_group", blood_gorup);
                params.put("weight", weight);
                params.put("date_of_birth", dateOfBirth);
                params.put("last_blood_donate", lastBloodDonate);
                params.put("contact_no", contactNo);
                params.put("email", email);
                params.put("password", password);
                params.put("conpassword", conPassword);

                return params;
            }

            };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        bitmap = (Bitmap) data.getExtras().get("data");
        iv.setImageBitmap(bitmap);
    }

        /* Capture camera photo*/


}

