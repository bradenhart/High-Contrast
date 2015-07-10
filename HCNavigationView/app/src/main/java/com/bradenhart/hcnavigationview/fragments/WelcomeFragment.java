package com.bradenhart.hcnavigationview.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bradenhart.hcnavigationview.R;
import static com.bradenhart.hcnavigationview.Constants.*;

/**
 * Created by bradenhart on 29/06/15.
 */
public class WelcomeFragment extends Fragment implements View.OnClickListener {

    private Context context;
    private EditText nameInput;
    private Button cameraBtn, galleryBtn, skipBtn, nextBtn;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor spEdit;
    private String userName;

    public WelcomeFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_welcome, container, false);

        context = getActivity();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        spEdit = sharedPreferences.edit();

        nameInput = (EditText) view.findViewById(R.id.input_name);
        nameInput.setImeOptions(EditorInfo.IME_ACTION_DONE);
        nameInput.setInputType(EditorInfo.TYPE_TEXT_VARIATION_PERSON_NAME);

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(KEY_USER_NAME)) {
                userName = savedInstanceState.getString(KEY_USER_NAME);
                nameInput.setText(userName);
            }
        }

        nameInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                userName = nameInput.getText().toString();
                spEdit.putString(KEY_USER_NAME, userName);
                if (sharedPreferences.getString(KEY_USER_NAME, defaultName).equals(userName)) {
                    Toast.makeText(context, userName, Toast.LENGTH_SHORT).show();
                }
            }
        });

        cameraBtn = (Button) view.findViewById(R.id.welcome_select_camera);
        galleryBtn = (Button) view.findViewById(R.id.welcome_select_gallery);

        cameraBtn.setOnClickListener(this);
        galleryBtn.setOnClickListener(this);

        skipBtn = (Button) view.findViewById(R.id.welcome_skip);
        nextBtn = (Button) view.findViewById(R.id.welcome_next);

        skipBtn.setOnClickListener(this);
        nextBtn.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {

        int id = view.getId();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        Fragment fragment = null;

        switch (id) {
            case R.id.welcome_select_camera:

                break;
            case R.id.welcome_select_gallery:

                break;
            case R.id.welcome_skip:
                spEdit.putString(KEY_USER_NAME, defaultName).apply();
                fragment = new SocialFragment();
                fragmentManager.beginTransaction().replace(R.id.welcome_container, fragment).commit();
                break;
            case R.id.welcome_next:
                userName = nameInput.getText().toString();
                spEdit.putString(KEY_USER_NAME, userName).apply();
                fragment = new SocialFragment();
                fragmentManager.beginTransaction().replace(R.id.welcome_container, fragment).commit();
                break;
            default:
                break;
        }
    }

    private void openGallery() {
        
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // save whatever the user has typed in the name field.
        // save their photo.
        // will reload them on orientation change.
        spEdit.putString(KEY_USER_NAME, nameInput.getText().toString()).apply();
    }
}
