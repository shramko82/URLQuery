package ua.shramko.urlquery;

import java.net.URL;

public class URLwithState {
  URL url;
  URLStates state;
  public URLwithState(URL url, URLStates state) {
    this.url = url;
    this.state = state;
  }
  public URLwithState(URL url) {
    this.url = url;
    state = URLStates.OPEN;
  }
  
  
}
