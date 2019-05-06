# MVP模式的Base模块
最近学习了MVP开发模式，发现是真的强，也是真的难懂，先写一点东西，记录一下
![](https://graph.baidu.com/resource/10181cef3f77f3ca9290301557132957.jpg)


当然，我们首先要先建三个包 Model,View,Presenter.
>以下代码是Base模块，给整体搭框架的，所以没有太多功能实现哦

## 每次新建活动都好麻烦，要不咱弄个厉害的老父亲？
每次新建活动的时候都要写一大堆初始化的东西，不如弄一个基类，重复的代码就写进这个里面
### BaseActivity
```
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 得到布局id
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 初始化数据
     * @param savedInstanceState
     */
    protected abstract void initData(Bundle savedInstanceState);

    /**
     * 初始化view
     */
    protected abstract void initView();

    /**
     * 启动新活动
     * @param newActivity
     */
    protected void startActivity(Class<?> newActivity) {
        startActivity(newActivity, null);
    }

    /**
     * 启动新活动（传输简单数据）
     * @param newActivity
     * @param bundle
     */
    protected void startActivity(Class<?> newActivity, Bundle bundle) {
        Intent intent = new Intent(BaseActivity.this, newActivity);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }
}

```

### 再来一个BaseNoBarActivity
```
public abstract class BaseNoBarActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initData(savedInstanceState);
        initView();
    }
}

```


---

## 契约接口，先签个协议吧
+ 这个一般是在Model层里实现
```
public interface IBaseContract {

    interface IBaseView {

    }

    interface IBasePresenter {

        /**
         * P层绑定V层的生命周期
         * @param view
         */
        void bindView(IBaseView view);

        /**
         * P层解绑V层的生命周期
         */
        void unBindView();

        /**
         * P层是否与V层绑定
         * @return
         */
        boolean isBindView();
    }

    interface IBaseModel {

    }
}

```
可以看到，在Base模块里，其实每个层要干的事情并不多，最累的当属Presenter,一上来就要有三个任务——**绑定View层，解绑View层，判断是否与View层解绑**

---



## 协议签好了，开始按照协议干活吧

### Model(懒得一批，啥都没有)
```
public abstract class BaseModel implements IBaseContract.IBaseModel{
}

```

### Presenter(这是个忙人)

```
public abstract class BasePresenter<V extends IBaseContract.IBaseView> implements IBaseContract.IBasePresenter {

    protected V mView;

    public BasePresenter() {
        // 完成初始化数据库等操作
    }

    /**
     * P层绑定V层的生命周期
     * @param view
     */
    @Override
    public void bindView(IBaseContract.IBaseView view) {
        this.mView = (V) view;
    }

    /**
     * P层解绑V层的生命周期
     */
    @Override
    public void unBindView() {
        if (mView != null) {
            mView = null;
        }
    }

    /**
     * P层是否与V层绑定
     * @return
     */
    @Override
    public boolean isBindView() {
        return mView != null;
    }
}

```

实现 **协议**里的接口，绑定，解绑，判断。

### View(虽然改名了，我还是认识你，被弱化了的Activity)
```
public abstract class BasePresenterActivity<P extends IBaseContract.IBasePresenter> extends BaseNoBarActivity implements IBaseContract.IBaseView{

    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        initPresenter();
        super.onCreate(savedInstanceState);
    }

    protected abstract P getPresenter();

    /**
     * 双向绑定
     */
    protected void initPresenter() {
        mPresenter = getPresenter();
        if (mPresenter != null) {
            mPresenter.bindView(this);
        }
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.unBindView();
            mPresenter = null;
        }
        super.onDestroy();
    }
}

```

停停停！有一个getPresenter()方法，这是干嘛的？我咋没看见它是咋实现的？其实这个是一个抽象方法，本身这个类就是一个抽象类，这个方法是留给后人的。


那么这个就是一个MVP框架的基础部分，其实根本没写啥，具体接下来的使用，我会继续更新。