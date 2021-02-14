package com.cultureamp;

import com.cultureamp.report.ReportFactory;

public class App {
  public static void main(String[] args) {
    ReportFactory reportFactory = new ReportFactory(args);
    
    reportFactory.
        getParticipationReport().
        generate();

    reportFactory.
        getRatingReport().
        generate();
  }

}
