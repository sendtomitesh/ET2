package com.emiexpert;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
public class EMIAdapter extends BaseAdapter{
	ArrayList<EmiMonth> emimonths;
	private LayoutInflater li;
	EMIAdapter(Context mContext, ArrayList<EmiMonth> emis)
	{
		this.emimonths=emis;
		li=LayoutInflater.from(mContext);
	}
	
	@Override
	public int getCount() {
		return emimonths.size();
	}

	@Override
	public Object getItem(int i) {
		return emimonths.get(i);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		ViewHolder holder;
		if(convertView == null)
		{
			convertView=li.inflate(R.layout.pp_list_item, null);
			holder=new ViewHolder();
			holder.tvEMI=(TextView)convertView.findViewById(R.id.tvemi);
			holder.tvOpeningBal=(TextView)convertView.findViewById(R.id.tvopeningbal);
			holder.tvInterestPaid=(TextView)convertView.findViewById(R.id.tvInterestPaid);
			holder.tvPrincipalPaid=(TextView)convertView.findViewById(R.id.tvPrincipalPaid);
			convertView.setTag(holder);
		}else{
			holder=(ViewHolder) convertView.getTag();
		}	
		holder.tvEMI.setText(String.valueOf(emimonths.get(position).getEmi()));		
		holder.tvOpeningBal.setText(String.valueOf(emimonths.get(position).getOpeningBal()));
		holder.tvInterestPaid.setText(String.valueOf(emimonths.get(position).getInterest()));
		holder.tvPrincipalPaid.setText(String.valueOf(emimonths.get(position).getPrinciple()));
		return convertView;
	}

	public class ViewHolder
	{
		TextView tvOpeningBal,tvEMI,tvInterestPaid,tvPrincipalPaid;
	}
}
