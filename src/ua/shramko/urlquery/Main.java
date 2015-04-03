package ua.shramko.urlquery;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Main {

  public static URL checkURL(URLList list, int numberOfThreads) {
    RequestResult requestResult = new RequestResult();
    for (int i = 0; i < numberOfThreads; i++) {
      Thread thread = new CheckURL(list, requestResult);
      thread.start();
      try {
        thread.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    return requestResult.getResultURL();
    
  }

  public static void main(String[] args) {

    List<URL> list = new ArrayList<URL>();
    try {
      list.add(new URL("http://www.google.com"));
      list.add(new URL("http://www.ya.ru"));
      list.add(new URL("http://www.mail.ru"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    URLList urlList = new URLList(list);

    URL url = checkURL(urlList, 2);
    System.out.println("Result url: "+url.getHost());

  }

}
