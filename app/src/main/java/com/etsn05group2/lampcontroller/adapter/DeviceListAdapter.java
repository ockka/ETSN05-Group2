package com.etsn05group2.lampcontroller.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.etsn05group2.lampcontroller.R;
import com.etsn05group2.lampcontroller.model.Device;

import java.util.List;

/**
 * Created by David on 2015-09-25.
 */
public class DeviceListAdapter  extends ArrayAdapter<Device> {
    //private List<Device> devices;

    public DeviceListAdapter(Context context, int textViewResourceId){
        super(context, textViewResourceId);
    }

    public DeviceListAdapter(Context context, int resource, List<Device> devices){
        super(context, resource, devices);
    }

    public View getView(int position, View convertView, ViewGroup parent){

        View v = convertView;

        if(v == null){
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.itemlistrow, null);
        }

        Device d = getItem(position);

        if(d != null){
            TextView tt1 = (TextView) v.findViewById(R.id.device_id);
            TextView tt2 = (TextView) v.findViewById(R.id.device_name);
            TextView tt3 = (TextView) v.findViewById(R.id.device_address);

            if(tt1 != null){
                tt1.setText(String.valueOf(d.getId()));
            }

            if(tt2 != null){
                tt2.setText(d.getName());
            }

            if(tt3 != null){
                tt3.setText(d.getMacAddress());
            }

        }
        return v;
    }







    public void addDevices(List<Device> devices){
        //this.devices = devices;
    }
}
