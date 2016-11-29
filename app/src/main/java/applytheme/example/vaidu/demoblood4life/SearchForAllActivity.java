package applytheme.example.vaidu.demoblood4life;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Map;

public class SearchForAllActivity extends AppCompatActivity {




    private static final String LOGIN_REQUEST_URL = "http://wasisadman.net16.net/getDonars.php";
    private Map<String, String> params;

    Spinner spinnerUniversityName, spinnerBloodGroup;

    private Button btnSearch;

    private String dUniversityName, dBlood_Group;

    ArrayAdapter<CharSequence> adapterUniversityName, adapterBloodGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_for_all_users);

        btnSearch = (Button)findViewById(R.id.btn_search);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*StringRequest stringRequest = new StringRequest(Request.Method.POST, LOGIN_REQUEST_URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(SearchForAllActivity.this,response,Toast.LENGTH_LONG).show();
                                //startActivity(new Intent(getApplicationContext(),User_Donar_List.class));
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(SearchForAllActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                            }
                        }){
                    @Override
                    protected Map<String,String> getParams(){
                        Map<String,String> params = new HashMap<String, String>();
                        params.put("dUniversityName",dUniversityName);
                        params.put("dBlood_Group",dBlood_Group);
                        return params;
                    }

                };

                RequestQueue requestQueue = Volley.newRequestQueue(SearchForAllActivity.this);
                requestQueue.add(stringRequest);*/

                Log.i("universityname",dUniversityName);
                Log.i("Blood_group", dBlood_Group);

                Intent i = new Intent(SearchForAllActivity.this, User_Donar_List.class);
                i.putExtra("intentUniversity", dUniversityName);
                i.putExtra("intentBlood_group", dBlood_Group);
                startActivity(i);



                /*ProgressDialog progress = new ProgressDialog(SearchForAllActivity.this);
                progress.setMessage("Loading...");
                progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progress.show();*/

//                startActivity(new Intent(getApplicationContext(),User_Donar_List.class));
            }
        });

        /*Spinner for Uiversity Name Starts*/

        spinnerUniversityName = (Spinner) findViewById(R.id.spinner_university_name);

        adapterUniversityName = ArrayAdapter.createFromResource(SearchForAllActivity.this, R.array.university_name, android.R.layout.simple_spinner_item);

        adapterUniversityName.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        spinnerUniversityName.setAdapter(adapterUniversityName);

        spinnerUniversityName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(SearchForAllActivity.this, parent.getItemAtPosition(position)+" is selected", Toast.LENGTH_SHORT).show();

                dUniversityName = parent.getItemAtPosition(position).toString();
            }




            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /*Spinner for Uiversity Name Ends*/


       /* Spinner for Blood Group Name Starts*/

        spinnerBloodGroup = (Spinner) findViewById(R.id.spinner_blood_group);

        adapterBloodGroup = ArrayAdapter.createFromResource(this, R.array.blood_group, android.R.layout.simple_spinner_item);

        adapterBloodGroup.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        spinnerBloodGroup.setAdapter(adapterBloodGroup);

        spinnerBloodGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(SearchForAllActivity.this, parent.getItemAtPosition(position)+" is selected", Toast.LENGTH_SHORT).show();

                dBlood_Group = parent.getItemAtPosition(position).toString();

                if(dBlood_Group.equals("A+")){
                    dBlood_Group = "A_POS";
                }else if(dBlood_Group.equals("A-")){
                    dBlood_Group="A_NEG";
                }else if(dBlood_Group.equals("B+")){
                    dBlood_Group="B_POS";
                }else if(dBlood_Group.equals("B-")){
                    dBlood_Group="B_NEG";
                }else if(dBlood_Group.equals("O+")){
                    dBlood_Group="O_POS";
                }else if(dBlood_Group.equals("O-")){
                    dBlood_Group="O_NEG";
                }else if(dBlood_Group.equals("AB+")){
                    dBlood_Group="AB_POS";
                }else if(dBlood_Group.equals("AB-")){
                    dBlood_Group="AB_NEG";
                }

            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /*Spinner for BLood Group Name Ends*/



    }
}
