package com.example.testeo_proyecto.adapters;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testeo_proyecto.FormColorActivity;
import com.example.testeo_proyecto.R;
import com.example.testeo_proyecto.entities.Colores;

import org.w3c.dom.Text;

import java.util.List;

public class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.ColorsViewHolder> {
    private List<Colores> data;
    private Activity activity;

    public ColorAdapter(List<Colores> data, Activity activity) {
        this.data = data;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ColorAdapter.ColorsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_color, parent, false);

        return new ColorsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ColorsViewHolder holder, int position) {
        Colores colors = data.get(position);

        TextView tvNomCol = holder.itemView.findViewById(R.id.tvNomColor);
        TextView tvHex = holder.itemView.findViewById(R.id.tvNomHex);
        View vColorBg = holder.itemView.findViewById(R.id.rvColorBg);

        tvNomCol.setText(colors.name);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"Color: " + colors.name, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(v.getContext(), FormColorActivity.class);

                //los extras me permiten enviar informacion entre actividades
                intent.putExtra("colorId",colors.id);
                intent.putExtra("colorName",colors.name);
                intent.putExtra("colorHex", colors.num);

                activity.startActivityForResult(intent, 123);
            }
        });

        try
        {
            String hex = colors.num.trim();
            tvHex.setText(hex);
            vColorBg.setBackgroundColor(android.graphics.Color.parseColor(colors.num));
        }catch (Exception e)
        {
            Log.d("Main App", "Usando Color por defecto");
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public class ColorsViewHolder extends RecyclerView.ViewHolder
    {
        public ColorsViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
