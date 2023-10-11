# Python3/Pypy3テンプレート集

#ライブラリ-------------------------------------------------------------------
from bisect import *
import heapq
import collections
from collections import deque
from queue import Queue
from itertools import groupby
import itertools
import math
import array
import string
import copy
from decimal import Decimal, ROUND_HALF_UP, ROUND_HALF_EVEN
from functools import reduce
from operator import and_, or_, xor

#便利スクリプト---------------------------------------------------------------
INF = 10**20
mod = 998244353
MOD = 10**9+7
def YesNo(b): print("Yes") if b else print("No")
def YESNO(b): print("YES") if b else print("NO")

#標準入力---------------------------------------------------------------------
import sys
sys.setrecursionlimit(10 ** 5 + 10000)
input = sys.stdin.readline    ####
def int1(x): return int(x) - 1
def II(): return int(input())
def MI(): return map(int, input().split())
def MI1(): return map(int1, input().split())
def LI(): return list(map(int, input().split()))
def LI1(): return list(map(int1, input().split()))
def LIS(): return list(map(int, SI()))
def LA(f): return list(map(f, input().split()))
def LLI(rows_number): return [LI() for _ in range(rows_number)]
def SI(): return input().strip('\n')
def MS(): return input().split()
def LS(): return list(input().strip('\n'))
def LLS(rows_number): return [LS() for _ in range(rows_number)]
def LMS(rows_number): return [MS() for _ in range(rows_number)]

#関数------------------------------------------------------------------------
###標準ライブラリ###
def ceil(a,b): #切り捨て
    return (a+b-1)//b

def inv(a,p): #aのpを法とする逆元(aとpは互いに素)
    return pow(a,p-2,p)%p

def transpose(A): #二次元配列の転置
    A_t = []
    for i in range(len(A[0])) :
        tmp = []
        for v in A :
            tmp.append(v[i])
        A_t.append(tmp)
    return A_t

def rotate_matrix(A): #グリッドを時計回りに90度回転
    return transpose(A[::-1])

def removeDuplicates_2D(A): #二次元配列の重複削除
    return list(map(list, set(map(tuple, A))))

def convert(S,c): # グリッドをの 黒 マスの点集合に変換する | S: グリッド c:黒マスがなにか(ex #,1)
    s = set()
    h = len(S)
    w = len(S[0])
    for i in range(h):
        for j in range(w):
            if S[i][j] == c:
                s.add((i, j))
    return s

def normalize(s): # グリッドの # マスの点集合を与えると最小の x 座標と最小の y 座標がともに 0 となるように平行移動して返す
    mi = min(i for (i, j) in s)
    mj = min(j for (i, j) in s)
    return set((i - mi, j - mj) for (i, j) in s)

def cumulativeSum_1D(A): #配列Aの累積和
  return list(itertools.accumulate(A))

def cumulativeSum_2D(S): #二次元配列Sの累積和 => 二次元リスト
    h = len(S)
    w = len(S[0])
    CS = [[0 for _ in range(w)]for _ in range(h)]
    CCS = [[0 for _ in range(w)]for _ in range(h)]
    for i in range(h):
        for j in range(w):
            if(j==0):
                CS[i][0] = S[i][0]
            else:
                CS[i][j] = CS[i][j-1] + S[i][j]
    for i in range(h):
        for j in range(w):
            if(i==0):
                CCS[0][j] = CS[0][j]
            else:
                CCS[i][j] = CCS[i-1][j] + CS[i][j]
    return CCS

def string_to_runLength(S: str): #文字列/リストからラングレス圧縮
    grouped = groupby(S)
    res = []
    for k, v in grouped:
        res.append((k, int(len(list(v)))))
    return res

def runLength_to_string(L: "list[tuple]"): #ラングレス圧縮から文字列 => 文字だけ
    res = ""
    for c, n in L:
        res += c * int(n)
    return res

def bfs(i,G): # i:始点
    n = len(G)
    dist = [-1] * n
    pre = [-1] * n
    que = deque()
    dist[i] = 0
    que.append(i)
    while not len(que)==0:
            v = que.popleft()
            for next_v in G[v]:
                    if dist[next_v] != -1:
                        continue
                    dist[next_v] = dist[v] + 1
                    pre[next_v] = v
                    que.append(next_v)
    return dist,pre

