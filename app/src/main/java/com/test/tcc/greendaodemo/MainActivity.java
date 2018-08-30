package com.test.tcc.greendaodemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.test.tcc.greendaodemo.greendao.User2Dao;
import com.test.tcc.greendaodemo.greendao.UserDao;

import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button add;
    Button delete;
    RecyclerView recyclerView;
    private UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final User2Dao userDao = App.getDaoSession().getUser2Dao();
        delete = findViewById(R.id.delete);
        add = findViewById(R.id.add);
        recyclerView = findViewById(R.id.user_rc_view);
        Random random = new Random();

      /*  for (int i =0 ; i<10 ;i++){
            User2 user = new User2();
            user.setUserName(random.nextInt(1000)+"");
            user.setPassWord(random.nextInt(1000)+"");
            user.setLv(random.nextInt(10)+"");
            user.setAddress("湖南怀化");
            userDao.insert(user);
        }*/

        List<User2> users = userDao.queryBuilder().where(User2Dao.Properties.Lv.le("5")).list();



       // List<User2> users = userDao.loadAll();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        userAdapter = new UserAdapter(users,this);
        recyclerView.setAdapter(userAdapter);
        userAdapter.setOnDeleteUserListener(new UserAdapter.OnDeleteUserClickListener() {
            @Override
            public void delete(User2 user) {
                userDao.delete(user);
                userAdapter.setUsers(userDao.loadAll());
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Random random = new Random();
                    User2 user = new User2();
                    user.setUserName(random.nextInt(1000) + "");
                    user.setPassWord(random.nextInt(1000) + "");
                    user.setLv(random.nextInt(10) + "");
                    user.setAddress("湖南怀化");
                    userDao.insert(user);
                    userAdapter.setUsers(userDao.loadAll());

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User2 deleteItem = userAdapter.getDeleteItem();
                if (deleteItem == null){
                    Toast.makeText(MainActivity.this,"已经没有user数据了",Toast.LENGTH_LONG).show();
                    return;
                }
                userDao.delete(userAdapter.getDeleteItem());
                userAdapter.setUsers(userDao.loadAll());
            }
        });





    }

    public List<User2> query(String name){
        User2Dao userDao = App.getDaoSession().getUser2Dao();
        List<User2> list = userDao.queryBuilder().where(User2Dao.Properties.UserName.eq(name)).list();
        return list;
    }


    public void delete(String name){
        User2Dao userDao = App.getDaoSession().getUser2Dao();
        User2 User2 = userDao.queryBuilder().where(User2Dao.Properties.UserName.eq(name)).unique();
        userDao.delete(User2);
    }

    public void update(User2 user2){
        User2Dao userDao = App.getDaoSession().getUser2Dao();
        userDao.update(user2);
    }


}
