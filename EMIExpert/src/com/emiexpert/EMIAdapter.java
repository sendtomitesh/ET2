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
			holder.tvMonth =(TextView)convertView.findViewById(R.id.tvMonth);
			holder.tvYear=(TextView)convertView.findViewById(R.id.tvYear);
			holder.tvMonthofYear=(TextView)convertView.findViewById(R.id.tvMonthofYear);			
			convertView.setTag(holder);
		}else{
			holder=(ViewHolder) convertView.getTag();
		}	
		holder.tvEMI.setText("EMI: " +String.valueOf(emimonths.get(position).getEmi()));		
		holder.tvOpeningBal.setText("Rs. " + String.valueOf(emimonths.get(position).getOpeningBal()));
		holder.tvInterestPaid.setText("Int: Rs." + String.valueOf(emimonths.get(position).getInterest()));
		holder.tvPrincipalPaid.setText("Pri: Rs." + String.valueOf(emimonths.get(position).getPrinciple()));
		holder.tvMonth.setText(String.valueOf(position+1));		
		holder.tvYear.setText(String.valueOf((position+1)/12)+"Y");
		holder.tvMonthofYear.setText(String.valueOf((position+1)%12)+"M");
		if (MainActivity.mLoan.isPartPaymentInthisMonth(position)) {
			holder.tvMonth.setBackgroundResource(R.drawable.month_back_selected);
		}
		else{
			holder.tvMonth.setBackgroundResource(R.drawable.month_back_default);
		}
		return convertView;
	}

	public class ViewHolder
	{
		TextView tvOpeningBal,tvEMI,tvInterestPaid,tvPrincipalPaid,tvMonth,tvYear,tvMonthofYear;
	}
}
