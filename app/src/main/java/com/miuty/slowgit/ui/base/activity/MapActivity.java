package com.app.qantas.domains.main;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.app.AppLocationProvider;
import com.app.location.providers.LocationGooglePlayServicesWithFallbackProvider;
import com.app.location.utils.LocationConfig;
import com.app.qantas.MainApplication;
import com.app.qantas.R;
import com.app.qantas.adapters.ProfileAutoCompleteAdapter;
import com.app.qantas.domains.base.BaseActivity;
import com.app.qantas.domains.dialogs.DialogAlertConfirmationSignIn;
import com.app.qantas.domains.dialogs.DialogSetTimeOnSite;
import com.app.qantas.domains.dialogs.DialogSetTimeOnSite_;
import com.app.qantas.domains.main.service.UpdateLocationService;
import com.app.qantas.domains.main.service.UpdateLocationService_;
import com.app.qantas.domains.manage_others.ManageOthersActivity_;
import com.app.qantas.domains.notification.NotificationActivity_;
import com.app.qantas.domains.profile.ProfileActivity_;
import com.app.qantas.domains.report_hazard.ReportHazardActivity_;
import com.app.qantas.domains.select_location.get_list_profile.SelectLocationActivity1_;
import com.app.qantas.domains.select_profile.SelectProfileActivity_;
import com.app.qantas.domains.select_site.SelectSiteActivity_;
import com.app.qantas.infrastructures.ActivityModule;
import com.app.qantas.models.LocationGeofences;
import com.app.qantas.models.MapLauncherMode;
import com.app.qantas.models.Profile;
import com.app.qantas.models.ProfileLocation;
import com.app.qantas.models.SignInLocation;
import com.app.qantas.models.Site;
import com.app.qantas.models.SiteSignBook;
import com.app.qantas.models.responses.AuthResponse;
import com.app.qantas.models.responses.ProfileLocationResponse;
import com.app.qantas.utils.AppUtil;
import com.app.qantas.widgets.AppFilterView;
import com.app.qantas.widgets.AppToolbar;
import com.app.qantas.widgets.KeyboardVisibility;
import com.app.qantas.widgets.QantasSupportMapFragment;
import com.balysv.materialripple.MaterialRippleLayout;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.maps.android.SphericalUtil;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.App;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.OnActivityResult;
import org.androidannotations.annotations.SystemService;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.RuntimePermissions;
import ru.terrakok.cicerone.Navigator;

import static com.app.qantas.models.MapLauncherMode.NORMAL;
import static com.app.qantas.models.MapLauncherMode.THE_FIRST;

