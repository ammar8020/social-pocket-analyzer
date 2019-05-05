package com.ammar.socialpocketa;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.ammar.socialpocketa.data.SharedPrefManager;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemSelectedListener {


    private static final String TAG = "MainActivity";

//    Intent mServiceIntent;
//    private MentionService mSensorService;
//
//    Context ctx;
//
//
//    public Context getCtx() {
//        return ctx;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        ctx = this;

        setContentView(R.layout.activity_main);


//        mSensorService = new MentionService(getCtx());
//        mServiceIntent = new Intent(getCtx(), mSensorService.getClass());
//        if (!isMyServiceRunning(mSensorService.getClass())) {
//            startService(mServiceIntent);
//        }


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //add this line to display Profile when the activity is loaded
        //displaySelectedScreen(R.id.nav_profile);


        HomeFragment postFragment = new HomeFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .add(R.id.post_container, postFragment)
                .commit();


        //loading the default fragment
        //loadFragment(new HomeFragment());

        //getting bottom navigation view and attaching the listener
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(MainActivity.this);



        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                Log.d(TAG, "onClick: opening dialog.");

                MyCustomDialog dialog = new MyCustomDialog();
                dialog.show(getFragmentManager(), "MyCustomDialog");

            }
        });



//        mSensorService = new MentionService(getCtx());
//        mServiceIntent = new Intent(getCtx(), mSensorService.getClass());
//        if (!isMyServiceRunning(mSensorService.getClass())) {
//            startService(mServiceIntent);
//        }


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

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
////        if (id == R.id.action_settings) {
////            return true;
////        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // Handle navigation view item clicks here.
        //calling the method displayselectedscreen and passing the id of selected menu
        displaySelectedScreen(item.getItemId());

        return true;
    }


    private void displaySelectedScreen(int itemId) {

        //creating fragment object
        Fragment fragment = null;

        //initializing the fragment object which is selected
        switch (itemId) {
            case R.id.nav_profile:
                fragment = new ProfileFragment();
                break;
            case R.id.nav_latest_replies:
                fragment = new LatestRepliesFragment();
                break;

            case R.id.nav_engage:
                fragment = new EngageFragment();
                break;
            case R.id.nav_replies_visualization:
                fragment = new RepliesVisualizationFragment();
                break;


            case R.id.nav_settings:
                fragment = new SettingFragment();
                break;


            case R.id.nav_logout:
                SharedPrefManager sharedPrefManager = new SharedPrefManager(getApplicationContext());
                sharedPrefManager.logout();


//
//                MentionService mentionService = new MentionService();
//                mentionService.stopServiceOnLogout();



                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;


            case R.id.navigation_home:
                fragment = new HomeFragment();
                break;

            case R.id.navigation_search:
//                fragment = new SearchFragment();
                fragment = new HashtagFragment();
                break;

            case R.id.navigation_notifications:
                fragment = new MentionsVisualizationFragment();
                break;

            case R.id.navigation_mentions:
                fragment = new MentionsFragment();
                break;
        }

        //replacing the fragment
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.post_container, fragment);
            ft.commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);


    }

    // Loading the selected Bottom Navigation Fragment
    /*private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }*/



//    private boolean isMyServiceRunning(Class<?> serviceClass) {
//        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
//        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
//            if (serviceClass.getName().equals(service.service.getClassName())) {
//                Log.i ("isMyServiceRunning?", true+"");
//                return true;
//            }
//        }
//        Log.i ("isMyServiceRunning?", false+"");
//        return false;
//    }
//
//
//    @Override
//    protected void onDestroy() {
//        stopService(mServiceIntent);
//        Log.i("MAINACT", "onDestroy!");
//        super.onDestroy();
//
//    }




}
