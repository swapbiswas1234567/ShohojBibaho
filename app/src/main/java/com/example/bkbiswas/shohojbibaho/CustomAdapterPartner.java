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


public class CustomAdapterPartner extends BaseAdapter{
    List <UserInfo> stuinfo;
    Context context;
    private LayoutInflater inflater;

    public CustomAdapterPartner(Context c,List l) {
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
            view=inflater.inflate(R.layout.sampletpartner,viewGroup,false);
        }

        TextView textView1=view.findViewById(R.id.samplet1partner);
        TextView textView2=view.findViewById(R.id.samplet2partner);
        TextView textView3=view.findViewById(R.id.samplet3partner);
        TextView textView4=view.findViewById(R.id.samplet4partner);
        TextView textView5=view.findViewById(R.id.samplet5partner);
        TextView textView6=view.findViewById(R.id.samplet6partner);
        TextView textView7=view.findViewById(R.id.samplet7partner);
        Button button=view.findViewById(R.id.sampleb1partner);
        ImageView imageView=view.findViewById(R.id.sampleipartner);

        textView1.setText("Name: " + stuinfo.get(i).getFirstName() + " " + stuinfo.get(i).getLastName());
        textView2.setText("Date of birth: " + stuinfo.get(i).getDay() + " " + stuinfo.get(i).getMonth() + " " + stuinfo.get(i).getYear());
        textView3.setText("Height: " + stuinfo.get(i).getHeightFt()+"' " + stuinfo.get(i).getHeightInch() + "''");
        textView4.setText("Weight: " + stuinfo.get(i).getWeight() + " Kgs");
        textView5.setText("Religion: " + stuinfo.get(i).getReligion());
        textView6.setText("Works at: " + stuinfo.get(i).getWorksAt() + " (" + stuinfo.get(i).getDesignation() + ")");
        textView7.setText("Country: " + stuinfo.get(i).getCountry() );

        Picasso.get().load(stuinfo.get(i).getLocation()).into(imageView);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setMessage("Are you sure want to send request for this convention?");
                builder1.setCancelable(true);

                SharedPreferences session = PreferenceManager.getDefaultSharedPreferences(context);
                String sender = session.getString("name","");
                String receiver = stuinfo.get(i).getFirstName() + " " + stuinfo.get(i).getLastName();
                String worksAt = session.getString("worksAt","");
                String senderContactNo = session.getString("contactNo","");
                String senderEmail = session.getString("emailid","");
                String receiverEmail = stuinfo.get(i).getEmail();
                String dob = session.getString("dob","");
                String religion = session.getString("religion","");
                String country = session.getString("country","");
                String link = session.getString("link","");

                Notification p = new Notification(sender, receiver,worksAt,senderContactNo,senderEmail,receiverEmail);
                DatabaseReference d = FirebaseDatabase.getInstance().getReference("Partner Notification");
                String s = d.push().getKey();
                d.child(s).setValue(p);

                String subject = "Partner Request From Shohoj Bibaho!";
                String message = sender + " visited your profile & interested in you!" +"\n";
                message = message + "Contact email: " + senderEmail + "\n";
                message = message + "Phone no: " + senderContactNo +"\n";
                message = message + "Date of birth: " + dob +"\n";
                message = message + "Religion: " + religion +"\n";
                message = message + "Country: " + country +"\n";
                message = message + "Works at: " + worksAt + "\n";
                message = message + "Profile picture link: " + link +"\n";
                String mail = receiverEmail;
                //Creating SendMail object
                SendMail sm = new SendMail(context, mail, subject, message, "Your request is being sent to" + receiver + "!", "Your request is sent to " + receiver);
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
