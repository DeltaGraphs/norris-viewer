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
        MarkerOptions mOpt = newMarker(map,"801", 10, 10, "shape", "default", "", "", "green");
        map.addMarker(mOpt);
    }

    public void addMarker(GoogleMap map, MarkerOptions mMarkerOptions){
        map.addMarker(mMarkerOptions);
    }

    public MarkerOptions newMarker(GoogleMap map, String id, float lat, float lng,String type, String shape, String icon, String text, String color){

        MarkerOptions m = new MarkerOptions();

        switch (type) {
            // shape marker
            case "shape":
                switch(shape) {


                    //normal marker
                    default:
                        m.position(new LatLng(lat, lng));
                        m.title(id);
                        switch (color) {
                            case "red":
                                m.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                                break;
                            case "blue":
                                m.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
                                break;
                            case "azure":
                                m.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
                                break;
                            case "cyan":
                                m.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));
                                break;
                            case "green":
                                m.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                                break;
                            case "magenta":
                                m.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
                                break;
                            case "orange":
                                m.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
                                break;
                            case "rose":
                                m.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE));
                                break;
                            case "violet":
                                m.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET));
                                break;
                            case "yellow":
                                m.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
                                break;
                        }
                }

            case "icon":
                m.position(new LatLng(lat, lng));
                m.title(id);
                switch (icon){
                    /*
                    case "bus":
                        m.icon(BitmapDescriptorFactory.fromResource(R.mipmap.bus_marker)));
                        break;
                    case "flag":
                        m.icon(BitmapDescriptorFactory.fromResource(R.mipmap.flag_marker)));
                        break;
                    case "car":
                        m.icon(BitmapDescriptorFactory.fromResource(R.mipmap.car_marker)));
                        break;
                    case "house":
                        m.icon(BitmapDescriptorFactory.fromResource(R.mipmap.house_marker)));
                        break;
                    case "man":
                        m.icon(BitmapDescriptorFactory.fromResource(R.mipmap.man_marker)));
                        break;
                    case "woman":
                        m.icon(BitmapDescriptorFactory.fromResource(R.mipmap.woman_marker)));
                        break;
                    */
                }
            case "text":
            default:
        }
        return m;
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
    public void setParams(float latitude, float longitude) {

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
