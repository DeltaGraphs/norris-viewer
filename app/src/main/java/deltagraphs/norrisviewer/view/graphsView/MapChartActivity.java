package deltagraphs.norrisviewer.view.graphsView;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

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

public class MapChartActivity extends ActionBarActivity implements OnMapReadyCallback, MapChartView {

    private GoogleMap map; // Might be null if Google Play services APK is not available.
    private MapChartPresenter mapChartPresenter;
    private String sourceURL;
    private String sourceTitle;
    LatLng center;
    private boolean hasLegend = true;
    private List<Marker> markers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_chart);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            sourceURL = extras.getString("EXTRA_SOURCE_URL");
            sourceTitle = extras.getString("EXTRA_SOURCE_TITLE");
        }
        setTitle(sourceTitle);
        mapChartPresenter = new MapChartPresenterImpl(this, sourceURL);
        markers = new ArrayList<Marker>();
        setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (map == null) {
            // Try to obtain the map from the SupportMapFragment.
            map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
        }
    }

    @Override
    public void onMapReady(GoogleMap mMap) {
    }

    public Marker addMapMarker(String id, float lat, float lng, String type, String property, String color){
        MarkerOptions mMarkerOptions = newMarker(id, lat, lng, type, property, color);
        setUpMapIfNeeded();
        Marker m = map.addMarker(mMarkerOptions);
        markers.add(m);
        return m;
    }

    public void removeMarker(String id){
        for(int i=0; i < markers.size(); i++) {
            //se gli Id sono uguali lo elimina dalla lista
            if(markers.get(i).getId() == id) {
                markers.get(i).remove();
                markers.remove(i);
            }
        }
    }

    public Marker updateMarkerPosition(String id, float lat, float lng, String type, String property, String color){
        removeMarker(id);
        Marker m = addMapMarker(id, lat, lng, type, property, color);
        return m;
    }

    public void cameraPosition(float lat, float lng){
        center = new LatLng(lat, lng);
        map.moveCamera(CameraUpdateFactory.newLatLng(center));
    }

    public float getHueFromString(String c){
        String color = c;

        int r = Integer.parseInt(color.substring(1, 3), 16); // Grab the hex representation of red (chars 1-2) and convert to decimal (base 10).
        int g = Integer.parseInt(color.substring(3, 5), 16);
        int b = Integer.parseInt(color.substring(5, 7), 16);

        float hue = (float)Convert.rgbToHsl(r, g, b).h * 360;
        return hue;
    }


    public MarkerOptions newMarker(String id, float lat, float lng, String type, String property, String color){

        MarkerOptions m = new MarkerOptions();
        m.position(new LatLng(lat, lng));
        m.title(id);
        if(hasLegend){
        }
        switch (type) {
            // shape marker
            case "shape":
                switch(property) {
                    //normal marker
                    case "normal":
                        float hue = getHueFromString(color);
                        m.icon(BitmapDescriptorFactory.defaultMarker(hue));
                    case "circle":
                        m.icon(BitmapDescriptorFactory.fromResource(R.mipmap.red_circle_marker));
                        break;
                }
                break;

            case "icon":
                switch (property){
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

            default:
                m.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                break;
        }
        return m;
    }

    //Gestione della traccia

    public Polyline setPolyline(ArrayList<LatLng> polyline, String color){

        PolylineOptions mPolylineOptions = new PolylineOptions()
                .width(8)
                .color(Color.parseColor(color));

        for(int i=0; i < polyline.size(); i++){
            mPolylineOptions.add(polyline.get(i));
        }
        Polyline mPolyline = map.addPolyline(mPolylineOptions);
        return mPolyline;
    }

    //Gestione del tipo della mappa

    public void setType(String type){
        //satellite, hybrid, terrain
        switch (type) {
            case "roadMap":
                map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            case "satellite":
                map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            case "hybrid":
                map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            case "terrain":
                map.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
            default:
                map.setMapType(GoogleMap.MAP_TYPE_NONE);
        }
    }


    public void setZoom(float width, float height){
        if(center!= null) {
            double[] ref = getBoundingBox(center.latitude, center.longitude, (int)width, (int)height);
            LatLngBounds bounds = new LatLngBounds(new LatLng(ref[0], ref[1]), new LatLng(ref[2], ref[3]));
            map.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 0));
            map.animateCamera(CameraUpdateFactory.zoomIn());
        }
    }

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

    @Override
    public void setMapType(String type) {
    }

    @Override
    public void setLegendOnPoint(Boolean legend) {
        hasLegend = legend;
    }

    @Override
    public void setData(ArrayList<FlowModel> flowList, String signal) {
        //switch (signal) {
            //case "configGraph":
                map.clear();
                for (int i = 0; i < flowList.size(); i++) {

                    flowList.get(i).getFlowId();
                    flowList.get(i).getFlowName();

                    MapChartFlow mapChartFlow = (MapChartFlow) flowList.get(i);

                    setPolyline(mapChartFlow.getTraceCoords(),mapChartFlow.getTraceStrokeColour());

                    mapChartFlow.getMaxItems();
                    String markerType = mapChartFlow.getMarkerType();
                    String markerProperty = mapChartFlow.getMarkerProperty(markerType);
                    String color = mapChartFlow.getMarkerColour();
                    for (int j = 0; j < mapChartFlow.getRecordSize(); j++) {
                        String id = mapChartFlow.getRecordMarkerId(j);
                        float lat = mapChartFlow.getRecordLatitude(j);
                        float lng = mapChartFlow.getRecordLongitude(j);
                        String recordId = mapChartFlow.getRecordId(j);
                        addMapMarker(id, lat, lng, markerType, markerProperty, color);
                    }
                }
                //break;
           // default:
      //  }
    }
}
