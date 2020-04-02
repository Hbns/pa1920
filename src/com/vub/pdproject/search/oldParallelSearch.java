/*
package com.vub.pdproject.search;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

import com.vub.pdproject.data.YelpData;

*/
/**
 * TODO: A parallel implementation of QueryEngine using Java Fork-join
 * (see assignment for detailed requirements of this implementation)
 * <p>
 * This is normally the only file you should change (except for Main.java for testing/evaluation).
 * If you for some reason feel the need to change another existing file,
 * contact Steven Adriaensen first, and mention this modification explicitly in the report.
 * Note that adding new files for modularity purposes is always ok.
 *
 * @author You
 *//*

public class ParallelSearch implements QueryEngine {
    final int p = 2; //parallelism level (i.e. max. # cores that can be used by Java Fork/Join)
    final int T = 1000; //sequential threshold (semantics depend on your cut-off implementation)


    @Override
    public List<RRecord> search(String query_str, YelpData data) {

        ForkJoinPool forkJoinPool = new ForkJoinPool(p);

       List<RRecord> relevant_businesses = forkJoinPool.invoke(new ParallelSearchTask(query_str, data,T));

        //return the result
        return relevant_businesses;


        //TODO: implement this method using Java Fork-Join


    }

}
*/
