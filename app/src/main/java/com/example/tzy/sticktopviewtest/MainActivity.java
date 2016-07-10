package com.example.tzy.sticktopviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 2015-09
 * by 涂臻宇
 */

public class MainActivity extends AppCompatActivity implements AbsListView.OnScrollListener{

    ListView listView;//滑动列表控件

    List<Person> lit; //列表数据集，不是重点

    DataAdapter adapter;//

    /**
     * 第一个headerView，显示顶部图片的视图
     */
    View firstLayout;
    /**
     * 第二个HeaderView,下拉时需要悬停在顶部的View
     * 实际上该View在ListView向下滑动的那个时依旧会滑出界面，
     * 此时，需要一个相同的view及时出现在屏幕顶部，替代之。
     */
    View secondLaout;
    /**
     * 替代布局：
     *该View和secondLaout引用了同一个布局文件，所以二者会生成相同的view，
     * hideView会隐藏在屏幕顶部，等到secondLaout正好触碰到屏幕顶部时，
     * hideView就要及时出现，顶替secondLaout，以达到secondLaout好像悬停在屏幕顶部的效果。
     */
    View hideView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstLayout = LayoutInflater.from(this).inflate(R.layout.first_layout,null);
        secondLaout = LayoutInflater.from(this).inflate(R.layout.second_layout, null);
        hideView = findViewById(R.id.secondLayout);

        lit = new ArrayList<>(30);

        for(int i=0;i<30;i++)
        {
            lit.add(new Person("李白",25,"男"));
        }

        adapter = new DataAdapter(this,lit);

        listView = (ListView)findViewById(R.id.listView);


        //按顺序添加两个view，先添加的在上面。
        listView.addHeaderView(firstLayout);
        listView.addHeaderView(secondLaout);

        listView.setAdapter(adapter);

        //listView监听滑动事件，很重要，因为可以根据滑动的位置
        //决定是否要加载隐藏的视图hideView
        listView.setOnScrollListener(this);

    }


    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

        /**
         * 核心代码：
         * 在滑动的那个过程中，判断如果第一个可见的列表项的位置（firstVisibleItem），
         * 如果是firstVisibleItem大于等于第二个，也就是说：
         * firstLayout已经滑出了屏幕，第一个可视的列表项是secondLaout或者是其后面的列表项，
         * 即接下来会滑出或已经滑出屏幕的列表项是secondLaout，换句话说：
         * secondLaout即将被挡住或者已经被挡住
         * 此时要把hideView给显示出来，替代secondLaout.
         */

        if(firstVisibleItem >= 1)
        {
            hideView.setVisibility(View.VISIBLE);
        }

        else //说明secondLaout没有被屏幕挡住，那就把hideView给收起来。
        {
            hideView.setVisibility(View.INVISIBLE);
        }
    }
}
