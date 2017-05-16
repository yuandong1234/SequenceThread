package dongyuan.sequencethread;

import java.util.concurrent.Executor;

/**
 * SerialExecutor为每一个runnable起一个线程
 * Created by yuandong on 2017/5/16.
 */

class SerialExecutor implements Executor {
    public void execute(Runnable r) {
        //这个看情况，如果此方法已经在子线程中，则不需要新起线程
        new Thread(r).start();
        // r.run();
    }
}