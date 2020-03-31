package com.vub.pdproject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.vub.pdproject.data.YelpData;
import com.vub.pdproject.search.ParallelSearch;
import com.vub.pdproject.search.QueryEngine;
import com.vub.pdproject.search.SequentialSearch;
import com.vub.pdproject.search.YelpQuery;
import com.vub.pdproject.search.QueryEngine.RRecord;

public class Main {
	
	/*
	 * Main method, providing a usage example and can be used for experimentation
	 */
	public static void main(String[] args) throws IOException {
		int benchmark = 1; //benchmark to be used (1-3 on your machine or 0 on Serenity)
		
		YelpQuery query = YelpQuery.forBenchmark(benchmark);
		System.out.println("*** QUERY ***");
		System.out.println(query); //prints out some information about this benchmark
		
		QueryEngine qe = new SequentialSearch();
		
		List<RRecord> rbids = query.execute(qe); //execute query using query engine
		
		//output the result of the query (names of businesses and their relevance, ordered by decreasing relevance)
		System.out.println();
		System.out.println("*** RESULT ***");
		int i = 1;
		for(RRecord rbid : rbids){
			System.out.println(i+") "+query.getData().getBusiness(rbid.businessID).name+" ("+rbid.relevance_score+")");
			i++;
		}
	}
	
	/*
	 * Unit tests
	 */
	final static double epsilon = 0.0001;
	
	@Test
    public void testParallelization2A() {
		QueryEngine sequential = new SequentialSearch();
		QueryEngine parallel = new ParallelSearch(4,Integer.MAX_VALUE);
		
		YelpQuery query;
		List<RRecord> res_seq;
		List<RRecord> res_par;
		
		//benchmark 1
		query = YelpQuery.forBenchmark(1);
		res_seq = query.execute(sequential);
		res_par = query.execute(parallel);
		assertTrue(correctOrder(res_par)); //check order
		assertEquals(res_seq.size(),res_par.size()); //check length
		assertTrue(correctRelevanceValues(res_seq,res_par,epsilon)); //check relevance values within a epsilon
		
		//benchmark 2
		query = YelpQuery.forBenchmark(2);
		res_seq = query.execute(sequential);
		res_par = query.execute(parallel);
		assertEquals(res_seq.size(),res_par.size()); //check length
		assertTrue(correctOrder(res_par)); //check order
		assertTrue(correctRelevanceValues(res_seq,res_par,epsilon)); //check values within a epsilon
		
		//benchmark 3
		query = YelpQuery.forBenchmark(3);
		res_seq = query.execute(sequential);
		res_par = query.execute(parallel);
		assertEquals(res_seq.size(),res_par.size()); //check length
		assertTrue(correctOrder(res_par)); //check order
		assertTrue(correctRelevanceValues(res_seq,res_par,epsilon)); //check values within a epsilon
    }
	
	@Test
    public void testParallelization2B() {
		//TODO: Add some tests for intermediary T values yourself
		QueryEngine sequential = new SequentialSearch();
		QueryEngine parallel = new ParallelSearch(4,0);
		
		YelpQuery query;
		List<RRecord> res_seq;
		List<RRecord> res_par;
		
		//benchmark 1
		query = YelpQuery.forBenchmark(1);
		res_seq = query.execute(sequential);
		res_par = query.execute(parallel);
		assertTrue(correctOrder(res_par)); //check order
		assertEquals(res_seq.size(),res_par.size()); //check length
		assertTrue(correctRelevanceValues(res_seq,res_par,epsilon)); //check relevance values within a epsilon
		
		//benchmark 2
		query = YelpQuery.forBenchmark(2);
		res_seq = query.execute(sequential);
		res_par = query.execute(parallel);
		assertEquals(res_seq.size(),res_par.size()); //check length
		assertTrue(correctOrder(res_par)); //check order
		assertTrue(correctRelevanceValues(res_seq,res_par,epsilon)); //check values within a epsilon
		
		//benchmark 3
		query = YelpQuery.forBenchmark(3);
		res_seq = query.execute(sequential);
		res_par = query.execute(parallel);
		assertEquals(res_seq.size(),res_par.size()); //check length
		assertTrue(correctOrder(res_par)); //check order
		assertTrue(correctRelevanceValues(res_seq,res_par,epsilon)); //check values within a epsilon
    }
	