@EActivity(R.layout.activity_main)
@RuntimePermissions
public class MainActivity extends BaseActivity<MainView, MainPresenter>
        implements MainView, OnMapReadyCallback, NavigationView.OnNavigationItemSelectedListener,
        GoogleMap.OnMarkerClickListener {

    public static final String TAG = MainActivity.class.getSimpleName();
    public static final int REQUEST_CODE_CHANGE_PROFILE = 1900;
    public static final int REQUEST_CODE_UPDATE_PROFILE = 1901;
    public static final int REQUEST_CODE_UPDATE_SET_TIME = 1902;
    public static final int REQUEST_CODE_CONFIRMATION_SIGN_IN = 1903;

    private long mLastPress;
    private static final long mBackPressThreshold = 4000;

    @App
    protected MainApplication application;
    @Inject
    protected MainPresenter presenter;
    @SystemService
    protected LayoutInflater layoutInflater;

    @ViewById(android.R.id.content)
    protected ViewGroup rootAndroidView;
    @ViewById(R.id.activity_main_drawer_layout)
    protected DrawerLayout drawerLayout;
    @ViewById(R.id.activity_main_cl_root)
    protected ConstraintLayout clRoot;
    @ViewById(R.id.activity_main_toolbar)
    protected AppToolbar toolbar;
    @ViewById(R.id.activity_main_nav_view)
    protected NavigationView navigationView;

    @ViewById(R.id.activity_main_ibtn_visibility)
    protected ImageButton ibtnVisibility;
    @ViewById(R.id.activity_main_ll_show_location_by)
    protected LinearLayout llShowLocationBy;
    @ViewById(R.id.activity_main_v_hide_show_location_by)
    protected View vHideShowLocationBy;
    @ViewById(R.id.activity_main_afv_filter)
    protected AppFilterView appFilterView;

    @ViewById(R.id.activity_main_btn_gps)
    protected ImageButton btnGps;
    @ViewById(R.id.activity_main_ll_action_bottom)
    protected LinearLayout llActionBottom;
    @ViewById(R.id.activity_main_abl_action_bar)
    protected LinearLayout ablActionBar;

    @ViewById(R.id.activity_main_tv_current_site_location)
    protected TextView tvCurrentSiteLocation;
    @ViewById(R.id.activity_main_iv_edit_set_time)
    protected ImageView ivSetTime;
    @ViewById(R.id.activity_main_mrl_edit_set_time)
    protected MaterialRippleLayout mrlSetTime;
    @ViewById(R.id.activity_main_tv_edit_set_time)
    protected TextView tvEditSetTime;

    @ViewById(R.id.layout_header_drawer_mrl_option)
    protected MaterialRippleLayout mrlOption;
    @ViewById(R.id.layout_header_drawer_mrl_header)
    protected MaterialRippleLayout mrlHeader;
    @ViewById(R.id.layout_header_drawer_tv_name)
    protected TextView tvProfileName;
    @ViewById(R.id.layout_header_drawer_tv_contractor)
    protected TextView tvProfileContractor;
    @ViewById(R.id.tv_network_state)
    protected TextView tvNetworkState;

    // extra
    @Extra
    protected MapLauncherMode mapLauncherMode = THE_FIRST;
    /*@Extra
    protected boolean needToShowSignIn = false;*/
    @Extra
    protected boolean isReselected = false;
    @Extra
    protected Profile selectedProfile;

    protected Site selectedSite;
    protected com.app.qantas.models.Location selectedLocation;

    @Extra
    protected Site siteTemp;
    @Extra
    protected com.app.qantas.models.Location locationTemp;

    protected QantasSupportMapFragment supportMapFragment;

    private AuthResponse authResponse;

    private LocationGooglePlayServicesWithFallbackProvider locationProvider;
    private Location currentLocation;
    private boolean hasGps = false;
    private ImageButton ibtnCloseDrawer;
    private GoogleMap googleMap;

    private List<Marker> markers = new ArrayList<>();
    private Marker ownerMarker;
    private List<Polygon> polygons = new ArrayList<>();

    private boolean reloadMap = true;

    // data object
    private List<ProfileLocation> profileLocationsByCoordinate = new ArrayList<>();
    private List<ProfileLocation> profileLocationsByProject = new ArrayList<>(); // list for searching
    private List<Site> sites = new ArrayList<>();

    private BroadcastReceiver countingReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "countingReceiver: start");
            //Toast.makeText(MainActivity.this, "hung", Toast.LENGTH_SHORT).show();

            hasGps = checkIfLocationOpened();
            if (currentLocation != null && hasGps && isNetworkAvailable) {
                Log.d(TAG, "countingReceiver: requested");
                //Toast.makeText(context, "countingReceiver: requested", Toast.LENGTH_SHORT).show();
                presenter.updateCurrentLocation(currentLocation.getLatitude(), currentLocation.getLongitude(), 0);
            } else {
                Log.d(TAG, "countingReceiver: not requested");
                //Toast.makeText(context, "countingReceiver: not requested", Toast.LENGTH_SHORT).show();
            }
        }
    };

    private boolean checkIfLocationOpened() {
        final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (manager.isProviderEnabled(LocationManager.GPS_PROVIDER) || manager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            return true;
        }
        // otherwise return false
        return false;
    }

    private BroadcastReceiver gpsReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "onReceive: start");
            if (intent != null) {
                if (intent.getAction().matches(LocationManager.PROVIDERS_CHANGED_ACTION)) {
                    LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                    if (manager != null) {
                        hasGps = manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
                        Log.d(TAG, "onReceive: hasGps = " + hasGps);
                        if (hasGps) {
                            updateLocationProvider();
                        } else {
                            Log.d(TAG, "turn of location");
                            if (currentLocation != null) {
                                presenter.updateCurrentLocation(currentLocation.getLatitude(), currentLocation.getLongitude(), 1);
                            }
                        }
                    }
                }
            }
        }
    };

    @AfterInject
    protected void initInject() {
        DaggerMainComponent.builder()
                .applicationComponent(application.getApplicationComponent())
                .activityModule(new ActivityModule(this))
                .build().inject(this);
    }

    @Override
    public void initViews() {
        super.initViews();

        // register necessary broadcasts & start the counting service
        startService(new Intent(this, UpdateLocationService_.class));
        registerReceiver(countingReceiver, new IntentFilter(UpdateLocationService.class.toString()));
        registerReceiver(gpsReceiver, new IntentFilter(LocationManager.PROVIDERS_CHANGED_ACTION));

        IntentFilter networkIntentFileter = new IntentFilter();
        networkIntentFileter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkIntentFileter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        registerReceiver(networkStateReceiver, networkIntentFileter);

        // update network state
        updateTextViewNetworkState();

        // get current User from local
        authResponse = presenter.getAuthentication();

        if (isReselected) {
            mapLauncherMode = NORMAL;
        }

        if (mapLauncherMode == THE_FIRST) {
            selectedSite = siteTemp;
            selectedLocation = locationTemp;
        }

        // check extra data (site & location)
        // these extra data is null if the app is opened in the second
        if (selectedSite == null) {
            selectedSite = authResponse.getSite();
        }
        if (selectedLocation == null) {
            selectedLocation = authResponse.getLocation();
        }
        if (selectedProfile == null) {
            selectedProfile = authResponse.getProfile();

        } else { // the first time launches maps or re-select profile
            if (isReselected) { // re-select profile
                if (selectedProfile.getSignInLocations() != null && selectedProfile.getSignInLocations().size() > 0) {
                    SignInLocation signInLocation = selectedProfile.getSignInLocations().get(0);
                    SiteSignBook siteSignBook = new SiteSignBook();
                    if (selectedProfile.isStaff()) {
                        siteSignBook.setIdStaff(selectedProfile.getId());
                    } else {
                        siteSignBook.setIdEmployee(selectedProfile.getId());
                    }
                    siteSignBook.setType(selectedProfile.getType());
                    siteSignBook.setDeviceCode(signInLocation.getDeviceCode());
                    siteSignBook.setTime(signInLocation.getSignInTime());
                    siteSignBook.setIdProject(signInLocation.getIdProject());
                    siteSignBook.setLocation(signInLocation.getLocation());
                    authResponse.setSiteSignBook(siteSignBook);
                }
            }
        }

        // save site & location & profile
        authResponse.setSite(selectedSite);
        authResponse.setLocation(selectedLocation);
        authResponse.setProfile(selectedProfile);

        if (mapLauncherMode == NORMAL && !isReselected) {
            //presenter.postCurrentProfile(selectedProfile);
            // check version app before set current profile
            presenter.checkAppVersion();
        } else {
            presenter.getProfileCurrentLocationByProject(selectedSite.getId());
        }

        // init Map component UI
        AppUtil.setTransparent(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ViewGroup.MarginLayoutParams layoutParamsGps = (ViewGroup.MarginLayoutParams) btnGps.getLayoutParams();
            layoutParamsGps.bottomMargin = AppUtil.convertDpToPixels(85f) + AppUtil.getHeightNavigationBar();
            btnGps.setLayoutParams(layoutParamsGps);
            ViewGroup.MarginLayoutParams layoutParamsActionBottom = (ViewGroup.MarginLayoutParams) llActionBottom.getLayoutParams();
            layoutParamsActionBottom.bottomMargin = AppUtil.convertDpToPixels(10f) + AppUtil.getHeightNavigationBar();
            btnGps.setLayoutParams(layoutParamsGps);

            ViewGroup.MarginLayoutParams layoutParamsShowLocationBy = (ViewGroup.MarginLayoutParams) llShowLocationBy.getLayoutParams();
            layoutParamsShowLocationBy.bottomMargin = AppUtil.getHeightNavigationBar();
            llShowLocationBy.setLayoutParams(layoutParamsShowLocationBy);
        }
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            RelativeLayout rlRootHeader = navigationView.getHeaderView(0).findViewById(R.id.layout_header_drawer_rl_root);
            rlRootHeader.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, AppUtil.convertDpToPixels(108f) - AppUtil.getHeightStatusBar()));
            ViewGroup.MarginLayoutParams layoutParamsActionBar = (ViewGroup.MarginLayoutParams) ablActionBar.getLayoutParams();
            layoutParamsActionBar.topMargin = AppUtil.convertDpToPixels(10f);
            ablActionBar.setLayoutParams(layoutParamsActionBar);
        } else {
            //ViewGroup.MarginLayoutParams layoutParamsActionBar = (ViewGroup.MarginLayoutParams) ablActionBar.getLayoutParams();
            //layoutParamsActionBar.topMargin = AppUtil.convertDpToPixels(10f) + AppUtil.getHeightStatusBar();
            //ablActionBar.setLayoutParams(layoutParamsActionBar);
        }

        if (navigationView != null) {
            mrlOption = navigationView.getHeaderView(0).findViewById(R.id.layout_header_drawer_mrl_option);
            mrlOption.setOnClickListener(v -> {
                SelectProfileActivity_.intent(this)
                        .isReSelected(true)
                        .startForResult(REQUEST_CODE_CHANGE_PROFILE);
            });
            mrlHeader = navigationView.getHeaderView(0).findViewById(R.id.layout_header_drawer_mrl_header);
            mrlHeader.setOnClickListener(v -> {
                ProfileLocation profileLocation = new ProfileLocation();
                profileLocation.setId(selectedProfile.getId());
                profileLocation.setName(selectedProfile.getName());
                profileLocation.setCompany(selectedProfile.getCompanyName());
                profileLocation.setType(selectedProfile.getType());

                ProfileActivity_.intent(this)
                        .profileLocation(profileLocation)
                        .startForResult(REQUEST_CODE_UPDATE_PROFILE);
            });
            tvProfileName = navigationView.getHeaderView(0).findViewById(R.id.layout_header_drawer_tv_name);
            tvProfileContractor = navigationView.getHeaderView(0).findViewById(R.id.layout_header_drawer_tv_contractor);

            tvProfileContractor.setText((authResponse != null && authResponse.getProfile() != null) ? authResponse.getProfile().getNameOrganisation() : "");
            tvProfileName.setText((authResponse != null && authResponse.getProfile() != null) ? authResponse.getProfile().getName() : "");
        }

        toolbar.setup(this, R.layout.layout_search_map, R.drawable.ic_menu, 0);
        toolbar.setColorTint(Color.parseColor("#575756"));
        toolbar.setOnLeftListener(v -> {
            drawerLayout.openDrawer(Gravity.START);
        });

        View.OnTouchListener onTouchListener = (view, motionEvent) -> {
            drawerLayout.openDrawer(Gravity.START);
            AppUtil.hideSoftInput(MainActivity.this, getCurrentFocus());
            uiHandler.postDelayed(() -> {
                if (!isFinishing()) {
                    toolbar.getLeftView().setOnTouchListener((View.OnTouchListener) toolbar.getLeftView().getTag());
                }
            }, 200);
            toolbar.getLeftView().setOnTouchListener(null);
            return false;
        };

        toolbar.getLeftView().setOnTouchListener(onTouchListener);
        toolbar.getLeftView().setTag(onTouchListener);

        navigationView.setNavigationItemSelectedListener(this);

        initViewCloseDrawer();

        if (reloadMap) {
            // load map async and get geofences by selected project
            uiHandler.postDelayed(() -> {
                supportMapFragment = new QantasSupportMapFragment();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.activity_main_fragment_map, supportMapFragment);
                ft.commitAllowingStateLoss(); // to prevent "can not perform this action after onsaveinstancestate"

                supportMapFragment.getMapAsync(this);
                supportMapFragment.setNonConsumingTouchListener(motionEvent -> {
                    AppUtil.hideSoftInput(MainActivity.this, getCurrentFocus());
                    AppUtil.clearFocus(MainActivity.this);
                    return false;
                });
            }, 500);
        }

        // setup botton sheet filter dialog
        appFilterView.setOnActionListener(new AppFilterView.OnActionListener() {
            @Override
            public void onFilterClose() {
                vHideShowLocationByClicked(null);
            }

            @Override
            public void onFilterSelected(int position) {
                refreshAllMarkersBySelectedPosition(position);
                vHideShowLocationByClicked(null);
            }
        });
        appFilterView.updateUIMode(selectedProfile.getType());

        /*if (sites != null && sites.size() > 0) {
            initAdapterSpinnerFilterAppView();
        }*/

        // init bottom sheet filter view
        updateFilterAppViewData();

        // request to grant permissions
        showPermissionInitialize();

        // init Map bottom (time on site and location)
        updateMapBottomButton();
    }

    private void updateMapBottomButton() {
        // init Map bottom (time on site and location)
        if (authResponse.getSiteSignBook() != null) {
            if (!TextUtils.isEmpty(authResponse.getSiteSignBook().getExpectedTimeToFinish())) {
                tvEditSetTime.setText(presenter.convertExpectedTime(authResponse.getSiteSignBook().getExpectedTimeToFinish(),
                        authResponse.getSiteSignBook().getTime()));
            }

            tvCurrentSiteLocation.setText(presenter.getCurrentSiteLocation
                    (authResponse.getSiteSignBook().getProjectName(), authResponse.getSiteSignBook().getLocation()));
            tvCurrentSiteLocation.setSelected(true);
        } else {
            tvEditSetTime.setText("00:00");

            /*tvCurrentSiteLocation.setText((authResponse != null
                    ? presenter.getCurrentSiteLocation(selectedSite, selectedLocation) : ""));*/
            tvCurrentSiteLocation.setText("--.--");
            tvCurrentSiteLocation.setSelected(true);
        }
    }

    @SuppressLint("MissingPermission")
    private void updateLocationProvider() {

        Log.d(TAG, "UPDATE LOCATION PROVIDER ");

        if (locationProvider == null) {
            locationProvider = new LocationGooglePlayServicesWithFallbackProvider(this);
        }

        AppLocationProvider.with(MainActivity.this)
                .location(locationProvider)
                .config(LocationConfig.BEST_EFFORT)
                .continuous().start(location -> {
            currentLocation = location;

            if (MainActivity.this.googleMap != null) {
                Log.d(TAG, "UPDATE LOCATION PROVIDER " + currentLocation.getLongitude());

                LatLng currentMarker = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
                if (ownerMarker != null) {
                    ownerMarker.remove();
                }
                //ownerMarker = addMarkerToMap(currentMarker, getResources().getColor(R.color.colorPrimary), "");
                MainActivity.this.googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentMarker, 15.0f));
            }
        });
    }

    private void refreshAllMarkersBySelectedPosition(int position) {
        Log.d(TAG, "refreshAllMarkersBySelectedPosition: " + System.currentTimeMillis());

        // remove marker to from Maps
        for (Marker marker : markers) {
            marker.remove();
        }

        // clear list markers data on Ram
        this.markers.clear();

        // re-draw marker with new text
        int i = 0;
        for (ProfileLocation pl : profileLocationsByCoordinate) {
            String text = "";
            // specific text by selected position
            switch (position) {
                case 0:
                    text = pl.getTimeOnProject();
                    break;
                case 1:
                    text = pl.getLocation();
                    break;
                case 2:
                    text = "No";
                    break;
                case 3:
                    text = pl.getLastLocationTimestamp();
                    break;
                case 4:
                    text = pl.getCompany();
                    break;
                case 5:
                    text = pl.getName();
                    break;
            }
            LatLng latLng = new LatLng(pl.getLatitude(), pl.getLongitude());
            Marker marker = addMarkerToMap(latLng, Color.RED, text);
            marker.setTag(i);
            this.markers.add(marker);
            i++;
        }
        Log.d(TAG, "refreshAllMarkersBySelectedPosition: " + System.currentTimeMillis());
    }

    private void initAdapterSpinnerFilterAppView() {
        if (sites != null && sites.size() > 0) {

            // convert sites to list string for showing dropdown list
            List<String> strings = new ArrayList<>();
            for (Site site : sites) {
                strings.add(site.getName());
            }

            Spinner snFilterSite = appFilterView.findViewById(R.id.sn_filter_site);
            ArrayAdapter filterSiteAdapter = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, strings);
            snFilterSite.setAdapter(filterSiteAdapter);
            snFilterSite.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // re-fresh data of profile searching list and re-draw polygon
                    selectedSite = sites.get(position);
                    authResponse.setSite(selectedSite);
                    presenter.getGeofencesByProject(selectedSite.getId());
                    presenter.getProfileCurrentLocationByProject(selectedSite.getId());
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
    }

    private void updateFilterAppViewData() {
        String[] values = new String[6];

        AuthResponse authResponse = presenter.getAuthResponseFromLocal();

        if (authResponse != null) {
            SiteSignBook siteSignBook = authResponse.getSiteSignBook();
            if (siteSignBook != null) {
                values[0] = presenter.getTimeOnSite(siteSignBook.getTime(), siteSignBook.getTimeZone());
                values[1] = siteSignBook.getLocation();
                values[2] = "No";
                values[3] = siteSignBook.getLastLocationTimeStamp();

                /*com.app.qantas.models.Location location = authResponse.getLocation();
                if (location != null) {
                    values[1] = location.getName();
                }*/
            } else { // reset data if not sign in
                values[0] = "";
                values[1] = "";
                values[2] = "";
                values[3] = "";
            }

            Profile profile = authResponse.getProfile();
            if (profile != null) {
                values[4] = profile.getNameOrganisation();
                values[5] = profile.getName();
            } else {// reset data if profile is empty
                values[4] = "";
                values[5] = "";
            }
        }

        appFilterView.setAfvValue(values);
    }

    @Override
    protected void onResume() {
        super.onResume();
        uiHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                presenter.checkAppVersion();
            }
        }, 5000);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //unregisterReceiver(countingReceiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(new Intent(this, UpdateLocationService_.class));
        unregisterReceiver(countingReceiver);
        unregisterReceiver(gpsReceiver);
    }

    @Click(R.id.activity_main_btn_gps)
    protected void btnGpsClicked() {
        if (currentLocation != null && this.googleMap != null) {
            LatLng currentMarker = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
            MainActivity.this.googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentMarker, 15));
        }
    }

    private void initViewCloseDrawer() {
        final FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.TOP | Gravity.RIGHT;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            params.topMargin = AppUtil.convertDpToPixels(42f) - AppUtil.getHeightStatusBar();
            params.rightMargin = AppUtil.convertDpToPixels(2f);
        } else {
            params.topMargin = AppUtil.convertDpToPixels(42f);
            params.rightMargin = AppUtil.convertDpToPixels(5f);
        }

        ibtnCloseDrawer = new ImageButton(this);
        ibtnCloseDrawer.setBackgroundColor(Color.TRANSPARENT);
        ibtnCloseDrawer.setImageResource(R.drawable.ic_close);
        rootAndroidView.addView(ibtnCloseDrawer, params);
        ibtnCloseDrawer.setVisibility(View.INVISIBLE);
        ibtnCloseDrawer.setOnClickListener(v -> {
            drawerLayout.closeDrawer(Gravity.START);
        });
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                ibtnCloseDrawer.setVisibility(slideOffset > 0 ? View.VISIBLE : View.INVISIBLE);
                ibtnCloseDrawer.setAlpha(slideOffset);
            }

            @Override
            public void onDrawerStateChanged(int newState) {
                super.onDrawerStateChanged(newState);
                updateSignInOutMenu();
            }
        });
    }

    @Override
    protected boolean isNetworkChecking() {
        return true;
    }

    @Override
    public Navigator getNavigator() {
        return super.getNavigator();
    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }

    @NonNull
    @Override
    public MainPresenter createPresenter() {
        return presenter;
    }

    @Override
    public void showLoading() {
        showProgressLoading();
    }

    @Override
    public void hideLoading() {
        hideProgressLoading();
    }

    @Override
    public void onGetLocationSuccessful(List<com.app.qantas.models.LatLng> latLngs) {
        Log.d(TAG, "onGetLocationSuccessful: ");
    }

    @Override
    public void onGetLocationFailed(Throwable throwable) {

    }

    @Override
    public void onPostSignInAndAddExpectedTimeSuccessful(int hour, int minute, Site site, com.app.qantas.models.Location location) {
        hideLoading();

        Toast.makeText(this, "Sign In success to " + location.getName(), Toast.LENGTH_LONG).show();

        selectedSite = siteTemp;
        selectedLocation = locationTemp;
        authResponse.setSite(siteTemp);
        authResponse.setLocation(locationTemp);

        //--Reload current site on bottom Maps
        tvCurrentSiteLocation.setText((authResponse != null
                ? presenter.getCurrentSiteLocation(authResponse.getSite(), authResponse.getLocation()) : ""));
        tvCurrentSiteLocation.setSelected(true);
        tvEditSetTime.setText(hour + ":" + minute);

        updateFilterAppViewData();
    }

    @Override
    public void addExpectedTimeSuccessful(int hour, int minute, Site site, com.app.qantas.models.Location location) {

        Toast.makeText(this, "Edit Time Success", Toast.LENGTH_LONG).show();

        //--Reload current site on bottom Maps
        tvCurrentSiteLocation.setText((authResponse != null
                ? presenter.getCurrentSiteLocation(authResponse.getSite(), authResponse.getLocation()) : ""));
        tvCurrentSiteLocation.setSelected(true);
        tvEditSetTime.setText(hour + ":" + minute);

        updateFilterAppViewData();
    }

    @Override
    public void onPostSignInAndAddExpectedTimeFailed(Throwable throwable) {

    }

    @Override
    public void addExpectedTimeFailed(Throwable throwable) {

    }

    @Override
    public void onGetGeofencesByProjectSuccessful(List<LocationGeofences> locationGeofences) {
        if (polygons != null) {
            for (Polygon p : polygons) {
                p.remove();
            }
        }
        // draw all polygons
        for (LocationGeofences lg : locationGeofences) {
            addPolygonToMap(lg);
        }
    }

    @Override
    public void onSubmitLocationSuccessful(List<com.app.qantas.models.Location> locations) {

    }

    @Override
    public void onSubmitLocationFailed(Throwable throwable) {

    }

    //--Begin Tuan Edit - Integrate Sign Out
    @Override
    public void onSignOutFailed(Throwable throwable) {
        Log.e(TAG, "SignOut Failed" + throwable.getMessage());
    }

    @Override
    public void onSignOutAThenSignInBSuccessful(SiteSignBook response, Site signInSite,
                                                com.app.qantas.models.Location signInLocation, int hour, int minute) {
        presenter.postSignInAndAddExpectedTime(hour, minute, signInSite, signInLocation, hasGps ? 0 : 1, currentLocation);
    }

    @Override
    public void onSignOutAThenSignInBFailed(Throwable throwable) {

    }


    @Override
    public void onSignOutSuccess(SiteSignBook response, Site site, com.app.qantas.models.Location location) {
        hideProgressLoading();

        Toast.makeText(this, "Sign out success", Toast.LENGTH_LONG).show();

        //--Change Sign In to Sign IN in Menu
        authResponse.setSiteSignBook(null);

        tvEditSetTime.setText("00:00");//Reset Time Stay in Location
        tvCurrentSiteLocation.setText("--.--");

        Log.d(TAG, "SignOut Success");

        updateFilterAppViewData();
    }
    //--End Tuan Edit

    @Override
    public void onPostSiteAndLocationSuccessful() {
        Log.d(TAG, "onPostSiteAndLocationSuccessful: ");
        //TODO: reload map with new Site
    }

    @Override
    public void onPostSiteAndLocationFailed(Throwable throwable) {

    }

    @Override
    public void onGetProfileLocationByCoordinateSuccess(ProfileLocationResponse response) {
        if (response != null) {
            profileLocationsByCoordinate.clear();
            profileLocationsByCoordinate.addAll(response.getProfileLocations());
            refreshAllMarkersBySelectedPosition(0);
        }
    }

    @Override
    public void onGetProfileLocationByCoordinateFailed(Throwable throwable) {

    }

    @Override
    public void onSubmitProfileAndGetAWSKeySuccessful(Profile profile) {
        // init bottom sheet filter view
        updateFilterAppViewData();
        updateMapBottomButton();
        presenter.getProfileCurrentLocationByProject(selectedSite.getId());
    }

    @Override
    public void onSubmitProfileAndGetAWSKeyFailed(Throwable throwable) {

    }

    @Override
    public void onGetProfileLocationByProjectSuccessful(ProfileLocationResponse response) {
        AutoCompleteTextView actSearch = toolbar.findViewById(R.id.act_search);
       /* ArrayList<ProfileLocation> profileLocations = new ArrayList<>();

        ProfileLocation profileLocation = new ProfileLocation();
        profileLocation.setId(1L);
        profileLocation.setName("name");
        profileLocation.setCompany("company");
        profileLocation.setDistance(10);

        ProfileLocation profileLocation1 = new ProfileLocation();
        profileLocation1.setId(1L);
        profileLocation1.setName("hung");
        profileLocation1.setCompany("company");
        profileLocation1.setDistance(10);

        profileLocations.add(profileLocation);
        profileLocations.add(profileLocation1);*/
        profileLocationsByProject = response.getProfileLocations();
        ArrayAdapter<ProfileLocation> arrayAdapter = new ProfileAutoCompleteAdapter(this, R.layout.layout_item_search_list, (ArrayList<ProfileLocation>) profileLocationsByProject);
        actSearch.setAdapter(arrayAdapter);
        actSearch.setOnItemClickListener((parent, view, position, id) -> {
            if (MainActivity.this.googleMap != null) {
                AppUtil.hideSoftInput(this, getCurrentFocus());
                AppUtil.clearFocus(this);
                ProfileLocation profileLocation = profileLocationsByProject.get(position);
                MainActivity.this.googleMap.animateCamera(CameraUpdateFactory.newLatLng
                        (new LatLng(profileLocation.getLatitude(), profileLocation.getLongitude())));
            }
        });
    }

    @Override
    public void onGetProfileLocationByProjectFailed(Throwable throwable) {

    }

    @Override
    public void onGetListSiteSuccessful(List<Site> response) {
        sites = response;
        initAdapterSpinnerFilterAppView();
    }

    @Override
    public void onGetListSiteFailed(Throwable throwable) {

    }

    @Override
    public void onMatchCurrentVersionApp() {
        // if version app is matched the flow will be run normally
        presenter.postCurrentProfile(selectedProfile);
    }

    @Override
    public void onOutOfDateVersionApp() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Title")
                .setMessage("Message")
                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final String appPackageName = getPackageName(); // get quantas package name
                        openMarketStore("com.google.android.googlequicksearchbox"); // harde code open google in store
                    }
                }).show();
    }

    @Override
    public void onGetVersionAppFailed(Throwable throwable) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        this.googleMap = googleMap;
        this.googleMap.getUiSettings().setMapToolbarEnabled(false);

        this.googleMap.setOnMarkerClickListener(this);
        this.googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        // set max min zoomable
        this.googleMap.setMaxZoomPreference(19.0f);
        this.googleMap.setMinZoomPreference(14.8f);

        // set default marker current user
        this.googleMap.getUiSettings().setCompassEnabled(true);
        this.googleMap.getUiSettings().setMyLocationButtonEnabled(false);

        this.googleMap.setOnCameraIdleListener(() -> {
            LatLng center = googleMap.getCameraPosition().target;
            LatLng topLeft = googleMap.getProjection().getVisibleRegion().farLeft;
            double distance = SphericalUtil.computeDistanceBetween(topLeft, center);
            if (authResponse.getProfile().isStaff()) {
                presenter.getProfileCurrentLocationByCoordinate(center.latitude, center.longitude, distance);
            }
        });

        if (this.googleMap != null) {
            if (ActivityCompat.checkSelfPermission
                    (this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            } else {
                this.googleMap.setMyLocationEnabled(true);
            }
        }

        //--Begin Edit Tuan- Zoom to current location after Maps load ready
        updateLocationProvider();
        //--End Edit

        // get all geofences by project id from server
        presenter.getGeofencesByProject(selectedSite.getId());
    }

    View vMarker;
    TextView tvContent;

    private Marker addMarkerToMap(LatLng latLng, int color, String text) {
        if (this.googleMap != null) {
            if (vMarker == null) {
                vMarker = layoutInflater.inflate(R.layout.layout_marker, null);
                ImageView ivLocation = vMarker.findViewById(R.id.layout_marker_iv_location);
                ivLocation.getDrawable().setColorFilter(color, PorterDuff.Mode.MULTIPLY);
                ivLocation.invalidate();
                tvContent = vMarker.findViewById(R.id.layout_marker_tv_content);

                // config view after converting to bitmap

            }
            tvContent.setText(text);

            Log.d(TAG, "vMarker width-height:" + vMarker.getMeasuredWidth() + "-" + vMarker.getMeasuredHeight());
            Bitmap bmMarker = AppUtil.createDrawableFromView(this, vMarker);
            Marker marker = this.googleMap.addMarker(new MarkerOptions()
                    .anchor((float) AppUtil.convertDpToPixels(10f) / bmMarker.getWidth(), 1)
                    .position(latLng)
                    .title("Title")
                    .snippet("Description")
                    .icon(BitmapDescriptorFactory.fromBitmap(bmMarker)));
            return marker;
        }
        return null;
    }

    private void addPolygonToMap(LocationGeofences locationGeofences) {
        List<LatLng> latLngs = presenter.convertListLocationGeoToLatLong(locationGeofences);
        Polygon polygon = this.googleMap.addPolygon(new PolygonOptions()
                .add(latLngs.toArray(new LatLng[latLngs.size()]))
                .strokeColor(Color.GRAY)
                .strokeWidth(0)
                .fillColor(Color.parseColor("#32800080"))
        );
        this.polygons.add(polygon);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        // close navigation when clicking
        drawerLayout.closeDrawer(Gravity.START);

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_map) {
            // Handle the camera action
        } else if (id == R.id.nav_list) {
            SelectLocationActivity1_.intent(this)
                    .isReSelected(false)
                    .selectedProfile(selectedProfile)
                    .site(selectedSite)
                    .start();
            return false;
        } else if (id == R.id.nav_notification) {
            NotificationActivity_.intent(this)
                    .start();
        } else if (id == R.id.nav_report_hazard) {
            ReportHazardActivity_.intent(this).start();
            return false;
        } else if (id == R.id.nav_manage_others) {
            ManageOthersActivity_.intent(this).start();
            return false;
        } else if (id == R.id.nav_signin) {

            //--Begin Tuan Edit
            authResponse = presenter.getAuthentication();

            //--If User signed in -> Menu change to Sign Out -> Click into Sign Out -> Sign Out Location
            if (authResponse.isSignIn() && authResponse.getSiteSignBook() != null) {
                com.app.qantas.models.Location location = new com.app.qantas.models.Location();
                location.setId(authResponse.getSiteSignBook().getIdLocation());
                location.setName(authResponse.getSiteSignBook().getLocation());
                location.setDeviceCode(authResponse.getSiteSignBook().getDeviceCode());

                Site siteSignedIn = new Site();
                siteSignedIn.setId(authResponse.getSiteSignBook().getIdProject());
                siteSignedIn.setTimeZone(authResponse.getSiteSignBook().getTimeZone());

                if (isNetworkAvailable) {
                    presenter.postSignOut(siteSignedIn, location, hasGps ? 0 : 1, currentLocation);
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("the internet is not available")
                            .setNegativeButton("OK", null).show();
                    updateTextViewNetworkState();
                }
            } else {
                SelectSiteActivity_.intent(this).isReSelected(true).start();
            }

            //--End Tuan Edit

            return false;
        }
        return true;
    }


    @NeedsPermission({Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    protected void showPermissionInitialize() {
        // check whether show SignInPopup after granted permission or NOT
        if (isReselected) {
            showSignInPopup();
        } else {
            if (!authResponse.getProfile().isStaff() && authResponse.getSiteSignBook() == null && mapLauncherMode == THE_FIRST) {
                showSignInPopup();
            }
        }

        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        if (this.googleMap != null) {
            this.googleMap.setMyLocationEnabled(true);
        }

        updateLocationProvider();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (locationProvider != null) {
            locationProvider.onActivityResult(requestCode, resultCode, data);
        }
    }

    @OnPermissionDenied({Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION})
    void onPermissionDenied() {

    }

    @OnNeverAskAgain({Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION})
    void onPermissionNeverAskAgain() {

    }

    @Click(R.id.activity_main_ibtn_visibility)
    protected void iBtnVisibilityClicked(View view) {
        AppUtil.hideSoftInput(this, getCurrentFocus());
        AppUtil.clearFocus(this);
        Animation bottomUp = AnimationUtils.loadAnimation(this, R.anim.bottom_up);
        bottomUp.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                ibtnVisibility.setEnabled(false);
                vHideShowLocationBy.setEnabled(false);
                vHideShowLocationBy.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ibtnVisibility.setEnabled(true);
                vHideShowLocationBy.setEnabled(true);
                vHideShowLocationBy.setVisibility(View.VISIBLE);
                AlphaAnimation animationAlpha = new AlphaAnimation(0.0f, 1.0f);
                animationAlpha.setDuration(100);
                animationAlpha.setFillAfter(true);
                vHideShowLocationBy.startAnimation(animationAlpha);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        llShowLocationBy.startAnimation(bottomUp);
        llShowLocationBy.setVisibility(View.VISIBLE);

        if (sites != null && sites.size() > 0) {
            //initAdapterSpinnerFilterAppView();
        } else {
            if (authResponse.getProfile().isStaff()) {
                presenter.getListSite();
            }
        }
    }

    @Click(R.id.activity_main_v_hide_show_location_by)
    protected void vHideShowLocationByClicked(View view) { // appfilter
        updateFilterAppViewData();
        AppUtil.hideSoftInput(this, getCurrentFocus());
        AppUtil.clearFocus(this);
        llShowLocationBy.setVisibility(View.GONE);
        Animation bottomDown = AnimationUtils.loadAnimation(this, R.anim.bottom_down);
        bottomDown.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                ibtnVisibility.setEnabled(false);
                vHideShowLocationBy.setEnabled(false);
                AlphaAnimation animationAlpha = new AlphaAnimation(1.0f, 0.0f);
                animationAlpha.setDuration(100);
                animationAlpha.setFillAfter(true);
                vHideShowLocationBy.startAnimation(animationAlpha);
                vHideShowLocationBy.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ibtnVisibility.setEnabled(true);
                vHideShowLocationBy.setEnabled(true);
                vHideShowLocationBy.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        llShowLocationBy.startAnimation(bottomDown);
        llShowLocationBy.setVisibility(View.GONE);
    }


    @Click({R.id.activity_main_iv_edit_set_time, R.id.activity_main_mrl_edit_set_time})
    protected void ivEditSetTimeClicked(View view) {
        AppUtil.hideSoftInput(this, getCurrentFocus());
        AppUtil.clearFocus(this);

        //--Begin Tuan Fix
        if (authResponse.getSiteSignBook() != null) {
            showEditTimePopup();
        }

//        SelectSiteActivity_.intent(this).isReSelected(true).start();
        //--End Tuan Fix
    }

    private void showSignInPopup() {
        Log.d(TAG, "showSignInPopup: ");
        AppUtil.hideSoftInput(this, getCurrentFocus());
        AppUtil.clearFocus(this);
        DialogSetTimeOnSite_
                .intent(this)
                .selectedSite(siteTemp)
                .selectedLocation(locationTemp)
                .isEdit(false)
                .startForResult(REQUEST_CODE_UPDATE_SET_TIME);
    }

    private void showEditTimePopup() {
        Log.d(TAG, "showEditTimePopup: ");
        AppUtil.hideSoftInput(this, getCurrentFocus());
        AppUtil.clearFocus(this);

        com.app.qantas.models.Location location = new com.app.qantas.models.Location();
        location.setName(authResponse.getSiteSignBook().getLocation());
        location.setId(authResponse.getSiteSignBook().getIdLocation());
        location.setDeviceCode(authResponse.getSiteSignBook().getDeviceCode());

        Site site = new Site();
        site.setName(authResponse.getSiteSignBook().getProjectName());
        site.setId(authResponse.getSiteSignBook().getIdProject());
        DialogSetTimeOnSite_
                .intent(this)
                .selectedSite(site)
                .selectedLocation(location)
                .isEdit(true)
                .startForResult(REQUEST_CODE_UPDATE_SET_TIME);
    }

    @OnActivityResult(REQUEST_CODE_CONFIRMATION_SIGN_IN)
    protected void onActivityResultConfirmationSignIn(
            int resultCode, @OnActivityResult.Extra(value = DialogAlertConfirmationSignIn.KEY_EXTRA_LEFT) boolean option1,
            @OnActivityResult.Extra(value = DialogAlertConfirmationSignIn.KEY_EXTRA_RIGHT) boolean option2,
            @OnActivityResult.Extra(value = DialogAlertConfirmationSignIn.KEY_EXTRA_HOUR) int hour,
            @OnActivityResult.Extra(value = DialogAlertConfirmationSignIn.KEY_EXTRA_MINUTE) int minute) {
        if (resultCode == RESULT_OK) {
            if (option1) { // sign out together
                if (authResponse.getSiteSignBook() != null) {
                    com.app.qantas.models.Location location = new com.app.qantas.models.Location();
                    location.setName(authResponse.getSiteSignBook().getLocation());
                    location.setId(authResponse.getSiteSignBook().getIdLocation());
                    location.setDeviceCode(authResponse.getSiteSignBook().getDeviceCode());

                    Site siteSignedIn = new Site();
                    siteSignedIn.setId(authResponse.getSiteSignBook().getIdProject());
                    siteSignedIn.setTimeZone(authResponse.getSiteSignBook().getTimeZone());

                    presenter.postSignOut(siteSignedIn, location, hasGps ? 0 : 1, currentLocation);
                }
            }

            if (option2) { // sign in A and sign out B
                com.app.qantas.models.Location locationSignedIN = new com.app.qantas.models.Location();
                locationSignedIN.setId(authResponse.getSiteSignBook().getIdLocation());
                locationSignedIN.setName(authResponse.getSiteSignBook().getLocation());
                locationSignedIN.setDeviceCode(authResponse.getSiteSignBook().getDeviceCode());

                Site siteSignedIn = new Site();
                siteSignedIn.setId(authResponse.getSiteSignBook().getIdProject());
                siteSignedIn.setTimeZone(authResponse.getSiteSignBook().getTimeZone());

                presenter.signOutAThenSignInB(siteSignedIn, locationSignedIN, selectedSite, selectedLocation, hour, minute,
                        hasGps ? 0 : 1, currentLocation);
            }
        }
    }

    @OnActivityResult(REQUEST_CODE_UPDATE_SET_TIME)
    protected void onEditSetTimeResult(int resultCode,
                                       @OnActivityResult.Extra(value = DialogSetTimeOnSite.IS_EDIT_EXTRA_KEY) boolean isEdit,
                                       @OnActivityResult.Extra(value = DialogSetTimeOnSite.HOUR_EXTRA_KEY) int hour,
                                       @OnActivityResult.Extra(value = DialogSetTimeOnSite.MINUTE_EXTRA_KEY) int minute) {
        if (resultCode == Activity.RESULT_OK) {
            if (isEdit) {
                if (authResponse.isSignIn()) {
                    SiteSignBook siteSignBook = authResponse.getSiteSignBook();
                    Site site = new Site();
                    site.setId(siteSignBook.getIdProject());
                    site.setTimeZone(siteSignBook.getTimeZone());
                    com.app.qantas.models.Location location = new com.app.qantas.models.Location();
                    location.setId(siteSignBook.getIdLocation());
                    presenter.addExpectedTime(hour, minute, site, location);
                }
            } else {
                if (authResponse.isSignIn()) {

                    //--modify by Hung
                    //--Start
                    //--not must show confirmation dialog. SignOut A then SignIn B instead
              /*  DialogAlertConfirmationSignIn_.intent(this)
                        .title(getString(R.string.dialog_confirmation_title))
                        .content(String.format("%s is already signed in to %s", authResponse.getProfileDetail().getName(),
                                authResponse.getSiteSignBook().getLocation()))//--Tuan Edit
                        .txtLeft("Sign Out Together")//--Tuan Edit
                        .txtRight(String.format("Sign Out %s and IN to %s", authResponse.getSiteSignBook().getLocation(),
                                selectedLocation.getName()))//--Tuan Edit
                        .hour(hour)
                        .minute(minute)
                        .startForResult(REQUEST_CODE_CONFIRMATION_SIGN_IN);*/

                    com.app.qantas.models.Location locationSignedIn = new com.app.qantas.models.Location();
                    locationSignedIn.setId(authResponse.getSiteSignBook().getIdLocation());
                    locationSignedIn.setName(authResponse.getSiteSignBook().getLocation());
                    locationSignedIn.setDeviceCode(authResponse.getSiteSignBook().getDeviceCode());


                    Site siteSignedIn = new Site();
                    siteSignedIn.setId(authResponse.getSiteSignBook().getIdProject());
                    siteSignedIn.setTimeZone(authResponse.getSiteSignBook().getTimeZone());

                    presenter.signOutAThenSignInB(siteSignedIn, locationSignedIn, siteTemp, locationTemp,
                            hour, minute, hasGps ? 0 : 1, currentLocation);
                    //--End
                } else {
                    presenter.postSignInAndAddExpectedTime(hour, minute, siteTemp, locationTemp, hasGps ? 0 : 1, currentLocation);
                }
            }
        }
    }

    @Click({R.id.activity_main_iv_edit_site_location, R.id.activity_main_mrl_edit_site_location})
    protected void ivEditSiteLocationClicked(View view) {
        AppUtil.hideSoftInput(this, getCurrentFocus());
        AppUtil.clearFocus(this);
        SelectSiteActivity_.intent(this).isReSelected(true).start();
    }

    private void updateSignInOutMenu() {
        if (navigationView != null) {
            Menu navMenu = navigationView.getMenu();
            authResponse = presenter.getAuthentication();
            MenuItem menuItem = navMenu.findItem(R.id.nav_signin);
            if (authResponse.isSignIn()) {
                menuItem.setTitle("SIGN OUT");
                menuItem.setIcon(R.drawable.ic_signout);
            } else {
                menuItem.setTitle("SIGN IN");
                menuItem.setIcon(R.drawable.ic_signin);
            }
        }
    }

    @Override
    public void onBackPressed() {
        long currentTime = System.currentTimeMillis();
        //Toast.makeText(this, getResources().getString(R.string.press_back_again_to_exit), Toast.LENGTH_SHORT).show();
        if (Math.abs(currentTime - mLastPress) > mBackPressThreshold) {
            mLastPress = currentTime;
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        reloadMap = false;
        initViews();
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        if (marker.equals(ownerMarker)) {
            // move camera to the clicked marker
            this.googleMap.animateCamera(CameraUpdateFactory.newLatLng(marker.getPosition()));

           /* ProfileActivity_.intent(this)
                    .id(authResponse.getProfileDetail().getId())
                    .role(selectedProfile.getType())
                    .startForResult(REQUEST_CODE_UPDATE_PROFILE);*/
            return true;
        }

        if (profileLocationsByCoordinate != null && profileLocationsByCoordinate.size() > 0) {

            // move camera to the clicked marker
            this.googleMap.animateCamera(CameraUpdateFactory.newLatLng(marker.getPosition()));

            int tag = (int) marker.getTag();
            ProfileActivity_.intent(this)
                    .profileLocation(profileLocationsByCoordinate.get(tag))
                    .startForResult(REQUEST_CODE_UPDATE_PROFILE);
        }
        return true;
    }

    /*Broadcast*/
    BroadcastReceiver networkStateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            handlerNetworkChange(context, intent);
        }
    };

    boolean isNetworkAvailable = false;

    public void handlerNetworkChange(Context context, Intent intent) {
        Log.d(TAG, "f1: ");

        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        int extraWifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE,
                WifiManager.WIFI_STATE_UNKNOWN);
        Log.d(TAG, "wifi = off");
        switch (extraWifiState) {
            case WifiManager.WIFI_STATE_DISABLED:
            case WifiManager.WIFI_STATE_DISABLING:
                Log.d(TAG, "wifi = off");
                isNetworkAvailable = false;
                updateTextViewNetworkState();
                break;
            case WifiManager.WIFI_STATE_ENABLING:
            case WifiManager.WIFI_STATE_ENABLED:
                Log.d(TAG, "wifi = on");
            case WifiManager.WIFI_STATE_UNKNOWN:
                Log.d(TAG, "wifi = unknow");
                if (activeNetwork != null) {
                    Log.d(TAG, "activeNetwork != null");
                    boolean isConnected = activeNetwork.isConnected();
                    if (isConnected) {
                        Log.d(TAG, "isConnected: ");
                        isNetworkAvailable = true;
                        updateTextViewNetworkState();
                    } else {
                        Log.d(TAG, "is not Connected: ");
                        isNetworkAvailable = false;
                        updateTextViewNetworkState();
                    }

                   /* NetworkInfo.State state = activeNetwork.getState();
                    Log.d(TAG, "state = : " + state);*/
                }

                break;
        }
    }

    CountDownTimer timer = new CountDownTimer(10000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {

        }

        @Override
        public void onFinish() {
            tvNetworkState.setVisibility(View.GONE);
        }
    };

    private void updateTextViewNetworkState() {
        tvNetworkState.setVisibility(View.VISIBLE);
        timer.cancel();
        timer.start();
        if (isNetworkAvailable) {
            tvNetworkState.setText("Updating data...");
        } else {
            tvNetworkState.setText("Waiting connection...");
        }
    }

    public void openMarketStore(String appPackageName) {
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
        } catch (android.content.ActivityNotFoundException anfe) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
        }
    }
}

