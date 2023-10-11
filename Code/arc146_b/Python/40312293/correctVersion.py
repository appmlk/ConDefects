import sys

sys.setrecursionlimit(10000000)
from collections import deque, namedtuple

## Input crap
infile = sys.stdin
intokens = deque()
def getTokens(): 
    while not intokens:
        for c in infile.readline().rstrip().split() :
            intokens.append(c)    
def gs(): getTokens(); return intokens.popleft()
def gi(): return int(gs())
def gf(): return float(gs())
def gbs(): return [c for c in gs()]
def gis(n): return [gi() for i in range(n)]
def ia(m): return [0] * m
def iai(m,v): return [v] * m
def twodi(n,m,v): return [iai(m,v) for i in range(n)]
def fill2(m) : r = gis(2*m); return r[0::2],r[1::2]
def fill3(m) : r = gis(3*m); return r[0::3],r[1::3],r[2::3]
def fill4(m) : r = gis(4*m); return r[0::4],r[1::4],r[2::4],r[3::4]
MOD = 998244353
##MOD = 1000000007

def main() :
    if len(sys.argv) > 1 : global infile; infile = open(sys.argv[1],'rt')
    ## PROGRAM STARTS HERE
    N,M,K = gi(),gi(),gi(); A = gis(N)

    ans = 0; working = [0]*N
    for pos in range(31,-1,-1) :
        cand = ans | 1<<pos
        for i,a in enumerate(A) :
            m = cand & (~a)
            if m == 0 : working[i] = 0
            else :
                mask = (1<<(m.bit_length()))-1
                working[i] = (mask&cand) - (mask&a)
        working.sort(); incs = 0
        for i in range(K):
            incs += working[i]
            if incs > M : break
        if incs <= M : ans = cand
    print(ans)

if __name__ == "__main__" :
    main()

