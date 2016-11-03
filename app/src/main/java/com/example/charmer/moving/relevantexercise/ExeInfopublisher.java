package com.example.charmer.moving.relevantexercise;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.charmer.moving.MainActivity;
import com.example.charmer.moving.MyApplicition.MyApplication;
import com.example.charmer.moving.MyView.GridView_picture;
import com.example.charmer.moving.R;
import com.example.charmer.moving.contantData.HttpUtils;
import com.example.charmer.moving.home_activity.Zixun_comment;
import com.example.charmer.moving.pojo.ListActivityBean;
import com.example.charmer.moving.pojo.VariableExercise;
import com.example.charmer.moving.utils.DensityUtil;
import com.example.charmer.moving.utils.xUtilsImageUtils;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class ExeInfopublisher extends AppCompatActivity {


    private BaseAdapter adapter;
    private BaseAdapter imgadapter;
    private BaseAdapter enrolleradapter;
    private ListView lv_exercise;
    private ListView lvenrollers;
    private TextView title ;
    private Button cancelexe;
    private TextView name;
    private TextView successfulpublishpercent;
    private TextView publishedNum;
    private ImageView imguser;
    private GridView_picture joinerImgs;
    private static final String TAG = "ExerciseinfoActivity";
    final ArrayList<VariableExercise.Exercises> exerciseList = new ArrayList<VariableExercise.Exercises>();
    private TextView textintroduce;
    private ImageView joinerImg;
    private String exerciseId;
    private ImageView enrollerImg;
    private TextView enrollerinfonum;
    private TextView enrollerinforate;
    private TextView enrollerName;
    private Button agreebtn;
    private TextView ignore;
    private RelativeLayout finishthis;
    VariableExercise.DataSummary ds = new VariableExercise.DataSummary();
    final List<VariableExercise.DataSummary> dsListJoin = new ArrayList<VariableExercise.DataSummary>();
    final List<VariableExercise.DataSummary> dsListEnroll = new ArrayList<VariableExercise.DataSummary>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exe_infopublisher);
        Intent intent = this.getIntent();
        exerciseId = intent.getStringExtra("exerciseId");
        lv_exercise = ((ListView)findViewById(R.id.exemidinfolist));
        textintroduce = ((TextView) findViewById(R.id.textintroduce));
        title = ((TextView) findViewById(R.id.titleinfo));
        cancelexe = ((Button) findViewById(R.id.cancelexe));
        cancelexe.setEnabled(false);
        name = ((TextView) findViewById(R.id.name));
        successfulpublishpercent = ((TextView) findViewById(R.id.successfulpublishpercent));
        publishedNum = ((TextView) findViewById(R.id.publishedNum));
        imguser = ((ImageView) findViewById(R.id.imguser));
        joinerImgs = ((GridView_picture) findViewById(R.id.joinerImgs));
        lvenrollers = ((ListView)findViewById(R.id.lvenrollers));
        finishthis =((RelativeLayout) findViewById(R.id.finishthis));
        adapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return exerciseList.size();
            }

            @Override
            public Object getItem(int position) {
                return exerciseList.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                Log.i(TAG, "加载listview item position:" + position);
                ViewHolder viewHolder = null;

                viewHolder = new ViewHolder();
                // 打气筒  view就是指每一个listview item
                convertView = View.inflate(ExeInfopublisher.this, R.layout.exemidinfo, null);
                viewHolder.type = ((TextView) convertView.findViewById(R.id.type));
                viewHolder.theme = ((TextView) convertView.findViewById(R.id.theme));
                viewHolder.place = ((TextView) convertView.findViewById(R.id.place));
                viewHolder.activityTime = ((TextView) convertView.findViewById(R.id.activityTime));
                viewHolder.cost = ((TextView) convertView.findViewById(R.id.cost));
                viewHolder.paymentMethod = ((TextView) convertView.findViewById(R.id.paymentMethod));
                viewHolder.currentNumber = ((TextView) convertView.findViewById(R.id.currentNumber));
                viewHolder.totalNumber = ((TextView) convertView.findViewById(R.id.totalNumber));


                VariableExercise.Exercises exercises = exerciseList.get(position);

                try {
                    viewHolder.type.setText(URLDecoder.decode(exercises.type,"utf-8"));
                    viewHolder.theme.setText(URLDecoder.decode(exercises.theme,"utf-8"));
                    viewHolder.place.setText(URLDecoder.decode(exercises.place,"utf-8"));
                    viewHolder.activityTime.setText(URLDecoder.decode(exercises.activityTime,"utf-8").substring(0,16));
                    viewHolder.cost.setText(URLDecoder.decode(exercises.cost.toString(),"utf-8"));
                    viewHolder.paymentMethod.setText(URLDecoder.decode(exercises.paymentMethod,"utf-8"));
                    viewHolder.currentNumber.setText(URLDecoder.decode(exercises.currentNumber.toString(),"utf-8"));
                    viewHolder.totalNumber.setText(URLDecoder.decode(exercises.totalNumber.toString(),"utf-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return convertView;
            }
        };
        lv_exercise.setAdapter(adapter);

        imgadapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return dsListJoin.size();
            }

            @Override
            public Object getItem(int position) {
                return dsListJoin.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                convertView = View.inflate(ExeInfopublisher.this, R.layout.joinerimg, null);
                joinerImg= (ImageView) convertView.findViewById(R.id.joinerImg);
                VariableExercise.DataSummary vds = dsListJoin.get(position);
                xUtilsImageUtils.display(joinerImg, HttpUtils.hoster+"upload/"+vds.userImg);
                System.out.println("==-=-=-=-=-=-"+vds.userImg);

                return convertView;
            }
        };

        joinerImgs.setAdapter(imgadapter);

        enrolleradapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return dsListEnroll.size();
            }

            @Override
            public Object getItem(int position) {
                return dsListEnroll.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                final int position1 = position;

                lvenrollers.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, DensityUtil.dip2px(ExeInfopublisher.this,100)*dsListEnroll.size()));

                convertView = View.inflate(ExeInfopublisher.this, R.layout.enrolleritem, null);
                enrollerImg = ((ImageView) convertView.findViewById(R.id.enrollerImg));
                enrollerinfonum = ((TextView) convertView.findViewById(R.id.enrollerinfonum));
                enrollerinforate = ((TextView) convertView.findViewById(R.id.enrollerinforate));
                enrollerName = ((TextView) convertView.findViewById(R.id.enrollerName));
                agreebtn = ((Button) convertView.findViewById(R.id.agreebtn));
                ignore = ((TextView) convertView.findViewById(R.id.ignore));
                VariableExercise.DataSummary vds = dsListEnroll.get(position);
                System.out.println("+++++++++++++22+++++++++"+dsListEnroll);
                xUtilsImageUtils.display(enrollerImg, HttpUtils.hoster+"upload/"+vds.userImg);
                enrollerinfonum.setText(enrollerinfonum.getText().toString()+vds.joinedNum);
                enrollerinforate.setText(enrollerinforate.getText().toString()+vds.appointmentRate);
                enrollerName.setText(vds.userName);
                agreebtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(
                                ExeInfopublisher.this);
                        builder.setMessage(getString(R.string.agreejoin_sure));
                        builder.setTitle(dsListEnroll.get(position1).userName+"请求参加您的活动");
