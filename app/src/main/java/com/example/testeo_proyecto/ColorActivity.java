package com.example.testeo_proyecto;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testeo_proyecto.entities.Colores;
import com.example.testeo_proyecto.adapters.ColorAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ColorActivity extends AppCompatActivity {
    RecyclerView rvColoress;
    boolean isLoading = false;
    boolean isLastPage = false;
    SearchView searchColors;
    String busqueda = "";
    int currentPage = 1;

    List<Colores> colorsData = new ArrayList<>();
    ColorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_color);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Toast.makeText(getApplicationContext(),"ColorActivity onCreate", Toast.LENGTH_SHORT).show();

        FloatingActionButton button = findViewById(R.id.fabGotoColorForm);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(this, FormColorActivity.class);
            //startActivity(intent);
            startActivityForResult(intent, 100);
        });

        rvColoress = findViewById(R.id.rvColores);
        rvColoress.setLayoutManager(new LinearLayoutManager(this));

        setUpReciclerView();
        //setUpSearchView();
        loadMoreColors(busqueda);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != RESULT_OK) return;

        if (requestCode == 123) {

            String colorJSON = data.getStringExtra("colorJSON");
            Colores updatedColor = new Gson().fromJson(colorJSON, Colores.class);

            Colores color = colorsData.stream().filter(c -> c.id == updatedColor.id)
                    .findFirst().orElse(null);
            int position = colorsData.indexOf(color);

            if (color != null) {
                color.name = updatedColor.name;
                color.num = updatedColor.num;
                adapter.notifyItemChanged(position); //esto es lo relevante
            }
        }

        if (requestCode == 100) {
            String colorJSON = data.getStringExtra("colorJSON");
            Colores createdColor = new Gson().fromJson(colorJSON, Colores.class);

            colorsData.add(createdColor);
            adapter.notifyItemInserted(colorsData.size() -1);
        }
    }

    //    @Override
//    protected void onResume()
//    {
//        super.onResume();
//
//        Toast.makeText(getApplicationContext(), "ColorActivity OnResume", Toast.LENGTH_LONG).show();
//
//        data.clear();
//        currentPage = 1;
//        adapter.notifyDataSetChanged(); //notifica al adapter que los datos han cambiado
//
//        loadMoreColors();
//    }

    private void loadMoreColors(String query)
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("colors");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                colorsData.clear();
                for (DataSnapshot colorSnapshot : snapshot.getChildren()) {
                    Colores colores = colorSnapshot.getValue(Colores.class);
                    if (colores != null) {
                        colorsData.add(colores);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
//        isLoading = true;
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://67ff052558f18d7209efd0c8.mockapi.io")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        //https://67ff052558f18d7209efd0c8.mockapi.io/Colores
//        ColorService service = retrofit.create(ColorService.class);
//        service.getColors(2,currentPage).enqueue(new Callback<List<Colores>>() {
//            @Override
//            public void onResponse(Call<List<Colores>> call, Response<List<Colores>> response) {
//                isLoading = false;
//
//                if (!response.isSuccessful()) return;
//                if (response.body() == null) return;
//                if (response.body().isEmpty())
//                {
//                    isLastPage = true;
//                    return;
//                }
//
//                data.addAll(response.body());//añade los nuevos colores a la lista
//                adapter.notifyDataSetChanged();
//                //List<Colores> data = response.body();
//            }
//
//            @Override
//            public void onFailure(Call<List<Colores>> call, Throwable throwable) {
//                isLoading = false;
//            }
//        });
    }

    private void setUpReciclerView()
    {
        adapter = new ColorAdapter(colorsData, ColorActivity.this);
        rvColoress.setAdapter(adapter);

        //Scroll Listener permite detectar cuando el usuario hace scroll y llega al final de la lista
        //para cargar mas datos
//        rvColoress.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//            }
//
//            @Override
//            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
//                if (layoutManager == null) return;
//
//                int visibleItemCount = layoutManager.getChildCount();
//                int totalItemCount = layoutManager.getItemCount();
//                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
//
//                if (!isLoading && !isLastPage)
//                {
//                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount && firstVisibleItemPosition >= 0)
//                    {
//                        currentPage++;
//                        loadMoreColors();
//                    }
//                }
//            }
//        });
    }
    private void setUpSearchView() {
        searchColors = findViewById(R.id.searchColor);
        searchColors.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d("MAIN_APP", query);

                if(!Objects.equals(busqueda, query)) {
                    colorsData.clear();
                    adapter.notifyDataSetChanged();
                    busqueda = query;
                    currentPage = 1;
                    loadMoreColors(busqueda);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d("MAIN_APP", newText);
                if (newText.isEmpty()) {
                    colorsData.clear();
                    adapter.notifyDataSetChanged();
                    busqueda = "";
                    currentPage = 1;
                    loadMoreColors(busqueda);
                }
                return false;
            }
        });
    }
}