package com.junrrein.proyectofinal.ui;

import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.junrrein.proyectofinal.R;
import com.junrrein.proyectofinal.databinding.ElegirUbicacionBinding;
import com.mapbox.geojson.Point;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.style.layers.SymbolLayer;
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource;

import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconAllowOverlap;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconIgnorePlacement;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconImage;

public class ElegirUbicacionActivity extends AppCompatActivity {

    private static final String SOURCE_ID = "mi.fuente";
    private static final String ICON_ID = "mi.icono";
    private static final String MARKER_LAYER_ID = "mi.capa.marcadores";

    private ElegirUbicacionBinding binding;
    private MapView mapView;
    private MapboxMap mapboxMap;
    private GeoJsonSource source;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, getString(R.string.mapbox_token));
        binding = ElegirUbicacionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setTitle("Elegir ubicación");

        source = new GeoJsonSource(SOURCE_ID);

        binding.confirmarUbicacion.setEnabled(false);
        mapView = binding.mapView;
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(onMapReadyCallback);
    }

    private OnMapReadyCallback onMapReadyCallback = mapboxMap -> {
        this.mapboxMap = mapboxMap;
        mapboxMap.setStyle(Style.MAPBOX_STREETS, this.onStyleLoaded);
    };

    private Style.OnStyleLoaded onStyleLoaded = style -> {
        double latitud = -31.634788;
        double longitud = -60.705824;
        CameraPosition position = new CameraPosition.Builder()
                .target(new LatLng(latitud, longitud))
                .zoom(12.0)
                .build();
        mapboxMap.setCameraPosition(position);

        style.addSource(source);
        style.addImage(ICON_ID, BitmapFactory.decodeResource(getResources(), R.drawable.mapbox_marker_icon_default));
        style.addLayer(generarMarkerLayer());
        mapboxMap.addOnMapClickListener(this.onMapClickListener);
    };

    private SymbolLayer generarMarkerLayer() {
        return new SymbolLayer(MARKER_LAYER_ID, SOURCE_ID)
                .withProperties(iconImage(ICON_ID),
//                        visibility(Property.NONE),
                        iconAllowOverlap(true),
                        iconIgnorePlacement(true));
    }

    private MapboxMap.OnMapClickListener onMapClickListener = point -> {
        source.setGeoJson(Point.fromLngLat(point.getLongitude(), point.getLatitude()));
        binding.confirmarUbicacion.setEnabled(true);

        return false;
    };

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }


    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }
}
