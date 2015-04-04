package ua.shramko.urlquery;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Main {

  public static URL checkURL(URLList list, int numberOfThreads)
      throws HostsDidNotAnswerException {
    RequestResult requestResult = new RequestResult();
    for (int i = 0; i < numberOfThreads; i++) {
      Thread thread = new CheckURL(list, requestResult);
      thread.start();
    }
    Thread thread = new CheckResultThread(list, requestResult);
    thread.start();
    try {
      thread.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    while (true) {
      if (requestResult.getResultURL() != null) {
        return requestResult.getResultURL();
      } else if (list.checkEnd()) {
        throw new HostsDidNotAnswerException();
      }
    }

  }

  public static void main(String[] args) {

    List<URL> list = new ArrayList<URL>();
    try {
      list.add(new URL("http://test.myownradio.biz/gradient"));
      list.add(new URL("http://test.myownradio.biz/gradient"));
      list.add(new URL("http://google.com"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    URLList urlList = new URLList(list);

    URL url;
    try {
      url = checkURL(urlList, 1);
      System.out.println("Result url: " + url.getHost());
    } catch (HostsDidNotAnswerException e) {
      e.printStackTrace();
    }

  }

}
