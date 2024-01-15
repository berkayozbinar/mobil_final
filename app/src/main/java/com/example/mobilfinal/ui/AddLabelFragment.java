package com.example.mobilfinal.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.mobilfinal.R;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddLabelFragment extends Fragment {
    EditText etLabel, etDescription;
    Button btnAdd;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_addlabel, container, false);

        etLabel = root.findViewById(R.id.et_label);
        etDescription = root.findViewById(R.id.et_description);
        btnAdd = root.findViewById(R.id.btn_add);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String label = etLabel.getText().toString();
                String description = etDescription.getText().toString();

                if (label.isEmpty() || description.isEmpty())  {
                    Toast.makeText(getActivity().getApplicationContext(), "Lütfen herhangi bir alanı boş bırakmayınız.", Toast.LENGTH_SHORT).show();
                }

                FirebaseFirestore db = FirebaseFirestore.getInstance();
                CollectionReference ref = db.collection("Label");

                Map<String, Object> labelmap = new HashMap<>();
                labelmap.put("label", label);
                labelmap.put("description", description);

                ref.add(labelmap);

                LinearLayout lL = root.findViewById(R.id.l_layoutlabel);
                View view = getLayoutInflater().inflate(R.layout.label, null);
                TextView tvLabel = view.findViewById(R.id.tv_addedlabel);
                tvLabel.setText(label);
                lL.addView(view);
            }
        });
        return root;
    }
}