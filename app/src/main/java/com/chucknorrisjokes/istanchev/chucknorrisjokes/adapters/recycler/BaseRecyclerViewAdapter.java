package com.chucknorrisjokes.istanchev.chucknorrisjokes.adapters.recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by istanchev on 8/29/17.
 */

public class BaseRecyclerViewAdapter<VH extends RecyclerView.ViewHolder, T> extends RecyclerView.Adapter<VH> {

    private List<T> data;
    private List<T> itemsPendingRemoval = new ArrayList<>();
    protected ItemListener itemListener;
    protected Context context;

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Setting the data.
     *
     * @param data Data that need to be set.
     */
    public void setData(List<T> data) {
        this.data = data;
    }

    /**
     * Returns the data.
     *
     * @return Array of data
     */
    public List<T> getData() {
        return data;
    }

    /**
     * Adds specific item at the end of the array.
     *
     * @param item The item to add at the end of array.
     */
    public void add(T item) {
        data.add(item);
        notifyDataSetChanged();
        // notifyItemInserted(getItemCount());
    }


    /**
     * Inserts the specific item at the specified position in the array.
     *
     * @param item     The item to insert at specified position.
     * @param position Specified position of the new item.
     */
    public void insert(T item, int position) {
        data.add(position, item);
        notifyItemInserted(position);
    }


    /**
     * Appends items on the existing data.
     *
     * @param items List of items that need to be append to the existing data.
     */
    public void appendItems(List<T> items) {
        int count = getItemCount();
        data.addAll(items);
        notifyItemRangeInserted(count, items.size());
    }


    /**
     * Removes item of specified position.
     *
     * @param position Specified position of removal.
     */
    public void remove(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }


    /**
     * Clears all the data in the array.
     */
    public void clear() {
        int size = getItemCount();
        data.clear();
        notifyItemRangeRemoved(0, size);
    }


    /**
     * Sorts the content of this adapter using the specified comparator.
     *
     * @param comparator The comparator used to sort the objects contained in this adapter.
     */
    public void sort(Comparator<? super T> comparator) {
        Collections.sort(data, comparator);
        notifyItemRangeChanged(0, getItemCount());
    }

    /**
     * Adds specific item with item_section at the end of the array.
     *
     * @param item The item to add at the end of array.
     */
    public void addItemWithSection(T item, int position) {
        position = position == -1 ? getItemCount() : position;
        getData().add(position, item);
        notifyItemInserted(position);
    }

    /**
     * Removes item with item_section of specified position.
     *
     * @param position Specified position of removal.
     */
    public void removeItemWithSection(int position) {
        if (position < getItemCount()) {
            getData().remove(position);
            notifyItemRemoved(position);
        }
    }

    /**
     * Returns the position of the specified item in the array.
     *
     * @param item The item to retrieve the position of.
     * @return The position of the specified item.
     */
    public int getPosition(T item) {
        return data.indexOf(item);
    }


    /**
     * Returns the item from the data in the specified position.
     *
     * @param position Specified position of the item.
     * @return The item of the specified position.
     */
    public T getItem(final int position) {
        return data.get(position);
    }


    /**
     * Interface for item click support
     */
    public interface ItemListener {
        void onItemClick(int position);
    }

    public void swipeRemove(int position) {
        T item = data.get(position);
        if (itemsPendingRemoval.contains(item)) {
            itemsPendingRemoval.remove(item);
        }
        if (data.contains(item)) {
            remove(position);
        }
    }

    public boolean isPendingRemoval(int position) {
        T item = data.get(position);
        return itemsPendingRemoval.contains(item);
    }
}
