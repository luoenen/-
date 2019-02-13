package com.luosenen.huelibrary.core;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.luosenen.huelibrary.R;
import com.luosenen.huelibrary.adapter.SeatAdapter;
import com.luosenen.huelibrary.info.Info;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.update.BmobUpdateAgent;

import static cn.bmob.v3.b.From.e;

public class FragmentOneActivity extends Fragment {
    private Button button;
    private ListView listView;
    private List<Info> infoList = new ArrayList<Info>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_fragment_one,container,false);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        BmobUpdateAgent.initAppVersion();
        BmobUpdateAgent.setUpdateOnlyWifi(false);
        BmobUpdateAgent.update(getActivity());
        button = getActivity().findViewById(R.id.search_result);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initInfo();
            }
        });
        initInfo();
        SeatAdapter adapter = new SeatAdapter(getActivity(), R.layout.activity_search_item, infoList);
        listView = getActivity().findViewById(R.id.result);
        listView.setAdapter(adapter);
    }

    private void initInfo() {
        BmobQuery<Info> query = new BmobQuery<Info>();
        query.order("-createdAt").findObjects(new FindListener<Info>() {
            @Override
            public void done(List<Info> list, BmobException e) {
                if (e!=null){
                    for(Info info:list){
                        infoList.add(info);
                    }
                }
            }
        });
    }

}
