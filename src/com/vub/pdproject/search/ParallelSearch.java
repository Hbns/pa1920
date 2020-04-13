package com.vub.pdproject.search;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.vub.pdproject.Util;
import com.vub.pdproject.data.YelpData;
import com.vub.pdproject.data.models.Business;

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
public class ParallelSearch extends RecursiveTask<List<QueryEngine.RRecord>> implements QueryEngine {
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
	ParallelSearch(int p) {
		this(p, Integer.MAX_VALUE);
	}

	/**
	 * Creates a parallel search engine with p worker threads and sequential cut-off threshold T.
	 *
	 * @param p parallelism level
	 * @param T sequential threshold
	 */
	public ParallelSearch(int p, int T) {
		this.p = p;
		this.T = T;
		//Hint: Initialise the Java Fork/Join framework here as well.
		forkJoinPool = new ForkJoinPool(p);

	}

	/**
	 * Creates a parallel search engine where the size of total array is calculated.
	 *
	 * @param query_str the query string to look for.
	 * @param data      the data to search in.
	 */
	ParallelSearch(String query_str, YelpData data) {
		this(query_str, data, 0, data.getBusinessIDs().size());

	}

	/**
	 * Creates a parallel search engine with all necessary variables.
	 *
	 * @param query_str the query string to look for.
	 * @param data      the data to search in.
	 * @param first     first and last are used to divide the work for fork/join.
	 * @param last      first and last are used to divide the work for fork/join.
	 */
	ParallelSearch(String query_str, YelpData data, int first, int last) {
		this.query = query_str;
		this.dataInMem = data;
		this.start = first;
		this.end = last;
	}

	/**
	 * The compute method for the fork/join framework.
	 *
	 * @return List<RRecords> with relevant businesses.
	 */
	protected List<RRecord> compute() {
		List<RRecord> result = new ArrayList<RRecord>();
		if (end - start < 2) {
			String bid = dataInMem.getBusinessIDs().get(start);
			double relevance = evaluate_relevanceP(query, bid, dataInMem);
			if (relevance > 0) {
				result.add(new RRecord(bid, relevance));

			}
		} else {
			int pivot = (start + end) / 2;
			ParallelSearch left = new ParallelSearch(query, dataInMem, start, pivot);
			ParallelSearch right = new ParallelSearch(query, dataInMem, pivot, end);
			right.fork();
			List<RRecord> relevant_businesses_left = left.compute();
			List<RRecord> relevant_businesses_right = right.join();
			List<RRecord> finalList = Stream.of(relevant_businesses_left, relevant_businesses_right)
					.flatMap(x -> x.stream())
					.collect(Collectors.toList());

			return finalList;
		}
		return result;

	}

	/**
	 * The search function to be called to start a parallel search.
	 *
	 * @param query_str the query string to look for.
	 * @param data:     The subset of businesses/reviews to be searched.
	 * @return
	 */
	@Override
	public List<RRecord> search(String query_str, YelpData data) {
		//Search relevant businesses
		List<RRecord> relevant_businesses = forkJoinPool.invoke(new ParallelSearch(query_str, data));
		//Convert List to Array.
		RRecord[] sortArray = relevant_businesses.stream().toArray(RRecord[]::new);
		//Sort the Array.
		sort(sortArray);
		//Convert to list and return
		List<RRecord> returnList = Arrays.asList(sortArray);
		return returnList;

	}

	/**
	 * http://www.java-allandsundry.com/2012/08/mergesort-using-forkjoin-framework.html
	 * Generic MergeSort used here for sorting the list with relevant businesses.
	 * @param a
	 * @param <T>
	 */
	public  <T extends Comparable<? super T>> void sort(T[] a) {
		T[] helper = a.clone();
		//T[] helper = (T[])Array.newInstance(a[1].getClass() , a.length);
		forkJoinPool.invoke(new MergeSortTask<T>(a, helper, 0, a.length-1));
	}

	public class MergeSortTask <T extends Comparable<? super T>> extends RecursiveAction{
		private static final long serialVersionUID = -749935388568367268L;
		private final T[] a;
		private final T[] helper;
		private final int lo;
		private final int hi;

