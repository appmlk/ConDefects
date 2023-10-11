def main():
    from collections import deque,defaultdict
    import itertools
    from math import gcd
    import sys,heapq,bisect
    sys.setrecursionlimit(10**6)
    readline=sys.stdin.readline
    MOD=998244353
    INF=10**15
    N,M=list(map(int, readline().split()))
    #10^3未満の数->999,998,
    l=[0,0]#num,length
    c=0
    for length in range(1,N+1):
        c=(c*10+1)%M
        if not c:
            l[0]=9
            l[1]=length
            break
        for i in range(2,10):
            if not (c*i)%M:
                l[0]=i
                l[1]=length
    if l==[0,0]:
        print(-1)
    else:
        print(str(l[0])*l[1])

if __name__=="__main__":
    main()