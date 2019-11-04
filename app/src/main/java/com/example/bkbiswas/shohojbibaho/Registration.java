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

import com.example.bkbiswas.shohojbibaho.UserInfo;

public class Registration extends AppCompatActivity {
    private EditText email, password, firstName, lastName, weight, heightFt, heightInch, contactNo, worksAt, designation;
    private Spinner day, month, year, country, religion, gender, bloodGroup;
    private Button signup;

    int UID = 0;
    SharedPreferences session;
    String yearList[] = new String[62];
    String dayList[] = new String[31];
    String bloodList[] = {"A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-"};
    String countryList[] = {"Afghanistan", "Albania", "Algeria", "Andorra", "Angola", "Anguilla", "Antigua", "Barbuda", "Argentina", "Armenia", "Aruba", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia", "Bosnia", "Herzegovina", "Botswana", "Brazil", "British Virgin Islands", "Brunei", "Bulgaria", "Burkina Faso", "Burundi", "Cambodia", "Cameroon", "Cape Verde", "Cayman Islands", "Chad", "Chile", "China", "Colombia", "Congo", "Cook Islands", "Costa Rica", "Cote D Ivoire", "Croatia", "Cruise Ship", "Cuba", "Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Estonia", "Ethiopia", "Falkland Islands", "Faroe Islands", "Fiji", "Finland", "France", "French Polynesia", "French West Indies", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Gibraltar", "Greece", "Greenland", "Grenada", "Guam", "Guatemala", "Guernsey", "Guinea", "Guinea Bissau", "Guyana", "Haiti", "Honduras", "Hong Kong", "Hungary", "Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland", "Isle of Man", "Israel", "Italy", "Jamaica", "Japan", "Jersey", "Jordan", "Kazakhstan", "Kenya", "Kuwait", "Kyrgyz Republic", "Laos", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania", "Luxembourg", "Macau", "Macedonia", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Mauritania", "Mauritius", "Mexico", "Moldova", "Monaco", "Mongolia", "Montenegro", "Montserrat", "Morocco", "Mozambique", "Namibia", "Nepal", "Netherlands", "Netherlands Antilles", "New Caledonia", "New Zealand", "Nicaragua", "Niger", "Nigeria", "Norway", "Oman", "Pakistan", "Palestine", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Poland", "Portugal", "Puerto Rico", "Qatar", "Reunion", "Romania", "Russia", "Rwanda", "Saint Pierre", "Miquelon", "Samoa", "San Marino", "Satellite", "Saudi Arabia", "Senegal", "Serbia", "Seychelles", "Sierra Leone", "Singapore", "Slovakia", "Slovenia", "South Africa", "South Korea", "Spain", "Sri Lanka", "St Kitts", "Nevis", "St Lucia", "St Vincent", "St. Lucia", "Sudan", "Suriname", "Swaziland", "Sweden", "Switzerland", "Syria", "Taiwan", "Tajikistan", "Tanzania", "Thailand", "Timor L'Este", "Togo", "Tonga", "Trinidad", "Tobago", "Tunisia", "Turkey", "Turkmenistan", "Turks", "Caicos", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "Uruguay", "Uzbekistan", "Venezuela", "Vietnam", "Virgin Islands (US)", "Yemen", "Zambia", "Zimbabwe"};
    String religionList[] = {"Christians", "Muslims", "Irreligious and atheist", "Hindus", "Buddhists", "Taoists/Confucianists/Chinese traditional religionists", "Ethnic and indigenous", "Sikhism", "Spiritism", "Judaism", "Bahá'ís", "Jainism"};
    String monthList[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

    public void changeAdapter(String[] religionList, String sreligion, Spinner religion) {
        int i = 0;
        String temp = "";
        while (true) {
            if (religionList[i].equals(sreligion)) {
                temp = religionList[i];
                for (int j = i; j >= 1; j--) religionList[j] = religionList[j - 1];
                religionList[0] = temp;
                break;
            }
            i++;
        }
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, this.religionList);
        this.religion.setAdapter(adapter);
    }

    public void changeAdapter2(String[] religionList, String sreligion, Spinner religion) {
        int i = 0;
        String temp = "";
        while (true) {
            if (religionList[i].equals(sreligion)) {
                temp = religionList[i];
                religionList[i] = religionList[0];
                religionList[0] = temp;
                break;
            }
            i++;
        }
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, this.religionList);
        this.religion.setAdapter(adapter);
    }

