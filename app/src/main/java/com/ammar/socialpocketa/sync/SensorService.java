package com.ammar.socialpocketa.sync;

import android.app.Notification;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Icon;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.widget.Toast;

import com.ammar.socialpocketa.R;
import com.ammar.socialpocketa.RetrofitClient;
import com.ammar.socialpocketa.data.DatabaseHelper;
import com.ammar.socialpocketa.Utils.DbImageUtil;
import com.ammar.socialpocketa.models.Mention;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SensorService extends Service {

    private static final String TAG = "SensorService";


    //private HomeAdapter adapter;
    private static List<Mention> postList;

    //vars
//    private static int noOfTweets = 0;

    private List<String> m_Ids = new ArrayList<>();

    //private ArrayList<String> mNames = new ArrayList<>();
    private List<String> mNames = new ArrayList<>();

//    private ArrayList<String> mImages = new ArrayList<>();
//
//    private ArrayList<String> mTimes = new ArrayList<>();
    //private ArrayList<String> mTweets = new ArrayList<>();

    //private ArrayList<String> mRTweets = new ArrayList<>();
    private List<String> mTweets = new ArrayList<>();
    private List<String> mSentiments = new ArrayList<>();

    private List<Boolean> mRetweeteds = new ArrayList<>();
    private List<String> mCreatedAt = new ArrayList<>();
    private List<String> mProfileImageUrls = new ArrayList<>();
    private List<String> mRetweetCounts = new ArrayList<>();
    private List<String> mFavoriteCounts = new ArrayList<>();
    private List<Boolean> mFavoriteds = new ArrayList<>();


    public int counter=0;

    String token = "";

    byte[] byteImage;

    int x = 0;

    Bitmap tempBitmap;

    Bitmap tempBitmapCircular;



//    Context mCon;

    DatabaseHelper mDatabaseHelper;


    public SensorService(Context applicationContext) {
        super();
        Log.i("HERE", "here I am!");

//        mCon = applicationContext;

//        mDatabaseHelper = new DatabaseHelper(getApplicationContext());

    }

    public SensorService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);


        Log.d(TAG, "onStartCommand: Inside onStartCommand");

        startTimer();

        

//        if () {
//            token = SharedPrefManager.getKeyToken();
//        }

        return START_STICKY;
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("EXIT", "ondestroy!");
        Intent broadcastIntent = new Intent(this, SensorRestarterBroadcastReceiver.class);

//        String token = SharedPrefManager.getKeyToken();
        broadcastIntent.putExtra("authToken", token);

        sendBroadcast(broadcastIntent);
        stoptimertask();

    }

    private Timer timer;
    private TimerTask timerTask;
    long oldTime=0;
    public void startTimer() {

        Log.d(TAG, "startTimer: Inside startTimer");
        
        //set a new Timer
        timer = new Timer();

        //initialize the TimerTask's job
        initializeTimerTask();

        //schedule the timer, to wake up every 1 second or so...
        timer.schedule(timerTask, 1000, 1000 * 10);
    }

    /**
     * it sets the timer to print the counter every x seconds
     */
    public void initializeTimerTask() {
        timerTask = new TimerTask() {
            public void run() {
                Log.i("in timer", "in timer ++++  "+ (counter++));

                mDatabaseHelper = new DatabaseHelper(getApplicationContext());

                apiResponse();

                displayDataFromDb();


            }
        };
    }

    /**
     * not needed
     */
    public void stoptimertask() {
        //stop the timer, if it's not already null
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }



