package applytheme.example.vaidu.demoblood4life;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class User_Donar_List extends AppCompatActivity {

    private String dUniversityName, dBlood_Group;

    private static final String LOGIN_REQUEST_URL = "http://wasisadman.net16.net/getDonars.php";

    private ListView donarListView;

    private JSONObject finalObject;
    private Donars donars;
    private URL url;
    private InputStream stream;
    private StringBuffer buffer;
    private String line;
    private String finalJson;
    private JSONObject parentObject;
    private JSONArray parentArray;
    private List<Donars> donarsList;
    private String donorUniversity;
    private String donorBlood_group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__donar__list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        donarListView = (ListView) findViewById(R.id.donarListView);


        //Create global configuration and initialize ImageLoader with this config
//        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
//        ImageLoader.getInstance().init(config);
        //compile 'com.nostra13.universalimageloader:universal-image-loader:1.9.5' this compile wile must add build.gradle

        // Create default option which wile be used for every
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true) // default
                .cacheOnDisk(true) // default
                .build();

        // Create global configuration and initialize ImageLoader with this config
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .defaultDisplayImageOptions(options)
                .build();
        ImageLoader.getInstance().init(config); // Do it application start
        //compile 'com.nostra13.universalimageloader:universal-image-loader:1.9.5' this compile wile must add build.gradle

        /*StringRequest stringRequest = new StringRequest(Request.Method.POST, LOGIN_REQUEST_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(User_Donar_List.this,response,Toast.LENGTH_LONG).show();
                        //startActivity(new Intent(getApplicationContext(),User_Donar_List.class));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(User_Donar_List.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("dUniversityName",donorUniversity);
                params.put("dBlood_Group",donorBlood_group);
                return params;
            }

//            Intent intent = getIntent();
//            String donorUniversity = intent.getExtras().getString("intentUniversity");
//            String donorBlood_group = intent.getExtras().getString("intentBlood_group");

        };

        RequestQueue requestQueue = Volley.newRequestQueue(User_Donar_List.this);
        requestQueue.add(stringRequest);*/

        Intent intent = getIntent();
        donorUniversity = intent.getExtras().getString("intentUniversity").toString().trim();
        donorBlood_group = intent.getExtras().getString("intentBlood_group").toString().trim();

        //Toast.makeText(getApplicationContext(),""+donorBlood_group,Toast.LENGTH_SHORT).show();




        final ProgressDialog progress = new ProgressDialog(User_Donar_List.this);
        progress.setMessage("Loading...");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.show();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                progress.dismiss();
            }
        }, 5000);


        new JSONTask().execute("http://wasisadman.net16.net/getDonars.php?donorUniversity=" + donorUniversity+"&donorBlood_group="+donorBlood_group);

    }

    public class JSONTask extends AsyncTask<String, String, List<Donars>> {


        @Override
        protected List<Donars> doInBackground(String... params) {

            HttpURLConnection connection = null;
            BufferedReader reader = null;
            try {

                url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                buffer = new StringBuffer();
                line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                finalJson = buffer.toString();

                parentObject = new JSONObject(finalJson);
                parentArray = parentObject.getJSONArray("result");

                donarsList = new ArrayList<>();

                //StringBuffer productStringBuffer = new StringBuffer();

                for (int i = 0; i < parentArray.length(); i++) {

                    finalObject = parentArray.getJSONObject(i);

                    donars = new Donars();

                    donars.setImage(finalObject.getString("image"));
                    donars.setName(finalObject.getString("name"));
                    donars.setPhone(finalObject.getString("phone"));
                    String blood = check_blood(finalObject.getString("blood_group"));
                    donars.setBlood_group(blood);
                    donars.setUniversity(finalObject.getString("university_name"));


                    //Adding the final object to an arrylist
                    donarsList.add(donars);

                }
                return donarsList;


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }

                try {

                    if (reader != null) {

                        reader.close();

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            return null;

        }

        @Override
        protected void onPostExecute(List<Donars> result) {
            super.onPostExecute(result);
            //Here i can set the all products to listview

            DonarsAdapter adapter = new DonarsAdapter(getApplicationContext(), R.layout.user_list_item, result);
            // new DonarsAdapter()
            donarListView.setAdapter(adapter);

            donarListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    //Toast.makeText(getApplicationContext(), "Clicked " + view.getVerticalScrollbarPosition(), Toast.LENGTH_LONG).show();
                    //donarsList.get(position).getPhone();
                    dialPhone(donarsList.get(position).getPhone());

                }
            });


        }

    }

    private String check_blood(String blood_group) {

        if(blood_group.equals("A_POS")){
            blood_group = "A+";
        }else if(blood_group.equals("A_NEG")){
            blood_group="A-";
        }else if(blood_group.equals("B_POS")){
            blood_group="B+";
        }else if(blood_group.equals("B_NEG")){
            blood_group="B-";
        }else if(blood_group.equals("O_POS")){
            blood_group="O+";
        }else if(blood_group.equals("O_NEG")){
            blood_group="O-";
        }else if(blood_group.equals("AB_POS")){
            blood_group="AB+";
        }else if(blood_group.equals("AB_NEG")){
            blood_group="AB-";
        }

        return blood_group;
    }
    private void dialPhone(String phnNumber) {
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phnNumber)));
    }

}
