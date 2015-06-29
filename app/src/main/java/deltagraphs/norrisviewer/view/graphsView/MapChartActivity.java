package deltagraphs.norrisviewer.view.graphsView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;


import java.util.ArrayList;
import java.util.List;

import deltagraphs.norrisviewer.R;
import deltagraphs.norrisviewer.model.flowModel.FlowModel;
import deltagraphs.norrisviewer.model.flowModel.MapChartFlow;
import deltagraphs.norrisviewer.presenter.Convert;
import deltagraphs.norrisviewer.presenter.graphsPresenter.MapChartPresenter;
import deltagraphs.norrisviewer.presenter.graphsPresenter.MapChartPresenterImpl;

/*
 * Name : MapChartActivity.java
 * Module : norrisviewer::view::graphsView
 * Location : norrisviewer\view\graphsView
 *
 * History :

 * Version Date Programmer Description
 * ===============================================================
 *
 * 0.5.0 2015-06-09 Davide Trivellato Coding of method setData(ArrayList<FlowModel> flowList, String signal)
 *
 * 0.4.0 2015-06-07 Davide Trivellato Coding of method setPolyline(ArrayList<LatLng> polyline, String color)
 *
 * 0.3.0 2015-06-06 Davide Trivellato Set zoom and camera position
 *
 * 0.2.1 2015-06-06 Davide Trivellato Fixed markers colors
 *
 * 0.2.0 2015-06-05 Davide Trivellato Set customization for markers
 *
 * 0.1.0 2015-06-04 Davide Trivellato Coding of methods and attributes
 *
 * 0.0.1 2015-06-04 Davide Trivellato Creation of the file
 *
 * ===============================================================
 *
 */

public class MapChartActivity extends ActionBarActivity implements OnMapReadyCallback, MapChartView {

    private GoogleMap map; // Might be null if Google Play services APK is not available.
    private MapChartPresenter mapChartPresenter;
    private String sourceURL;
    private String sourceTitle;
    private LatLng center;
    private boolean hasLegend = true;
    private List<Marker> markers;

    /*
    This method is called on the creation of the activity
    and its job is to get information from
    previous activity to get title and URL.
     It also initialize the map and the marker's list.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_chart);
        Bundle extras = getIntent().getExtras();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (extras != null) {
            sourceURL = extras.getString("EXTRA_SOURCE_URL");
            sourceTitle = extras.getString("EXTRA_SOURCE_TITLE");
        }
        setTitle(sourceTitle);
        markers = new ArrayList<Marker>();
        setUpMapIfNeeded();
        //mapChartPresenter = new MapChartPresenterImpl(this, sourceURL);
        Context context = getApplicationContext();
        CharSequence text = "Loading may take a few moments...";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    /*
    This method is called when the activity is show back form a pause state.
     */
    @Override
    public void onResume() {
        super.onResume();
        mapChartPresenter = new MapChartPresenterImpl(this, sourceURL);
        mapChartPresenter.startListening();
    }

    //manage the onStop event
    @Override
    public void onStop() {
        super.onStop();
        mapChartPresenter.stopListening();
    }

    //manage the onPause event
    @Override
    public void onPause() {
        super.onPause();
    }

    //manage the onRestart event
    @Override
    public void onRestart() {
        super.onRestart();
        mapChartPresenter.startListening();
    }

    //manage the onDestroy event
    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    /*
    This method istantiates the map if
    it has not been already initialized.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (map == null) {
            // Try to obtain the map from the SupportMapFragment.
            map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
        }
    }

    /*
    This method creates a new marker or updates an existing one.
    It belongs to an id, a pair of coordinates, a type with a property and
    optionally a color.
    */
    private Marker addMapMarker(String id, float lat, float lng, String type, String property, String color) {
        MarkerOptions mMarkerOptions = newMarkerOpt(id, lat, lng, type, property, color);
        setUpMapIfNeeded();
        removeMarker(id);
        Marker m = map.addMarker(mMarkerOptions);
        markers.add(m);
        return m;
    }

