package com.example.testapp2;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testapp2.adapters.OtherFieldAdpter;
import com.example.testapp2.models.AdpterContectModel;
import com.example.testapp2.models.OtherFieldModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.Manifest.permission.CALL_PHONE;

public class ScrollingActivity extends AppCompatActivity {


    //Declaration
    private static final String TAG = ScrollingActivity.class.getSimpleName();
    private final static int CALL_PHONE_PERMISSION = 27;

    //Views
    @BindView(R.id.txtName)
    TextView txtName;
    @BindView(R.id.txtAddress)
    TextView txtAddress;
    @BindView(R.id.txtParentDetails)
    TextView txtParentDetails;
    /*  @BindView(R.id.rvContacts)
      RecyclerView rvContacts;*/
    @BindView(R.id.rvOtherFields)
    RecyclerView rvOtherFields;
    @BindView(R.id.txtTakenValue)
    TextView txtTakenValue;
    @BindView(R.id.txtPValue)
    TextView txtPValue;
    @BindView(R.id.txtAValue)
    TextView txtAValue;
    @BindView(R.id.txtPercentageValue)
    TextView txtPercentageValue;
    @BindView(R.id.txtNoOfExamTakenValue)
    TextView txtNoOfExamTakenValue;
    @BindView(R.id.txtExamAppeardValue)
    TextView txtExamAppeardValue;
    @BindView(R.id.txtAvgPerformanceValue)
    TextView txtAvgPerformanceValue;

    @BindView(R.id.civProfilePic)
    CircleImageView civProfilePic;
    @BindView(R.id.app_bar)
    AppBarLayout mAppBarLayout;
    @BindView(R.id.include)
    NestedScrollView include;

    @BindView(R.id.constraintCard1)
    CardView constraintCard1;
    @BindView(R.id.constraintCard2)
    CardView constraintCard2;
    @BindView(R.id.constraintCard3)
    CardView constraintCard3;
    @BindView(R.id.constraintCard4)
    CardView constraintCard4;

    @BindView(R.id.txtParent1Name)
    TextView txtParent1Name;
    @BindView(R.id.txtParent1Contact)
    TextView txtParent1Contact;
    @BindView(R.id.txtParent2Name)
    TextView txtParent2Name;
    @BindView(R.id.txtParent2Contact)
    TextView txtParent2Contact;
    @BindView(R.id.txtAttendanceViewMore)
    TextView txtAttendanceViewMore;
    @BindView(R.id.txtExamViewMore)
    TextView txtExamViewMore;
    @BindView(R.id.parent1ContectCard)
    CardView parent1ContectCard;
    @BindView(R.id.parent2ContectCard)
    CardView parent2ContectCard;


    //Custom Objects
    private OtherFieldAdpter mOtherFieldAdpter;
    private Context mContext;
    private Activity mActivity;

    //Data types and variable
    private int pageNumber = 1;
    private boolean toLoadMorePages = true;
    private boolean isLoading = false;
    private int firstVisibleInListview = 0;
    private static final int PERCENTAGE_TO_ANIMATE_AVATAR = 20;
    private boolean mIsAvatarShown = true;
    private int mMaxScrollSize;
    private boolean isParent1Clicked = false;


    //Collections
    private List<AdpterContectModel> mListContect;
    private List<OtherFieldModel> mListOtherField;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        ButterKnife.bind(this);



        mAppBarLayout.addOnOffsetChangedListener(mOnOffsetChangedListener3);


        include.setSmoothScrollingEnabled(true);
        include.getMaxScrollAmount();
        include.setFocusable(true);

        mListContect = new ArrayList<>();
        mListContect.add(new AdpterContectModel("kemar Desai", "9999999999"));
        mListContect.add(new AdpterContectModel("Ramaben", "8888888888"));

