package com.ddmeng.preferencesprovider.utils;


import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import com.ddmeng.preferencesprovider.BuildConfig;

import java.io.File;
import java.io.FileOutputStream;

public class LogUtils {

    private static boolean DEBUG = BuildConfig.DEBUG;
    private static String DEFAULT_LOG_TAG = "mengdd";

    private final static boolean LOG_TO_FILE = false;
    private final static String LOG_FILE_PATH = Environment
            .getExternalStorageDirectory().getPath() + "/mengdd_debug_log.txt";

    private static File LOG_FILE;

    public static void footPrint() {
        if (DEBUG) {
            String className = Thread.currentThread().getStackTrace()[3]
                    .getClassName();
            int index = className.lastIndexOf(".");
            if (index > -1) {
                className = className.substring(index + 1);
            }
            String msgToPrint = Thread.currentThread().getId() + " "
                    + className + "."
                    + Thread.currentThread().getStackTrace()[3].getMethodName();
            println(Log.DEBUG, DEFAULT_LOG_TAG, msgToPrint);
        }
    }

    public static void footPrint(String tag) {
        if (DEBUG) {
            String msgToPrint = Thread.currentThread().getStackTrace()[3]
                    .getMethodName();
            println(Log.DEBUG, tag, msgToPrint);
        }
    }

    public static String getStackTraceString(Throwable tr) {
        return Log.getStackTraceString(tr);
    }

    public static void v(String tag, String msg) {
        if (DEBUG) {
            println(Log.VERBOSE, tag, msg);
        }
    }

    public static void v(String tag, String msg, Throwable tr) {
        if (DEBUG) {
            println(Log.VERBOSE, tag, msg + '\n' + getStackTraceString(tr));
        }
    }

    public static void d(String msg) {
        if (DEBUG) {
            String className = Thread.currentThread().getStackTrace()[3]
                    .getClassName();
            int index = className.lastIndexOf(".");
            if (index > -1) {
                className = className.substring(index + 1);
            }

            String msgToPrint = Thread.currentThread().getId() + " "
                    + className + "."
                    + Thread.currentThread().getStackTrace()[3].getMethodName();
            if (!TextUtils.isEmpty(msg)) {
                msgToPrint += "--" + msg;
            }
            println(Log.DEBUG, DEFAULT_LOG_TAG, msgToPrint);
        }
    }

    public static void d(String tag, String msg) {
        if (DEBUG) {
            String msgToPrint = Thread.currentThread().getStackTrace()[3]
                    .getMethodName();
            if (!TextUtils.isEmpty(msg)) {
                msgToPrint += "--" + msg;
            }
            println(Log.DEBUG, tag, msgToPrint);
        }
    }

    public static void d(String tag, String msg, Throwable tr) {
        if (DEBUG) {
            println(Log.DEBUG, tag, msg + '\n' + getStackTraceString(tr));
        }
    }

    public static void i(String msg) {
        if (DEBUG) {
            String className = Thread.currentThread().getStackTrace()[3]
                    .getClassName();
            int index = className.lastIndexOf(".");
            if (index > -1) {
                className = className.substring(index + 1);
            }

            String msgToPrint = Thread.currentThread().getId() + " "
                    + className + "."
                    + Thread.currentThread().getStackTrace()[3].getMethodName();
            if (!TextUtils.isEmpty(msg)) {
                msgToPrint += "--" + msg;
            }
            println(Log.INFO, DEFAULT_LOG_TAG, msgToPrint);
        }
    }

    public static void i(String tag, String msg) {
        if (DEBUG) {
            String msgToPrint = Thread.currentThread().getStackTrace()[3]
                    .getMethodName();
            msgToPrint += "--" + msg;
            println(Log.INFO, tag, msgToPrint);
        }
    }

    public static void i(String tag, String msg, Throwable tr) {
        if (DEBUG) {
            println(Log.INFO, tag, msg + '\n' + getStackTraceString(tr));
        }
    }

    public static int w(String tag, String msg) {
        return println(Log.WARN, tag, msg);
    }

    public static int w(String tag, String msg, Throwable tr) {
        return println(Log.WARN, tag, msg + '\n' + getStackTraceString(tr));
    }

    public static int w(String tag, Throwable tr) {
        return println(Log.WARN, tag, getStackTraceString(tr));
    }

    public static int e(String msg) {
        return println(Log.ERROR, DEFAULT_LOG_TAG, msg);
    }

    public static int e(String tag, String msg) {
        return println(Log.ERROR, tag, msg);
    }

    public static int e(String tag, String msg, Throwable tr) {
        return println(Log.ERROR, tag, msg + '\n' + getStackTraceString(tr));
    }

    public static int wtf(String tag, String msg) {
        return Log.wtf(tag, msg, null);
    }

    public static int wtf(String tag, Throwable tr) {
        return Log.wtf(tag, tr.getMessage(), tr);
    }

    public static int wtf(String tag, String msg, Throwable tr) {
        return Log.wtf(tag, msg, tr);
    }

    public static int println(int priority, String tag, String msg) {
        if (TextUtils.isEmpty(msg)) {
            msg = "";
        }
        if (LOG_TO_FILE) {
            logToFile(tag, msg);
            return 0;
        } else {
            return Log.println(priority, tag, msg);
        }
    }

    private static synchronized void logToFile(String tag, String msg) {
        try {
            if (LOG_FILE == null) {
                LOG_FILE = new File(LOG_FILE_PATH);
                if (LOG_FILE.exists()) {
                    LOG_FILE.delete();
                }
                LOG_FILE.createNewFile();
            }

            FileOutputStream outputStream = new FileOutputStream(LOG_FILE, true);

            StringBuilder builder = new StringBuilder();
            builder.append(tag).append("  :  ").append(msg).append("\n");

            outputStream.write(builder.toString().getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