		public MergeSortTask(T[] a, T[] helper, int lo, int hi){
			this.a = a;
			this.helper = helper;
			this.lo = lo;
			this.hi = hi;
		}
		@Override
		protected void compute() {
			if (lo>=hi) return;
			int mid = lo + (hi-lo)/2;
			MergeSortTask<T> left = new MergeSortTask<>(a, helper, lo, mid);
			MergeSortTask<T> right = new MergeSortTask<>(a, helper, mid+1, hi);
			invokeAll(left, right);
			merge(this.a, this.helper, this.lo, mid, this.hi);

		}
		private void merge(T[] a, T[] helper, int lo, int mid, int hi){
			for (int i=lo;i<=hi;i++){
				helper[i]=a[i];
			}
			int i=lo,j=mid+1;
			for(int k=lo;k<=hi;k++){
				if (i>mid){
					a[k]=helper[j++];
				}else if (j>hi){
					a[k]=helper[i++];
				}else if(isLess(helper[i], helper[j])){
					a[k]=helper[i++];
				}else{
					a[k]=helper[j++];
				}
			}
		}
		private boolean isLess(T a, T b) {
			return a.compareTo(b) < 0;
		}
	}
	public double evaluate_relevanceP(String keyword, String businessID, YelpData data) {
		//fetch data for business
		Business bd = data.getBusiness(businessID);

		//check in how many times query string appears in reviews
		int occurences = 0;
		for(String rid : bd.reviews){
			occurences += countOccurrencesP(keyword,data.getReview(rid).text);
		}

		//calculate relevance score
		double relevance_score = 0;
		if(countOccurrencesP(keyword,bd.name) > 0){
			relevance_score = 0.5;
		}
		relevance_score += 1.5*occurences/(occurences+20);
		relevance_score *= bd.stars;
		return relevance_score;
	}

	/**
	 *
	 * @param keyword	The keyword to be searched for
	 * @param text 		The text to be searched.
	 * @return 			The number of occurrences of a keyword in a text
	 * 					Search is case-sensitive.
	 * 					To match, a word in text must be delimited by white space/punctuation marks
	 *                  (or appear at the beginning/end of the text)
	 *
	 * 					examples:
	 *
	 * 					keyword: burger
	 *
	 * 					text: "This burger was so good!" (returns 1)
	 * 					text: "Great burger" (returns 1)
	 * 					text: "Great burger!" (returns 1)
	 * 					text: "Great Burger" (returns 0)
	 * 					text: "burgers don't get any better!" (returns 0)
	 *
	 */
	public int countOccurrencesP(String keyword, String text){
		int count = 0;
		if (text.length() == 0){
			count = 0;
		}else{
			count = forkJoinPool.invoke(new CountOccurences(keyword, prepareText(text)));
		}
		return count;
	}

	public String[] prepareText(String input){
		String possibleSplits = "\\s|,|!|\\(|\\)|\\.|-";
		String[] output;
		output = input.split(possibleSplits);
		return output;
	}

	public class CountOccurences extends RecursiveTask<Integer>{
		String query;
		String[] review;
		int start;
		int end;


		CountOccurences(String keyword, String[] text){
			this(keyword, text, 0, text.length);
		}

		CountOccurences(String keyword, String[] text, int start, int end){
			this.query = keyword;
			this.review = text;
			this.start = start;
			this.end = end;
		}
		protected Integer compute(){
		int count = 0;
			if (end - start < 2) {
				if (query.equals(review[start])){
					count++;
				}
			}
			//else if (T > 0){
//split in T parts and handle each part seq
			//}
			else{
				int pivot = (start + end) / 2;
				CountOccurences left = new CountOccurences(query, review, start, pivot);
				CountOccurences right = new CountOccurences(query, review, pivot, end);
				right.fork();
				int occurrence_left = left.compute();
				int occurrences_right = right.join();
				int total_occurrences = occurrence_left + occurrences_right;

				count = total_occurrences;
			}
			return count;

		}
	}
}



