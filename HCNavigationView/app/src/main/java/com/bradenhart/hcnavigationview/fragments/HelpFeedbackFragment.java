package com.bradenhart.hcnavigationview.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bradenhart.hcnavigationview.R;
import static com.bradenhart.hcnavigationview.Constants.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by bradenhart on 25/06/15.
 */
public class HelpFeedbackFragment extends Fragment implements  View.OnClickListener {

    private final String LOGTAG = "HelpFeedbackActivity";
    private Context context;
    private CircleImageView dp, dp2;
    private ImageView button;
    private final int GALLERY = 1;
    private static Bitmap Image = null;
    private static Bitmap rotateImage = null;
    private TextView imagePath;
    private Uri mImageUri;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor spEdit;

    public HelpFeedbackFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_help_feedback, container, false);

        context = getActivity();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        spEdit = sharedPreferences.edit();


        dp = (CircleImageView) view.findViewById(R.id.test_dp);
        button = (ImageView) view.findViewById(R.id.test_gallery_button);
        button.setOnClickListener(this);

        imagePath = (TextView) view.findViewById(R.id.test_dp_path);
        dp2 = (CircleImageView) view.findViewById(R.id.second_image);
        dp2.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {

        int id = view.getId();

        switch (id) {
            case R.id.test_gallery_button:
                dp.setImageBitmap(null);
                if (Image != null)
                    Image.recycle();
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), GALLERY);
                break;
            case R.id.second_image:
                setStoredImageAsDp();
                break;
            default:
                break;
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY && resultCode != 0) {
            mImageUri = data.getData();
            //imagePath.setText(mImageUri.getPath());
            imagePath.setText(mImageUri.toString());
            try {
                Image = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), mImageUri);
                if (getOrientation(getActivity(), mImageUri) != 0) {
                    Matrix matrix = new Matrix();
                    matrix.postRotate(getOrientation(getActivity(), mImageUri));
                    if (rotateImage != null)
                        rotateImage.recycle();
                    rotateImage = Bitmap.createBitmap(Image, 0, 0, Image.getWidth(), Image.getHeight(), matrix, true);
                    saveImage(rotateImage, mImageUri);
                    //dp.setImageBitmap(rotateImage);
                } else
                    saveImage(Image, mImageUri);
                    //dp.setImageBitmap(Image);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static int getOrientation(Context context, Uri photoUri) {
        Cursor cursor = context.getContentResolver().query(photoUri,
                new String[] { MediaStore.Images.ImageColumns.ORIENTATION },null, null, null);

        if (cursor.getCount() != 1) {
            return -1;
        }
        cursor.moveToFirst();
        int result = cursor.getInt(0);
        cursor.close();
        return result;
    }

    private void saveImage(Bitmap image, Uri imageUri) {
        dp.setImageBitmap(image);
        spEdit.putString(KEY_PROFILE_PIC, imageUri.toString()).apply();
    }

    private void setStoredImageAsDp() {
        String path = sharedPreferences.getString(KEY_PROFILE_PIC, null);
        dp2.setImageURI(Uri.parse(path));
    }

}