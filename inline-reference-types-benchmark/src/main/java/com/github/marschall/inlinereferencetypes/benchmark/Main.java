package com.github.marschall.inlinereferencetypes.benchmark;

import static org.openjdk.jmh.results.format.ResultFormatType.TEXT;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public final class Main {

  /**
   * Main method, runs the benchmarks.
   *
   * @param args first element contains the file name
   * @throws RunnerException  if something goes wrong during the execution of the benchmarks
   */
  public static void main(String[] args) throws RunnerException {
    int threads;
    if (args.length > 0) {
      threads = Integer.parseInt(args[0]);
    } else {
      threads = 1;
    }
    String outputFilename = "inlinereferencetypes-threads" + threads + ".txt";
    Options options = new OptionsBuilder()
            .include("com.github.marschall.inlinereferencetypes.benchmark.*")
            .warmupIterations(5)
            .measurementIterations(5)
            .forks(3)
            .resultFormat(TEXT)
            .threads(threads)
            .output(outputFilename)
            .build();
    new Runner(options).run();
  }

}