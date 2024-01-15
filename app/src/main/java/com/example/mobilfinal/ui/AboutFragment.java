package com.example.mobilfinal.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mobilfinal.R;

import java.net.URL;

public class AboutFragment extends Fragment {
    Button btnGithub, btnLinkedin, btnYoutube;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_about, container, false);

        btnGithub = root.findViewById(R.id.btn_github);
        btnLinkedin = root.findViewById(R.id.btn_linkedin);
        btnYoutube = root.findViewById(R.id.btn_youtube);

        btnGithub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://github.com/berkayozbinar"));
                startActivity(intent);
            }
        });
        btnLinkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.linkedin.com/in/berkayozbinar"));

                startActivity(intent);
            }
        });
        btnYoutube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.youtube.com/@BerkayOzbinar"));

                startActivity(intent);
            }
        });
        return root;
    }
}