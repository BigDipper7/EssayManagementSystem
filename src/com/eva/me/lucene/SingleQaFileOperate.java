package com.eva.me.lucene;

/**
 * Created by xing on 2016/5/6.
 */
public class SingleQaFileOperate {
    private static QaFileOperate qaFileOperate = null;
    public static QaFileOperate getSinleQaFileOperate(){
        if(qaFileOperate == null){
            synchronized (SingleQaFileOperate.class){
                if(qaFileOperate == null){
                    qaFileOperate = new QaFileOperate();
                }
            }
        }
        return qaFileOperate;
    }
}