/*	public class Node {
		Node left;
		Node right;
		int sum;
		int lo;
		int hi;
		public Node(Node ln, Node rn, int s, int l, int h) {
			left  = ln; right = rn; sum   = s;
			lo    = l; hi    = h;
		}
		boolean isALeaf() { return left == null; }
	}
	public class PrefixSum extends RecursiveAction {

		private static final long serialVersionUID = 1L;

		*//**
		 * This task computes the first pass, the "up" pass of a parallel prefix.
		 * It builds a binary tree in parallel from bottom (ie. the leaf nodes) and to top
		 * (i.e returns the root).
		 * Every node holds the sum of the integers in its range [low, high[.
		 * The root of the tree holds the sum for the entire range [O,n[
		 *//*
		class BuildTree extends RecursiveTask<Node> {

			private static final long serialVersionUID = 1L;

			private int SEQUENTIAL_THRESHOLD = 1000;

			int[] input;
			int lo;
			int hi;

			public BuildTree(int[] in, int l, int h) {
				input = in;
				lo = l;
				hi = h;
			}

			@Override
			protected Node compute() {
				if (hi - lo <= SEQUENTIAL_THRESHOLD) {
					int ans = 0;
					for (int i = lo; i < hi; ++i)
						ans += input[i];
					return new Node(null, null, ans, lo, hi);
				} else {
					int mid = (lo + hi) / 2;
					BuildTree left = new BuildTree(input, lo, mid);
					BuildTree right = new BuildTree(input, mid, hi);
					left.fork();
					Node rightNode = right.compute();
					Node leftNode = left.join();
					int sum = leftNode.sum + rightNode.sum;
					return new Node(leftNode, rightNode, sum, lo, hi);
				}
			}
		}

		Node node;
		int fromLeft;
		int[] input;
		int[] output;

		PrefixSum(Node n, int sum, int[] in, int[] out) {
			node = n;
			fromLeft = sum;
			input = in;
			output = out;
		}

		*//**
		 * PrefixSum computes the second pass, the "down" pass of a parallel prefix.
		 * It computes the prefix-sum passing "down" as an argument the sum of the array
		 * indices to the left of the node
		 *//*

		@Override
		protected void compute() {
			if (node.isALeaf()) {
				// sequential prefix sum from [lo,hi[
				int lo = node.lo;
				int hi = node.hi;
				output[lo] = fromLeft + input[lo];
				for (int i = lo + 1; i < hi; i++) {
					output[i] = output[i - 1] + input[i];
				}
			} else {
				PrefixSum left = new PrefixSum(node.left, fromLeft, input, output);
				PrefixSum right = new PrefixSum(node.right, fromLeft + node.left.sum, input, output);
				left.fork();
				right.compute();
				left.join();
			}
		}

		int[] prefixSumParallel(int[] input) {
			int[] output = new int[input.length];
			// step 1: "up" pass
			Node root = forkJoinPool.invoke(new BuildTree(input, 0, input.length));
			// step 2: "down" pass
			forkJoinPool.invoke(new PrefixSum(root, 0, input, output));
			return output;
		}

	}

	public interface FilterTwo<T,U> {
		public boolean filter(T element, U value);
	}

	public class Pack extends RecursiveAction{

		private static final long serialVersionUID = 1L;

		private int SEQUENTIAL_THRESHOLD = 1000;


		final FilterTwo<Integer, Integer> greaterThan = (d, e) -> (d > e);
		final FilterTwo<Integer, Integer> smallerThan = (d, e) -> (d < e);

		final int[] input;
		final int[] output;
		final int lo;
		final int hi;
		final int[] bitVector;
		final int[] bitSum;

		public Pack(int[] in, int[] out, int l, int h, int[] bitv, int[] bits) {
			input = in; output =out; lo = l; hi = h; bitVector = bitv; bitSum = bits;
		}

		@Override
		protected void compute() {
			if ((hi - lo) < SEQUENTIAL_THRESHOLD) {
				for (int i = lo; i < hi; i++) {
					if (bitVector[i] ==1)
						output[bitSum[i]-1] = input[i];
				}
			}else{
				int mid = (lo + hi)/2;
				Pack left = new Pack(input, output, lo, mid, bitVector, bitSum);
				Pack right = new Pack(input, output, mid, hi, bitVector, bitSum);
				left.fork();
				right.compute();
				left.join();
			}
		}


		int[] packParallel(int[] input, FilterTwo<Integer, Integer> filter ){
			MapArray.BinaryOp<Integer> bitOn = (elem, pivot) -> filter.filter(elem, pivot) ? 1:0	;
			// step 1: perform parallel map to produce bit vector of {0,1} indicating
			// whether the corresponding input element satisfies the filter
			final int[] bitVector =  MapArray.map(input, bitOn);
			// step 2: perform parallel prefix sum on the bit vector produced in the previous step.
			final int[] bitSum = PrefixSum.prefixSumParallel(bitVector);
			// step 3: perform parallel map to assign input elements to bitsum[i]-1 in the
			// output array if bitvector[i] is set
			int out_length = bitSum[bitSum.length-1];
			final int[] output = new int[out_length];
			forkJoinPool.invoke(new Pack(input, output, 0, input.length, bitVector, bitSum));
			return output;

		}

	}
	public interface BinaryOp<T> {
		public Integer apply(T op1, T op2);
	}
	public class MapArray extends RecursiveAction{

		*//**
		 * Users of {@link MapArray} should use a {@link BinaryOp} instance to map
		 * source to target elements.
		 * op1: index of the source element of the array
		 * op2: source element of the array
		 *//*



		final BinaryOp<Integer> sum = (a,b) -> a+b;
		final BinaryOp<Integer> square = (a,b) -> a*b;


		private static final long serialVersionUID = 1L;

		private int SEQUENTIAL_THRESHOLD = 1000;

		int lo;
		int hi;
		int[] in;
		int[] out;
		BinaryOp<Integer> binop;

		public MapArray(int[] input, int[] output, int l,int h, BinaryOp<Integer> b){
			lo=l; hi=h; in = input; out = output; binop = b;
		}
		protected void compute(){
			if( (hi - lo) < SEQUENTIAL_THRESHOLD) {
				for(int i=lo; i < hi; i++)
					out[i] = binop.apply(i, in[i]);
			} else {
				int mid = (hi+lo)/2;
				MapArray left = new MapArray(in,out,lo,mid,binop);
				MapArray right= new MapArray(in,out,mid,hi,binop);
				left.fork();
				right.compute();
				left.join();
			}
		}


		int[] map(int[] input, BinaryOp<Integer> binop){
			int[] output = new int[input.length];
			MapArray t = new MapArray(input, output, 0, input.length,binop);
			forkJoinPool.invoke(t);
			return output;
		}}*/


//prefix, pack