package com.example.starsgallery.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.starsgallery.R;
import com.example.starsgallery.beans.Star;
import com.example.starsgallery.service.StarService;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class StarAdapter extends RecyclerView.Adapter<StarAdapter.StarViewHolder> implements Filterable {

    private List<Star> stars;
    private List<Star> starsFilter;
    private Context context;
    private NewFilter mFilter;

    public StarAdapter(Context context, List<Star> stars) {
        this.context = context;
        this.stars = stars;
        this.starsFilter = new ArrayList<>(stars);
        this.mFilter = new NewFilter(this);
    }

    @NonNull
    @Override
    public StarViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.star_item, viewGroup, false);
        final StarViewHolder holder = new StarViewHolder(v);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();

                if (position == RecyclerView.NO_POSITION) {
                    return;
                }

                Star selectedStar = starsFilter.get(position);

                View popup = LayoutInflater.from(context).inflate(R.layout.star_edit_item, null, false);

                final ImageView img = popup.findViewById(R.id.img);
                final RatingBar bar = popup.findViewById(R.id.ratingBar);
                final TextView ids = popup.findViewById(R.id.ids);

                Glide.with(context)
                        .load(selectedStar.getImg())
                        .apply(new RequestOptions().override(100, 100))
                        .into(img);

                bar.setRating(selectedStar.getRating());
                ids.setText(String.valueOf(selectedStar.getId()));

                AlertDialog dialog = new AlertDialog.Builder(context)
                        .setTitle("Notez :")
                        .setMessage("Donner une note entre 1 et 5 :")
                        .setView(popup)
                        .setPositiveButton("Valider", (dialogInterface, which) -> {
                            float s = bar.getRating();
                            int id = Integer.parseInt(ids.getText().toString());

                            Star star = StarService.getInstance().findById(id);
                            if (star != null) {
                                star.setRating(s);
                                StarService.getInstance().update(star);
                                int adapterPosition = holder.getAdapterPosition();
                                if (adapterPosition != RecyclerView.NO_POSITION) {
                                    notifyItemChanged(adapterPosition);
                                }
                            }
                        })
                        .setNegativeButton("Annuler", null)
                        .create();

                dialog.show();
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull StarViewHolder holder, int position) {
        Star star = starsFilter.get(position);

        Glide.with(context)
                .load(star.getImg())
                .placeholder(R.drawable.star)
                .error(R.drawable.star)
                .apply(new RequestOptions().override(100, 100))
                .into(holder.img);

        holder.name.setText(star.getName().toUpperCase(Locale.ROOT));
        holder.stars.setRating(star.getRating());
        holder.ids.setText(String.valueOf(star.getId()));
    }

    @Override
    public int getItemCount() {
        return starsFilter.size();
    }

    @Override
    public Filter getFilter() {
        return mFilter;
    }

    public class StarViewHolder extends RecyclerView.ViewHolder {

        TextView ids;
        ImageView img;
        TextView name;
        RatingBar stars;

        public StarViewHolder(@NonNull View itemView) {
            super(itemView);

            ids = itemView.findViewById(R.id.ids);
            img = itemView.findViewById(R.id.imgStar);
            name = itemView.findViewById(R.id.tvName);
            stars = itemView.findViewById(R.id.rating);
        }
    }

    public class NewFilter extends Filter {

        public RecyclerView.Adapter mAdapter;

        public NewFilter(RecyclerView.Adapter mAdapter) {
            this.mAdapter = mAdapter;
        }

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Star> filtered = new ArrayList<>();

            if (charSequence == null || charSequence.length() == 0) {
                filtered.addAll(stars);
            } else {
                String filterPattern = charSequence.toString().toLowerCase(Locale.ROOT).trim();

                for (Star p : stars) {
                    if (p.getName().toLowerCase(Locale.ROOT).startsWith(filterPattern)) {
                        filtered.add(p);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filtered;
            results.count = filtered.size();

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            starsFilter = (List<Star>) filterResults.values;
            mAdapter.notifyDataSetChanged();
        }
    }
}
