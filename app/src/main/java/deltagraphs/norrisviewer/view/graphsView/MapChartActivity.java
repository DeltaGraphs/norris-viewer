package deltagraphs.norrisviewer.view.graphsView;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;


import deltagraphs.norrisviewer.R;

public class MapChartActivity extends FragmentActivity implements OnMapReadyCallback, MapChartView {

    private GoogleMap map; // Might be null if Google Play services APK is not available.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_chart);
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
/*
    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }
*/

    @Override
    public void onMapReady(GoogleMap map) {
        map.addMarker(new MarkerOptions()
                .position(new LatLng(10, 10))
                .title("Hello world"));
    }

    public void newMarker(GoogleMap map, float lat, float lng,String type, String shape, String icon, String text, String color){
        map.addMarker(new MarkerOptions()
                .position(new LatLng(lat, lng))
                .title(text));
    }

    public void setType(GoogleMap map,String type){
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


    @Override
    public void setParams(float latitude, float longitude, int scale) {

    }

    @Override
    public void setMapType(String type) {

    }

    @Override
    public void setZoom(Boolean choise) {

    }

    @Override
    public void setLegendOnPoint(Boolean legend) {

    }
}
