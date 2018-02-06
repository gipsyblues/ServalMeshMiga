package org.servalproject;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.os.Environment;
import android.util.Log;

/**
 * Created by Miga on 2018/02/04.
 * 如果ddms內的data打不開則需要到terminal輸入指令: adb shell su -c "chmod 777 /data"
 * 如果解除data資料夾內的檔案的權限,則需要輸入指令: adb shell su -c "chmod 777 /data/log file name.txt"
 * 如果要拉出data資料夾內的檔案到桌面,則需要輸入指令:adb pull /data/log file name.txt ~/桌面
 * New 20180206 上述那些不需要打了
 * 請在乎叫此function前先判斷Android SDK版本，目前只有寫Android 5.0.2可以進入此Fuction
 * 手機: f418 , 818b , 3c06
 * if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP_MR1)// 現在SDK版本 < 22的話則進入寫LOG , 只有Android 5.0.2版本可以成功寫log : 818b, f418, 3c06
 *   WriteLog.appendLog("Control.java/Control開啟"+"\r\n");
 */
public class WriteLog
{
    public static void appendLog(String text,String DeviceWiFiApName)
    {
    	//File logFile = new File("/data/"+getDate()+".txt");
    	//File logFile = new File("/mnt/shell/emulated/0/"+getDate()+".txt");
        String LastFourName=DeviceWiFiApName.substring(DeviceWiFiApName.length()-4);//取後方4個字, e.g. f418
        File logFile = new File(Environment.getExternalStorageDirectory().getAbsoluteFile()+"/MigaLog/", getDate()+"_"+LastFourName+".txt");//實際位置:/mnt/shell/emulated/0/, For Android 5.0.1成功
       // context.
        //File appDirectory = new File(  Environment.getDataDirectory() + "/ServalMeshLog" );
       // File logDirectory = new File( appDirectory + "/log" );
       //File logFile = new File( Environment.getDataDirectory().getAbsoluteFile(), "logcat" +getDate()+".txt" );
    	/* File appDirectory = new File( "sdcard/ServalMeshLog" );
         File logDirectory = new File( appDirectory + "/log" );
         File logFile = new File( logDirectory, "logcat" + getDate() + ".txt" );

         // create app folder
         if ( !appDirectory.exists() ) {
             appDirectory.mkdir();
         }

         // create log folder
         if ( !logDirectory.exists() ) {
             logDirectory.mkdir();
         }
  */
        if (!logFile.exists())
        {
            try
            {
                logFile.createNewFile();
            }
            catch (IOException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        try
        {
            //BufferedWriter for performance, true to set append to file flag
            BufferedWriter buf = new BufferedWriter(new FileWriter(logFile, true));
            buf.append(getDateTime()+"  "+text);
            buf.newLine();
            buf.close();
            Log.d("Miga","Write Log File Success:"+text);
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //取得現在時間
    public static String getDateTime(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Calendar c = Calendar.getInstance();
        String str = df.format(c.getTime());
        return str;
    }
    //取得現在時間
    public static String getDate(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        String str = df.format(c.getTime());
        return str;
    }
}
