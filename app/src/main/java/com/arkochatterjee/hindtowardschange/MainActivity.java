package com.arkochatterjee.hindtowardschange;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;
    FragmentTransaction fragmentTransaction;
    NavigationView navigationView;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FontsOverride.setDefaultFont(this, "MONOSPACE", "avenir.otf");

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        View headerView = navigationView.getHeaderView(0);

      //  TextView navUsername = (TextView) headerView.findViewById(R.id.aa17);
       // Typeface typeface1 = Typeface.createFromAsset(getAssets(), "font/M_R.ttf");

       // navUsername.setTypeface(typeface1);

        // getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       /* getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.navlogo);
*/



        toolbar.setNavigationIcon(null);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.main_container,new home_fragment());
        fragmentTransaction.commit();
        getSupportActionBar().setTitle("Home");
        // ((TextView) findViewById(R.id.main_toolbar_title)).setText("HOME");
        navigationView =(NavigationView)findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home_id:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container,new home_fragment());
                        fragmentTransaction.commit();
                        // ((TextView) findViewById(R.id.main_toolbar_title)).setText("HOME");
                        getSupportActionBar().setTitle("Home");
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;

                    case R.id.events_id:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container, new Events());
                        fragmentTransaction.commit();
                        // ((TextView) findViewById(R.id.main_toolbar_title)).setText("HOME");
                        getSupportActionBar().setTitle("Events");
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;


                    case R.id.about_id:
                       fragmentTransaction = getSupportFragmentManager().beginTransaction();
                       fragmentTransaction.replace(R.id.main_container,new AboutUs());
                       fragmentTransaction.commit();



                        // ((TextView) findViewById(R.id.main_toolbar_title)).setText("DOMAINS");
                        getSupportActionBar().setTitle("About Us");
                       //  Intent intent = new Intent(MainActivity.this, Login.class);
                        //startActivity(intent);
                        //item.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;

                    case R.id.contactus_id:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container, new ContactUs());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Contact Us");
                        //  ((TextView) findViewById(R.id.main_toolbar_title)).setText("WORKSHOPS");
                        //getSupportActionBar().setTitle("W");
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;

                    case R.id.credits_id:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container,new Credits_Fragment());
                        fragmentTransaction.commit();

                        getSupportActionBar().setTitle("Credits");
                        // ((TextView) findViewById(R.id.main_toolbar_title)).setText("SPONSERS");
                        //getSupportActionBar().setTitle("SPONSERS");
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;

                    case R.id.member_id:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container,new Member_Fragment());
                        fragmentTransaction.commit();
                        // ((TextView) findViewById(R.id.main_toolbar_title)).setText("SPONSERS");
                        getSupportActionBar().setTitle("Members");
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;

                    case R.id.logout_id:
                        FirebaseAuth.getInstance().signOut();
                        Intent startintent = new Intent(MainActivity.this, Login.class);
                        startActivity(startintent);
                        //getActivity().finish();
                        break;



                }



                return true;
            }
        });
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }
}