    /*
    This method delete a marker if it exists.
    It also remove the marker from the arrayList.
     */
    private void removeMarker(String id) {
        for (int i = 0; i < markers.size(); i++) {
            //if in ID already exists, it's removed from the list
            if (markers.get(i).getTitle().equals(id)) {
                markers.get(i).remove();
                markers.remove(i);
            }
        }
    }

    /*
    This method sets the position of the camera
    on the Google map.
     */
    public void cameraPosition(float lat, float lng) {
        center = new LatLng(lat, lng);
        map.moveCamera(CameraUpdateFactory.newLatLng(center));
    }

    /*
    This method parse a color and tranform it from a string to a float type.
     */
    private float getHueFromString(String c) {
        String color = c;

        int r = Integer.parseInt(color.substring(1, 3), 16); // Grab the hex representation of red (chars 1-2) and convert to decimal (base 10).
        int g = Integer.parseInt(color.substring(3, 5), 16);
        int b = Integer.parseInt(color.substring(5, 7), 16);

        float hue = (float) Convert.rgbToHsl(r, g, b).h * 360;
        return hue;
    }


    /*
    This method create a custom marker with its own options.
     */
    private MarkerOptions newMarkerOpt(String id, float lat, float lng, String type, String property, String color) {

        MarkerOptions m = new MarkerOptions();
        m.position(new LatLng(lat, lng));
        m.title(id);
        if(!hasLegend){

        }
        switch (type) {
            // shape marker
            case "shape":
                switch (property) {
                    //normal marker
                    case "normal":
                        float hue = getHueFromString(color);
                        m.icon(BitmapDescriptorFactory.defaultMarker(hue));
                    case "circle":
                        m.icon(BitmapDescriptorFactory.fromResource(R.mipmap.red_circle_marker));
                        break;
                    case "bus":
                        m.icon(BitmapDescriptorFactory.fromResource(R.mipmap.bus_marker));
                        break;
                    case "flag":
                        m.icon(BitmapDescriptorFactory.fromResource(R.mipmap.flag_marker));
                        break;
                    case "car":
                        m.icon(BitmapDescriptorFactory.fromResource(R.mipmap.car_marker));
                        break;
                    case "house":
                        m.icon(BitmapDescriptorFactory.fromResource(R.mipmap.house_marker));
                        break;
                    case "man":
                        m.icon(BitmapDescriptorFactory.fromResource(R.mipmap.man_marker));
                        break;
                    case "woman":
                        m.icon(BitmapDescriptorFactory.fromResource(R.mipmap.woman_marker));
                        break;
                }
                break;

            case "text":
                Bitmap.Config conf = Bitmap.Config.ARGB_8888;
                Bitmap bmp = Bitmap.createBitmap(300, 70, conf);
                Canvas canvas = new Canvas(bmp);
                Paint paint = new Paint();
                paint.setColor(Color.parseColor(color));
                paint.setTextSize(46);
                canvas.drawText(property, 0, 50, paint); // paint defines the text color, stroke width, size
                m.icon(BitmapDescriptorFactory.fromBitmap(bmp));
                break;

            default:
                m.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                break;
        }
        return m;
    }

    /*
    This method menages the trace and its options
    like the color.
     */
    public Polyline setPolyline(ArrayList<LatLng> polyline, String color) {

        PolylineOptions mPolylineOptions = new PolylineOptions();

        mPolylineOptions.width(8);

        if (color == "default")
            mPolylineOptions.color(Color.RED);
        else
            mPolylineOptions.color(Color.parseColor(color));

        for (int i = 0; i < polyline.size(); i++) {
            mPolylineOptions.add(polyline.get(i));
        }
        Polyline mPolyline = map.addPolyline(mPolylineOptions);
        return mPolyline;
    }