    private void updateProfile() {
        String semail, spassword, sfirstName, slastName, sgender, sreligion, sday, smonth, syear, sweight, sbloodGroup, sheightft,
                sheightInch, scontactNo, sworksAt, sdesignation, scountry;
        session = PreferenceManager.getDefaultSharedPreferences(this);
        DatabaseReference d;

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
        sbloodGroup = bloodGroup.getSelectedItem().toString().trim() + "";
        sreligion = religion.getSelectedItem().toString().trim() + "";
        String userName = session.getString("username", "");

        //Toast.makeText(getApplicationContext(),userName,Toast.LENGTH_SHORT).show();

        if (!validateEmail(semail) || semail.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Email is not valid!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (spassword.length() < 6) {
            Toast.makeText(getApplicationContext(), "Password must be of atleast 6 characters!", Toast.LENGTH_SHORT).show();
            return;
        }

        UserInfo u = new UserInfo(semail, spassword,
                sfirstName, slastName, sgender, sday, smonth, syear, sweight, sbloodGroup, sheightft, sheightInch, sreligion, scontactNo,
                sworksAt, sdesignation, scountry, userName);
        DatabaseReference db = FirebaseDatabase.getInstance().getReference("userInfo");
        u.setLocation(userName);
        db.child(userName).setValue(u);
        u.setSharedPreferences(this);

        session.edit().putString("ep", "0").commit();

        Intent intent = new Intent(Registration.this, Homepage.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("EXIT", true);
        startActivity(intent);
    }

    public void changeHint() {    //I will change it///
        SharedPreferences session = PreferenceManager.getDefaultSharedPreferences(this);
        String semail, spassword, sfirstName, slastName, sgender, sreligion, sday, smonth, syear, scountry, sweight, sblood, sfeet, sinch, scontact, swa, sdes;
        semail = session.getString("e", "");
        spassword = session.getString("p", "");
        sfirstName = session.getString("f", "");
        slastName = session.getString("l", "");
        sgender = session.getString("g", "");
        sreligion = session.getString("r", "");
        sday = session.getString("d", "");
        smonth = session.getString("m", "");
        syear = session.getString("y", "");
        scountry = session.getString("c", "");
        sweight = session.getString("w", "");
        sblood = session.getString("b", "");
        sfeet = session.getString("hf", "");
        sinch = session.getString("hi", "");
        scontact = session.getString("cn", "");
        swa = session.getString("wa", "");
        sdes = session.getString("des", "");

        email.setText(semail);
        password.setText(spassword);
        firstName.setText(sfirstName);
        lastName.setText(slastName);
        weight.setText(sweight);
        heightFt.setText(sfeet);
        heightInch.setText(sinch);
        contactNo.setText(scontact);
        worksAt.setText(swa);
        designation.setText(sdes);
        signup.setText("CONFIRM CHANGES");
        signup.setBackgroundColor(0xFFFF0000);

        changeAdapter2(this.religionList, sreligion, this.religion);
        changeAdapter(this.dayList, sday, this.day);
        changeAdapter(this.monthList, smonth, this.month);
        changeAdapter(this.yearList, syear, this.year);
        changeAdapter2(this.countryList, scountry, this.country);
        changeAdapter2(this.bloodList, sblood, this.bloodGroup);

        if (sgender.equals("Female")) gender.setSelection(1);
    }

    private boolean validateEmail(String data) {
        Pattern emailPattern = Pattern.compile(".+@.+\\.[a-z]+");
        Matcher emailMatcher = emailPattern.matcher(data);
        return emailMatcher.matches();
    }

    private void input() {
        String semail, spassword, sfirstName, slastName, sgender, sreligion, sday, smonth, syear, sweight, sheightft, sheightInch,
                scontactNo, sworksAt, sdesignation, sbloodGroup, scountry;
        session = PreferenceManager.getDefaultSharedPreferences(this);
        DatabaseReference d = FirebaseDatabase.getInstance().getReference("idGenerator");

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
            Toast.makeText(getApplicationContext(), "Email is not valid!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (spassword.length() < 6) {
            Toast.makeText(getApplicationContext(), "Password must be of atleast 6 characters!", Toast.LENGTH_SHORT).show();
            return;
        }

        //Toast.makeText(getApplicationContext(),sgender + " " + sreligion + " " + sday + " " + smonth + " "+ syear + " " +scountry,Toast.LENGTH_SHORT).show();

        Login login = new Login(this);
        login.saveInfo(semail, spassword, sfirstName, slastName, sgender, sday, smonth, syear, sweight, sbloodGroup, sheightft,
                sheightInch, sreligion, scontactNo, scountry, sworksAt, sdesignation);

        Intent intent = new Intent(Registration.this, Successfull.class);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration3);

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
        //////Here
        weight = findViewById(R.id.weight);
        heightFt = findViewById(R.id.heightFt);
        heightInch = findViewById(R.id.heightInch);
        contactNo = findViewById(R.id.contactNo);
        bloodGroup = findViewById(R.id.bloodGroup);
        worksAt = findViewById(R.id.worksAt);
        designation = findViewById(R.id.designaiton);

        session = PreferenceManager.getDefaultSharedPreferences(this);

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

        ArrayAdapter adapter3 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, yearList);
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
        bloodGroup.setAdapter(adapter6);

        if (session.getString("ep", "").equals("1")) {
            changeHint();
        }

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (session.getString("ep", "").equals("1")) {
                    updateProfile();
                } else {
                    input();
                }
            }
        });
        ///////////Toast.makeText(getApplicationContext(),country_list[1],Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
