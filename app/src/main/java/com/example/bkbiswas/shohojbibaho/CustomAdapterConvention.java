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

public class CustomAdapterConvention extends BaseAdapter{
    List <ConventionInfo> stuinfo;
    Context context;
    private LayoutInflater inflater;

    public CustomAdapterConvention(Context c,List l) {
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
            view=inflater.inflate(R.layout.samplecon,viewGroup,false);
        }

        TextView textView1=view.findViewById(R.id.samplet1con);
        TextView textView2=view.findViewById(R.id.samplet2con);
        TextView textView3=view.findViewById(R.id.samplet3con);
        TextView textView4=view.findViewById(R.id.samplet4con);
        TextView textView5=view.findViewById(R.id.samplet5con);
        TextView textView6=view.findViewById(R.id.samplet6con);
        TextView textView7=view.findViewById(R.id.samplet7con);
        Button button=view.findViewById(R.id.sampleb1con);
        ImageView imageView=view.findViewById(R.id.sampleiCon);

        textView1.setText("Convention Name: " + stuinfo.get(i).getConvName());
        textView2.setText("Mobile No: " + stuinfo.get(i).getContactNo());
        textView3.setText("Location: " + stuinfo.get(i).getLocation());
        textView4.setText("Area: " + stuinfo.get(i).getArea() + " sq ft");
        textView5.setText("People Capacity: " + stuinfo.get(i).getCapPeople());
        textView6.setText("Decoration Charge Per Person: " + stuinfo.get(i).getDecCharge() + " tk");
        textView7.setText("Floor Cost: " + stuinfo.get(i).getFloorCost() + " tk");

        Picasso.get().load(stuinfo.get(i).getUri()).into(imageView);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setMessage("Are you sure want to send request for this convention?");
                builder1.setCancelable(true);

                SharedPreferences session = PreferenceManager.getDefaultSharedPreferences(context);
                String sender = session.getString("name","");
                String receiver = stuinfo.get(i).getOwner();
                String conventionName = stuinfo.get(i).getConvName();
                String senderContactNo = session.getString("contactNo","");
                String senderEmail = session.getString("emailid","");
                String receiverEmail = stuinfo.get(i).getEmail();

                Notification c = new Notification(sender, receiver,conventionName,senderContactNo,senderEmail,receiverEmail);
                DatabaseReference d = FirebaseDatabase.getInstance().getReference("Convention Notification");
                String s = d.push().getKey();
                d.child(s).setValue(c);

                String subject = "Shohoj Bibaho Convention Booking Request";
                String message = "You received a convention booking request from " + sender +"\n";
                message = message + "Customer email: " + senderEmail + "\n";
                message = message + "Customer contact no: " + senderContactNo +"\n";
                message = message + "Requested convention centre: " + stuinfo.get(i).getConvName();
                String mail = receiverEmail;
                //Creating SendMail object
                SendMail sm = new SendMail(context, mail, subject, message, "Your convention booking request is in queue!", "Your booking order is placed!");
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
