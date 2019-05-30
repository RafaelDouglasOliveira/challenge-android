package com.br.testeame.homescreen;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.br.testeame.R;
import com.br.testeame.aboutscreen.fragment.AboutApplicationFragment;
import com.br.testeame.homescreen.fragment.HomeFragment;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FrameLayout contenerLayout;
    HomeFragment homeFragment;
    AboutApplicationFragment aboutApplicationFragment;
    Toolbar toolbar;
    ImageView imgLogoToolBar;
    TextView titleToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        contenerLayout = findViewById(R.id.contener_layout);
        initFragment();
        initToolBar();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void initFragment() {

        homeFragment = new HomeFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.contener_layout,homeFragment).commit();
    }

    private void setAboutApplicationFragment() {

        aboutApplicationFragment = new AboutApplicationFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.contener_layout,aboutApplicationFragment).commit();
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {

           initFragment();
            setTitleToolBar(getString(R.string.nav_header_title),true);


        } else if (id == R.id.menu_about) {

            setAboutApplicationFragment();
            setTitleToolBar("sobre",false);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setTitleToolBar(String title , boolean imgLogo) {

        if(!imgLogo) {
            imgLogoToolBar.setVisibility(View.GONE);
        } else {
            imgLogoToolBar.setVisibility(View.VISIBLE);
        }

        titleToolBar.setText(title);
    }

    private void initToolBar() {
        if(toolbar != null) {
            imgLogoToolBar = toolbar.findViewById(R.id.img_logo);
            titleToolBar = toolbar.findViewById(R.id.title_customer);
        }
    }


}
