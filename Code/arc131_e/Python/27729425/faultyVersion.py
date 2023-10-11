
"""

自分より小さいものに有向辺を貼るとする。
その全てに関して、同じ色を使うとする。
すると、3頂点のうち、最大は必ず同じ辺で入出

そのような組み合わせを見つけるとよい。

N-1
N-2
N-3
N-4
...
1

の中からいくつか取り、
N(N-1)/6 を2こ作りたい

雑に行ける？

dp[i][j] = 和が (i,j) の場合の前の推移

"""

import sys
from sys import stdin
import pprint

N = int(stdin.readline())

if ( N*(N-1)//2 ) % 3 != 0:
    print ("No")
    sys.exit()

e = N * (N-1) // 6

#print (e)

dp = [ [ [ None ] * (e+1) for i in range(e+1) ] for k in range(N)]
dp[0][0][0] = (0,0,0)

for pl in range(N-1):

    for i in range(e+1):
        for j in range(e+1):

            if dp[pl][i][j] == None:
                continue

            dp[pl+1][i][j] = (pl,i,j)
            dp[pl+1][i][j] = (pl,i,j)
            
            #iに加える
            if i + pl+1 <= e:
                dp[pl+1][i+pl+1][j] = (pl,i,j)
            if j + pl+1 <= e:
                dp[pl+1][i][j+pl+1] = (pl,i,j)

#pprint.pprint (dp)

if dp[N-1][e][e] == None:
    print ("No")
    sys.exit()

ans = [ None ] * (N-1)

i,x,y = N-1,e,e

while x+y != 0:

    nexi,nexx,nexy = dp[i][x][y]
    #print (nexx,nexy)

    print (nexx,nexy)

    diff = None
    if x != nexx:
        diff = x - nexx
        ans[(N-1) - diff] = "R" * diff
    elif y != nexy:
        diff = y - nexy
        ans[(N-1) - diff] = "W" * diff

    #print (x,y,nexx,nexy,diff)

    i,x,y = nexi,nexx,nexy

for i in range(N-1):
    if ans[i] == None:
        ans[i] = "B" * (N-1-i)

print ("Yes")
for i in ans:
    print (i)