package com.chucknorrisjokes.istanchev.chucknorrisjokes.adapters.recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chucknorrisjokes.istanchev.chucknorrisjokes.R;
import com.chucknorrisjokes.istanchev.chucknorrisjokes.callbacks.ChosenCategoryCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by istanchev on 8/29/17.
 */

public class CategoriesAdapter extends BaseRecyclerViewAdapter<RecyclerView.ViewHolder, String> {

    ChosenCategoryCallback chosenCategoryCallback;

    public CategoriesAdapter(Context context, List<String> data, ChosenCategoryCallback chosenCategoryCallback) {
        this.setData(data);

        this.context = context;
        this.chosenCategoryCallback = chosenCategoryCallback;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CategoryViewHolder(LayoutInflater.from(context).inflate(R.layout.item_choose_category, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        String currentModel = getItem(position);

        if (holder instanceof CategoryViewHolder) {
            ((CategoryViewHolder) holder).init(context, currentModel, chosenCategoryCallback);
        }
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_item_choose_category_category_name)
        TextView txtCategoryName;

        Context context;
        String model;
        ChosenCategoryCallback chosenCategoryCallback;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void init(Context context, String model, ChosenCategoryCallback chosenCategoryCallback) {
            this.context = context;
            this.model = model;
            this.chosenCategoryCallback = chosenCategoryCallback;

            if (context != null && !TextUtils.isEmpty(model)) {
                txtCategoryName.setText(model);
            }
        }

        @OnClick(R.id.txt_item_choose_category_category_name)
        public void onClick() {
            if (chosenCategoryCallback != null) {
                chosenCategoryCallback.onCategoryChosen(model);
            }
        }
    }
}
