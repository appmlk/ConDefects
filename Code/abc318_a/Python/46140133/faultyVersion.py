# スタックサイズの変更
import sys
sys.setrecursionlimit(10**6)

a, b ,c = map(int, input().split())

if(a-b>0):
    print((a-b)//c+1)
else:
    print(0)