/*
package com.vub.pdproject.search;

import com.vub.pdproject.data.YelpData;

import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ForkJoinPool;

public class ParallelSearchTask extends RecursiveTask {
    String query;
    YelpData dataInMemory;
    int first;
    int last;
    int p = 2;
    int t;

    */
/**
     * Creates a parallel search engine with p worker threads.
     * Counting occurrences is to be done sequentially (T ~ +inf)
     *
     * @param p parallelism level
     *//*

    ParallelSearchTask(int p) {
        this(p, Integer.MAX_VALUE);
    }


    */
/**
     * Creates a parallel search engine with p worker threads and sequential cut-off threshold T.
     *
     * @param p parallelism level
     * @param T sequential threshold
     *//*

  ParallelSearchTask(String query_str, YelpData data, int T) {
        this.p = p;
        this.t = T;
        //Hint: Initialise the Java Fork/Join framework here as well.
      this(query_str, data, 0, data.getBusinessIDs().size());

    }

    ParallelSearchTask(String query_str, YelpData data, int start, int end) {
        this.query = query_str;
        this.dataInMemory = data;
        this.first = start;
        this.last = end;

    }

    protected QueryEngine.RRecord compute() {

        int i = 0;
        int t = 0;
        if (last-first < T) {
            //single region
            double relevance = evaluate_relevance(query, dataInMemory.getReview().text);
        } else {
                int pivot = (first + last) / 2;
                ParallelSearchTask left = new ParallelSearchTask(query, dataInMemory, first, pivot);
                ParallelSearchTask right = new ParallelSearchTask(query, dataInMemory, pivot, last);
                right.fork();
                QueryEngine.RRecord relevant_businesses_left = left.compute();
                QueryEngine.RRecord relevant_businesses_right = right.join();

            return relevant_businesses_left.addAll(relevant_businesses_right);

        }
    }
    protected int evaluate_relevance(String qstr, String field)

    {
        ForkJoinPool forkJoinPool = new ForkJoinPool(p);

        int relevance = forkJoinPool.invoke(new ParallelOccurrencesTask(qstr, field));

        //return the result
        return relevance;
    }
}*/
