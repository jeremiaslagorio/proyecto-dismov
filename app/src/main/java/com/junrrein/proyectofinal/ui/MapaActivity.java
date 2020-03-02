package com.junrrein.proyectofinal.ui;

import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.junrrein.proyectofinal.R;
import com.mapbox.geojson.Feature;
import com.mapbox.geojson.FeatureCollection;
import com.mapbox.geojson.Point;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.style.layers.PropertyFactory;
import com.mapbox.mapboxsdk.style.layers.SymbolLayer;
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource;

import java.util.ArrayList;
import java.util.List;

import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconAllowOverlap;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconIgnorePlacement;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconOffset;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconSize;

public class MapaActivity extends AppCompatActivity {

    public static final String EVENTOS_MAPA = "com.junrrein.proyectofinal.eventos-mapa";

    private static final String SOURCE_ID = "mi.fuente";
    private static final String ICON_ID = "mi.icono";
    private static final String MARKER_LAYER_ID = "mi.capa.marcadores";

    private MapView mapView;
    private MapboxMap mapboxMap;
    private List<EventoMapa> eventos;
    private FeatureCollection featureCollection;
    private GeoJsonSource source;

    @Override
    @SuppressWarnings("unchecked")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, getString(R.string.mapbox_token));
        setContentView(R.layout.mapa);
        setTitle("Mapa");

        eventos = (ArrayList<EventoMapa>) getIntent().getSerializableExtra(EVENTOS_MAPA);
        featureCollection = generarFeatures();
        source = new GeoJsonSource(SOURCE_ID, featureCollection);

        mapView = findViewById(R.id.mapView);
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
                .build();
        mapboxMap.setCameraPosition(position);

        style.addSource(source);
        style.addImage(ICON_ID, BitmapFactory.decodeResource(getResources(), R.drawable.mapbox_marker_icon_default));
        style.addLayer(generarMarkerLayer());
    };

    private FeatureCollection generarFeatures() {
        List<Feature> features = new ArrayList<>();

        for (EventoMapa evento : eventos) {
            Feature feature = Feature.fromGeometry(Point.fromLngLat(evento.longitud, evento.latitud));

            features.add(feature);
        }

        return FeatureCollection.fromFeatures(features);
    }

    private SymbolLayer generarMarkerLayer() {
        return new SymbolLayer(MARKER_LAYER_ID, SOURCE_ID)
                .withProperties(PropertyFactory.iconImage(ICON_ID),
//                        iconSize(0.5f),
                        iconAllowOverlap(true),
                        iconIgnorePlacement(true));
    }

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