	@Test
    public void testCountOccurrences() {
		//TODO: Add some tests for intermediary T values yourself
		QueryEngine sequential = new SequentialSearch();
		QueryEngine parallel = new ParallelSearch(4,0);
		
		String keyword = "burger";
		YelpData data;
		List<RRecord> res_seq;
		List<RRecord> res_par;
		
		data = YelpData.forReviewText("Super Food", 5, "");
		res_seq = sequential.search(keyword, data);
		res_par = parallel.search(keyword, data);
		assertEquals(res_seq.size(),res_par.size());
				
		data = YelpData.forReviewText("Super Food", 5, "burger");
		res_seq = sequential.search(keyword, data);
		res_par = parallel.search(keyword, data);
		assertEquals(res_seq.get(0).relevance_score,res_par.get(0).relevance_score,epsilon);
		
		data = YelpData.forReviewText("Super Food", 5, "This burger was soooo good!");
		res_seq = sequential.search(keyword, data);
		res_par = parallel.search(keyword, data);
		assertEquals(res_seq.get(0).relevance_score,res_par.get(0).relevance_score,epsilon);
		
		data = YelpData.forReviewText("Super Food", 5, "Great burger");
		res_seq = sequential.search(keyword, data);
		res_par = parallel.search(keyword, data);
		assertEquals(res_seq.get(0).relevance_score,res_par.get(0).relevance_score,epsilon);

		data = YelpData.forReviewText("Super Food", 5, "Great burger!");
		res_seq = sequential.search(keyword, data);
		res_par = parallel.search(keyword, data);
		assertEquals(res_seq.get(0).relevance_score,res_par.get(0).relevance_score,epsilon);
		
		data = YelpData.forReviewText("Super Food", 5, "Great Burger!");
		res_seq = sequential.search(keyword, data);
		res_par = parallel.search(keyword, data);
		assertTrue(res_seq.size() == 0); //0
		
		data = YelpData.forReviewText("Super Food", 5, "burgers don't get any better!");
		res_seq = sequential.search(keyword, data);
		res_par = parallel.search(keyword, data);
		assertTrue(res_seq.size() == 0); //0
		
		data = YelpData.forReviewText("Super super burger - burger supper", 3, "While super burger supper, not super super burger (burger) supper as advertised");
		res_seq = sequential.search(keyword, data);
		res_par = parallel.search(keyword, data);
		assertEquals(res_seq.get(0).relevance_score,res_par.get(0).relevance_score,epsilon);
		
		data = YelpData.forReviewText("Super burgeR", 1, "burgerburger burger    burger,,,burger-burger:burger; burger"+System.lineSeparator()+"burger!");
		res_seq = sequential.search(keyword, data);
		res_par = parallel.search(keyword, data);
		assertEquals(res_seq.get(0).relevance_score,res_par.get(0).relevance_score,epsilon); //5 occurrences (3rd,4th,7th,8th,9th substring occurrence)
    }
	
	static private boolean correctOrder(List<RRecord> res){
		for(int i = 1; i < res.size(); i++){
			if(res.get(i-1).relevance_score < res.get(i).relevance_score){
				return false;
			}
		}
		return true;
	}
	
	static private boolean correctRelevanceValues(List<RRecord> res_seq, List<RRecord> res_par, double epsilon){
		for(int i = 0; i < res_seq.size(); i++){
			if(Math.abs(res_seq.get(i).relevance_score - res_par.get(i).relevance_score) > epsilon){
				return false;
			}
		}
		return true;
	}

}