    /*
    The followiong method set the zoom on the map and move the camere to the center position of the map zoomed
     */
    public void setZoom(float width, float height) {
        if (center != null) {
            double[] ref = getBoundingBox(center.latitude, center.longitude, (int) width, (int) height);
            LatLngBounds bounds = new LatLngBounds(new LatLng(ref[0], ref[1]), new LatLng(ref[2], ref[3]));
            map.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 0));
            map.animateCamera(CameraUpdateFactory.zoomIn());
        }
    }

    /* This method provides an algorithm that calculate
    the bounding box of the zoomed map */
    private double[] getBoundingBox(final double pLatitude, final double pLongitude, final int widthInMeters, final int heightInMeters) {

        double[] boundingBox = new double[4];

        final double latRadian = Math.toRadians(pLatitude);

        final double degLatKm = 110.574235;
        final double degLongKm = 110.572833 * Math.cos(latRadian);
        final double deltaLat = widthInMeters / 1000.0 / degLatKm;
        final double deltaLong = heightInMeters / 1000.0 / degLongKm;

        final double minLat = pLatitude - deltaLat;
        final double minLong = pLongitude - deltaLong;
        final double maxLat = pLatitude + deltaLat;
        final double maxLong = pLongitude + deltaLong;

        boundingBox[0] = minLat;
        boundingBox[1] = minLong;
        boundingBox[2] = maxLat;
        boundingBox[3] = maxLong;

        return boundingBox;
    }

    /*
    This method set the type of the map.
    Possible types are:
        -road map
        -satellite map
        -hybrid map
        -terrain map
     */
    @Override
    public void setMapType(String type) {
        //satellite, hybrid, terrain
        switch (type) {
            case "roadmap":
                map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                break;
            case "satellite":
                map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                break;
            case "hybrid":
                map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                break;
            case "terrain":
                map.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                break;
            default:
                map.setMapType(GoogleMap.MAP_TYPE_NONE);
                break;
        }
    }

    /*
    This method enable or disable the legend on point.
     */
    @Override
    public void setLegendOnPoint(Boolean legend) {
        if(legend == false)
            map.setOnMarkerClickListener(null);
        hasLegend = legend;
    }

    /*
    This is the main method of the class.
    The method set the data on the view when
    an update has arrived.
     */
    @Override
    public void setData(ArrayList<FlowModel> flowList, String signal) {
        boolean isMapCleared = false;
        for (int i = 0; i < flowList.size(); i++) {

            flowList.get(i).getFlowId();
            String idLine = flowList.get(i).getFlowName();

            MapChartFlow mapChartFlow = (MapChartFlow) flowList.get(i);
            if(mapChartFlow.isTraceUpdated() == true) {
                if(isMapCleared == false) {
                    map.clear();
                    isMapCleared = true;
                }
                String polyLineColour = mapChartFlow.getTraceStrokeColour();
                setPolyline(mapChartFlow.getTraceCoords(), polyLineColour);
            }
            String markerType = mapChartFlow.getMarkerType();
            String markerProperty = mapChartFlow.getMarkerProperty(markerType);
            String color = mapChartFlow.getMarkerColour();

            for (int j = 0; j < mapChartFlow.getRecordSize(); j++) {
                String id = idLine + " - " + mapChartFlow.getRecordMarkerId(j);
                float lat = mapChartFlow.getRecordLatitude(j);
                float lng = mapChartFlow.getRecordLongitude(j);
                String recordId = mapChartFlow.getRecordId(j);
                addMapMarker(id, lat, lng, markerType, markerProperty, color);
            }
        }
    }

    /* This method generate a custom menu for the
    action bar on the top of the activity  */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_map_chart, menu);
        return true;
    }

    /* This method menage the actions user can perform
    on the menu */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_credits) {
            Context context = getApplicationContext();
            CharSequence text = "Credits to DeltaGraphs 2015";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
}
