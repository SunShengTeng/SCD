package cn.sst.scd.hystrix;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;
import rx.Observable;
import rx.Subscriber;

/**
 * @author shengtengsun
 * @Description 自定义熔断
 * @Date 2020/9/21 7:55 下午
 * @Version 1.1.0
 **/
public class InventoryHystrixObservableCommend extends HystrixObservableCommand {

    public InventoryHystrixObservableCommend(HystrixCommandGroupKey group) {
        super(group);
    }

    @Override
    protected Observable construct() {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    if (!subscriber.isUnsubscribed()) {
                        for (int i = 0; i < 5; i++) {
                            subscriber.onNext("发送消息" + i);
                        }
                        subscriber.onCompleted();
                    }
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    @Override
    protected Observable resumeWithFallback() {
        return super.resumeWithFallback();
    }
}
