package com.example.bkbiswas.shohojbibaho;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapterCar extends BaseAdapter{
    List <CarInfo> stuinfo;
    Context context;
    private LayoutInflater inflater;

    public CustomAdapterCar(Context c,List l) {
        this.context=c;
        this.stuinfo = l;
    }

    @Override
    public int getCount() {
        return stuinfo.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        if(view==null){
            inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(R.layout.samplecar,viewGroup,false);
        }

        TextView textView1=view.findViewById(R.id.samplet1car);
        TextView textView2=view.findViewById(R.id.samplet2car);
        TextView textView3=view.findViewById(R.id.samplet3car);
        TextView textView4=view.findViewById(R.id.samplet4car);
        TextView textView5=view.findViewById(R.id.samplet5car);
        Button button=view.findViewById(R.id.sampleb1car);
        ImageView imageView=view.findViewById(R.id.sampleiCar);

        textView1.setText("Owner: " + stuinfo.get(i).getOwner());
        textView2.setText("Mobile No: " + stuinfo.get(i).getContactNo());
        textView3.setText("Type: " + stuinfo.get(i).getCarType());
        textView4.setText("No Of Seats: " + stuinfo.get(i).getNoOfSeats());
        textView5.setText("Costing Per Hour: " + stuinfo.get(i).getExpectedAmountperHour() + " tk");

        Picasso.get().load(stuinfo.get(i).getUri()).into(imageView);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setMessage("Are you sure want to send request for this car?");
                builder1.setCancelable(true);

                SharedPreferences session = PreferenceManager.getDefaultSharedPreferences(context);
                String sender = session.getString("name","");
                String receiver = stuinfo.get(i).getOwner();
                String carNo = stuinfo.get(i).getCarRegistrationNo();
                String senderContactNo = session.getString("contactNo","");
                String senderEmail = session.getString("emailid","");
                String receiverEmail = stuinfo.get(i).getEmail();

                Notification c = new Notification(sender, receiver,carNo,senderContactNo,senderEmail,receiverEmail);
                DatabaseReference d = FirebaseDatabase.getInstance().getReference("Car Notification");
                String s = d.push().getKey();
                d.child(s).setValue(c);

                String subject = "Shohoj Bibaho Car Booking Request";
                String message = "You received a car booking request from " + sender +"\n";
                message = message + "Customer email: " + senderEmail + "\n";
                message = message + "Customer contact no: " + senderContactNo +"\n";
                message = message + "Requested car no: " + stuinfo.get(i).getCarRegistrationNo();
                String mail = receiverEmail;
                //Creating SendMail object
                SendMail sm = new SendMail(context, mail, subject, message, "Your car request is in queue!", "Your order is placed!");

                sm.execute();

                /*builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                AlertDialog alert11 = builder1.create();
                                alert11.show();


                                dialog.cancel();
                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();*/
            }
        });

        return  view;
    }
}
