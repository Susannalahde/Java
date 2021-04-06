package com.example.viikko11;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Home_Fragment extends Fragment {

    private TextView read;
    private EditText write;
    private TextView user;
    private ViewGroup.LayoutParams parameters;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        user = view.findViewById(R.id.user);
        read = view.findViewById(R.id.textView);
        write = view.findViewById(R.id.inputText);
        parameters = read.getLayoutParams();
        super.onViewCreated(view, savedInstanceState);
    }

    public void enable(){
        write.setEnabled(true);
        write.setHint("Write here!");
    }

    public void disable(){
        write.setEnabled(false);
        String text = write.getText().toString();
        read.setText(text);
        write.setText("");
        write.setHint("Disabled");
    }

    public void changeUsername(String username){
        user.setText(username);
    }

    public void changeFont(String fontsize){
        read.setTextSize(Integer.parseInt(fontsize));
    }

    public void changeHeight(String height){
        parameters.height = Integer.parseInt(height);
        read.setLayoutParams(parameters);
    }
    public void changeWidth(String width){
        parameters.width = Integer.parseInt(width);
        read.setLayoutParams(parameters);
    }
    public void changeRows(String amount){
        read.setLines(Integer.parseInt(amount));
    }
}
