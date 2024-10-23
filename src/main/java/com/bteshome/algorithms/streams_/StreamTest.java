package com.bteshome.algorithms.streams_;

public class StreamTest {
    public static void test() {
        var history = new StreamAlgorithms1.BrowserHistory("leet");
        history.visit("goo");
        history.visit("fa");
        history.visit("yt");
        System.out.println(history.forward(0));
    }
}
