package com.example.viikko11;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawer;
    Home_Fragment fragment;
    FragmentManager manager;
    NavigationView navView;
    Menu menu;
    EditText username;
    EditText font;
    EditText height;
    EditText width;
    EditText rows;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment = new Home_Fragment();
        manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.fragment_container,fragment).commit();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        navView = findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(this);
        menu = navView.getMenu();
        navView.setCheckedItem(R.id.enabled);

        //Luodaan Asetukset valikolle avausnappula
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        username = (EditText) menu.findItem(R.id.username).getActionView().findViewById(R.id.editUsername);
        font = (EditText) menu.findItem(R.id.fontSize).getActionView().findViewById(R.id.editTextView);
        height = (EditText) menu.findItem(R.id.height).getActionView().findViewById(R.id.editTextView);
        width = (EditText) menu.findItem(R.id.width).getActionView().findViewById(R.id.editTextView);
        rows = (EditText) menu.findItem(R.id.rows).getActionView().findViewById(R.id.editTextView);

    }

    //Suoritetaan toiminto valikosta painetulle asetukselle
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.username:
                fragment.changeUsername(username.getText().toString());
                break;
            case R.id.enabled:
                fragment.enable();
                break;
            case R.id.disabled:
                fragment.disable();
                break;
            case R.id.fontSize:
                fragment.changeFont(font.getText().toString());
                break;
            case R.id.height:
                fragment.changeHeight(height.getText().toString());
                break;
            case R.id.width:
                fragment.changeWidth(width.getText().toString());
                break;
            case R.id.rows:
                fragment.changeRows(rows.getText().toString());
                break;
            case R.id.Finnish:
                changeLanguage("fi");
                break;
            case R.id.English:
                changeLanguage("en");
                break;

        }
        return true;
    }

    //Vaihtaa lokaalin kielen haluttuun ja käynnistää main activityn uudestaan.
    public void changeLanguage(String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Resources resources = MainActivity.this.getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        resources.updateConfiguration(config, resources.getDisplayMetrics());
        finish();
        startActivity(getIntent());
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}