def bfs01(s, G): # i:始点 => dist
    N = len(G)
    dist = [INF] * N
    S = deque([s])
    T = deque()
    dist[s] = 0
    
    d = 0
    while S:
        while S:
            v = S.popleft()
            for c, w in G[v]:
                if d+c < dist[w]:
                    dist[w] = d+c
                    if c:
                        T.append(w)
                    else:
                        S.append(w)
        S, T = T, S
        d += 1
    return dist

def dijkstra(s,G): #s:始点 => cost,pre | G:タプルの中身(コスト,行先)
    n = len(G)
    hq = [(0, s)]
    heapq.heapify(hq)
    cost = [INF]*n
    cost[s]= 0
    pre = [-1] * n
    while hq:
        c,v = heapq.heappop(hq)
        if c > cost[v]:
            continue
        for d,u in G[v]:
            tmp = d+cost[v]
            if tmp < cost[u]:
                cost[u] = tmp
                pre[u] = v
                heapq.heappush(hq,(tmp,u))
    return cost, pre

def coordinates(A): # 変換表(元の値 : 座標圧縮の値),変換表2(座標圧縮の値: 元の値), 圧縮後配列
    B = sorted(set(A))
    C = { v: i for i, v in enumerate(B) }
    D = { i: v for i, v in enumerate(B) }
    E = list(map(lambda v: C[v], A))
    return C, D, E

def eng_L(): return list(string.ascii_lowercase)

def ENG_L(): return list(string.ascii_uppercase)

def bit_len(n): #bit長
    return n.bit_length()

def bit_cnt(n): # bitにしたときの1の数
    cnt = 0
    for i in range(bit_len(n)+1):
        cnt += n>>i & 1
    return cnt

def idx_le(A, x): # x 以下の最大の要素位置 / なければ "No"
    return bisect_right(A, x)-1 if bisect_right(A, x)-1 != -1 else "No"

def idx_lt(A, x):  # x 未満の最大の要素位置 / なければ "No"
    return bisect_left(A, x)-1 if bisect_right(A, x)-1 != -1 else "No"

def idx_ge(A, x): # x 以上の最小の要素位置 / なければ "No"
    return bisect_left(A, x) if bisect_left(A, x) != len(A) else "No"

def idx_gt(A, x): # x 超過の最小の要素位置 / なければ "No"
    return bisect_right(A, x) if bisect_right(A, x) != len(A) else "No"

def cnt_le(A, x): # x 以下の要素の個数
    if(idx_le(A, x) == "No"): return 0
    return idx_le(A, x) + 1

def cnt_lt(A, x): # x 未満の要素の個数
    if(idx_lt(A, x) == "No"): return 0
    return idx_lt(A, x) + 1

def cnt_ge(A, x): # x 以上の要素の個数
    return len(A) - cnt_lt(A, x)

def cnt_gt(A, x): # x 超過の要素の個数
    return len(A) - cnt_le(A, x)

###数学ライブラリ###
def allAND(A): # 配列Aの総AND
    return reduce(and_, A)

def allOR(A): # 配列Aの総OR
    return reduce(or_, A)

def allXOR(A): # 配列Aの総XOR
    return reduce(xor, A)

def allGCD(A): # 配列Aの総GCD
    if(len(A)==1):
        return A[0]
    g = math.gcd(A[0],A[1])
    for i in range(1,len(A)):
        g = math.gcd(g, A[i])
    return g

def mex(A): #配列Aのmexを求める
    B = set()
    for a in A:
        if(a>=0):
            B.add(a)
    B = list(B)
    B.sort()
    if(len(B)==0):
        return 0
    if(B[0]!=0):
        return 0
    m = 0
    for i in range(1,len(B)):
        if(B[i]==B[i-1]+1):
            m +=1
        else:
            break
    return m +1

def gcd(a,b): #aとbの最大公約数を求める
    return math.gcd(a,b)

def lcm(a,b): #aとbの最小公倍数を求める
    return a*b//gcd(a,b)

