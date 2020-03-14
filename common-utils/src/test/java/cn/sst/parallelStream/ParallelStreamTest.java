package cn.sst.parallelStream;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Stream;

/**
 * @author shengtengsun
 * @Description 并行流测试
 * @Date 2020/1/16 3:36 下午
 * @Version 1.1.0
 **/
public class ParallelStreamTest {
    public static void main(String[] args) {

        Stream<String> stream = Stream.of("How", "do", "you", "do");
        List<String> list = stream.collect(ArrayList::new, ArrayList::add, (t, u) -> {
            System.out.println("t:" + t + " u:" + u);
            t.addAll(u);
        });
        System.out.println(list);
    }

    public static void printPCInfo() {
        System.out.println(Runtime.getRuntime().availableProcessors());
        System.out.println(ForkJoinPool.getCommonPoolParallelism());
    }
}
