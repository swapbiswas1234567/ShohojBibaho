package com.example.bkbiswas.shohojbibaho;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EditProfile extends AppCompatActivity {

    private EditText eemail,epassword,efirstName,elastName,eweight,eheightFt,eheightInch,econtactNo,eworksAt,edesignation;
    private Spinner sday,smonth,syear,scountry,sreligion,sgender,sbloodGroup;
    private Button change;

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String gender;
    private String day;
    private String month;
    private String year;
    private String weight;
    private String bloodGroup;
    private String heightFt;
    private String heightInch;
    private String religion;
    private String contactNo;
    private String country;
    private String worksAt;
    private String designation;
    private String currentUser;
    private String location;

    String yearList[] = new String[62];
    String dayList[] = new String[31];
    String bloodList[] = {"A+","A-","B+","B-","O+","O-","AB+","AB-"};
    String countryList[] = {"Afghanistan","Albania","Algeria","Andorra","Angola","Anguilla","Antigua", "Barbuda","Argentina","Armenia","Aruba","Australia","Austria","Azerbaijan","Bahamas","Bahrain","Bangladesh","Barbados","Belarus","Belgium","Belize","Benin","Bermuda","Bhutan","Bolivia","Bosnia", "Herzegovina","Botswana","Brazil","British Virgin Islands","Brunei","Bulgaria","Burkina Faso","Burundi","Cambodia","Cameroon","Cape Verde","Cayman Islands","Chad","Chile","China","Colombia","Congo","Cook Islands","Costa Rica","Cote D Ivoire","Croatia","Cruise Ship","Cuba","Cyprus","Czech Republic","Denmark","Djibouti","Dominica","Dominican Republic","Ecuador","Egypt","El Salvador","Equatorial Guinea","Estonia","Ethiopia","Falkland Islands","Faroe Islands","Fiji","Finland","France","French Polynesia","French West Indies","Gabon","Gambia","Georgia","Germany","Ghana","Gibraltar","Greece","Greenland","Grenada","Guam","Guatemala","Guernsey","Guinea","Guinea Bissau","Guyana","Haiti","Honduras","Hong Kong","Hungary","Iceland","India","Indonesia","Iran","Iraq","Ireland","Isle of Man","Israel","Italy","Jamaica","Japan","Jersey","Jordan","Kazakhstan","Kenya","Kuwait","Kyrgyz Republic","Laos","Latvia","Lebanon","Lesotho","Liberia","Libya","Liechtenstein","Lithuania","Luxembourg","Macau","Macedonia","Madagascar","Malawi","Malaysia","Maldives","Mali","Malta","Mauritania","Mauritius","Mexico","Moldova","Monaco","Mongolia","Montenegro","Montserrat","Morocco","Mozambique","Namibia","Nepal","Netherlands","Netherlands Antilles","New Caledonia","New Zealand","Nicaragua","Niger","Nigeria","Norway","Oman","Pakistan","Palestine","Panama","Papua New Guinea","Paraguay","Peru","Philippines","Poland","Portugal","Puerto Rico","Qatar","Reunion","Romania","Russia","Rwanda","Saint Pierre", "Miquelon","Samoa","San Marino","Satellite","Saudi Arabia","Senegal","Serbia","Seychelles","Sierra Leone","Singapore","Slovakia","Slovenia","South Africa","South Korea","Spain","Sri Lanka","St Kitts", "Nevis","St Lucia","St Vincent","St. Lucia","Sudan","Suriname","Swaziland","Sweden","Switzerland","Syria","Taiwan","Tajikistan","Tanzania","Thailand","Timor L'Este","Togo","Tonga","Trinidad", "Tobago","Tunisia","Turkey","Turkmenistan","Turks", "Caicos","Uganda","Ukraine","United Arab Emirates","United Kingdom","Uruguay","Uzbekistan","Venezuela","Vietnam","Virgin Islands (US)","Yemen","Zambia","Zimbabwe"};
    String religionList[] = {"Christians","Muslims", "Irreligious and atheist","Hindus","Buddhists", "Taoists/Confucianists/Chinese traditional religionists","Ethnic and indigenous","Sikhism","Spiritism","Judaism","Bahá'ís","Jainism"};
    String monthList[] = {"January","February","March","April","May","June","July","August","September","October","November","December"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile);

        eemail = findViewById(R.id.emailep);
        epassword = findViewById(R.id.passwordep);
        efirstName = findViewById(R.id.firstNameep);
        elastName = findViewById(R.id.lastNameep);
        sreligion = findViewById(R.id.religionep);
        sday = findViewById(R.id.dayep);
        smonth = findViewById(R.id.monthep);
        syear = findViewById(R.id.yearep);
        scountry = findViewById(R.id.countryep);
        sgender = findViewById(R.id.genderep);
        change = findViewById(R.id.signupep);
        //////Here
        eweight=findViewById(R.id.weightep);
        eheightFt=findViewById(R.id.heightFtep);
        eheightInch=findViewById(R.id.heightInchep);
        econtactNo=findViewById(R.id.contactNoep);
        sbloodGroup=findViewById(R.id.bloodGroupep);
        eworksAt=findViewById(R.id.worksAtep);
        edesignation=findViewById(R.id.designaitonep);

        int ID = 0;
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        for(int i = 0; i<=3; i++){
            int c = date.charAt(i) - 48;
            ID = ID*10 + c;
        }

        int j =0;
        for(int i =1; i<=31; i++){
            dayList[i-1] = i + "";
            yearList[j]=ID + "";
            j++;
            ID--;
            yearList[j]=ID + "";
            j++;
            ID--;
        }

        changeHint();

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateProfile();
            }
        });

        eemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eemail.setError("You can't change your email!");
            }
        });
    }















    private void changeHint(){
        final Intent intent = getIntent();
        email = intent.getStringExtra("email");
        password = intent.getStringExtra("password");
        firstName = intent.getStringExtra("firstName");
        lastName=intent.getStringExtra("lastName");
        gender = intent.getStringExtra("gender");
        religion=intent.getStringExtra("religion");
        day =intent.getStringExtra("day");
        month=intent.getStringExtra("month");
        year=intent.getStringExtra("year");
        weight=intent.getStringExtra("weight");
        heightFt=intent.getStringExtra("heightFt");
        heightInch=intent.getStringExtra("heightInch");
        contactNo=intent.getStringExtra("contactNo");
        worksAt=intent.getStringExtra("worksAt");
        designation=intent.getStringExtra("designation");
        bloodGroup=intent.getStringExtra("bloodGroup");
        country=intent.getStringExtra("country");
        currentUser=intent.getStringExtra("userName");
        location = intent.getStringExtra("location");

        eemail.setText(email);
        epassword.setText(password);
        efirstName.setText(firstName);
        elastName.setText(lastName);
        eweight.setText(weight);
        eheightFt.setText(heightFt);
        eheightInch.setText(heightInch);
        econtactNo.setText(contactNo);
        eworksAt.setText(worksAt);
        edesignation.setText(designation);

        int i = 0;

        while(true){
            if(monthList[i].equals(month)){
                smonth.setSelection(i);
                i=0;
                break;
            }
            i++;
        }
        sday.setSelection(Integer.parseInt(day)-1);
        syear.setSelection(2018-Integer.parseInt(year));

        while(true){
            if(bloodList[i].equals(bloodGroup)){
                sbloodGroup.setSelection(i);
                i=0;
                break;
            }
            i++;
        }

        while(true){
            if(religionList[i].equals(religion)){
                sreligion.setSelection(i);
                i=0;
                break;
            }
            i++;
        }

        while(true){
            if(countryList[i].equals(country)){
                scountry.setSelection(i);
                i=0;
                break;
            }
            i++;
        }

        if(gender.equals("Female")) sgender.setSelection(1);
    }


    private void updateProfile(){
        email = eemail.getText().toString().trim();
        password = epassword.getText().toString().trim();
        firstName = efirstName.getText().toString().trim();
        lastName = elastName.getText().toString().trim();
        weight = eweight.getText().toString().trim();
        heightFt = eheightFt.getText().toString().trim();
        heightInch = eheightInch.getText().toString().trim();
        contactNo = econtactNo.getText().toString().trim();
        worksAt = eworksAt.getText().toString().trim();
        designation = edesignation.getText().toString().trim();

        day = sday.getSelectedItem().toString().trim();
        month = smonth.getSelectedItem().toString().trim();
        year = syear.getSelectedItem().toString().trim();
        religion = sreligion.getSelectedItem().toString().trim();
        country = scountry.getSelectedItem().toString().trim();

        if (password.isEmpty()) {
            epassword.setError("You must choose a password!");
            return;
        }

        if (password.length() < 6) {
            epassword.setError("Password must be at least 6 characters!");
            return;
        }

        if (firstName.isEmpty()) {
            efirstName.setError("You can't keep this field empty!");
            return;
        }

        if (lastName.isEmpty()) {
            elastName.setError("You can't keep this field empty!");
            return;
        }

        int x = firstName.length();
        for(int i = 0; i<x; i++){
            if(firstName.charAt(i)>='A' && firstName.charAt(i)<='Z' || firstName.charAt(i)>='a' && firstName.charAt(i)<='z' ){

            }
            else{
                efirstName.setError("Name must contain only letters!");
                return;
            }
        }

        int y = lastName.length();
        for(int i = 0; i<y; i++){
            if(lastName.charAt(i)>='A' && lastName.charAt(i)<='Z' || lastName.charAt(i)>='a' && lastName.charAt(i)<='z' ){

            }
            else{
                elastName.setError("Name must contain only letters!");
                return;
            }
        }

        if(x+y > 15){
            Toast.makeText(getApplicationContext(),"Name is too long!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(contactNo.isEmpty()){
            econtactNo.setError("You can't keep this field empty!");
            return;
        }

        UserInfo u = new UserInfo(email, password, firstName, lastName, gender, day, month, year, weight, bloodGroup, heightFt, heightInch, religion, contactNo, country, worksAt, designation, currentUser);
        u.setLocation(location);
        DatabaseReference d = FirebaseDatabase.getInstance().getReference("userInfo");
        d.child(currentUser).setValue(u);

        Intent intent = new Intent(EditProfile.this, Homepage.class);
        startActivity(intent);
    }



   /* private void updateProfile(){
        String semail,spassword,sfirstName,slastName,sgender,sreligion,sday,smonth,syear,sweight,sbloodGroup,sheightft,
                sheightInch,scontactNo,sworksAt,sdesignation,scountry;
        SharedPreferences session = this.getApplicationContext().getSharedPreferences("user", MODE_PRIVATE);
        DatabaseReference d;

        semail = email.getText().toString().trim();
        spassword = password.getText().toString().trim();
        sfirstName = firstName.getText().toString().trim();
        slastName = lastName.getText().toString().trim();
        sweight  = weight.getText().toString().trim();
        sheightft  = heightFt.getText().toString().trim();
        sheightInch = heightInch.getText().toString().trim();
        scontactNo = contactNo.getText().toString().trim();
        sworksAt = worksAt.getText().toString().trim();
        sdesignation = designation.getText().toString().trim();

        sgender = gender.getSelectedItem().toString().trim() + "";
        sday = day.getSelectedItem().toString().trim() + "";
        smonth = month.getSelectedItem().toString().trim() +"";
        syear = year.getSelectedItem().toString().trim()+ "";
        scountry = country.getSelectedItem().toString().trim()+"";
        sbloodGroup = bloodGroup.getSelectedItem().toString().trim()+"";
        sreligion = religion.getSelectedItem().toString().trim()+"";
        String userName = session.getString("username","");

        //Toast.makeText(getApplicationContext(),userName,Toast.LENGTH_SHORT).show();

        if(!validateEmail(semail) || semail.isEmpty())  {
            Toast.makeText(getApplicationContext(),"Email is not valid!",Toast.LENGTH_SHORT).show();
            return;
        }

        if(spassword.length()<6){
            Toast.makeText(getApplicationContext(),"Password must be of atleast 6 characters!",Toast.LENGTH_SHORT).show();
            return;
        }

        com.example.bkbiswas.shohojbibaho.UserInfo u = new com.example.bkbiswas.shohojbibaho.UserInfo(semail, spassword,
                sfirstName,slastName,sgender,sday,smonth,syear,sweight,sbloodGroup,sheightft,sheightInch,sreligion,scontactNo,
                scountry,sworksAt,sdesignation,userName);
        DatabaseReference db = FirebaseDatabase.getInstance().getReference("userInfo");
        u.setLocation(userName);
        db.child(userName).setValue(u);
        u.setSharedPreferences(this);

        Intent intent = new Intent(EditProfile.this, Homepage.class);
        //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //intent.putExtra("EXIT", true);
        startActivity(intent);
    }


    private boolean validateEmail(String data){
        Pattern emailPattern = Pattern.compile(".+@.+\\.[a-z]+");
        Matcher emailMatcher = emailPattern.matcher(data);
        return emailMatcher.matches();
    }*/
}
