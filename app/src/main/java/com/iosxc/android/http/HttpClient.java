package com.iosxc.android.http;

import com.iosxc.android.http.request.HttpRequest;
import com.iosxc.android.http.response.HttpHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by crazz on 5/2/15.
 */
public class HttpClient {

    private static HttpClient client;
    private List<HttpInterceptor> interceptors;
    private AtomicBoolean isRunning = new AtomicBoolean(false);
    private ConcurrentLinkedQueue<HttpExecutor> queue;
    private Thread httpExecThread;

    private  HttpClient() {
        this.queue = new ConcurrentLinkedQueue<HttpExecutor>();
        this.interceptors = new ArrayList<HttpInterceptor>();
    }

    public static HttpClient defaultClient() {
        if(client==null){
            synchronized (HttpClient.class){
                if(client==null){
                    client = new HttpClient();
                }
            }
        }
        return client;
    }

    public List<HttpInterceptor> getInterceptors() {
        return interceptors;
    }

    public AtomicBoolean isRunning() {
        return isRunning;
    }

    public void execute(HttpRequest request,HttpHandler handler) throws HttpException {
        HttpExecutor executor = new HttpExecutor();
        executor.setHttpHandler(handler);
        executor.setHttpRequest(request);
        executor.setHttpClient(this);
        queue.offer(executor);
        start();
    }
    private void runQueue() throws HttpException {
        httpExecThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    if(!queue.isEmpty()){
                        queue.poll().execute();
                    }else{
                        stop();
                    }
                }
            }
        });
        httpExecThread.setName("iosxc-http-thread");
        httpExecThread.start();
    }

    private void start() throws HttpException {
        if(isRunning.get()==false){
            isRunning.set(true);
            this.runQueue();
        }
    }

    private void stop(){
        isRunning.set(false);
        if(httpExecThread!=null){
            httpExecThread.interrupt();
            httpExecThread = null;
        }
    }
}
