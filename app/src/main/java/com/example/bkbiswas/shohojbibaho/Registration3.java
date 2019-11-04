package com.example.bkbiswas.shohojbibaho;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Registration3 extends AppCompatActivity {
    private EditText email, password, firstName, lastName, weight, heightFt, heightInch, contactNo, worksAt, designation;
    private Spinner day, month, year, country, religion, gender, bloodGroup;
    private Button signup;

    int uid = 0;
    String userName = "";
    //SharedPreferences session;
    String yearList[] = new String[62];
    String dayList[] = new String[31];
    String bloodList[] = {"A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-"};
    String countryList[] = {"Afghanistan", "Albania", "Algeria", "Andorra", "Angola", "Anguilla", "Antigua", "Barbuda", "Argentina", "Armenia", "Aruba", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia", "Bosnia", "Herzegovina", "Botswana", "Brazil", "British Virgin Islands", "Brunei", "Bulgaria", "Burkina Faso", "Burundi", "Cambodia", "Cameroon", "Cape Verde", "Cayman Islands", "Chad", "Chile", "China", "Colombia", "Congo", "Cook Islands", "Costa Rica", "Cote D Ivoire", "Croatia", "Cruise Ship", "Cuba", "Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Estonia", "Ethiopia", "Falkland Islands", "Faroe Islands", "Fiji", "Finland", "France", "French Polynesia", "French West Indies", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Gibraltar", "Greece", "Greenland", "Grenada", "Guam", "Guatemala", "Guernsey", "Guinea", "Guinea Bissau", "Guyana", "Haiti", "Honduras", "Hong Kong", "Hungary", "Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland", "Isle of Man", "Israel", "Italy", "Jamaica", "Japan", "Jersey", "Jordan", "Kazakhstan", "Kenya", "Kuwait", "Kyrgyz Republic", "Laos", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania", "Luxembourg", "Macau", "Macedonia", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Mauritania", "Mauritius", "Mexico", "Moldova", "Monaco", "Mongolia", "Montenegro", "Montserrat", "Morocco", "Mozambique", "Namibia", "Nepal", "Netherlands", "Netherlands Antilles", "New Caledonia", "New Zealand", "Nicaragua", "Niger", "Nigeria", "Norway", "Oman", "Pakistan", "Palestine", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Poland", "Portugal", "Puerto Rico", "Qatar", "Reunion", "Romania", "Russia", "Rwanda", "Saint Pierre", "Miquelon", "Samoa", "San Marino", "Satellite", "Saudi Arabia", "Senegal", "Serbia", "Seychelles", "Sierra Leone", "Singapore", "Slovakia", "Slovenia", "South Africa", "South Korea", "Spain", "Sri Lanka", "St Kitts", "Nevis", "St Lucia", "St Vincent", "St. Lucia", "Sudan", "Suriname", "Swaziland", "Sweden", "Switzerland", "Syria", "Taiwan", "Tajikistan", "Tanzania", "Thailand", "Timor L'Este", "Togo", "Tonga", "Trinidad", "Tobago", "Tunisia", "Turkey", "Turkmenistan", "Turks", "Caicos", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "Uruguay", "Uzbekistan", "Venezuela", "Vietnam", "Virgin Islands (US)", "Yemen", "Zambia", "Zimbabwe"};
    String religionList[] = {"Christians", "Muslims", "Irreligious and atheist", "Hindus", "Buddhists", "Taoists/Confucianists/Chinese traditional religionists", "Ethnic and indigenous", "Sikhism", "Spiritism", "Judaism", "Bahá'ís", "Jainism"};
    String monthList[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};


    private boolean validateEmail(String data) {
        Pattern emailPattern = Pattern.compile(".+@.+\\.[a-z]+");
        Matcher emailMatcher = emailPattern.matcher(data);
        return emailMatcher.matches();
    }

    private void input() {
        final String semail, spassword, sfirstName, slastName, sgender, sreligion, sday, smonth, syear, sweight, sheightft, sheightInch, scontactNo, sworksAt, sdesignation, sbloodGroup, scountry;
        //session = this.getApplicationContext().getSharedPreferences("user", MODE_PRIVATE);

        semail = email.getText().toString().trim();
        spassword = password.getText().toString().trim();
        sfirstName = firstName.getText().toString().trim();
        slastName = lastName.getText().toString().trim();
        sweight = weight.getText().toString().trim();
        sheightft = heightFt.getText().toString().trim();
        sheightInch = heightInch.getText().toString().trim();
        scontactNo = contactNo.getText().toString().trim();
        sworksAt = worksAt.getText().toString().trim();
        sdesignation = designation.getText().toString().trim();

        sgender = gender.getSelectedItem().toString().trim() + "";
        sday = day.getSelectedItem().toString().trim() + "";
        smonth = month.getSelectedItem().toString().trim() + "";
        syear = year.getSelectedItem().toString().trim() + "";
        scountry = country.getSelectedItem().toString().trim() + "";
        sreligion = religion.getSelectedItem().toString().trim() + "";
        sbloodGroup = bloodGroup.getSelectedItem().toString().trim() + "";

        if (!validateEmail(semail) || semail.isEmpty()) {
            email.setError("Email is not valid!");
            return;
        }

        if (spassword.isEmpty()) {
            password.setError("You must choose a password!");
            return;
        }

        if (spassword.length() < 6) {
            password.setError("Password must be at least 6 characters!");
            return;
        }

        if (sfirstName.isEmpty()) {
            firstName.setError("You can't keep this field empty!");
            return;
        }

        if (slastName.isEmpty()) {
            lastName.setError("You can't keep this field empty!");
            return;
        }

        int x = sfirstName.length();
        for(int i = 0; i<x; i++){
            if(sfirstName.charAt(i)>='A' && sfirstName.charAt(i)<='Z' || sfirstName.charAt(i)>='a' && sfirstName.charAt(i)<='z' ){

            }
            else{
                firstName.setError("Name must contain only letters!");
                return;
            }
        }

        int y = slastName.length();
        for(int i = 0; i<y; i++){
            if(slastName.charAt(i)>='A' && slastName.charAt(i)<='Z' || slastName.charAt(i)>='a' && slastName.charAt(i)<='z' ){

            }
            else{
                lastName.setError("Name must contain only letters!");
                return;
            }
        }

        if(x+y > 15){
            Toast.makeText(getApplicationContext(),"Name is too long!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(scontactNo.isEmpty()){
            contactNo.setError("You can't keep this field empty!");
            return;
        }

                userName = lowercase(sfirstName,slastName) + uid + "";

                Intent intent = new Intent(Registration3.this, Successfull.class);
                intent.putExtra("email",semail);
                intent.putExtra("password",spassword);
                intent.putExtra("firstName",sfirstName);
                intent.putExtra("lastName",slastName);
                intent.putExtra("gender",sgender);
                intent.putExtra("religion",sreligion);
                intent.putExtra("day",sday);
                intent.putExtra("month",smonth);
                intent.putExtra("year",syear);
                intent.putExtra("weight",sweight);
                intent.putExtra("heightFt",sheightft);
                intent.putExtra("heightInch",sheightInch);
                intent.putExtra("contactNo",scontactNo);
                intent.putExtra("worksAt",sworksAt);
                intent.putExtra("designation",sdesignation);
                intent.putExtra("bloodGroup",sbloodGroup);
                intent.putExtra("country",scountry);
                intent.putExtra("userName",userName);
                intent.putExtra("uid",uid+"");
                startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration3);

        final DatabaseReference d1 = FirebaseDatabase.getInstance().getReference("idGenerator");
        final DatabaseReference d2 = FirebaseDatabase.getInstance().getReference("userInfo");

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        religion = findViewById(R.id.religion);
        day = findViewById(R.id.day);
        month = findViewById(R.id.month);
        year = findViewById(R.id.year);
        country = findViewById(R.id.country);
        gender = findViewById(R.id.gender);
        signup = findViewById(R.id.signup);
        weight = findViewById(R.id.weight);
        heightFt = findViewById(R.id.heightFt);
        heightInch = findViewById(R.id.heightInch);
        contactNo = findViewById(R.id.contactNo);
        bloodGroup = findViewById(R.id.bloodGroup);
        worksAt = findViewById(R.id.worksAt);
        designation = findViewById(R.id.designaiton);

        int ID = 0;
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        for (int i = 0; i <= 3; i++) {
            int c = date.charAt(i) - 48;
            ID = ID * 10 + c;
        }

        int j = 0;
        for (int i = 1; i <= 31; i++) {
            dayList[i - 1] = i + "";
            yearList[j] = ID + "";
            j++;
            ID--;
            yearList[j] = ID + "";
            j++;
            ID--;
        }

        /*ArrayAdapter adapter3 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, yearList);
        year.setAdapter(adapter3);

        ArrayAdapter adapter4 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, dayList);
        day.setAdapter(adapter4);

        ArrayAdapter adapter5 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, monthList);
        month.setAdapter(adapter5);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, countryList);
        country.setAdapter(adapter);

        ArrayAdapter adapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, religionList);
        religion.setAdapter(adapter2);

        ArrayAdapter adapter6 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, bloodList);
        bloodGroup.setAdapter(adapter6);*/


        d1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot i: dataSnapshot.getChildren()){
                    ID id = i.getValue(ID.class);
                    uid = id.getID();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input();
            }
        });
    }









    public String lowercase(String x, String y){
        String name = "";
        int lx = x.length();
        int ly = y.length();
        char c;

        for(int i = 0; i<lx; i++){
            if(x.charAt(i) >='A' && x.charAt(i) <='Z'){
                c=x.charAt(i);
                int a = (int)c;
                a+=32;
                c = (char)a;
                name = name + c + "";
            }

            else    name = name + x.charAt(i) +"";
        }


        for(int i = 0; i<ly; i++){
            if(y.charAt(i) >='A' && y.charAt(i) <='Z'){
                c=y.charAt(i);
                int a = (int)c;
                a+=32;
                c = (char)a;
                name = name + c + "";
            }

            else    name = name + y.charAt(i) + "";
        }

        return name;
    }

}










    /*public void changeAdapter(String[] religionList, String sreligion, Spinner religion){
        int i=0;
        String temp="";
        while(true){
            if(religionList[i].equals(sreligion)){
                temp = religionList[i];
                for(int j =i; j>=1; j--) religionList[j] = religionList[j-1];
                religionList[0] = temp;
                break;
            }
            i++;
        }
        ArrayAdapter adapter = new ArrayAdapter(Registration3.this, android.R.layout.simple_spinner_item,this.religionList);
        religion.setAdapter(adapter);
    }

    public void changeAdapter2(String[] s, String x, Spinner sp){
        int i=0;
        String temp="";
        while(true){
            if(s[i].equals(x)){
                temp = s[i];
                s[i] = s[0];
                s[0] = temp;
                break;
            }
            i++;
        }
        ArrayAdapter adapter = new ArrayAdapter(Registration3.this, android.R.layout.simple_spinner_item,s);
        sp.setAdapter(adapter);
    }*/
