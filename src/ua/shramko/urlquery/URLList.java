package ua.shramko.urlquery;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class URLList {
  private List<URLwithState> urlList = new ArrayList<URLwithState>();

  public URLList(List<URL> list) {
    for (URL url : list) {
      urlList.add(new URLwithState(url));
    }
  }
  
  public synchronized URLwithState getItem() {
    URLwithState result = null;
    for (URLwithState url : urlList) {
      if (url.state == URLStates.OPEN) {
        result = url;
        url.state = URLStates.CHECKING;
        break;
      }
    }
    return result;
  }

  public boolean checkEnd() {
    boolean result = true;
    for (URLwithState url : urlList) {
      if (url.state != URLStates.BROKEN) {
        result = false;
        break;
      }
    }
    
    return result;
  } 
}
