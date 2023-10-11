import sys
from collections import deque, defaultdict
from math import *
from bisect import bisect_left, bisect_right
input = sys.stdin.readline

def get(s1, s2):
    global n 
    i = 0
    while i < len(s1) and (s1[i] == '?' or s1[i] == s2[i]):
        i += 1
    if i == len(s1): return n
    else:
        if s1[i] < s2[i]:
            v = 0
            for j in range(i):
                v = v * 2 + int(s2[j])
            for j in range(i, len(s1)):
                if s1[j] == '0': v = v * 2 
                else:  v = v * 2 + 1
            return v
        else:
            j = i - 1
            while j >= 0 and (s1[j] != '?' or s2[j] == '0'):  
                j -= 1
            if j == -1: return -1
            else:
                v = 0
                for k in range(j):
                    v = v * 2 + int(s2[k])
                v = v * 2 
                for k in range(j+1, len(s1)):
                    if s1[k] == '0': v = v << 1
                    else: v = v * 2 + 1
                return v 

if __name__ == "__main__":
    s1 = input().strip()
    n = int(input())
    s2 = bin(n)[2:]
    
    i = 0
    while i < len(s1) - 1 and s1[i] == '0':
        i += 1
    s1 = s1[i:]
    v = 0
    if len(s1) < len(s2):
        for x in s1:
            if x == '0': v = v * 2
            else: v = v * 2 + 1 
    elif len(s1) == len(s2):
        v = get(s1, s2)
    else:
        le = len(s1) - len(s2)
        if s1[:le].count('1') > 0: v = -1
        else:
            v = get(s1[le:], s2)
    print(v)

    
    
        

    
            
