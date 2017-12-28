package me.xiba.startlearnmvvm.movie.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

import me.xiba.startlearnmvvm.base.BaseAdapterViewModel;
import me.xiba.startlearnmvvm.base.BaseEvent;

/**
 * Created by liukun on 2017/12/25.
 */

public class BindingRecyclerAdapter<T, VM extends BaseAdapterViewModel> extends RecyclerView.Adapter<AdapterItemHolder> {

    private ArrayList<T> mData;
    private int mViewModelBrId;
    private int mLayoutId;
    private int mEventBrId;
    private BaseEvent mEvent;
    private Class<VM> mModelClass;

    public BindingRecyclerAdapter(int viewModelBrId, int layoutId, int eventBrId, BaseEvent event, Class<VM> modelClass) {
        this.mViewModelBrId = viewModelBrId;
        this.mLayoutId = layoutId;
        this.mEventBrId = eventBrId;
        this.mEvent = event;
        this.mModelClass = modelClass;
    }

    @Override
    public AdapterItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(inflater, mLayoutId, parent, false);

        AdapterItemHolder<VM> holder = new AdapterItemHolder<VM>(binding.getRoot());
        holder.setBinding(binding);
        holder.setViewModel(createNewViewModel());

        return holder;
    }

    @Override
    public void onBindViewHolder(AdapterItemHolder holder, int position) {
        holder.getBinding().setVariable(mViewModelBrId, holder.getViewModel());
        holder.getBinding().setVariable(mEventBrId, mEvent);
        holder.getViewModel().setData(mData.get(position));

        holder.getBinding().executePendingBindings();

    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public void setData(ArrayList<T> data){
        mData = data;
        notifyDataSetChanged();
    }

    public VM createNewViewModel(){
        try {
             return mModelClass.newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException("Cannot create an instance of " + mModelClass, e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Cannot create an instance of " + mModelClass, e);
        }
    }

}
