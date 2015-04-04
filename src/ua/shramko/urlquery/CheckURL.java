package ua.shramko.urlquery;

import java.io.IOException;
import java.net.HttpURLConnection;

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
        URLwithState urlFromList = urlList.getItem();
        boolean finish = requestResult.getResultURL() != null
            || urlFromList == null;
        if (finish) {
          break;
        }
        HttpURLConnection urlConnection = (HttpURLConnection) urlFromList.url
            .openConnection();
        urlConnection.setRequestMethod("HEAD");
        urlConnection.connect();
        int responseCode = urlConnection.getResponseCode();
        if (responseCode == 200) {
          requestResult.setResultURL(urlFromList.url);
          break;
        } else {
          urlFromList.state = URLStates.BROKEN;
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
