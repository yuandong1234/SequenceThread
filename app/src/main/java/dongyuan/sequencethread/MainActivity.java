package dongyuan.sequencethread;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static String TAG=MainActivity.class.getSimpleName();

    ExecutorManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager=new ExecutorManager(new SerialExecutor());
        findViewById(R.id.button).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
        findViewById(R.id.button4).setOnClickListener(this);
        findViewById(R.id.button5).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id. button:
                manager.execute(new Runnable() {
                    @Override
                    public void run() {
                    Log.e(TAG,"任务1："+Thread.currentThread().toString());
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Log.e(TAG,"点击了Button1");
                    }
                });
                break;
            case R.id. button2:
                manager.execute(new Runnable() {
                    @Override
                    public void run() {
                        Log.e(TAG,"任务2："+Thread.currentThread().toString());
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Log.e(TAG,"点击了Button2");
                    }
                });

                break;
            case R.id. button3:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        startTask();
                    }
                }).start();
                break;
            case R.id. button4:
                manager.execute(new Runnable() {
                    @Override
                    public void run() {
                        Log.e(TAG,"任务4："+Thread.currentThread().toString());
                        Log.e(TAG,"点击了Button4");
                    }
                });
                break;
            case R.id. button5:
              manager.execute(new Runnable() {
                  @Override
                  public void run() {
                      Log.e(TAG,"任务5："+Thread.currentThread().toString());
                      Log.e(TAG,"点击了Button5");
                  }
              });
                break;
        }
    }

    private  void  startTask(){
        manager.execute(new Runnable() {
            @Override
            public void run() {
                Log.e(TAG,"任务3："+Thread.currentThread().toString());
                Log.e(TAG,"点击了Button3");
            }
        });
    }
}