//                        builder.setIcon(getResources().getDrawable(
//                                R.drawable.delete1));
                        builder.setPositiveButton(
                                getString(R.string.ok),
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(
                                            DialogInterface dialogInterface,
                                            int which) {
                                        // TODO Auto-generated method
                                        agreeJoin(exerciseId,dsListEnroll.get(position1).userAccount.toString(),ExeInfopublisher.this);
                                    }
                                });
                        builder.setNegativeButton(
                                getString(R.string.cancel),
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(
                                            DialogInterface dialogInterface,
                                            int which) {
                                        // TODO Auto-generated method
                                        // stub
                                        dialogInterface.dismiss();
                                    }
                                });
                        builder.create().show();
                    }
                });

                ignore.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(
                                ExeInfopublisher.this);
                        builder.setMessage(getString(R.string.ignore_sure));
                        builder.setTitle("您将忽略"+dsListEnroll.get(position1).userName+"的请求!");
//                        builder.setIcon(getResources().getDrawable(
//                                R.drawable.delete1));
                        builder.setPositiveButton(
                                getString(R.string.ok),
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(
                                            DialogInterface dialogInterface,
                                            int which) {
                                        // TODO Auto-generated method
                                        ignoreEnroll(exerciseId,dsListEnroll.get(position1).userAccount.toString(),ExeInfopublisher.this);
                                    }
                                });
                        builder.setNegativeButton(
                                getString(R.string.cancel),
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(
                                            DialogInterface dialogInterface,
                                            int which) {
                                        // TODO Auto-generated method
                                        // stub
                                        dialogInterface.dismiss();
                                    }
                                });
                        builder.create().show();
                    }
                });
                return convertView;
            }
        };

        lvenrollers.setAdapter(enrolleradapter);

        getExerciseList(exerciseId);

        cancelexe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(
                            ExeInfopublisher.this);
                    builder.setMessage(getString(R.string.cancel_sure));
                    builder.setTitle(R.string.cancelExeintro);
