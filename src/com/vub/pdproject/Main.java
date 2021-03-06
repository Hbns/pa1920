package com.vub.pdproject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
		// parameters to be given when running the jar file.
		int procP = Integer.parseInt(args[0]);
		//int procP = 4;
		int cutoT = Integer.parseInt(args[1]);
		//int cutoT = 100;
		int bench = Integer.parseInt(args[2]);
		//int bench = 1;
		int testruns = Integer.parseInt(args[3]);
		//int testruns = 4;

//		int benchmark = bench; //benchmark to be used (1-3 on your machine or 0 on Serenity)
//		long startTime = System.currentTimeMillis();
//		YelpQuery query = YelpQuery.forBenchmark(benchmark);
//		System.out.println("*** QUERY ***");
//		System.out.println(query); //prints out some information about this benchmark
//
		QueryEngine qe = new ParallelSearch(procP, cutoT);
//		//QueryEngine qe = new ParallelSearch(4,100);
//		//QueryEngine qe = new SequentialSearch();
//
//		List<RRecord> rbids = query.execute(qe); //execute query using query engine
//
//		//output the result of the query (names of businesses and their relevance, ordered by decreasing relevance)
//		System.out.println();
//		System.out.println("*** RESULT ***");
//		int i = 1;
//		for(RRecord rbid : rbids){
//			System.out.println
//					(i+") "+ query.getData().getBusiness(rbid.businessID).name+" ("+rbid.relevance_score+")");
//			i++;
//		}
//		long endTime = System.currentTimeMillis();
//		System.out.println("Total execution time: " + (endTime-startTime) + "ms");

		long[] temp = new long[testruns];
		for (int k = 1; k <= testruns; k++) {
			long startTime = System.currentTimeMillis();
			YelpQuery query = YelpQuery.forBenchmark(bench);
			//System.out.println("*** QUERY ***");
			//System.out.println(query); //prints out some information about this benchmark
			List<RRecord> rbids = query.execute(qe); //execute query using query engine
			//System.out.println();
			//System.out.println("*** RESULT ***");
			//int i = 1;
			//for (RRecord rbid : rbids) {
			//	System.out.println(i + ") " + query.getData().getBusiness(rbid.businessID).name + " (" + rbid.relevance_score + ")");
			//	i++;
			//}
			long endTime = System.currentTimeMillis();
			temp[k - 1] = (endTime - startTime);
			//System.out.println("Total execution time: " + (endTime - startTime) + "ms");
		}
		long avg = (Arrays.stream(temp).sum() / testruns);
		List<Long> send = Arrays.stream(temp).boxed().collect(Collectors.toList());
		write2file(bench, send, testruns ,procP, cutoT);
		//System.out.println("Avrage execution time: " + avg + "ms for preset " + bench + ".");
	}

	//writes runtimes for a given strategy to file
	static private void write2file(int preset, List<Long> runtimes,int testruns, int p, int t){
		File res_file = new File("runtimes"+testruns+"_P"+p+"_T"+t+".csv");
		if(res_file.exists()){
			//avoids accidentally overwriting previous results
			System.out.println(res_file+" already exists, skipping preset ");
			//continue;
		}
		PrintWriter csv_writer;
		try {
			csv_writer = new PrintWriter(new FileOutputStream(res_file,true));
			String line = "";
			for(Long rt : runtimes){
				line += ","+rt;
			}
			csv_writer.println(line);
			//csv_writer.println("Average run time: " + avg + " for " + runtimes.size() + " testruns. ");

			csv_writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
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
		QueryEngine sequential = new SequentialSearch();
		QueryEngine parallel = new ParallelSearch(4,0);
		QueryEngine parallelT60 = new ParallelSearch(4,60);
		QueryEngine parallelT1K = new ParallelSearch(4,1000);
		
		String keyword = "burger";
		YelpData data;
		List<RRecord> res_seq;
		List<RRecord> res_par;
		List<RRecord> res_parT60;
		List<RRecord> res_parT1K;
		
		data = YelpData.forReviewText("Super Food", 5, "");
		res_seq = sequential.search(keyword, data);
		res_par = parallel.search(keyword, data);
		res_parT60 = parallel.search(keyword, data);
		res_parT1K = parallel.search(keyword, data);
		assertEquals(res_seq.size(),res_par.size());
		assertEquals(res_seq.size(),res_parT60.size());
		assertEquals(res_seq.size(),res_parT1K.size());
				
		data = YelpData.forReviewText("Super Food", 5, "burger");
		res_seq = sequential.search(keyword, data);
		res_par = parallel.search(keyword, data);
		res_parT60 = parallel.search(keyword, data);
		res_parT1K = parallel.search(keyword, data);
		assertEquals(res_seq.get(0).relevance_score,res_par.get(0).relevance_score,epsilon);
		assertEquals(res_seq.get(0).relevance_score,res_parT60.get(0).relevance_score,epsilon);
		assertEquals(res_seq.get(0).relevance_score,res_parT1K.get(0).relevance_score,epsilon);
		
		data = YelpData.forReviewText("Super Food", 5, "This burger was soooo good!");
		res_seq = sequential.search(keyword, data);
		res_par = parallel.search(keyword, data);
		res_parT60 = parallel.search(keyword, data);
		res_parT1K = parallel.search(keyword, data);
		assertEquals(res_seq.get(0).relevance_score,res_par.get(0).relevance_score,epsilon);
		assertEquals(res_seq.get(0).relevance_score,res_parT60.get(0).relevance_score,epsilon);
		assertEquals(res_seq.get(0).relevance_score,res_parT1K.get(0).relevance_score,epsilon);
		
		data = YelpData.forReviewText("Super Food", 5, "Great burger");
		res_seq = sequential.search(keyword, data);
		res_par = parallel.search(keyword, data);
		res_parT60 = parallel.search(keyword, data);
		res_parT1K = parallel.search(keyword, data);
		assertEquals(res_seq.get(0).relevance_score,res_par.get(0).relevance_score,epsilon);
		assertEquals(res_seq.get(0).relevance_score,res_parT60.get(0).relevance_score,epsilon);
		assertEquals(res_seq.get(0).relevance_score,res_parT1K.get(0).relevance_score,epsilon);

		data = YelpData.forReviewText("Super Food", 5, "Great burger!");
		res_seq = sequential.search(keyword, data);
		res_par = parallel.search(keyword, data);
		res_parT60 = parallel.search(keyword, data);
		res_parT1K = parallel.search(keyword, data);
		assertEquals(res_seq.get(0).relevance_score,res_par.get(0).relevance_score,epsilon);
		assertEquals(res_seq.get(0).relevance_score,res_parT60.get(0).relevance_score,epsilon);
		assertEquals(res_seq.get(0).relevance_score,res_parT1K.get(0).relevance_score,epsilon);
		
		data = YelpData.forReviewText("Super Food", 5, "Great Burger!");
		res_seq = sequential.search(keyword, data);
		res_par = parallel.search(keyword, data);
		res_parT60 = parallel.search(keyword, data);
		res_parT1K = parallel.search(keyword, data);
		assertTrue(res_seq.size() == 0); //0
		assertTrue(res_par.size() == 0);
		assertTrue(res_parT60.size() == 0);
		assertTrue(res_parT1K.size() == 0);
		
		data = YelpData.forReviewText("Super Food", 5, "burgers don't get any better!");
		res_seq = sequential.search(keyword, data);
		res_par = parallel.search(keyword, data);
		res_parT60 = parallel.search(keyword, data);
		res_parT1K = parallel.search(keyword, data);
		assertTrue(res_seq.size() == 0); //0
		assertTrue(res_par.size() == 0);
		assertTrue(res_parT60.size() == 0);
		assertTrue(res_parT1K.size() == 0);

		data = YelpData.forReviewText("Super super burger - burger supper", 3, "While super burger supper, not super super burger (burger) supper as advertised");
		res_seq = sequential.search(keyword, data);
		res_par = parallel.search(keyword, data);
		res_parT60 = parallel.search(keyword, data);
		res_parT1K = parallel.search(keyword, data);
		assertEquals(res_seq.get(0).relevance_score,res_par.get(0).relevance_score,epsilon);
		assertEquals(res_seq.get(0).relevance_score,res_parT60.get(0).relevance_score,epsilon);
		assertEquals(res_seq.get(0).relevance_score,res_parT1K.get(0).relevance_score,epsilon);

		
		data = YelpData.forReviewText("Super burgeR", 1, "burgerburger burger    burger,,,burger-burger:burger; burger"+System.lineSeparator()+"burger!");
		res_seq = sequential.search(keyword, data);
		res_par = parallel.search(keyword, data);
		res_parT60 = parallel.search(keyword, data);
		res_parT1K = parallel.search(keyword, data);
		assertEquals(res_seq.get(0).relevance_score,res_par.get(0).relevance_score,epsilon); //5 occurrences (3rd,4th,7th,8th,9th substring occurrence)
		assertEquals(res_seq.get(0).relevance_score,res_parT60.get(0).relevance_score,epsilon);
		assertEquals(res_seq.get(0).relevance_score,res_parT1K.get(0).relevance_score,epsilon);
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