package com.app.qantas.domains.main;

        import android.Manifest;
        import android.annotation.SuppressLint;
        import android.app.Activity;
        import android.app.AlertDialog;
        import android.content.BroadcastReceiver;
        import android.content.Context;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.content.IntentFilter;
        import android.content.pm.PackageManager;
        import android.graphics.Bitmap;
        import android.graphics.Color;
        import android.graphics.PorterDuff;
        import android.location.Location;
        import android.location.LocationManager;
        import android.net.ConnectivityManager;
        import android.net.NetworkInfo;
        import android.net.Uri;
        import android.net.wifi.WifiManager;
        import android.os.Build;
        import android.os.CountDownTimer;
        import android.support.annotation.NonNull;
        import android.support.constraint.ConstraintLayout;
        import android.support.design.widget.NavigationView;
        import android.support.v4.app.ActivityCompat;
        import android.support.v4.app.FragmentTransaction;
        import android.support.v4.widget.DrawerLayout;
        import android.text.TextUtils;
        import android.util.Log;
        import android.view.Gravity;
        import android.view.LayoutInflater;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.view.ViewGroup;
        import android.view.animation.AlphaAnimation;
        import android.view.animation.Animation;
        import android.view.animation.AnimationUtils;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.AutoCompleteTextView;
        import android.widget.FrameLayout;
        import android.widget.ImageButton;
        import android.widget.ImageView;
        import android.widget.LinearLayout;
        import android.widget.RelativeLayout;
        import android.widget.Spinner;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.app.AppLocationProvider;
        import com.app.location.providers.LocationGooglePlayServicesWithFallbackProvider;
        import com.app.location.utils.LocationConfig;
        import com.app.qantas.MainApplication;
        import com.app.qantas.R;
        import com.app.qantas.adapters.ProfileAutoCompleteAdapter;
        import com.app.qantas.domains.base.BaseActivity;
        import com.app.qantas.domains.dialogs.DialogAlertConfirmationSignIn;
        import com.app.qantas.domains.dialogs.DialogSetTimeOnSite;
        import com.app.qantas.domains.dialogs.DialogSetTimeOnSite_;
        import com.app.qantas.domains.main.service.UpdateLocationService;
        import com.app.qantas.domains.main.service.UpdateLocationService_;
        import com.app.qantas.domains.manage_others.ManageOthersActivity_;
        import com.app.qantas.domains.notification.NotificationActivity_;
        import com.app.qantas.domains.profile.ProfileActivity_;
        import com.app.qantas.domains.report_hazard.ReportHazardActivity_;
        import com.app.qantas.domains.select_location.get_list_profile.SelectLocationActivity1_;
        import com.app.qantas.domains.select_profile.SelectProfileActivity_;
        import com.app.qantas.domains.select_site.SelectSiteActivity_;
        import com.app.qantas.infrastructures.ActivityModule;
        import com.app.qantas.models.LocationGeofences;
        import com.app.qantas.models.MapLauncherMode;
        import com.app.qantas.models.Profile;
        import com.app.qantas.models.ProfileLocation;
        import com.app.qantas.models.SignInLocation;
        import com.app.qantas.models.Site;
        import com.app.qantas.models.SiteSignBook;
        import com.app.qantas.models.responses.AuthResponse;
        import com.app.qantas.models.responses.ProfileLocationResponse;
        import com.app.qantas.utils.AppUtil;
        import com.app.qantas.widgets.AppFilterView;
        import com.app.qantas.widgets.AppToolbar;
        import com.app.qantas.widgets.KeyboardVisibility;
        import com.app.qantas.widgets.QantasSupportMapFragment;
        import com.balysv.materialripple.MaterialRippleLayout;
        import com.google.android.gms.maps.CameraUpdateFactory;
        import com.google.android.gms.maps.GoogleMap;
        import com.google.android.gms.maps.OnMapReadyCallback;
        import com.google.android.gms.maps.model.BitmapDescriptorFactory;
        import com.google.android.gms.maps.model.LatLng;
        import com.google.android.gms.maps.model.Marker;
        import com.google.android.gms.maps.model.MarkerOptions;
        import com.google.android.gms.maps.model.Polygon;
        import com.google.android.gms.maps.model.PolygonOptions;
        import com.google.maps.android.SphericalUtil;

        import org.androidannotations.annotations.AfterInject;
        import org.androidannotations.annotations.App;
        import org.androidannotations.annotations.Click;
        import org.androidannotations.annotations.EActivity;
        import org.androidannotations.annotations.Extra;
        import org.androidannotations.annotations.OnActivityResult;
        import org.androidannotations.annotations.SystemService;
        import org.androidannotations.annotations.ViewById;

        import java.util.ArrayList;
        import java.util.List;

        import javax.inject.Inject;

        import permissions.dispatcher.NeedsPermission;
        import permissions.dispatcher.OnNeverAskAgain;
        import permissions.dispatcher.OnPermissionDenied;
        import permissions.dispatcher.RuntimePermissions;
        import ru.terrakok.cicerone.Navigator;

        import static com.app.qantas.models.MapLauncherMode.NORMAL;
        import static com.app.qantas.models.MapLauncherMode.THE_FIRST;

