package com.tommunyiri.eclectics.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;
import com.tommunyiri.eclectics.R;
import com.tommunyiri.eclectics.models.Article;
import com.tommunyiri.eclectics.ui.activities.MainActivity;
import com.tommunyiri.eclectics.utils.ShimmerPlaceholder;

import java.util.ArrayList;
import java.util.List;

public class ArticlesListAdapter extends RecyclerView.Adapter<ArticlesListAdapter.ViewHolder> implements Filterable {
    private List<Article> articleList;
    private List<Article> articleListFiltered;
    private Context context;
    private String TAG = "ArticlesListAdapter";
    private MainActivity mainActivity;

    public ArticlesListAdapter(List<Article> articleList, Context context, MainActivity mainActivity) {
        this.articleList = articleList;
        this.articleListFiltered = articleList;
        this.context = context;
        this.mainActivity = mainActivity;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle,tvDescription;
        private RoundedImageView ivArticleImage;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            tvDescription = view.findViewById(R.id.tvDescription);
            tvTitle = view.findViewById(R.id.tvTitle);
            ivArticleImage = view.findViewById(R.id.ivArticleImage);
        }

        public TextView getTextView() {
            return tvTitle;
        }
    }

    @NonNull
    @Override
    public ArticlesListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_article, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticlesListAdapter.ViewHolder holder, int position) {
        final Article article = articleListFiltered.get(position);
        holder.tvTitle.setText(article.getTitle());
        holder.tvDescription.setText(article.getDescription());
        Glide.with(context).load(article.getUrlToImage())
                .placeholder(ShimmerPlaceholder.Companion.getShimmerPlaceHolder())
                .into(holder.ivArticleImage);

    }

    @Override
    public int getItemCount() {
        return articleListFiltered != null ? articleListFiltered.size() : 0;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    articleListFiltered = articleList;
                } else {
                    List<Article> filteredList = new ArrayList<>();
                    for (Article row : articleList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for first name and last name match
                        if (row.getTitle().toLowerCase().contains(charString.toLowerCase()) ||
                                row.getDescription().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }
                    articleListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = articleListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                articleListFiltered = (ArrayList<Article>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