        mListOtherField = new ArrayList<>();
        mListOtherField.add(new OtherFieldModel("DOB", "23-04-2006"));
        mListOtherField.add(new OtherFieldModel("DOB", "23-04-2006"));
        mListOtherField.add(new OtherFieldModel("DOB", "23-04-2006"));
        mListOtherField.add(new OtherFieldModel("DOB", "23-04-2006"));
        mListOtherField.add(new OtherFieldModel("DOB", "23-04-2006"));
        mListOtherField.add(new OtherFieldModel("DOB", "23-04-2006"));
        mListOtherField.add(new OtherFieldModel("Cast", "Hindu"));
        mListOtherField.add(new OtherFieldModel("Cast", "Hindu"));
        mListOtherField.add(new OtherFieldModel("Cast", "Hindu"));
        mListOtherField.add(new OtherFieldModel("Cast", "Hindu"));
        mListOtherField.add(new OtherFieldModel("Cast", "Hindu"));
        mListOtherField.add(new OtherFieldModel("Cast", "Hindu"));
        mListOtherField.add(new OtherFieldModel("Cast", "Hindu"));
        mListOtherField.add(new OtherFieldModel("Cast", "Hindu"));
        mListOtherField.add(new OtherFieldModel("Cast", "Hindu"));
        mListOtherField.add(new OtherFieldModel("Cast", "Hindu"));

        initialization();
        layoutEffects();

