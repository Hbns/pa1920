package com.vub.pdproject.search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.vub.pdproject.data.YelpData;

import static com.vub.pdproject.search.SequentialSearch.evaluate_relevance;

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
	int p; //parallelism level (i.e. max. # cores that can be used by Java Fork/Join)
	int T; //sequential threshold (semantics depend on your cut-off implementation)
	static ForkJoinPool forkJoinPool;
	String query;
	YelpData dataInMem;
	int start;
	int end;

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
		forkJoinPool = new ForkJoinPool(p);

	}

	/**
	 * Creates a parallel search engine where the size of total array is calculated.
	 * @param query_str the query string to look for.
	 * @param data the data to search in.
	 */
	ParallelSearch(String query_str, YelpData data){
		this(query_str, data,0,data.getBusinessIDs().size());

	}

	/**
	 * Creates a parallel search engine with all necessary variables.
	 * @param query_str the query string to look for.
	 * @param data the data to search in.
	 * @param first first and last are used to divide the work for fork/join.
	 * @param last first and last are used to divide the work for fork/join.
	 */
	ParallelSearch(String query_str, YelpData data, int first, int last){
		this.query = query_str;
		this.dataInMem = data;
		this.start = first;
		this.end = last;
	}

	/**
	 * The compute method for the fork/join framework.
	 * @return List<RRecords> with relevant businesses.
	 */
	protected List<RRecord> compute(){
		List<RRecord> result = new ArrayList<RRecord>();
		if (end - start < 2) {
			String bid = dataInMem.getBusinessIDs().get(start);
			double relevance = evaluate_relevance(query, bid, dataInMem);
			if (relevance > 0) {
				result.add(new RRecord(bid, relevance));

			}
		}else{
			int pivot = (start + end) / 2;
			ParallelSearch left = new ParallelSearch(query, dataInMem, start, pivot);
			ParallelSearch right = new ParallelSearch(query, dataInMem, pivot, end);
			right.fork();
			List<RRecord> relevant_businesses_left = left.compute();
			List<RRecord> relevant_businesses_right = right.join();
			List<RRecord> finalList = Stream.of(relevant_businesses_left, relevant_businesses_right)
					.flatMap(x -> x.stream())
					.collect(Collectors.toList());

			//and sort the list parallel!
			Collections.sort(finalList);

			return finalList;
		}
		return result;

	}

	/**
	 * The search function to be called to start a parallel search.
	 * @param query_str the query string to look for.
	 * @param data: The subset of businesses/reviews to be searched.
	 * @return
	 */
	@Override
	public List<RRecord> search(String query_str, YelpData data) {

		List<RRecord> relevant_businesses = forkJoinPool.invoke(new ParallelSearch(query_str, data));
		List<RRecord> sorted_businesses = forkJoinPool.invoke(new ParallelSort(relevant_businesses));
		//TODO: implement this method using Java Fork-Join
		return relevant_businesses;
	}

}
class ParallelSort extends RecursiveTask<List<QueryEngine.RRecord>>{
	List<QueryEngine.RRecord> list;

	ParallelSort(List<QueryEngine.RRecord> lst){
this.list = lst;
	}

	@Override
	protected List<QueryEngine.RRecord> compute() {
		return list;
	}
}
