package com.example.studify_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class AdaptadorActividades extends RecyclerView.Adapter<AdaptadorActividades.ActividadViewHolder> {
    private List<Actividad> listaMaterias;

    private Context context;

    private OnEliminarClickListener eliminarListener;


    public interface OnEliminarClickListener {
        void onEliminarClick(Actividad actividad, int posicion);
    }


    public AdaptadorActividades(Context context, List<Actividad> listaMaterias, OnEliminarClickListener listener) {
        this.context = context;
        this.listaMaterias = listaMaterias;
        this.eliminarListener = listener;
    }

    @NonNull
    @Override
    public ActividadViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_actividades, parent, false);
        return new ActividadViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActividadViewHolder holder, int position) {
        Actividad materia = listaMaterias.get(position);

        holder.txtNombre.setText(materia.getNombre());
        holder.txtCategoria.setText(materia.getCategoria());
        holder.txtFecha.setText(materia.getFechaEntrega());
        holder.txtDescripcion.setText(materia.getDescripcion());

        holder.btnEliminar.setOnClickListener(v -> {
            Toast.makeText(context, "Eliminando: " + materia.getNombre(), Toast.LENGTH_SHORT).show();

            listaMaterias.remove(position);

            notifyItemRemoved(position);

            notifyItemRangeChanged(position, listaMaterias.size());
        });

        holder.btnEliminar.setOnClickListener(v -> {
            if (eliminarListener != null) {
                eliminarListener.onEliminarClick(materia, position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listaMaterias.size();
    }
    public static class ActividadViewHolder extends RecyclerView.ViewHolder {
        TextView txtNombre, txtCategoria, txtFecha, txtDescripcion;
        Button btnEliminar;

        public ActividadViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNombre = itemView.findViewById(R.id.txtNombre);
            txtCategoria = itemView.findViewById(R.id.txtCategoria);
            txtFecha = itemView.findViewById(R.id.txtFecha);
            txtDescripcion = itemView.findViewById(R.id.txtDescripcion);
            btnEliminar = itemView.findViewById(R.id.btnEliminar);
        }
    }
}
