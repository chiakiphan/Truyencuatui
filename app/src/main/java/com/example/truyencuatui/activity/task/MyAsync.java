package com.example.truyencuatui.activity.task;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.truyencuatui.R;

public class MyAsync extends AsyncTask<Void,Integer,Void> {
    Activity contextCha;
    //constructor này được truyền vào là MainActivity
    public MyAsync(Activity ctx)
    {
        contextCha=ctx;
    }
    //hàm này sẽ được thực hiện đầu tiên
    @Override
    protected void onPreExecute() {
        // TODO Auto-generated method stub
        super.onPreExecute();
        Toast.makeText(contextCha, "onPreExecute!",
                Toast.LENGTH_LONG).show();
    }
    //sau đó tới hàm doInBackground
    //tuyệt đối không được cập nhật giao diện trong hàm này
    @Override
    protected Void doInBackground(Void... arg0) {
        for(int i=0;i<=100;i++)
        {
            //nghỉ 100 milisecond thì tiến hành update UI
            SystemClock.sleep(100);
            //khi gọi hàm này thì onProgressUpdate sẽ thực thi
            publishProgress(i);
        }
        return null;
    }
    /**
     * ta cập nhập giao diện trong hàm này
     */
    @Override
    protected void onProgressUpdate(Integer... values) {
        // TODO Auto-generated method stub
        super.onProgressUpdate(values);
        //thông qua contextCha để lấy được control trong MainActivity
        ProgressBar paCha=(ProgressBar) contextCha
                .findViewById(R.id.progress);
        //vì publishProgress chỉ truyền 1 đối số
        //nên mảng values chỉ có 1 phần tử
        int giatri=values[0];
        //tăng giá trị của Progressbar lên
        paCha.setProgress(giatri);
        //đồng thời hiện thị giá trị là % lên TextView

    }
}
