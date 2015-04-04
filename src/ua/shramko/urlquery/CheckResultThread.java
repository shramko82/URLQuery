package ua.shramko.urlquery;

public class CheckResultThread extends Thread {
  RequestResult requestResult;
  URLList urlList;

  public CheckResultThread(URLList urlList, RequestResult requestResult) {
    this.urlList = urlList;
    this.requestResult = requestResult;
  }

  public void run() {
    while (true) {
      boolean finish = requestResult.getResultURL() != null
          || urlList.checkEnd();
      if (finish) {
        break;
      }
    }
  }

}
