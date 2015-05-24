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
        MarkerOptions mOpt = newMarker(map,"823", 10, 10, "icon", "", "bus", "", "green");
        Marker m = map.addMarker(mOpt);
        cameraPosition(map, 1000, 1000);
        //zoomAtPoint(map, m, 400, 500);
    }

    public void addMarker(GoogleMap map, MarkerOptions mMarkerOptions){
        map.addMarker(mMarkerOptions);
    }

    public void removeMarker(GoogleMap map, Marker mMarker){
        if(mMarker != null)
            mMarker.remove();
    }

    public void updateMarkerPosition(GoogleMap map, Marker mMarker, float lat, float lng){
        removeMarker(map, mMarker);
        mMarker = map.addMarker(new MarkerOptions().position(new LatLng(lat, lng)));
    }

    public void cameraPosition(GoogleMap map, float lat, float lng){
        LatLng position = new LatLng(lat, lng);
        map.moveCamera(CameraUpdateFactory.newLatLng(position));
    }

    public MarkerOptions newMarker(GoogleMap map, String id, float lat, float lng,String type, String shape, String icon, String text, String color){

        MarkerOptions m = new MarkerOptions();
        m.position(new LatLng(lat, lng));
        m.title(id);
        switch (type) {
            // shape marker
            case "shape":
                switch(shape) {
                    //normal marker
                    case "normal":
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
                switch (icon){
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
            case "text":

            default:
                m.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                break;
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


    public void zoomAtPoint(GoogleMap map, Marker mMarker, int width, int height){
        LatLng position = mMarker.getPosition();
        double[] ref = getBoundingBox(position.latitude, position.longitude, width, height);
        LatLngBounds bounds = new LatLngBounds(new LatLng(ref[0], ref[1]), new LatLng( ref[2], ref[3]));
        map.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 0));
        map.animateCamera(CameraUpdateFactory.zoomIn());
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