def extgcd(a, b): # a,b =>ax+by=gcd(a,b)を満たす(g,x,y) a,bが互いに素のとき、xはaのbを法とする逆元
    if b:
        d, y, x = extgcd(b, a % b)
        y -= (a // b)*x
        return d, x, y
    return a, 1, 0

def fact_L(n,mod): # [0!, 1! ..., n!] を返す
    fact = [1]
    p = 1
    for i in range(1,n+1):
        p *= i
        p %= mod
        fact.append(p)
    return fact

def bitCount_L(n): # n以下のそれぞれのbitカウントを返す
    bitcount = [0] * (n+1)
    for i in range(1,n+1):
        bitcount[i] = bitcount[i//2] + i%2
    return bitcount

def factorial(n, m=0): #nの階乗 | m:mod(デフォなし)
    if(n<0):
        return -1
    elif(n==0):
        return 1
    P = 1
    for i in range(1,n+1):
        P *= i
        if(m==0):
          continue
        P %= m
    return P

def nPr(n, r, m=0): #順列nPr
    if(n<=0 or r<0 or n<r):
        return -1
    if(r==0):
        return 1
    P = 1
    for i in range(n,n-r,-1):
        P *= i
        if(m==0):
          continue
        P %= m
    return P

def nCr(n, r, m=0): #組み合わせnCr
    if(n<r):
        return 0
    if(n==r):
        return 1
    if(n<=0 or r<0 or n<r):
        return -1
    N = 1
    for i in range(r):
        N *= n-i
        if(m==0):
            continue
        N %= m
    R = factorial(r)
    return N//R

def nCrm(n,r,m=mod): #逆元を用いた組み合わせnCr%mod
    if(n<r):
        return 0
    if(n==r):
        return 1
    if(n<=0 or r<0 or n<r):
        return -1
    over=1
    for i in range(n-r+1,n+1):
        over *= i
        over %= m
    under=1
    for i in range(1,r+1):
        under *= i
        under %= m
    return over*pow(under,m-2,m)%m

def is_prime(n): #素数判定 => True/False
    if n == 2:
        return 1
    if n == 1 or n%2 == 0:
        return 0
    m = n - 1
    lsb = m & -m
    s = lsb.bit_length()-1
    d = m // lsb
    test_numbers = [2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37]
    for a in test_numbers:
        if a == n:
            continue
        x = pow(a,d,n)
        r = 0
        if x == 1:
            continue
        while x != m:
            x = pow(x,2,n)
            r += 1
            if x == 1 or r == s:
                return 0
    return 1

def prime_L(n): #n以下の素数のリスト
    is_prime = [True] * (n + 1)
    is_prime[0] = False
    is_prime[1] = False
    for i in range(2, int(n**0.5) + 1):
        if not is_prime[i]:
            continue
        for j in range(i * 2, n + 1, i):
            is_prime[j] = False
    return [i for i in range(n + 1) if is_prime[i]]

def find_prime_factor(n):
    if n%2 == 0:
        return 2
    m = int(n**0.125)+1
    for c in range(1,n):
        f = lambda a: (pow(a,2,n)+c)%n
        y = 0
        g = q = r = 1
        k = 0
        while g == 1:
            x = y
            while k < 3*r//4:
                y = f(y)
                k += 1
            while k < r and g == 1:
                ys = y
                for _ in range(min(m, r-k)):
                    y = f(y)
                    q = q*abs(x-y)%n
                g = math.gcd(q,n)
                k += m
            k = r
            r *= 2
        if g == n:
            g = 1
            y = ys
            while g == 1:
                y = f(y)
                g = math.gcd(abs(x-y),n)
        if g == n:
            continue
        if is_prime(g):
            return g
        elif is_prime(n//g):
            return n//g
        else:
            return find_prime_factor(g)

def primeFactorization_2L(n): #2以上の整数n => [[素因数, 指数], ...]の2次元リスト
    if(n<=10**6):
        arr = []
        temp = n
        for i in range(2, int(-(-n**0.5//1))+1):
            if temp%i==0:
                cnt=0
                while temp%i==0:
                    cnt+=1
                    temp //= i
                arr.append([i, cnt])
        if temp!=1:
            arr.append([temp, 1])
        if arr==[]:
            arr.append([n, 1])
        return arr
    else:
        res = {}
        while not is_prime(n) and n > 1:
            p = find_prime_factor(n)
            s = 0
            while n%p == 0:
                n //= p
                s += 1
            res[p] = s
        if n > 1:
            res[n] = 1
        R = []
        for r in res:
            R.append([r,res[r]])
        R.sort()
        return R

def divisor_L(n): #nまでの約数のリスト
    if(n==1):
        return [1]
    if(n<=10**6):
        lower_divisors , upper_divisors = [], []
        i = 1
        while i*i <= n:
            if n % i == 0:
                lower_divisors.append(i)
                if i != n // i:
                    upper_divisors.append(n//i)
            i += 1
        return lower_divisors + upper_divisors[::-1]
    else:
        L = primeFactorization_2L(n)
        E = [[]for i in range(len(L))]
        for i in range(len(L)):
            for j in range(L[i][1]+1):
                E[i].append(L[i][0]**j)
        D = []
        for p in list(itertools.product(*E)):
            s = 1
            for v in p:
                s *= v
            D.append(s)
        D.sort()
        return D

def floorsqrt(n): # N => ⌊√N⌋
	# only for n <= 10 ** 18
	ok = 10 ** 9 + 10
	ng = 0
	while ok - ng > 1:
		t = (ok + ng) // 2
		if t * t > n: ok = t
		else: ng = t
	return ng

def decimal_to_nAry(num_10,n): #10進数からn進数へ変換する(n<=36) |int型 => str型
    str_n = []
    while num_10:
        if num_10%n >= 10:
            str_n.append(chr(num_10%n+55))
        else:
            str_n.append(str(num_10%n))
        num_10 //= n
    return "".join(str_n[::-1])

def nAry_to_decimal(X,n): #n進数から10進数へ変換する(n<=36) | str型 => int型
    num = 0
    X = X.upper()
    X = list(X)
    for i in range(len(X)):
        if(("0"<=X[i]<="9")==False):
            X[i] = str(ord(X[i]) - 55)
    for i in range(1,len(X)+1):
        num += int(X[-i]) * pow(n, (i-1))
    return num

def roundOff(x,d): #四捨五入する x:対象の数字, d:四捨五入する位(正|負) => float型の数値
    return float(Decimal(x).quantize(Decimal(f"1E{d}"), rounding=ROUND_HALF_UP))

###幾何ライブラリ###
def dsin(d): #度数法でsinを計算する
    return math.sin(math.radians(d))

def dcos(d): #度数法でcosを計算する
    return math.cos(math.radians(d))

def rotate(x,y,d,cx=0,cy=0): #P(x,y)をA(cx,cy)を中心としてに反時計回りにd°回転 => [x,y]
  nx = (x-cx)*dcos(d)-(y-cy)*dsin(d)
  ny = (x-cx)*dsin(d)+(y-cy)*dcos(d)
  return [nx+cx,ny+cy]

def findAngle(O,A,B): #∠AOBを求める(弧度法)
    s = [A[0]-O[0],A[1]-O[1]]
    t = [B[0]-O[0],B[1]-O[1]]
    u = s[0]*t[0]+s[1]*t[1]
    l = (s[0]**2+s[1]**2)**(1/2) * (t[0]**2+t[1]**2)**(1/2)
    v = u/l
    t = math.degrees(math.acos(v))
    return t

def outerProduct(Av,Bv): #二次元ベクトルの外積(=符号付面積)を求める(a×b)
    return Av[0]*Bv[1] - Bv[0]*Av[1]

def CCW(O,A,B): #Oを中心として、Aから見たAとBの位置関係を求める。
    # -1: 時計回り, 0: 一直線上, 1: 反時計回り
    s = [A[0]-O[0],A[1]-O[1]]
    t = [B[0]-O[0],B[1]-O[1]]
    op = outerProduct(s,t)
    if(op > 0): return 1
    if(op < 0): return -1
    if(op == 0): return 0

def matrixMultiplication_2D(a,b,m): #行列の掛け算(a×b) m:mod
    I,J,K,L = len(a),len(b[0]),len(b),len(a[0])
    if(L!=K):
        return -1
    c = [[0] * J for _ in range(I)]
    for i in range(I) :
        for j in range(J) :
            for k in range(K) :
                c[i][j] += a[i][k] * b[k][j]
            c[i][j] %= m
    return c

def matrixExponentiation_2D(x,n,m): #行列の累乗 (x^n) m:mod
    y = [[0] * len(x) for _ in range(len(x))]
    for i in range(len(x)):
        y[i][i] = 1
    while n > 0:
        if n & 1:
            y = matrixMultiplication_2D(x,y,m)
        x = matrixMultiplication_2D(x,x,m)
        n >>= 1
    return y

def twoCircles(A,B): #二つの円の半径の位置関係 | 引数はそれぞれ[x,y(=座標),r(=半径)]
    # 1 :　一方の円が他方の円を完全に含み、2 つの円は接していない
    # 2 :　一方の円が他方の円を完全に含み、2 つの円は接している
    # 3 :　2 つの円が互いに交差する
    # 4 :　2 つの円の内部に共通部分は存在しないが、2 つの円は接している
    # 5 :　2 つの円の内部に共通部分は存在せず、2 つの円は接していない
    x1 = A[0]
    x2 = B[0]
    y1 = A[1]
    y2 = B[1]
    r1 = A[2]
    r2 = B[2]
    d = abs((x1-x2)+1j*(y1-y2))
    if(abs(r2-r1)>d):
        return 1
    elif(abs(r2-r1)==d):
        return 2
    elif(r1+r2>d):
        return 3
    elif(r1+r2==d):
        return 4
    elif(r1+r2<d):
        return 5

###デバッグ用ライブラリ###
def TS(_str): #変数/リストに格納されている値を確認
    print('{}: {}'.format(_str, eval(_str)))

def T2d(A): #二次元配列の確認用
    for a in A:
        print(*a)

def T3d(A): #三次元配列の確認用
    for a in A:
        T2d(a)
        BR()

def BR(): #横線で区切りを入れる
    print("---")

#クラス----------------------------------------------------------------------

#カンニングペーパー-----------------------------------------------------------
'''
###標準ライブラリ###
ceil(a,b): #切り捨て
inv(a,p): #xのpを法とする逆元
transpose(A): #二次元配列の転置
rotate_matrix(A): #グリッドを時計回りに90度回転
removeDuplicates_2D(A): #二次元配列の重複削除
convert(S, c): # グリッドをの 黒 マスの点集合に変換する | S: グリッド c:黒マスがなにか(ex #,1)
normalize(s): # グリッドの # マスの点集合を与えると最小の x 座標と最小の y 座標がともに 0 となるように平行移動して返す
例)normalize(convert(h,w,A))
cumulativeSum_1D(A) #配列Aの累積和
cumulativeSum_2D(S): #二次元配列Sの累積和 => 二次元リスト
string_to_runLength(S: str) #文字列/リストからラングレス圧縮 => [(文字,個数), ...]の二次元リスト
runLength_to_string(L: "list[tuple]") #ラングレス圧縮 => 文字列
bfs(i,G) # i:始点 => dist,pre
bfs01(i,G) # i:始点 => dist
dijkstra(s,G): #s:始点 => cost,pre | G:タプルの中身(コスト,行先)
coordinates(A) # 変換表(元の値 : 座標圧縮の値),変換表2(座標圧縮の値: 元の値), 圧縮後配列
eng_L() #英小文字のリスト
ENG_L() #英大文字のリスト
bit_len(n): #bit長
bit_cnt(n): # bitにしたときの1の数
idx_le(A, x) # x 以下の最大の要素位置 / なければ "No"
idx_lt(A, x) # x 未満の最大の要素位置 / なければ "No"
idx_ge(A, x) # x 以上の最小の要素位置 / なければ "No"
idx_gt(A, x) # x 超過の最小の要素位置 / なければ "No"
cnt_le(A, x) # x 以下の要素の個数
cnt_lt(A, x) # x 未満の要素の個数
cnt_ge(A, x) # x 以上の要素の個数
cnt_gt(A, x) # x 超過の要素の個数

###数学ライブラリ###
allAND(A): # 配列Aの総AND
allOR(A): # 配列Aの総OR
allXOR(A): # 配列Aの総XOR
allGCD(A): # 配列Aの総GCD
mex(A) #配列Aのmexを求める
gcd(a,b) #aとbの最大公約数を求める
lcm(a,b) #aとbの最小公倍数を求める
extgcd(a, b): # a,b =>ax+by=gcd(a,b)を満たす(g,x,y) a,bが互いに素のとき、xはaのbを法とする逆元
fact_L(n,mod): # [0!, 1! ..., n!] を返す
bitCount_L(n): # n以下のそれぞれのbitカウントを返す
factorial(n,m) #nの階乗 | m:mod(デフォなし)
nPr(n,r,m) #順列nPr | m:mod(デフォなし)
nCr(n,r,m) #組み合わせ,nCr | m:mod(デフォなし)
nCrm(n,r,m) #逆元を用いた組み合わせnCr%mod
divisor_L(n) #nの約数のリスト
is_prime(n) #素数判定 => True/False
prime_L(n) #nまでの素数のリスト
primeFactorization_2L(n) #2以上の整数n => [[素因数, 指数], ...]の2次元リスト
floorsqrt(n): # N => ⌊√N⌋
decimal_to_nAry(num_10,n) #10進数からn進数へ変換する(n<=36) |int型 => str型
nAry_to_decimal(num_n,n) #n進数から10進数へ変換する(n<=36) | str型 => int型
roundOff(x,d): #四捨五入する x:対象の数字, d:四捨五入する位(正|負) => float型の数値

###幾何ライブラリ###
dsin(d): #度数法でsinを計算する
dcos(d): #度数法でcosを計算する
rotate(x,y,d,cx,cy): #P(x,y)をA(cx,cy)を中心としてに反時計回りにd°回転(デフォ原点) => [x,y]
findAngle(O,A,B) #∠AOBを求める(弧度法) | 引数はそれぞれ[x,y(=座標)]
outerProduct(Av,Bv) #二次元ベクトルの外積(=符号付面積)を求める(a×b) | 引数はそれぞれ[x,y(=座標)]
CCW(O,A,B) #Oを中心として、Aから見たAとBの位置関係
=> -1:時計回り, 0:一直線上, 1:反時計回り | 引数はそれぞれ[x,y(=座標)]
matrixMultiplication_2D(a,b,m) #行列の掛け算(a×b) m:mod | 引数は二次元リスト
matrixExponentiation_2D(x,n m)#行列の累乗 (x^n) m:mod | 引数は二次元リスト
twoCircles(A,B): #二つの円の半径の位置関係 | 引数はそれぞれ[x,y(=座標),r(=半径)]
=> 1, 2, 3, 4, 5 各数字に対応する位置関係の説明は上記参照

###デバッグ用ライブラリ###
TS(_str) # 変数/リストに格納されている値を確認 => 〇〇:××
T2d(A): # 二次元配列の確認用
T3d(A): # 三次元配列の確認用
BR() # 横線で区切りを入れる

###文法チートシート###
|S|<x => "0"*(x-|S|) + S : str(n).zfill(x)
全部大文字に変換：str.upper()
全部小文字に変換：str.lower()
先頭のみ大文字に変換：str.capitalize()
各単語の先頭のみ大文字に変換（タイトルケース）:str.title()
大文字と小文字を入れ替える：str.swapcase()
文字 → ASCIIコード ord(s)
ASCIIコード → 文字 chr(x)
ASCII表
65:A ~ 90:Z
97:a ~ 122:z
'''

#PyPyで再帰関数を用いる場合はコメントを外す----------------------------------
# import pypyjit
# pypyjit.set_param('max_unroll_recursion=-1')

#----------------------------------------------------------------------------

x,a,d,n = MI()

m = a
M = a+d*(n-1)

ans = min(abs(m-x),abs(M-x))
if(d==0):
    print(ans)
    exit()
k = (x+d-a)//d

for i in range(max(1,k-5),min(k+5,n+1)):
    v = a + d*(i-1)
    ans = min(ans,abs(v-x))


print(ans)
