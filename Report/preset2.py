#! /usr/bin/env python3

print('Preset2')
import matplotlib.pyplot as plt
 
seqPreset1=[455,382,383,378,383,379,381,379,382,378,382,380,383,382,381,378,382,384,383,380,381,378,380,387,381,380,380,378,382,384,381,381,382,378,382,381,383,381,381,378,383,387,383,382,382,377,385,384,380,382,381,378,380,387,385,383,381,378,385,384,380,382,383,377,384,382,381,380,381,378,384,382,381,380,381,377,384,384,379,381,381,378,384,382,382,380,381,378,382,380,383,384,383,378,380,380,380,380,380,377,384,382,383,381,380,377,380,384,382,380,382,377,384,385,381,379,382,377,380,386,381,382,381,378,386,387,379,381,382,377,384,380,384,380,382,377,383,383,381,380,380,378,380,384,396,380,382,377,386,382,382,381,383,377,383,379,384,379,382,377,386,382,384,379,380,378,380,383,380,380,382,378,380,379,380,380,382,378,386,385,384,379,380,378,384,385,382,380,381,378,382,382,381,380,381,378,382,383,380,382,381,377,380,381,382,382,381,378,384,386,383,380,381,378,383,380,382,382,381,378,384,380,382,381,381,377,381,382,385,381,381,378,383,383,379,381,382,378,379,384,398,380,380,377,384,385,383,381,394,377,380,387,380,382,383,377,386,386,381,380,380,377,384,380,383,381,379,378,384,389,381,380,380,378,382,384,382,381,382,378,385,382,380,381,381,378,381,380,381,380,381,377,383,387,384,380,380,377,382,381,388,381,381,377,383,384,383,381,381,377,380,382,381,381,381,378,383,381,380,380,380,378,380,379,384,380,382,377,383,385,386,381,381,377,380,382,383,380,382,378,386,385,381,380,383,377,382,379,384,381,382,377,380,382,382,381,388,377,383,384,382,380,382,377,382,387,382,381,383,377,386,380,385,380,383,377,385,384,382,381,383,378,380,385,380,381,383,377,385,383,384,380,382,378,385,382,382,380,385,377,382,385,380,382,383,377,384,383,381,380,382,377,380,380,382,379,381,377,385,387,383,380,389,377,384,381,381,381,381,377,380,383,384,380,383,377,379,382,382,381,381,377,380,383,382,381,383,377,380,381,385,380,381,377,379,381,381,381,381,377,386,381,382,379,384,377,380,383,382,381,382,377,385,380,383,381,385,377,384,386,380,382,384,377,386,382,385,380,383,377,385,382,380,381,387,377,383,380,380,380,381,378,384,387,383,381,383,377,380,384,381,379,384,379,382,379,380,380,383,378,382,379,379,379,382,377,380,383,381,383,383,377,380,382,380,381,383,377,380,386,380,381,382,378,380,384,387,380,383,377,380,385,381,379,384,377,384,384,380,381,383,377,382,386,381,380,385,377,380,380,380,381,382,377,384,384,381,383,382,378,380,381,380,380,383,378,379,383,383,380,382,378,379,381,381,379,383,377,384,385,383,379,385,377,380,379,381,381,389,377,380,380,384,380,383,377,385,385,380,380,383,377,380,381,381,381,383,378,385,383,380,381,388,378,380,385,381,382,384,377,380,391,383,379,383,377,383,380,380,381,383,377,383,381,382,380,383,378,384,388,379,380,384,377,379,381,395,380,387,377,383,386,389,381,383,377,379,387,381,381,383,377,385,386,380,380,383,378,382,385,380,381,382,377,383,386,380,381,384,377,383,381,382,381,384,377,382,384,384,381,383,377,379,381,383,381,381,378,383,382,381,380,382,378,386,388,381,382,381,377,384,383,384,381,382,377,384,384,379,379,383,377,382,384,382,379,383,377,380,383,382,380,385,377,381,386,382,379,383,377,387,382,385,380,383,377,380,380,379,379,383,377,380,384,381,382,384,377,381,380,383,380,384,377,380,381,381,379,387,378,384,383,380,381,382,378,386,382,380,381,383,377,386,383,383,383,385,377,387,383,381,381,384,378,384,384,380,380,383,377,387,387,379,381,384,377,388,383,383,381,383,377,379,382,384,379,383,377,379,385,383,380,383,378,383,387,380,379,384,377,384,385,383,382,383,377,380,383,382,380,384,377,382,383,381,380,381,377,383,384,383,379,382,377,383,380,380,381,382,378,381,381,380,379,384,377,379,388,383,381,384,377,380,385,387,379,383,377,384,381,385,381,383,378,380,387,383,381,382,377,387,380,382,380,383,377,383,379,382,380,383,377,380,389,382,381,383,377,385,381,382,379,388,377,384,382,380,381,383,377,386,384,380,382,384,377,393,393,382,380,382,377,385,380,382,379,382,377,380,382,379,381,382,377,382,384,382,379,382,377,379,382,381,381,385,377,384,385,382,381,384,377,380,382,383,380,383,377,381,385,385,380,383,377,382,382,380,381,383,377,384,384,381,383,383,377,381,380,387,380,382,377,387,386,382,380,382,378,385,386,382,379,383,378,382,384,383,381,383,381,389,381,380,381,384,377,386,385,380,381,383,378,384,380,382,380,384,377,379,380,380,381,389,377,386,380,381,381,382,378,386,380,390,380,382,377,384,383,382,381,387,377,386,388,389,381,382,378,380,381,387,379,381,377,380,380,382,382,381,378,380,380,380,381,382,377,386,388,380,381,382,378,379,381,380,381,379,378,380,381,380,380,381,377,380,382,382,379,382,378,380,381,381,379,382,377,385,379,381,381,383,378,385,382,384,382,381,378,384,383,383,380,382,378,382,382,380,381,380,377,381,380,380,381,383,377,383,379,381,380,382,377,383,383,384,380,382,378,382,379,384,382,384,377,380,380,383,382,384,378,381,386,383,381,382,377,385,385,383,383,382,378,379,384,381,381,381,378,381,385,381,381,382,378,383,382,382,379,381,378,384,379,380,380,381,377,380,379,386,379,382,378,382,383,384,380,380,378,379,383,383,383,382,378,381,383,383,382,386,378,383,384,379,380,381,378,383,381,382,381,380,378,382,381,380,381,382,377,385,384,393,380,382,377,382,385,383,381,382,377,383,387,384,380,382,378,382,380,383,380,381,378,385,381,379,380,381,378,379,380,387,382,383,378,383,382,381,383,384,377,385,381,381,380,382,378,382,382,383,380,382,378,384,381,384,379,386,377,380,388,384,380,383,378,385,384,381,382,381,378,380,381,382,379,382,378,382,382,380,380,383,377,381,382,383,380,382,378,384,383,381,381,382,377,380,380,387,381,382,378,382,383,383,382,380,378,379,385,382,383,382,377,384,384,381,379,382,378,382,386,383,380,382,378,383,380,384,380,382,377,383,379,384,380,382,378,381,384,388,381,383,378,385,381,383,383,381,378,386,381,382,381,387,378,380,379,381,379,381,377,380,380,381,380,383,377,383,381,383,380,384,377,380,381,386,379,382,377,384,381,383,379,382,377,381,381,380,380,381,377,383,385,383,382,380,378,384,383,380,380,386,378,383,382,380,381,381,378,384,383,383,379,382,378,386,379,380,381,381,378,382,384,390,378,382,378,379,385,388,380,382,378,386,383,382,380,380,377,386,381]
par2Preset1=[355,330,326,327,326,330,329,326,330,326,329,339,323,334,326,322,326,324,333,324,329,326,330,328,325,331,325,332,325,328,334,325,331,325,332,333,326,331,327,330,327,327,334,327,329,327,331,330,324,330,326,332,326,328,330,324,325,325,327,336,323,330,326,331,326,328,335,323,330,327,329,331,326,333,326,330,326,326,334,325,330,326,330,330,322,328,326,329,327,326,331,325,331,326,327,329,325,334,326,331,326,327,331,328,339,326,329,328,326,331,325,328,326,326,329,327,330,319,330,328,326,333,323,331,327,331,332,327,333,326,329,330,325,331,324,332,325,327,332,325,331,327,326,332,327,330,327,331,326,328,337,324,332,325,329,332,328,332,324,332,326,332,328,324,328,325,330,328,328,328,326,327,327,327,331,322,330,326,327,333,325,332,326,331,325,329,332,326,331,323,330,327,324,327,328,330,324,328,332,325,331,324,329,332,326,336,325,330,326,327,331,324,332,324,329,332,324,330,326,329,326,327,333,322,332,325,328,332,327,332,325,329,324,326,331,326,331,325,329,329,327,328,324,332,326,328,335,323,329,326,331,331,326,347,326,328,326,327,325,324,332,326,331,330,324,338,326,330,328,331,331,326,331,322,330,335,326,331,324,326,327,328,329,324,330,326,321,330,325,330,323,329,322,325,326,325,330,328,328,328,326,326,326,328,327,329,331,324,328,325,329,328,326,329,324,328,326,329,330,325,325,326,329,331,325,332,325,332,325,329,329,328,333,321,327,330,328,336,327,331,328,329,330,324,328,326,331,331,326,330,326,326,327,325,331,326,332,327,330,332,327,327,325,329,324,327,336,326,331,326,330,335,326,333,325,329,326,329,330,325,331,326,328,332,325,331,324,329,327,328,334,326,331,327,329,333,326,329,326,328,326,326,333,326,333,328,324,328,326,329,327,330,324,324,329,325,333,326,327,337,326,334,327,331,325,325,328,327,327,322,327,334,322,334,325,332,328,325,331,328,332,325,328,332,326,329,327,330,325,327,331,321,335,324,329,330,325,331,326,331,326,328,334,325,328,327,326,331,325,333,325,329,324,323,331,325,332,325,331,330,325,330,325,327,327,327,330,325,330,324,329,330,327,326,326,327,324,327,333,326,330,326,329,329,322,332,325,329,327,328,331,326,328,327,329,334,325,330,326,329,326,327,331,324,333,327,325,332,326,331,326,331,327,332,331,327,334,324,332,330,322,330,327,326,324,329,329,327,331,326,329,330,328,333,327,327,327,328,328,324,334,323,331,333,327,328,325,328,326,328,334,325,326,325,327,330,325,330,326,327,327,327,338,325,333,324,331,331,326,331,326,332,328,324,330,324,334,326,326,331,323,334,326,332,326,327,326,327,330,326,330,331,325,331,322,328,327,327,329,325,331,325,328,327,324,331,326,335,325,327,332,324,329,327,326,327,329,331,320,327,327,328,330,327,329,328,330,329,326,330,327,329,329,331,325,318,321,326,328,328,328,328,328,333,326,328,329,328,332,323,330,330,328,328,324,330,325,328,333,328,330,325,330,332,325,329,324,331,323,330,329,325,331,324,329,331,326,330,327,330,326,328,328,322,328,326,330,332,327,329,329,329,323,328,334,321,329,327,330,332,326,333,326,326,325,327,329,324,330,327,326,328,327,337,326,332,327,328,332,325,328,327,330,329,328,329,328,330,321,327,331,325,332,326,328,330,322,329,326,331,325,328,330,327,330,326,327,333,327,328,322,330,323,325,330,323,328,325,329,330,328,329,325,330,327,327,331,326,331,323,330,331,325,326,325,330,325,328,333,326,334,326,332,329,325,335,326,329,328,328,333,326,332,327,330,332,327,331,326,332,328,328,329,326,331,326,334,330,328,333,326,329,323,326,329,328,325,328,329,330,324,326,325,328,328,325,331,324,332,328,329,336,326,331,325,329,326,328,328,325,330,327,330,327,327,333,327,331,324,326,332,323,328,326,329,332,326,332,326,333,327,331,331,326,333,324,328,333,322,333,326,332,327,325,327,326,330,328,328,334,328,331,324,328,325,330,335,325,328,325,329,333,326,331,326,327,326,327,330,325,334,326,329,331,325,331,325,330,325,328,332,325,330,325,328,332,323,331,326,326,326,328,330,326,336,328,330,330,325,335,325,325,327,330,331,326,332,326,331,330,326,335,322,331,327,331,330,327,330,326,330,329,327,330,327,331,324,328,331,327,328,327,329,330,327,330,327,326,327,330,339,324,332,323,327,329,325,332,326,326,325,328,330,328,331,326,326,331,326,330,326,330,320,326,331,324,326,326,331,328,326,328,325,331,325,329,327,327,326,322,329,331,326,331,327,329,327,331,333,322,330,326,330,334,324,331,327,329,325,328,329,324,329,324,330,332,326,332,326,330,322,328,330,325,331,327,330,330,326,331,326,327,326,327,342,325,328,326,331,333,324,330,325,328,325,325,328,325,328,328,328,328,325,331,324,328,327,329,330,327,331,327,322,330,325,331,327,330,321,328,332,324,334,327,330,334,323,331,325,329,326,327,328,328,330,327,329,338,327,329,325,329,325,333,331,325,331,326,331,330,327,327,326,328,325,326,333,320,331,324,327,331,325,331,323,331,326,322,327,327,329,325,331,328,326,329,326,329,326,329,333,326,331,319,322,332,326,331,319,322,324,327,329,318,328,323,329,330,323,328,324,329,327,329,333,324,330,327,326,339,327,333,323,328,327,326,331,327,330,325,328,332,326,326,328,327,325,325,329,325,329,328,332,331,325,334,326,330,326,328,333,326,330,326,332,331,326,332,326,329,323,329,330,326,334,326,332,330,326,331,326,326,321,328,331,322,332,325,325,328,325,332,327,328,324,329,332,328,328,325,327,330,322,328,327,328,328,325,332,328,329,326,329,331,326,334,326,330,326,327,330,326,331,326,326,331,327,328,326,328,326,322,329,324,332,325,331,328,325,330,326,328,325,327,332,323,327,326,330,332,326,331,324,330,326,326,330,326,329,324,329,332,328,328,323,327,327,328,332,324,332,325,333,329,328,328,325,330,326,328,330,326,331,328,328,330,323,334,326,331,326,327,330,326,331,322,324,338,325,332,325,328,326,323,323,324,330,328,330,333,324,332,326,330,326,326,331,326,326,327,330,331,326,332,325,331,330,320,322,324,331,325,328,333,327,331,327,329,325,326,331,327,333,324,328,331,323,331,325,327,327,332,331,325,331,326,330,331,334,328,327,326,325,332,328,326,331,325,328,332,327,330,327,328,325,326,331,326,326,325,333,330,326,331,325,327,325,326,329,329,330,327,330,331,325,327,327,327,325,328,330,327,333,323,329,330,324,328,328,329,325,328,331,324,331,327,324,324,326,327,323,327,326,327,331,326,329,325,326,329]
par4Preset1=[564,296,285,294,293,291,286,296,289,285,299,300,285,290,294,311,285,301,290,282,297,286,306,286,285,290,286,318,285,299,288,286,308,288,294,290,289,298,288,300,296,286,291,286,305,287,286,304,286,290,293,288,302,287,295,286,286,293,287,288,291,292,286,298,291,285,304,289,288,298,289,292,286,307,286,293,292,287,289,290,308,292,291,299,288,286,286,286,301,286,282,304,285,305,285,285,297,288,296,285,292,286,286,302,290,297,289,295,288,290,302,295,288,288,289,293,288,285,307,287,299,287,304,286,286,293,292,293,294,284,299,287,303,295,283,285,283,288,295,286,290,296,294,288,302,286,286,304,289,293,288,294,289,288,306,288,284,303,296,301,287,294,288,285,298,292,304,287,291,300,298,294,281,302,289,290,300,296,284,288,289,293,285,287,287,285,298,286,299,285,294,288,287,302,289,294,290,286,296,286,283,292,288,293,286,282,302,285,293,287,283,285,285,294,283,293,284,286,299,285,301,282,282,285,300,294,287,286,286,288,304,287,300,300,295,289,286,296,298,303,289,295,291,287,283,286,283,299,296,287,300,286,293,288,298,287,296,291,293,294,287,285,285,302,298,298,300,292,296,291,292,288,292,300,296,285,282,285,285,304,286,295,288,283,285,283,292,285,283,300,286,287,286,284,298,286,299,302,286,310,286,291,286,294,287,284,307,286,299,291,291,288,291,308,293,288,307,285,317,285,286,300,289,297,300,301,285,298,289,285,299,289,285,292,295,290,288,282,286,287,305,285,282,301,303,292,294,296,298,288,297,283,309,286,286,299,297,293,287,286,293,283,296,283,283,292,286,292,301,294,294,288,300,286,295,296,294,290,283,308,288,297,289,285,302,285,288,296,289,289,286,300,291,298,288,285,300,286,283,286,291,292,288,298,291,285,294,286,284,300,286,315,293,299,295,285,308,285,302,292,283,313,285,291,297,287,286,299,289,292,287,286,290,292,288,300,286,286,295,303,289,288,300,288,286,289,295,286,286,287,292,285,283,286,289,289,299,292,289,284,292,289,296,286,283,290,290,290,299,285,304,286,300,296,283,309,290,292,294,295,288,283,296,285,307,292,286,294,286,296,298,288,291,300,292,287,283,286,283,303,285,292,297,284,286,287,293,286,297,291,282,300,286,297,292,288,303,292,287,301,301,292,294,292,282,282,302,287,299,287,286,290,299,298,286,282,286,287,299,301,284,286,289,294,294,295,292,297,286,286,299,290,296,292,288,293,290,294,287,281,287,287,289,290,282,297,289,292,292,288,306,286,298,285,286,285,294,302,286,293,290,284,290,293,301,289,302,290,295,297,287,282,291,282,292,286,298,290,287,289,295,287,294,292,295,285,283,285,285,297,286,294,291,289,297,286,292,295,285,297,287,287,290,299,291,287,299,294,285,286,295,291,287,295,288,295,286,289,299,286,285,292,290,291,299,285,285,292,291,285,282,286,288,285,297,291,288,286,291,292,308,285,287,284,284,298,291,287,288,286,304,286,284,292,286,298,287,298,284,295,290,282,293,286,283,292,301,289,288,296,290,288,304,310,288,289,287,295,286,301,301,291,302,288,292,293,285,299,284,296,292,301,289,297,291,285,295,289,288,292,287,292,284,282,309,283,297,298,287,304,286,302,296,285,300,288,301,289,293,288,286,299,293,310,287,285,286,284,299,289,301,291,285,293,289,297,292,285,293,283,282,292,284,287,291,293,286,295,288,293,292,291,281,289,284,295,297,287,286,290,293,286,286,291,285,301,288,301,286,290,292,283,303,298,285,288,282,305,286,299,285,292,305,283,295,289,291,292,292,298,288,285,303,301,295,303,288,301,287,294,288,299,285,286,293,303,288,290,281,303,285,288,283,283,287,286,296,302,288,300,285,316,285,287,289,288,294,285,283,299,285,298,290,299,299,290,291,294,293,287,295,287,287,299,286,293,291,288,303,291,286,289,286,286,298,292,296,285,292,287,308,285,284,290,292,293,293,289,296,302,290,287,285,302,286,300,296,301,286,283,289,299,296,287,296,286,286,311,286,285,286,285,298,287,294,300,285,298,285,297,289,285,302,297,297,289,286,297,287,294,291,286,294,286,304,295,285,299,292,305,285,294,295,281,300,286,295,294,285,299,295,304,294,286,301,300,292,286,299,290,285,287,281,305,287,286,298,288,305,295,302,288,285,293,283,287,285,288,296,303,285,286,292,290,295,295,287,283,292,304,308,288,285,309,296,288,286,290,293,287,305,302,288,290,286,302,285,300,299,291,290,285,305,287,285,293,305,290,292,288,292,285,304,297,294,291,291,289,293,296,296,288,305,286,290,300,286,295,283,294,298,293,299,285,298,285,284,285,295,294,292,307,302,286,299,289,297,285,286,286,288,293,282,285,302,288,293,298,287,292,285,294,305,293,288,283,296,301,294,297,284,292,287,311,295,286,299,290,304,295,287,294,288,293,291,299,295,295,290,286,300,282,287,290,284,287,298,283,286,297,306,285,295,288,286,301,285,300,289,286,297,286,295,300,286,304,286,292,292,284,287,300,293,290,300,288,285,290,284,297,299,283,285,297,290,299,296,288,293,296,287,286,299,289,306,286,285,287,284,305,286,298,289,288,296,296,304,285,282,286,282,301,288,287,308,286,303,285,302,297,287,291,293,295,284,283,299,293,303,289,306,290,285,302,284,294,290,288,292,300,299,290,287,293,297,293,290,299,286,294,295,287,285,297,286,307,298,297,290,288,291,287,286,291,285,291,292,292,294,309,302,287,294,286,283,288,299,290,305,287,285,288,294,288,285,306,297,289,299,295,296,295,303,284,292,286,286,292,285,309,294,289,300,297,294,288,290,293,296,292,289,294,292,298,289,298,293,296,286,289,296,293,301,295,285,287,295,298,289,299,285,292,286,312,286,301,290,285,302,299,286,284,292,291,299,288,303,281,286,304,285,298,300,287,285,297,298,299,286,292,296,287,286,301,296,290,286,283,285,287,305,297,285,286,298,291,302,294,288,283,289,286,299,288,288,290,286,289,293,289,296,285,292,284,286,308,287,291,286,306,301,298,291,285,293,286,297,293,299,294,297,285,297,282,294,286,291,299,303,291,298,298,284,287,299,296,295,297,286,305,286,293,287,303,290,294,313,297,283,306,297,291,289,298,290,288,287,296,293,296,288,307,286,304,285,295,293,285,304,285,283,290,306,294,289,300,291,302,300,297,291,300,288,298,284,290,281,301,290,291,296,282,296,287,292,288,286,300,302,286,294,287,295,293,286,290,295,293,302,290,290,298,289,294,284,288,285,300,286,294,288,288,300,297,294,288,282,286,290,296,300,296,287,286,287,286,281,288,283,294,292,300,288,284,288,288,304,297,285,298]


box_plot_data=[seqPreset1, par2Preset1, par4Preset1]
plt.boxplot(box_plot_data,0,'',patch_artist=True,labels=['seq','P=2','P=4'])
plt.show()