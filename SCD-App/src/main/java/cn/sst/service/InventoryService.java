package cn.sst.service;


import rx.Observable;
import rx.Subscriber;

/**
 * @author shengtengsun
 * @Description
 * @Date 2020/9/21 7:53 下午
 * @Version 1.1.0
 **/
public class InventoryService {
    /**
     * 库存总数
     **/
    private Integer inventoryCount = 0;

    /**
     * 库存增加Observable
     *
     * @param
     * @return void
     * @author shengtengsun
     * @date 2020/9/22 5:04 下午
     **/
    public Observable addInventory() {
        return Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                try {
                    if (!subscriber.isUnsubscribed()) {
                        System.out.println("增加库存");
                        subscriber.onNext(1);
                        inventoryCount += 1;
                    }
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }
}
