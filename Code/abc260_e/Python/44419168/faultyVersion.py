############################################################################################
import bisect,collections,copy,heapq,itertools,math,string,sys,queue,time,random
from decimal import Decimal
def I(): return input()
def IS(): return input().split()
def II(): return int(input())
def IIS(): return list(map(int,input().split()))
def LIIS(): return list(map(int,input().split()))

def make_divisors(n):
    lower_divisors , upper_divisors = [], []
    i = 1
    while i*i <= n:
        if n % i == 0:
            lower_divisors.append(i)
            if i != n // i:
                upper_divisors.append(n//i)
        i += 1
    return lower_divisors + upper_divisors[::-1]

import math

def prime_numbers(n):
    prime = [True for i in range(n+1)]
    prime[0] = False
    prime[1] = False

    sqrt_n = math.ceil(math.sqrt(n))
    for i in range(2, sqrt_n):
        if prime[i]:
            for j in range(2*i, n+1, i):
                prime[j] = False
    numbers=[]
    for i in range(2,n+1):
        if prime[i]:
            numbers.append(i)

    return numbers

def factorization(n):
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
 
INF=10**18
MOD=998244353
MOD2=10**9+7
#sys.setrecursionlimit(500005)
def bit_count(x):
    return bin(x).count("1")
def yesno(f):
    if f:print("Yes")
    else:print("No")
####################################################
n,m=IIS()
li=[-1]*(m+1)
idx=1
st=set()
for i in range(n):
    a,b=IIS()
    li[a]=b
    st.add(b)
    idx=max(idx,a)
cnt=[]
cnt.append((m-idx+1,m))
for i in range(1,m):
    if i in st:break
    idx=max(idx,li[i])
    idx=max(i+1,idx)
    cnt.append((m-idx+1,m-i))
ans=[0]*(m+1)
for i,j in cnt:
    ans[j]+=1
    ans[j-i]-=1
cnt=0
for i in range(1,m+1)[::-1]:
    cnt+=ans[i]
    ans[i]=cnt
print(*ans[1:])


