package deltagraphs.norrisviewer.view.graphsView;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;


import java.util.ArrayList;

import deltagraphs.norrisviewer.R;
import deltagraphs.norrisviewer.model.flowModel.FlowModel;
import deltagraphs.norrisviewer.model.flowModel.MapChartFlow;
import deltagraphs.norrisviewer.presenter.graphsPresenter.MapChartPresenter;
import deltagraphs.norrisviewer.presenter.graphsPresenter.MapChartPresenterImpl;

public class MapChartActivity extends FragmentActivity implements OnMapReadyCallback, MapChartView {

    private GoogleMap map; // Might be null if Google Play services APK is not available.
    private MapChartPresenter mapChartPresenter;
    private String sourceURL;
    private String sourceTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_chart);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            sourceURL = extras.getString("EXTRA_SOURCE_URL");
            sourceTitle = extras.getString("EXTRA_SOURCE_TITLE");
        }
        sourceURL = "http://norris-nrti-dev.herokuapp.com/page1/map1";
        setTitle(sourceTitle);
        mapChartPresenter = new MapChartPresenterImpl(this, sourceURL);
        setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (map == null) {
            // Try to obtain the map from the SupportMapFragment.
            map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (map != null) {
                addMapMarker("345", 10, 10, "icon", "car", "yellow");
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap mMap) {
    }

    public void addMapMarker(String id, float lat, float lng, String type, String property, String color){
        MarkerOptions mMarkerOptions = newMarker(id, lat, lng, type, property, color);
        map.addMarker(mMarkerOptions);
    }

    public void removeMarker(Marker mMarker){
        if(mMarker != null)
            mMarker.remove();
    }

    public void updateMarkerPosition(Marker mMarker, float lat, float lng){
        removeMarker(mMarker);
        mMarker = map.addMarker(new MarkerOptions().position(new LatLng(lat, lng)));
    }

    public void cameraPosition(float lat, float lng){
        LatLng position = new LatLng(lat, lng);
        map.moveCamera(CameraUpdateFactory.newLatLng(position));
    }

    public MarkerOptions newMarker(String id, float lat, float lng, String type, String property, String color){

        MarkerOptions m = new MarkerOptions();
        m.position(new LatLng(lat, lng));
        m.title(id);
        switch (type) {
            // shape marker
            case "shape":
                switch(property) {
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
                            default:
                                m.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                                break;
                        }
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


    public void zoomAtPoint(Marker mMarker, int width, int height){
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
    public void setLegendOnPoint(Boolean legend) {

    }

    @Override
    public void setZoom(float height, float width) {

    }

    @Override
    public void setData(ArrayList<FlowModel> flowList) {
        for(int i=0; i<flowList.size(); i++){
            flowList.get(i).getFlowId();
            flowList.get(i).getFlowName();
            MapChartFlow mapChartFlow = (MapChartFlow) flowList.get(i);
            mapChartFlow.getMaxItems();
            String markerType = mapChartFlow.getMarkerType();
            String markerProperty = mapChartFlow.getMarkerProperty(markerType);
            String color = mapChartFlow.getMarkerColour();
            for(int j =0; j< mapChartFlow.getRecordSize(); j++) {
                String id = mapChartFlow.getRecordMarkerId(j);
                float lat = mapChartFlow.getRecordLatitude(j);
                float lng = mapChartFlow.getRecordLongitude(j);
                String recordId = mapChartFlow.getRecordId(j);
                addMapMarker(id, lat, lng, markerType, markerProperty, color);
            }
        }
        map.clear();
    }
}
