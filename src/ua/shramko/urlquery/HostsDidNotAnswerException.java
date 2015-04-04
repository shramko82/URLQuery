package ua.shramko.urlquery;

public class HostsDidNotAnswerException extends Exception {
  private static final long serialVersionUID = 1L;

  HostsDidNotAnswerException() {
    System.out.println("Any host did not answer");
  }
}
