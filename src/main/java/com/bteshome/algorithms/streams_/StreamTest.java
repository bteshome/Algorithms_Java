package com.bteshome.algorithms.streams_;

public class StreamTest {
    public static void test() {
        /*var history = new StreamAlgorithms1.BrowserHistory("leet");
        history.visit("goo");
        history.visit("fa");
        history.visit("yt");
        System.out.println(history.forward(0));
*/
        var mkAverage = new StreamAlgorithms3TODO.MKAverageTODO(3, 1);
        mkAverage.addElement(3);
        mkAverage.addElement(1);
        System.out.println(mkAverage.calculateMKAverage());
        mkAverage.addElement(10);
        System.out.println(mkAverage.calculateMKAverage());
        mkAverage.addElement(5);
        mkAverage.addElement(5);
        mkAverage.addElement(5);
        System.out.println(mkAverage.calculateMKAverage());

    }
}
