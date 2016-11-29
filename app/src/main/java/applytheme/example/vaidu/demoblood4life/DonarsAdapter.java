package applytheme.example.vaidu.demoblood4life;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Guitorio on 11/11/2016.
 */
public class DonarsAdapter extends ArrayAdapter<Donars> {


    private List<Donars> donarsList;
    private int resourse;


    private LayoutInflater inflater;

    public DonarsAdapter(Context context, int resource, List<Donars> objects) {
        super(context, resource, objects);
        donarsList = objects;
        this.resourse = resource;

        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){

            convertView = inflater.inflate(resourse,null);

        }

        //Initialize all components of row layout

        ImageView donarImage;
        TextView donarName;
        TextView donarBloodGroup;
        TextView donarPhone;
        TextView donarUniversity;
        final ProgressBar progressBar;

        donarImage = (ImageView)convertView.findViewById(R.id.donarImage);
        donarName = (TextView)convertView.findViewById(R.id.txtName);
        donarBloodGroup = (TextView)convertView.findViewById(R.id.txtBloodGroup);
        donarUniversity = (TextView)convertView.findViewById(R.id.txtUniversity);
        donarPhone = (TextView)convertView.findViewById(R.id.txtPhone);
        progressBar = (ProgressBar)convertView.findViewById(R.id.progressBar);

        //For load image before adding progressbar
        //ImageLoader.getInstance().displayImage(productsList.get(position).getImage(),productImage);

        //Adding prograessbar
        //For load image
        ImageLoader.getInstance().displayImage(donarsList.get(position).getImage(), donarImage, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                progressBar.setVisibility(view.VISIBLE);
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                progressBar.setVisibility(view.GONE);
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                progressBar.setVisibility(view.GONE);
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {
                progressBar.setVisibility(view.GONE);
            }
        });
        //Just for load image
        donarName.setText(donarsList.get(position).getName());
        donarBloodGroup.setText(donarsList.get(position).getBlood_group());
        donarPhone.setText(donarsList.get(position).getPhone());
        donarUniversity.setText(donarsList.get(position).getUniversity());


        return convertView;
    }




}
