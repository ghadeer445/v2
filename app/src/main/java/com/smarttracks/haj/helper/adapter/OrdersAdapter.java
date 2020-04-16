package com.v_2.haj.helper.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.v_2.haj.HomeActivity;
import com.v_2.haj.R;
import com.v_2.haj.app.AppConfig;
import com.v_2.haj.helper.Models.Order;
import com.v_2.haj.helper.SQLiteHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.MyViewHolder> {

    private List<Order> ordersList;
    private View vv;
    Context ctx;
    private SQLiteHandler db;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView user_name, delivary_date, tank_size, address, payment_type, mobile_number, status, date;
        public ImageButton deleteButton;
        public Button pendingButton, finishedButton, rejectedButton;

        public MyViewHolder(View view) {
            super(view);
            vv = view;
//            user_name = (TextView) view.findViewById(R.id.user_name);
//            delivary_date = (TextView) view.findViewById(R.id.delivary_date);
//            rejectedButton = (Button) view.findViewById(R.id.rejectedButton);
//            deleteButton = (ImageButton) view.findViewById(R.id.deleteButton);
        }
    }


    public OrdersAdapter(Context ctx, List<Order> ordersList) {
        this.ordersList = ordersList;
        this.ctx = ctx;
        db = new SQLiteHandler(ctx);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = null;

//        itemView = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.orders_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Order order = ordersList.get(position);
        holder.user_name.setText(order.getUser_name());
        holder.delivary_date.setText(order.getDelivary_date());
        holder.tank_size.setText(order.getTank_size());
        holder.address.setText(order.getAddress());
        holder.payment_type.setText(order.getPayment_type());
        holder.mobile_number.setText(order.getMobile_number());


//        holder.status.setText(Html.fromHtml("<font color='#0000EE'>Pending</font>"));
//        holder.pendingButton.setBackgroundResource(R.drawable.selected_buttonshape);


        holder.date.setText(order.getCreated_date());
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                new AlertDialog.Builder(ctx)
//                        .setTitle(R.string.delete)
//                        .setMessage(R.string.sure_delete)
//                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//                                delete(order.getId());
//                            }
//                        })
//                        .setNegativeButton(android.R.string.no, null)
//                        .setIcon(android.R.drawable.ic_dialog_alert)
//                        .show();

            }
        });
        holder.rejectedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                holder.pendingButton.setBackgroundResource(R.drawable.buttonshape);
            }
        });

    }

    @Override
    public int getItemCount() {
        return ordersList.size();
    }

    private void delete(final String id) {
        AndroidNetworking.post(AppConfig.API)
                .addBodyParameter("action", "delete_request")
                .addBodyParameter("id", id)
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            int status = response.getInt("status");
                            String msg = response.getString("msg");
                            if (status == 1) {
                                Toast.makeText(ctx,
                                        msg, Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(ctx,
                                        HomeActivity.class);
                                ctx.startActivity(intent);
                            } else {
                                Toast.makeText(ctx,
                                        msg, Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            Toast.makeText(ctx, "Error in json obj! " + e.getMessage(), Toast.LENGTH_LONG).show();

                        }
                    }

                    @Override
                    public void onError(ANError error) {
                        Toast.makeText(ctx, "Error while calling api! " + error.getErrorBody(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void update(final String id, final String status) {
        AndroidNetworking.post(AppConfig.API)
                .addBodyParameter("action", "update_request_status")
                .addBodyParameter("id", id)
                .addBodyParameter("status", status)
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            int status = response.getInt("status");
                            String msg = response.getString("msg");
                            if (status == 1) {
                                Toast.makeText(ctx,
                                        msg, Toast.LENGTH_LONG).show();
                                notifyDataSetChanged();
                            } else {
                                Toast.makeText(ctx,
                                        msg, Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            Toast.makeText(ctx, "Error in json obj! " + e.getMessage(), Toast.LENGTH_LONG).show();

                        }
                    }

                    @Override
                    public void onError(ANError error) {
                        Toast.makeText(ctx, "Error while calling api! " + error.getErrorBody(), Toast.LENGTH_LONG).show();
                    }
                });
    }
}