//                    builder.setIcon(getResources().getDrawable(
//                            R.drawable.delete1));
                    builder.setPositiveButton(
                            getString(R.string.ok),
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(
                                        DialogInterface dialogInterface,
                                        int which) {
                                    // TODO Auto-generated method
                                    ExeSharedMthd.cancelExe(exerciseId, MainActivity.getUser().getUseraccount(),ExeInfopublisher.this);
                                    finish();
                                     }
                            });
                    builder.setNegativeButton(
                            getString(R.string.cancel),
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(
                                        DialogInterface dialogInterface,
                                        int which) {
                                    // TODO Auto-generated method
                                    // stub
                                    dialogInterface.dismiss();
                                }
                            });
                    builder.create().show();
                }

        });
        finishthis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void getExerciseList(String exerciseId) {
        String str = HttpUtils.hoster+"getexebyid";
        RequestParams params = new RequestParams(str);

        params.addQueryStringParameter("exerciseId",exerciseId);

        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                exerciseList.clear();
                VariableExercise bean = gson.fromJson(result, VariableExercise.class);
                exerciseList.addAll(bean.exerciseList);
                System.out.println("=-=-=-="+exerciseList.get(0).currentNumber);
                dsListJoin.clear();
                dsListJoin.addAll(bean.dsListJoin);
                dsListEnroll.clear();
                dsListEnroll.addAll(bean.dsListEnroll);
                lvenrollers.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, DensityUtil.dip2px(ExeInfopublisher.this,100)*dsListEnroll.size()));
                ds = bean.ds;
                if(exerciseList.size()>0) {
                    cancelexe.setEnabled(true);
                }
                try{
                    title.setText(URLDecoder.decode(bean.exerciseList.get(0).title,"utf-8"));
                    textintroduce.setText(URLDecoder.decode(bean.exerciseList.get(0).exerciseIntroduce,"utf-8"));
                    name.setText(ds.userName);
                    successfulpublishpercent.setText(ds.successfulpublishpercent);
                    publishedNum.setText(ds.publishedNum+"");
                    xUtilsImageUtils.display(imguser,HttpUtils.hoster+"upload/"+ds.userImg);

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                Log.i("exerciseList", "exerciseList: "+exerciseList);
                //通知listview更新界面

                imgadapter.notifyDataSetChanged();
                enrolleradapter.notifyDataSetChanged();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

    }

    private void agreeJoin(String exerciseId,String joiner,Context contexts){
        final String exeId = exerciseId;
        final Context context = contexts;
        String str = HttpUtils.hoster+"enrollexe";
        RequestParams params = new RequestParams(str);

        params.addQueryStringParameter("exerciseId",exerciseId);
        params.addQueryStringParameter("joiner",joiner);
        params.addQueryStringParameter("state","1");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                System.out.println(result);
                if ("0".equals(result)){
                    Toast.makeText(context,"人数已满，操作失败！",Toast.LENGTH_SHORT).show();
                }else
                if ("1".equals(result)){
                    Toast.makeText(context,"操作成功！",Toast.LENGTH_SHORT).show();
                    getExerciseList(exeId);
                }else
                {
                    Toast.makeText(context,"操作失败！",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }


    private void ignoreEnroll(String exerciseId,String joiner,Context contexts){
        final String exeId = exerciseId;
        final Context context = contexts;
        String str = HttpUtils.hoster+"enrollexe";
        RequestParams params = new RequestParams(str);

        params.addQueryStringParameter("exerciseId",exerciseId);
        params.addQueryStringParameter("joiner",joiner);
        params.addQueryStringParameter("state","2");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                System.out.println(result);
                if ("true".equals(result)) {
                    Toast.makeText(context,"忽略成功！",Toast.LENGTH_SHORT).show();
                    getExerciseList(exeId);
                }else{
                    Toast.makeText(context,"忽略成功！",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
    private static class ViewHolder{
        TextView type ;
        TextView theme ;
        TextView place ;
        TextView activityTime ;
        TextView cost ;
        TextView paymentMethod ;
        TextView currentNumber ;
        TextView totalNumber ;
    }
}