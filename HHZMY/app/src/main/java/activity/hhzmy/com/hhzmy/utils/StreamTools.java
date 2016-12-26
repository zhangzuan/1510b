package activity.hhzmy.com.hhzmy.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by lvzhuozhao on 2016/11/9.
 */
public class StreamTools {
    public static String Read(InputStream  is)
    {
        try {
            ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
            byte []  b=new byte[1024];
            int len=0;
            while ((len=is.read(b))!=-1)
            {
                outputStream.write(b,0,len);
            }
            return outputStream.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
