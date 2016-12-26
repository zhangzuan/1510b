package activity.bawe.com.yunifang.base;

import android.text.TextUtils;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

import activity.bawe.com.yunifang.utils.CommonUtils;
import activity.bawe.com.yunifang.utils.MD5Encoder;
import activity.bawe.com.yunifang.view.ShowingPage;

/**
* autour:张钻
* date: 2016/12/1 15:49
* update: 2016/12/1
*/
public abstract class BaseData {

    private final File fileDir;
    public static final int NOTIME = 0;
    public static final int NORMALTIME = 3 * 24 * 60 * 60 * 1000;


    //缓存数据存到哪里？
    public BaseData() {
        //找到存储路径
        File cacheDir = CommonUtils.getContext().getCacheDir();
        fileDir = new File(cacheDir, "yunifang");
        if (!fileDir.exists()) {
            //创建文件夹
            fileDir.mkdir();
        }
    }
    //？name=zhangsan  index 索引
    // 0页
    //http://www.baidu.com
    //http://www.baidu.com 1

    /**
     * @param path      请求地址
     * @param args      请求参数
     * @param index     页码索引
     * @param validTime 有效时间
     */
    public void getData(String path, String args, int index, int validTime) {
        //先判断有效时间
        if (validTime == 0) {
            //直接请求网络，要最新数据
            getDataFromNet(path, args, index, validTime);
        } else {
            //从本地获取
            String data = getDataFromLocal(path, index, validTime);
            if (TextUtils.isEmpty(data)) {
                //如果为空，请求网络
                getDataFromNet(path, args, index, validTime);
            } else {
                //拿到了数据，返回数据
                setResultData(data);
            }
        }
    }

    /**
     * @param path      请求地址
     * @param index     页码索引
     * @param validTime 有效时间
     */
    public void postData(String path, HashMap<String, String> argsMap, int index, int validTime) {
        //先判断有效时间
        if (validTime == 0) {
            //直接请求网络，要最新数据
            postDataFromNet(path, argsMap, index, validTime);
        } else {
            //从本地获取
            String data = getDataFromLocal(path, index, validTime);
            if (TextUtils.isEmpty(data)) {
                //如果为空，请求网络
                postDataFromNet(path, argsMap, index, validTime);
            } else {
                //拿到了数据，返回数据
                setResultData(data);
            }
        }
    }

    private void postDataFromNet(String path, HashMap<String, String> argsMap, int index, int validTime) {
        //将Map中的参数取出
        Set<String> strings = argsMap.keySet();
        FormEncodingBuilder formEncodingBuilder = new FormEncodingBuilder();
        for (String key : strings
                ) {
            formEncodingBuilder.add(key, argsMap.get(key));
        }
        //创建client对象
        OkHttpClient okHttpClient = new OkHttpClient();
        //创建body
        RequestBody requestBody = formEncodingBuilder.build();
        //创建一个post请求
        Request request = new Request.Builder()
                .url(path)
                .post(requestBody)
                .build();
        //new call
        Call call = okHttpClient.newCall(request);
        //请求加入调度
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                //设置error
                setResulttError(ShowingPage.StateType.STATE_LOAD_ERROR);
            }
            @Override
            public void onResponse(final Response response) throws IOException {
                final String data = response.body().string();
                CommonUtils.runOnMainThread(new Runnable() {
                    @Override
                    public void run() {
                        //设置数据
                        setResultData(data);
                    }
                });
            }
        });


    }


    public abstract void setResultData(String data);

    private String getDataFromLocal(String path, int index, int validTime) {
        //读取文件信息
        //读时间
        try {
            File file = new File(fileDir, MD5Encoder.encode(path) + index);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

            String s = bufferedReader.readLine();

            long time = Long.parseLong(s);
            //和当前时间进行比较
            //111-110
            if (System.currentTimeMillis() < time) {
                //将信息读出来
                StringBuilder builder = new StringBuilder();
                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
                    builder.append(line);
                }
                bufferedReader.close();
                return builder.toString();


            } else {
                //无效
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * 从网络获取数据
     *
     * @param path
     * @param args
     * @param index
     * @param validTime
     */
    private void getDataFromNet(final String path, final String args, final int index, final int validTime) {
//        RequestParams requestParams = new RequestParams(path + "?" + args);
        //创建okHttpClient对象
        OkHttpClient mOkHttpClient = new OkHttpClient();
        //创建一个Request
        final Request request = new Request.Builder()
                .url(path + "?" + args)
                .build();
        //new call
        Call call = mOkHttpClient.newCall(request);
        //请求加入调度
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
            }

            @Override
            public void onResponse(final Response response) throws IOException {
                final String data = response.body().string();
                CommonUtils.runOnMainThread(new Runnable() {
                    @Override
                    public void run() {
                        //设置数据
                        setResultData(data);
                    }
                });


                //写到本地
                writeDataToLocal(path, args, index, validTime, data);


            }
        });

    }

    protected abstract void setResulttError(ShowingPage.StateType stateLoadError);

    /**
     * 将数据写到本地
     *
     * @param path
     * @param args
     * @param index
     * @param validTime
     * @param
     */
    private void writeDataToLocal(String path, String args, int index, int validTime, String data) {
        //每一条请求，都是生成一个文件   dawedfakwehfaowehfoaw
        try {
            File file = new File(fileDir, MD5Encoder.encode(path) + index);
            //写流信息
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));

            //323250328383
            //打开个娃偶尔很乏味偶发沃尔夫哈乌龟啊我费劲儿啊
            //100+10
            bufferedWriter.write(System.currentTimeMillis() + validTime + "\r\n");
            //从网络上请求的数据
            bufferedWriter.write(data);

            bufferedWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
