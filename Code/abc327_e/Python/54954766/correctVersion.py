import sys
import math
import os
import copy
import time
from fractions import Fraction
import copy
import sys
from collections import defaultdict
from math import sqrt
from decimal import Decimal, getcontext

if os.getenv('ENVIRONMENT') == 'local':
    sys.stdin = open("input.txt", "r")
    sys.stdout = open("output.txt", "w")
    sys.stderr = open("error.txt", "w")

def ra(a):
    return range(a)
def en(a):
    return enum(a)
def mat(n,m):
    return [[0 for _ in range(m)] for _ in range(n)]
def help(n):
    if os.getenv('ENVIRONMENT') == 'local':
        print("siu:",n)

# map(int,input().split())
# int(input())
# for index, value in reversed(list(enumerate(my_list))):
# for index, value in enumerate(my_list)):map(int,input().split())
# for l in sys.stdin:
# list append:siu.append(j)
# map : mp = {"pink": 0, "blue": -1e9}
# log is naturally ln if not specified
# lines = sys.stdin.readlines()
# try: except EOFError: break
#    def compute_poly(x):
#      nonlocal a
# Remove the last element of the list removed_element = my_list.pop()
# new_string = my_string[:-1]  # Remove the last character
# math.gcd

def solve():
    n = int(input())
    v = list(map(int,input().split()))
    dp = [0.0 for i in range(n+1)]
    for i in range(0,n):
        for j in range(i+1, 0, -1):
            dp[j] = max(dp[j], dp[j-1]*0.9+v[i])   
    ans = -1e18
    for i, j in enumerate(dp):
        if i == 0:
            continue
        ans = max(ans, j/(1-0.9**i)*(1-0.9)-1200/(i**0.5))
    print(ans)



start_time = time.time()
# t = int(input())
# for i in range(t):
solve()
if os.getenv('ENVIRONMENT') == 'local':
    elapsed_time = time.time() - start_time
    print("Timepy:", elapsed_time, "seconds")