      /*  LinearLayoutManager contectLayoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rvContacts.setLayoutManager(contectLayoutManager);
        ContectsAdpter contectsAdpter = new ContectsAdpter(this, mListContect);
        rvContacts.setAdapter(contectsAdpter);*/


    }


    private void initialization() {

        mContext = ScrollingActivity.this;
        mActivity = ScrollingActivity.this;

        ButterKnife.bind(this);

        mOtherFieldAdpter = new OtherFieldAdpter(this, mListOtherField);
        rvOtherFields.setLayoutManager(new GridLayoutManager(this, 2));
        rvOtherFields.setAdapter(mOtherFieldAdpter);


    }

    private void layoutEffects() {
        //civProfilePic.startAnimation(AnimationUtils.loadAnimation(this, R.anim.scrolanimation));

        //to give fade effect on card one by one when app start
        constraintCard1.startAnimation(AnimationUtils.loadAnimation(this, R.anim.scrolanimation));
        constraintCard2.setAlpha(0);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms

                constraintCard2.startAnimation(AnimationUtils.loadAnimation(ScrollingActivity.this, R.anim.scrolanimation));
                constraintCard2.setAlpha(1);
            }
        }, 500);


        //to overlap first card on appbar layout
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) include.getLayoutParams();
        AppBarLayout.ScrollingViewBehavior behavior = (AppBarLayout.ScrollingViewBehavior) params.getBehavior();
       // behavior.setOverlayTop(160);

    }

    /**
     * ------------------------------Give Effect On Profile Pic---------------------------------------------------
     */

    // Give Zoom effect according to scroll
    AppBarLayout.OnOffsetChangedListener mOnOffsetChangedListener3 = new AppBarLayout.OnOffsetChangedListener() {
        @Override
        public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
            float percentage = 1 - ((float) Math.abs(verticalOffset) / appBarLayout.getTotalScrollRange());
            Log.d("TAG", "==== onOffsetChanged: " + percentage);
            //mAppCompatImageView.setAlpha(percentage);
            civProfilePic.setScaleX(percentage);
            civProfilePic.setScaleY(percentage);
            //mAppCompatImageView.setScaleY(percentage/100);
        }
    };

    // Give Pop up type zoom effect
    AppBarLayout.OnOffsetChangedListener mOnOffsetChangedListener = new AppBarLayout.OnOffsetChangedListener() {
        @Override
        public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
            if (mMaxScrollSize == 0)
                mMaxScrollSize = appBarLayout.getTotalScrollRange();
            Log.d("", "onOffsetChanged: ----------------->" + mMaxScrollSize);
            int percentage = (Math.abs(i)) * 1000 / mMaxScrollSize;
            if (percentage >= PERCENTAGE_TO_ANIMATE_AVATAR && mIsAvatarShown) {
                mIsAvatarShown = false;

                civProfilePic.animate()
                        .scaleY(0).scaleX(0)
                        .setDuration(300)
                        .start();
            }
            if (percentage <= PERCENTAGE_TO_ANIMATE_AVATAR && !mIsAvatarShown) {
                mIsAvatarShown = true;

                civProfilePic.animate()
                        .scaleY(1).scaleX(1)
                        //.setDuration(300)
                        .start();
            }
        }
    };

    //Give Fade Effect according to Scroll
    AppBarLayout.OnOffsetChangedListener mOnOffsetChangedListener1 = new AppBarLayout.OnOffsetChangedListener() {
        @Override
        public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
            verticalOffset = Math.abs(verticalOffset);
            float halfScrollRange = (int) (appBarLayout.getTotalScrollRange() * 0.5f);
            float ratio = (float) verticalOffset / halfScrollRange;
            ratio = Math.max(0f, Math.min(1f, ratio));
            ratio = 1 - ratio;
            ViewCompat.setAlpha(civProfilePic, ratio);

        }
    };


    /**
     * ---------------------------------------Give menu Setting in toolbar--------------------------------------
     *
     * @param menu
     * @return
     */


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    /**
     * ----------------------This method will check call permission while user try to call any Inquiry person.---------------
     */


    public void getCallPermission() {

        Log.e(TAG, "getCallPermission: ");
        if (ContextCompat.checkSelfPermission(mContext, CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(ScrollingActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION)) {
                ActivityCompat.requestPermissions(ScrollingActivity.this, new String[]{Manifest.permission.CALL_PHONE}, CALL_PHONE_PERMISSION);
            } else {
                ActivityCompat.requestPermissions(ScrollingActivity.this, new String[]{Manifest.permission.CALL_PHONE}, CALL_PHONE_PERMISSION);
            }
        } else {
            Log.e(TAG, "permission already granted");
            makeACall();
        }
    }

    /**
     * This method will initiate call from default caller.
     */
    private void makeACall() {

        String phoneNumber = "";
        String parent1PhoneNumber = "";
        String parent2PhoneNumber = "";

        txtParent1Contact.setText("7405292830");
        parent1PhoneNumber = txtParent1Contact.getText().toString();
        txtParent2Contact.setText("8980614147");
        parent2PhoneNumber = txtParent2Contact.getText().toString();

        if (!parent1PhoneNumber.isEmpty() || !parent2PhoneNumber.isEmpty()) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(ScrollingActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION)) {
                    ActivityCompat.requestPermissions(ScrollingActivity.this, new String[]{Manifest.permission.CALL_PHONE}, CALL_PHONE_PERMISSION);
                } else {
                    ActivityCompat.requestPermissions(ScrollingActivity.this, new String[]{Manifest.permission.CALL_PHONE}, CALL_PHONE_PERMISSION);
                }
            } else {
                Log.e(TAG, "permission already granted");
                if(isParent1Clicked == true) {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + parent1PhoneNumber));
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + parent2PhoneNumber));
                    startActivity(intent);
                }

            }
            return;
        } else {
            Toast.makeText(mContext, getString(R.string.contactNotAvailable), Toast.LENGTH_SHORT).show();
            return;

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case CALL_PHONE_PERMISSION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.e(TAG, "permission granted");
                    makeACall();
                } else {
                    Toast.makeText(ScrollingActivity.this, "Permission denied!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }


    @OnClick({R.id.parent1ContectCard, R.id.parent2ContectCard,R.id.txtAttendanceViewMore, R.id.txtExamViewMore})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txtAttendanceViewMore:
                Toast.makeText(mContext, "hi", Toast.LENGTH_SHORT).show();
                break;
            case R.id.txtExamViewMore:
                Toast.makeText(mContext, "hi hi", Toast.LENGTH_SHORT).show();
                break;
            case R.id.parent1ContectCard:
                isParent1Clicked= true;
                getCallPermission();


                break;
            case R.id.parent2ContectCard:
                isParent1Clicked = false;
                getCallPermission();
                break;
        }
    }
}