@EActivity(R.layout.activity_main)
@RuntimePermissions
public class MainActivity extends BaseActivity<MainView, MainPresenter>
        implements MainView, OnMapReadyCallback, NavigationView.OnNavigationItemSelectedListener,
        GoogleMap.OnMarkerClickListener {

    public static final String TAG = MainActivity.class.getSimpleName();
    public static final int REQUEST_CODE_CHANGE_PROFILE = 1900;
    public static final int REQUEST_CODE_UPDATE_PROFILE = 1901;
    public static final int REQUEST_CODE_UPDATE_SET_TIME = 1902;
    public static final int REQUEST_CODE_CONFIRMATION_SIGN_IN = 1903;

    private long mLastPress;
    private static final long mBackPressThreshold = 4000;

    @App
    protected MainApplication application;
    @Inject
    protected MainPresenter presenter;
    @SystemService
    protected LayoutInflater layoutInflater;

    @ViewById(android.R.id.content)
    protected ViewGroup rootAndroidView;
    @ViewById(R.id.activity_main_drawer_layout)
    protected DrawerLayout drawerLayout;
    @ViewById(R.id.activity_main_cl_root)
    protected ConstraintLayout clRoot;
    @ViewById(R.id.activity_main_toolbar)
    protected AppToolbar toolbar;
    @ViewById(R.id.activity_main_nav_view)
    protected NavigationView navigationView;

    @ViewById(R.id.activity_main_ibtn_visibility)
    protected ImageButton ibtnVisibility;
    @ViewById(R.id.activity_main_ll_show_location_by)
    protected LinearLayout llShowLocationBy;
    @ViewById(R.id.activity_main_v_hide_show_location_by)
    protected View vHideShowLocationBy;
    @ViewById(R.id.activity_main_afv_filter)
    protected AppFilterView appFilterView;

    @ViewById(R.id.activity_main_btn_gps)
    protected ImageButton btnGps;
    @ViewById(R.id.activity_main_ll_action_bottom)
    protected LinearLayout llActionBottom;
    @ViewById(R.id.activity_main_abl_action_bar)
    protected LinearLayout ablActionBar;

    @ViewById(R.id.activity_main_tv_current_site_location)
    protected TextView tvCurrentSiteLocation;
    @ViewById(R.id.activity_main_iv_edit_set_time)
    protected ImageView ivSetTime;
    @ViewById(R.id.activity_main_mrl_edit_set_time)
    protected MaterialRippleLayout mrlSetTime;
    @ViewById(R.id.activity_main_tv_edit_set_time)
    protected TextView tvEditSetTime;

    @ViewById(R.id.layout_header_drawer_mrl_option)
    protected MaterialRippleLayout mrlOption;
    @ViewById(R.id.layout_header_drawer_mrl_header)
    protected MaterialRippleLayout mrlHeader;
    @ViewById(R.id.layout_header_drawer_tv_name)
    protected TextView tvProfileName;
    @ViewById(R.id.layout_header_drawer_tv_contractor)
    protected TextView tvProfileContractor;
    @ViewById(R.id.tv_network_state)
    protected TextView tvNetworkState;

    // extra
    @Extra
    protected MapLauncherMode mapLauncherMode = THE_FIRST;
    /*@Extra
    protected boolean needToShowSignIn = false;*/
    @Extra
    protected boolean isReselected = false;
    @Extra
    protected Profile selectedProfile;

    protected Site selectedSite;
    protected com.app.qantas.models.Location selectedLocation;

    @Extra
    protected Site siteTemp;
    @Extra
    protected com.app.qantas.models.Location locationTemp;

    protected QantasSupportMapFragment supportMapFragment;

    private AuthResponse authResponse;

    private LocationGooglePlayServicesWithFallbackProvider locationProvider;
    private Location currentLocation;
    private boolean hasGps = false;
    private ImageButton ibtnCloseDrawer;
    private GoogleMap googleMap;

    private List<Marker> markers = new ArrayList<>();
    private Marker ownerMarker;
    private List<Polygon> polygons = new ArrayList<>();

    private boolean reloadMap = true;

    // data object
    private List<ProfileLocation> profileLocationsByCoordinate = new ArrayList<>();
    private List<ProfileLocation> profileLocationsByProject = new ArrayList<>(); // list for searching
    private List<Site> sites = new ArrayList<>();

    private BroadcastReceiver countingReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "countingReceiver: start");
            //Toast.makeText(MainActivity.this, "hung", Toast.LENGTH_SHORT).show();

            hasGps = checkIfLocationOpened();
            if (currentLocation != null && hasGps && isNetworkAvailable) {
                Log.d(TAG, "countingReceiver: requested");
                //Toast.makeText(context, "countingReceiver: requested", Toast.LENGTH_SHORT).show();
                presenter.updateCurrentLocation(currentLocation.getLatitude(), currentLocation.getLongitude(), 0);
            } else {
                Log.d(TAG, "countingReceiver: not requested");
                //Toast.makeText(context, "countingReceiver: not requested", Toast.LENGTH_SHORT).show();
            }
        }
    };

    private boolean checkIfLocationOpened() {
        final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (manager.isProviderEnabled(LocationManager.GPS_PROVIDER) || manager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            return true;
        }
        // otherwise return false
        return false;
    }

    private BroadcastReceiver gpsReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "onReceive: start");
            if (intent != null) {
                if (intent.getAction().matches(LocationManager.PROVIDERS_CHANGED_ACTION)) {
                    LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                    if (manager != null) {
                        hasGps = manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
                        Log.d(TAG, "onReceive: hasGps = " + hasGps);
                        if (hasGps) {
                            updateLocationProvider();
                        } else {
                            Log.d(TAG, "turn of location");
                            if (currentLocation != null) {
                                presenter.updateCurrentLocation(currentLocation.getLatitude(), currentLocation.getLongitude(), 1);
                            }
                        }
                    }
                }
            }
        }
    };

    @AfterInject
    protected void initInject() {
        DaggerMainComponent.builder()
                .applicationComponent(application.getApplicationComponent())
                .activityModule(new ActivityModule(this))
                .build().inject(this);
    }

    @Override
    public void initViews() {
        super.initViews();

        // register necessary broadcasts & start the counting service
        startService(new Intent(this, UpdateLocationService_.class));
        registerReceiver(countingReceiver, new IntentFilter(UpdateLocationService.class.toString()));
        registerReceiver(gpsReceiver, new IntentFilter(LocationManager.PROVIDERS_CHANGED_ACTION));

        IntentFilter networkIntentFileter = new IntentFilter();
        networkIntentFileter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkIntentFileter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        registerReceiver(networkStateReceiver, networkIntentFileter);

        // update network state
        updateTextViewNetworkState();

        // get current User from local
        authResponse = presenter.getAuthentication();

        if (isReselected) {
            mapLauncherMode = NORMAL;
        }

        if (mapLauncherMode == THE_FIRST) {
            selectedSite = siteTemp;
            selectedLocation = locationTemp;
        }

        // check extra data (site & location)
        // these extra data is null if the app is opened in the second
        if (selectedSite == null) {
            selectedSite = authResponse.getSite();
        }
        if (selectedLocation == null) {
            selectedLocation = authResponse.getLocation();
        }
        if (selectedProfile == null) {
            selectedProfile = authResponse.getProfile();

        } else { // the first time launches maps or re-select profile
            if (isReselected) { // re-select profile
                if (selectedProfile.getSignInLocations() != null && selectedProfile.getSignInLocations().size() > 0) {
                    SignInLocation signInLocation = selectedProfile.getSignInLocations().get(0);
                    SiteSignBook siteSignBook = new SiteSignBook();
                    if (selectedProfile.isStaff()) {
                        siteSignBook.setIdStaff(selectedProfile.getId());
                    } else {
                        siteSignBook.setIdEmployee(selectedProfile.getId());
                    }
                    siteSignBook.setType(selectedProfile.getType());
                    siteSignBook.setDeviceCode(signInLocation.getDeviceCode());
                    siteSignBook.setTime(signInLocation.getSignInTime());
                    siteSignBook.setIdProject(signInLocation.getIdProject());
                    siteSignBook.setLocation(signInLocation.getLocation());
                    authResponse.setSiteSignBook(siteSignBook);
                }
            }
        }

        // save site & location & profile
        authResponse.setSite(selectedSite);
        authResponse.setLocation(selectedLocation);
        authResponse.setProfile(selectedProfile);

        if (mapLauncherMode == NORMAL && !isReselected) {
            //presenter.postCurrentProfile(selectedProfile);
            // check version app before set current profile
            presenter.checkAppVersion();
        } else {
            presenter.getProfileCurrentLocationByProject(selectedSite.getId());
        }

        // init Map component UI
        AppUtil.setTransparent(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ViewGroup.MarginLayoutParams layoutParamsGps = (ViewGroup.MarginLayoutParams) btnGps.getLayoutParams();
            layoutParamsGps.bottomMargin = AppUtil.convertDpToPixels(85f) + AppUtil.getHeightNavigationBar();
            btnGps.setLayoutParams(layoutParamsGps);
            ViewGroup.MarginLayoutParams layoutParamsActionBottom = (ViewGroup.MarginLayoutParams) llActionBottom.getLayoutParams();
            layoutParamsActionBottom.bottomMargin = AppUtil.convertDpToPixels(10f) + AppUtil.getHeightNavigationBar();
            btnGps.setLayoutParams(layoutParamsGps);

            ViewGroup.MarginLayoutParams layoutParamsShowLocationBy = (ViewGroup.MarginLayoutParams) llShowLocationBy.getLayoutParams();
            layoutParamsShowLocationBy.bottomMargin = AppUtil.getHeightNavigationBar();
            llShowLocationBy.setLayoutParams(layoutParamsShowLocationBy);
        }
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            RelativeLayout rlRootHeader = navigationView.getHeaderView(0).findViewById(R.id.layout_header_drawer_rl_root);
            rlRootHeader.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, AppUtil.convertDpToPixels(108f) - AppUtil.getHeightStatusBar()));
            ViewGroup.MarginLayoutParams layoutParamsActionBar = (ViewGroup.MarginLayoutParams) ablActionBar.getLayoutParams();
            layoutParamsActionBar.topMargin = AppUtil.convertDpToPixels(10f);
            ablActionBar.setLayoutParams(layoutParamsActionBar);
        } else {
            //ViewGroup.MarginLayoutParams layoutParamsActionBar = (ViewGroup.MarginLayoutParams) ablActionBar.getLayoutParams();
            //layoutParamsActionBar.topMargin = AppUtil.convertDpToPixels(10f) + AppUtil.getHeightStatusBar();
            //ablActionBar.setLayoutParams(layoutParamsActionBar);
        }

        if (navigationView != null) {
            mrlOption = navigationView.getHeaderView(0).findViewById(R.id.layout_header_drawer_mrl_option);
            mrlOption.setOnClickListener(v -> {
                SelectProfileActivity_.intent(this)
                        .isReSelected(true)
                        .startForResult(REQUEST_CODE_CHANGE_PROFILE);
            });
            mrlHeader = navigationView.getHeaderView(0).findViewById(R.id.layout_header_drawer_mrl_header);
            mrlHeader.setOnClickListener(v -> {
                ProfileLocation profileLocation = new ProfileLocation();
                profileLocation.setId(selectedProfile.getId());
                profileLocation.setName(selectedProfile.getName());
                profileLocation.setCompany(selectedProfile.getCompanyName());
                profileLocation.setType(selectedProfile.getType());

                ProfileActivity_.intent(this)
                        .profileLocation(profileLocation)
                        .startForResult(REQUEST_CODE_UPDATE_PROFILE);
            });
            tvProfileName = navigationView.getHeaderView(0).findViewById(R.id.layout_header_drawer_tv_name);
            tvProfileContractor = navigationView.getHeaderView(0).findViewById(R.id.layout_header_drawer_tv_contractor);

            tvProfileContractor.setText((authResponse != null && authResponse.getProfile() != null) ? authResponse.getProfile().getNameOrganisation() : "");
            tvProfileName.setText((authResponse != null && authResponse.getProfile() != null) ? authResponse.getProfile().getName() : "");
        }

        toolbar.setup(this, R.layout.layout_search_map, R.drawable.ic_menu, 0);
        toolbar.setColorTint(Color.parseColor("#575756"));
        toolbar.setOnLeftListener(v -> {
            drawerLayout.openDrawer(Gravity.START);
        });

        View.OnTouchListener onTouchListener = (view, motionEvent) -> {
            drawerLayout.openDrawer(Gravity.START);
            AppUtil.hideSoftInput(MainActivity.this, getCurrentFocus());
            uiHandler.postDelayed(() -> {
                if (!isFinishing()) {
                    toolbar.getLeftView().setOnTouchListener((View.OnTouchListener) toolbar.getLeftView().getTag());
                }
            }, 200);
            toolbar.getLeftView().setOnTouchListener(null);
            return false;
        };

        toolbar.getLeftView().setOnTouchListener(onTouchListener);
        toolbar.getLeftView().setTag(onTouchListener);

        navigationView.setNavigationItemSelectedListener(this);

        initViewCloseDrawer();

        if (reloadMap) {
            // load map async and get geofences by selected project
            uiHandler.postDelayed(() -> {
                supportMapFragment = new QantasSupportMapFragment();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.activity_main_fragment_map, supportMapFragment);
                ft.commitAllowingStateLoss(); // to prevent "can not perform this action after onsaveinstancestate"

                supportMapFragment.getMapAsync(this);
                supportMapFragment.setNonConsumingTouchListener(motionEvent -> {
                    AppUtil.hideSoftInput(MainActivity.this, getCurrentFocus());
                    AppUtil.clearFocus(MainActivity.this);
                    return false;
                });
            }, 500);
        }

        // setup botton sheet filter dialog
        appFilterView.setOnActionListener(new AppFilterView.OnActionListener() {
            @Override
            public void onFilterClose() {
                vHideShowLocationByClicked(null);
            }

            @Override
            public void onFilterSelected(int position) {
                refreshAllMarkersBySelectedPosition(position);
                vHideShowLocationByClicked(null);
            }
        });
        appFilterView.updateUIMode(selectedProfile.getType());

        /*if (sites != null && sites.size() > 0) {
            initAdapterSpinnerFilterAppView();
        }*/

        // init bottom sheet filter view
        updateFilterAppViewData();

        // request to grant permissions
        showPermissionInitialize();

        // init Map bottom (time on site and location)
        updateMapBottomButton();
    }

    private void updateMapBottomButton() {
        // init Map bottom (time on site and location)
        if (authResponse.getSiteSignBook() != null) {
            if (!TextUtils.isEmpty(authResponse.getSiteSignBook().getExpectedTimeToFinish())) {
                tvEditSetTime.setText(presenter.convertExpectedTime(authResponse.getSiteSignBook().getExpectedTimeToFinish(),
                        authResponse.getSiteSignBook().getTime()));
            }

            tvCurrentSiteLocation.setText(presenter.getCurrentSiteLocation
                    (authResponse.getSiteSignBook().getProjectName(), authResponse.getSiteSignBook().getLocation()));
            tvCurrentSiteLocation.setSelected(true);
        } else {
            tvEditSetTime.setText("00:00");

            /*tvCurrentSiteLocation.setText((authResponse != null
                    ? presenter.getCurrentSiteLocation(selectedSite, selectedLocation) : ""));*/
            tvCurrentSiteLocation.setText("--.--");
            tvCurrentSiteLocation.setSelected(true);
        }
    }

    @SuppressLint("MissingPermission")
    private void updateLocationProvider() {

        Log.d(TAG, "UPDATE LOCATION PROVIDER ");

        if (locationProvider == null) {
            locationProvider = new LocationGooglePlayServicesWithFallbackProvider(this);
        }

        AppLocationProvider.with(MainActivity.this)
                .location(locationProvider)
                .config(LocationConfig.BEST_EFFORT)
                .continuous().start(location -> {
            currentLocation = location;

            if (MainActivity.this.googleMap != null) {
                Log.d(TAG, "UPDATE LOCATION PROVIDER " + currentLocation.getLongitude());

                LatLng currentMarker = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
                if (ownerMarker != null) {
                    ownerMarker.remove();
                }
                //ownerMarker = addMarkerToMap(currentMarker, getResources().getColor(R.color.colorPrimary), "");
                MainActivity.this.googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentMarker, 15.0f));
            }
        });
    }

    private void refreshAllMarkersBySelectedPosition(int position) {
        Log.d(TAG, "refreshAllMarkersBySelectedPosition: " + System.currentTimeMillis());

        // remove marker to from Maps
        for (Marker marker : markers) {
            marker.remove();
        }

        // clear list markers data on Ram
        this.markers.clear();

        // re-draw marker with new text
        int i = 0;
        for (ProfileLocation pl : profileLocationsByCoordinate) {
            String text = "";
            // specific text by selected position
            switch (position) {
                case 0:
                    text = pl.getTimeOnProject();
                    break;
                case 1:
                    text = pl.getLocation();
                    break;
                case 2:
                    text = "No";
                    break;
                case 3:
                    text = pl.getLastLocationTimestamp();
                    break;
                case 4:
                    text = pl.getCompany();
                    break;
                case 5:
                    text = pl.getName();
                    break;
            }
            LatLng latLng = new LatLng(pl.getLatitude(), pl.getLongitude());
            Marker marker = addMarkerToMap(latLng, Color.RED, text);
            marker.setTag(i);
            this.markers.add(marker);
            i++;
        }
        Log.d(TAG, "refreshAllMarkersBySelectedPosition: " + System.currentTimeMillis());
    }

    private void initAdapterSpinnerFilterAppView() {
        if (sites != null && sites.size() > 0) {

            // convert sites to list string for showing dropdown list
            List<String> strings = new ArrayList<>();
            for (Site site : sites) {
                strings.add(site.getName());
            }

            Spinner snFilterSite = appFilterView.findViewById(R.id.sn_filter_site);
            ArrayAdapter filterSiteAdapter = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, strings);
            snFilterSite.setAdapter(filterSiteAdapter);
            snFilterSite.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // re-fresh data of profile searching list and re-draw polygon
                    selectedSite = sites.get(position);
                    authResponse.setSite(selectedSite);
                    presenter.getGeofencesByProject(selectedSite.getId());
                    presenter.getProfileCurrentLocationByProject(selectedSite.getId());
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
    }

    private void updateFilterAppViewData() {
        String[] values = new String[6];

        AuthResponse authResponse = presenter.getAuthResponseFromLocal();

        if (authResponse != null) {
            SiteSignBook siteSignBook = authResponse.getSiteSignBook();
            if (siteSignBook != null) {
                values[0] = presenter.getTimeOnSite(siteSignBook.getTime(), siteSignBook.getTimeZone());
                values[1] = siteSignBook.getLocation();
                values[2] = "No";
                values[3] = siteSignBook.getLastLocationTimeStamp();

                /*com.app.qantas.models.Location location = authResponse.getLocation();
                if (location != null) {
                    values[1] = location.getName();
                }*/
            } else { // reset data if not sign in
                values[0] = "";
                values[1] = "";
                values[2] = "";
                values[3] = "";
            }

            Profile profile = authResponse.getProfile();
            if (profile != null) {
                values[4] = profile.getNameOrganisation();
                values[5] = profile.getName();
            } else {// reset data if profile is empty
                values[4] = "";
                values[5] = "";
            }
        }

        appFilterView.setAfvValue(values);
    }

    @Override
    protected void onResume() {
        super.onResume();
        uiHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                presenter.checkAppVersion();
            }
        }, 5000);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //unregisterReceiver(countingReceiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(new Intent(this, UpdateLocationService_.class));
        unregisterReceiver(countingReceiver);
        unregisterReceiver(gpsReceiver);
    }

    @Click(R.id.activity_main_btn_gps)
    protected void btnGpsClicked() {
        if (currentLocation != null && this.googleMap != null) {
            LatLng currentMarker = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
            MainActivity.this.googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentMarker, 15));
        }
    }

    private void initViewCloseDrawer() {
        final FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.TOP | Gravity.RIGHT;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            params.topMargin = AppUtil.convertDpToPixels(42f) - AppUtil.getHeightStatusBar();
            params.rightMargin = AppUtil.convertDpToPixels(2f);
        } else {
            params.topMargin = AppUtil.convertDpToPixels(42f);
            params.rightMargin = AppUtil.convertDpToPixels(5f);
        }

        ibtnCloseDrawer = new ImageButton(this);
        ibtnCloseDrawer.setBackgroundColor(Color.TRANSPARENT);
        ibtnCloseDrawer.setImageResource(R.drawable.ic_close);
        rootAndroidView.addView(ibtnCloseDrawer, params);
        ibtnCloseDrawer.setVisibility(View.INVISIBLE);
        ibtnCloseDrawer.setOnClickListener(v -> {
            drawerLayout.closeDrawer(Gravity.START);
        });
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                ibtnCloseDrawer.setVisibility(slideOffset > 0 ? View.VISIBLE : View.INVISIBLE);
                ibtnCloseDrawer.setAlpha(slideOffset);
            }

            @Override
            public void onDrawerStateChanged(int newState) {
                super.onDrawerStateChanged(newState);
                updateSignInOutMenu();
            }
        });
    }

    @Override
    protected boolean isNetworkChecking() {
        return true;
    }

    @Override
    public Navigator getNavigator() {
        return super.getNavigator();
    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }

    @NonNull
    @Override
    public MainPresenter createPresenter() {
        return presenter;
    }

    @Override
    public void showLoading() {
        showProgressLoading();
    }

    @Override
    public void hideLoading() {
        hideProgressLoading();
    }

    @Override
    public void onGetLocationSuccessful(List<com.app.qantas.models.LatLng> latLngs) {
        Log.d(TAG, "onGetLocationSuccessful: ");
    }

    @Override
    public void onGetLocationFailed(Throwable throwable) {

    }

    @Override
    public void onPostSignInAndAddExpectedTimeSuccessful(int hour, int minute, Site site, com.app.qantas.models.Location location) {
        hideLoading();

        Toast.makeText(this, "Sign In success to " + location.getName(), Toast.LENGTH_LONG).show();

        selectedSite = siteTemp;
        selectedLocation = locationTemp;
        authResponse.setSite(siteTemp);
        authResponse.setLocation(locationTemp);

        //--Reload current site on bottom Maps
        tvCurrentSiteLocation.setText((authResponse != null
                ? presenter.getCurrentSiteLocation(authResponse.getSite(), authResponse.getLocation()) : ""));
        tvCurrentSiteLocation.setSelected(true);
        tvEditSetTime.setText(hour + ":" + minute);

        updateFilterAppViewData();
    }

    @Override
    public void addExpectedTimeSuccessful(int hour, int minute, Site site, com.app.qantas.models.Location location) {

        Toast.makeText(this, "Edit Time Success", Toast.LENGTH_LONG).show();

        //--Reload current site on bottom Maps
        tvCurrentSiteLocation.setText((authResponse != null
                ? presenter.getCurrentSiteLocation(authResponse.getSite(), authResponse.getLocation()) : ""));
        tvCurrentSiteLocation.setSelected(true);
        tvEditSetTime.setText(hour + ":" + minute);

        updateFilterAppViewData();
    }

    @Override
    public void onPostSignInAndAddExpectedTimeFailed(Throwable throwable) {

    }

    @Override
    public void addExpectedTimeFailed(Throwable throwable) {

    }

    @Override
    public void onGetGeofencesByProjectSuccessful(List<LocationGeofences> locationGeofences) {
        if (polygons != null) {
            for (Polygon p : polygons) {
                p.remove();
            }
        }
        // draw all polygons
        for (LocationGeofences lg : locationGeofences) {
            addPolygonToMap(lg);
        }
    }

    @Override
    public void onSubmitLocationSuccessful(List<com.app.qantas.models.Location> locations) {

    }

    @Override
    public void onSubmitLocationFailed(Throwable throwable) {

    }

    //--Begin Tuan Edit - Integrate Sign Out
    @Override
    public void onSignOutFailed(Throwable throwable) {
        Log.e(TAG, "SignOut Failed" + throwable.getMessage());
    }

    @Override
    public void onSignOutAThenSignInBSuccessful(SiteSignBook response, Site signInSite,
                                                com.app.qantas.models.Location signInLocation, int hour, int minute) {
        presenter.postSignInAndAddExpectedTime(hour, minute, signInSite, signInLocation, hasGps ? 0 : 1, currentLocation);
    }

    @Override
    public void onSignOutAThenSignInBFailed(Throwable throwable) {

    }


    @Override
    public void onSignOutSuccess(SiteSignBook response, Site site, com.app.qantas.models.Location location) {
        hideProgressLoading();

        Toast.makeText(this, "Sign out success", Toast.LENGTH_LONG).show();

        //--Change Sign In to Sign IN in Menu
        authResponse.setSiteSignBook(null);

        tvEditSetTime.setText("00:00");//Reset Time Stay in Location
        tvCurrentSiteLocation.setText("--.--");

        Log.d(TAG, "SignOut Success");

        updateFilterAppViewData();
    }
    //--End Tuan Edit

    @Override
    public void onPostSiteAndLocationSuccessful() {
        Log.d(TAG, "onPostSiteAndLocationSuccessful: ");
        //TODO: reload map with new Site
    }

    @Override
    public void onPostSiteAndLocationFailed(Throwable throwable) {

    }

    @Override
    public void onGetProfileLocationByCoordinateSuccess(ProfileLocationResponse response) {
        if (response != null) {
            profileLocationsByCoordinate.clear();
            profileLocationsByCoordinate.addAll(response.getProfileLocations());
            refreshAllMarkersBySelectedPosition(0);
        }
    }

    @Override
    public void onGetProfileLocationByCoordinateFailed(Throwable throwable) {

    }

    @Override
    public void onSubmitProfileAndGetAWSKeySuccessful(Profile profile) {
        // init bottom sheet filter view
        updateFilterAppViewData();
        updateMapBottomButton();
        presenter.getProfileCurrentLocationByProject(selectedSite.getId());
    }

    @Override
    public void onSubmitProfileAndGetAWSKeyFailed(Throwable throwable) {

    }

    @Override
    public void onGetProfileLocationByProjectSuccessful(ProfileLocationResponse response) {
        AutoCompleteTextView actSearch = toolbar.findViewById(R.id.act_search);
       /* ArrayList<ProfileLocation> profileLocations = new ArrayList<>();

        ProfileLocation profileLocation = new ProfileLocation();
        profileLocation.setId(1L);
        profileLocation.setName("name");
        profileLocation.setCompany("company");
        profileLocation.setDistance(10);

        ProfileLocation profileLocation1 = new ProfileLocation();
        profileLocation1.setId(1L);
        profileLocation1.setName("hung");
        profileLocation1.setCompany("company");
        profileLocation1.setDistance(10);

        profileLocations.add(profileLocation);
        profileLocations.add(profileLocation1);*/
        profileLocationsByProject = response.getProfileLocations();
        ArrayAdapter<ProfileLocation> arrayAdapter = new ProfileAutoCompleteAdapter(this, R.layout.layout_item_search_list, (ArrayList<ProfileLocation>) profileLocationsByProject);
        actSearch.setAdapter(arrayAdapter);
        actSearch.setOnItemClickListener((parent, view, position, id) -> {
            if (MainActivity.this.googleMap != null) {
                AppUtil.hideSoftInput(this, getCurrentFocus());
                AppUtil.clearFocus(this);
                ProfileLocation profileLocation = profileLocationsByProject.get(position);
                MainActivity.this.googleMap.animateCamera(CameraUpdateFactory.newLatLng
                        (new LatLng(profileLocation.getLatitude(), profileLocation.getLongitude())));
            }
        });
    }

    @Override
    public void onGetProfileLocationByProjectFailed(Throwable throwable) {

    }

    @Override
    public void onGetListSiteSuccessful(List<Site> response) {
        sites = response;
        initAdapterSpinnerFilterAppView();
    }

    @Override
    public void onGetListSiteFailed(Throwable throwable) {

    }

    @Override
    public void onMatchCurrentVersionApp() {
        // if version app is matched the flow will be run normally
        presenter.postCurrentProfile(selectedProfile);
    }

    @Override
    public void onOutOfDateVersionApp() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Title")
                .setMessage("Message")
                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final String appPackageName = getPackageName(); // get quantas package name
                        openMarketStore("com.google.android.googlequicksearchbox"); // harde code open google in store
                    }
                }).show();
    }

    @Override
    public void onGetVersionAppFailed(Throwable throwable) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        this.googleMap = googleMap;
        this.googleMap.getUiSettings().setMapToolbarEnabled(false);

        this.googleMap.setOnMarkerClickListener(this);
        this.googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        // set max min zoomable
        this.googleMap.setMaxZoomPreference(19.0f);
        this.googleMap.setMinZoomPreference(14.8f);

        // set default marker current user
        this.googleMap.getUiSettings().setCompassEnabled(true);
        this.googleMap.getUiSettings().setMyLocationButtonEnabled(false);

        this.googleMap.setOnCameraIdleListener(() -> {
            LatLng center = googleMap.getCameraPosition().target;
            LatLng topLeft = googleMap.getProjection().getVisibleRegion().farLeft;
            double distance = SphericalUtil.computeDistanceBetween(topLeft, center);
            if (authResponse.getProfile().isStaff()) {
                presenter.getProfileCurrentLocationByCoordinate(center.latitude, center.longitude, distance);
            }
        });

        if (this.googleMap != null) {
            if (ActivityCompat.checkSelfPermission
                    (this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            } else {
                this.googleMap.setMyLocationEnabled(true);
            }
        }

        //--Begin Edit Tuan- Zoom to current location after Maps load ready
        updateLocationProvider();
        //--End Edit

        // get all geofences by project id from server
        presenter.getGeofencesByProject(selectedSite.getId());
    }

    View vMarker;
    TextView tvContent;

    private Marker addMarkerToMap(LatLng latLng, int color, String text) {
        if (this.googleMap != null) {
            if (vMarker == null) {
                vMarker = layoutInflater.inflate(R.layout.layout_marker, null);
                ImageView ivLocation = vMarker.findViewById(R.id.layout_marker_iv_location);
                ivLocation.getDrawable().setColorFilter(color, PorterDuff.Mode.MULTIPLY);
                ivLocation.invalidate();
                tvContent = vMarker.findViewById(R.id.layout_marker_tv_content);

                // config view after converting to bitmap

            }
            tvContent.setText(text);

            Log.d(TAG, "vMarker width-height:" + vMarker.getMeasuredWidth() + "-" + vMarker.getMeasuredHeight());
            Bitmap bmMarker = AppUtil.createDrawableFromView(this, vMarker);
            Marker marker = this.googleMap.addMarker(new MarkerOptions()
                    .anchor((float) AppUtil.convertDpToPixels(10f) / bmMarker.getWidth(), 1)
                    .position(latLng)
                    .title("Title")
                    .snippet("Description")
                    .icon(BitmapDescriptorFactory.fromBitmap(bmMarker)));
            return marker;
        }
        return null;
    }

    private void addPolygonToMap(LocationGeofences locationGeofences) {
        List<LatLng> latLngs = presenter.convertListLocationGeoToLatLong(locationGeofences);
        Polygon polygon = this.googleMap.addPolygon(new PolygonOptions()
                .add(latLngs.toArray(new LatLng[latLngs.size()]))
                .strokeColor(Color.GRAY)
                .strokeWidth(0)
                .fillColor(Color.parseColor("#32800080"))
        );
        this.polygons.add(polygon);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        // close navigation when clicking
        drawerLayout.closeDrawer(Gravity.START);

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_map) {
            // Handle the camera action
        } else if (id == R.id.nav_list) {
            SelectLocationActivity1_.intent(this)
                    .isReSelected(false)
                    .selectedProfile(selectedProfile)
                    .site(selectedSite)
                    .start();
            return false;
        } else if (id == R.id.nav_notification) {
            NotificationActivity_.intent(this)
                    .start();
        } else if (id == R.id.nav_report_hazard) {
            ReportHazardActivity_.intent(this).start();
            return false;
        } else if (id == R.id.nav_manage_others) {
            ManageOthersActivity_.intent(this).start();
            return false;
        } else if (id == R.id.nav_signin) {

            //--Begin Tuan Edit
            authResponse = presenter.getAuthentication();

            //--If User signed in -> Menu change to Sign Out -> Click into Sign Out -> Sign Out Location
            if (authResponse.isSignIn() && authResponse.getSiteSignBook() != null) {
                com.app.qantas.models.Location location = new com.app.qantas.models.Location();
                location.setId(authResponse.getSiteSignBook().getIdLocation());
                location.setName(authResponse.getSiteSignBook().getLocation());
                location.setDeviceCode(authResponse.getSiteSignBook().getDeviceCode());

                Site siteSignedIn = new Site();
                siteSignedIn.setId(authResponse.getSiteSignBook().getIdProject());
                siteSignedIn.setTimeZone(authResponse.getSiteSignBook().getTimeZone());

                if (isNetworkAvailable) {
                    presenter.postSignOut(siteSignedIn, location, hasGps ? 0 : 1, currentLocation);
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("the internet is not available")
                            .setNegativeButton("OK", null).show();
                    updateTextViewNetworkState();
                }
            } else {
                SelectSiteActivity_.intent(this).isReSelected(true).start();
            }

            //--End Tuan Edit

            return false;
        }
        return true;
    }


    @NeedsPermission({Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    protected void showPermissionInitialize() {
        // check whether show SignInPopup after granted permission or NOT
        if (isReselected) {
            showSignInPopup();
        } else {
            if (!authResponse.getProfile().isStaff() && authResponse.getSiteSignBook() == null && mapLauncherMode == THE_FIRST) {
                showSignInPopup();
            }
        }

        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        if (this.googleMap != null) {
            this.googleMap.setMyLocationEnabled(true);
        }

        updateLocationProvider();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (locationProvider != null) {
            locationProvider.onActivityResult(requestCode, resultCode, data);
        }
    }

    @OnPermissionDenied({Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION})
    void onPermissionDenied() {

    }

    @OnNeverAskAgain({Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION})
    void onPermissionNeverAskAgain() {

    }

    @Click(R.id.activity_main_ibtn_visibility)
    protected void iBtnVisibilityClicked(View view) {
        AppUtil.hideSoftInput(this, getCurrentFocus());
        AppUtil.clearFocus(this);
        Animation bottomUp = AnimationUtils.loadAnimation(this, R.anim.bottom_up);
        bottomUp.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                ibtnVisibility.setEnabled(false);
                vHideShowLocationBy.setEnabled(false);
                vHideShowLocationBy.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ibtnVisibility.setEnabled(true);
                vHideShowLocationBy.setEnabled(true);
                vHideShowLocationBy.setVisibility(View.VISIBLE);
                AlphaAnimation animationAlpha = new AlphaAnimation(0.0f, 1.0f);
                animationAlpha.setDuration(100);
                animationAlpha.setFillAfter(true);
                vHideShowLocationBy.startAnimation(animationAlpha);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        llShowLocationBy.startAnimation(bottomUp);
        llShowLocationBy.setVisibility(View.VISIBLE);

        if (sites != null && sites.size() > 0) {
            //initAdapterSpinnerFilterAppView();
        } else {
            if (authResponse.getProfile().isStaff()) {
                presenter.getListSite();
            }
        }
    }

    @Click(R.id.activity_main_v_hide_show_location_by)
    protected void vHideShowLocationByClicked(View view) { // appfilter
        updateFilterAppViewData();
        AppUtil.hideSoftInput(this, getCurrentFocus());
        AppUtil.clearFocus(this);
        llShowLocationBy.setVisibility(View.GONE);
        Animation bottomDown = AnimationUtils.loadAnimation(this, R.anim.bottom_down);
        bottomDown.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                ibtnVisibility.setEnabled(false);
                vHideShowLocationBy.setEnabled(false);
                AlphaAnimation animationAlpha = new AlphaAnimation(1.0f, 0.0f);
                animationAlpha.setDuration(100);
                animationAlpha.setFillAfter(true);
                vHideShowLocationBy.startAnimation(animationAlpha);
                vHideShowLocationBy.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ibtnVisibility.setEnabled(true);
                vHideShowLocationBy.setEnabled(true);
                vHideShowLocationBy.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        llShowLocationBy.startAnimation(bottomDown);
        llShowLocationBy.setVisibility(View.GONE);
    }


    @Click({R.id.activity_main_iv_edit_set_time, R.id.activity_main_mrl_edit_set_time})
    protected void ivEditSetTimeClicked(View view) {
        AppUtil.hideSoftInput(this, getCurrentFocus());
        AppUtil.clearFocus(this);

        //--Begin Tuan Fix
        if (authResponse.getSiteSignBook() != null) {
            showEditTimePopup();
        }

//        SelectSiteActivity_.intent(this).isReSelected(true).start();
        //--End Tuan Fix
    }

    private void showSignInPopup() {
        Log.d(TAG, "showSignInPopup: ");
        AppUtil.hideSoftInput(this, getCurrentFocus());
        AppUtil.clearFocus(this);
        DialogSetTimeOnSite_
                .intent(this)
                .selectedSite(siteTemp)
                .selectedLocation(locationTemp)
                .isEdit(false)
                .startForResult(REQUEST_CODE_UPDATE_SET_TIME);
    }

    private void showEditTimePopup() {
        Log.d(TAG, "showEditTimePopup: ");
        AppUtil.hideSoftInput(this, getCurrentFocus());
        AppUtil.clearFocus(this);

        com.app.qantas.models.Location location = new com.app.qantas.models.Location();
        location.setName(authResponse.getSiteSignBook().getLocation());
        location.setId(authResponse.getSiteSignBook().getIdLocation());
        location.setDeviceCode(authResponse.getSiteSignBook().getDeviceCode());

        Site site = new Site();
        site.setName(authResponse.getSiteSignBook().getProjectName());
        site.setId(authResponse.getSiteSignBook().getIdProject());
        DialogSetTimeOnSite_
                .intent(this)
                .selectedSite(site)
                .selectedLocation(location)
                .isEdit(true)
                .startForResult(REQUEST_CODE_UPDATE_SET_TIME);
    }

    @OnActivityResult(REQUEST_CODE_CONFIRMATION_SIGN_IN)
    protected void onActivityResultConfirmationSignIn(
            int resultCode, @OnActivityResult.Extra(value = DialogAlertConfirmationSignIn.KEY_EXTRA_LEFT) boolean option1,
            @OnActivityResult.Extra(value = DialogAlertConfirmationSignIn.KEY_EXTRA_RIGHT) boolean option2,
            @OnActivityResult.Extra(value = DialogAlertConfirmationSignIn.KEY_EXTRA_HOUR) int hour,
            @OnActivityResult.Extra(value = DialogAlertConfirmationSignIn.KEY_EXTRA_MINUTE) int minute) {
        if (resultCode == RESULT_OK) {
            if (option1) { // sign out together
                if (authResponse.getSiteSignBook() != null) {
                    com.app.qantas.models.Location location = new com.app.qantas.models.Location();
                    location.setName(authResponse.getSiteSignBook().getLocation());
                    location.setId(authResponse.getSiteSignBook().getIdLocation());
                    location.setDeviceCode(authResponse.getSiteSignBook().getDeviceCode());

                    Site siteSignedIn = new Site();
                    siteSignedIn.setId(authResponse.getSiteSignBook().getIdProject());
                    siteSignedIn.setTimeZone(authResponse.getSiteSignBook().getTimeZone());

                    presenter.postSignOut(siteSignedIn, location, hasGps ? 0 : 1, currentLocation);
                }
            }

            if (option2) { // sign in A and sign out B
                com.app.qantas.models.Location locationSignedIN = new com.app.qantas.models.Location();
                locationSignedIN.setId(authResponse.getSiteSignBook().getIdLocation());
                locationSignedIN.setName(authResponse.getSiteSignBook().getLocation());
                locationSignedIN.setDeviceCode(authResponse.getSiteSignBook().getDeviceCode());

                Site siteSignedIn = new Site();
                siteSignedIn.setId(authResponse.getSiteSignBook().getIdProject());
                siteSignedIn.setTimeZone(authResponse.getSiteSignBook().getTimeZone());

                presenter.signOutAThenSignInB(siteSignedIn, locationSignedIN, selectedSite, selectedLocation, hour, minute,
                        hasGps ? 0 : 1, currentLocation);
            }
        }
    }

    @OnActivityResult(REQUEST_CODE_UPDATE_SET_TIME)
    protected void onEditSetTimeResult(int resultCode,
                                       @OnActivityResult.Extra(value = DialogSetTimeOnSite.IS_EDIT_EXTRA_KEY) boolean isEdit,
                                       @OnActivityResult.Extra(value = DialogSetTimeOnSite.HOUR_EXTRA_KEY) int hour,
                                       @OnActivityResult.Extra(value = DialogSetTimeOnSite.MINUTE_EXTRA_KEY) int minute) {
        if (resultCode == Activity.RESULT_OK) {
            if (isEdit) {
                if (authResponse.isSignIn()) {
                    SiteSignBook siteSignBook = authResponse.getSiteSignBook();
                    Site site = new Site();
                    site.setId(siteSignBook.getIdProject());
                    site.setTimeZone(siteSignBook.getTimeZone());
                    com.app.qantas.models.Location location = new com.app.qantas.models.Location();
                    location.setId(siteSignBook.getIdLocation());
                    presenter.addExpectedTime(hour, minute, site, location);
                }
            } else {
                if (authResponse.isSignIn()) {

                    //--modify by Hung
                    //--Start
                    //--not must show confirmation dialog. SignOut A then SignIn B instead
              /*  DialogAlertConfirmationSignIn_.intent(this)
                        .title(getString(R.string.dialog_confirmation_title))
                        .content(String.format("%s is already signed in to %s", authResponse.getProfileDetail().getName(),
                                authResponse.getSiteSignBook().getLocation()))//--Tuan Edit
                        .txtLeft("Sign Out Together")//--Tuan Edit
                        .txtRight(String.format("Sign Out %s and IN to %s", authResponse.getSiteSignBook().getLocation(),
                                selectedLocation.getName()))//--Tuan Edit
                        .hour(hour)
                        .minute(minute)
                        .startForResult(REQUEST_CODE_CONFIRMATION_SIGN_IN);*/

                    com.app.qantas.models.Location locationSignedIn = new com.app.qantas.models.Location();
                    locationSignedIn.setId(authResponse.getSiteSignBook().getIdLocation());
                    locationSignedIn.setName(authResponse.getSiteSignBook().getLocation());
                    locationSignedIn.setDeviceCode(authResponse.getSiteSignBook().getDeviceCode());


                    Site siteSignedIn = new Site();
                    siteSignedIn.setId(authResponse.getSiteSignBook().getIdProject());
                    siteSignedIn.setTimeZone(authResponse.getSiteSignBook().getTimeZone());

                    presenter.signOutAThenSignInB(siteSignedIn, locationSignedIn, siteTemp, locationTemp,
                            hour, minute, hasGps ? 0 : 1, currentLocation);
                    //--End
                } else {
                    presenter.postSignInAndAddExpectedTime(hour, minute, siteTemp, locationTemp, hasGps ? 0 : 1, currentLocation);
                }
            }
        }
    }

    @Click({R.id.activity_main_iv_edit_site_location, R.id.activity_main_mrl_edit_site_location})
    protected void ivEditSiteLocationClicked(View view) {
        AppUtil.hideSoftInput(this, getCurrentFocus());
        AppUtil.clearFocus(this);
        SelectSiteActivity_.intent(this).isReSelected(true).start();
    }

    private void updateSignInOutMenu() {
        if (navigationView != null) {
            Menu navMenu = navigationView.getMenu();
            authResponse = presenter.getAuthentication();
            MenuItem menuItem = navMenu.findItem(R.id.nav_signin);
            if (authResponse.isSignIn()) {
                menuItem.setTitle("SIGN OUT");
                menuItem.setIcon(R.drawable.ic_signout);
            } else {
                menuItem.setTitle("SIGN IN");
                menuItem.setIcon(R.drawable.ic_signin);
            }
        }
    }

    @Override
    public void onBackPressed() {
        long currentTime = System.currentTimeMillis();
        //Toast.makeText(this, getResources().getString(R.string.press_back_again_to_exit), Toast.LENGTH_SHORT).show();
        if (Math.abs(currentTime - mLastPress) > mBackPressThreshold) {
            mLastPress = currentTime;
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        reloadMap = false;
        initViews();
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        if (marker.equals(ownerMarker)) {
            // move camera to the clicked marker
            this.googleMap.animateCamera(CameraUpdateFactory.newLatLng(marker.getPosition()));

           /* ProfileActivity_.intent(this)
                    .id(authResponse.getProfileDetail().getId())
                    .role(selectedProfile.getType())
                    .startForResult(REQUEST_CODE_UPDATE_PROFILE);*/
            return true;
        }

        if (profileLocationsByCoordinate != null && profileLocationsByCoordinate.size() > 0) {

            // move camera to the clicked marker
            this.googleMap.animateCamera(CameraUpdateFactory.newLatLng(marker.getPosition()));

            int tag = (int) marker.getTag();
            ProfileActivity_.intent(this)
                    .profileLocation(profileLocationsByCoordinate.get(tag))
                    .startForResult(REQUEST_CODE_UPDATE_PROFILE);
        }
        return true;
    }

    /*Broadcast*/
    BroadcastReceiver networkStateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            handlerNetworkChange(context, intent);
        }
    };

    boolean isNetworkAvailable = false;

    public void handlerNetworkChange(Context context, Intent intent) {
        Log.d(TAG, "f1: ");

        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        int extraWifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE,
                WifiManager.WIFI_STATE_UNKNOWN);
        Log.d(TAG, "wifi = off");
        switch (extraWifiState) {
            case WifiManager.WIFI_STATE_DISABLED:
            case WifiManager.WIFI_STATE_DISABLING:
                Log.d(TAG, "wifi = off");
                isNetworkAvailable = false;
                updateTextViewNetworkState();
                break;
            case WifiManager.WIFI_STATE_ENABLING:
            case WifiManager.WIFI_STATE_ENABLED:
                Log.d(TAG, "wifi = on");
            case WifiManager.WIFI_STATE_UNKNOWN:
                Log.d(TAG, "wifi = unknow");
                if (activeNetwork != null) {
                    Log.d(TAG, "activeNetwork != null");
                    boolean isConnected = activeNetwork.isConnected();
                    if (isConnected) {
                        Log.d(TAG, "isConnected: ");
                        isNetworkAvailable = true;
                        updateTextViewNetworkState();
                    } else {
                        Log.d(TAG, "is not Connected: ");
                        isNetworkAvailable = false;
                        updateTextViewNetworkState();
                    }

                   /* NetworkInfo.State state = activeNetwork.getState();
                    Log.d(TAG, "state = : " + state);*/
                }

                break;
        }
    }

    CountDownTimer timer = new CountDownTimer(10000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {

        }

        @Override
        public void onFinish() {
            tvNetworkState.setVisibility(View.GONE);
        }
    };

    private void updateTextViewNetworkState() {
        tvNetworkState.setVisibility(View.VISIBLE);
        timer.cancel();
        timer.start();
        if (isNetworkAvailable) {
            tvNetworkState.setText("Updating data...");
        } else {
            tvNetworkState.setText("Waiting connection...");
        }
    }

    public void openMarketStore(String appPackageName) {
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
        } catch (android.content.ActivityNotFoundException anfe) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
        }
    }
}
