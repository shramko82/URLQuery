package ua.shramko.urlquery;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class CheckURL extends Thread {

  URLList urlList;
  RequestResult requestResult;

  public CheckURL(URLList urlList, RequestResult requestResult) {
    this.urlList = urlList;
    this.requestResult = requestResult;
  }

  public void run() {
    while (true) {
      try {
        URL urlFromList = urlList.getItem();
        boolean finish = requestResult.getResultURL() != null
            || urlFromList == null;
        if (finish) {
          break;
        }
        HttpURLConnection urlConnection = (HttpURLConnection) urlFromList
            .openConnection();
        urlConnection.setRequestMethod("HEAD");
        urlConnection.connect();
        int responseCode = urlConnection.getResponseCode();
        if (responseCode == 200) {
          requestResult.setResultURL(urlFromList);
          break;
        }
        sleep(1000);
      } catch (IOException e) {
        e.printStackTrace();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

}
