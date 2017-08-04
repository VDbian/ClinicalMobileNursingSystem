package com.example.fei.clinicalmobilenursingsystem.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.fei.clinicalmobilenursingsystem.R;
import com.example.fei.clinicalmobilenursingsystem.bean.MedicalOrdersBean;

import java.util.List;

public class ListViewMedicalOrdersAdapter extends BaseAdapter {
    private List<MedicalOrdersBean> list;
    private Context context;
    private LayoutInflater inflater;

    public ListViewMedicalOrdersAdapter(List<MedicalOrdersBean> list, Context context) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list == null ? null : list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HoldView holdView = null;
        if (convertView == null) {
            holdView = new HoldView();
            convertView = inflater.inflate(R.layout.item_listview_medical_orders, null);
            holdView.textViewAdviceProject = (TextView) convertView.findViewById(R.id.textView_advice_project);
            holdView.textViewDosage = (TextView) convertView.findViewById(R.id.textView_dosage);
            holdView.textViewProperty = (TextView) convertView.findViewById(R.id.textView_property);
            holdView.textViewStandard = (TextView) convertView.findViewById(R.id.textView_standard);
            holdView.textViewUnit = (TextView) convertView.findViewById(R.id.textView_unit);
            convertView.setTag(holdView);
        } else {
            holdView = (HoldView) convertView.getTag();
        }
        MedicalOrdersBean medicalOrdersBean = list.get(position);
//        if (medicalOrdersBean.is_selected()) {
//            convertView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorRed));
//        } else if (position % 2 == 0) {
//            convertView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorLightSteelBlue));
//        } else {
//            convertView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorCornflowerBlue));
//        }
        holdView.textViewDosage.setText(medicalOrdersBean.getDosage());
        holdView.textViewDosage.setTextColor(ContextCompat.getColor(context,R.color.colorMainText));
        holdView.textViewAdviceProject.setText(medicalOrdersBean.getAdvice_project());
        holdView.textViewAdviceProject.setTextColor(ContextCompat.getColor(context,R.color.colorMainText));
        holdView.textViewUnit.setText(medicalOrdersBean.getUnit());
        holdView.textViewUnit.setTextColor(ContextCompat.getColor(context,R.color.colorMainText));
        holdView.textViewStandard.setText(medicalOrdersBean.getStandard());
        holdView.textViewStandard.setTextColor(ContextCompat.getColor(context,R.color.colorMainText));
        holdView.textViewProperty.setText(medicalOrdersBean.getProperty());
        holdView.textViewProperty.setTextColor(ContextCompat.getColor(context,R.color.colorMainText));
        return convertView;
    }

    private class HoldView {
        TextView textViewProperty;
        TextView textViewAdviceProject;
        TextView textViewStandard;
        TextView textViewUnit;
        TextView textViewDosage;
    }
}

