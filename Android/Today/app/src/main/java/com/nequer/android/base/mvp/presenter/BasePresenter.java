package com.nequer.android.base.mvp.presenter;

import com.nequer.android.base.mvp.impl.IBaseContract;

import java.util.List;

public abstract class BasePresenter<V extends IBaseContract.IBaseView> implements IBaseContract.IBasePresenter {
    protected V mView;
    protected List<IBaseContract.IBaseModel> mBaseModelList;
    public BasePresenter(){

    }

    /**
     * P层绑定V层
     */
    @Override
    public void bindView(IBaseContract.IBaseView view){
        this.mView = (V)view;
    }

    /**
     * 解绑V层的生命周期
     */
    @Override
    public void unBindView(){
        if(mView != null){
            mView = null;
        }
    }
    /**
     * 判断是否绑定
     */
    @Override
    public boolean isBindView(){
        return mView != null;
    }


    /**
     * 一个presenter可能注册多个model
     * @param model
     */
    @Override
    public void registerModel(IBaseContract.IBaseModel model) {
        if (!mBaseModelList.contains(model)) {
            mBaseModelList.add(model);
        }
    }

}
