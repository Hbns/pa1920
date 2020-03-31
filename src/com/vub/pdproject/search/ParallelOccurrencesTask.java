package com.vub.pdproject.search;

import java.util.concurrent.RecursiveTask;

public class ParallelOccurrencesTask extends RecursiveTask<Integer> {
    String Oquery;
    String Otext;
    int Ofirst;
    int Olast;


    ParallelOccurrencesTask(String keyword, String text) {
        this(keyword, text, 0, text.length());
    }

    ParallelOccurrencesTask(String keyword, String text, int first, int last) {
        this.Oquery = keyword;
        this.Otext = text;
        this.Ofirst = first;
        this.Olast = last;

    }

    protected Integer compute() {
        int res = 0;
        if (Olast - Ofirst < 2) {
            return res;
        } else {
            int pivot = (Ofirst - Olast) / 2;
            ParallelOccurrencesTask Oleft_task = new ParallelOccurrencesTask(Oquery, Otext, Ofirst, pivot);
            ParallelOccurrencesTask Oright_task = new ParallelOccurrencesTask(Oquery, Otext, pivot, Olast);
            Oright_task.fork();
            int Oleft_res = Oleft_task.compute();
            int Oright_res = Oright_task.join();
            return Oleft_res + Oright_res;
        }

    }
}
