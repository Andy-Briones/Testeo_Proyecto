package com.example.testeo_proyecto.adapters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.testeo_proyecto.R;

import java.security.BasicPermission;
import java.util.List;

public class AdapterColor extends RecyclerView.Adapter<AdapterColor.BasicViewHolder2>
{

    public List<String>data2;
    public AdapterColor(List<String> data)
    {
        this.data2 = data2;
    }

    @NonNull
    @Override
    public BasicViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_third, parent, false);

        return new AdapterColor.BasicViewHolder2(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BasicViewHolder2 holder, int position) {
        TextView tvText = holder.itemView.findViewById(R.id.tvNameColor);
        TextView tvNum = holder.itemView.findViewById(R.id.tvCodeColor);
        Button btn1 = holder.itemView.findViewById(R.id.btnColor);
        String text = data2.get(position);
        tvText.setText(text);
    }

    @Override
    public int getItemCount() {
        return data2.size();
    }

    public class BasicViewHolder2 extends RecyclerView.ViewHolder
    {
        public BasicViewHolder2(@NonNull View itemView) {
            super(itemView);
        }
    }
}
