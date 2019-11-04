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

public class CustomAdapterCatering extends BaseAdapter{
    List <CateringInfo> stuinfo;
    Context context;
    private LayoutInflater inflater;

    public CustomAdapterCatering(Context c,List l) {
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
            view=inflater.inflate(R.layout.samplecat,viewGroup,false);
        }

        TextView textView1=view.findViewById(R.id.samplet1cat);
        TextView textView2=view.findViewById(R.id.samplet2cat);
        TextView textView3=view.findViewById(R.id.samplet3cat);
        TextView textView4=view.findViewById(R.id.samplet4cat);
        TextView textView5=view.findViewById(R.id.samplet5cat);
        Button button=view.findViewById(R.id.sampleb1cat);
        ImageView imageView=view.findViewById(R.id.sampleiCat);

        textView1.setText("Catering Name: " + stuinfo.get(i).getCateringName());
        textView2.setText("Mobile No: " + stuinfo.get(i).getContactNo());
        textView3.setText("Service Charge: " + stuinfo.get(i).getChargePerWaiter() + " tk");
        textView4.setText("Item Name: " + stuinfo.get(i).getItemName());
        textView5.setText("Item Price: " + stuinfo.get(i).getPrice() + " tk");

        Picasso.get().load(stuinfo.get(i).getUri()).into(imageView);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setMessage("Are you sure want to send request for this catering?");
                builder1.setCancelable(true);

                SharedPreferences session = PreferenceManager.getDefaultSharedPreferences(context);
                String sender = session.getString("name","");
                String senderEmail = session.getString("emailid","");
                String receiverEmail = stuinfo.get(i).getEmail();
                String receiver = stuinfo.get(i).getOwner();
                String itemName = stuinfo.get(i).getItemName();
                String senderContactNo = session.getString("contactNo","");

                Notification c = new Notification(sender, receiver,itemName,senderContactNo,senderEmail,receiverEmail);
                DatabaseReference d = FirebaseDatabase.getInstance().getReference("Catering Notification");
                String s = d.push().getKey();
                d.child(s).setValue(c);

                String subject = "Shohoj Bibaho Catering Booking Request";
                String message = "You received a catering request from " + sender +"\n";
                message = message + "Customer email: " + senderEmail + "\n";
                message = message + "Customer contact no: " + senderContactNo +"\n";
                message = message + "Ordered Item: " + stuinfo.get(i).getItemName();
                String mail = receiverEmail;
                //Creating SendMail object
                SendMail sm = new SendMail(context, mail, subject, message, "Your catering request is in queue!", "Your order is placed!");

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




    private void sendEmail() {
        //Getting content for email
        //String email = "kanak113331@gmail.com";

    }
}
