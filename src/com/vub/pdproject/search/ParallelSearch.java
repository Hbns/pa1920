package com.vub.pdproject.search;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

import com.vub.pdproject.data.YelpData;

/**
 * TODO: A parallel implementation of QueryEngine using Java Fork-join
 * (see assignment for detailed requirements of this implementation)
 *
 * This is normally the only file you should change (except for Main.java for testing/evaluation).
 * If you for some reason feel the need to change another existing file, 
 * contact Steven Adriaensen first, and mention this modification explicitly in the report.
 * Note that adding new files for modularity purposes is always ok.
 *
 * @author You
 *
 */
public class ParallelSearch extends RecursiveTask<List<QueryEngine.RRecord>> implements QueryEngine{
	final int p; //parallelism level (i.e. max. # cores that can be used by Java Fork/Join)
	final int T; //sequential threshold (semantics depend on your cut-off implementation)

	/**
	 * Creates a parallel search engine with p worker threads.
	 * Counting occurrences is to be done sequentially (T ~ +inf)
	 *
	 * @param p parallelism level
	 */
	ParallelSearch(int p){
		this(p,Integer.MAX_VALUE);
	}

	/**
	 * Creates a parallel search engine with p worker threads and sequential cut-off threshold T.
	 *
	 * @param p parallelism level
	 * @param T sequential threshold
	 */
	public ParallelSearch(int p, int T){
		this.p = p;
		this.T = T;


		//Hint: Initialise the Java Fork/Join framework here as well.
	}

	protected List<RRecord> compute(){
		List<RRecord> result = new ArrayList<RRecord>();
		//continue
		return result;

	}

	@Override
	public List<RRecord> search(String query_str, YelpData data) {
		ForkJoinPool forkJoinPool = new ForkJoinPool(p);
		List<RRecord> relevant_businesses = forkJoinPool.invoke(new ParallelSearch(2,1000));

		//TODO: implement this method using Java Fork-Join
		return relevant_businesses;
	}

}
