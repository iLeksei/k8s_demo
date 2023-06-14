package com.example.examinator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Warmup(iterations = 1)
@Measurement(iterations = 3)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@BenchmarkMode(Mode.AverageTime)
@State(Scope.Benchmark)
@Fork(value = 2, jvmArgs = {"-Xms2G", "-Xmx2G"})
public class CompletableFutureAndFutureTests {

    private ObjectMapper objectMapper;
    private ExecutorService executor;

    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()
                .include(CompletableFutureAndFutureTests.class.getSimpleName())
                .forks(2)
                .build();

        new Runner(opt).run();
    }


    @Setup
    public void setUp() {
        objectMapper = new ObjectMapper();
        executor = Executors.newFixedThreadPool(2);
    }

    @Benchmark
    public void benchmarkTest1(Blackhole bh) throws JsonProcessingException {
        String result = getQuestionsAsync1();
        bh.consume(result);
    }

    @Benchmark
    public void benchmarkTest2(Blackhole bh) throws JsonProcessingException, ExecutionException, InterruptedException {
        String result = getQuestionsAsync2();
        bh.consume(result);
    }

    public String getQuestionsAsync1() throws JsonProcessingException {
        System.out.println("GET /questions-async-1");
        CompletableFuture<List<String>> jsQuestions = CompletableFuture.supplyAsync(() ->
                new ArrayList<>(){{
                    add("js-question-1");
                    add("js-question-2");
                }}
        );
        CompletableFuture<List<String>> javaQuestions = CompletableFuture.supplyAsync(() ->
                new ArrayList<>(){{
                    add("java-question-1");
                    add("java-question-2");
                }}
        );

        CompletableFuture<List<String>> questionsFuture = jsQuestions.thenCombine(javaQuestions,
                (jsResult, javaResult) -> {
                    jsResult.addAll(javaResult);
                    return jsResult;
                });

        List<String> questions = questionsFuture.join();
        return objectMapper.writeValueAsString(questions);
    }

    public String getQuestionsAsync2() throws ExecutionException, InterruptedException, JsonProcessingException {
        System.out.println("GET /questions-async-2");
        Future<List<String>> jsQuestions = executor.submit(() ->
                new ArrayList<>(){{
                    add("js-question-1");
                    add("js-question-2");
                }}
        );
        Future<List<String>> javaQuestions = executor.submit(() ->
                new ArrayList<>(){{
                    add("java-question-1");
                    add("java-question-2");
                }}
        );

        List<String> jsQuestionsResult = jsQuestions.get();
        List<String> javaQuestionsResult = javaQuestions.get();
        jsQuestionsResult.addAll(javaQuestionsResult);

        return objectMapper.writeValueAsString(jsQuestionsResult);
    }


}
