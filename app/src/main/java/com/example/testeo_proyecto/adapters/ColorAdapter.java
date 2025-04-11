package com.example.testeo_proyecto.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testeo_proyecto.R;
import com.example.testeo_proyecto.entities.Colores;

import org.w3c.dom.Text;

import java.util.List;

public class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.ColorsViewHolder> {
    private List<Colores> data;

    public ColorAdapter(List<Colores> data) {
        this.data = data;
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

        tvNomCol.setText(colors.nombre);

        try
        {
            String hex = "#"+colors.num.trim();
            tvHex.setText(hex);
            vColorBg.setBackgroundColor(android.graphics.Color.parseColor(hex));
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