//    @Override
//    public void onTaskRemoved(Intent rootIntent){
//        Intent restartServiceIntent = new Intent(getApplicationContext(), this.getClass());
//        restartServiceIntent.setPackage(getPackageName());
//
//        PendingIntent restartServicePendingIntent = PendingIntent.getService(getApplicationContext(), 1, restartServiceIntent, PendingIntent.FLAG_ONE_SHOT);
//        AlarmManager alarmService = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
//        alarmService.set(
//                AlarmManager.ELAPSED_REALTIME,
//                SystemClock.elapsedRealtime() + 1000,
//                restartServicePendingIntent);
//
//        super.onTaskRemoved(rootIntent);
//    }



    public void apiResponse() {

        Log.d(TAG, "apiResponse: function called");


        
        //now making the call object
        //Here using the api method that we created inside the api interface
        Call<List<Mention>> call = RetrofitClient
                .getInstance()
                .getApi()
                .getMentions();


        call.enqueue(new Callback<List<Mention>>() {

            @Override
            public void onResponse(Call<List<Mention>> call, Response<List<Mention>> response) {


                Log.d(TAG, "onResponse: inside onResponse");


                try {

                    //In this point we got our Mentions list
                    postList = response.body();

//                    Log.d(TAG, "onResponse: Response is: " + postList);

//                    noOfTweets = postList.size();

                    Log.d(TAG, "onResponse: got the response");


                    //Creating a String array for the ListView
//                    String[] _ids = new String[postList.size()];
//                    String[] texts = new String[postList.size()];
//                    String[] sentiments = new String[postList.size()];
                    //String[] names = new String[postList.size()];

                    x = 0;
                    //looping through all the texts and inserting the text inside the string array
                    for (int i = 0; i < postList.size(); i++) {


                        x = i;


//                        _ids[i] = postList.get(i).getId();
//                        texts[i] = postList.get(i).getText();
//                        sentiments[i] = postList.get(i).getSentimentAnalysisLogreg();

                        m_Ids.add(postList.get(i).getId());
                        mTweets.add(postList.get(i).getText());
                        mSentiments.add(postList.get(i).getSentimentAnalysisLogreg());
                        mNames.add(postList.get(i).getName());
                        mRetweeteds.add(postList.get(i).getRetweeted());
                        mCreatedAt.add(postList.get(i).getCreatedAt());
                        mProfileImageUrls.add(postList.get(i).getProfileImageUrl());
                        mRetweetCounts.add(postList.get(i).getRetweetCount());
                        mFavoriteCounts.add(postList.get(i).getFavoriteCount());
                        mFavoriteds.add(postList.get(i).getFavorited());


                        Log.d(TAG, "onResponse: Tweet = " + postList.get(i).getText());


                        // get the data and append to a list
                        Cursor data = mDatabaseHelper.getData();
                        List<String> listData = new ArrayList<>();

                        List<Bitmap> imageData = new ArrayList<>();

                        final Bitmap bitmapImage;

                        int k = 0;

                        Boolean flag = false;

//                        int s = 1;


                        while(data.moveToNext()){



//                            if (s == 1) {
//
//
//                                s++;
//                                continue;
//
//                            }
                            if ( data.getString(1).equals( postList.get(i).getText() ) ) {

                                Log.d(TAG, "onResponse: Tweet Data found in Database");

                                flag = true;

                                break;

                            }


                            // get the value from the database in column 1
                            // then add it to the ArrayList
//                            listData.add(data.getString(1));
//
//                            byte[] image = data.getBlob(2);
//
//                            bitmapImage = DbImageUtil.getImage(image);
//
//                            imageData.add(bitmapImage);
//
//                            Log.d(TAG, "populateListView: ImageData in the form of bitmapImage: " + imageData.get(k));
//
//                            k++;

                        }

//                        x = i;

                        if (flag.equals(false)) {
//                            Log.d(TAG, "onClick: ID is " + m_Ids.get(position));
//                            Log.d(TAG, "onClick: Name is " + mNames.get(position));
//                            Log.d(TAG, "onClick: Tweet  is " + mTweets.get(position));

                            Log.d(TAG, "onResponse: Inserting into Database");


//                            Bitmap tempBitmap;

                            Glide.with(getApplicationContext())
                                    .asBitmap()
                                    .load(mProfileImageUrls.get(i))
                                    .into(new SimpleTarget<Bitmap>() {
                                        @Override
                                        public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
//                                            holder.image.setImageBitmap(resource);


                                            Log.d(TAG, "onResourceReady: Bitmap Image: " + resource);


//                                            setBitmapImage(resource);

                                            tempBitmap = resource;


                                            byteImage = DbImageUtil.getBytes(resource);

                                            Log.d(TAG, "onResourceReady: byteImage: " + byteImage);
//
//
//                                            AddData(postList.get(x).getText(), byteImage);
                                        }
                                    });


                            Glide.with(getApplicationContext())
                                    .asBitmap()
                                    .load(mProfileImageUrls.get(i))
                                    .apply(RequestOptions.circleCropTransform())
                                    .into(new SimpleTarget<Bitmap>() {
                                        @Override
                                        public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {


                                            tempBitmapCircular = resource;

                                            Log.d(TAG, "onResourceReady: tempBitmapCircular inside glide: " + tempBitmapCircular);

                                        }
                                    });





//                            Log.d(TAG, "onResponse: byteImage again: " + byteImage);

//                            AddData(postList.get(i).getText(), byteImage);

                            Log.d(TAG, "onResponse: tempBitmap: " + tempBitmap);

                            byteImage = DbImageUtil.getBytes(tempBitmap);

                            Log.d(TAG, "onResourceReady: byteImage: " + byteImage);



                            AddData(postList.get(i).getText(), byteImage);


                            int notificationId;

//                            for (int y = 0; y < postList.size(); y++) {


                            String CHANNEL_ID = "123";
                            String textTitle = " Mentioned you";
//                                String textContent = mTweets.get(2);



//                            Glide.with(getApplicationContext())
//                                    .asBitmap()
//                                    .load(mProfileImageUrls.get(i))
//                                    .apply(RequestOptions.circleCropTransform())
//                                    .into(new SimpleTarget<Bitmap>() {
//                                        @Override
//                                        public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
//
//
//                                            tempBitmapCircular = resource;
//
//                                        }
//                                    });


                            Log.d(TAG, "onResponse: tempBitmapCircular: " + tempBitmapCircular);

                            NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                                    .setLargeIcon(tempBitmapCircular)
                                    .setSmallIcon(R.drawable.ic_mentions_small_notifications_512)
                                    .setContentTitle(postList.get(i).getName() + textTitle)
                                    .setContentText(postList.get(i).getText())
                                    .setPriority(NotificationCompat.PRIORITY_MAX)
                                    .setDefaults(Notification.DEFAULT_ALL);

                            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());

                            notificationId = i;
                            // notificationId is a unique int for each notification that you must define
                            notificationManager.notify(notificationId, builder.build());


//                            }






                        } else {

                            Log.d(TAG, "onResponse: Data already present in Database");

                        }







//                        String CHANNEL_ID = "123";
//                        String textTitle = "New Mention Available";
//                        String textContent = mTweets.get(2);
//
//                        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
//                                .setSmallIcon(R.drawable.icon_mentions)
//                                .setContentTitle(textTitle)
//                                .setContentText(texts[i])
//                                .setPriority(NotificationCompat.PRIORITY_MAX);
//
//                        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
//
//                        int notificationId = 1;
//                        // notificationId is a unique int for each notification that you must define
//                        notificationManager.notify(notificationId, builder.build());


                        //names[i] = postList.get(i).getUser();

                        //mRTweets.get(i).concat(texts[i]);



                        //List<Integer> newList = new ArrayList<Home>(texts);
                    }
//
//                    m_Ids = Arrays.asList(_ids);
//                    mTweets = Arrays.asList(texts);
//                    mSentiments = Arrays.asList(sentiments);

                    Log.d(TAG, "onResponse: Tweet text = " + mTweets);


//                    String CHANNEL_ID = "123";
//                    String textTitle = "New Mention Available";
//                    String textContent = mTweets.get(2);

//                    NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
//                            .setSmallIcon(R.drawable.icon_mentions)
//                            .setContentTitle(textTitle)
//                            .setContentText(textContent)
//                            .setPriority(NotificationCompat.PRIORITY_DEFAULT);
//
//                    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
//
//                    int notificationId = 1;
//                    // notificationId is a unique int for each notification that you must define
//                    notificationManager.notify(notificationId, builder.build());


//                    int notificationId;
//
//                    for (int i = 0; i < postList.size(); i++) {
//
//
//                        String CHANNEL_ID = "123";
//                        String textTitle = "New Mention Available";
//                        String textContent = mTweets.get(2);
//
//                        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
//                                .setSmallIcon(R.drawable.icon_mentions)
//                                .setContentTitle(textTitle)
//                                .setContentText(mTweets.get(i))
//                                .setPriority(NotificationCompat.PRIORITY_MAX);
//
//                        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
//
//                        notificationId = i;
//                        // notificationId is a unique int for each notification that you must define
//                        notificationManager.notify(notificationId, builder.build());
//
//
//                    }




//                    for (int i = 0; i < noOfTweets; i++ ){
//                        mImages.add("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg");
//                        mNames.add("Havasu Falls");
//                        mTimes.add("2 days ago");
//                    }

                    //mNames = Arrays.asList(names);

                    //displaying the string array into listview
                    //listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, heroes));

                    //displaying the string array into recycler view
                    //HomeAdapter adapter = new HomeAdapter(getContext(), mRTweets);

//                    pbMention.setVisibility(View.GONE);
//
//                    //recyclerView.setLayoutManager(manager);
//                    MentionsAdapter adapter = new MentionsAdapter(getContext(), m_Ids, mNames,
//                            mTweets, mSentiments, mRetweeteds, mCreatedAt, mProfileImageUrls,
//                            mRetweetCounts, mFavoriteCounts, mFavoriteds);
//                    recyclerView.setAdapter(adapter);
//                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));



                /*recyclerView = findViewById(R.id.recycler_view);
                LinearLayoutManager manager = new LinearLayoutManager(this);
                recyclerView.setLayoutManager(manager);
                recyclerView.setHasFixedSize(true);
                adapter = new MyAdapter();
                recyclerView.setAdapter(adapter);*/


//                try {

                    /*s.set(0,response.body().getText());
                    Toast.makeText(getContext(), s.get(0), Toast.LENGTH_LONG).show();*/

                    //Toast.makeText(getContext(), s, Toast.LENGTH_LONG).show();
                /*} catch (IOException e) {
                    e.printStackTrace();
                }*/

                /*if(response.code() == 201){
                    Home post = new Home();
                    post = response.body();
                    Toast.makeText(getContext(), post.getText(), Toast.LENGTH_LONG).show();

                }else if(response.code() == 422){
                    Toast.makeText(getContext(), "Invalid email or password", Toast.LENGTH_LONG).show();
                }*/

                    //Toast.makeText(getContext(), response.body().getText(), Toast.LENGTH_LONG).show();


//                postList = response.body().getText();
                /*adapter = new HomeAdapter(get, postList);
                recyclerView.setAdapter(adapter);*/

                    //HomeAdapter adapter = new HomeAdapter(getContext(), mNames, mImages, mTimes, mTweets);

                /*HomeAdapter adapter = new HomeAdapter(getContext(), postList);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));*/


                } catch (NullPointerException e) {
                    Log.e(TAG, "onResponse: NullPointerException " + e.getMessage() );
//                    noOfTweets = 0;
                }


            }


            @Override
            public void onFailure(Call<List<Mention>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }



//    public int getValueOfx() {
////
////        return x;
////    }



    public void AddData(String newEntry, byte[] byteImage) {
        boolean insertData = mDatabaseHelper.addData(newEntry, byteImage);

        Log.d(TAG, "AddData: Adding byte Image to DB: " + byteImage);

        if (insertData) {
            Toast.makeText(getApplicationContext(), "Data Successfully Inserted!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
        }
    }


    public void displayDataFromDb() {


        // get the data and append to a list
        Cursor data = mDatabaseHelper.getData();
        List<String> listData = new ArrayList<>();

        List<Bitmap> imageData = new ArrayList<>();

        Bitmap bitmapImage;

        int i = 0;

        while(data.moveToNext()){
            // get the value from the database in column 1
            // then add it to the ArrayList
            listData.add(data.getString(1));



            byte[] image = data.getBlob(2);


            Log.d(TAG, "displayDataFromDb: byteImage in DB: " + image);

            bitmapImage = DbImageUtil.getImage(image);

            imageData.add(bitmapImage);

            Log.d(TAG, "displayDataFromDb: Tweet Text is: " + listData.get(i) );

            Log.d(TAG, "displayDataFromDb: Image Data: " + imageData.get(i));

            i++;

        }





    }

//    public void setBitmapImage(Bitmap bitmap) {
//
//
//
//    }


}
