# ﷽
from collections import deque
import sys
input = lambda: sys.stdin.readline().strip()
def inlst():return [int(i) for i in input().split()]
oo=float('inf')

def solve():
    n,k=inlst()
    lst=inlst()
    q=[0]*n
    for i,e in enumerate(lst):
        q[e-1]=i

    ans=deque()   
    for i in range(n):
        cur=i
        for j in range(i-1,-1,-1):
            
            if q[j]-q[cur]>=k:
                ans.append((q[cur]+1, q[j]+1))
              
                q[cur],q[j]=q[j],q[cur]
                cur=j
             
    print(len(ans))
    for a in ans:print(*a)

    

def main():
    # for i in range(int(input())):
        solve()


if __name__ == "__main__":
    main()
