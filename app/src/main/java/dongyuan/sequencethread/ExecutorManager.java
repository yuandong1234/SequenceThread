package dongyuan.sequencethread;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Executor;

/**
 * ExecutorManager，将用户提交的Runnable保存到一个队列里面，然后顺序执行
 * Created by yuandong on 2017/5/16.
 */
class ExecutorManager implements Executor {
    //队列，用来存放提交进来的Runnable
    final Queue<Runnable> tasks = new ArrayDeque<Runnable>();
    //提交进来的Runnable实际执行的位置。
    final Executor executor;
    //当前正在执行的Runnable
    Runnable active;

    public ExecutorManager(Executor executor) {
        this.executor = executor;
    }

    public synchronized void execute(final Runnable r) {
        //将Runnable包装，并添加到队里中。
        tasks.offer(new Runnable() {
            public void run() {
                try {
                    r.run();
                } finally {
                    //执行完毕后，会自动调用队里中的下一个Runnable
                    scheduleNext();
                }
            }
        });
        if (active == null) {
            //首次调度
            scheduleNext();
        }
    }

    protected synchronized void scheduleNext() {
        if ((active = tasks.poll()) != null) {
            executor.execute(active);
        }
    }
}

