package com.example.vrs.starwar;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vrs.starwar.adapter.ListDetailsAdapter;
import com.example.vrs.starwar.bean.Film;
import com.example.vrs.starwar.bean.People;
import com.example.vrs.starwar.bean.Planet;
import com.example.vrs.starwar.bean.Specie;
import com.example.vrs.starwar.bean.Starship;
import com.example.vrs.starwar.bean.Vehicle;
import com.example.vrs.starwar.parent.BaseActivity;
import com.example.vrs.starwar.service.StarwarDataServiceImpl;
import com.example.vrs.starwar.util.APPConstant;

import java.util.ArrayList;

public class LauncherDetailsActivity extends BaseActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private FloatingActionButton floatingActionButton;
    private RecyclerView recyclerView;

    private ListDetailsAdapter adapter;
    private String message;
    private People people;
    private MenuItem itemFilm;
    private MenuItem itemSpecies;
    private MenuItem itemVehicle;
    private MenuItem itemStarship;
    private MenuItem itemPeople;
    private MenuItem itemPlanet;
    private Starship starShip;
    private Film films;
    private Planet planet;
    private Vehicle vehicles;
    private Specie species;
    private LinearLayout btnContainer;
    private boolean isClick = true;
    private Button btnPlanet;
    private Button btnFilm;
    private Button btnVehicle;
    private Button btnStarship;
    private Button btnPeople;
    private Button btnSpecies;
    private Button btnInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        APPConstant.URL = "http://swapi.co/api/people/1/";
        if (checkNetworkStatus(this))
            new GetServerResponse().execute();
        else {
            showAlert("No Network Available", true, APPConstant.MSG_ALERT);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        itemFilm = menu.findItem(R.id.action_film);
        itemSpecies = menu.findItem(R.id.action_species);
        itemVehicle = menu.findItem(R.id.action_vehicles);
        itemStarship = menu.findItem(R.id.action_starship);
        itemPeople = menu.findItem(R.id.action_people);
        itemPlanet = menu.findItem(R.id.action_planet);
        if (APPConstant.URL.contains("/people/")) {
            itemPeople.setVisible(false);
            itemPlanet.setVisible(false);
            btnPeople.setVisibility(View.GONE);
            btnPlanet.setVisibility(View.GONE);
            btnSpecies.setVisibility(View.VISIBLE);
            btnStarship.setVisibility(View.VISIBLE);
            btnFilm.setVisibility(View.VISIBLE);
            btnVehicle.setVisibility(View.VISIBLE);
        } else if (APPConstant.URL.contains("/films/")) {
            itemPeople.setVisible(false);
            itemFilm.setVisible(false);
            btnPeople.setVisibility(View.GONE);
            btnFilm.setVisibility(View.GONE);
            btnSpecies.setVisibility(View.VISIBLE);
            btnStarship.setVisibility(View.VISIBLE);
            btnPlanet.setVisibility(View.VISIBLE);
            btnVehicle.setVisibility(View.VISIBLE);

        } else if (APPConstant.URL.contains("/species/") || APPConstant.URL.contains("/starships/") || APPConstant.URL.contains("/vehicles/") || APPConstant.URL.contains("/planets/")) {
            itemSpecies.setVisible(false);
            itemStarship.setVisible(false);
            itemPlanet.setVisible(false);
            itemVehicle.setVisible(false);
            btnSpecies.setVisibility(View.GONE);
            btnStarship.setVisibility(View.GONE);
            btnPlanet.setVisibility(View.GONE);
            btnVehicle.setVisibility(View.GONE);
            btnPeople.setVisibility(View.VISIBLE);
            btnFilm.setVisibility(View.VISIBLE);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        setButtonContainerViisbility();
        if (id == R.id.action_film) {
            performFilmMenuItemEvent();
        } else if (id == R.id.action_people) {
            performPeopleMenuItemEvent();
        } else if (id == R.id.action_species) {
            performSpeciesMenuItemEvent();
        } else if (id == R.id.action_planet) {
            performPlanetMenuItemEvent();
        } else if (id == R.id.action_starship) {
            performStarshipMenuItemEvent();
        } else if (id == R.id.action_vehicles) {
            performVehicleMenuItemEvent();
        }

        return super.onOptionsItemSelected(item);
    }

    private void performVehicleMenuItemEvent() {
        if (APPConstant.URL.contains("/people/")) {
            setRecyclerViewData(people.getVehicles());
        } else if (APPConstant.URL.contains("/films/")) {
            setRecyclerViewData(films.getVehicles());
        } else
            Toast.makeText(getApplicationContext(), "No data Available", Toast.LENGTH_LONG).show();

    }

    private void performStarshipMenuItemEvent() {

        if (APPConstant.URL.contains("/people/")) {
            setRecyclerViewData(people.getStarships());
        } else if (APPConstant.URL.contains("/films/")) {
            setRecyclerViewData(films.getStarships());

        } else
            Toast.makeText(getApplicationContext(), "No data Available", Toast.LENGTH_LONG).show();
    }

    private void performPlanetMenuItemEvent() {

        if (APPConstant.URL.contains("/films/")) {
            setRecyclerViewData(films.getPlanets());
        } else
            Toast.makeText(getApplicationContext(), "No data Available", Toast.LENGTH_LONG).show();
    }

    private void performSpeciesMenuItemEvent() {
        if (APPConstant.URL.contains("/people/")) {
            setRecyclerViewData(people.getSpecies());

        } else if (APPConstant.URL.contains("/films/")) {
            setRecyclerViewData(films.getSpecies());
        } else
            Toast.makeText(getApplicationContext(), "No data Available", Toast.LENGTH_LONG).show();


    }

    private void performPeopleMenuItemEvent() {
        if (APPConstant.URL.contains("/films/")) {
            setRecyclerViewData(films.getCharacters());

        } else if (APPConstant.URL.contains("/species/")) {
            setRecyclerViewData(species.getPeople());
        } else if (APPConstant.URL.contains("/planets/")) {
            setRecyclerViewData(planet.getResidents());
        } else if (APPConstant.URL.contains("/vehicles/")) {
            setRecyclerViewData(vehicles.getPilots());
        } else if (APPConstant.URL.contains("/starships/")) {
            setRecyclerViewData(starShip.getPilots());
        } else {
            Toast.makeText(getApplicationContext(), "No data Available", Toast.LENGTH_LONG).show();

        }
    }

    private void performFilmMenuItemEvent() {
        if (APPConstant.URL.contains("/people/")) {
            setRecyclerViewData(people.getFilms());
        } else if (APPConstant.URL.contains("/starships/")) {
            setRecyclerViewData(starShip.getFilms());

        } else if (APPConstant.URL.contains("/films/")) {
            Toast.makeText(getApplicationContext(), "No data Available", Toast.LENGTH_LONG).show();
        } else if (APPConstant.URL.contains("/planets/")) {
            setRecyclerViewData(planet.getFilms());

        } else if (APPConstant.URL.contains("/vehicles/")) {
            setRecyclerViewData(vehicles.getFilms());
//                adapter = new ListDetailsAdapter(mActivity, vehicles.getFilms());
//                recyclerView.setAdapter(adapter);
        } else if (APPConstant.URL.contains("/species/")) {
            setRecyclerViewData(species.getFilms());
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_info:
//                Snackbar.make(view, "You clicked on the View", Snackbar.LENGTH_SHORT).show();
                if(APPConstant.URL.contains("/planets/"))
                mActivity.showPlanetDialog(planet);
                else if(APPConstant.URL.contains("/people/"))
                mActivity.showActorDialog(people);
                else if(APPConstant.URL.contains("/vehicles/"))
                mActivity.showVehicleDialog(vehicles);
                else if(APPConstant.URL.contains("/films/"))
                mActivity.showFilmsDialog(films);
                else if(APPConstant.URL.contains("/starships/"))
                mActivity.showStarshipsDialog(starShip);
                else if (APPConstant.URL.contains("/species/"))
                    mActivity.showSpeciesDialog(species);

                break;
            case R.id.btn_films:
                performFilmMenuItemEvent();
                break;
            case R.id.btn_vehicle:
                performVehicleMenuItemEvent();
                break;
            case R.id.btn_starship:
                performStarshipMenuItemEvent();
                break;
            case R.id.btn_people:
                performPeopleMenuItemEvent();
                break;
            case R.id.btn_species:
                performSpeciesMenuItemEvent();
                break;
            case R.id.btn_planet:
                performPlanetMenuItemEvent();
                break;
        }
        setButtonContainerViisbility();
    }

    private void setButtonContainerViisbility() {
        btnContainer.setVisibility(View.GONE);
        isClick = true;
    }

    private class GetServerResponse extends AsyncTask<Void, String, Void> {
        @Override
        public void onPreExecute() {
            super.onPreExecute();
            mActivity.showProgress(mActivity,
                    "Fetching Details , Please Wait...");
        }

        @Override
        protected Void doInBackground(Void... params) {


            try {

                StarwarDataServiceImpl dataServiceImpl = new StarwarDataServiceImpl();
                if (APPConstant.URL.contains("/people/"))
                    people = dataServiceImpl.getPeopleDetails(APPConstant.URL, mActivity);
                else if (APPConstant.URL.contains("/starships/"))
                    starShip = dataServiceImpl.getStarshipDetails(APPConstant.URL, mActivity);
                else if (APPConstant.URL.contains("/films/"))
                    films = dataServiceImpl.getFilmsDetails(APPConstant.URL, mActivity);
                else if (APPConstant.URL.contains("/planets/"))
                    planet = dataServiceImpl.getPlanetDetails(APPConstant.URL, mActivity);
                else if (APPConstant.URL.contains("/vehicles/"))
                    vehicles = dataServiceImpl.getVehicleDetails(APPConstant.URL, mActivity);
                else if (APPConstant.URL.contains("/species/"))
                    species = dataServiceImpl.getSpeciesDetails(APPConstant.URL, mActivity);


            } catch (Exception e) {

                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            dismissProgress();
            invalidateOptionsMenu();
            try {
                if (APPConstant.URL.contains("/people/")) {
//                    if (people != null) {
                    init(people.getFilms());
//                    } else {
//                        showAlert(APPConstant.MSG_ERROR_OCCURED, APPConstant.MSG_ALERT);
//                    }
                } else if (APPConstant.URL.contains("/starships/")) {
                    init(starShip.getFilms());

                } else if (APPConstant.URL.contains("/films/")) {
                    init(films.getCharacters());

                } else if (APPConstant.URL.contains("/planets/")) {
                    init(planet.getFilms());

                } else if (APPConstant.URL.contains("/vehicles/")) {

                    init(vehicles.getFilms());

                } else if (APPConstant.URL.contains("/species/")) {

                    init(species.getFilms());

                }
            } catch (Exception e) {
                showAlert(APPConstant.MSG_ERROR_OCCURED, true, APPConstant.MSG_ALERT);

            }
        }

    }

    @Override
    public void onBackPressed() {
        showBackPressAlert("Do you want the Application.","Alert");

    }

    public void showAlert(final String message, final boolean isActivityFinish, String title) {

        final Dialog dialog = new Dialog(this, R.style.CustomDialog);
        dialog.setContentView(R.layout.custom_alert);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        // set the custom dialog components - text, image and button
        TextView title1 = (TextView) dialog.findViewById(R.id.title);
        TextView text = (TextView) dialog.findViewById(R.id.tv_msg);
        ImageButton cancel = (ImageButton) dialog.findViewById(R.id.cancel);
        Button btnOk = (Button) dialog.findViewById(R.id.btn_ok);
        Button btnCancel = (Button) dialog.findViewById(R.id.btn_cancel);
        btnOk.setText(APPConstant.OK_BUTTON);
        btnCancel.setText(APPConstant.CANCEL_BUTTON);
        btnCancel.setVisibility(View.GONE);
        title1.setText(title);
        text.setText(message);
        Typeface face = Typeface.createFromAsset(this.getAssets(),
                "font/RobotoCondensed-Bold.ttf");
        title1.setTypeface(face);
        btnOk.setTypeface(face);
        text.setTypeface(face);
        // if button is clicked, close the custom dialog
        cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.cancel();
                if (isActivityFinish)
                    finish();

            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        dialog.show();

    }
    public void showBackPressAlert(final String message, String title) {

        final Dialog dialog = new Dialog(this, R.style.CustomDialog);
        dialog.setContentView(R.layout.custom_alert);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        // set the custom dialog components - text, image and button
        TextView title1 = (TextView) dialog.findViewById(R.id.title);
        TextView text = (TextView) dialog.findViewById(R.id.tv_msg);
        ImageButton cancel = (ImageButton) dialog.findViewById(R.id.cancel);
        Button btnOk = (Button) dialog.findViewById(R.id.btn_ok);
        Button btnCancel = (Button) dialog.findViewById(R.id.btn_cancel);
        LinearLayout llParent = (LinearLayout) dialog.findViewById(R.id.btn_container);
        llParent.setVisibility(View.VISIBLE);
        btnOk.setText(APPConstant.OK_BUTTON);
        btnCancel.setText(APPConstant.CANCEL_BUTTON);
        btnCancel.setVisibility(View.VISIBLE);
        title1.setText(title);
        text.setText(message);
        Typeface face = Typeface.createFromAsset(this.getAssets(),
                "font/RobotoCondensed-Bold.ttf");
        title1.setTypeface(face);
        btnOk.setTypeface(face);
        text.setTypeface(face);
        // if button is clicked, close the custom dialog
        btnCancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.cancel();

            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.cancel();

            }
        });
        btnOk.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.cancel();
                finish();
            }
        });

        dialog.show();

    }
    private void init(ArrayList<String> films) {
        initView();
        setSupportActionBar(toolbar);
        initRecycleView();
        setTitle();
        initListener();
        adapter = new ListDetailsAdapter(mActivity, films);
        recyclerView.setAdapter(adapter);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//              Snackbar.make(v, "You clicked on the fab", Snackbar.LENGTH_SHORT).show();
                if (isClick) {
                    btnContainer.setVisibility(View.VISIBLE);
                    btnContainer.startAnimation(inFromRightAnimation());
                    isClick = false;
                } else {

                    setButtonContainerViisbility();
                }
            }
        });
    }

    private boolean checkNetworkStatus(Activity activity) {
        boolean isNetwork = false;
        try {

            ConnectivityManager cm = (ConnectivityManager) activity
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getNetworkInfo(0);
            if (netInfo != null
                    && netInfo.getState() == NetworkInfo.State.CONNECTED) {
                isNetwork = true;
            } else {
                netInfo = cm.getNetworkInfo(1);
                if (netInfo != null
                        && netInfo.getState() == NetworkInfo.State.CONNECTED)
                    isNetwork = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isNetwork;
    }

    private void initListener() {
        btnFilm.setOnClickListener(this);
        btnVehicle.setOnClickListener(this);
        btnStarship.setOnClickListener(this);
        btnPeople.setOnClickListener(this);
        btnSpecies.setOnClickListener(this);
        btnPlanet.setOnClickListener(this);
        btnInfo.setOnClickListener(this);
    }

    public void callasync(String url) {
        setButtonContainerViisbility();
        if (checkNetworkStatus(this)){
        APPConstant.URL = url;
            new GetServerResponse().execute();}
        else {
            showAlert("No Network Available", false, APPConstant.MSG_ALERT);
        }


    }

    private void initRecycleView() {
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setHasFixedSize(true);
    }

    private void initView() {
        btnInfo = (Button) findViewById(R.id.btn_info);
        btnFilm = (Button) findViewById(R.id.btn_films);
        btnVehicle = (Button) findViewById(R.id.btn_vehicle);
        btnStarship = (Button) findViewById(R.id.btn_starship);
        btnPeople = (Button) findViewById(R.id.btn_people);
        btnSpecies = (Button) findViewById(R.id.btn_species);
        btnPlanet = (Button) findViewById(R.id.btn_planet);
        btnContainer = (LinearLayout) findViewById(R.id.button_container);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

    }

    private void setTitle() {
        if (APPConstant.URL.contains("/people/")) {
            collapsingToolbarLayout.setTitle("Actor: " + people.getName());

        } else if (APPConstant.URL.contains("/starships/")) {
            collapsingToolbarLayout.setTitle("Starship: " + starShip.getName());
        } else if (APPConstant.URL.contains("/films/")) {
            collapsingToolbarLayout.setTitle("Film: " + films.getTitle());
        } else if (APPConstant.URL.contains("/planets/")) {
            collapsingToolbarLayout.setTitle("Planet: " + planet.getName());

        } else if (APPConstant.URL.contains("/vehicles/")) {
            collapsingToolbarLayout.setTitle("Vehicle: " + vehicles.getName());

        } else if (APPConstant.URL.contains("/species/")) {
            collapsingToolbarLayout.setTitle("Specie: " + species.getName());


        }
    }

    private void setRecyclerViewData(ArrayList<String> list) {
        if (list.size() > 0) {
            adapter = new ListDetailsAdapter(mActivity, list);
            recyclerView.setAdapter(adapter);
        } else {
            showAlert("No Data Available", false, APPConstant.MSG_ALERT);
        }
    }

    private Animation inFromRightAnimation() {

        Animation inFromRight = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, +1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        inFromRight.setDuration(500);
        inFromRight.setInterpolator(new AccelerateInterpolator());
        return inFromRight;
    }


}
