package com.example.tzy.sticktopviewtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by tzy on 2015/9/3.
 */
public class DataAdapter extends BaseAdapter {

    List<Person> list;

    Context context;


    public DataAdapter(Context context, List<Person> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        MyViewHoder myViewHoder ;

        if(convertView == null)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.item,null);
            myViewHoder = new MyViewHoder(convertView);
            convertView.setTag(myViewHoder);
        }

        myViewHoder = (MyViewHoder) convertView.getTag();

        Person p =list.get(position);

        myViewHoder.textNmae.setText(p.getName());
        myViewHoder.textAge.setText(p.getAge()+"");
        myViewHoder.textGender.setText(p.getGender());

        return convertView;
    }

    public static class MyViewHoder
    {

        TextView textNmae;
        TextView textAge;
        TextView textGender;

        public MyViewHoder(View itemView) {
            textNmae = (TextView)itemView.findViewById(R.id.textNmae);
            textAge = (TextView)itemView.findViewById(R.id.textAge);
            textGender = (TextView)itemView.findViewById(R.id.textGender);
        }
    }
}
