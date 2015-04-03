package ua.shramko.urlquery;

import java.net.URL;

public class RequestResult {

  private URL resultURL;

  public synchronized URL getResultURL() {
    return resultURL;
  }

  public synchronized void setResultURL(URL resultURL) {
    this.resultURL = resultURL;
  }

}
