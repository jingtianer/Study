package com.nequer.android.base.mvp.model;

import com.nequer.android.base.mvp.INotifyListener;
import com.nequer.android.base.mvp.impl.IBaseContract;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseModel implements IBaseContract.IBaseModel{
    /**
     * 维护每一个Presenter对应的回调
     */
    protected Map<IBaseContract.IBasePresenter, List<INotifyListener>> mListenerMap;

    @Override
    public void addNotifyListener(IBaseContract.IBasePresenter presenter, INotifyListener callback){
        if (presenter == null){
            return;
        } else {
            presenter.registerModel(this);
        }

        if (mListenerMap == null){
            mListenerMap = new HashMap<>();
        }
        List<INotifyListener> notifyListenerList = mListenerMap.get(presenter);
        if (notifyListenerList == null){
            notifyListenerList = new ArrayList<>();
        }
        notifyListenerList.add(callback);
        mListenerMap.put(presenter,notifyListenerList);
    }

    @Override
    public void removeNotifyListener(IBaseContract.IBasePresenter presenter) {
        if ((mListenerMap != null && mListenerMap.get(presenter) != null)) {
            mListenerMap.get(presenter).clear();
            mListenerMap.remove(presenter);
            presenter = null;
        }
    }

    @Override
    public void removeAllNotifyListener() {
        if (mListenerMap != null) {
            mListenerMap.clear();
        }
    }

}
