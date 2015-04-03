package ua.shramko.urlquery;

import java.net.URL;
import java.util.List;

public class URLList {
  private List<URL> urlList;
  private int count;

  public URLList(List<URL> urlList) {
    this.urlList = urlList;
  }
  
  public synchronized URL getItem() {
    if (count < urlList.size()) {
      return urlList.get(count++);
    } else {
      return null;
    }
  } 
}
