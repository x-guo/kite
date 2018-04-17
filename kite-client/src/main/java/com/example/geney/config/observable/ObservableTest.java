package com.example.geney.config.observable;

import rx.Observable;
import rx.Subscriber;

/**
 * @author zhenyu.guo
 * @date 2018/3/20.
 */
public class ObservableTest {

    //创建事件源
    Observable<String> observable = Observable.create(subscriber1 -> {
        subscriber1.onNext("hello");
        subscriber1.onNext("RxJava");
        subscriber1.onNext("i am");
        subscriber1.onNext("程序员");
        subscriber1.onNext("ha ha...");
        subscriber1.onCompleted();
    });

    Subscriber<String> subscriber = new Subscriber<String>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable throwable) {

        }

        @Override
        public void onNext(String s) {
            System.out.println("s = [" + s + "]");
        }
    };

    //订阅
    //

}
