# 使用SQLite进行本地登陆和注册功能
## SQLite简单用法介绍             
SQLite基本用法很简单，继承SQLiteOpenHelper 中有两个抽象方法，分别是onCreate()和 onUpgrade()，我们必须在自己的帮助类里面重写这两个方法，然后分别在这两个方法中去实现创建、升级数据库的逻辑。    
     SQLiteOpenHelper 中 还 有 两 个 非 常 重 要 的 实 例 方 法 ， getReadableDatabase() 和getWritableDatabase()。  
>使用 SQL 操作数据库   

+ 添加数据的方法如下： 
``` 
     db.execSQL("insert into Book (name, author, pages, price) values(?, ?, ?, ?)",
new String[] { "The Da Vinci Code", "Dan Brown", "454", "16.96" });
     db.execSQL("insert into Book (name, author, pages, price) values(?, ?, ?, ?)",
new String[] { "The Lost Symbol", "Dan Brown", "510", "19.95" });
```
+ 更新数据的方法如下：  
```
    db.execSQL("update Book set price = ? where name = ?", new String[] { "10.99",
"The Da Vinci Code" });  
```
+ 删除数据的方法如下：
```
    db.execSQL("delete from Book where pages > ?", new String[] { "500" });
```
+ 查询数据的方法如下：
```
    db.rawQuery("select * from Book", null);
```  
---
## 实现
### OpenHelper
```
public class OpenHelper extends SQLiteOpenHelper {

    //建表语句
    public static final String CREATE_USER = "create table User ("
            + "id integer primary key autoincrement, "
            + "username text, "
            + "userpwd text)";

    public OpenHelper(Context context, String name, CursorFactory factory,
                      int version) {
        super(context, name, factory, version);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub

        db.execSQL(CREATE_USER);//创建用户表
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

    }

}
```
## SqliteDB

```
public class SqliteDB {
    /**
     * 数据库名
     */
    public static final String DB_NAME = "sqlite_dbname";
    /**
     * 数据库版本
     */
    public static final int VERSION = 1;

    private static SqliteDB sqliteDB;

    private SQLiteDatabase db;

    private SqliteDB(Context context) {
        OpenHelper dbHelper = new OpenHelper(context, DB_NAME, null, VERSION);
        db = dbHelper.getWritableDatabase();
    }

    /**
     * 获取SqliteDB实例
     * @param context
     */
    public synchronized static SqliteDB getInstance(Context context) {
        if (sqliteDB == null) {
            sqliteDB = new SqliteDB(context);
        }
        return sqliteDB;
    }

    /**
     * 将User实例存储到数据库。
     */
    public int  saveUser(User user) {
        if (user != null) {
           /* ContentValues values = new ContentValues();
            values.put("username", user.getUsername());
            values.put("userpwd", user.getUserpwd());
            db.insert("User", null, values);*/

            Cursor cursor = db.rawQuery("select * from User where username=?", new String[]{user.getUsername().toString()});
            if (cursor.getCount() > 0) {
                return -1;
            } else {
                try {
                    db.execSQL("insert into User(username,userpwd) values(?,?) ", new String[]{user.getUsername().toString(), user.getUserpwd().toString()});
                } catch (Exception e) {
                    Log.d("错误", e.getMessage().toString());
                }
                return 1;
            }
        }
        else {
            return 0;
        }
    }

    /**
     * 从数据库读取User信息。
     */
    public List<User> loadUser() {
        List<User> list = new ArrayList<User>();
        Cursor cursor = db
                .query("User", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(cursor.getInt(cursor.getColumnIndex("id")));
                user.setUsername(cursor.getString(cursor
                        .getColumnIndex("username")));
                user.setUserpwd(cursor.getString(cursor

                        .getColumnIndex("userpwd")));
                list.add(user);
            } while (cursor.moveToNext());
        }
        return list;
    }

    public int Quer(String pwd,String name)
    {
        
        HashMap<String,String> hashmap=new HashMap<String,String>();
        Cursor cursor =db.rawQuery("select * from User where username=?", new String[]{name});

       // hashmap.put("name",db.rawQuery("select * from User where name=?",new String[]{name}).toString());
        if (cursor.getCount()>0)
        {
            Cursor pwdcursor =db.rawQuery("select * from User where userpwd=? and username=?",new String[]{pwd,name});
            if (pwdcursor.getCount()>0)
            {
                return 1;
            }
            else {
                return -1;
            }
        }
        else {
            return 0;
        }


    }
}



```
## User 
```
package model;


public class User {

    private int id;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpwd() {
        return userpwd;
    }

    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd;
    }

    private String username;
    private String userpwd;

}

```
## LoginActivity
```
package activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.sqlitetest.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.SqliteDB;
import model.User;

public class LoginActivity extends AppCompatActivity {
    private Button reg;
    private Button login;
    private EditText count;
    private EditText pwd;
    private TextView state;
    private List<User> userList;
    private List<User> dataList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        reg= (Button) findViewById(R.id.regin);
        login= (Button) findViewById(R.id.login);
        count= (EditText) findViewById(R.id.count);
        pwd= (EditText) findViewById(R.id.pwd);
        state= (TextView) findViewById(R.id.state);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=count.getText().toString().trim();
                String pass=pwd.getText().toString().trim();

                User user=new User();
                user.setUsername(name);
                user.setUserpwd(pass);

             int result=SqliteDB.getInstance(getApplicationContext()).saveUser(user);
                if (result==1){
                    state.setText("注册成功！");
                }else  if (result==-1)
                {
                    state.setText("用户名已经存在！");
                }
                else
                {
                    state.setText("！");
                }

            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=count.getText().toString().trim();
                String pass=pwd.getText().toString().trim();
                //userList=SqliteDB.getInstance(getApplicationContext()).loadUser();
                int result=SqliteDB.getInstance(getApplicationContext()).Quer(pass,name);
                if (result==1)
                {
                        state.setText("登录成功！");
                }
                else if (result==0){
                    state.setText("用户名不存在！");

                }
                else if(result==-1)
                {
                    state.setText("密码错误！");
                }
/*                for (User user : userList) {

                    if (user.getUsername().equals(name))
                    {
                        if (user.getUserpwd().equals(pass))
                        {
                            state.setText("登录成功！");

                        }else {
                            state.setText("密码错误！");

                        }
                    }
                    else {
                        state.setText("用户名不存在！");

                    }

                }*/

            }
        });
    }
}

```